package it.gov.scuolesuperioridizagarolo2.supporto_compiti._3_dicembre;

import java.util.Random;

public class Esercizio3C {
    public static void main(String[] args) {
        int n = 3;
        int somma = 0;
        int conta = 0;
        System.out.println("N=" + n);
        Random r = new Random();
        int i;
        do {
            conta++;
            i = r.nextInt(n + 5);
            if (i <= n)
                somma += i;
            System.out.print(i + " ");
        }
        while (i <= n);
        System.out.println();
        System.out.println("SOMMA=" + somma);
        System.out.println("NUM VAL=" + conta);
        System.out.println("MEDIA=" + (somma * 1.0 / conta));
    }
}
