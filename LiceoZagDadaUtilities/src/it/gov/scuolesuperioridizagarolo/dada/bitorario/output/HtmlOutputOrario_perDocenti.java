package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;

import java.util.Collection;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlOutputOrario_perDocenti extends HtmlOutputOrario {
    @Override
    protected String getSubTitle() {
        return "Orario per Docenti";
    }

    @Override
    protected int getPrintForPage(EPaperFormat paperFormat) {
        if (paperFormat == EPaperFormat.A3)
            return 6;
        else
            return 3;
    }

    @Override
    protected Collection<String> raggruppaPer(BitOrarioGrigliaOrario o) {
        return o.getDocenti();
    }

    @Override
    protected String getLezione(BitOrarioGrigliaOrario griglia, NoteVariazioniBitOrarioGrigliaOrario note, EOra o, EGiorno s, String classe) {
        final BitOrarioOraLezione l = griglia.getLezioneConDocente(classe, s, o);
        if (l == null) return "";
        String n = note.getNote(l);
        if (n == null) n = "";
        if (l != null && l.getNote() != null && n.length() > 0) {
            n += (" " + l.getNote()).trim();
        }
        if (n.length()>0){
            n="<br><span style='border:2px solid black;color:white;background-color:black'>"+n+"</span>";
        }

        if (l.getClasse() != null)
            return "<center style='font-size:12px'> <b>" + l.getClasse() + "</b> (" + l.getAula() + ") - <span style='font-size:12px'>" + l.getMateriaPrincipale().toLowerCase() + "</span> " + n + "</center>";
        else
            return "<center style='color:red;font-size:18px'> <b>" + l.getMateriaPrincipale().toUpperCase() + "</span>" + n + "</center>";
    }

    @Override
    protected String intestazione(String s, BitOrarioGrigliaOrario grigliaOrario) {
        return "<span style='font-size:18px;font-weight: bold;'> " + s + "</span> (<span style='font-size:10px;'> " + grigliaOrario.getTitolo() + "</span>)";
    }
}
