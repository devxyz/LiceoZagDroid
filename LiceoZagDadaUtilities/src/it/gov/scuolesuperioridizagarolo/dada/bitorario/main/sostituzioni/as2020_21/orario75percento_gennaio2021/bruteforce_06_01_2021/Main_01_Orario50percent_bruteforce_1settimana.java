package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario75percento_gennaio2021.bruteforce_06_01_2021;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.util.*;

public class Main_01_Orario50percent_bruteforce_1settimana {

    public static void main(String[] args) {
        int numSettimaneOrario = 1;

        int numGiorniAperturaScuola_PerSettimana = 5;
        int numGiorniLezione_PerSettimana = 5;
        int numGiorniRiposo_PerSettimana = 1;
        int numSettimaneDAD = 1;

        int maxStudentiOre8 = 300;//60% del 50% del totale di studenti
        int maxStudentiPerGiorno = 500;//60% del totale perche' facciamo 3 giorni presenza e 2 giorni DAD (480)
        int maxStudentiDADPerGiorno = 550;//55% del totale


        System.out.println("CONFIGURAZIONE: ");
        System.out.println("numSettimaneOrario:" + numSettimaneOrario);
        System.out.println("numGiorniAperturaScuola_PerSettimana:" + numGiorniAperturaScuola_PerSettimana);
        System.out.println("numSettimaneDAD:" + numSettimaneDAD);
        System.out.println("maxStudentiOre8:" + maxStudentiOre8);
        System.out.println("maxStudentiPerGiorno:" + maxStudentiPerGiorno);
        System.out.println("maxStudentiDADPerGiorno:" + maxStudentiDADPerGiorno);


        OrarioComplessivoPlurisettimanale best = null;
        int best_value = 0;

        Random r = new Random(13);

        for (int i = 0; i < 10000; i++) {
            OrarioComplessivoPlurisettimanale soluzione = new OrarioComplessivoPlurisettimanale(numSettimaneOrario, numGiorniAperturaScuola_PerSettimana);

            //NO GIORNO LIBERO !!!!!!!!!!!!!
            //scelgo un giorno libero casuale per ogni classe e lo imposto in tutte le settimane
            for (OrarioClasse orarioClasse : soluzione.orario) {
                while (orarioClasse.contaNumeroGiorni(TipoOrario.DAD) < 2) {
                    int indexGiornoLibero = r.nextInt(numGiorniAperturaScuola_PerSettimana);
                    orarioClasse.set_PerTutteLeSettimane_UnPrecisoGiorno(indexGiornoLibero, TipoOrario.DAD, false);
                }
            }

            //scelgo, per ogni classe, le settimane di DAD
            for (OrarioClasse orarioClasse : soluzione.orario) {
                //orarioClasse.scegliSettimaneDAD(r, numSettimaneDAD);
            }

            //assegna entrate alle 8 fino a raggiungere il valore massimo di studenti ammessi
            for (int indexSettimana = 0; indexSettimana < numSettimaneOrario; indexSettimana++) {

                for (int indexGiorno = 0; indexGiorno < numGiorniAperturaScuola_PerSettimana; indexGiorno++) {
                    //per ogni giorno scelgo alcune classi  che non hanno l'orario assegnato

                    //cerco le classi libere
                    final LinkedList<ClassData> classiDaAssegnare;
                    {
                        ArrayList<ClassData> classData = new ArrayList<>(soluzione.classiSenzaAssegnazione(indexSettimana, indexGiorno));
                        if (classData.size() == 0)
                            continue;
                        Collections.shuffle(classData, r);
                        classiDaAssegnare = new LinkedList<>(classData);
                    }

                    //totale studenti ore 8 per ogni settimana

                    while (classiDaAssegnare.size() > 0) {

                        ClassData classeSucc = classiDaAssegnare.removeFirst();
                        int[] totStudentiOre8 = new int[numSettimaneOrario];

                        //controllo il giorno corrente di tutte le settimane e vedo in quale e' necessario impostare la classe
                        for (int indexSettimana2 = 0; indexSettimana2 < numSettimaneOrario; indexSettimana2++) {
                            TipoOrario tipoOrario = soluzione.get(classeSucc).settimane[indexSettimana2].giorni[indexGiorno];
                            //se trovo un giorno in cui la classe va impostata
                            if (tipoOrario == null) {
                                //calcolo il numero di studenti già assegnati alle 8
                                totStudentiOre8[indexSettimana2] = soluzione.totStudentiBy(TipoOrario.ORE8, indexSettimana2, indexGiorno);
                            }
                        }

                        //calcola il num di posti che si possono ancora assegnare
                        int margine = maxStudentiOre8;
                        for (int a : totStudentiOre8) {
                            margine = Math.min(margine, maxStudentiOre8 - a);
                        }

                        //assegna orario ore 8 se possibile

                        if (classeSucc.numberOfStudents <= margine) {
                            soluzione.get(classeSucc).set_PerTutteLeSettimane_UnPrecisoGiorno(indexGiorno, TipoOrario.ORE8, true);
                        } else {
                            soluzione.get(classeSucc).set_PerTutteLeSettimane_UnPrecisoGiorno(indexGiorno, TipoOrario.ORE10, true);
                        }
                    }


                }
            }

            //contra num studenti al giorno
            if (!soluzione.verificaNumStudentiPresenti(maxStudentiPerGiorno))
                continue;



            //contra num studenti in DAD al giorno
            if (!soluzione.verificaNumStudentiDAD(maxStudentiDADPerGiorno))
                continue;

            int val = soluzione.totClassiBy(TipoOrario.ORE8);
            if (best_value < val) {
                best = soluzione;
                best_value = val;
            }

        }

        if (best != null) {

            System.out.println("=============================");
            System.out.println("=============================");
            best.printReport();
            System.out.println("=============================");
            best.printReportTotaliPerGiorno();


        } else {
            System.out.println("SOLUZIONE NON TROVATA");
        }
    }

}
