package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlMobile_Aule {

    public HtmlMobile_Aule() {
    }


    public void print(BitOrarioGrigliaOrario o, BitOrarioGrigliaOrario oDefault, File f) throws IOException {

        final TreeSet<RoomData> strings = o.getAule();


        for (RoomData aule : strings) {

            final RoomData room = (aule);
            if (room.isAulaFittizia()) continue;
            if (room.maxStudents == 0) continue;

            File f1 = new File(f, aule.simpleName());
            PrintStream p = new PrintStream(f1);

            p.println("<table cellspacing=0 style='width:100%;table-layout: fixed; border:4px solid black; '>");

            p.println("<tr style='border:1px solid clack' >");
            p.println("<td style='border:1px solid black; background-color:blue;color:yellow' colspan='" + (1 + EGiorno.numeroGiorniDiLezione()) + "'>");
            p.println("<center>" + o.getTitolo() + "<br><span style='font-size:200%'><b>Aula " + aule.simpleName() + "</b></span></center>" + "<BR><center style='color:yellow;font-size:16px'>Entrata N." + aule.getEntry().number() + " - " + aule.getEntry().description + "</center>");
            p.println("</td>");
            p.println("</tr>");

            //==============================
            p.println("<tr>");
            p.println("<td style='width:5%' > </td>");
            for (EGiorno s : EGiorno.values()) {
                if (!s.flagGiornoDiLezione()) continue;

                p.println("<td style='width:15%;font-size:16px;'><center><b>" + s.shortName() + "</b></center></td>");
            }
            p.println("</tr>");
            int id = 0;

            for (EOra ora : EOra.values()) {

                if (!ora.flagOraDiLezione()) continue;

                p.println("<tr  style='border:1px solid black'>");
                p.println("<td style='background-color:yellow ;text-align: center;border-bottom:2px solid red; border-top:2px solid red'><span style='font-size:200%'><b>" + ora.getProgressivOra() + "<br></span> (" + ora.printOraInizioPresenza() + ")</b></td>");
                for (EGiorno settimana : EGiorno.values()) {
                    id++;
                    if (!settimana.flagGiornoDiLezione()) {
                        continue;
                    }
                    final List<BitOrarioOraLezione> lezioneList = o.getLezioneInAula(ora, settimana, aule, true);

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
                                        final String docente = lezione.getDocentiFormatted();
                                        final ClassData classe = lezione.getClasse() == null ? null/*ClassData.CLASS_SCONOSCIUTA*/ : lezione.getClasse();

                                        if (lezione.getAula().isDDI())
                                            p.printf("<hr>" +
                                                    "<center>" +
                                                    "<span style='color:red;font-size:100%%;font-size:100%%'> ** DDI docente **<br>" + docente + " <b></b></span></center>");
                                        else
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

            {
                //copy
                Path copied = new File(f, f1.getName() + ".html").toPath();
                Path originalPath = f1.toPath();
                Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
            }

        }
    }
}



