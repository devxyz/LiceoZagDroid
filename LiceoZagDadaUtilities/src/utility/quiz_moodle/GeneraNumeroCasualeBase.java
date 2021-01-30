package utility.quiz_moodle;

import java.util.Random;

public class GeneraNumeroCasualeBase {
    static char[] cifre(int base) {
        char[] ris = new char[base];
        int i = 0;
        char v = '0';
        while (i < base && v <= '9') {
            ris[i] = v;
            i++;
            v++;
        }

        v = 'A';
        while (i < base && v <= 'Z') {
            ris[i] = v;
            i++;
            v++;
        }
        return ris;
    }

    public static String generaNumero(Random r, int base, int num_cifre) {
        char[] cifre = cifre(base);
        StringBuilder sb = new StringBuilder(num_cifre);
        int i = r.nextInt(cifre.length - 1) + 1;
        sb.append(cifre[i]);
        while (sb.length() < num_cifre) {
            i = r.nextInt(cifre.length);
            sb.append(cifre[i]);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Random r = new Random(0);


        System.out.println(generaNumero(r, 16, 6));
    }
}
