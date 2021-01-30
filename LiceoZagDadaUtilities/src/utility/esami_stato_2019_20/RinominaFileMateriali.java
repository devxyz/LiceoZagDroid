package utility.esami_stato_2019_20;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;

public class RinominaFileMateriali {
    private static Integer estraiNumero(File f){
        return Integer.parseInt(f.getName().
                replaceAll(".pdf","").trim());
    }
    public static void main(String[] args) {
        String nomi[] = (
                "Informatica\tIl tempo/1\tPING PONG\n" +
                "Informatica\tIl tempo/2\tLa favola di Cesare\n" +
                "Informatica\tIl tempo/3\tTime to Live\n" +
                "Informatica\tIl tempo/4\tSpeed test\n" +
                "Informatica\tIl tempo/5\tVeloce come la luce\n" +
                "Informatica\tLa visione scientifica del mondo/1\tA caccia di squali\n" +
                "Informatica\tLa visione scientifica del mondo/2\tIP routing \n" +
                "Informatica\tLa visione scientifica del mondo/3\tLo standard\n" +
                "Informatica\tLa visione scientifica del mondo/4\tInternet TCP/IP\n" +
                "Informatica\tLa visione scientifica del mondo/5\tLa rete virtuale\n" +
                "Informatica\tLa crisi delle certezze/1\tHUB & SWITCH\n" +
                "Informatica\tLa crisi delle certezze/2\tWifi\n" +
                "Informatica\tLa crisi delle certezze/3\tIP routing\t\n" +
                "Informatica\tLa crisi delle certezze/4\terrare humanum est\n" +
                "Informatica\tLa crisi delle certezze/5\tSono uno zero\n" +
                "Informatica\tLa Natura/1\tIl cerchio della vita\n" +
                "Informatica\tLa Natura/2\tDES nel bancomat\n" +
                "Informatica\tLa Natura/3\tLa selezione naturale dei cavi\n" +
                "Informatica\tLa Natura/4\tL’evoluzione di G\n" +
                "Informatica\tLa Natura/5\tLa nascita di un protocollo\n" +
                "Informatica\tTotalitarismi e dittature/1\tRSA\n" +
                "Informatica\tTotalitarismi e dittature/2\tIl terzo incomodo\n" +
                "Informatica\tTotalitarismi e dittature/3\tLa libertà di scrivere\n" +
                "Informatica\tTotalitarismi e dittature/4\tLa muraglia cinese\n" +
                "Informatica\tTotalitarismi e dittature/5\t\n")
                .split("\n");

        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2019-20/esame-stato 201920/cartella-lavoro/materiali 5B/materiali_singoli");
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".pdf");
            }
        });
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return estraiNumero(o1).compareTo(estraiNumero(o2));
            }
        });

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            System.out.println(file.getName() + "->" +nomi[i]);
            file.renameTo(new File(root,nomi[i].replace("/","-").replaceAll("[ \t]+","-")+".pdf"));
        }

    }
}
