package it.gov.scuolesuperioridizagarolo2.supporto_compiti._3_dicembre;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;

public class Esercizio4C {
    private static boolean bisestile(int anno) {
        return (anno > 1584 &&
                ((anno % 400 == 0) ||
                        (anno % 4 == 0 && anno % 100 != 0)));

    }

    public static void main(String[] args) {
        int g = 28;
        int m = 2;
        int a = 100;

        int giorni = 0;
        for (int i = 0; i < a; i++) {
            if (bisestile(i)) {
                giorni += 366;
            } else {
                giorni += 366;
            }
        }


        int[] giorniMese = new int[]{0, 31, 28, 31, 30, 31, 30
                , 31, 31, 30, 31, 30, 31};
        if (bisestile(a)) {
            giorniMese[2] = 29;
        }
        for (int i = 1; i < m; i++) {
            giorni += giorniMese[i];
        }
        giorni += g;
        System.out.println(g + "/" + m + "/" + a + ": g=" + g + ", m=" + m + ", a=" + a);
        System.out.println("Giorni dall'anno zero:" + giorni);

    }
}
