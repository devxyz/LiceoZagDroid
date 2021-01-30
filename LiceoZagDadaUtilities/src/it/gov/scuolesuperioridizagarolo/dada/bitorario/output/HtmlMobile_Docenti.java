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
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlMobile_Docenti {

    public HtmlMobile_Docenti() {
    }

    static boolean SKIP_DDI_MISTA = true;

    public void print(BitOrarioGrigliaOrario o, BitOrarioGrigliaOrario oDefault, File f) throws IOException {

        final Collection<String> strings = o.getDocenti();

        for (String docente : strings) {
            File f1 = new File(f, docente.replaceAll("'", "_").replaceAll("/", "_"));
            PrintStream p = new PrintStream(f1);

            p.println("<table cellspacing=0 style='width:100%;table-layout: fixed; border:4px solid black; '>");

            p.println("<tr style='border:1px solid clack' >");
            p.println("<td style='border:1px solid black; background-color:blue;color:yellow' colspan='" + (1 + EGiorno.numeroGiorniDiLezione()) + "'>");
            p.println("<center style='font-size:20px'>" + o.getTitolo() + "<br><span style='font-size:200%'><b>" + docente + "</b></span></center>");
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

            Set<RoomData> auleUtilizzate = new TreeSet<>();

            for (EOra ora : EOra.values()) {

                if (!ora.flagOraDiLezione()) continue;

                p.println("<tr  style='border:1px solid black'>");
                p.println("<td style='background-color:yellow ;text-align: center;border-bottom:2px solid red; border-top:2px solid red'><span style='font-size:200%'><b>" +
                        ora.getProgressivOra() + "</span> " +
                        "<br>(" + ora.printOraInizioPresenza() + " / " + ora.printOraFinePresenza() + ")</b>" +
                        "<br>( DAD" + ora.printOraInizioDDI() + " / " + ora.printOraFineDDI() + ")" +
                        "</td>");
                for (EGiorno settimana : EGiorno.values()) {

                    boolean isDDIGiornaliera = o.isFullDDI(docente, settimana, false);
                    EOra[] t = o.intervalloDidatticaScuola__conTrasferimento(docente, settimana);
                    EOra inizioDidatticaAScuola = t[0];
                    EOra fineDidatticaAScuola = t[1];

                    id++;
                    if (!settimana.flagGiornoDiLezione()) {
                        continue;
                    }
                    final BitOrarioOraLezione lezione = o.getLezioneConDocente(docente, settimana, ora);

                    final BitOrarioOraLezione lezioneDefault = oDefault.getLezioneConDocente(docente, settimana, ora);

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
                                if (lezione.isDisposizionePura())
                                    p.println("<td style='padding:5px;border:1px solid black;vertical-align: middle;background-color:white'><center>" +
                                            "D" + button_dettagli
                                            + "</center></td>");
                                else
                                    p.println("<td style='padding:5px;border:1px solid black;vertical-align: middle;background-color:white'><center>" +
                                            "*P*" + button_dettagli
                                            + "</center></td>");


                                break;
                            }
                            default: {
                                final ClassData classe = lezione.getClasse();
                                final String aula = lezione.getAula() == null ? "-" : lezione.getAula().simpleName();
                                String materia = UtilMaterie.abbreviazioneMateria(lezione);
                                if (lezione.getAula() != null)
                                    auleUtilizzate.add(lezione.getAula());

                                if (!SKIP_DDI_MISTA) {
                                    if (isDDIGiornaliera) {

                                        p.printf("<td style='padding:10px;border:1px solid black;vertical-align: middle;background-color:white'>" +
                                                "<center>" +
                                                "<span style='color:blue;font-size:200%%'><b>%s</b></span><br><span style='font-size:110%%'>%s <b>%s</b></span>%s%s (**)</center>" +
                                                "</td>%n", classe.className, materia, aula, button_dettagli, testo_dettagli);

                                    } else if (ora.compareTo(inizioDidatticaAScuola) <= 0 || ora.compareTo(fineDidatticaAScuola) >= 0) {
                                        p.printf("<td style='padding:10px;border:1px solid black;vertical-align: middle;background-color:white'>" +
                                                "<center>" +
                                                "<span style='color:blue;font-size:200%%'><b>%s</b></span><br><span style='font-size:110%%'>%s <b>%s</b></span>%s%s (*)</center>" +
                                                "</td>%n", classe.className, materia, aula, button_dettagli, testo_dettagli);

                                    } else {
                                        p.printf("<td style='padding:10px;border:1px solid black;vertical-align: middle;background-color:white'>" +
                                                "<center>" +
                                                "<span style='color:blue;font-size:200%%'><b>%s</b></span><br><span style='font-size:110%%'>%s <b>%s</b></span>%s%s</center>" +
                                                "</td>%n", classe.className, materia, aula, button_dettagli, testo_dettagli);
                                    }
                                } else {
                                    boolean isDDI = (lezione.getAula() != null && lezione.getAula().isDDI());
                                    if (!isDDI) {
                                        p.printf("<td style='padding:10px;border:1px solid black;vertical-align: middle;background-color:white'>" +
                                                "<center>" +
                                                "<span style='color:blue;font-size:200%%'><b>%s</b></span><br><span style='font-size:110%%'>%s <b>%s</b></span>%s%s</center>" +
                                                "</td>%n", classe.className, materia, aula, button_dettagli, testo_dettagli);
                                    } else {
                                        p.printf("<td style='padding:10px;border:2px solid red;vertical-align: middle;background-color:#D6EAF8'>" +
                                                "<center>" +
                                                "<span style='color:blue;font-size:200%%'><b>%s</b></span><br><span style='font-size:110%%'>%s <b>%s</b></span>%s%s</center>" +
                                                "</td>%n", classe.className, materia, aula, button_dettagli, testo_dettagli);

                                    }

                                }
                                break;

                            }

                        }
                    }


                }
                p.println("</tr>");
            }


            p.println("</table>");
            p.println("<h3>L'Indicazione DDI indica lezioni svolte in Didattica Digitale Integrata. <br>La lezione viene svolta a distanza mediante piattaforma GSuite (Classroom/Meet). " +
                    "Gli studenti restano a casa e si collegano in remoto, il docente si collega, di norma, dalle postazioni predisposte a scuola</h3>");
            if (!SKIP_DDI_MISTA) {
                p.println("<h4>Le lezioni indicate con (**) saranno svolte in modalita' remota DA CASA sia da DOCENTI che da STUDENTI</h4>");
                p.println("<h4>Le lezioni indicate con (*) saranno svolte in modalita' remota DA CASA dagli STUDENTI;" +
                        "i DOCENTI la svolgeranno di norma dall'edificio scolastico a meno che non siano in grado di raggiungere la propria abitazione o la scuola in tempo, sfruttando eventuali ore di buco presenti</h4>");
            }


            p.println("<h3 style='color:red'>Elenco aule utilizzate:</h3>");
            p.println("<ul>");
            for (RoomData r : auleUtilizzate) {
                p.println("<li>");
                if (r.isDDI()) {
                    RoomData ddi_linkedRoom = r.getDDI_linkedRoom();
                    p.printf("<b style='color:red'> %s:</b>%s area %s\n", r.simpleName(), r.usage, ddi_linkedRoom != null ? ddi_linkedRoom.location.description : r.location);
                } else
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
