package it.gov.scuolesuperioridizagarolo.dada.bitorario;

import java.util.ArrayList;

/**
 * Created by stefano on 12/02/2018.
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<String> ss = new ArrayList<>();
        for (char c = 'a', c2 = 'z'; c <= c2; c++, c2--) {
            String s = "";

            for (int x = 1; x <= c - 'a'; x++)
                s += ("  ");

            for (int i = 1; i <= c2 - c; i++) {
                s += ("" + c + c2);
            }
            ss.add(s);
        }


        for (String s : ss) {
            System.out.println(s);
        }
        for (int i = ss.size() - 1; i >= 0; i--) {
            String s = ss.get(i);
            System.out.println(s);
        }
    }
}
