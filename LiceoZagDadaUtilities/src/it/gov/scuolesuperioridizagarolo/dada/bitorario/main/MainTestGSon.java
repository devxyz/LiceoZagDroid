package it.gov.scuolesuperioridizagarolo.dada.bitorario.main;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.BitOrarioOraLezioneJSonConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by stefano on 28/11/2017.
 */
public class MainTestGSon {
    public static void main(String[] args) throws IOException {
        final BitOrarioGrigliaOrario orarioTotale = MainParserGeneraStampeOrario.parsingDefaultFileOrarioAuleClassi(new File(MainParserGeneraStampeOrario.DEBUG_FOLDER_INPUT));


        final String a = BitOrarioOraLezioneJSonConverter.toJSon(orarioTotale);

        FileWriter f = new FileWriter("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/data/orario.json");
        f.write(a);
        f.close();

        final BitOrarioGrigliaOrario o2 = BitOrarioOraLezioneJSonConverter.fromJSon(a);


    }
}
