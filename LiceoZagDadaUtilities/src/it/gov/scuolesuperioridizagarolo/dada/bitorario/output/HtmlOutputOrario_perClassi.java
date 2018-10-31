package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlOutputOrario_perClassi extends HtmlOutputOrario {
    @Override
    protected String getSubTitle() {
        return "Orario per Classi";
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
        final TreeSet<ClassData> classi = o.getClassi();
        TreeSet<String> ris = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.charAt(1) + o1).compareTo((o2.charAt(1) + o2));
            }
        });

        for (ClassData x : classi) {
            ris.add(x.className);
        }
        //ris.addAll(classi);
        return ris;
    }

    @Override
    protected String getLezione(BitOrarioGrigliaOrario griglia, NoteVariazioniBitOrarioGrigliaOrario note, EOra o, EGiorno s, String classe) {
        final BitOrarioOraLezione l = griglia.getLezioneInClasse(o, s, ClassesAndRoomContainer.parseClass(classe));
        if (l == null) return "";
        String n = note.getNote(l);
        if (n == null) n = "";
        if (l != null && l.getNote() != null && n.length() > 0) {
            n += (" " + l.getNote()).trim();
        }
        if (n.length() > 0) {
            n = "<br><span style='border:2px solid black;color:white;background-color:black'>" + n + "</span>";
        }

        if (l.getDocenteCompresenza() != null)
            return "<center>" + l.getMateriaPrincipale().replaceAll("_", " ") + "<br>" + l.getDocentePrincipale() + " - " + l.getDocenteCompresenza() +
                    "<b> (" + l.getAula().simpleName() + ") " + n + "</b></center>";
        else
            return "<center>" + l.getMateriaPrincipale().replaceAll("_", " ") + "<br>" + l.getDocentePrincipale() + "<b> (" + l.getAula().simpleName() + ") " + n + "</b></center>";
    }

    @Override
    protected String intestazione(String s, BitOrarioGrigliaOrario grigliaOrario) {
        return "<span style='font-size:18px;font-weight: bold;'>CLASSE " + s + "</span> (<span style='font-size:10px;'> " + grigliaOrario.getTitolo() + "</span>)";
    }

    protected int defaultCellFontSize() {
        return 13;
    }

}
