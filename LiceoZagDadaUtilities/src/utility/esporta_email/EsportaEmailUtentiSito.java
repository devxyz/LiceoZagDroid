package utility.esporta_email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EsportaEmailUtentiSito {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = EsportaEmailUtentiSito.class.getResourceAsStream("utenti.xml");
        BufferedReader in = new BufferedReader(new InputStreamReader(resourceAsStream));
        String s;
        while ((s = in.readLine()) != null) {
            s = s.trim();
            if (s.startsWith("<name>")) {
                String nome = normalizza1(s);
                System.out.print(nome);
                System.out.print("\t");
            }
            if (s.startsWith("<email>")) {
                String nome = normalizza2(s);
                System.out.print(nome);
                System.out.print("\n");
            }
        }
        in.close();
    }

    private static String normalizza1(String s) {
        return s.replace("<name>", "").replace("</name>", "").replace("]]>", "").replace("<![CDATA[", "");
    }
    private static String normalizza2(String s) {
        return s.replace("<email>", "").replace("</email>", "").replace("]]>", "").replace("<![CDATA[", "");
    }
}
