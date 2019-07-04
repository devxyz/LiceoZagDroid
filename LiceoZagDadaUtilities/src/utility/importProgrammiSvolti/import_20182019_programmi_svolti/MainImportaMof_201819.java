package utility.importProgrammiSvolti.import_20182019_programmi_svolti;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileUtil;
import org.apache.commons.vfs2.VFS;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by stefano on 03/06/2018.
 */
public class MainImportaMof_201819 {
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
        File folder = new File("/Users/stefano/Dropbox/Circolari Scolastiche LICEO/AS 2018.19/mof/");
        String[] nomi = new String[]{
                "visforms_20190628_mof.csv",
        };

        for (String nome : nomi) {
            File f = new File(folder, nome);
            File folderOut = folder;

            String BASIC_URL = "http://www.scuolesuperioridizagarolo.edu.it/visform/mof_201819/";
            final CSVFormat fx = CSVFormat.newFormat(';').withHeader();

            final FileReader in = new FileReader(f);
            final CSVParser parse = fx.parse(in);
            int i = 0;


            Map<String, Integer> mm = parse.getHeaderMap();
            System.out.println(mm);
            ArrayList<String> header = new ArrayList<>();
            for (int j = 0; j < mm.size(); j++) {
                header.add("");
            }
            for (Map.Entry<String, Integer> e : mm.entrySet()) {
                header.set(e.getValue(), e.getKey());
            }
            System.out.println("header:" + header);


            //Nominativo di accesso;Nome Docente;Cognome Docente;Email;File PDF Domanda Ferie
            for (CSVRecord p : parse) {
                i++;
                if (i == 1)
                    continue;

                int pos = 0;
                String NominativodiaccessoNominativodiaccesso = p.get(pos++);
                String NomeDocenteNomeDocente = p.get(pos++);
                String CognomeDocenteCognomeDocente = p.get(pos++);
                String EmailEmail = p.get(pos++);

                final String cartellaFileLocale = (CognomeDocenteCognomeDocente.toUpperCase()).replace(" ", "") + "-" + NomeDocenteNomeDocente.replace(" ", "").toUpperCase() + "_" + i;
                File ffout = new File(folderOut, "/dati/" + cartellaFileLocale);
                ffout.mkdirs();

                //carica i vari link ai files
                ArrayList<String> files = new ArrayList<>();
                while (pos < p.size()) {
                    final String nomeFileLocale = header.get(pos).replace("/","_").replace(" ","").replace(":","").replace("�","");
                    String e = p.get(pos++);
                    if (e != null && e.trim().length() > 0) {
                        String url = BASIC_URL + e;
                        FileSystemManager fsManager = VFS.getManager();
                        FileObject remoteFile = fsManager.resolveFile(new URL(url));

                        final String[] split = url.split("\\.");
                        String extension = split[split.length - 1];
                        final FileObject localFile = fsManager.resolveFile(ffout, nomeFileLocale + "." + extension);
                        FileUtil.copyContent(remoteFile, localFile);

                /*if (localFile.exists()) {
                    System.out.println("SKIP (already exists)");
                    continue;
                }


                */

                    }
                }

                System.out.println("  nome e cognome:" + NomeDocenteNomeDocente + " " + CognomeDocenteCognomeDocente);
                System.out.println(files);

/*
                File ffout = new File(folderOut, "/dati/");
                ffout.mkdirs();
                String url = BASIC_URL + File_PDF_Domanda_Ferie;
                System.out.println("=======================================");
                System.out.println("Processing " + url);
                System.out.println("  nome e cognome:" + Nome_Docente + " " + Cognome_Docente);


                final String[] split = url.split("\\.");
                String extension = split[split.length - 1];

                FileSystemManager fsManager = VFS.getManager();
                FileObject remoteFile = fsManager.resolveFile(new URL(url));
                final String nomeFileLocale = (Cognome_Docente.toUpperCase()).replace(" ", "") + "-" + Nome_Docente.replace(" ", "").toUpperCase() + "_" + i;
                final FileObject localFile = fsManager.resolveFile(ffout, nomeFileLocale + "." + extension);

                /*if (localFile.exists()) {
                    System.out.println("SKIP (already exists)");
                    continue;
                }


                FileUtil.copyContent(remoteFile, localFile);*/
            }


            in.close();

            //zip

        }


    }


}
