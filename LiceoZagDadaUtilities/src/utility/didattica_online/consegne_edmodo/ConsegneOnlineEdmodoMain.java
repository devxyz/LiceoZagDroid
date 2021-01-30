package utility.didattica_online.consegne_edmodo;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class ConsegneOnlineEdmodoMain {
    static File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/didattica_online/consegne_edmodo/data");

    public static void main(String[] args) throws Throwable {
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".csv");
            }
        });
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        TreeSet<String> report = new TreeSet<>();
        for (File file : files) {

            String classe = file.getName().split("[ ]+")[0];
            System.out.println("CLASSE " + classe);
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            System.out.println("nomecognome\tconsegne\tnon_consegne\tpercentuale");
            int num = 0;
            while ((line = in.readLine()) != null) {
                num++;
                if (num == 1) continue;
                line = line.replace("" + (char) 0, "").trim();
                if (line.length() == 0) continue;
                //System.out.println(line);

                int consegne = 0;
                int non_consegne = 0;
                String[] elements = line.split("\t");
                //System.out.println(Arrays.toString(elements));
                String nomecognome = elements[0];
                String s = nomecognome.replaceAll("ò", "o")
                        .toUpperCase()
                        .replace(" DI ", " DI_")
                        .replace(" D' ", " D'_")
                        .replace(" DE ", " DE_")
                        .replace(" DEL ", " DEL_")
                        .replace(" DELLE ", " DELLE_")
                        .replace("FRANCESCO MATTIA", "FRANCESCO_MATTIA")
                        .replace("CRISTIAN EDUARD", "CRISTIAN_EDUARD")
                        .toUpperCase();
                ;
                String[] split = s.split("[ ]+");
                if (split.length != 2) {
                    throw new IllegalArgumentException("Errore con " + nomecognome + ": " + Arrays.toString(split));
                }
                String cognomenome = split[1] + " " + split[0];

                for (int i = 1;
                     i < elements.length - 1;
                     i++) {
                    if (
                            elements[i].equalsIgnoreCase("Consegnato")
                                    || elements[i].equalsIgnoreCase("=100")
                    ) {
                        consegne++;
                    } else {
                        non_consegne++;
                    }
                }

                int percentuale = (int) ((double) consegne / (double) (consegne + non_consegne) * 100);
                System.out.println(nomecognome.trim() + "\t" + consegne + "\t" + non_consegne + "\t" + percentuale);
                String r = "(" + classe + ") " + cognomenome + "\t" + cognomenome + "\t" + "##REPORT CONSEGNE ELABORATI ASINCRONI " + cognomenome.replace("_", " ") + "##\\nconsegnati:" + consegne + ", non consegnati:" + non_consegne + "\t" + ("" + percentuale).replace(".", ",");
                report.add(r);

            }
            in.close();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for (String s : report) {
            System.out.println(s);
        }
    }
}
