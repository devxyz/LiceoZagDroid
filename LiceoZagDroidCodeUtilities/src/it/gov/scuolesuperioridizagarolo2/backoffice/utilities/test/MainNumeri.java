package it.gov.scuolesuperioridizagarolo2.backoffice.utilities.test;

import java.util.*;

public class MainNumeri {
    public static void main(String[] args) {
        test("1-2-3-4-5-6-7-8-9-10");

        test("9-8-7-6-5-4");

        test("1-9--2-8-3-7");
        test("2-2-5-5-2-2--4-2-4-4-4");
    }

    public static void test(String s) {
        System.out.println();
        String[] split = s.split("[-]+");
        ArrayList<Integer> ss = new ArrayList<>();

        for (String s1 : split) {
            ss.add(Integer.parseInt(s1));
        }
        Collections.sort(ss);
        System.out.println(s);
        System.out.println(ss);
    }
}
