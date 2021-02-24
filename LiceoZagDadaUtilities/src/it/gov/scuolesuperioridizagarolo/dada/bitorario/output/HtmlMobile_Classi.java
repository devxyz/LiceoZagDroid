package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EEntry;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlMobile_Classi {

    public HtmlMobile_Classi() {
    }


    public void print(BitOrarioGrigliaOrario o, BitOrarioGrigliaOrario oDefault, File f) throws IOException {

        final TreeSet<ClassData> strings = o.getClassi();


        for (ClassData classe : strings) {

            File f1 = new File(f, classe.className);
            PrintStream p = new PrintStream(f1);

            p.println("<table cellspacing=0 style='width:100%;table-layout: fixed; border:4px solid black; '>");

            p.println("<tr style='border:1px solid clack' >");
            p.println("<td style='border:1px solid black; background-color:blue;color:yellow' colspan='" + (1 + EGiorno.numeroGiorniDiLezione()) + "'>");
            p.println("<center>" + o.getTitolo() + "<br><span style='font-size:200%'><b>Classe " + classe + "</b></span></center>");
            p.println("</td>");
            p.println("</tr>");

            //==============================
            p.println("<tr>");
            p.println("<td style='width:5%' > </td>");
            for (EGiorno s : EGiorno.values()) {
                if (!s.flagGiornoDiLezione()) continue;
                final BitOrarioOraLezione lezione = o.getLezionePrimaInClasse(s, classe);
                if (lezione != null && lezione.getAula() != null) {
                    EEntry entry = lezione.getAula().getEntry();
                    if (entry.number() > 0)
                        p.println("<td style='width:15%;font-size:16px;'><center><b>" + s.shortName() + "</b></center><BR><center style='color:red'>Entrata N." + entry.number() + "</center></td>");
                    else
                        p.println("<td style='width:15%;font-size:16px;'><center><b>" + s.shortName() + "</b></center><BR><center style='color:red'>DDI</center></td>");
                } else {
                    p.println("<td style='width:15%;font-size:16px;'><center><b>" + s.shortName() + "</b></center><BR><center>-</center></td>");
                }
            }
            p.println("</tr>");
            int id = 0;

            Set<RoomData> auleUtilizzate = new TreeSet<>();
            boolean qualcheGiornoDDI = false;
            for (EOra ora : EOra.values()) {

                if (!ora.flagOraDiLezione()) continue;

                p.println("<tr  style='border:1px solid black'>");
                p.println("<td style='background-color:yellow ;text-align: center;border-bottom:2px solid red; border-top:2px solid red'><span style='font-size:200%'><b>" +
                        ora.getProgressivOra() + "</span>" +
                        "<br> (" + ora.printOraInizioPresenza() + " / " + ora.printOraFinePresenza() + ")</b>" +
                        "<br> (DDI " + ora.printOraInizioDDI() + " / " + ora.printOraFineDDI() + ")" +
                        "</td>");
                for (EGiorno settimana : EGiorno.values()) {
                    id++;
                    if (!settimana.flagGiornoDiLezione()) {
                        continue;
                    }
                    final BitOrarioOraLezione lezione = o.getLezioneInClasse(ora, settimana, classe);

                    final BitOrarioOraLezione lezioneDefault = oDefault.getLezioneInClasse(ora, settimana, classe);

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
                                    "value='info' >" : "";
                    if (lezione == null)
                        p.println("<td style='border:1px solid black;background-color:lightgray;vertical-align: middle;'><center> - </ce</td>");
                    else {
                        switch (lezione.getTipoLezione()) {
                            case DISPOSIZIONE: {
                                p.println("<td style='padding:5px;border:1px solid black;vertical-align: middle;background-color:yellow'><center>" +
                                        "DISP" + button_dettagli
                                        + "</center></td>");

                                break;
                            }

                            default: {
                                final String docente = lezione.getDocentiFormatted();
                                final String aula = lezione.getAula() == null ? "-" : lezione.getAula().simpleName();
                                String materia = UtilMaterie.abbreviazioneMateria(lezione);
                                boolean ddi = false;
                                if (lezione.getAula() != null && lezione.getAula().isDDI()) {
                                    qualcheGiornoDDI=true;
                                    ddi = true;
                                }

                                if (ddi) {
                                    if (classe == ClassData.CLASS_4F) {
                                        System.out.println("DEBUG!!!" + lezione.getAula());
                                    }
                                    auleUtilizzate.add(RoomData.DDI_da_casa);
                                } else
                                    auleUtilizzate.add(lezione.getAula());

                                if (ddi) {
                                    p.printf("<td style='padding:10px;border:2px solid red;vertical-align: middle;background-color:#D6EAF8'>" +
                                            "<center>" +
                                            "<span style='color:blue;font-size:100%%'><b>%s</b><br><font size='1' color='red'>%s</font></span><br><span style='font-size:110%%'><b>%s</b></span>%s%s</center>" +
                                            "</td>%n", materia, docente, "DDI (da casa)", button_dettagli, testo_dettagli);
                                } else {
                                    p.printf("<td style='padding:10px;border:1px solid black;vertical-align: middle;background-color:white'>" +
                                            "<center>" +
                                            "<span style='color:blue;font-size:100%%'><b>%s</b><br><font size='1' color='red'>%s</font></span><br><span style='font-size:110%%'><b>%s</b></span>%s%s</center>" +
                                            "</td>%n", materia, docente, aula, button_dettagli, testo_dettagli);
                                }
                                break;

                            }

                        }
                    }


                }
                p.println("</tr>");
            }


            p.println("</table>");
            if (qualcheGiornoDDI)
                p.println("<h3>L'Indicazione DDI rappresenta la Didattica Digitale Integrata. <br>La lezione viene svolta a distanza mediante piattaforma GSuite (Classroom/Meet). " +
                        "Gli studenti restano a casa e si collegano in remoto, il docente si collega dalle postazioni predisposte a scuola</h3>");

            p.println("<h3 style='color:red'>Elenco aule utilizzate:</h3>");
            p.println("<ul>");
            for (RoomData r : auleUtilizzate) {
                p.println("<li>");
                if (r.isDDI())
                    p.printf("<b>DDI da casa:</b> %s\n", r.usage);
                else
                    p.printf("<b>%s:</b> %s area %s\n", r.simpleName(), r.usage, r.location.description);

                p.println("</li>");
            }
            p.println("</ul>");
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



