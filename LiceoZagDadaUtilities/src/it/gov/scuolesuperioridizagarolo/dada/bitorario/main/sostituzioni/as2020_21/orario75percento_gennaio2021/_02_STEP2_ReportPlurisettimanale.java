package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario75percento_gennaio2021;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

public class _02_STEP2_ReportPlurisettimanale {
    public final _02_STEP1_ReportSettimanale_GiornoLibero reportSettimanale;
    public final ArrayList<Set<ClassData>> pause_dad_plurisettimana;

    public _02_STEP2_ReportPlurisettimanale(_02_STEP1_ReportSettimanale_GiornoLibero reportSettimanale, ArrayList<Set<ClassData>> pause_dad_plurisettimana) {
        this.reportSettimanale = reportSettimanale;
        this.pause_dad_plurisettimana = pause_dad_plurisettimana;
    }

    public _02_STEP1_ReportSettimanale_GiornoLibero getReportSettimanale() {
        return reportSettimanale;
    }

    public ArrayList<Set<ClassData>> getPause_dad_plurisettimana() {
        return pause_dad_plurisettimana;
    }

    public boolean verificaLimiteStudentiPerGiorno(int maxStudentiPerGiorno) {
        int numSettimaneOrarioDAD = pause_dad_plurisettimana.size();

        //per ogni settimana
        for (int settimana = 0; settimana < numSettimaneOrarioDAD; settimana++) {
            // per ogni giorno
            for (int giorno = 0; giorno < reportSettimanale.numeroGiorniLezioneSettimanaliAperturaScuola; giorno++) {
                Set<ClassData> presenti = classiInPresenza(settimana, giorno);
                int numStud = _02_STEP1_ReportSettimanale_GiornoLibero.numeroStudenti(presenti);
                if (numStud > maxStudentiPerGiorno) return false;
            }
        }
        return true;
    }

    public Set<ClassData> classiInPresenza(int settimana, int giorno) {
        Set<ClassData> classData = reportSettimanale.classiInPresenza(giorno);//prendo le classi presenti
        classData.removeAll(pause_dad_plurisettimana.get(settimana));//tolgo quelle in dad
        return classData;
    }

    public String getClassDataType(ClassData classe, int settimana, int giorno) {
        if (reportSettimanale.classiNonInPresenza(giorno).contains(classe))
            return NULLA;
        if (reportSettimanale.classiNonInPresenzaExtra(giorno).contains(classe))
            return NULLA;
        if (pause_dad_plurisettimana.get(settimana).contains(classe)) {
            return DAD;
        } else {
            return PRE;
        }
    }

    public final static String DAD = "DAD";
    public final static String PRE = "PRE";
    public final static String NULLA = "---";

    public int contaStudentiPerGiornoPerEntrata8(int settimana, int giorno, Set<ClassData> classiEntrata8) {
        ClassData[] values = ClassData.values();
        int tot = 0;
        for (ClassData c : values) {
            if (getClassDataType(c, settimana, giorno).equals(PRE)) {
                if (classiEntrata8.contains(c))
                    tot += c.numberOfStudents;
            }
        }
        return tot;
    }

    public int contaStudentiPerGiornoPerEntrata8(int giorno, Set<ClassData> classiEntrata8) {
        int tot = 0;
        int numSettimaneOrarioDAD = pause_dad_plurisettimana.size();

        //per ogni settimana
        for (int settimana = 0; settimana < numSettimaneOrarioDAD; settimana++) {
            tot += contaStudentiPerGiornoPerEntrata8(settimana, giorno, classiEntrata8);

        }
        return tot;

    }

    public boolean verificaLimiteStudentiPerGiornoPerEntrata8(int maxStudentiPerGiorno_ore8, int giorno, Set<ClassData> classiEntrata8) {
        int numSettimaneOrarioDAD = pause_dad_plurisettimana.size();

        //per ogni settimana
        for (int settimana = 0; settimana < numSettimaneOrarioDAD; settimana++) {
            int b = contaStudentiPerGiornoPerEntrata8(settimana, giorno, classiEntrata8);
            if (b > maxStudentiPerGiorno_ore8) return false;
        }
        return true;
    }


    public void printReport() {
        reportSettimanale.printReport();


        System.out.println();
        System.out.println();
        System.out.println("===================================================================================================================================================");
        System.out.println(getClass().getSimpleName().toUpperCase(Locale.ROOT));
        System.out.println("===================================================================================================================================================");

        int numSettimaneOrarioDAD = pause_dad_plurisettimana.size();
        //per ogni settimana
        System.out.println("Classi in presenza per giorno/settimana");
        System.out.print("----------");
        for (int giorno = 0; giorno < reportSettimanale.numeroGiorniLezioneSettimanaliAperturaScuola; giorno++) {
            System.out.printf("\tday %d", giorno + 1);
        }
        System.out.println();


        for (int settimana = 0; settimana < numSettimaneOrarioDAD; settimana++) {
            System.out.printf("SETTIMANA %d\t", settimana);
            // per ogni giorno
            for (int giorno = 0; giorno < reportSettimanale.numeroGiorniLezioneSettimanaliAperturaScuola; giorno++) {
                Set<ClassData> presenti = classiInPresenza(settimana, giorno);
                System.out.printf("%s\t", presenti);
            }
            System.out.println();
        }

        System.out.println("Percentuale num. studenti in presenza per giorno/settimana rispetto al totale ");
        System.out.print("----------");
        for (int giorno = 0; giorno < reportSettimanale.numeroGiorniLezioneSettimanaliAperturaScuola; giorno++) {
            System.out.printf("\t%10s%3d", "Giorno", giorno + 1);
        }
        System.out.println();

        int totStudenti = reportSettimanale.totaleStudentiScuola();
        for (int settimana = 0; settimana < numSettimaneOrarioDAD; settimana++) {
            System.out.printf("SETTIMANA %d\t", settimana);
            // per ogni giorno
            for (int giorno = 0; giorno < reportSettimanale.numeroGiorniLezioneSettimanaliAperturaScuola; giorno++) {
                Set<ClassData> presenti = classiInPresenza(settimana, giorno);
                int num = _02_STEP1_ReportSettimanale_GiornoLibero.numeroStudenti(presenti);
                System.out.printf("%6.2f%% (%3d)\t", 100. * num / totStudenti, num);
            }
            System.out.println();
        }

        System.out.println();


        System.out.println("Presenza classi");
        {
            System.out.print("--\t");
            for (int settimana = 0; settimana < numSettimaneOrarioDAD; settimana++) {

                // per ogni giorno
                for (int giorno = 0; giorno < reportSettimanale.numeroGiorniLezioneSettimanaliAperturaScuola; giorno++) {
                    System.out.printf("%d/%d\t", giorno, settimana);
                }
                System.out.print("|\t");
            }
        }
        System.out.println();
        for (ClassData c : ClassData.values()) {
            System.out.print(c.className + "\t");
            for (int settimana = 0; settimana < numSettimaneOrarioDAD; settimana++) {
                for (int giorno = 0; giorno < reportSettimanale.numeroGiorniLezioneSettimanaliAperturaScuola; giorno++) {
                    String v = getClassDataType(c, settimana, giorno);
                    System.out.printf("%s\t", v);
                }
                System.out.print("|\t");
            }
            System.out.println();
        }
    }

}
