package it.gov.scuolesuperioridizagarolo.util;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

/**
 * Created by stefano on 11/03/15.
 */

public class C_TextUtil {


    private static final ArrayList<String[]> stopWordsCircolai = new ArrayList<>(Arrays.asList(
            "Powered by TCPDF www.tcpdf.org".split("[ ]+"),
            "ISTITUTO TECNICO COMMERCIALE E PER GEOMETRI".split("[ ]+"),
            "UFFICIO SCOLASTICO REGIONALE PER IL LAZIO".split("[ ]+"),
            "MINISTERO ISTRUZIONE UNIVERSIT RICERCA".split("[ ]+"),
            "WEB www.fermitivoli.gov.it EMAIL rmtd07000g@istruzione.it PEC rmtd07000g@pec.istruzione.it".split("[ ]+"),
            "C.F. 86000020585  Cod. Ist RMTD07000G Distretto scol. 34".split("[ ]+"),
            "Via Acquaregna, 112 00019 TIVOLI Tel. 06/121126986 06/121126985 Fax 0774/334373".split("[ ]+")
    ));

    public static String normalizeTextFromHtml(String testo) {
        //if (true)return testo;

        return normalize_UTF8__to__ASCII(testo);
    }

    public static String normalize_UTF8__to__ASCII(String c) {
        if (c == null) return null;
        StringBuilder sb = new StringBuilder(c.length());
        for (int i = 0; i < c.length(); i++) {
            sb.append(normalize_UTF8__to__ASCII(c.charAt(i)));
        }
        return sb.toString();
    }

    private static boolean isEmpty(String s) {
        return s.trim().length() == 0;
    }

    public static String normalize_forTextToSpeech(String s) {
        //normalizza i caratteri, inserisce e riscrive abbreviazioni e simili e sistema gli a-capo
        String x = normalize_UTF8__to__ASCII(s);
        x = normalize_lineFeed(x);

        String[][] replace = new String[][]{
                {"A'", "à"},
                {"a'", "à"},
                {"e'", "é"},
                {"i'", "ì"},
                {"o'", "ò"},
                {"u'", "ù"},
                {"prof.[ ]*ssa'", "professoressa"},
                {"Prof.[ ]*ssa'", "professoressa"},
                {"n.'", "numero"},
        };
        for (int i = 0; i < replace.length; i++) {
            String from = replace[i][0];
            String to = replace[i][1];
            x = x.replaceAll(from, to);
        }
        return x;

    }

    public static String capitalize(String s) {
        StringBuilder sb = new StringBuilder();

        final String[] split = s.split("[ ]+");
        for (String w : split) {
            if (w.length() > 1) {
                sb.append(("" + w.charAt(0)).toUpperCase());
                sb.append(("" + w.substring(1)).toLowerCase()).append(" ");
            } else {
                sb.append(w.toLowerCase()).append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static String normalize_UTF8__to__ASCII(char c) {

        switch (c) {
            case 61482:
                return "F";
            //punto elenco
            case 61623:
                return "*";
            case 8217:
                return "\'";
            case 8221:
            case 8220:
                return "\"";
            case 8211:
                return "-";
            case 192:
                return "A'";
            case 249:
                return "u'";
            case 224:
                return "a'";
            case 236:
                return "i'";
            case 232:
                return "e'";
            case 176:
                return "'";
            //punto elenco
            case 183:
                return "*";
            //punti elenco
            case 61656:
            case 61607:
                return "*";
            case 200:
                return "E'";
            case 233:
                return "e'";
            case 242:
                return "o'";
            case 8364:
                return "euro";

            case 'ê':
            case 'ë':
                return "e'";
            case 'û':
                return "u'";
            case 'ï':
            case 'î':
                return "i'";
            case 'â':
                return "a'";
            case 160://à
                return " ";
            case 'Ô':
                return "o'";

            case 'É':
            case 'Ê':
            case 'Ë':
                return "E'";
            case 'Û':
            case 'Ù':
                return ("U'");
            case 'Ï':
            case 'Î':
                return ("I'");
            case 'Â':
            case 'Ã':
                return ("A'");


            default:
                if (c > 255)
                    return "#" + (int) c + "#";
                return "" + c;
        }
    }

    private static boolean endsWithPoint(String s) {
        return s.endsWith(".") || s.endsWith(":") || s.endsWith(";");
    }

    private static boolean startWithUppercaseOrNumber(String s) {
        if (s.length() <= 0) return false;

        return Character.isUpperCase(s.charAt(0)) && !Character.isUpperCase(s.charAt(1)) || Character.isDigit(s.charAt(0));
    }

    private static boolean containsAll(String s, String[] values) {
        for (String value : values) {
            if (!s.contains(value)) return false;
        }
        return true;
    }

    public static String currency(double d) {
        return new Formatter().format("%.02f €", d).toString();
    }

    private static boolean startWithLowercase(String s) {
        return s.length() > 0 && Character.isLowerCase(s.charAt(0));
    }


    /**
     * normalizza un testo eliminando gli \n non necessari in funzione dellle maiuscole
     *
     * @param text
     * @return todo: gestire linee a capo circolari
     */
    public static String normalizeTextAndLineFeed_forTextCircolari(String text, boolean skipStopLines) {
        //righe reali (con almeno 2 \n)
        final String[] split = text.split("[\n][\n \t]*[\n]");


        StringBuilder sb = new StringBuilder(text.length());

        for (String multiline : split) {
            final String[] line = multiline.split("[\n]");
            for (String l : line) {

                String trim = l.trim();
                //controlla se cancellare
                if (skipStopLines) {
                    boolean skip = false;
                    for (String[] s : stopWordsCircolai) {
                        if (containsAll(trim, s)) {
                            skip = true;
                            break;
                        }
                    }
                    if (skip) continue;
                }
                if (((sb.length() == 0) || sb.charAt(sb.length() - 1) == '\n')) {
                    sb.append(l);
                } else if (startWithLowercase(trim)) {
                    sb.append(" ").append(l);
                } else {
                    sb.append("\n").append(l);
                }
            }

            sb.append("\n");
        }

        return sb.toString().trim(); //C_TextUtil.normalize_lineFeed(sb.toString());
    }


    /**
     * normalizza un testo eliminando gli \n non necessari in funzione dellle maiuscole
     *
     * @param text
     * @return
     */
    @Deprecated
    public static String normalize_lineFeed(String text) {
        text = text.trim();
        final String[] split = text.split("[\n]");
        StringBuilder sb = new StringBuilder(text.length());
        String prevTrim = "";
        for (String line : split) {
            String trim = line.trim();
            //controlla se cancellare
            if (trim.length() == 0) {
                if (prevTrim.length() > 0)
                    sb.append(".\n");
                else
                    sb.append("\n");
                prevTrim = trim;
                continue;
            }

            if (sb.length() == 0) {
                sb.append(line);
                continue;
            }
            boolean b = endsWithPoint(prevTrim);

            if (startWithUppercaseOrNumber(trim) || b) {

                if (!b) {
                    if (prevTrim.length() > 0)
                        sb.append(".\n");
                    else
                        sb.append("\n");
                } else
                    sb.append("\n");
                sb.append(line);
            } else {
                sb.append(" ");
                sb.append(line);
            }
            prevTrim = trim;

        }

        return sb.toString();
    }

    public static String extractContentFromHTML(String s) {
        return Jsoup.parse(s).text();
    }

    public static void main(String[] args) {
        String testo = "Storia e geografia";
        System.out.println(capitalize(testo));
    }


}
