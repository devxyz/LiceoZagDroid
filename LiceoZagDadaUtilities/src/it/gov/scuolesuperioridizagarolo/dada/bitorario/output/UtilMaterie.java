package it.gov.scuolesuperioridizagarolo.dada.bitorario.output;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;

public class UtilMaterie {
    public static String abbreviazioneMateria(BitOrarioOraLezione ll) {

        final String materiaPrincipale = ll.getMateriaPrincipale();

        return abbreviazioneMateria(materiaPrincipale);
    }

    public static String abbreviazioneMateria(String mat) {
        String[] split = mat.split("[ ]+");
        String ris = "";
        for (String materiaPrincipale : split) {

            int min = materiaPrincipale.contains(".") ? Math.min(materiaPrincipale.length(), 6) : Math.min(materiaPrincipale.length(), 3);
            while (min > 3 && !isConsanant(materiaPrincipale.charAt(min - 1))) {
                min--;
            }
            ris = ris + " " + materiaPrincipale.substring(0, min);
        }
        return ris.trim();
    }

    public static boolean isConsanant(char c) {
        String cons = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        return cons.contains("" + c);
    }


}
