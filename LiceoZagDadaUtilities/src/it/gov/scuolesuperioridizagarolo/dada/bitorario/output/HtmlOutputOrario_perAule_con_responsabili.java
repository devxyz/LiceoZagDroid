package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlOutputOrario_perAule_con_responsabili {
    public void print(BitOrarioGrigliaOrario o, File f) throws IOException {
        PrintStream p = new PrintStream(f);
        p.print("<html><body>");
        String titolo = o.getTitolo();

        final TreeSet<String> aule = o.getAule();
        for (String a : aule) {
            p.print(titolo);
            p.print("<h1>Aula " + a);
            p.print(" - <span style='font-size:14px'>Docenti responsabili della chiusura del PC di classe, riconsegna chiavi e registri cartacei</span></h1>");
            p.print("<table style='width:100%;table-layout: fixed; border:4px solid black; '>");

            //==============================
            p.print("<tr>");
            p.print("<td style='width:5%' > </td>");
            for (EGiorno s : EGiorno.values()) {
                if (!s.flagGiornoDiLezione()) continue;
                p.print("<td style='width:15%'><b>" + s + "</b></td>");

            }
            p.print("</tr>");
            for (EOra ora : EOra.values()) {
                if (!ora.flagOraDiLezione()) continue;

                p.print("<tr  style='border:1px solid black'>");
                p.print("<td style='text-align: right;' >" + ora.printOra() + "</td>");
                for (EGiorno settimana : EGiorno.values()) {
                    if (!settimana.flagGiornoDiLezione()) continue;

                    final List<BitOrarioOraLezione> lezioneInAula = o.getLezioneInAula(ora, settimana, a);
                    if (lezioneInAula.size() > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (BitOrarioOraLezione s : lezioneInAula) {
                            final boolean chiusuraPC = o.oraLezioneChiusuraPC(s.getNomeAula(), settimana, ora);
                            final boolean consegnaRegistro = o.oraLezioneConsegnaRegistro(s.getClasse(), settimana, ora);
                            if (chiusuraPC || consegnaRegistro) {
                                sb.append("<b>" + s.getDocentePrincipale() + " (" + s.getClasse() + "):<br></b>");
                                if (chiusuraPC)
                                    sb.append("<br> - Chiusura Armadi/PC");
                                if (consegnaRegistro)
                                    sb.append("<br> - Consegna registro " + s.getClasse() + "");

                                if (lezioneInAula.size() > 1)
                                    sb.append("<hr style='border:1px solid black'>");
                            }
                        }


                        if (sb.length() > 0) {
                            p.print("<td style='border:3px solid black; text-align:left; background-color:lightgray'>");
                            p.print(sb.toString());
                            p.print("</td>");
                        } else {
                            p.print("<td style='border:1px solid black; text-align:center; background-color:white'>");
                            for (BitOrarioOraLezione x : lezioneInAula) {
                                p.print(" <i>" + x.getClasse() + "</i>");
                            }

                            p.print("</td>");
                        }

                    } else {
                        p.print("<td style='border:1px solid black; text-align:center'>");
                        p.print("</td>");

                    }
                }
                p.print("</tr>");
            }
            p.print("</table>");
            p.print("<div style='display: block; page-break-before: always;'></div>");
        }


    }
}
