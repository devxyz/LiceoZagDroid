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
public class HtmlOutputOrario_perCompresenze extends HtmlOutputOrario {
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
        StringBuilder sb2 = new StringBuilder();
        int cambi = 0;
        int uscita = 0;


        sb.append("<b>Disposizioni</b><ul>");
        for (BitOrarioOraLezione l : classi) {
            if (l.isDisposizionePura()) {
                sb.append("<li>" + l.getDocentePrincipale() + "</li>\n");
            }
        }
        sb.append("</ul><hr>\n");


        sb2.setLength(0);
        for (BitOrarioOraLezione l : classi) {
            if (l.isDisposizioneProgetto()) {
                sb2.append("<li>" + l.getDocentePrincipale() + "</li>\n");
            }
        }
        if (sb2.length() > 0) {
            sb.append("<b>Progetti</b><ul>");
            sb.append(sb2);
            sb.append("</ul>\n");
        }

        sb2.setLength(0);
        for (BitOrarioOraLezione l : classi) {
            if (l.getDocenteCompresenza() != null) {
                sb2.append("<li><b>" + l.getDocenteCompresenza() + "</b> (Compr. " + l.getClasse().className + ")" + "</li>\n");
            }
        }
        if (sb2.length() > 0) {
            sb.append("<b>Compresenze</b><ul>");
            sb.append(sb2);
            sb.append("</ul>\n");
        }



        sb2.setLength(0);
        for (BitOrarioOraLezione l : classi) {
            if (l.getDocenteSostegno() != null) {

                sb2.append("<li><b>" + l.getDocenteSostegno() + "</b> (Sost. " + l.getClasse().className + ")" + "</li>\n");
            }
        }
        if (sb2.length() > 0) {
            sb.append("<b>Sostegno</b><ul>");
            sb.append(sb2);
            sb.append("</ul>\n");
        }


        return sb.toString();
    }

    @Override
    protected String intestazione(String s, BitOrarioGrigliaOrario grigliaOrario) {
        return "<span style='font-size:24px;font-weight: bold;'>Docenti in compresenza</span>";
    }


}
