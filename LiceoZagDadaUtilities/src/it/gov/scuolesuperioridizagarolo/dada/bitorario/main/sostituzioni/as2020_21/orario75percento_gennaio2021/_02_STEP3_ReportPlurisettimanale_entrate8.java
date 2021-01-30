package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario75percento_gennaio2021;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

public class _02_STEP3_ReportPlurisettimanale_entrate8 {
    final _02_STEP2_ReportPlurisettimanale reportPlurisettimanale;
    final ArrayList<Set<ClassData>> classiEntrataOre8_perGiorno;
    public static final String PRE_8 = "P08";
    public static final String PRE_10 = "P10";
    public final static String DAD = _02_STEP2_ReportPlurisettimanale.DAD;
    //public final static String PRE = _02_ReportPlurisettimanale.PRE;
    public final static String NULLA = _02_STEP2_ReportPlurisettimanale.NULLA;


    public _02_STEP3_ReportPlurisettimanale_entrate8(_02_STEP2_ReportPlurisettimanale reportPlurisettimanale, ArrayList<Set<ClassData>> classiEntrataOre8_perGiorno) {
        this.reportPlurisettimanale = reportPlurisettimanale;
        this.classiEntrataOre8_perGiorno = classiEntrataOre8_perGiorno;
    }

    public void printReport() {
        reportPlurisettimanale.printReport();
        System.out.println();
        System.out.println();
        System.out.println("===================================================================================================================================================");
        System.out.println(getClass().getSimpleName().toUpperCase(Locale.ROOT));
        System.out.println("===================================================================================================================================================");


        System.out.println("Percentuale num. studenti in presenza per giorno/settimana ");

        _02_STEP1_ReportSettimanale_GiornoLibero reportSettimanale = reportPlurisettimanale.getReportSettimanale();
        int numSettimaneOrarioDAD = reportPlurisettimanale.pause_dad_plurisettimana.size();

        {
            System.out.print("ORE 8 -----");

            for (int giorno = 0; giorno < reportSettimanale.numeroGiorniLezioneSettimanaliAperturaScuola; giorno++) {
                System.out.printf("\t%10s%3d", "Giorno", giorno + 1);
            }
            System.out.println();

            int totStudenti = reportSettimanale.totaleStudentiScuola();
            for (int settimana = 0; settimana < numSettimaneOrarioDAD; settimana++) {
                System.out.printf("SETTIMANA %d\t", settimana);
                // per ogni giorno
                for (int giorno = 0; giorno < reportSettimanale.numeroGiorniLezioneSettimanaliAperturaScuola; giorno++) {
                    //cerca le classi che entrano alle ore 8 e calcola num studenti
                    int tot8 = 0;
                    int tot10 = 0;
                    for (ClassData c : ClassData.values()) {
                        String t = getClassDataType(c, settimana, giorno);
                        if (t.equals(PRE_8)) {
                            tot8 += c.numberOfStudents;
                        } else if (t.equals(PRE_10)) {
                            tot10 += c.numberOfStudents;
                        }
                    }


                    System.out.printf("%6.2f%% (%3d)\t", 100. * tot8 / totStudenti, tot8);
                }
                System.out.println();
            }

            System.out.println();
        }


        {
            System.out.println();
            System.out.print("ORE 10 ----");

            for (int giorno = 0; giorno < reportSettimanale.numeroGiorniLezioneSettimanaliAperturaScuola; giorno++) {
                System.out.printf("\t%10s%3d", "Giorno", giorno + 1);
            }
            System.out.println();

            int totStudenti = reportSettimanale.totaleStudentiScuola();
            for (int settimana = 0; settimana < numSettimaneOrarioDAD; settimana++) {
                System.out.printf("SETTIMANA %d\t", settimana);
                // per ogni giorno
                for (int giorno = 0; giorno < reportSettimanale.numeroGiorniLezioneSettimanaliAperturaScuola; giorno++) {
                    //cerca le classi che entrano alle ore 8 e calcola num studenti
                    int tot8 = 0;
                    int tot10 = 0;
                    for (ClassData c : ClassData.values()) {
                        String t = getClassDataType(c, settimana, giorno);
                        if (t.equals(PRE_8)) {
                            tot8 += c.numberOfStudents;
                        } else if (t.equals(PRE_10)) {
                            tot10 += c.numberOfStudents;
                        }
                    }


                    System.out.printf("%6.2f%% (%3d) \t", 100. * tot10 / totStudenti, tot10);
                }
                System.out.println();
            }

            System.out.println();
        }

        //=========================================
        System.out.println();
        System.out.println("Presenza classi / orario di entrata");
        {
            System.out.print("--\t");
            for (int settimana = 0; settimana < numSettimaneOrarioDAD; settimana++) {

                // per ogni giorno
                for (int giorno = 0; giorno < reportSettimanale.numeroGiorniLezioneSettimanaliAperturaScuola; giorno++) {
                    System.out.printf("%d/%d\t", giorno + 1, settimana + 1);
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


    public String getClassDataType(ClassData classe, int settimana, int giorno) {
        String t = reportPlurisettimanale.getClassDataType(classe, settimana, giorno);
        if (t.equals(_02_STEP2_ReportPlurisettimanale.PRE)) {
            if (classiEntrataOre8_perGiorno.get(giorno).contains(classe)) {
                return PRE_8;
            } else {
                return PRE_10;
            }
        } else {
            return t;
        }
    }
}
