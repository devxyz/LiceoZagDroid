package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario75percento_gennaio2021;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.util.*;

class _02_STEP1_ReportSettimanale_GiornoLibero {
    //numero di giorni di lezione per classe alla settimana
    public final int numeroGiorniLezioneSettimanaliPerClasse;
    public final int numeroGiorniLezioneSettimanaliAperturaScuola;
    private final ArrayList<Set<ClassData>> classiNonInPresenza = new ArrayList<>();
    private final ArrayList<Set<ClassData>> classiNonInPresenzaExtra = new ArrayList<>();

    _02_STEP1_ReportSettimanale_GiornoLibero(int numeroGiorniLezioneSettimanaliAperturaScuola, int numeroGiorniLezioneSettimanaliPerClasse) {
        this.numeroGiorniLezioneSettimanaliAperturaScuola = numeroGiorniLezioneSettimanaliAperturaScuola;
        this.numeroGiorniLezioneSettimanaliPerClasse = numeroGiorniLezioneSettimanaliPerClasse;
        for (int i = 0; i < numeroGiorniLezioneSettimanaliAperturaScuola; i++) {
            classiNonInPresenza.add(new TreeSet<>());
            classiNonInPresenzaExtra.add(new TreeSet<>());
        }
    }

    public boolean verificaQuotaMax(int numStudentiPresentiLimite) {
        for (int i = 0; i < numeroGiorniLezioneSettimanaliAperturaScuola; i++) {
            Set<ClassData> classData = classiInPresenza(i);
            if (numeroStudenti(classData) > numStudentiPresentiLimite) {
                return false;
            }
        }
        return true;
    }

    public int totaleStudentiScuola() {
        int totStudenti = 0;
        for (ClassData c : ClassData.values()) {
            totStudenti += c.numberOfStudents;
        }
        return totStudenti;
    }

    public void addClassiNonInPresenza(int giorno, Collection<ClassData> c) {
        for (ClassData classData : c) {
            classiNonInPresenza.get(giorno).add(classData);
        }
    }

    public void addClassiNonInPresenzaExtra(int giorno, Collection<ClassData> c) {
        for (ClassData classData : c) {
            classiNonInPresenzaExtra.get(giorno).add(classData);
        }
    }

    public Set<ClassData> classiNonInPresenza(int giorno) {
        return classiNonInPresenza.get(giorno);
    }

    public Set<ClassData> classiNonInPresenzaExtra(int giorno) {
        return classiNonInPresenzaExtra.get(giorno);
    }

    public static int numeroStudenti(Collection<ClassData> classi) {
        int count = 0;
        for (ClassData x : classi) {
            count += x.numberOfStudents;
        }
        return count;
    }

    public Set<ClassData> classiInPresenza(int giorno) {
        Set<ClassData> classiNonInPresenza = classiNonInPresenza(giorno);
        Set<ClassData> classiNonInPresenzaExtra = classiNonInPresenzaExtra(giorno);
        Set<ClassData> ris = new TreeSet<>(Arrays.asList(ClassData.values()));
        ris.removeAll(classiNonInPresenza);
        ris.removeAll(classiNonInPresenzaExtra);
        return ris;
    }

    public void printReport() {
        System.out.println();
        System.out.println();
        System.out.println("===================================================================================================================================================");
        System.out.println(getClass().getSimpleName().toUpperCase(Locale.ROOT));
        System.out.println("===================================================================================================================================================");

        // System.out.println(" > classi sempre a scuola: " + classiChePossonoStareAriposo);
        System.out.println("GIORNI LIBERI CLASSI:");
        System.out.println(" Giorni apertura scuola:" + numeroGiorniLezioneSettimanaliAperturaScuola);
        System.out.println(" Giorni lezione per classe:" + numeroGiorniLezioneSettimanaliPerClasse);

        Map<ClassData, Integer> numLezioniInPresenzaPerClasse = new TreeMap<>();

        {
            for (ClassData c : ClassData.values()) {
                numLezioniInPresenzaPerClasse.put(c, 0);
            }
            for (int j = 0; j < numeroGiorniLezioneSettimanaliAperturaScuola; j++) {
                Set<ClassData> cc = classiInPresenza(j);
                for (ClassData c : cc) {
                    numLezioniInPresenzaPerClasse.put(c, numLezioniInPresenzaPerClasse.get(c) + 1);
                }
            }
        }

        int totStudenti = totaleStudentiScuola();

        for (int j = 0; j < numeroGiorniLezioneSettimanaliAperturaScuola; j++) {
            Set<ClassData> classData = classiNonInPresenza(j);
            Set<ClassData> classDataExtra = classiNonInPresenzaExtra(j);
            Set<ClassData> classi = classiInPresenza(j);
            int studentiPresenti = numeroStudenti(classi);

            System.out.printf("(%d)  > (%6.2f) %50s - extra %s\n", (j + 1), (100. * studentiPresenti / totStudenti), classData, classDataExtra);
        }


        Map<String, ArrayList<ClassData>> report = new TreeMap<>();
        Map<String, TreeSet<String>> report2 = new TreeMap<>();
        for (Map.Entry<ClassData, Integer> s : numLezioniInPresenzaPerClasse.entrySet()) {
            String kk;
            if (s.getValue() < numeroGiorniLezioneSettimanaliPerClasse) {
                kk = "*" + s.getValue();
            } else {
                kk = "" + s.getValue();
            }
            report.computeIfAbsent(kk, s1 -> new ArrayList<>());
            report2.computeIfAbsent(kk, s1 -> new TreeSet<>());
            report.get(kk).add(s.getKey());
            switch (s.getKey()._class) {
                case 1:
                    report2.get(kk).add("PRIME");
                    break;
                case 2:
                    report2.get(kk).add("SECONDE");
                    break;
                case 3:
                    report2.get(kk).add("TERZE");
                    break;
                case 4:
                    report2.get(kk).add("QUARTE");
                    break;
                case 5:
                    report2.get(kk).add("QUINTE");
                    break;
            }

        }

        System.out.println("Report giorni presenza/giorni totali");
        for (Map.Entry<String, TreeSet<String>> e : report2.entrySet()) {
            System.out.println(e.getKey() + " in presenza su " + numeroGiorniLezioneSettimanaliPerClasse + " totali" + "\t" + e.getValue());
        }

        System.out.println("Report giorni presenza/giorni totali per classe");
        for (Map.Entry<String, ArrayList<ClassData>> e : report.entrySet()) {
            System.out.println(e.getKey() + " in presenza su " + numeroGiorniLezioneSettimanaliPerClasse + " totali" + "\t" + e.getValue());
        }

        System.out.println("LEZIONI IN PRESENZA PER CLASSE:");
        int k = 0;
        for (Map.Entry<ClassData, Integer> s : numLezioniInPresenzaPerClasse.entrySet()) {
            if (s.getValue() < 5) {
                System.out.printf("   %2s (*%d)\t", s.getKey(), s.getValue());
            } else {
                System.out.printf("   %2s (%2d)\t", s.getKey(), s.getValue());
            }
            k++;
            if (k % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
