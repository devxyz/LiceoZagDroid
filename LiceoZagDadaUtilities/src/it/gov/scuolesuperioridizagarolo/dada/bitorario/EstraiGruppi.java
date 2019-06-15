package it.gov.scuolesuperioridizagarolo.dada.bitorario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

//estrae coppie di numeri a partire dai valori specificati
public class EstraiGruppi {
    public static void main(String[] args) {
        ArrayList<Integer> valori = new ArrayList<>();
        valori.addAll(Arrays.asList(1, 3, 4, 5, 6));
        System.out.println("Valori " + valori);

        Collections.shuffle(valori, new Random(1000));
        for (int i = 0; i < valori.size(); i++) {
            System.out.print(" "+valori.get(i));
            if (i%2!=0) {
                System.out.println();
                System.out.println("==============");
            }
        }
        System.out.println();
    }
}
