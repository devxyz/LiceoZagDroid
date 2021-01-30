package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class Report_perDocentiRidotto {
    private boolean showAule = true;

    public Report_perDocentiRidotto(boolean showAule) {
        this.showAule = showAule;
    }

    public Report_perDocentiRidotto() {
    }


    public void print(BitOrarioGrigliaOrario o, NoteVariazioniBitOrarioGrigliaOrario note, File f, EPaperFormat format) throws IOException {
        PrintStream p = new PrintStream(f);
        p.print("<html><body>");

        inizioTabella(p);

        int count = format == EPaperFormat.A4 ? 10 : 20;
        p.println("<h1>" + o.getTitolo() + "</h1>");
        p.println("<h3>Orario Docenti: aula, classe, disciplina</h3>");
        p.println("<h4>Le lezioni indicate con (C-C) saranno svolte in modalita' remota DA CASA sia da DOCENTI che da STUDENTI</h4>");
        p.println("<h4>Le lezioni indicate con (C-S) saranno svolte in modalita' remota DA CASA dagli STUDENTI;" +
                "i DOCENTI la svolgeranno di norma dall'edificio scolastico a meno che non siano in grado di raggiungere la propria abitazione o la scuola in tempo, sfruttando eventuali ore di buco presenti</h4>");

        final TreeSet<String> docenti = o.getDocenti();
        for (String docente : docenti) {
            if (count <= 0) {
                count = format == EPaperFormat.A4 ? 10 : 20;
                fineTabella(p);
                p.println("(*) modifiche all'orario<br>");
                p.println("<div style='display: block; page-break-before: always;'></div>");

                p.println("<h1>" + o.getTitolo() + "</h1>");
                inizioTabella(p);
            }

            count--;

            p.print("<tr>");
            p.print("<td style='border:1px solid black'>" + docente + "</td>");
            int orebuche = 0;
            Map<String, Integer> anomalie = new TreeMap<>();
            for (EGiorno st : EGiorno.values()) {

                boolean isDDIGiornaliera = o.isFullDDI(docente, st, false);
                EOra[] t = o.intervalloDidatticaScuola__conTrasferimento(docente, st);
                EOra inizioDidatticaAScuola = t[0];
                EOra fineDidatticaAScuola = t[1];


                if (!st.flagGiornoDiLezione()) continue;
                for (EOra ox : EOra.values()) {

                    if (!ox.flagOraDiLezione()) continue;
                    final BitOrarioOraLezione ll = o.getLezioneConDocente(docente, st, ox);

                    String x = "";
                    if (note.getNote(ll) != null)
                        x = "<span style='color:white;background-color:black;padding:1px'> * </span> ";

                    int spessore;
                    if (ox.ordinal() == 0) {
                        spessore = 5;
                    } else spessore = 1;

                    if (ll == null)
                        //se ora di buco
                        if (o.isOraBucoDocente(docente, st, ox)) {
                            p.print("<td style='border:1px solid black;border-left:" + spessore + "px solid black;background-color:red'><b>" + "</td>");
                            orebuche++;
                        } else {
                            p.print("<td style='border:1px solid black;border-left:" + spessore + "px solid black;background-color:gray'><b>" + "</td>");
                        }
                    else {


                        if (ll.getClasse() == null) {
                            if (ll.isDisposizionePura())
                                p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; text-align:center;color:black;background-color:yellow'><b>D" + x +
                                        "</b></span></td>");
                            else

                                p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; text-align:center;color:black;background-color:yellow'><b>*P*" + x +
                                        "</b></span></td>");

                        } else {

                            //conta le lezioni nello stesso giorno nella stessa classe
                            String val = ll.getClasse() + "_" + ll.getGiorno();
                            anomalie.putIfAbsent(val, 0);
                            Integer num = anomalie.get(val);
                            anomalie.put(val, num + 1);


                            String aulaBreve = ll.getAula() != null ? ll.getAula().simpleName() : "-";
                            String materia = UtilMaterie.abbreviazioneMateria(ll);

                            String sx = showAule ? "<br><span style=''> (" + aulaBreve + ")" + x + "</span>" : "";
                            if (showAule) {
                                if (ll.getAula().isDDI()) {
                                    if (isDDIGiornaliera) {
                                        sx = showAule ? "<br><span style=''> (" + aulaBreve + " C/C)" + x + "</span>" : "";
                                        p.print("<td style='border:3px solid red; border-left:" + spessore + "px solid black; background:#AED6F1 ;text-align:center'><b>" + ll.getClasse() +
                                                "</b> <i>" + materia + "</i>" + sx + "</td>");
                                    }
                                    else if (ox.compareTo(inizioDidatticaAScuola) <= 0 || ox.compareTo(fineDidatticaAScuola) >= 0) {
                                        sx = showAule ? "<br><span style=''> (" + aulaBreve + " C/S)" + x + "</span>" : "";
                                        p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; background:#D2B4DE ;text-align:center'><b>" + ll.getClasse() +
                                                "</b> <i>" + materia + "</i>" + sx + "</td>");

                                    } else {
                                        p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; background:#FAD7A0 ;text-align:center'><b>" + ll.getClasse() +
                                                "</b> <i>" + materia + "</i>" + sx + "</td>");
                                    }

                                } else {
                                    p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; text-align:center'><b>" + ll.getClasse() +
                                            "</b> <i>" + materia + "</i>" + sx + "</td>");
                                }

                            } else {
                                p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; text-align:center'><b>" + ll.getClasse() +
                                        "</b> <i>" + materia + "</i>" + sx + "</td>");
                            }
                        }
                    }
                }
                p.print("<td style='boder:0px;border-left:5px solid black;border-right:5px solid black; text-align:center'> &nbsp; </td>");
            }

            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Integer> e : anomalie.entrySet()) {
                if (e.getValue() > 2) {
                    sb.append(e.getKey() + ":" + e.getValue() + " ");
                }
            }

            p.println("<td> ore buche: " + orebuche + " " + sb + "</d>");

            p.print("</tr>");

        }

        fineTabella(p);
        p.print("</body></html>");
        p.close();
    }

    private void fineTabella(PrintStream p) {
        p.print("</table>");
    }

    private void inizioTabella(PrintStream p) {
        p.print("<table cellspacing=0 width:100% style=';border:4px solid black; '>");
        p.print("<tr>");
        p.print("<td></td>");
        for (EGiorno s : EGiorno.values()) {
            if (!s.flagGiornoDiLezione()) continue;
            p.print("<td style='border:1px solid black;text-align:center' colspan='" + (EOra.oreDiLezione() + 1) + "'>" + s + "</td>");
        }
        p.print("</tr>");

        p.print("<tr>");
        p.print("<td style='border:1px solid black;width=10'>nome docente</td>");
        for (EGiorno s : EGiorno.values()) {
            if (!s.flagGiornoDiLezione()) continue;
            for (EOra o2 : EOra.values()) {

                if (!o2.flagOraDiLezione()) continue;
                p.print("<td style='border:1px solid black; text-align:center'>" + o2.getProgressivOra() + "</td>");
            }
            p.print("<td style='border:1px solid black; text-align:center'> &nbsp; </td>");
        }
        p.println("<td>NOTE</td>");
        p.print("</tr>");
    }

}
