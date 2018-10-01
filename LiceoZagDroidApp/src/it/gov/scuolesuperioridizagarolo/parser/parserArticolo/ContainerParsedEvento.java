package it.gov.scuolesuperioridizagarolo.parser.parserArticolo;

import it.gov.scuolesuperioridizagarolo.parser.impl.Token;

import java.util.Date;

/**
 * Created by stefano on 27/09/2018.
 */
public class ContainerParsedEvento extends ContainerParsedArticolo {
    public Date dataEvento = null;

    public void parseDataEvento(Token s) {
        dataEvento = parseDate(s);
    }


}
