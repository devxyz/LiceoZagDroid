package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.BitOrarioOraLezioneJSonConverter;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.impl.CheckForClassroom_CoerenzaCapacitàClassiAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.SostituzioneAuleEngine3;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.MainParserGeneraStampeOrario;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.output.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.AbstractLessonConstraint;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_OreConsecutiveStessaAula;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;
import it.gov.scuolesuperioridizagarolo.util.C_DateUtil;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

//import dada.bitorario.output.*;

/**
 * definendo alcune aule non utilizzabili si cercano nuove aule da assegnare
 */
public class MotoreSostituzioneAule3 {


    @Deprecated
    public static void doTaskFromTXT(AbstractVincoliSostituzioni l) throws IOException {
        throw new IllegalArgumentException("Specificare cartella contenente dati");
    }


    public static void doTaskFromJSon(AbstractVincoliSostituzioni l, File jSonFile, File folderOutput, FilterAule[] ff, boolean showTimetablesChanges, boolean vincoliOreConsecutive) throws IOException {
        final BitOrarioGrigliaOrario orarioInModifica = MainParserGeneraStampeOrario.readJsonFileOrarioAuleClassi(jSonFile);
        final BitOrarioGrigliaOrario orarioStandard = !showTimetablesChanges ? orarioInModifica : MainParserGeneraStampeOrario.readJsonFileOrarioAuleClassi(jSonFile);
        if (showTimetablesChanges)
            orarioStandard.setReadOnly(true);
        _doTask(l, folderOutput, ff, orarioInModifica, orarioStandard, vincoliOreConsecutive, showTimetablesChanges);
    }

/*
    public static void doTaskFromTXT(AbstractVincoliSostituzioni l, File folderInput, File folderOutput, FilterAule[] ff, boolean showTimetablesChanges, boolean vincoliOreConsecutive) throws IOException {
        final BitOrarioGrigliaOrario orarioInModifica = MainParserGeneraStampeOrario.parsingDefaultFileOrarioAuleClassi(folderInput);
        final BitOrarioGrigliaOrario orarioStandard = !showTimetablesChanges ? orarioInModifica : MainParserGeneraStampeOrario.parsingDefaultFileOrarioAuleClassi(folderInput);
        if (orarioInModifica != orarioStandard)
            orarioStandard.setReadOnly(true);
        _doTask(l, folderOutput, ff, orarioInModifica, orarioStandard, vincoliOreConsecutive, showTimetablesChanges);
    }
*/

    public static void doTaskFromTXT(AbstractVincoliSostituzioni l,
                                     File fileAllocazioneAule, File file_disposizione_docenti, File file_disposizione_da_orario_orizzontale_opzional,
                                     File fileSostegnoOrizzontale, File fileProgetti, File folderOutput, FilterAule[] ff,
                                     boolean showTimetablesChanges, boolean vincoliOreConsecutive) throws IOException {
        String titolo = "Orario Liceo Scientifico";
        final BitOrarioGrigliaOrario orarioInModifica = MainParserGeneraStampeOrario.parsingFileOrarioAuleClassi(titolo, fileAllocazioneAule, file_disposizione_docenti, file_disposizione_da_orario_orizzontale_opzional, fileSostegnoOrizzontale, fileProgetti, false);
        final BitOrarioGrigliaOrario orarioStandard = MainParserGeneraStampeOrario.parsingFileOrarioAuleClassi(titolo, fileAllocazioneAule, file_disposizione_docenti, file_disposizione_da_orario_orizzontale_opzional, fileSostegnoOrizzontale, fileProgetti, false);
        orarioStandard.setReadOnly(true);

        _doTask(l, folderOutput, ff, orarioInModifica, orarioStandard, vincoliOreConsecutive, showTimetablesChanges);
    }

    private static void _doTask(
            AbstractVincoliSostituzioni l, File folderOutput, FilterAule[] ff,
            BitOrarioGrigliaOrario orarioInModifica,
            BitOrarioGrigliaOrario orarioStandard,
            boolean vincoliOreConsecutive,
            boolean showTimetablesChanges) throws IOException {
        l.preOrarioBeforeAssignment(orarioInModifica);

        System.out.println("\n" +
                "\n********************************************************************************************");
        System.out.println("********************** Controllo INIZIALE multiplo stessa aula");
        System.out.println("********************************************************************************************");
        final String s22 = AbstractVincoliSostituzioni.checkAuleMultiple(orarioInModifica);
        System.out.println(s22);
        System.out.println();

        System.out.println("\n" +
                "\n********************************************************************************************");
        System.out.println("********************** predisposizione vincoli");
        System.out.println("********************************************************************************************");

        final LessonConstraintContainer l1 = new LessonConstraintContainer();
        final LessonConstraintContainer l1_clone = l1.clone();
        l.invoke(orarioInModifica, l1);
        String dal = l.getDal();
        String al = l.getAl();


        final String[] dalSplit = dal.split("/");
        final String[] alSplit = al.split("/");

        final File folder = folderOutput;//new File("/Users/stefano/Dropbox/orari/");
        folder.mkdirs();

        //avvia elaborazione
        System.out.println("********************************************************************************************");
        System.out.println("********************** Risoluzione vincoli");
        System.out.println("********************************************************************************************");
        TreeMap<AbstractLessonConstraint, Integer> numerositaVincoliNonSoddisfatti = new TreeMap<>();
        SostituzioneAuleEngine3.spostamentiPerAuleNonDisponibili(orarioInModifica, l1, ff, numerositaVincoliNonSoddisfatti, vincoliOreConsecutive);
        l.postOrarioBeforeFinalCheck(orarioInModifica, l1);


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\n" +
                "\n********************************************************************************************");
        System.out.println("********************** Controllo vincoli utente");
        System.out.println("********************************************************************************************");
        final List<String> strings = l1_clone.checkAllNotPassed(orarioInModifica.getLezioni(), orarioInModifica);
        for (String x : strings) {
            System.out.println("  >> NON RISOLTO: " + x);
        }

        //controllo correttezza riassegnazione
        System.out.println("\n\n********************************************************************************************");
        System.out.println("********************** Controllo capacità aule");
        System.out.println("********************************************************************************************");
        new CheckForClassroom_CoerenzaCapacitàClassiAule().printReport(orarioInModifica);

        System.out.println("\n" +
                "\n********************************************************************************************");
        System.out.println("********************** Controllo uso multiplo stessa aula - per errore programma");
        System.out.println("********************************************************************************************");
        final String s1 = AbstractVincoliSostituzioni.checkAuleMultiple(orarioInModifica);
        System.out.println(s1);

        System.out.println("\n" +
                "\n********************************************************************************************");
        System.out.println("********************** Controllo stessa aula per ore consecutive su stessa materia/classe/insegnante");
        System.out.println("********************************************************************************************");
        {
            final List<LessonConstraint_OreConsecutiveStessaAula> genera = LessonConstraint_OreConsecutiveStessaAula.genera(orarioInModifica);
            final ArrayList<BitOrarioOraLezione> lezioni = orarioInModifica.getLezioni();

            for (LessonConstraint_OreConsecutiveStessaAula x : genera) {
                if (!x.checkAll(lezioni, orarioInModifica))
                    System.out.println("   >> vincolo non soddisfatto: " + x);
            }
        }


        final String s2 = C_DateUtil.toYYYYMMDDhhmmss(new Date());
        final String subName2 = "";//dalSplit[2] + "." + dalSplit[1] + "." + dalSplit[0] + "_" + alSplit[2] + "." + alSplit[1] + "." + alSplit[0];

        final String subName = dal.replace("/", ".") + "_" + al.replace("/", ".") + ".json.zip";
        String nomeFile = "timetable_" + s2 + "_" + subName;

        //report
        {


            final NoteVariazioniBitOrarioGrigliaOrario note;
            if (showTimetablesChanges) {
                note = NoteVariazioniBitOrarioGrigliaOrario.generateDifferenze(orarioStandard, orarioInModifica);
            } else {
                note = new NoteVariazioniBitOrarioGrigliaOrario();
            }

            orarioInModifica.setTitolo("Orario LICEO SCIENTIFICO dal " + dal + " al " + al);

            if (false) {
                final File rootoriginale = new File(folder, "originale/" + subName2);
                rootoriginale.mkdirs();
                generaFileOutput(orarioStandard, orarioStandard, subName2, note, rootoriginale);
            }


            {
                final File rootmodificato = new File(folder, "html" + dal.replace("/", ".").replace("-", ".") + "-" + al.replace("/", ".").replace("-", ".") + "/" + subName2);
                rootmodificato.mkdirs();
                if (showTimetablesChanges)
                    generaFileOutput(orarioInModifica, orarioStandard, subName2, note, rootmodificato);
                else
                    generaFileOutput(orarioInModifica, orarioInModifica, subName2, note, rootmodificato);
            }


        }

        System.out.println("Controllo nome " + nomeFile);
        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(subName)) {

                System.err.println("E' gia' presente un file con le date specificate " + file.getName() + " che e' stato rinominato");
                file.renameTo(new File(file.getAbsoluteFile() + ".bak"));
                //JOptionPane.showMessageDialog(null, "ERRORE!!!!");
                //Desktop.getDesktop().open(folder);
                //return;
            }
        }


        final String s = BitOrarioOraLezioneJSonConverter.toJSon(orarioInModifica);
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(new File(folder, nomeFile)));
        out.putNextEntry(new ZipEntry("timetable.json"));
        out.write(s.getBytes());
        out.close();

        FileWriter list = new FileWriter(new File(folder, "timetables.txt"));
        final File[] files = folder.listFiles();
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (File file : files) {
            if (file.getName().endsWith(".json.zip")) {
                list.write(file.getName());
                list.write("\n");
            }
        }

        list.close();

        Desktop.getDesktop().open(folder);
    }

    private static void generaFileOutput(BitOrarioGrigliaOrario orarioInModifica, BitOrarioGrigliaOrario orarioStandard, String subName2, NoteVariazioniBitOrarioGrigliaOrario note, File root) throws IOException {
        //OK
        new HtmlOutputOrario_perAule().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_AULE_DETTAGLIATO.A3" + ".html"), EPaperFormat.A3);
        new HtmlOutputOrario_perAulePerAree().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_AULE_DETTAGLIATO_PER_AREE.A3" + ".html"), EPaperFormat.A3);
        //ok
        new HtmlOutputOrario_perClassi().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_CLASSI_DETTAGLIATO.A3" + ".html"), EPaperFormat.A3);

        //new Report_perDocentiDaConvocareRidotto().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_DOCENTI_DA_CONVOCARE_RIDOTTO_A3" + ".html"), EPaperFormat.A3);
        new Report_perDocentiRaggruppatiPerMateria().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_DOCENTI_PER_MATERIA_RIDOTTO_A3" + ".html"), EPaperFormat.A3);

        //OK
        new Report_perDocentiRidotto().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_DOCENTI_RIDOTTO.A3" + ".html"), EPaperFormat.A3);
        new Report_perDocentiRidottoTestuale().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_DOCENTI_RIDOTTO" + ".txt"), EPaperFormat.A3);

        new Report_perDocentiRidotto(false).print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_DOCENTI_RIDOTTO_NO_AULE.A3" + ".html"), EPaperFormat.A3);
        new Report_perClasseRidotto2().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_CLASSI_RIDOTTO.A3" + ".html"), EPaperFormat.A3);
        new Report_perClasseSoloAula().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_CLASSI_SOLO_AULA.A3" + ".html"), EPaperFormat.A3);
        //new Report_perClasseRidottoSplitGiorni().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_CLASSI_RIDOTTO_3FOGLI_ORIZZ" + ".html"), EPaperFormat.A3);
        new Report_perClasseRidotto2(false).print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_CLASSI_RIDOTTO_NO_AULE.A3" + ".html"), EPaperFormat.A3);
        new HtmlOutputOrario_perDisposizioni().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_DISPOSIZIONI.A3" + ".html"), EPaperFormat.A3);
        new HtmlOutputOrario_perCompresenze().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_COMPRESENZE.A3" + ".html"), EPaperFormat.A3);

        //OK
        new Report_perAuleVuote().print(orarioInModifica, new File(root, subName2 + "_AULE_VUOTE.A3." + ".html"));


        new Report_perVariazioniAule().print(orarioInModifica, orarioStandard, new File(root, subName2 + "_VARIAZIONI" + ".html"));

        FileWriter w4 = new FileWriter(new File(root, "qrcode.html"));
        w4.write(printQCODEClassi());
        w4.close();


        final File folder_docenti = new File(root, "DOCENTI");
        folder_docenti.mkdirs();
        new HtmlMobile_Docenti().print(orarioInModifica, orarioStandard, folder_docenti);

        final File folder_classi = new File(root, "CLASSI");
        folder_classi.mkdirs();
        new HtmlMobile_Classi().print(orarioInModifica, orarioStandard, folder_classi);

        final File folder_aule = new File(root, "AULE");
        folder_aule.mkdirs();
        new HtmlMobile_Aule().print(orarioInModifica, orarioStandard, folder_aule);

        final File folder_extra = new File(root, "EXTRA");
        folder_extra.mkdirs();
        new HtmlMobile_AuleVuote().print(orarioInModifica, new File(folder_extra, "AULE-VUOTE"));
        new HtmlOutputOrario_perAule().print(orarioInModifica, note, new File(folder_extra, "AULE-OCCUPATE"), EPaperFormat.A4);
        new HtmlMobile_Variazioni().print(orarioStandard, note, new File(folder_extra, "VARIAZIONI"));
    }


    private static String printQCODEClassi() {
        StringBuilder sb = new StringBuilder();
        final ArrayList<ClassData> allClasses = ClassesAndRoomContainer.getAllClasses();
        sb.append("<html><body>");
        for (ClassData c : allClasses) {

            // sb.append("<div style='display: block; page-break-before: always;'></div>\n");
            sb.append("<hr>");
            sb.append("<h1 style='font-size:40px;font-weight:bolder'>Orario delle lezioni aggiornato Classe " + c.className + "</h1>\n");
            sb.append("<img src='https://chart.googleapis.com/chart?cht=qr&chl=http%3A%2F%2Fwww.scuolesuperioridizagarolo.gov.it%2Forario%2Forario2.php%3Fclasse%3D" + c.className + "&chs=400x400&choe=UTF-8&chld=L|2' alt=''>\n");

        }
        sb.append("</body></html>");
        return sb.toString();
    }


}
