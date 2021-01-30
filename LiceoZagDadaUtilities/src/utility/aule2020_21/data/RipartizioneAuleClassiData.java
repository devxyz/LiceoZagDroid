package utility.aule2020_21.data;

import utility.aule2020_21.Aula202021;
import utility.aule2020_21.Classe202021;

import java.util.*;

public abstract class RipartizioneAuleClassiData {
    private static Class<?> selection = null;


    public static <L extends RipartizioneAuleClassiData> void selectDataSource(Class<L> c) {
        if (selection == null) {
            RipartizioneAuleClassiData.selection = c;
        } else {
            throw new IllegalArgumentException("Classe dati già impostata: " + selection);
        }
    }

    public static RipartizioneAuleClassiData singleton() {
        if (selection == null) {
            throw new IllegalArgumentException("Sorgente dati non impostata");
        }
        if (selection == RipartizioneAuleClassiData_01_7prime_conAulaExtra.class) {
            return new RipartizioneAuleClassiData_01_7prime_conAulaExtra();
        } else if (selection == RipartizioneAuleClassiData_02_6prime_senzaAulaExtra.class) {
            return new RipartizioneAuleClassiData_02_6prime_senzaAulaExtra();
        } else if (selection == RipartizioneAuleClassiData_03_7prime_senzaAulaExtra.class) {
            return new RipartizioneAuleClassiData_03_7prime_senzaAulaExtra();
        } else if (selection == RipartizioneAuleClassiData_04_7primecompattate_senzaAulaExtra.class) {
            return new RipartizioneAuleClassiData_04_7primecompattate_senzaAulaExtra();
        } else if (selection == RipartizioneAuleClassiData_05_6prime_con_palestra.class) {
            return new RipartizioneAuleClassiData_05_6prime_con_palestra();
        } else if (selection == RipartizioneAuleClassiData_06_7prime_con_palestra.class) {
            return new RipartizioneAuleClassiData_06_7prime_con_palestra();
        } else if (selection == RipartizioneAuleClassiData_07_6prime_con_tutte_le_aule_disponibili.class) {
            return new RipartizioneAuleClassiData_07_6prime_con_tutte_le_aule_disponibili();
        } else if (selection == RipartizioneAuleClassiData_08_aule_definitive.class) {
            return new RipartizioneAuleClassiData_08_aule_definitive();
        } else throw new IllegalArgumentException("Invalid data");
    }


    abstract String getAuleImpl();

    abstract String getClassiImpl();

    public ArrayList<Classe202021> classi() {
        Map<String, Integer> m = toMap(getClassiImpl());
        ArrayList<Classe202021> ris = new ArrayList<>();
        for (Map.Entry<String, Integer> e : m.entrySet()) {
            ris.add(new Classe202021(e.getKey(), e.getValue()));
        }
        return ris;
    }

    public ArrayList<Classe202021> classi(String... classiEscluse) {
        Set<String> cc = new TreeSet<>(Arrays.asList(classiEscluse));


        Map<String, Integer> m = toMap(getClassiImpl());
        ArrayList<Classe202021> ris = new ArrayList<>();
        for (Map.Entry<String, Integer> e : m.entrySet()) {
            String classe = e.getKey();
            boolean skip = false;
            for (String s : classiEscluse) {
                if (classe.startsWith(s)) {
                    skip = true;
                }
            }
            if (skip) continue;

            ris.add(new Classe202021(classe, e.getValue()));
        }
        return ris;
    }

    public ArrayList<Aula202021> aule() {
        Map<String, Integer> m = toMap(getAuleImpl());
        ArrayList<Aula202021> ris = new ArrayList<>();
        for (Map.Entry<String, Integer> e : m.entrySet()) {
            ris.add(new Aula202021(e.getKey(), e.getValue()));
        }
        return ris;
    }


    private static Map<String, Integer> toMap(String s) {
        TreeMap<String, Integer> ris = new TreeMap<>();
        for (String row : s.split("\n")) {
            if (row.trim().length() == 0) continue;
            String[] split = row.split("\t");
            ris.put(split[1], Integer.parseInt(split[0]));
        }
        return ris;
    }

    public static void main(String[] args) {
        selectDataSource(RipartizioneAuleClassiData_01_7prime_conAulaExtra.class);
        ArrayList<Classe202021> classi = singleton().classi();
        for (Classe202021 x : classi) {
            System.out.println(x);
        }
        System.out.println();
        ArrayList<Aula202021> aule = singleton().aule();
        int count = 0;
        for (Aula202021 x : aule) {
            if (x.capienza > 0) {
                count++;
                System.out.println(x);
            }
        }
        System.out.println(count);
    }
}
