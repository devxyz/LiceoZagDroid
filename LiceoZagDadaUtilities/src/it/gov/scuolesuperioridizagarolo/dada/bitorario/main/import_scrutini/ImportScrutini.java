package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.import_scrutini;

import java.io.*;
import java.util.ArrayList;

@Deprecated
public class ImportScrutini {
    public static class Studente {
        final String nome;
        final String classe;
        final ArrayList<Integer> voti = new ArrayList<>();

        public int classe() {
            return Integer.parseInt(classe.charAt(0) + "");
        }

        public String sezione() {
            return (classe.charAt(1) + "");
        }

        public Studente(String nome, String classe) {
            this.nome = nome;
            this.classe = classe;
        }

        public double media() {
            double s = 0;
            for (Integer x : voti) {
                s += x;
            }

            return voti.size() == 0 ? 0 : s / voti.size();
        }

        public int insuff() {
            int n = 0;
            for (Integer x : voti) {
                n += x >= 6 ? 0 : 1;
            }
            return n;
        }
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/stefano/Dropbox/DROPBOX LICEO/elenco-scrutini.txt");
        File file2 = new File("/Users/stefano/Dropbox/DROPBOX LICEO/elenco-scrutini.csv");
        BufferedReader in = new BufferedReader(new FileReader(file));
        BufferedWriter out = new BufferedWriter(new FileWriter(file2));
        String classe = "";
        ArrayList<Studente> ss = new ArrayList<>();
        out.write("nome;classe;classe_successiva;insuff;critico;media;voti\n");
        String linea;
        while ((linea = in.readLine()) != null) {
            if (linea.startsWith("-")) {
                classe = in.readLine().trim().replace("\t", "").toUpperCase();
                // System.out.println("Classe: " + classe);
                continue;
            }

            String[] elementi = linea.split("[\t]+");
            String nome = elementi[0].trim().toUpperCase();
            //System.out.println(Arrays.toString(elementi));
            Studente s = new Studente(nome, classe);
            ss.add(s);

            // System.out.print(" >>> ");
            for (int i = 1; i < elementi.length - 3; i++) {
                if (isNumeric(elementi[i])) {
                    s.voti.add(Integer.parseInt(elementi[i]));
                    //System.out.print(" " + elementi[i]);
                }
            }

            //skip linea assenze
            linea = in.readLine();
            if (!linea.startsWith("A")) throw new IllegalArgumentException("Invalid data " + linea);

            out.write(s.nome);
            out.write(";");
            out.write(s.classe);
            out.write(";");
            boolean bocciatura = s.insuff() >= 4 && s.media() < 5.5;
            if (bocciatura)
                out.write((s.classe()) + "" + s.sezione());
            else
                out.write((s.classe() + 1) + "" + s.sezione());
            out.write(";");
            out.write("" + s.insuff());
            out.write(";");
            out.write("" + (bocciatura ? "si" : "no"));
            out.write(";");
            out.write(("" + s.media()).replace(".", ","));

            for (Integer x : s.voti) {
                out.write(";");
                out.write("" + x);
            }
            out.write("\n");
        }
        in.close();
        out.close();

    }

}
