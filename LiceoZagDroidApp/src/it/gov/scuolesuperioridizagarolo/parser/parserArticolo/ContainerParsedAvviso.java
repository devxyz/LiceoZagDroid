package it.gov.scuolesuperioridizagarolo.parser.parserArticolo;

import it.gov.scuolesuperioridizagarolo.parser.impl.Token;

import java.util.Date;

/**
 * Created by stefano on 27/09/2018.
 */
public class ContainerParsedAvviso extends ContainerParsedArticolo {
    public Date dataAvviso = null;

    public void parseDataAvviso(Token s) {
        dataAvviso = parseDate(s);
    }
}
