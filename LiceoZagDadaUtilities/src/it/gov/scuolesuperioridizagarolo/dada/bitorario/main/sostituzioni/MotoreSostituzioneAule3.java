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
    public static void doTask(AbstractVincoliSostituzioni l) throws IOException {
        throw new IllegalArgumentException("Specificare cartella contenente dati");
    }

    public static void doTask(AbstractVincoliSostituzioni l, File folderInput, File folderOutput, FilterAule[] ff, boolean showTimetablesChanges) throws IOException {
        final BitOrarioGrigliaOrario orarioInModifica = MainParserGeneraStampeOrario.parsingDefaultFileOrarioAuleClassi(folderInput);
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
        SostituzioneAuleEngine3.spostamentiPerAuleNonDisponibili(orarioInModifica, l1, ff);
        l.postOrarioBeforeFinalCheck(orarioInModifica, l1);


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\n" +
                "\n********************************************************************************************");
        System.out.println("********************** Controllo vincoli utente");
        System.out.println("********************************************************************************************");
        final java.util.List<String> strings = l1_clone.checkAllNotPassed(orarioInModifica.getLezioni(), orarioInModifica);
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
        final String subName2 = dalSplit[2] + "." + dalSplit[1] + "." + dalSplit[0] + "_" + alSplit[2] + "." + alSplit[1] + "." + alSplit[0];

        final String subName = dal.replace("/", ".") + "_" + al.replace("/", ".") + ".json.zip";
        String nomeFile = "timetable_" + s2 + "_" + subName;

        //report
        {
            orarioInModifica.setTitolo("Orario LICEO SCIENTIFICO dal " + dal + " al " + al);
            final File root = new File(folder, "html/" + subName2);
            root.mkdirs();

            final BitOrarioGrigliaOrario orarioStandard = !showTimetablesChanges ? orarioInModifica : MainParserGeneraStampeOrario.parsingDefaultFileOrarioAuleClassi(folderInput);
            orarioStandard.setReadOnly(true);

            final NoteVariazioniBitOrarioGrigliaOrario note = NoteVariazioniBitOrarioGrigliaOrario.generateDifferenze(orarioStandard, orarioInModifica);


            new HtmlOutputOrario_perAule().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_AULE_A3" + ".html"), EPaperFormat.A3);
            new HtmlOutputOrario_perClassi().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_CLASSI_A3" + ".html"), EPaperFormat.A3);
            new Report_perDocentiDaConvocareRidotto().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_DOCENTI_DA_CONVOCARE_RIDOTTO_A3" + ".html"), EPaperFormat.A3);
            new Report_perDocentiIngleseRidotto().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_DOCENTI_INGLESE_RIDOTTO_A3" + ".html"), EPaperFormat.A3);
            new Report_perDocentiRidotto().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_DOCENTI_RIDOTTO_A3" + ".html"), EPaperFormat.A3);
            new Report_perClasseRidotto().print(orarioInModifica, note, new File(root, subName2 + "_ORARIO_CLASSI_RIDOTTO_A3_ORIZZ" + ".html"), EPaperFormat.A3);
            new Report_perAuleVuote().print(orarioInModifica, new File(root, subName2 + "_AULE VUOTE" + ".html"));
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
