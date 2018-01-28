package it.gov.scuolesuperioridizagarolo2.test;

/**
 * Created by stefano on 24/01/2018.
 */
public class Test2 {
    public static void main2(String[] args) {
        int n=('z'-'a'+1)*2;
        int n1=('z'-'a'+1)*2;
        for (char c='z';c>='a';c--){

            for (int i=1;i<=(n1-n)/2;i++){
                System.out.print(" ");
            }
            for (int i=1;i<=n;i++){
                System.out.print(c);
            }
            System.out.println();
            n--;
            n--;
        }
    }
    public static void main(String[] args) {
        int n=1;
        int n1=('Z'-'A'+1);
        for (char c='A';c<='Z';c++){

            for (int i=1;i<=(n1-n);i++){
                System.out.print(" ");
            }
            for (int i=1;i<=n;i++){
                System.out.print(c);
            }
            System.out.println();
            n++;
        }
    }
}
