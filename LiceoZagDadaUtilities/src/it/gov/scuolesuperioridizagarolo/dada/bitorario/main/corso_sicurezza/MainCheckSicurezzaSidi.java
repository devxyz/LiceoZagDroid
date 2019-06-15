package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.corso_sicurezza;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class MainCheckSicurezzaSidi {
    public static void main(String[] args) throws IOException {
        StudenteSidiCollection sidi = ImportFromCSVSidiStudenti.parse();
        StudenteCorsoSicurezzaCollection sicurezza = ImportFromJSONSicurezza.parse();

        PrintStream fout = new PrintStream(new FileOutputStream("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/scuolesuperioridizagarolo/dada/bitorario/main/corso_sicurezza/" +
                "output_09.04.2019.csv"));
        fout.print(StudenteCorsoSicurezza.toExcelHeader());
        fout.print(";");
        fout.print(StudenteSidi.toExcelHeader());
        fout.println();

        for (StudenteCorsoSicurezza s_sicurezza : sicurezza.getStudenti()) {
            StudenteSidi s_sidi = sidi.searchByCF(s_sicurezza.codicefiscaleStudente);
            if (s_sidi != null) {
                fout.print(s_sicurezza.toExcel());
                fout.print(";");
                fout.print(s_sidi.toExcel());
                fout.println();
            } else {

                fout.print(s_sicurezza.toExcel());
                fout.print(";");
                fout.print(new StudenteSidi("-", "-", "-", "-", "-", "-", "-", "-", "-").toExcel());
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
                fout.println();
            }

        }

        fout.close();
    }
}
