package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class Report_perOccupazioneAule {

    public Report_perOccupazioneAule() {
    }


    public void print(BitOrarioGrigliaOrario o, File f) throws IOException {
        PrintStream p = new PrintStream(f);
        p.print("<html><body>");


        //==============================
        for (EGiorno settimana : EGiorno.values()) {
            if (!settimana.flagGiornoDiLezione()) continue;
            p.println("<h1>Aule disponibili - " + o.getTitolo() + "</h1>");
            p.print("<table style='width:100%;table-layout: fixed; border:4px solid black; '>");
            p.print("<tr>");
            p.print("<td style='width:5%' > </td>");
            for (EOra s : EOra.values()) {
                if (s == EOra.USCITA) continue;
                p.print("<td style='width:15%;font-size:20px'><b>" + s.printOra() + "</b></td>");
            }
            p.print("</tr>");

            p.print("<tr  style='border:1px solid black'>");
            p.print("<td style='text-align: right;font-size:20px' >" + settimana.getNome() + "</td>");
            for (EOra ora : EOra.values()) {
                if (ora == EOra.USCITA) continue;
                final TreeSet<String> l = o.getAuleVuote(ora, settimana);
                p.print("<td style='border:1px solid black; text-align:left;padding-left:5px;vertical-align:top'>");

                final TreeSet<String> aule = o.getAule();
                String aulaPrec = null;

                p.print("<table >");
                for (String a : aule) {

                    final RoomData room = ClassesAndRoomContainer.getRoom(a);
                    if (room.flagAulaFittizia()) continue;
                    if (room.maxStudents == 0) continue;


                    int studentiClasse = 0;
                    final List<BitOrarioOraLezione> lezioni = o.getLezioneInAula(ora, settimana, a);
                    for (BitOrarioOraLezione lezione : lezioni) {
                        if (lezione.getClasse() != null) {
                            final int i = ClassesAndRoomContainer.getClass(lezione.getClasse()).numberOfStudents;
                            studentiClasse += i;
                        }
                    }

                    final int x = ClassesAndRoomContainer.getRoom(a).maxStudents - studentiClasse;
                    if (x > 3) {
                        if (aulaPrec!=null  && aulaPrec.charAt(0) != a.charAt(0)) {
                            p.print("</table><hr style='border:4px solid black'>");
                            p.print("<table >");
                        }
                        if (lezioni.size() > 0)
                            p.print("<tr style='border-top:2px dashed red'><td style='width:100px;border-top:1px dashed black'><b>" + a + "</b> </td> <td style='border-top:1px dashed black'> <b>" + x + " posti</b> <br><i>(" + lezioni.get(0).getClasse() + " " + lezioni.get(0).getDocentePrincipale() + ")</i></td></tr>");
                        else
                            p.print("<tr><td style='width:100px;border-top:1px dashed black'><b>** " + a + "</b></td><td style='border-top:1px dashed black'> " + x + " <i>posti aula vuota</i></td></tr>");
                        aulaPrec = a;
                    }

                }
                p.print("</table>");


                p.print("</td>");


            }
            p.print("</tr>");
            p.print("</table><br>");
            p.print("<div style='display: block; page-break-before: always;'></div>");
        }

    }


}
