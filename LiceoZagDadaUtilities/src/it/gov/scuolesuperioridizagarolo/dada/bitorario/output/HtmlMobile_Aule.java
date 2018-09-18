package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlMobile_Aule {

    public HtmlMobile_Aule() {
    }


    public void print(BitOrarioGrigliaOrario o, BitOrarioGrigliaOrario oDefault, File f) throws IOException {

        final Collection<String> strings = o.getAule();


        for (String aule : strings) {

            final RoomData room = ClassesAndRoomContainer.getRoom(aule);
            if (room.flagAulaFittizia()) continue;

            PrintStream p = new PrintStream(new File(f, aule));

            p.println("<table cellspacing=0 style='width:100%;table-layout: fixed; border:4px solid black; '>");

            p.println("<tr style='border:1px solid clack' >");
            p.println("<td style='border:1px solid black; background-color:blue;color:yellow' colspan='" + (1 + EGiorno.numeroGiorniDiLezione()) + "'>");
            p.println("<center>" + o.getTitolo() + "<br><span style='font-size:200%'><b>Aula " + aule + "</b></span></center>");
            p.println("</td>");
            p.println("</tr>");

            //==============================
            p.println("<tr>");
            p.println("<td style='width:5%' > </td>");
            for (EGiorno s : EGiorno.values()) {
                if (!s.flagGiornoDiLezione()) continue;
                p.println("<td style='width:15%;font-size:16px;'><center><b>" + s + "</b></center></td>");
            }
            p.println("</tr>");
            int id = 0;

            for (EOra ora : EOra.values()) {

                if (!ora.flagOraDiLezione()) continue;

                p.println("<tr  style='border:1px solid black'>");
                p.println("<td style='background-color:yellow ;text-align: center;border-bottom:2px solid red; border-top:2px solid red'><span style='font-size:200%'><b>" + ora.getProgressivOra() + "<br></span> (" + ora.printOra() + ")</b></td>");
                for (EGiorno settimana : EGiorno.values()) {
                    id++;
                    if (!settimana.flagGiornoDiLezione()) {
                        continue;
                    }
                    final List<BitOrarioOraLezione> lezioneList = o.getLezioneInAula(ora, settimana, aule);

                    //final List<BitOrarioOraLezione> lezioneDefaultList = oDefault.getLezioneInAula(ora, settimana, aule);
                    if (lezioneList == null || lezioneList.size() == 0) {
                        p.println("<td style='border:1px solid black;background-color:lightgray;vertical-align: middle;'><center> - </ce</td>");
                    } else {

                        p.print("<td style='padding:9px;border:1px solid black;vertical-align: middle;background-color:white'>");
                        for (BitOrarioOraLezione lezione : lezioneList) {


                            String testo_dettagli = "";

                            String button_dettagli = "";
                            if (lezione == null)
                                p.println("-");
                            else {
                                switch (lezione.getTipoLezione()) {
                                    case DISPOSIZIONE: {
                                        p.println("DISP" + button_dettagli + "</center>");
                                        break;
                                    }
                                    default: {
                                        final String docente = lezione.getDocentePrincipale() + " " + (lezione.getDocenteCompresenza() == null ? "" : " " + lezione.getDocenteCompresenza());
                                        final String classe = lezione.getClasse() == null ? "-" : lezione.getClasse();

                                        p.printf("" +
                                                "<center>" +
                                                "<span style='color:blue;font-size:100%%'><b>%s</b></span><br><span style='font-size:100%%'>" + classe + " <b>%s</b></span>%s%s</center>" +
                                                "%n", lezione.getMateriaPrincipale().replace("_", " "), docente, button_dettagli, testo_dettagli);
                                        break;

                                    }

                                }
                            }
                        }
                        p.print("</td>");
                    }

                }
                p.println("</tr>");
            }


            p.println("</table>");
            p.close();
        }
    }
}



