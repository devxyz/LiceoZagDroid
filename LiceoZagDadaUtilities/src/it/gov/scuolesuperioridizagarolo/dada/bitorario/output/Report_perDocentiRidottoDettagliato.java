package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

//import dada.bitorario.data.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class Report_perDocentiRidottoDettagliato {

    public Report_perDocentiRidottoDettagliato() {
    }

    private static String reduce(String s) {
        if (s.length() < 3) return ("" + s.charAt(0)).toUpperCase() + s.substring(1).toLowerCase();
        final String substring = s.substring(0, 3);
        return ("" + substring.charAt(0)).toUpperCase() + substring.substring(1).toLowerCase();
    }


    public void print(BitOrarioGrigliaOrario o, File f, EPaperFormat format) throws IOException {
        PrintStream p = new PrintStream(f);
        p.print("<html><body>");

        inizioTabella(p);

        int count = format == EPaperFormat.A4 ? 16 : 30;
        p.println("<h1>" + o.getTitolo() + "</h1>");

        final TreeSet<String> docenti = o.getDocenti();
        for (String docente : docenti) {
            if (count <= 0) {
                count = format == EPaperFormat.A4 ? 16 : 30;
                fineTabella(p);
                p.println("<br>");
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
                    final BitOrarioOraLezione ll = o.getLezioneConDocente(ox, st, docente);

                    int spessore;
                    if (ox.ordinal() == 0) {
                        spessore = 5;
                    } else spessore = 1;

                    if (ll == null)
                        p.print("<td style='border:1px solid black;border-left:" + spessore + "px solid black;background-color:gray'>" + "</td>");
                    else {
                        if (ll.getClasse() == null) {
                            p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; text-align:center;color:red;background-color:yellow'><b>" + ll.getMateriaPrincipale() +
                                    "</b></span></td>");

                        } else {

                            String aulaBreve = ll.getAula() != null ? ll.getAula().simpleName() : "-";
                            p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; text-align:center'><b>" + ll.getClasse() +
                                    "</b><br>" +
                                    reduce(ll.getMateriaPrincipale()) + "<br>" +
                                    "<span style=''> (" + aulaBreve + ")" + "</span></td>");
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
        p.print("<table cellspacing=0 style=';border:4px solid black;width:100%'>");
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
