package utility.componi_esercizi;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by stefano on 26/11/2018.
 */
public class MergeEserciziDaFile {
    public static void componi(File sourceDir, File destDir, int numeroVersioni) throws IOException {
        if (!sourceDir.exists()) {
            throw new IllegalArgumentException("Cartella " + sourceDir + " non esiste");
        }
        final File[] folder = sourceDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        Random r = new Random();
        for (int i = 0; i < numeroVersioni; i++) {
            File dd = new File(destDir, "" + i);
            dd.mkdirs();
            System.out.println("==============================================");
            for (File file : folder) {
                final File[] files = file.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return !pathname.getName().startsWith(".");
                    }
                });
                final File file1 = files[r.nextInt(files.length)];
                File fdest = new File(dd, file1.getName());
                FileUtils.copyFile(file1, fdest);
                System.out.println("   " + file1.getName());
            }
        }
    }

    public static void main_1(String[] args) throws IOException {

        File source = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2018-19/compiti in classe/3B*ott-Registrato RE - 1 assente/02-nov_dic/testo/esercizi");
        File dest = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2018-19/compiti in classe/3B*ott-Registrato RE - 1 assente/02-nov_dic/testo/esercizi-file");
        componi(source, dest, 12);
    }

    public static void main(String[] args) throws IOException {

        File source = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2018-19/compiti in classe/2B*ott-registrato RE/02-nov_dic/testo/Esercizi");
        File dest = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2018-19/compiti in classe/2B*ott-registrato RE/02-nov_dic/testo/esercizi-file");
        componi(source, dest, 12);
    }
}
