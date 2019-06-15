package utility.importProgrammiSvolti.import_20172018_programmi_svolti;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.MainParserGeneraStampeOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileUtil;
import org.apache.commons.vfs2.VFS;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by stefano on 03/06/2018.
 */
public class MainImportaProgrammiSvolti_201718 {
    static ArrayList<String> associaMaterieProgrammi(Set<String> materie, File[] programmi) {
        TreeSet<String> mat = new TreeSet<>(materie);
        TreeSet<String> prog = new TreeSet<>();
        for (File p : programmi) {
            prog.add(p.getName());
        }

        ArrayList<String> ris = new ArrayList<>();
        for (String m : materie) {
            boolean trovato = false;
            for (String p : prog) {
                final TreeSet<String> split = new TreeSet<String>(Arrays.asList(p.toUpperCase().split("[-_,;:'\\.]")));


                if (split.contains(m.toUpperCase())) {
                    ris.add("ASSOCIATO " + m + "  -->  " + p);
                    prog.remove(p);
                    trovato = true;
                    break;
                }

                if (m.equalsIgnoreCase("STORIA_GEOGRAFIA")) {
                    String m2 = "GEOSTORIA";
                    if (split.contains(m2.toUpperCase())) {
                        ris.add("ASSOCIATO " + m2 + "  -->  " + p);
                        prog.remove(p);
                        trovato = true;
                        break;
                    }
                }

                if (m.equalsIgnoreCase("STORIA_GEOGRAFIA")) {
                    String m2 = "STORIA";
                    if (split.contains(m2.toUpperCase())) {
                        ris.add("ASSOCIATO " + m2 + "  -->  " + p);
                        prog.remove(p);
                        trovato = true;
                        break;
                    }
                }

                if (m.equalsIgnoreCase("SC.MOTORIE")) {
                    String m2 = "MOTORIE";
                    if (split.contains(m2.toUpperCase())) {
                        ris.add("ASSOCIATO " + m2 + "  -->  " + p);
                        prog.remove(p);
                        trovato = true;
                        break;
                    }
                }

            }
            if (!trovato) {
                ris.add("*** MATERIA NON ASSEGNATA " + m + "  -->  ?????");
            }
        }
        for (String p : prog) {
            ris.add("*** PROGRAMMA NON ASSEGNATO " + p + "  -->  ?????");
        }

        return ris;
    }


    public static void main(String[] args) throws IOException {
        File f = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/Dada-orario-utilities/src/utility/importProgrammiSvolti/visforms_20180619.csv");
        File folderOut = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/Dada-orario-utilities/data/programmi-svolti");

        String BASIC_URL = "http://www.scuolesuperioridizagarolo.gov.it/pasw4/visform/programmi_201718/";
        final CSVFormat fx = CSVFormat.newFormat(';');
        final FileReader in = new FileReader(f);
        final CSVParser parse = fx.parse(in);
        int i = 0;
        if (true)
            for (CSVRecord p : parse) {
                i++;
                if (i == 1)
                    continue;

                final String nomedoc = p.get(6);
                final String cognomedoc = p.get(7);
                final String materia = p.get(8);
                final String classe = p.get(9);
                final String sez = p.get(10).split(" ")[0];
                final String scuola = p.get(10).split(" ")[1];
                final String nomefile = p.get(11);


                File ffout = new File(folderOut, scuola.toUpperCase() + "/" + classe + (sez.toUpperCase()));
                ffout.mkdirs();
                String url = BASIC_URL + nomefile;
                System.out.println("=======================================");
                System.out.println("Processing " + url);


                final String[] split = url.split("\\.");
                String extension = split[split.length - 1];

                FileSystemManager fsManager = VFS.getManager();
                FileObject remoteFile = fsManager.resolveFile(new URL(url));
                final String nomeFileLocale = (classe + sez + "(" + scuola + ")-" + materia + "-prof." + cognomedoc + "_" + nomedoc).replaceAll("[ ]+", "_").toUpperCase();
                final FileObject localFile = fsManager.resolveFile(ffout, nomeFileLocale + "." + extension);

                FileUtil.copyContent(remoteFile, localFile);
            }

        final BitOrarioGrigliaOrario orarioTotale = MainParserGeneraStampeOrario.parsingDefaultFileOrarioAuleClassi(new File(MainParserGeneraStampeOrario.DEBUG_FOLDER_INPUT));
        final TreeSet<ClassData> classi = orarioTotale.getClassi();
        for (ClassData c : classi) {
            File progClasse = new File(folderOut, "LICEO/" + c.className.toUpperCase());
            if (!progClasse.exists())
                continue;

            final File[] files = progClasse.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(".pdf");
                }
            });
            final int numProgrammi = files.length;
            final Set<String> materie = orarioTotale.getMaterie(c);
            System.out.print(c + ". Numero programmi: " + numProgrammi + " - Num Materie:" + materie.size());
            final ArrayList<String> strings = associaMaterieProgrammi(materie, files);
            boolean anomalie = false;
            for (String s : strings) {
                if (s.startsWith("***")) {
                    anomalie = true;
                    break;
                }
            }

            if (anomalie) {
                System.out.println(" ERRORE!!");

                Collections.sort(strings);
                for (String string : strings) {
                    if (string.startsWith("ASSOCIATO"))
                        continue;
                    System.out.println("  > " + string);
                }
                /*
                System.out.println("   >PROGRAMMI");
                for (File file : files) {
                    System.out.println("     > " + file.getName());
                }
                System.out.println("   >MATERIE");
                for (String s : materie) {
                    System.out.println("     > " + s);
                }*/

            } else
                System.out.println(" OK");
        }


        in.close();
    }
}
