package utility.consigliDiClasse;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.MainParserGeneraStampeOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by stefano on 24/09/2018.
 */
@Deprecated
public class DocentiDaNominare {
    public static void main(String[] args) throws IOException {
        final File folderInput = new File("/Users/stefano/Dropbox/Circolari Scolastiche Liceo/AS 2018.19/Orario Scolastico/orario/04-2018.10.01_2018.10.06");
        final BitOrarioGrigliaOrario o = MainParserGeneraStampeOrario.parsingDefaultFileOrarioAuleClassi(folderInput);

        final TreeSet<String> docenti = o.getDocenti();
        for (String d : docenti) {
            if (d.startsWith("X_")) {
                System.out.print(d + ":n");
                Set<String> classi = new TreeSet<>();
                final ArrayList<BitOrarioOraLezione> ll = o.getLezioneConDocente(d);
                for (BitOrarioOraLezione x : ll) {
                    if (x.getTipoLezione() != BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
                        classi.add(x.getClasse().className + "(" + x.getMateriaPrincipale().toLowerCase() + ") ");
                    }
                }

                System.out.println("    " + classi);
                System.out.println("--------------------------------------------------------------");
            }
        }


    }
}
