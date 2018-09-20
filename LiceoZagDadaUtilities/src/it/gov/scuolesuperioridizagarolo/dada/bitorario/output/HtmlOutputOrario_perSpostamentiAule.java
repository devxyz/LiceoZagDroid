package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

//import dada.bitorario.data.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlOutputOrario_perSpostamentiAule extends HtmlOutputOrario {
    @Override
    protected String getSubTitle() {
        return "";
    }

    @Override
    protected int getPrintForPage(EPaperFormat paperFormat) {
        return 1;
    }

    @Override
    protected Collection<String> raggruppaPer(BitOrarioGrigliaOrario o) {
        return Arrays.asList("fittizio");
    }

    @Override
    protected String getLezione(BitOrarioGrigliaOrario griglia, NoteVariazioniBitOrarioGrigliaOrario note, EOra o, EGiorno s, String x) {
        final TreeSet<ClassData> classi = griglia.cambiDiAula(o, s);
        StringBuilder sb = new StringBuilder();
        int cambi = 0;
        int uscita = 0;
        for (ClassData classe : classi) {
            final String s1 = griglia.dettaglioCambiaAula(classe, o, s);
            if (s1.toLowerCase().contains("uscita"))
                uscita++;
            else
                cambi++;
            sb.append("<b>" + classe + "</b> <span style='font-size:10px'>" + s1 + "</span><br>");
        }

        return "<center><b style='color:red;font-size:20px'>" + (cambi) + " cambi e " + uscita + " uscite</b><hr>" + sb + "</center>";
    }

    @Override
    protected String intestazione(String s, BitOrarioGrigliaOrario grigliaOrario) {
        return "<span style='font-size:24px;font-weight: bold;'>Cambiamenti di aula al termine dell'ora</span>";
    }
}
