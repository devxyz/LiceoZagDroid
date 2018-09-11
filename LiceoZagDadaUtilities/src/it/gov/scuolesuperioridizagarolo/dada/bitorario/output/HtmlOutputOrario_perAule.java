package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlOutputOrario_perAule extends HtmlOutputOrario {
    @Override
    protected String getSubTitle() {
        return "Orario per Aule";
    }

    @Override
    protected int getPrintForPage(EPaperFormat paperFormat) {
        if (paperFormat == EPaperFormat.A3)
            return 6;
        else
            return 1;
    }

    @Override
    protected Collection<String> raggruppaPer(BitOrarioGrigliaOrario o) {
        final TreeSet<String> aule = o.getAule();
        TreeSet<String> ris = new TreeSet<>();
        for (String a : aule) {
            final RoomData room = ClassesAndRoomContainer.getRoom(a);
            if (room.maxStudents > 0 && !room.flagAulaFittizia())
                ris.add(a);
        }

        return ris;
    }

    @Override
    protected String getLezione(BitOrarioGrigliaOrario griglia, NoteVariazioniBitOrarioGrigliaOrario note, EOra o, EGiorno s, String classe) {
        final List<BitOrarioOraLezione> lezioni = griglia.getLezioneInAula(o, s, classe);
        if (lezioni == null || lezioni.size() == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (BitOrarioOraLezione l : lezioni) {
            if (sb.length() > 0) {
                sb.append("<br>");
            }
            final String s1 = (l.getDocenteCompresenza() == null) ? " " : " - " + l.getDocenteCompresenza();

            String n = note.getNote(l);
            if (n == null) n = "";
            if (l != null && l.getNote() != null && n.length() > 0) {
                n += (" " + l.getNote()).trim();
            }
            if (n.length() > 0) {
                n = "<br><span style='border:2px solid black;color:white;background-color:black'>" + n + "</span>";
            }


            sb.append("<center style='font-size:20px'><b>" + l.getClasse() + " </b><br> " + l.getDocentePrincipale() + " " + s1 + " " + n + " <br> </center>");
        }
        return sb.toString();
    }

    @Override
    protected String intestazione(String s, BitOrarioGrigliaOrario grigliaOrario) {
        return "<span style='font-size:30px;font-weight: bold;'>AULA " + s + "  (<span style='font-size:10px;'> " + grigliaOrario.getTitolo() + "</span>)";
    }
}
