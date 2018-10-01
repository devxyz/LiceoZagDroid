package it.gov.scuolesuperioridizagarolo.parser.parserArticolo;

import it.gov.scuolesuperioridizagarolo.parser.impl.Token;

import java.util.Date;

/**
 * Created by stefano on 27/09/2018.
 */
public class ContainerParsedCircolare extends ContainerParsedArticolo {
    public Integer numCircolare = null;
    public Date dataCircolare = null;

    @Override
    public String toString() {
        return "ParserContainerCircolare{" +
                "dataCircolare=" + dataCircolare +
                ", numCircolare=" + numCircolare +
                ", oggetto=" + oggetto +
                '}';
    }

    public void parseNumeroCircolare(Token num) {
        numCircolare = parseNumero(num);
    }

    public void parseDataCircolare(Token s) {
        dataCircolare = parseDate(s);
    }
}
