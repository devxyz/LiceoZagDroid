package utility;

public class AsciiBin {
    public static void main(String[] args) {
        String parola = "scuola";
        for (int i = 0; i < parola.length(); i++) {
            char c = parola.charAt(i);
            System.out.print("0" + Integer.toBinaryString(c));
        }
    }
}
