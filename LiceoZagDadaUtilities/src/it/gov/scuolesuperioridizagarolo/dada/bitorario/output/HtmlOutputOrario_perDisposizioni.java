package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

//import dada.bitorario.data.*;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlOutputOrario_perDisposizioni extends HtmlOutputOrario {
    @Override
    protected String getSubTitle() {
        return "";
    }

    @Override
    protected int getPrintForPage(EPaperFormat paperFormat) {
        return 1;
    }

    @Override
    protected Collection<String> raggruppaPer(BitOrarioGrigliaOrario o) {
        return Arrays.asList("fittizio");
    }

    @Override
    protected String getLezione(BitOrarioGrigliaOrario griglia, NoteVariazioniBitOrarioGrigliaOrario note, EOra o, EGiorno s, String x) {
        final ArrayList<BitOrarioOraLezione> classi = griglia.getLezioni(o, s);
        StringBuilder sb = new StringBuilder();
        int cambi = 0;
        int uscita = 0;
        for (BitOrarioOraLezione l : classi) {

            if (l.isDisposizionePura()) {
                if (sb.length() > 0)
                    sb.append("<br>");
                sb.append("<b>" + l.getDocentePrincipale() + "</b>");
            }
        }

        return sb.toString();
    }

    @Override
    protected String intestazione(String s, BitOrarioGrigliaOrario grigliaOrario) {
        return "<span style='font-size:24px;font-weight: bold;'>Docenti a disposizione</span>";
    }


}
