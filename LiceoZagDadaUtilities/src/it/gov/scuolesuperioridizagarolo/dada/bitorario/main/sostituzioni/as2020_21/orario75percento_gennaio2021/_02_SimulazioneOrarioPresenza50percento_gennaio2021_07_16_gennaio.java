package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario75percento_gennaio2021;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.util.*;

public class _02_SimulazioneOrarioPresenza50percento_gennaio2021_07_16_gennaio {
    private static ArrayList<ClassData> suffle(Random r, ClassData[] cc) {
        return suffle(r, Arrays.asList(cc));
    }

    private static ArrayList<ClassData> suffle(Random r, List<ClassData> cc) {
        ArrayList<ClassData> c = new ArrayList<>(cc);
        Collections.shuffle(cc, r);
        return c;
    }

    private static ClassData[] toArray(Collection<ClassData> cc) {
        return cc.toArray(new ClassData[cc.size()]);
    }

    public static void main(String[] args) {
        if (true) {
            System.out.println("SOLUZIONE SU 6 giorni 50% - 60+40===============");
            int numGiorniLezioneAperturaScuola = 6;
            int numGiorniLezionePerClasse = 5;
            int numSettimaneOrarioDAD = 2;

            List<ClassData> cc2 = new ArrayList<>();
            cc2.addAll(ClassData.searchPerAnnoCorso(1));
            cc2.addAll(ClassData.searchPerAnnoCorso(2));
            cc2.addAll(ClassData.searchPerAnnoCorso(3));
            cc2.addAll(ClassData.searchPerAnnoCorso(4));
            cc2.addAll(ClassData.searchPerAnnoCorso(5));
            List<ClassData[]> cc = new ArrayList<>();
            cc.add(cc2.toArray(new ClassData[cc2.size()]));//1 riposo settimanale

            _02_STEP3_ReportPlurisettimanale_entrate8 reportSettimanale = allocazioneLezioniClassiSettimana(
                    100, 55, 100, 60,
                    numGiorniLezioneAperturaScuola, numGiorniLezionePerClasse, numSettimaneOrarioDAD,
                    cc
            );
            reportSettimanale.printReport();
            System.out.println("================================");
            int totStudenti = getTotStudenti(ClassData.values());
            System.out.printf("Numero totale studenti: %3d\n", totStudenti);
            int studentiGiornalieri = (int) (totStudenti * 0.5);
            System.out.printf("Numero studenti giornalieri (50%% totale): %3d\n", studentiGiornalieri);
            int fascia8 = (int) (studentiGiornalieri * 0.6);
            System.out.printf("Numero studenti ore 8 (60%% degli studenti giornalieri): %3d\n", fascia8);
            int fascia10 = (int) (studentiGiornalieri * 0.4);
            System.out.printf("Numero studenti ore 10 (40%% degli studenti giornalieri): %3d\n", fascia10);

        }

        if (false) {
            System.out.println("SOLUZIONE SU 5 giorni 50% ===============");
            int numGiorniLezioneAperturaScuola = 5;
            int numGiorniLezionePerClasse = 5;
            int numSettimaneOrarioDAD = 2;

            List<ClassData[]> cc = new ArrayList<>();
            //cc.add(ClassData.values());

            _02_STEP3_ReportPlurisettimanale_entrate8 reportSettimanale = allocazioneLezioniClassiSettimana(
                    100, 55, 60, 45,
                    numGiorniLezioneAperturaScuola, numGiorniLezionePerClasse, numSettimaneOrarioDAD,
                    cc
            );
            reportSettimanale.printReport();
        }


        if (false) {
            System.out.println("SOLUZIONE SU 6 giorni 75% ===============");
            int numGiorniLezioneAperturaScuola = 6;
            int numGiorniLezionePerClasse = 5;
            int numSettimaneOrarioDAD = 4;

            List<ClassData[]> cc = new ArrayList<>();
            cc.add(ClassData.values());

            _02_STEP3_ReportPlurisettimanale_entrate8 reportSettimanale = allocazioneLezioniClassiSettimana(
                    84, 65.5, 60, 60,
                    numGiorniLezioneAperturaScuola, numGiorniLezionePerClasse, numSettimaneOrarioDAD,
                    cc
            );
            reportSettimanale.printReport();
        }



        /*
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        {
            System.out.println("SOLUZIONE SU 5 giorni ===============");
            int numgiorni = 5;
            Collection<ClassData[]> cc = new ArrayList<>();
            cc.add(ClassData.values());
            cc.add(toArray(ClassData.searchPerAnnoCorso(3)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(4)));

            doSomething(numgiorni, cc);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        {
            System.out.println("SOLUZIONE SU 5 giorni con prime e quinte sempre a scuola===============");
            int numgiorni = 5;
            Collection<ClassData[]> cc = new ArrayList<>();
            cc.add(toArray(ClassData.searchPerAnnoCorso(2)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(3)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(4)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(2)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(3)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(4)));

            doSomething(numgiorni, cc);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        {
            System.out.println("SOLUZIONE SU 5 giorni con prime sempre a scuola===============");
            int numgiorni = 5;
            Collection<ClassData[]> cc = new ArrayList<>();
            cc.add(toArray(ClassData.searchPerAnnoCorso(2)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(3)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(4)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(5)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(2)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(3)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(4)));

            doSomething(numgiorni, cc);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        {
            System.out.println("SOLUZIONE SU 5 giorni con quinte sempre a scuola===============");
            int numgiorni = 5;
            Collection<ClassData[]> cc = new ArrayList<>();
            cc.add(toArray(ClassData.searchPerAnnoCorso(1)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(2)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(3)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(4)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(2)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(3)));
            //cc.add(toArray(ClassData.searchPerAnnoCorso(4)));

            doSomething(numgiorni, cc);
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        {
            System.out.println("SOLUZIONE SU 5 giorni con eguale distribuzione===============");
            int numgiorni = 5;
            Collection<ClassData[]> cc = new ArrayList<>();
            cc.add(toArray(ClassData.searchPerAnnoCorso(1)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(2)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(3)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(4)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(5)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(1)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(2)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(3)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(4)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(5)));
            doSomething(numgiorni, cc);
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        {
            System.out.println("SOLUZIONE SU 5 giorni con eguale distribuzione al 50%===============");
            int numgiorni = 5;
            Collection<ClassData[]> cc = new ArrayList<>();
            cc.add(toArray(ClassData.searchPerAnnoCorso(1)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(2)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(3)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(4)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(5)));

            cc.add(toArray(ClassData.searchPerAnnoCorso(1)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(2)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(3)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(4)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(5)));

            cc.add(toArray(ClassData.searchPerAnnoCorso(1)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(2)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(3)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(4)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(5)));
            doSomething(numgiorni, cc);
        }
*/

    }

    private static Set<ClassData> allocazioneEntrata08_perGiorno(Random r, int limiteMaxStudenti) {
        Set<ClassData> ris = new TreeSet<>();
        int tot = 0;
        int v = 0;

        LinkedList<ClassData> classi;
        {
            ArrayList<ClassData> c2 = new ArrayList<>(Arrays.asList(ClassData.values()));
            Collections.shuffle(c2, r);
            classi = new LinkedList<>(c2);
        }

        do {
            ClassData c = classi.removeFirst();
            v = c.numberOfStudents;
            if (tot + v < limiteMaxStudenti) {
                ris.add(c);
                tot += v;
            }
        } while (tot + v < limiteMaxStudenti && classi.size() > 0);

        return ris;
    }

    //per ogni settimana indica quali classi sono in DAD (ogni classe puo' comparire in DAD un numero di volte pari alle occorrenze in @param classiDaConsiderare
    private static ArrayList<Set<ClassData>> allocazioneDAD_perSettimana(Random r, int numsettimane, Collection<ClassData[]> classiDaConsiderare) {
        ArrayList<Set<ClassData>> settimana = new ArrayList<>();
        for (int i = 0; i < numsettimane; i++) {
            settimana.add(new TreeSet<>());
        }

        //sistema ogni classe in un diverso giorno della settimana
        for (ClassData[] c : classiDaConsiderare) {
            for (ClassData c2 : c) {
                int index = r.nextInt(numsettimane);
                int prove = 0;
                //cerca di trovare una allocazione alla classe (non possibile se la classe ha num allocazioni > settimane)
                while (!settimana.get(index).contains(c2) && prove < 2 * numsettimane) {
                    index = r.nextInt(numsettimane);
                    prove++;
                }
                settimana.get(index).add(c2);
            }
        }

        return settimana;
    }


    private static _02_STEP3_ReportPlurisettimanale_entrate8 allocazioneLezioniClassiSettimana(
            //quota riempimento usata per distribuire le giornate di stop nella settimana
            double PERCENT_quota_riempimento_giornaliera_step1_allocazioneClassiSettimana,
            double PERCENT_quota_riempimento_giornaliera_step2_allocazioneDadTraSettimane,
            double PERCENT_quota_riempimento_giornaliera_step3_entrata_ore_8_predisposizione,
            double PERCENT_quota_riempimento_giornaliera_step4_entrata_ore_8_definitivo,


            int numGiorniLezioneAperturaScuola,
            int numGiorniLezionePerClasse,
            int numSettimaneOrarioDAD,

            //classi che possono avere uno stop durante la settimana (una o piu' volte a seconda delle occorrenze)
            List<ClassData[]> classiStopAggiuntiviInSettimana) {
        ClassData[] classi = ClassData.values();

        Random r = new Random(13);

        int totStudenti = getTotStudenti(classi);
        int presenzaStudentiMassimaGiornaliera_step1 = (int) (totStudenti * PERCENT_quota_riempimento_giornaliera_step1_allocazioneClassiSettimana / 100.);
        int presenzaStudentiMassimaGiornaliera_step2_Plurisettimanale = (int) (totStudenti * PERCENT_quota_riempimento_giornaliera_step2_allocazioneDadTraSettimane / 100.);
        int presenzaStudentiMassimaGiornaliera_step3_Entrata8_predisp = (int) (totStudenti * PERCENT_quota_riempimento_giornaliera_step3_entrata_ore_8_predisposizione * PERCENT_quota_riempimento_giornaliera_step2_allocazioneDadTraSettimane / 100. / 100.);
        int presenzaStudentiMassimaGiornaliera_step4_Entrata8_definitivo = (int) (totStudenti * PERCENT_quota_riempimento_giornaliera_step4_entrata_ore_8_definitivo * PERCENT_quota_riempimento_giornaliera_step2_allocazioneDadTraSettimane / 100. / 100.);

        System.out.printf("Totale studenti:                                              %3d\n", totStudenti);
        System.out.printf("presenzaStudentiMassimaGiornaliera_step1:                     %3d\n", presenzaStudentiMassimaGiornaliera_step1);
        System.out.printf("presenzaStudentiMassimaGiornaliera_step2_Plurisettimanale:    %3d\n", presenzaStudentiMassimaGiornaliera_step2_Plurisettimanale);
        System.out.printf("presenzaStudentiMassimaGiornaliera_step3_Entrata8_predisp:    %3d\n", presenzaStudentiMassimaGiornaliera_step3_Entrata8_predisp);
        System.out.printf("presenzaStudentiMassimaGiornaliera_step4_Entrata8_definitivo: %3d\n", presenzaStudentiMassimaGiornaliera_step4_Entrata8_definitivo);

        //RIPETE VARIE VOLTE...
        for (int i = 0; i < 1000000; i++) {
            System.out.println("==================================");
            System.out.printf("%4d - TENTATIVO\n", (i + 1));
            //shuffle
            LinkedList<ClassData> classiChePossonoStareAriposo = new LinkedList<>();
            //1 giorno per ogni classe
            for (ClassData[] classData : classiStopAggiuntiviInSettimana) {
                classiChePossonoStareAriposo.addAll(suffle(r, classData));
            }
            /*
            classiChePossonoStareAriposo.addAll(suffle(r, ClassData.values()));//2° giorno
            classiChePossonoStareAriposo.addAll(suffle(r, ClassData.searchPerAnnoCorso(2)));//2° giorno
            classiChePossonoStareAriposo.addAll(suffle(r, ClassData.searchPerAnnoCorso(3)));//2° giorno
            classiChePossonoStareAriposo.addAll(suffle(r, ClassData.searchPerAnnoCorso(4)));//2° giorno
            */


            //classi a riposo per ogni giorno
            TreeSet<ClassData>[] classiARiposo = new TreeSet[numGiorniLezioneAperturaScuola];
            TreeSet<ClassData>[] classiARiposoExtra = new TreeSet[numGiorniLezioneAperturaScuola];//riposo per le classi che rimangono non utilizzate

            int[] numStudentiAScuolaPerGiorno = new int[numGiorniLezioneAperturaScuola];
            for (int giorno = 0; giorno < numGiorniLezioneAperturaScuola; giorno++) {
                classiARiposo[giorno] = new TreeSet<>();
                classiARiposoExtra[giorno] = new TreeSet<>();
                int studentiAScuolaOggi = totStudenti;

                //tiene a casa le classi fino a raggiungere la quota del 75%
                while (studentiAScuolaOggi > presenzaStudentiMassimaGiornaliera_step1 && classiChePossonoStareAriposo.size() > 0) {
                    //estrae la prima classe e la mette a casa
                    ClassData classe = classiChePossonoStareAriposo.removeFirst();
                    classiARiposo[giorno].add(classe);
                    studentiAScuolaOggi -= classe.numberOfStudents;
                }
                numStudentiAScuolaPerGiorno[giorno] = studentiAScuolaOggi;
            }

            //assegna le classi rimaste ai vari giorni
            {
                int k = 0;
                for (ClassData c : classiChePossonoStareAriposo) {
                    classiARiposoExtra[k % numGiorniLezioneAperturaScuola].add(c);
                    k++;
                }
            }

            _02_STEP1_ReportSettimanale_GiornoLibero ris1 = new _02_STEP1_ReportSettimanale_GiornoLibero(numGiorniLezioneAperturaScuola, numGiorniLezionePerClasse);
            for (int j = 0; j < numGiorniLezioneAperturaScuola; j++) {
                ris1.addClassiNonInPresenza(j, classiARiposo[j]);
                ris1.addClassiNonInPresenzaExtra(j, classiARiposoExtra[j]);
            }

            //verifica soluzione:
            if (ris1.verificaQuotaMax(presenzaStudentiMassimaGiornaliera_step1)) {
                //fa vari tentativi...
                for (int j = 0; j < 300; j++) {
                    //cerca allocazione DAD su piu' settimane
                    List<ClassData[]> rx = new ArrayList<>();
                    rx.add(ClassData.values());
                    ArrayList<Set<ClassData>> pause_dad_plurisettimana = allocazioneDAD_perSettimana(r, numSettimaneOrarioDAD, rx);
                    _02_STEP2_ReportPlurisettimanale ris2 = new _02_STEP2_ReportPlurisettimanale(ris1, pause_dad_plurisettimana);
                    if (ris2.verificaLimiteStudentiPerGiorno(presenzaStudentiMassimaGiornaliera_step2_Plurisettimanale)) {

                        ArrayList<Set<ClassData>> classiEntrataOre8_perGiorno = new ArrayList<>();
                        for (int giorno = 0; giorno < ris2.getReportSettimanale().numeroGiorniLezioneSettimanaliAperturaScuola; giorno++) {
                            //cerca di allocare entrate differenziate
                            int count = 0;
                            int best = 0;
                            Set<ClassData> best_classData_entrata8 = null;
                            boolean verifica3;
                            //cerca la migliore entro 1000 tentativi
                            do {
                                Set<ClassData> classData_entrata8 = allocazioneEntrata08_perGiorno(r, presenzaStudentiMassimaGiornaliera_step3_Entrata8_predisp);
                                count++;
                                verifica3 = ris2.verificaLimiteStudentiPerGiornoPerEntrata8(presenzaStudentiMassimaGiornaliera_step4_Entrata8_definitivo, giorno, classData_entrata8);
                                if (verifica3) {
                                    int tot = ris2.contaStudentiPerGiornoPerEntrata8(giorno, classData_entrata8);
                                    if (tot > best) {
                                        best = tot;
                                        best_classData_entrata8 = classData_entrata8;
                                    }
                                }
                            }
                            while (count < 1000);

                            //se non trovata soluzione, salta
                            if (best_classData_entrata8 == null) {
                                classiEntrataOre8_perGiorno.clear();
                                break;
                            } else {
                                classiEntrataOre8_perGiorno.add(best_classData_entrata8);
                            }
                        }

                        if (classiEntrataOre8_perGiorno.size() > 0) {
                            return new _02_STEP3_ReportPlurisettimanale_entrate8(ris2, classiEntrataOre8_perGiorno);
                        }
                    }
                }

            }
        }
        return null;
    }

    private static int getTotStudenti(ClassData[] classi) {
        int totStudenti = 0;
        for (ClassData c : classi) {
            totStudenti += c.numberOfStudents;
        }
        return totStudenti;
    }


}
