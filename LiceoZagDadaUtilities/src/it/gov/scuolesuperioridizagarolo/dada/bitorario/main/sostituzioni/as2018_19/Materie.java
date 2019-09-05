package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2018_19;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.MainParserGeneraStampeOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Materie {
    public static void main(String[] args) throws IOException {

        File file = new File("/Users/stefano/Dropbox/Circolari Scolastiche LICEO/AS 2018.19/Orario Scolastico//orario-definitivo/DEFINITIVO_timetable_20181114222113_12.11.2018_09.06.2019.json.zip");
        final BitOrarioGrigliaOrario orarioInModifica = MainParserGeneraStampeOrario.readJsonFileOrarioAuleClassi(file);
        TreeMap<String, Integer> classi = new TreeMap<>();
        for (BitOrarioOraLezione l : orarioInModifica.getLezioni()) {
            String key = l.getDocentePrincipale() + "\t" + l.getClasse() + "\t" + l.getMateriaPrincipale();
            classi.putIfAbsent(key, 0);
            classi.put(key, classi.get(key) + 1);
        }

        for (Map.Entry<String, Integer> entry : classi.entrySet()) {
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }
    }
}
