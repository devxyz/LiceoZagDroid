package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2019_20;

public class Prova {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7};
        for (int i = 0; i < a.length; i++) {
            int a1 = a[i];
            for (int i1 = i + 1; i1 < a.length; i1++) {
                int a2 = a[i1];
                for (int i2 = i1 + 1; i2 < a.length; i2++) {
                    int a3 = a[i2];
                    System.out.println(a1 + "-" + a2 + "-" + a3);
                }
            }
        }
    }
}
