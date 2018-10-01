package it.gov.scuolesuperioridizagarolo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by stefano on 22/08/2018.
 */
public class LoginUtil {
    public static boolean verifyQRCODEDocente(String codiceEncode64) {
        final byte[] decode = C_Base64.decode(codiceEncode64);
        if (decode == null) return false;
        String dec = new String(decode);
        System.out.println(dec);
        final String[] split = dec.split("#");
        if (split.length != 4) return false;
        System.out.println(Arrays.toString(split));
        if (!split[1].equals("user:docente")) return false;
        if (!split[3].startsWith("now:")) return false;
        final String split1 = split[3].substring(4);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-SS");
        final Date parse;
        try {
            parse = df.parse(split1);
        } catch (ParseException e) {
            return false;
        }

        System.out.println(parse);

        final long tempoTrascorso = System.currentTimeMillis() - parse.getTime();
        System.out.println("Tempo trascorso ms:" + tempoTrascorso);
        return !(tempoTrascorso < 0 || tempoTrascorso > 70 * 60 * 1000);

    }


    public static void main(String[] args) {
        String prova = "I3VzZXI6ZG9jZW50ZSNyYW5kOjYxNTA5I25vdzoyMDE4LTA4LTIyLTIwLTI1LTE1";
        System.out.println(verifyQRCODEDocente(prova));
    }
}
