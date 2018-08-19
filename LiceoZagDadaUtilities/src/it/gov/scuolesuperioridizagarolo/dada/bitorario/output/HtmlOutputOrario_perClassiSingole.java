package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EPaperFormat;

/**
 * Created by stefano on 25/09/2017.
 */
public class HtmlOutputOrario_perClassiSingole extends HtmlOutputOrario_perClassi {
    @Override
    protected String getSubTitle() {
        return "Orario per Classi";
    }

    @Override
    protected int getPrintForPage(EPaperFormat paperFormat) {
        if (paperFormat == EPaperFormat.A3)
            return 1;
        else
            return 1;
    }

    protected int defaultCellFontSize() {
        return 18;
    }


}
