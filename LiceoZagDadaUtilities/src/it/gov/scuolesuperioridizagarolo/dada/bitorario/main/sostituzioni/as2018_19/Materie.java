package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2018_19;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.MainParserGeneraStampeOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;

import java.io.File;
import java.io.IOException;

public class Materie {
    public static void main(String[] args) throws IOException {

        File file = new File("/Users/stefano/Dropbox/Circolari Scolastiche LICEO/AS 2018.19/Orario Scolastico//orario-definitivo/DEFINITIVO_timetable_20181114222113_12.11.2018_09.06.2019.json.zip");
        final BitOrarioGrigliaOrario orarioInModifica = MainParserGeneraStampeOrario.readJsonFileOrarioAuleClassi(file);
        for (String m : orarioInModifica.getMaterie()) {
            System.out.println(m);
        }


        int N = 45;
        int somma=0;
        for (int i = 1; i <=N; i++) {
            somma+=i;

        }
    }
}
