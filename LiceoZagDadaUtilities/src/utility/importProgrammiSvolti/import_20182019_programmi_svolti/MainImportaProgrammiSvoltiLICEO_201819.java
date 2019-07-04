package utility.importProgrammiSvolti.import_20182019_programmi_svolti;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.MainParserGeneraStampeOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
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
public class MainImportaProgrammiSvoltiLICEO_201819 {
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
        File folder = new File("/Users/stefano/Dropbox/Circolari Scolastiche LICEO/AS 2018.19/programmi-svolti/");
        String[] nomi = new String[]{
                "visforms_20190623_liceo.csv",
        };

        for (String nome : nomi) {
            File f = new File(folder, nome);
            File folderOut = folder;

            String BASIC_URL = "http://www.scuolesuperioridizagarolo.edu.it/visform/programmazioni_201819/";
            final CSVFormat fx = CSVFormat.newFormat(';');
            final FileReader in = new FileReader(f);
            final CSVParser parse = fx.parse(in);
            int i = 0;
            for (CSVRecord p : parse) {
                i++;
                if (i == 1)
                    continue;

                final String nomedoc = p.get(5);
                final String cognomedoc = p.get(6);
                final String materia = p.get(8).replace("/", "-").replace("’", "");
                final String classe = p.get(9);
                final String sez = p.get(10).split(" ")[0];
                final String scuola = p.get(10).split(" ")[1];
                final String nomefile = p.get(11);
                final String pubblicato = p.get(1);


                File ffout = new File(folderOut, scuola.toUpperCase() + "/" + classe + (sez.toUpperCase()));
                ffout.mkdirs();
                String url = BASIC_URL + nomefile;
                System.out.println("=======================================");
                System.out.println("Processing " + url);
                System.out.println("  nome e cognome:" + nomedoc + " " + cognomedoc);
                System.out.println("  disciplina:" + materia);
                System.out.println("  classe:" + classe + " " + sez + " " + scuola);

                if (pubblicato.trim().equals("0")){
                    System.out.println("  NON PUBBLICATO");
                    continue;
                }



                final String[] split = url.split("\\.");
                String extension = split[split.length - 1];

                FileSystemManager fsManager = VFS.getManager();
                FileObject remoteFile = fsManager.resolveFile(new URL(url));
                final String nomeFileLocale = (classe + sez + "(" + scuola + ")-" + materia + "-prof." + cognomedoc + "_" + nomedoc).replaceAll("[ ]+", "_").toUpperCase();
                final FileObject localFile = fsManager.resolveFile(ffout, nomeFileLocale + "." + extension);

                if (localFile.exists()) {
                    System.out.println("SKIP (already exists)");
                    continue;
                }


                FileUtil.copyContent(remoteFile, localFile);
            }


            in.close();

            //zip

        }


    }


}
