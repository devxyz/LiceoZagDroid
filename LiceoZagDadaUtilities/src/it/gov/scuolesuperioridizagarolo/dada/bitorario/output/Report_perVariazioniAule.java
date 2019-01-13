package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.TreeSet;

/**
 * Created by stefano on 01/08/2018.
 */
public class Report_perVariazioniAule {
    public void print(BitOrarioGrigliaOrario nuovoOrario, BitOrarioGrigliaOrario vecchioOrario, File f) throws IOException {


        final NoteVariazioniBitOrarioGrigliaOrario note = NoteVariazioniBitOrarioGrigliaOrario.generateDifferenze(vecchioOrario, nuovoOrario);

        PrintStream out = new PrintStream(f);
        out.println("<html>" +
                "<head>\n" +
                "<style>\n" +
                "td  " +
                "h1  " +
                "html {color:black;} " +
                ".pagebreak { page-break-before: always; } " +
                "</style>\n" +
                "</head>" +
                "<body>");
        out.println("<h1>" + nuovoOrario.getTitolo() + "</h1>");

        TreeSet<BitOrarioOraLezione> lezioniModificate = new TreeSet<>(note.getMapNote().keySet());


        //variazioni
        out.println("<table border=1>");
        out.println("<tr>");
        out.println("<td style='font-weight:bolder'>Giorno</td>\n");
        out.println("<td style='font-weight:bolder'>Ora</td>\n");
        out.println("<td style='font-weight:bolder'>Classe</td>\n");
        out.println("<td style='font-weight:bolder'>Docente</td>\n");
        out.println("<td style='font-weight:bolder'>Lezione</td>\n");
        out.println("<td style='font-weight:bolder'>Aula Prec.</td>\n");
        out.println("<td style='font-weight:bolder'>Aula</td>\n");
        out.println("<td style='font-weight:bolder'>Note</td>\n");
        out.println("</tr>\n");

        int i = 1;
        String prec = "";
        for (BitOrarioOraLezione x : lezioniModificate) {
            String docente = x.getDocentiFormatted();
            RoomData precAula = null;
            final BitOrarioOraLezione precLezione = vecchioOrario.getLezioneConDocente(x.getOra(), x.getGiorno(), x.getDocentePrincipale());



            if (precLezione != null && precLezione.getAula() != null) {
                precAula = precLezione.getAula();
            }

            if (!prec.equals(x.getGiorno().getNome())) {
                i++;
                prec = x.getGiorno().getNome();
            }

            if (precAula == null) continue;

            out.println("<tr>");
            out.println("<td style='font-size:120%;font-weight:bolder;'>" + x.getGiorno().getNome().toUpperCase() + "</td>\n");
            out.println("<td style='font-size:120%;font-weight:bolder;'>" + x.getOra().getProgressivOra() + "</td>\n");
            out.println("<td style=''>" + x.getClasse() + "</td>\n");
            out.println("<td style=''>" + (docente) + "</td>\n");
            out.println("<td style=''>" + x.getMateriaPrincipale() + "</td>\n");
            out.println("<td style=''>" + precAula + "</td>\n");
            out.println("<td style='font-weight:bolder;'>" + (x.getAula() != null ? x.getAula() : "") + "</td>\n");
            out.println("<td style=''>" + (x.getNote() != null ? x.getNote() : "") + "</td>\n");

            out.println("</tr>\n");
        }
        out.println("</table>");

        out.println("</body></html>");
    }
}