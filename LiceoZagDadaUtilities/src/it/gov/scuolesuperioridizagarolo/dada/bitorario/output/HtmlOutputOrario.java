package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;

/**
 * Created by stefano on 25/09/2017.
 */
public abstract class HtmlOutputOrario {

    public HtmlOutputOrario() {
    }

    protected abstract String getSubTitle();

    protected abstract int getPrintForPage(EPaperFormat paperFormat);

    protected abstract Collection<String> raggruppaPer(BitOrarioGrigliaOrario o);

    protected abstract String getLezione(BitOrarioGrigliaOrario griglia, NoteVariazioniBitOrarioGrigliaOrario note, EOra o, EGiorno s, String key);

    protected abstract String intestazione(String s, BitOrarioGrigliaOrario grigliaOrario);

    protected int defaultCellFontSize() {
        return 10;
    }

    protected boolean extraPagebreak(BitOrarioGrigliaOrario griglia, NoteVariazioniBitOrarioGrigliaOrario note, String gruppo) {
        return false;
    }

    public void print(BitOrarioGrigliaOrario o, NoteVariazioniBitOrarioGrigliaOrario note, File f, EPaperFormat paperFormat) throws IOException {
        PrintStream p = new PrintStream(f);
        p.print("<html><body>");
        final Collection<String> strings = raggruppaPer(o);

        final int printForPage = getPrintForPage(paperFormat);
        int i = 0;
        p.print("<h1>" + o.getTitolo() + "</h1>");
        p.println("<h3>" + getSubTitle() + "</h3>");
        for (String gruppo : strings) {
            boolean b = extraPagebreak(o, note, gruppo);
            if (i >= printForPage || b) {
                p.print("<div style='display: block; page-break-before: always;'></div>");
                p.print("<h1>" + o.getTitolo() + "</h1>");
                p.println("<h3>" + getSubTitle() + "</h3>");
                i = 0;
            }


            p.print("<table cellspacing=0 style='width:100%;table-layout: fixed; border:4px solid black; '>");

            p.print("<tr style='border:1px solid clack' >");
            p.print("<td style='border:1px solid black; background-color:lightgray;;' colspan='" + (1 + EGiorno.numeroGiorniDiLezione()) + "'>");
            p.print(intestazione(gruppo, o));
            p.print("</td>");
            p.print("</tr>");

            //==============================
            p.print("<tr>");
            p.print("<td style='width:5%' > </td>");
            for (EGiorno s : EGiorno.values()) {
                if (!s.flagGiornoDiLezione()) continue;
                p.print("<td style='width:15%;font-size:16px;'><b>" + s + "</b></td>");
            }
            p.print("</tr>");


            for (EOra ora : EOra.values()) {

                if (!ora.flagOraDiLezione()) continue;

                p.print("<tr  style='border:1px solid black'>");
                p.print("<td style='text-align: right;'><span style='font-size:18px'>" + ora.getProgressivOra() + "</span> (dalle ore " + ora.printOraInizioPresenza() + ")</td>");
                for (EGiorno settimana : EGiorno.values()) {
                    if (!settimana.flagGiornoDiLezione()) {
                        continue;
                    }
                    final String print = getLezione(o, note, ora, settimana, gruppo);
                    if (print == null || print.trim().length() == 0)
                        p.print("<td style='border:1px solid black;background-color:lightgray;vertical-align: text-top;'>" + print + "</td>");
                    else
                        p.print("<td style='border:1px solid black;vertical-align: text-top;font-size:" + defaultCellFontSize() + "px;'>" + print + "</td>");

                }
                p.print("</tr>");
            }


            p.print("</table><br>");
            i++;
        }


        p.print("</body></html>");
        p.close();
    }


}
