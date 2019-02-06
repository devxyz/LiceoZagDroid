package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class Report_perAuleVuote {

    public Report_perAuleVuote() {
    }


    public void print(BitOrarioGrigliaOrario o, File f) throws IOException {
        PrintStream p = new PrintStream(f);
        p.print("<html><body>");
        p.println("<h1>Aule vuote - " + o.getTitolo() + "</h1>");
        p.print("<table style='width:100%;table-layout: fixed; border:4px solid black; '>");

        //==============================
        p.print("<tr>");
        p.print("<td style='width:5%' > </td>");
        for (EGiorno s : EGiorno.values()) {
            if (!s.flagGiornoDiLezione()) continue;
            p.print("<td style='width:15%'><b>" + s + "</b></td>");
        }
        p.print("</tr>");
        for (EOra ora : EOra.values()) {
            if (ora == EOra.USCITA) continue;
            if (ora == EOra.ENTRATA) continue;
            p.print("<tr  style='border:1px solid black'>");
            p.print("<td style='text-align: center;font-size:120%;' >" + ora.getProgressivOra() + "^ora<br>" + ora.printOra() + "</td>");
            for (EGiorno settimana : EGiorno.values()) {
                if (!settimana.flagGiornoDiLezione()) continue;

                final TreeSet<RoomData> l = o.getAuleVuote(ora, settimana);
                p.print("<td style='border:1px solid black; text-align:left;padding-left:5px'>");
                for (RoomData s : l) {
                    final RoomData room = (s);
                    if (room.flagAulaFittizia()) continue;
                    if (room.maxStudents == 0) continue;

                    p.print(" <b>" + s + "</b> - " + (s).maxStudents + " posti " + (s.flagLIM ? "LIM" : ""));
                }
                p.print("</td>");


            }
            p.print("</tr>");
        }
        p.print("</table><br>");
    }


}
