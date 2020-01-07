package it.gov.scuolesuperioridizagarolo2.supporto_compiti._3_dicembre;

import java.util.Random;

public class Esercizio3B {
    public static void main(String[] args) {
        int s = 31;
        int somma = 0;
        int conta = 0;
        System.out.println("S="+s);
        Random r = new Random();
        do {
            conta++;
            int i = r.nextInt(20);
            somma += i;
            System.out.print(i + " ");
        }
        while (somma < s);
        System.out.println();
        System.out.println("SOMMA=" + somma);
        System.out.println("NUM VAL=" + conta);
    }
}
