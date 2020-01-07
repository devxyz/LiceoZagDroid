package it.gov.scuolesuperioridizagarolo2.supporto_compiti._3_dicembre;

public class Esercizio2B {
    public static void main(String[] args) {
        int i1 = 120;
        int i2 = 234;

        int s = i1 + i2;
        int p = i1 * i2;
        System.out.println("SOMMA:" + s + ", PRODOTTO:" + p);

        boolean trovato = false;
        for (int i = 1; i <= s; i++) {
            for (int j = 1; j <= s; j++) {
                if (i * j == p && j + i == s) {
                    System.out.print("(" + i + "," + j + ") ");
                    trovato = true;

                }

                if (trovato) break;
            }
            if (trovato) break;
        }

        if (!trovato) {
            System.out.println("NON TROVATO");
        }
    }
}
