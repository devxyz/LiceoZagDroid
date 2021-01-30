package it.gov.scuolesuperioridizagarolo.dada.bitorario.engine2;

public class TestStack {
    static int count=0;
    public static void main(String[] args) {
        test();

    }

    static void test(){
        int a,b,c;
        count++;
        System.out.println(count);
        test();
    }
}
