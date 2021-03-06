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
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class Report_perClasseRidotto2 {
    private boolean showAule = true;

    public Report_perClasseRidotto2(boolean showAule) {
        this.showAule = showAule;
    }

    public Report_perClasseRidotto2() {
    }

    public static boolean isConsanant(char c) {
        String cons = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        return cons.contains("" + c);
    }

    public static void main(String[] args) {
        System.out.println(UtilMaterie.abbreviazioneMateria("scienze motorie"));
    }


    public void print(BitOrarioGrigliaOrario o, NoteVariazioniBitOrarioGrigliaOrario note, File f, EPaperFormat format) throws IOException {
        PrintStream p = new PrintStream(f);
        p.print("<html><body>");
        EGiorno[] giorni = EGiorno.values();
        generaTabella(o, note, format, p, new EGiorno[]{EGiorno.LUNEDI, EGiorno.MARTEDI,EGiorno.MERCOLEDI, EGiorno.GIOVEDI,EGiorno.VENERDI});
        p.println("<div style='display: block; page-break-before: always;'></div>");
        //generaTabella(o, note, format, p, new EGiorno[]{EGiorno.MERCOLEDI, EGiorno.GIOVEDI});
        //p.println("<div style='display: block; page-break-before: always;'></div>");
        //generaTabella(o, note, format, p, new EGiorno[]{EGiorno.VENERDI});


        p.print("</body></html>");
        p.close();
    }

    private void generaTabella(BitOrarioGrigliaOrario o, NoteVariazioniBitOrarioGrigliaOrario note, EPaperFormat format, PrintStream p, EGiorno[] giorni) {
        inizioTabella(p, giorni);
        int count = format == EPaperFormat.A4 ? 11 : 40;
        p.println("<h1>" + o.getTitolo() + "</h1>");
        p.println("<h3>Orario Classi: aula, disciplina, insegnante</h3>");
        final TreeSet<ClassData> classi = o.getClassi();
        for (ClassData classe : classi) {
            if (count <= 0) {
                count = format == EPaperFormat.A4 ? 11 : 40;
                fineTabella(p);
                p.println("(*) modifiche all'orario<br>");
                p.println("<div style='display: block; page-break-before: always;'></div>");

                p.println("<h1>" + o.getTitolo() + "</h1>");
                inizioTabella(p, giorni);
            }

            count--;

            p.print("<tr>");
            p.print("<td style='border:1px solid black;border-right:5px solid black'>" + classe + "</td>");

            for (EGiorno st : giorni) {
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
                            p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; text-align:center;color:black;background-color:yellow'><b>" + UtilMaterie.abbreviazioneMateria(ll) + x +
                                    "</b></span></td>");

                        } else {

                            String aulaBreve = ll.getAula() != null ? ll.getAula().simpleName() : "-";
                            final String sx = showAule ? "<br><span style=''> (" + aulaBreve + ")" + x + "</span>" : "";
                            p.print("<td style='border:1px solid black; border-left:" + spessore + "px solid black; text-align:center'><b>" + UtilMaterie.abbreviazioneMateria(ll) +
                                    "</b> <br> <i><small>" + ll.getDocentePrincipale().replace(" ", "_") +
                                    (ll.getDocenteCompresenza() != null ? " " + (ll.getDocenteCompresenza().replace(" ", "_")) : "") + " " +
                                    (ll.getDocenteSostegno() != null ? " " + (ll.getDocenteSostegno().replace(" ", "_")) : "")
                                    + "</small></i>" + sx + "</td>");
                        }
                    }
                }
                p.print("<td style='boder:0px;border-left:5px solid black;border-right:5px solid black; text-align:center'> &nbsp; </td>");
            }


            p.print("</tr>");

        }

        fineTabella(p);
    }

    private void fineTabella(PrintStream p) {
        p.print("</table>");
    }

    private void inizioTabella(PrintStream p, EGiorno[] giorni) {
        p.print("<table cellspacing=0 width:100% style=';border:4px solid black; '>");
        p.print("<tr>");
        p.print("<td></td>");
        for (EGiorno s : giorni) {
            if (!s.flagGiornoDiLezione()) continue;
            p.print("<td style='border:1px solid black;text-align:center' colspan='" + (EOra.oreDiLezione() + 1) + "'>" + s + "</td>");
        }
        p.print("</tr>");

        p.print("<tr>");
        p.print("<td style='border:1px solid black;width=10'>classe</td>");
        for (EGiorno s : giorni) {
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
