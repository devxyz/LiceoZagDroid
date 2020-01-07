package it.gov.scuolesuperioridizagarolo2.supporto_compiti._3_dicembre;

public class Esercizio1A {
    public static void main(String[] args) {
        int n = 10;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    System.out.print("("+i + "," + j + ") ");
                }
            }
        }
    }
}
