package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

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
public class Report_perDocentiRidottoTestuale {
    private boolean showAule = false;

    public Report_perDocentiRidottoTestuale(boolean showAule) {
        this.showAule = showAule;
    }

    public Report_perDocentiRidottoTestuale() {
    }


    public void print(BitOrarioGrigliaOrario o, NoteVariazioniBitOrarioGrigliaOrario note, File f, EPaperFormat format) throws IOException {
        PrintStream p = new PrintStream(f);


        int count = format == EPaperFormat.A4 ? 15 : 40;
        p.println(o.getTitolo());
        p.println("Orario Docenti: aula, classe, disciplin");
        final TreeSet<String> docenti = o.getDocenti();
        for (String docente : docenti) {
            p.println("\n" + docente);
            for (EGiorno st : EGiorno.values()) {
                p.println("---- " + st + " ----");
                if (!st.flagGiornoDiLezione()) continue;
                for (EOra ox : EOra.values()) {

                    if (!ox.flagOraDiLezione()) continue;
                    final BitOrarioOraLezione ll = o.getLezioneConDocente(ox, st, docente);


                    int spessore;
                    if (ox.ordinal() == 0) {
                        spessore = 5;
                    } else spessore = 1;

                    if (ll == null) {
                    } else {
                        if (ll.getClasse() == null) {
                            if (ll.isDisposizionePura())
                                p.print(ox + ": DISPOSIZIONE\n");
                            else
                                p.print(ox + ": PROGETTO POTENZIAMENTO\n");
                        } else {


                            String aulaBreve = ll.getAula() != null ? ll.getAula().simpleName() : "-";
                            String materia = ll.getMateriaPrincipale();

                            final String sx = ox + ": " + ll.getClasse() +
                                    " " + materia + " " + (showAule ? ox + " (" + aulaBreve + ")" : "");
                            p.println(sx);
                        }
                    }
                }
            }



        }

        p.close();
    }

}
