package it.gov.scuolesuperioridizagarolo2.supporto_compiti._3_dicembre;

public class Esercizio2D {
    public static void main(String[] args) {
        int P = 3;
        System.out.println("P:" + P);

        boolean trovato = false;
        for (int i = 1; i <= P; i++) {
            for (int j = 1; j <= P; j++) {
                if (i + j <= P) {
                    System.out.print("(" + i + "," + j + ") ");
                    trovato = true;
                }
            }
        }

        if (!trovato) {
            System.out.println("NON TROVATO");
        }
    }
}
