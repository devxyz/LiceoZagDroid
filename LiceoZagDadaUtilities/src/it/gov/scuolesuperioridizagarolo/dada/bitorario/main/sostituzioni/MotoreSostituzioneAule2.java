package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.BitOrarioOraLezioneJSonConverter;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.impl.CheckForClassroom_CoerenzaCapacitàClassiAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.SostituzioneAuleEngine2;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.MainParserGeneraStampeOrario;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.output.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;
import it.gov.scuolesuperioridizagarolo.util.C_DateUtil;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

//import dada.bitorario.output.*;

/**
 * definendo alcune aule non utilizzabili si cercano nuove aule da assegnare
 */
@Deprecated
public class MotoreSostituzioneAule2 {


    public static void doTask(AbstractVincoliSostituzioni l) throws IOException {
        throw new IllegalArgumentException("Specificare cartella contenente dati");
    }
    public static void doTask(AbstractVincoliSostituzioni l, File folderInput, File folderOutput) throws IOException {
        final BitOrarioGrigliaOrario orarioTotale = MainParserGeneraStampeOrario.parsingDefaultFileOrarioAuleClassi(folderInput);
        final BitOrarioGrigliaOrario orarioStandard = MainParserGeneraStampeOrario.parsingDefaultFileOrarioAuleClassi(folderInput);
        final LessonConstraintContainer l1 = new LessonConstraintContainer();
        l.invoke(orarioTotale, l1);
        String dal = l.getDal();
        String al = l.getAl();


        final String[] dalSplit = dal.split("/");
        final String[] alSplit = al.split("/");

        folderOutput.mkdirs();


        //avvia elaborazione
        final String html = SostituzioneAuleEngine2.spostamentiPerAuleNonDisponibili(orarioTotale, l1, "Assegnazione aule dal " + dal + " al " + al);
        final java.util.List<String> strings = l1.checkAllNotPassed(orarioTotale.getLezioni(), orarioTotale);
        System.out.println("ESITO CHECK FINALE");
        for (String x : strings) {
            System.out.println(" > " + x);
        }

        //controllo correttezza riassegnazione
        System.out.println("Controllo occupazione aule");
        new CheckForClassroom_CoerenzaCapacitàClassiAule().printReport(orarioTotale);

        System.out.println("Controllo uso multiplo aule");
        final String s1 = AbstractVincoliSostituzioni.checkAuleMultiple(orarioTotale);
        System.out.println(s1);


        final String s2 = C_DateUtil.toYYYYMMDDhhmmss(new Date());
        final String subName2 = dalSplit[2] + "." + dalSplit[1] + "." + dalSplit[0] + "_" + alSplit[2] + "." + alSplit[1] + "." + alSplit[0];

        final String subName = dal.replace("/", ".") + "_" + al.replace("/", ".") + ".json.zip";
        String nomeFile = "timetable_" + s2 + "_" + subName;

        //report
        {
            orarioTotale.setTitolo("Modifiche aule dal " + dal + " al " + al);
            final File root = new File(folderOutput, "html/" + subName2);
            root.mkdirs();

            FileWriter w = new FileWriter(new File(root, subName2 + "_VARIAZIONI" + ".html"));
            w.write(html);
            w.close();

            final NoteVariazioniBitOrarioGrigliaOrario note = NoteVariazioniBitOrarioGrigliaOrario.generateDifferenze(orarioStandard, orarioTotale);


            new HtmlOutputOrario_perAule().print(orarioTotale, note, new File(root, subName2 + "_ORARIO_AULE_A3" + ".html"), EPaperFormat.A3);
            new HtmlOutputOrario_perClassi().print(orarioTotale, note, new File(root, subName2 + "_ORARIO_CLASSI_A3" + ".html"), EPaperFormat.A3);
            new Report_perDocentiRidotto().print(orarioTotale, note, new File(root, subName2 + "_ORARIO_DOCENTI_RIDOTTO_A3" + ".html"), EPaperFormat.A3);
            new Report_perAuleVuote().print(orarioTotale, new File(root, subName2 + "_AULE VUOTE" + ".html"));

            FileWriter w4 = new FileWriter(new File(root, "qrcode.html"));
            w4.write(printQCODEClassi());
            w4.close();


            final File folder_docenti = new File(root, "DOCENTI");
            folder_docenti.mkdirs();
            new HtmlMobile_Docenti().print(orarioTotale, orarioStandard, folder_docenti);

            final File folder_classi = new File(root, "CLASSI");
            folder_classi.mkdirs();
            new HtmlMobile_Classi().print(orarioTotale, orarioStandard, folder_classi);

            final File folder_aule = new File(root, "AULE");
            folder_aule.mkdirs();
            new HtmlMobile_Aule().print(orarioTotale, orarioStandard, folder_aule);

            final File folder_extra = new File(root, "EXTRA");
            folder_extra.mkdirs();
            new HtmlMobile_AuleVuote().print(orarioTotale, new File(folder_extra, "AULE-VUOTE"));
            new HtmlOutputOrario_perAule().print(orarioTotale, note, new File(folder_extra, "AULE-OCCUPATE"), EPaperFormat.A4);


            //variazioni
            TreeSet<BitOrarioOraLezione> lezioniModificate = new TreeSet<>(note.getMapNote().keySet());
            StringBuffer sb = new StringBuffer();
            sb.append("<table border=1>");
            sb.append("<tr>");
            sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Giorno</td>\n");
            sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Orario</td>\n");
            sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Classe</td>\n");
            sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Docente</td>\n");
            sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Lezione</td>\n");
            sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Precedente Aula</td>\n");
            sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Nuova Aula</td>\n");
            sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Note</td>\n");
            sb.append("</tr>\n");
            String[] colori = new String[]{"YELLOW", "PINK"};

            int i = 1;
            String prec = "";
            for (BitOrarioOraLezione x : lezioniModificate) {
                String docente = (x.getDocentePrincipale() + (x.getDocenteCompresenza() != null ? " " + x.getDocenteCompresenza() : " ")).trim();
                String precAula = "";
                final BitOrarioOraLezione precLezione = orarioStandard.getLezioneConDocente(x.getOra(), x.getGiorno(), x.getDocentePrincipale());
                if (precLezione != null && precLezione.getNomeAula() != null) {
                    precAula = precLezione.getNomeAula();
                }

                if (!prec.equals(x.getGiorno().getNome())) {
                    i++;
                    prec = x.getGiorno().getNome();
                }

                String color = colori[i % colori.length];
                sb.append("<tr>");
                sb.append("<td style='font-size:120%;font-weight:bolder;background-color:" + color + "'>" + x.getGiorno().getNome().toUpperCase() + "</td>\n");
                sb.append("<td style='font-size:120%;font-weight:bolder;background-color:" + color + "'>" + x.getOra().getProgressivOra() + "</td>\n");
                sb.append("<td style='background-color:" + color + "'>" + x.getClasse() + "</td>\n");
                sb.append("<td style='background-color:" + color + "'>" + (docente) + "</td>\n");
                sb.append("<td style='background-color:" + color + "'>" + x.getMateriaPrincipale() + "</td>\n");
                sb.append("<td style='background-color:" + color + "'>" + precAula + "</td>\n");
                sb.append("<td style='font-size:150%;font-weight:bolder;background-color:" + color + "'>" + (x.getNomeAula() != null ? x.getNomeAula() : "") + "</td>\n");
                sb.append("<td style='background-color:" + color + "'>" + (x.getNote() != null ? x.getNote() : "") + "</td>\n");

                sb.append("</tr>\n");
            }
            sb.append("</table>");


            FileWriter w2 = new FileWriter(new File(folder_extra, "VARIAZIONI"));
            w2.write(sb.toString());
            w2.close();


        }

        System.out.println("Controllo nome " + nomeFile);
        for (File file : folderOutput.listFiles()) {
            if (file.getName().endsWith(subName)) {

                System.err.println("E' gia' presente un file con le date specificate " + file.getName() + " che e' stato rinominato");
                file.renameTo(new File(file.getAbsoluteFile() + ".bak"));
                //JOptionPane.showMessageDialog(null, "ERRORE!!!!");
                //Desktop.getDesktop().open(folder);
                //return;
            }
        }


        final String s = BitOrarioOraLezioneJSonConverter.toJSon(orarioTotale);
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(new File(folderOutput, nomeFile)));
        out.putNextEntry(new ZipEntry("timetable.json"));
        out.write(s.getBytes());
        out.close();

        FileWriter list = new FileWriter(new File(folderOutput, "timetables.txt"));
        final File[] files = folderOutput.listFiles();
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

        Desktop.getDesktop().open(folderOutput);
    }

    private static String printQCODEClassi() {
        StringBuilder sb = new StringBuilder();
        final ArrayList<ClassData> allClasses = ClassesAndRoomContainer.getAllClasses();
        sb.append("<html><body>");
        for (ClassData c : allClasses) {

            // sb.append("<div style='display: block; page-break-before: always;'></div>\n");
            sb.append("<hr>");
            sb.append("<h1 style='font-size:40px;font-weight:bolder'>Orario delle lezioni aggiornato Classe " + c.classname + "</h1>\n");
            sb.append("<img src='https://chart.googleapis.com/chart?cht=qr&chl=http%3A%2F%2Fwww.scuolesuperioridizagarolo.gov.it%2Forario%2Forario2.php%3Fclasse%3D" + c.classname + "&chs=400x400&choe=UTF-8&chld=L|2' alt=''>\n");

        }
        sb.append("</body></html>");
        return sb.toString();
    }


}
