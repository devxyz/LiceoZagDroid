package crittografia;

public class CodiceAlberti {
    private static String ruotaDestra(String s) {
        return s.charAt(s.length() - 1) + s.substring(0, s.length() - 1);
    }

    private static char decodifica(char carattereDiscoInterno, String discoEsterno, String discoInterno) {
        int i = discoInterno.indexOf(carattereDiscoInterno);
        if (i < 0)
            throw new IllegalArgumentException("Carattere " + carattereDiscoInterno + " non trovato in " + discoInterno);
        return discoEsterno.charAt(i);
    }

    private static String allinea(char carattereDiscoEsterno, char chiave, String discoEsterno, String discoInterno) {
        int i = discoEsterno.indexOf(carattereDiscoEsterno);
        if (i < 0)
            throw new IllegalArgumentException("Carattere " + carattereDiscoEsterno + " non trovato in " + discoEsterno);
        while (discoInterno.charAt(i) != chiave) {
            discoInterno = ruotaDestra(discoInterno);
        }
        return discoInterno;
    }

    public static void main(String[] args) {
        String discoEsterno = "ABCDEFGILMNOPQRSTVXZ1234";
        String discoInterno = "okbpfzsemdgutairlhnxyqc&";
        char chiave = 'a';

        System.out.println(ruotaDestra(discoEsterno));

        String codice = "AradpEnlsLrtl";
        String risultato = "";
        for (int i = 0; i < codice.length(); i++) {
            char c = codice.charAt(i);
            System.out.print("Carattere " + c+" - ");
            if (Character.isUpperCase(c)) {
                discoInterno = allinea(c, chiave, discoEsterno, discoInterno);
                System.out.println("Allinea con carattere:");
                System.out.println(" ESTERNO:" + discoEsterno);
                System.out.println(" INTERNO:" + discoInterno);
            } else {
                char decodifica = decodifica(c, discoEsterno, discoInterno);
                System.out.println(" Decodifica:" + decodifica);
                risultato += decodifica;
            }
        }

        System.out.println("RISULTATO FINALE:" + risultato);

    }
}
