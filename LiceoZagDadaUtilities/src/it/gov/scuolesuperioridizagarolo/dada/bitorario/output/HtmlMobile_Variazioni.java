package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlMobile_Variazioni {

    public HtmlMobile_Variazioni() {
    }


    public void print(BitOrarioGrigliaOrario orarioStandard, NoteVariazioniBitOrarioGrigliaOrario note, File variazioni) throws IOException {
        //variazioni
        TreeSet<BitOrarioOraLezione> lezioniModificate = new TreeSet<>(note.getMapNote().keySet());
        StringBuffer sb = new StringBuffer();
        sb.append("<table border=1>");
        sb.append("<tr>");
        sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Giorno</td>\n");
        sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Ora</td>\n");
        sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Classe</td>\n");
        sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Docente</td>\n");
        sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Lezione</td>\n");
        sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Aula Prec.</td>\n");
        sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Aula</td>\n");
        sb.append("<td style='font-size:150%;font-weight:bolder;background-color:blue;color:white'>Note</td>\n");
        sb.append("</tr>\n");
        String[] colori = new String[]{"YELLOW", "PINK"};

        int i = 1;
        String prec = "";
        for (BitOrarioOraLezione x : lezioniModificate) {
            final String docente = x.getDocentiFormatted();
            RoomData precAula = null;
            final BitOrarioOraLezione precLezione = orarioStandard.getLezioneConDocente(x.getOra(), x.getGiorno(), x.getDocentePrincipale());
            if (precLezione != null && precLezione.getAula() != null) {
                precAula = precLezione.getAula();
            }

            if (!prec.equals(x.getGiorno().getNome())) {
                i++;
                prec = x.getGiorno().getNome();
            }

            String color = colori[i % colori.length];
            sb.append("<tr>");
            sb.append("<td style='font-size:120%;font-weight:bolder;background-color:" + color + "'>" + x.getGiorno().getNome().toUpperCase() + "</td>\n");
            sb.append("<td style='font-size:120%;font-weight:bolder;background-color:" + color + "'>" + x.getOra().getProgressivOra() + "</td>\n");
            sb.append("<td style='background-color:" + color + "'>" + x.getClasse() + "</td>\n");
            sb.append("<td style='background-color:" + color + "'>" + (docente) + "</td>\n");
            sb.append("<td style='background-color:" + color + "'>" + x.getMateriaPrincipale() + "</td>\n");
            sb.append("<td style='background-color:" + color + "'>" + precAula + "</td>\n");
            sb.append("<td style='font-size:150%;font-weight:bolder;background-color:" + color + "'>" + (x.getAula() != null ? x.getAula() : "") + "</td>\n");
            sb.append("<td style='background-color:" + color + "'>" + (x.getNote() != null ? x.getNote() : "") + "</td>\n");

            sb.append("</tr>\n");
        }
        sb.append("</table>");


        FileWriter w2 = new FileWriter(variazioni);
        w2.write(sb.toString());
        w2.close();
    }
}
