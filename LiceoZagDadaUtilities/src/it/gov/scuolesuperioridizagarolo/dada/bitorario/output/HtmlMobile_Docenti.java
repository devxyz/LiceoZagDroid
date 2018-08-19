package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlMobile_Docenti {

    public HtmlMobile_Docenti() {
    }


    public void print(BitOrarioGrigliaOrario o, BitOrarioGrigliaOrario oDefault, File f) throws IOException {

        final Collection<String> strings = o.getDocenti();

        for (String docente : strings) {
            PrintStream p = new PrintStream(new File(f, docente));

            p.println("<table cellspacing=0 style='width:100%;table-layout: fixed; border:4px solid black; '>");

            p.println("<tr style='border:1px solid clack' >");
            p.println("<td style='border:1px solid black; background-color:blue;color:yellow' colspan='" + (1 + EGiorno.numeroGiorniDiLezione()) + "'>");
            p.println("<center>" + o.getTitolo() + "<br><span style='font-size:200%'><b>" + docente + "</b></span></center>");
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
                    final BitOrarioOraLezione lezione = o.getLezioneConDocente(ora, settimana, docente);

                    final BitOrarioOraLezione lezioneDefault = oDefault.getLezioneConDocente(ora, settimana, docente);

                    boolean cambiamento = false;
                    if (lezioneDefault == null && lezione != null ||
                            lezioneDefault != null && lezione == null ||
                            lezioneDefault != null && lezione != null && !lezioneDefault.equals(lezione)) {
                        cambiamento = true;
                    }

                    final String msg1 = cambiamento ? "<hr><i>Orario modificato rispetto all'orario standard " + (lezioneDefault == null ? "" : lezioneDefault.toStringShort()) + "</i>" : "";
                    final String msg2 = ((lezione == null ? null : lezione.getNote()) != null ? "<hr><i>" + (lezione.getNote()) : "</i>");
                    String testo_dettagli = cambiamento ? "<div id ='msg_" + id + "' style=' display: none;border:2px solid red;background-color:pink'>" + msg1 + msg2 + "</div>" : "";

                    String button_dettagli = cambiamento ?
                            "<br><input " +
                                    "onclick=\"javascript:document.getElementById('msg_" + id + "').style.display='block' \" " +
                                    "type='button' " +
                                    "style=\"border:3px solid red; font-size:120% ;padding:5px; margin:5px; background-color:yellow   \" " +
                                    "value='Dettagli' >" : "";
                    if (lezione == null)
                        p.println("<td style='border:1px solid black;background-color:lightgray;vertical-align: middle;'><center> - </ce</td>");
                    else {
                        switch (lezione.getTipoLezione()) {
                            case DISPOSIZIONE: {
                                p.println("<td style='padding:5px;border:1px solid black;vertical-align: middle;background-color:yellow'><center>" +
                                        "DISPOSIZIONE" + button_dettagli
                                        + "</center></td>");

                                break;
                            }
                            default: {
                                final String classe = lezione.getClasse();
                                final String aula = lezione.getAula() == null ? "-" : lezione.getAula().simpleName();

                                p.printf("<td style='padding:10px;border:1px solid black;vertical-align: middle;background-color:white'>" +
                                        "<center>" +
                                        "<span style='color:blue;font-size:200%%'><b>%s</b></span><br><span style='font-size:110%%'>Aula <b>%s</b></span>%s%s</center>" +
                                        "</td>%n", classe, aula, button_dettagli, testo_dettagli);
                                break;

                            }

                        }
                    }


                }
                p.println("</tr>");
            }


            p.println("</table>");
            p.close();
        }


    }


}
