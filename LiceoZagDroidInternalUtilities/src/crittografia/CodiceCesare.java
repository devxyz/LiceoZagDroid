package crittografia;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class CodiceCesare {
    static char successivo(char c, char[] alfabeto) {
        for (int i = 0; i < alfabeto.length; i++) {
            char c1 = alfabeto[i];
            if (c == c1) {
                if (i < alfabeto.length - 1) {
                    return alfabeto[i + 1];
                } else {
                    return alfabeto[0];
                }
            }
        }
        return c;
    }

    static char precedente(char c, char[] alfabeto) {
        for (int i = 0; i < alfabeto.length; i++) {
            char c1 = alfabeto[i];
            if (c == c1) {
                if (i > 0) {
                    return alfabeto[i - 1];
                } else {
                    return alfabeto[alfabeto.length - 1];
                }
            }
        }
        return c;
    }

    static char successivo(char c, int n, char[] alfabeto) {
        char cc = c;
        for (int i = 1; i <= n; i++) {
            cc = successivo(cc, alfabeto);
        }
        return cc;
    }

    static char precedente(char c, int n, char[] alfabeto) {
        char cc = c;
        for (int i = 1; i <= n; i++) {
            cc = precedente(cc, alfabeto);
        }
        return cc;
    }

    private static String codifica(String s, int k, String alfabeto) {
        String ris = "";
        char[] alfabetoArray = alfabeto.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            ris += successivo(s.charAt(i), k, alfabetoArray);
        }
        return ris;
    }

    private static String decodifica(String s, int k, String alfabeto) {
        String ris = "";
        char[] alfabetoArray = alfabeto.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            ris += precedente(s.charAt(i), k, alfabetoArray);
        }
        return ris;
    }

    public static void main(String[] args) {
        main_decodifica();
    }

    public static void main_codifica() {
        String alfabeto = "";
        for (char i = 'A'; i <= 'Z'; i++) {
            alfabeto += i;
        }
        alfabeto += "_;,:.!?0123456789";

        Random r = new Random(0);

        String frasi[] = new String[]{
                "Pippo!qui",
                "a;e;i;o;u",
                "anguria!",
                "giochi?si",
                "aiuto!!!!",
                "zanz!bar",
                "Paper1n0",
                "Fonzies!",
                "1nf0rmat1",
                "Casa!Cose",
                "DES_3DES!",
        };

        ByteArrayOutputStream buffer;
        for (String frase : frasi) {
            buffer = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(buffer);

            frase = frase.replace(" ", "").toUpperCase();
            int k = r.nextInt(4) + 4;
            System.out.println("=================================================");
            out.println("Utilizzando il Codice di Cesare con chiave k=" + k + " codificare la parola <b style='font-size:20px'>[" + frase + "]</b>.\n" +
                    "<br>Utilizzare l'alfabeto esteso riportato di seguito, che permette anche la scrittura di simboli di punteggiatura e cifre numeriche<br>");
            out.println("UTILIZZARE ESCLUSIVAMENTE LETTERE MAIUSCOLE, PUNTEGGIATURA E CIFRE NUMERICHE PER SCRIVERE LA RISPOSTA<hr>");
            out.println("<br>Alfabeto utilizzato: <b style='font-size:20px;font-family:monospace'>" + alfabeto + "</b>");
            out.println("<br>Chiave: <b style='font-size:20px;font-family:monospace'>" + k + "</b>");
            //out.println("ALFABETO SPOSTATO:[" + codifica(alfabeto, k, alfabeto) + "]");
            out.println("<br>Parola da criptare:\n <b style='font-size:20px;font-family:monospace'>" + frase + "</b>");
            out.println();
            String codifica = codifica(frase, k, alfabeto);
            out.println("<br><br>RISPOSTA:\n " + codifica + "");
            System.out.println(buffer.toString());
            StringSelection selection = new StringSelection(buffer.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            JOptionPane.showMessageDialog(null, "VADO AVANTI?");
            out.close();
        }
    }

    public static void main_decodifica() {
        String alfabeto = "";
        for (char i = 'A'; i <= 'Z'; i++) {
            alfabeto += i;
        }
        alfabeto += "_;,:.!?0123456789";

        Random r = new Random(0);

        String frasi[] = new String[]{
                "Pippo!qui",
                "a;e;i;o;u",
                "anguria!",
                "giochi?si",
                "aiuto!!!!",
                "zanz!bar",
                "Paper1n0",
                "Fonzies!",
                "1nf0rmat1",
                "Casa!Cose",
                "DES_3DES!",
        };

        ByteArrayOutputStream buffer;
        for (String frase : frasi) {
            buffer = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(buffer);

            frase = frase.replace(" ", "").toUpperCase();
            int k = r.nextInt(4) + 4;
            String codifica = codifica(frase, k, alfabeto);
            System.out.println("=================================================");
            out.println("Utilizzando il Codice di Cesare con chiave k=" + k + " decodificare la stringa <b style='font-size:20px'>[" + codifica + "]</b>.\n" +
                    "<br>Utilizzare l'alfabeto esteso riportato di seguito, che permette anche la scrittura di simboli di punteggiatura e cifre numeriche<br>");
            out.println("UTILIZZARE ESCLUSIVAMENTE LETTERE MAIUSCOLE, PUNTEGGIATURA E CIFRE NUMERICHE PER SCRIVERE LA RISPOSTA<hr>");
            out.println("<br>Alfabeto utilizzato: <b style='font-size:20px;font-family:monospace'>" + alfabeto + "</b>");
            out.println("<br>Chiave: <b style='font-size:20px;font-family:monospace'>" + k + "</b>");
            //out.println("ALFABETO SPOSTATO:[" + codifica(alfabeto, k, alfabeto) + "]");
            out.println("<br>Parola da decriptare:\n <b style='font-size:20px;font-family:monospace'>" + codifica + "</b>");
            out.println();

            out.println("<br><br>" + frase + "");
            System.out.println(buffer.toString());
            StringSelection selection = new StringSelection(buffer.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            JOptionPane.showMessageDialog(null, "VADO AVANTI?");
            out.close();
        }
    }
}
