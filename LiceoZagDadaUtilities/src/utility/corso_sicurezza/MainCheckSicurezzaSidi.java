package utility.corso_sicurezza;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class MainCheckSicurezzaSidi {
    public static void main(String[] args) throws IOException {
        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/corso_sicurezza/");

        File fASL = new File(root, "export_09_03_2020.json");

        File f1_liceo = new File(root, "EsportazioneDati_FAT_RMPS07701G_2019.csv");
        File f2_ipia = new File(root, "EsportazioneDati_FAT_RMRI07701R_2019.csv");


        StudenteSidiCollection sidi = ImportFromCSVSidiStudenti.parse(f1_liceo, f2_ipia);
        StudenteCorsoSicurezzaCollection sicurezza = ImportFromJSONSicurezza.parse(fASL);

        PrintStream fout = new PrintStream(new FileOutputStream(new File(root, "output_09.03.2020.csv")));
        fout.print(StudenteCorsoSicurezza.toExcelHeader());
        fout.print(";");
        fout.print(StudenteSidi.toExcelHeader());
        fout.print(";status");
        fout.println();

        for (StudenteCorsoSicurezza s_sicurezza : sicurezza.getStudenti()) {
            StudenteSidi s_sidi = sidi.searchByCF(s_sicurezza.codicefiscaleStudente);
            if (s_sidi != null) {
                fout.print(s_sicurezza.toExcel());
                fout.print(";");
                fout.print(s_sidi.toExcel());
                fout.print(";registrato");
                fout.println();
            } else {

                fout.print(s_sicurezza.toExcel());
                fout.print(";");
                fout.print(new StudenteSidi("-", "-", "-", "-", "0", "-", "-", "-", "-").toExcel());
                fout.print(";studente trasferito in altra scuola?");
                fout.println();
            }

        }

        for (StudenteSidi s_sidi : sidi.searchAnnoCorso(3)) {
            StudenteCorsoSicurezza s_sicurezza = sicurezza.searchByCF(s_sidi.CodiceFiscale);
            if (s_sicurezza != null) {

            } else {

                fout.print(new StudenteCorsoSicurezza("-", "-", "-", "-", "-", "-", "-").toExcel());
                fout.print(";");
                fout.print(s_sidi.toExcel());
                fout.print(";studente non registrato in ASL");
                fout.println();
            }

        }

        fout.close();
    }
}
