package it.gov.scuolesuperioridizagarolo.parser;

import it.gov.scuolesuperioridizagarolo.model.TermineInfoWeb;
import it.gov.scuolesuperioridizagarolo.parser.impl.ParseException;
import it.gov.scuolesuperioridizagarolo.parser.impl.Token;
import it.gov.scuolesuperioridizagarolo.parser.impl.WordParser;
import it.gov.scuolesuperioridizagarolo.parser.impl.WordParserConstants;
import it.gov.scuolesuperioridizagarolo.parser.parserArticolo.ContainerParsedArticolo;
import it.gov.scuolesuperioridizagarolo.util.DebugUtil;
import org.tartarus.snowball.ext.ItalianStemmer;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by stefano on 15/06/15.
 */
public class ItalianWordSplit {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final Calendar c = Calendar.getInstance();

    public static String normalize(Token t) {
        switch (t.kind) {
            case WordParserConstants.DATA: {
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
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Errore nell'analisi della data " + t.image + "(norm:" + n + ")", e);
                }
                return formatter.format(c.getTime()) + "(#DATA)";
            }
            case WordParserConstants.CLASSE: {
                return t.image.toUpperCase()
                        .replace(" ", "")
                        .replace("I", "1")
                        .replace("II", "2")
                        .replace("III", "3")
                        .replace("IV", "4")
                        .replace("V", "5")
                        +
                        "(#CLASSE)";
                /*return "#CLASSE_"+t.image.toUpperCase().replace(" ", "") ;*/
            }
            case WordParserConstants.NUMERO: {
                return t.image;//"classe:" + t.image.toUpperCase().replace(" ", "");
            }
            case WordParserConstants.CIRCOLARE: {
                return t.image.toLowerCase();
            }
            case WordParserConstants.ALTRO: {
                if (t.image.length() <= 3) return null;

                final String ris = t.image.toLowerCase().trim();
                if (StopWords.stopWords.contains(ris))
                    return null;
                return ris;
            }
            default:
                if (t.image.length() <= 3) return null;

                final String ris = t.image.toLowerCase().trim();
                if (StopWords.stopWords.contains(ris))
                    return null;
                return ris;

        }
    }

    @Deprecated
    public static TreeSet<TermineInfoWeb> parseWords(String... ss) {
        return parseWords(Arrays.asList(ss));
    }

    @Deprecated
    public static Set<String> estraiRadiciTermini(Collection<String> termini) {
        ItalianStemmer it = new ItalianStemmer();
        Set<String> ris = new TreeSet<String>();
        for (String s : termini) {
            ris.add(it.stem(s));
        }
        return ris;
    }

    @Deprecated
    public static TreeSet<TermineInfoWeb> parseWords(Collection<String> ss) {
        final StringBuffer sb = new StringBuffer();
        for (String s : ss) {
            sb.append(s).append("\n");
        }
        if (DebugUtil.DEBUG__ItalianWordSplit) {
            System.out.println("PARSING CIRCOLARE:\n" + sb.toString());
        }

        final TreeMap<String, TermineInfoWeb> ris = new TreeMap<String, TermineInfoWeb>();
        final WordParser parser = new WordParser(new StringReader(sb.toString()));
        Token nextToken;
        do {
            nextToken = parser.getNextToken();
            if (nextToken.kind != WordParserConstants.ALTRO) {
                String normalize = normalize(nextToken);
                if (normalize != null) {
                    TermineInfoWeb tf = ris.get(normalize);
                    if (tf == null) {
                        tf = new TermineInfoWeb(normalize);
                        ris.put(normalize, tf);
                    }
                    tf.aggiungiOccorrenze(1);
                }
                if (DebugUtil.DEBUG__ItalianWordSplit)
                    System.out.println("WORD " + nextToken.image + " type " + WordParserConstants.tokenImage[nextToken.kind]);
            } else {
                if (DebugUtil.DEBUG__ItalianWordSplit)
                    System.out.println("SKIPPED " + nextToken.image);

            }
        } while (nextToken.kind != WordParserConstants.EOF);
        return new TreeSet<TermineInfoWeb>(ris.values());
    }

    public static ArrayList<Token> parseTextAsToken(String s) {
        ArrayList<Token> ris = new ArrayList<>();
        final WordParser parser = new WordParser(new StringReader(s));
        Token nextToken;
        do {
            nextToken = parser.getNextToken();
            ris.add(nextToken);
        } while (nextToken.kind != WordParserConstants.EOF);
        return ris;
    }

    /**
     * restituisce le parole normalizzate) estratte dalla stringa s
     * @param s
     * @return
     */
    public static ArrayList<String> parseTextNormalized(String s) {
        ArrayList<String> ris = new ArrayList<>();
        final WordParser parser = new WordParser(new StringReader(s));
        Token nextToken;
        do {
            nextToken = parser.getNextToken();
            String normalize = normalize(nextToken);
            if (normalize != null) {
                ris.add(normalize);
            }
        } while (nextToken.kind != WordParserConstants.EOF);
        return ris;
    }


    public static void main(String[] args) throws ParseException {
        //String s1 = "Circolare n.150 - 23.02.2018 - rettifica orario consigli delle classi quinte e altro - 27.02.2018";
        String s1 = "EVENTO GARA A SQUADRE OLIMPIADI DI MATEMATICA - 8 marzo 2014";
        //String s = "Avviso 23.02.2018 - rettifica orario consigli delle classi quinte - 27.02.2018";


        WordParser parser = new WordParser(new StringReader(s1));
        final ArrayList<ContainerParsedArticolo> a = new ArrayList<>();
        final ArrayList<Token> b = parser.parse();
        System.out.println(b);


/*

        parser = new WordParser(new StringReader(s1));
        Token nextToken;
        do {
            nextToken = parser.getNextToken();
            String normalize = nextToken.image;
            if (true || normalize != null) {
                //ris.add(normalize);
                System.out.println("txt:" + normalize + "\tkind:" + WordParserConstants.tokenImage[nextToken.kind] + "\tnormalize:" + normalize(nextToken));
            }
        }
        while (nextToken.kind != WordParserConstants.EOF);
        final ArrayList<String> strings = parseTextNormalized(s1);
        System.out.println(strings);
*/

    }



}
