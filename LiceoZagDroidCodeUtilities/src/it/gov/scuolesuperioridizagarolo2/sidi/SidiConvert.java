package it.gov.scuolesuperioridizagarolo2.sidi;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.TreeMap;

/**
 * Created by stefano on 14/11/2018.
 */
public class SidiConvert {
    public static String[] convert(String[] s, int step) {

        final TreeMap<String, String> mapSidi = CorrispondenzeSidi.map();


        String[] ris = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            if (i % step == 0 && s[i].trim().length() > 0) {
                final String infoStudente = mapSidi.get(s[i]);
                if (infoStudente == null) {
                    System.err.println("ERRORE CODICE SIDI NON TROVATO: " + s[i]);
                } else {
                    ris[i] = s[i] + " => " + infoStudente;
                }
            } else {
                ris[i] = "";
            }
        }
        return ris;
    }


    public static void doTask(String s, int step) {
        final String[] convert = convert(s.split("\n"), step);
        StringBuilder sb = new StringBuilder();
        for (String s1 : convert) {
            System.out.println(s1);
            sb.append(s1).append("\n");
        }

        StringSelection selection = new StringSelection(sb.toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
