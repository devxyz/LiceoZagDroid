package utility.esporta_utenti_sitoweb;

import java.io.*;
import java.util.Random;

public class EsportaEmailUtentiSito {
    public static String password(Random r, int size) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < size) {
            sb.append("" + r.nextInt(10));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/utility/esporta_utenti_sitoweb");
        BufferedReader in = new BufferedReader(new FileReader(new File(root, "j2xml1590020200813131755.xml")));
        String s;
        Random r = new Random(0);
        while ((s = in.readLine()) != null) {
            s = s.trim();
            if (s.startsWith("<name>")) {
                String nomecognome = normalizza1(s).trim().replace(" De ", " De_").replace(" Di ", " Di_").replace(" Del ", " Del_");

                int beginIndex = nomecognome.lastIndexOf(" ");
                String cognome = nomecognome.substring(beginIndex, nomecognome.length()).replace("_", " ").trim();
                String nome = nomecognome.substring(0, beginIndex).replace("_", " ").trim();
                String emailGoogle = nome.replace(" ", "").replace("'", "").toLowerCase() + "." + cognome.replace(" ", "").replace("'", "").toLowerCase() + "@scuolesuperioridizagarolo.edu.it";
                String password = "Pass" + password(r, 8);
                String intestazione = "First Name [Required]\tLast Name [Required]\tEmail Address [Required]\tPassword [Required]\tPassword Hash Function [UPLOAD ONLY]\tOrg Unit Path [Required]\tNew Primary Email [UPLOAD ONLY]\tRecovery Email\tHome Secondary Email\tWork Secondary Email\tRecovery Phone [MUST BE IN THE E.164 FORMAT]\tWork Phone\tHome Phone\tMobile Phone\tWork Address\tHome Address\tEmployee ID\tEmployee Type\tEmployee Title\tManager Email\tDepartment\tCost Center\tBuilding ID\tFloor Name\tFloor Section\tChange Password at Next Sign-In\tNew Status [UPLOAD ONLY]";
                System.out.print(nome);
                System.out.print("\t");
                System.out.print(cognome);
                System.out.print("\t");
                System.out.print(emailGoogle);
                System.out.print("\t");
                System.out.print(password);
                System.out.print("\t");
            }
            if (s.startsWith("<email>")) {
                String emailPersonale = normalizza2(s);
                System.out.print("");
                System.out.print("\t");
                System.out.print("/docenti");
                System.out.print("");
                System.out.print("\t");
                System.out.print(emailPersonale);
                System.out.print("\n");
            }
        }
        in.close();
    }

    private static String normalizza1(String s) {
        return s.replace("<name>", "").replace("</name>", "").replace("]]>", "").replace("<![CDATA[", "");
    }

    private static String normalizza2(String s) {
        return s.replace("<email>", "").replace("</email>", "").replace("]]>", "").replace("<![CDATA[", "");
    }
}
