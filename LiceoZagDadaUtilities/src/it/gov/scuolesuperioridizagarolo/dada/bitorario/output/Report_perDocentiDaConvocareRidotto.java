package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class Report_perDocentiDaConvocareRidotto {

    public Report_perDocentiDaConvocareRidotto() {
    }

    private Set<String> docentiDaConvocare(BitOrarioGrigliaOrario o) {
        Set<String> ris = new TreeSet<>();
        final TreeSet<String> docenti = o.getDocenti();
        for (String d : docenti) {
            if (d.toUpperCase().startsWith("XX_")) {
                ris.add(d);
            }
        }
        return ris;
    }

    public void print(BitOrarioGrigliaOrario o, NoteVariazioniBitOrarioGrigliaOrario note, File f, EPaperFormat format) throws IOException {
        PrintStream p = new PrintStream(f);
        p.print("<html><body>");

        inizioTabella(p);

        int count = format == EPaperFormat.A4 ? 15 : 40;
        p.println("<h1>" + o.getTitolo() + "</h1>");

        final Set<String> docenti = docentiDaConvocare(o);
        p.print("<pre>" + docenti + "</pre>");
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
            for (EGiorno st : EGiorno.values()) {
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
                        p.print("<td style='border:1px solid black;border-left:" + spessore + "px solid black;background-color:gray'><b>" + "</td>");
                    else {
                        if (ll.getClasse() == null) {
                            p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; text-align:center;color:black;background-color:yellow'><b>" + ll.getMateriaPrincipale() + x +
                                    "</b></span></td>");

                        } else {

                            String aulaBreve = ll.getAula() != null ? ll.getAula().simpleName() : "-";
                            p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; text-align:center'><b>" + ll.getClasse() +
                                    "</b><br><span style=''> (" + aulaBreve + ")" + x + "</span></td>");
                        }
                    }
                }
            }


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
            p.print("<td style='border:1px solid black;text-align:center' colspan='" + (EOra.oreDiLezione()) + "'>" + s + "</td>");
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

        }
        p.print("</tr>");
    }

}
