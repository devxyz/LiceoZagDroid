package it.gov.scuolesuperioridizagarolo.parser.parserArticolo;

import it.gov.scuolesuperioridizagarolo.dao.ArticoloDB;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloType;
import it.gov.scuolesuperioridizagarolo.parser.impl.ParseException;
import it.gov.scuolesuperioridizagarolo.parser.impl.Token;
import it.gov.scuolesuperioridizagarolo.parser.impl.WordParser;
import it.gov.scuolesuperioridizagarolo.parser.impl.WordParserConstants;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by stefano on 27/09/2018.
 */
public class ParserArticolo {
    public static ContainerParsedArticolo parse(ArticoloDB a) throws ParseException {
        return parse(a.getType(), a.getTitle());
    }

    public static ContainerParsedArticolo parse(ArticoloType t, String title) throws ParseException {
        WordParser wp = new WordParser(new StringReader(title));
        final ArrayList<Token> parse = wp.parse();
        switch (t) {
            case AVVISO: {
                ContainerParsedAvviso a = new ContainerParsedAvviso();
                for (Token token : parse) {
                    a.appendOggetto(token);
                    if (token.kind == WordParserConstants.DATA && a.dataAvviso == null) {
                        a.parseDataAvviso(token);
                    }
                }
                return a;
            }

            case CIRCOLARE: {
                ContainerParsedCircolare a = new ContainerParsedCircolare();
                for (Token token : parse) {
                    a.appendOggetto(token);


                    if (token.kind == WordParserConstants.DATA && a.dataCircolare == null) {
                        a.parseDataCircolare(token);
                        a.oggetto.setLength(0);//cancello l'oggetto prima della data della circolare
                    }
                    if ((token.kind == WordParserConstants.NUMERO_PUNTATO || token.kind == WordParserConstants.NUMERO) && a.numCircolare == null) {
                        a.parseNumeroCircolare(token);
                    }

                }

                return a;
            }

            case EVENTO: {
                ContainerParsedEvento a = new ContainerParsedEvento();
                for (Token token : parse) {
                    a.appendOggetto(token);
                    if (token.kind == WordParserConstants.DATA && a.dataEvento == null) {
                        a.parseDataEvento(token);
                    }
                }

                return a;
            }

            case GENERICO: {
                ContainerParsedArticolo a = new ContainerParsedArticolo();
                for (Token token : parse) {
                    a.appendOggetto(token);
                }

                return a;
            }
        }
        return null;
    }
}
