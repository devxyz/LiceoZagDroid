package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class Report_perClasseRidottoSplitGiorni {
    private boolean showAule = true;

    public Report_perClasseRidottoSplitGiorni(boolean showAule) {
        this.showAule = showAule;
    }

    public Report_perClasseRidottoSplitGiorni() {
    }

    public static boolean isConsanant(char c) {
        String cons = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        return cons.contains("" + c);
    }

    public static String abbreviazioneMateria(BitOrarioOraLezione ll) {

        final String materiaPrincipale = ll.getMateriaPrincipale();
        int min = materiaPrincipale.contains(".") ? Math.min(materiaPrincipale.length(), 6) : Math.min(materiaPrincipale.length(), 3);
        while (min > 3 && !isConsanant(materiaPrincipale.charAt(min - 1))) {
            min--;
        }
        return ll.getMateriaPrincipale().substring(0, min);
    }

    public void print(BitOrarioGrigliaOrario o, NoteVariazioniBitOrarioGrigliaOrario note, File f, EPaperFormat format) throws IOException {
        PrintStream p = new PrintStream(f);
        p.print("<html><body>");


        List<EGiorno[]> intervalli = new ArrayList<>();
        intervalli.add(new EGiorno[]{EGiorno.LUNEDI, EGiorno.MARTEDI, EGiorno.MERCOLEDI});
        intervalli.add(new EGiorno[]{EGiorno.GIOVEDI, EGiorno.VENERDI, EGiorno.SABATO});
        for (EGiorno[] intervallo : intervalli) {
            inizioTabella(p, intervallo);

            p.println("<h1>" + o.getTitolo() + "</h1>");
            final TreeSet<ClassData> classi = o.getClassi();
            for (ClassData classe : classi) {

                p.print("<tr>");
                p.print("<td style='border:1px solid black;border-right:5px solid black;font-size:36px'><b>" + classe + "</b></td>");
                for (EGiorno st : intervallo) {
                    if (!st.flagGiornoDiLezione()) continue;
                    for (EOra ox : EOra.values()) {

                        if (!ox.flagOraDiLezione()) continue;
                        final BitOrarioOraLezione ll = o.getLezioneInClasse(ox, st, classe);

                        String x = "";
                        if (note.getNote(ll) != null)
                            x = "<span style='color:white;background-color:black;padding:1px'> * </span> ";

                        int spessore;
                        if (ox.ordinal() == 0) {
                            spessore = 5;
                        } else spessore = 1;

                        if (ll == null)
                            p.print("<td style='border:1px solid black;border-left:" + spessore + "px solid black;background-color:lightgray'><b>" + "</td>");
                        else {
                            if (ll.getClasse() == null) {
                                p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; text-align:center;color:black;background-color:yellow'><b>" + abbreviazioneMateria(ll) + x +
                                        "</b></span></td>");

                            } else {

                                String aulaBreve = ll.getAula() != null ? ll.getAula().simpleName() : "-";
                                final String sx = showAule ? "<br><span style=''> (" + aulaBreve + ")" + x + "</span>" : "";
                                p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; text-align:center'><b style='font-size:20px'>" + abbreviazioneMateria(ll) +
                                        "</b>" + sx + "</td>");
                            }
                        }
                    }
                    p.print("<td style='boder:1px;border-left:5px solid black;border-right:15px solid black; text-align:center;font-size:36px''>  </td>");
                }


                p.print("</tr>");

            }

            fineTabella(p);


            p.println("<div style='display: block; page-break-before: always;'></div>");

        }

        p.print("</body></html>");
        p.close();
    }

    private void fineTabella(PrintStream p) {
        p.print("</table>");
    }

    private void inizioTabella(PrintStream p, EGiorno[] intervallo) {
        p.print("<table cellspacing=0 width:100% style=';border:4px solid black; '>");
        p.print("<tr>");
        p.print("<td></td>");
        for (EGiorno s : intervallo) {
            if (!s.flagGiornoDiLezione()) continue;
            p.print("<td style='border:1px solid black;text-align:center' colspan='" + (EOra.oreDiLezione() + 1) + "'><h1>" + s + "</h1></td>");
        }
        p.print("</tr>");

        p.print("<tr>");
        p.print("<td style='border:1px solid black;width=10'>classe</td>");
        for (EGiorno s : intervallo) {
            if (!s.flagGiornoDiLezione()) continue;
            for (EOra o2 : EOra.values()) {

                if (!o2.flagOraDiLezione()) continue;
                p.print("<td style='border:1px solid black; text-align:center'>" + o2.getProgressivOra() + "</td>");
            }
            p.print("<td style='boder:0px;border-left:5px solid black;border-right:5px solid black; text-align:center'> &nbsp; </td>");

        }
        p.print("</tr>");
    }

}
