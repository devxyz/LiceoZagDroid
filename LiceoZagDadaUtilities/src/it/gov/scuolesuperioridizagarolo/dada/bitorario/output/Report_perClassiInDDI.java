package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class Report_perClassiInDDI {


    public Report_perClassiInDDI() {
    }


    public void print(BitOrarioGrigliaOrario o, NoteVariazioniBitOrarioGrigliaOrario note, File f, EPaperFormat format) throws IOException {
        PrintStream p = new PrintStream(f);
        p.println(o.getTitolo());
        p.println("Classi in DDI");
        Map<ClassData, EGiorno> report = new TreeMap<>();
        ArrayList<BitOrarioOraLezione> lezioni = o.getLezioni();
        for (BitOrarioOraLezione l : lezioni) {
            BitOrarioOraEnumTipoLezione tipoLezione = l.getTipoLezione();
            switch (tipoLezione) {
                case LEZIONE_CON_COMPRESENZA:
                case LEZIONE_SINGOLA: {
                    if (l.getAula().isDDI()) {
                        report.put(l.getClasse(), l.getGiorno());
                    }
                    break;
                }
            }
        }

        for (Map.Entry<ClassData, EGiorno> e : report.entrySet()) {
            p.println(e.getKey() + "\t" + e.getValue());
        }
        p.close();
    }

}
