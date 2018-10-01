package it.gov.scuolesuperioridizagarolo.parser.parserArticolo;

import it.gov.scuolesuperioridizagarolo.parser.impl.Token;
import it.gov.scuolesuperioridizagarolo.parser.impl.WordParserConstants;

import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

/**
 * Created by stefano on 27/09/2018.
 */
public class ContainerParsedArticolo {
    public StringBuilder oggetto = new StringBuilder();
    public TreeSet<String> termini = new TreeSet<>();
    private Calendar c = Calendar.getInstance();

    protected Integer parseNumero(Token t) {
        if (t.kind == WordParserConstants.NUMERO)
            return Integer.parseInt(t.image.trim());
        if (t.kind == WordParserConstants.NUMERO_PUNTATO)
            return Integer.parseInt(t.image.toUpperCase().replace("N.", "").trim());
        return null;
    }

    protected Date parseDate(Token t) {
        if (t.kind == WordParserConstants.DATA) {
            String n = t.image.toLowerCase()
                    .replace("gennaio", "01")
                    .replace("febbraio", "02")
                    .replace("marzo", "03")
                    .replace("aprile", "04")
                    .replace("maggio", "05")
                    .replace("giugno", "06")
                    .replace("luglio", "07")
                    .replace("agosto", "08")
                    .replace("settembre", "09")
                    .replace("ottobre", "10")
                    .replace("novembre", "11")
                    .replace("dicembre", "12")
                    .replace("-", "/")
                    .replace(".", "/")
                    .replaceAll("[ /-]+", "/");
            String[] s = n.split("/");


            try {
                c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(s[0]));
                c.set(Calendar.MONTH, Integer.parseInt(s[1]) - 1);
                c.set(Calendar.YEAR, Integer.parseInt(s[2]));

                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.HOUR, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);

            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Errore nell'analisi della data " + t.image + "(norm:" + n + ")", e);
            }
            return c.getTime();
        }
        return null;
    }

    public void appendOggetto(Token t) {
        if (t == null) return;
        String s = t.image.trim();
        if (s.length() == 0) return;
        if (oggetto.length() > 0) {
            oggetto.append(" ").append(s);
        } else
            oggetto.append(s);
        termini.add(t.image.toUpperCase().trim());
    }

    @Override
    public String toString() {
        return "ParserContainerArticolo{" +
                "oggetto=" + oggetto +
                '}';
    }
}
