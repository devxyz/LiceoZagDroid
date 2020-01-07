package it.gov.scuolesuperioridizagarolo2.supporto_compiti._3_dicembre;

public class Eserciizio4B {

    public static boolean primo(int n) {
        if (n == 2) // 2 e' primo
            return true;

        if (n % 2 == 0) // i numeri pari non sono primi (il caso n = 2 e' stato gia' trattato)
            return false;

        int divisore = 3;
        while (divisore <= Math.sqrt(n)) {
            if (n % divisore == 0)
                return false; // ha trovato un divisore
            divisore += 2; // continuo solo con i divisori dispari
        }
        return true; // solo se il ciclo arriva fino alla fine, quindi non ha trovato
        // alcun divisore
    }

    public static void main(String[] args) {
        int n = 13;
        int n1 = n;


        System.out.println(n);
        for (int i = 2; i <= n; i++) {
            if (primo(i)) {
                int conta = 0;
                while (n % i == 0) {
                    conta++;
                    n = n / i;
                }
                if (conta > 0) {
                    System.out.print(" " + i + "^" + conta);
                }
            }
        }

    }
}
