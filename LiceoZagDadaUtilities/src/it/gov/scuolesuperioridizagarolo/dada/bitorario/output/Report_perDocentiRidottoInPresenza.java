package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
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
public class Report_perDocentiRidottoInPresenza {
    private boolean showAule = true;

    public Report_perDocentiRidottoInPresenza(boolean showAule) {
        this.showAule = showAule;
    }

    public Report_perDocentiRidottoInPresenza() {
    }


    public void print(BitOrarioGrigliaOrario o, NoteVariazioniBitOrarioGrigliaOrario note, File f, EPaperFormat format) throws IOException {
        PrintStream p = new PrintStream(f);
        p.print("<html><body>");

        inizioTabella(p);

        int count = format == EPaperFormat.A4 ? 15 : 40;
        p.println("<h1>" + o.getTitolo() + "</h1>");
        p.println("<h3>Orario Docenti: aula, classe, disciplina (Solo in presenza)</h3>");

        final TreeSet<String> docenti = o.getDocenti();
        for (String docente : docenti) {
            if (count <= 0) {
                count = format == EPaperFormat.A4 ? 15 : 40;
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

                    if (ll == null) {
                        //se ora di buco
                        if (o.isOraBucoDocente(docente, st, ox)) {
                            orebuche++;
                        } else {

                        }
                        p.print("<td style='border:1px solid black;border-left:" + spessore + "px solid black;background-color:gray'><b>" + "</td>");
                    } else {


                        if (ll.getClasse() == null) {
                            //SKIP DISPOSIZIONI
                            p.print("<td style='border:1px solid black;border-left:" + spessore + "px solid black;background-color:gray'><b>" + "</td>");
                        } else {

                            //conta le lezioni nello stesso giorno nella stessa classe
                            String val = ll.getClasse() + "_" + ll.getGiorno();
                            anomalie.putIfAbsent(val, 0);
                            Integer num = anomalie.get(val);
                            anomalie.put(val, num + 1);


                            String aulaBreve = ll.getAula() != null ? ll.getAula().simpleName() : "-";
                            String materia = UtilMaterie.abbreviazioneMateria(ll);

                            final String sx = showAule ? "<br><span style=''> (" + aulaBreve + ")" + x + "</span>" : "";
                            if (showAule) {
                                if (ll.getAula().isDDI()) {
                                    p.print("<td style='border:1px solid black;border-left:" + spessore + "px solid black;background-color:gray'> #DDI# </td>");

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
