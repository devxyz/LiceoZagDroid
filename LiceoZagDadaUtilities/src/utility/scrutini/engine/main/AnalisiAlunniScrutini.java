package utility.scrutini.engine.main;

import utility.scrutini.engine.AnalizzaFileScrutiniAndCarenze;
import utility.scrutini.engine.data.DatiStudente;

import java.io.*;
import java.util.ArrayList;

public class AnalisiAlunniScrutini {
    public static void main(String[] args) throws IOException {
        File root = new File("/Users/stefano/Downloads");
        File[] tt = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(".pdf") && pathname.getName().toLowerCase().contains("tabello");
            }
        });
        ArrayList<DatiStudente> stud = new ArrayList<>();

        for (File f : tt) {
            stud.addAll(AnalizzaFileScrutiniAndCarenze.generaReport(f, null));
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(new File(root, "scrutini_alunni.csv")));
        out.write("nome;classe;esito;anno_superato;classe_successiva\n");
        for (DatiStudente x : stud) {
            String nome = x.getNome();
            String classe = x.getClasseNumero() + "" + x.getClasseSezione();
            String esito = x.getEsito();
            boolean anno_superato = x.getEsitoEnum().isAnnoSuperato();
            String classe_successiva = anno_superato ? (x.getClasseNumero() + 1) + "" + x.getClasseSezione() : classe;
            out.write(nome + ";" + classe + ";" + esito + ";" + anno_superato + ";" + classe_successiva + "\n");
        }
        out.close();


    }
}
