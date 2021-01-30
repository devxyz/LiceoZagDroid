package it.gov.scuolesuperioridizagarolo2.backoffice.utilities.test;

import java.util.Arrays;

public class MainParole {
    public static void main(String[] args) {
        test("oggi-non-piove----mai");

        test("candele-marea-conchiglia--candele-candela");

        test("non-so-trovare-il-trovatore");
        test("papaveri-grano-papaveri--grano-zio--aiuola");
    }

    public static void test(String s) {
        System.out.println();
        String[] split = s.split("[-]+");
        Arrays.sort(split);
        System.out.println(s);
        System.out.println(Arrays.toString(split));
    }
}
