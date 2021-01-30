package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario75percento_gennaio2021.bruteforce_06_01_2021;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.util.*;
@Deprecated

//TODO DA RIVEDERE
public class Main_03_Orario75percent_bruteforce_dad_settimana_intera_4settimane {

    static interface VincoliAssegnazione {

        //eseguito ogni vokta che si trova quella classe e quel tipo di orario
        abstract boolean accept(ClassData classe, TipoOrario tipo, int indexSettimana, int indexGiorno);
    }

    private static class VincoliAssegnazione_evitare_classe_tipo_giorno implements VincoliAssegnazione {
        final Set<ClassData> c;
        final Set<TipoOrario> t;
        final Set<Integer> indexGiorno;

        protected VincoliAssegnazione_evitare_classe_tipo_giorno(ClassData[] c, TipoOrario[] t, int[] indexGiorno) {
            this.c = new TreeSet<>(Arrays.asList(c));
            this.t = new TreeSet<>(Arrays.asList(t));
            this.indexGiorno = new TreeSet<>();
            for (int i : indexGiorno) {
                this.indexGiorno.add(i);
            }
        }

        //eseguito ogni vokta che si trova quella classe e quel tipo di orario
        public boolean accept(ClassData classe, TipoOrario tipo, int indexSettimana, int indexGiorno) {
            if (c.contains(classe) && t.contains(tipo) && this.indexGiorno.contains(indexGiorno)) return false;
            return true;
        }
    }


    public static void main(String[] args) {
        ArrayList<VincoliAssegnazione> vincoli = vincoliDocentiEsterni();


        int numSettimaneOrario = 4;

        int numGiorniAperturaScuola_PerSettimana = 6;
        int numGiorniLezione_PerSettimana = 5;
        int numGiorniRiposo_PerSettimana = 1;
        int numSettimaneDAD = 1;

        int maxStudentiOre8 = 360;//60% degli studenti in presenza = 360

        int maxStudentiPerGiorno = 620;//75% in presenza su 800 = 600

        int maxStudentiDADPerGiorno = 500;//25% del totale = 200
        int maxEntrate_ore10_perClasse = 12;

        //
        int minClassiLibereAlGiorno = 1;
        int maxClassiLibereAlGiorno = 20;

        int maxClassiInPresenza = 36 - 10;
        //maxEntrate_ore10_perClasse = 2;

        System.out.println("CONFIGURAZIONE: ");
        System.out.println("numSettimaneOrario:" + numSettimaneOrario);
        System.out.println("numGiorniAperturaScuola_PerSettimana:" + numGiorniAperturaScuola_PerSettimana);
        System.out.println("numSettimaneDAD:" + numSettimaneDAD);
        System.out.println("maxStudentiOre8:" + maxStudentiOre8);
        System.out.println("maxStudentiPerGiorno:" + maxStudentiPerGiorno);
        System.out.println("maxStudentiDADPerGiorno:" + maxStudentiDADPerGiorno);
        System.out.println("maxEntrate_ore10_perClasse:" + maxEntrate_ore10_perClasse);
        System.out.println("numGiorniLezione_PerSettimana:" + numGiorniLezione_PerSettimana);
        System.out.println("numGiorniRiposo_PerSettimana:" + numGiorniRiposo_PerSettimana);

        OrarioComplessivoPlurisettimanale best = null;
        int best_value = 0;

        Random r = new Random(13);
        int skip = 0;
        int erroriVincoli = 0;
        int erroriverificaMaxNumeroEntrate10 = 0;
        int erroriverificaNumStudentiPresenti = 0;
        int erroriverificaMinMaxGIORNO_LIBERO_perOgniGiornoDelleSettimane = 0;
        int erroriverificaNumStudentiDAD = 0;
        int erroriverificaNumClassiPresenti = 0;

        int maxCicle = 1000000;
        for (int i = 0; i < maxCicle; i++) {
            //massimo 10000 skip
            //if (skip > maxCicle) break;

            OrarioComplessivoPlurisettimanale soluzione = new OrarioComplessivoPlurisettimanale(numSettimaneOrario, numGiorniAperturaScuola_PerSettimana);

            //GIORNO LIBERO !!!!!!!!!!!!!
            //scelgo un giorno libero casuale per ogni classe e lo imposto in tutte le settimane
            for (OrarioClasse orarioClasse : soluzione.orario) {
                while (orarioClasse.contaNumeroGiorni(TipoOrario.GIORNO_LIBERO) < numGiorniRiposo_PerSettimana) {
                    int indexGiornoLibero = r.nextInt(numGiorniAperturaScuola_PerSettimana);
                    orarioClasse.set_PerTutteLeSettimane_UnPrecisoGiorno(indexGiornoLibero, TipoOrario.GIORNO_LIBERO, false);
                }
            }


            //scelgo, per ogni classe, le settimane di DAD
            for (OrarioClasse orarioClasse : soluzione.orario) {
                orarioClasse.scegliSettimaneDAD(r, numSettimaneDAD);
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

            //verifica soluzione vincoli aggiuntivi
            if (!soluzione.verificaVincoli(vincoli)) {
                skip++;
                erroriVincoli++;
                //System.out.println("ERRORE: vincoli");
                continue;
            }


            //contra num studenti presenti al giorno
            if (!soluzione.verificaNumStudentiPresenti(maxStudentiPerGiorno)) {
                skip++;
                erroriverificaNumStudentiPresenti++;
                //System.out.println("ERRORE: verificaNumStudentiPresenti");
                continue;
            }

            //contra num classi presenti al giorno
            if (!soluzione.verificaNumClassiPresenti(maxClassiInPresenza)) {
                skip++;
                erroriverificaNumClassiPresenti++;
                //System.out.println("ERRORE: verificaNumStudentiPresenti");
                continue;
            }

            //contra num studenti in DAD al giorno
            if (!soluzione.verificaNumStudentiDAD(maxStudentiDADPerGiorno)) {
                skip++;
                erroriverificaNumStudentiDAD++;
                //System.out.println("ERRORE: verificaNumStudentiDAD");
                continue;
            }


            //contra num studenti a casa (liberi)
            if (!soluzione.verificaMinMaxGIORNO_LIBERO_perOgniGiornoDelleSettimane(minClassiLibereAlGiorno, maxClassiLibereAlGiorno)) {
                skip++;
                erroriverificaMinMaxGIORNO_LIBERO_perOgniGiornoDelleSettimane++;
                continue;

            }


            //contra num studenti in DAD al giorno
            if (!soluzione.verificaMaxNumeroEntrate10(maxEntrate_ore10_perClasse)) {
                skip++;
                erroriverificaMaxNumeroEntrate10++;
                //System.out.println("ERRORE: verificaMaxEntrate10");
                continue;
            }

            //se c'e' il sabato i P10 diventano P8
            if (numGiorniAperturaScuola_PerSettimana == 6) {
                //per ogni classe assegna il sabato a P8
                soluzione.assegnaP8_sabato();
            }

            System.out.println();
            System.out.printf("Trovata soluzione i=%d, skip=%d\n", i, skip);
            System.out.printf(" > erroriVincoli:\t%d\n", erroriVincoli);
            System.out.printf(" > erroriverificaMaxNumeroEntrate10:\t%d\n", erroriverificaMaxNumeroEntrate10);
            System.out.printf(" > erroriverificaNumStudentiPresenti:\t%d\n", erroriverificaNumStudentiPresenti);
            System.out.printf(" > erroriverificaMinMaxGIORNO_LIBERO_perOgniGiornoDelleSettimane:\t%d\n", erroriverificaMinMaxGIORNO_LIBERO_perOgniGiornoDelleSettimane);
            System.out.printf(" > erroriverificaNumStudentiDAD:\t%d\n", erroriverificaNumStudentiDAD);
            System.out.printf(" > erroriverificaNumClassiPresenti:\t%d\n", erroriverificaNumClassiPresenti);

            int val = soluzione.totClassiBy(TipoOrario.ORE8);
            if (best_value < val) {
                best = soluzione;
                best_value = val;
            }

        }

        {

            System.out.println("SOLUZIONE:");
            System.out.println("======= ASSEGNAZIONI ======================");
            best.printReport();

            System.out.println("======= PRESENZE     ======================");
            best.printReportTotaliPerGiorno();


        }
    }

    static ArrayList<VincoliAssegnazione> vincoliDocentiEsterni() {
        int LUN = 0;
        int MAR = 1;
        int MER = 2;
        int GIO = 3;
        int VEN = 4;
        int SAB = 5;
        ArrayList<VincoliAssegnazione> vincoli = new ArrayList<>();
        //2H graziosi - venerdi''
        vincoli.add(new VincoliAssegnazione_evitare_classe_tipo_giorno(
                new ClassData[]{ClassData.CLASS_2H},
                new TipoOrario[]{TipoOrario.GIORNO_LIBERO, TipoOrario.ORE10},
                new int[]{VEN}));

        //CARA - lunedi'
        vincoli.add(new VincoliAssegnazione_evitare_classe_tipo_giorno(
                new ClassData[]{ClassData.CLASS_2E, ClassData.CLASS_3C},
                new TipoOrario[]{TipoOrario.GIORNO_LIBERO, TipoOrario.ORE10},
                new int[]{LUN}));

        //CARA - giovedi''
        vincoli.add(new VincoliAssegnazione_evitare_classe_tipo_giorno(
                new ClassData[]{ClassData.CLASS_2E, ClassData.CLASS_3C},
                new TipoOrario[]{TipoOrario.GIORNO_LIBERO},
                new int[]{GIO}));

        //BRUSCOLOTTI - MA + GIO
        vincoli.add(new VincoliAssegnazione_evitare_classe_tipo_giorno(
                new ClassData[]{ClassData.CLASS_3C, ClassData.CLASS_3A, ClassData.CLASS_3H, ClassData.CLASS_3E},
                new TipoOrario[]{TipoOrario.GIORNO_LIBERO},
                new int[]{MAR, GIO}));

        //BIONDI E. - lun e giovedi''
        vincoli.add(new VincoliAssegnazione_evitare_classe_tipo_giorno(
                new ClassData[]{ClassData.CLASS_5F},
                new TipoOrario[]{TipoOrario.GIORNO_LIBERO},
                new int[]{LUN, GIO}));
        vincoli.add(new VincoliAssegnazione_evitare_classe_tipo_giorno(
                new ClassData[]{ClassData.CLASS_5F},
                new TipoOrario[]{TipoOrario.ORE10},
                new int[]{GIO}));


        //CARINGI - lun e MERC''
        vincoli.add(new VincoliAssegnazione_evitare_classe_tipo_giorno(
                new ClassData[]{ClassData.CLASS_3A, ClassData.CLASS_4H},
                new TipoOrario[]{TipoOrario.GIORNO_LIBERO},
                new int[]{LUN, MER}));

        //GRASSOTTI - MA
        vincoli.add(new VincoliAssegnazione_evitare_classe_tipo_giorno(
                new ClassData[]{ClassData.CLASS_1E, ClassData.CLASS_1F, ClassData.CLASS_1D},
                new TipoOrario[]{TipoOrario.GIORNO_LIBERO},
                new int[]{MAR}));

        //GRASSOTTI - VEN
        vincoli.add(new VincoliAssegnazione_evitare_classe_tipo_giorno(
                new ClassData[]{ClassData.CLASS_2H, ClassData.CLASS_3H, ClassData.CLASS_4H},
                new TipoOrario[]{TipoOrario.GIORNO_LIBERO},
                new int[]{VEN}));

        //LEGGERI - ME+VE
        vincoli.add(new VincoliAssegnazione_evitare_classe_tipo_giorno(
                new ClassData[]{ClassData.CLASS_1C, ClassData.CLASS_1D, ClassData.CLASS_1F},
                new TipoOrario[]{TipoOrario.GIORNO_LIBERO},
                new int[]{MER, VEN}));


        //ROSSI - MA GIO
        vincoli.add(new VincoliAssegnazione_evitare_classe_tipo_giorno(
                new ClassData[]{ClassData.CLASS_2C, ClassData.CLASS_2G, ClassData.CLASS_4G, ClassData.CLASS_5G,},
                new TipoOrario[]{TipoOrario.GIORNO_LIBERO},
                new int[]{MAR, GIO}));

        /*
        //BIANCHI
        vincoli.add(new VincoliAssegnazione_evitare_classe_tipo_giorno(
                new ClassData[]{ClassData.CLASS_1A, ClassData.CLASS_2A, ClassData.CLASS_1D,},
                new TipoOrario[]{TipoOrario.GIORNO_LIBERO},
                new int[]{MAR, GIO, VEN}));

        //BIANCHI
        vincoli.add(new VincoliAssegnazione_evitare_classe_tipo_giorno(
                new ClassData[]{ClassData.CLASS_3A},
                new TipoOrario[]{TipoOrario.GIORNO_LIBERO},
                new int[]{MAR, GIO}));
*/
        return vincoli;
    }

}
