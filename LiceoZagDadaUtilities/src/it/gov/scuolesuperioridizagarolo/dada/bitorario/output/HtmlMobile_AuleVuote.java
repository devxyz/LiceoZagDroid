package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlMobile_AuleVuote {

    public HtmlMobile_AuleVuote() {
    }


    public void print(BitOrarioGrigliaOrario o, File f) throws IOException {
        PrintStream p = new PrintStream(f);

        p.println("<h1>Aule vuote - " + o.getTitolo() + "</h1>");
        p.print("<table style='width:100%;table-layout: fixed; border:4px solid black; '>");

        //==============================
        /*
        p.print("<tr>");
        p.print("<td style='width:5%' > </td>");
        for (EGiorno s : EGiorno.values()) {
            if (!s.flagGiornoDiLezione())
                continue;
            p.print("<td style='width:15%'><b>" + s + "</b></td>");
        }
        p.print("</tr>");
        */
        for (EGiorno settimana : EGiorno.values()) {
            if (!settimana.flagGiornoDiLezione())
                continue;
            p.print("<tr  style='border:1px solid black'>");
            p.print("<td style='text-align: right;' > " + settimana + "</td>");

            for (EOra ora : EOra.values()) {
                if (ora == EOra.ENTRATA) continue;
                if (ora == EOra.USCITA) continue;

                final TreeSet<String> l = o.getAuleVuote(ora, settimana);
                p.println("<td style='border:1px solid black; text-align:center;vertical-align: text-top;padding-left:5px'>");
                p.println("<b style='color:red;width:100%;padding:5px'>" + ora.getProgressivOra() + "^ ora dalle " + ora.printOra() + "</b><hr>");
                for (String s : l) {
                    final String[] split = s.split("_");
                    p.println("<b style='font-size:150%;color:blue'>" + split[0] + (split.length>1?"*":"")+"</b> <br> " + ClassesAndRoomContainer.getRoom(s).maxStudents + " posti <br>");
                }
                p.print("</td>");


            }
            p.print("</tr>");
        }
        p.print("</table><br>");
    }


}
