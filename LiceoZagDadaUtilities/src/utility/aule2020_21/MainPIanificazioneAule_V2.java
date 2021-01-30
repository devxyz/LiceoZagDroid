package utility.aule2020_21;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import utility.aule2020_21.data.*;
import utility.aule2020_21.misura_ottimo_soluzione.AssegnazioneClasseAulaGiornaliera202021_MisuraPeso;
import utility.aule2020_21.misura_ottimo_soluzione.AssegnazioneClasseAulaGiornaliera202021_MisuraPeso_classiScienzeAppPiano1;

import java.io.PrintStream;
import java.util.*;

public class MainPIanificazioneAule_V2 {
    static Random r = SingletonRandomizer.getSingleton();

    static void shuffle(ArrayList<Classe202021> classi) {
        RipartizioneAuleClassiEngine.sortClassiCrescenti(classi);
        Collections.shuffle(classi, r);
    }

    static final boolean PRINT1 = false;
    static final boolean PRINT2 = true;
    static final boolean PRINT3 = false;


    protected static boolean testFerrigno(ArrayList<AssegnazioneClasseAulaGiornaliera202021> assegnazioni) {
        if (true) return true;
        for (AssegnazioneClasseAulaGiornaliera202021 aaa : assegnazioni) {
            for (AssegnazioneClasseAula202021 a : aaa.assegnazioni) {
                if (a.classe == null || a.aula == null) continue;
                ClassData classData = a.classe.toClassData();
                RoomData roomData = a.aula.toRoomData();
                if (roomData == RoomData.F2) {
                    //ferrigno
                    if (classData == ClassData.CLASS_4A) return false;
                    if (classData == ClassData.CLASS_2D) return false;
                    if (classData == ClassData.CLASS_2A) return false;

                    //centracchio
                    if (classData == ClassData.CLASS_5C) return false;
                    if (classData == ClassData.CLASS_1B) return false;
                    if (classData == ClassData.CLASS_4B) return false;
                    if (classData == ClassData.CLASS_3B) return false;
                    if (classData == ClassData.CLASS_2C) return false;


                    if (classData._section.equals("D")) return false;
                    if (classData._section.equals("B")) return false;
                    if (classData._section.equals("F")) return false;
                    if (classData._section.equals("H")) return false;
                }
            }
        }

        return true;
    }


    public static void main(String[] args) {
        RipartizioneAuleClassiData.selectDataSource(RipartizioneAuleClassiData_08_aule_definitive.class);
        int pesoOttimo = Integer.MIN_VALUE;
        ArrayList<AssegnazioneClasseAulaGiornaliera202021> assegnazioneMigliore = null;

        int numGiorni = 5;
        boolean soluzioneTrovata = false;


        for (int i = 0; i < 5000; i++) {
            System.out.println("Test n." + i);

            //per giorni settimana
            Map<Integer, HashSet<Classe202021>> classiRiposoPerGiorno = new TreeMap<>();
            for (int j = 1; j <= numGiorni; j++) {
                classiRiposoPerGiorno.put(j, new HashSet<>());
            }

            //no didattica a distanza per le prime e le seconde
            ArrayList<Classe202021> classi = RipartizioneAuleClassiData.singleton().classi("1");
            shuffle(classi);

            //posiziona ogni classe in un giorno differente (il suo giorno di riposo)
            int giorno = 1;
            for (Classe202021 c : classi) {
                classiRiposoPerGiorno.get(giorno).add(c);
                giorno++;
                if (giorno > numGiorni) {
                    giorno = 1;
                }
            }

            //assegna le aule per ogni giorno
            int totNonAssegnate = 0;
            ArrayList<AssegnazioneClasseAulaGiornaliera202021> assegnazioniSettimana = new ArrayList<>();
            for (int nGiorno = 1; nGiorno <= numGiorni; nGiorno++) {
                ArrayList<Classe202021> presente = RipartizioneAuleClassiData.singleton().classi();
                HashSet<Classe202021> riposo = classiRiposoPerGiorno.get(nGiorno);
                presente.removeAll(riposo);
                EGiorno ggiorno = EGiorno.values()[nGiorno - 1];


                ArrayList<Aula202021> aule = RipartizioneAuleClassiData.singleton().aule();


                AssegnazioneClasseAulaGiornaliera202021 assegnazioni = RipartizioneAuleClassiEngine.assegnaClassiAule(aule, presente, ggiorno);
                int nonAssegnate = (assegnazioni.assegnazioniNonEffettuate().size());
                totNonAssegnate += nonAssegnate;
                if (PRINT1) {
                    System.out.println("  GIORNO:" + nGiorno);
                    System.out.println("     > riposo: " + riposo);
                    System.out.println("     > presente: " + presente);
                    System.out.println("     > assegnazioni: " + assegnazioni.assegnazioniEffettuate());
                    System.out.println("     > non assegnate: " + assegnazioni.assegnazioniNonEffettuate());
                    System.out.println("     > aula libera: " + assegnazioni.assegnazioniAuleLibere());
                }
                assegnazioniSettimana.add(assegnazioni);
            }

            //********************
            // ricerca soluzione migliore
            //final AssegnazioneClasseAulaGiornaliera202021_MisuraPeso misuraOttimo = new AssegnazioneClasseAulaGiornaliera202021_MisuraPeso_numeroCambiAula();
            final AssegnazioneClasseAulaGiornaliera202021_MisuraPeso misuraOttimo = new AssegnazioneClasseAulaGiornaliera202021_MisuraPeso_classiScienzeAppPiano1();


            boolean testSuperato = true;//testFerrigno(assegnazioniSettimana);
            if (PRINT2) {
                System.out.println("NON ASSEGNATE: " + totNonAssegnate);
                if (testSuperato && totNonAssegnate == 0) {
                    System.out.println(">>>>>>>>>>>>>> SOLUZIONE >>>>>>>>>>>>>>>>>");
                }

                if (PRINT3) {
                    if (totNonAssegnate > 0) {
                        giorno = 1;
                        for (AssegnazioneClasseAulaGiornaliera202021 a : assegnazioniSettimana) {
                            ArrayList<AssegnazioneClasseAula202021> r2 = a.assegnazioniNonEffettuate();
                            if (r2.size() > 0) {
                                System.out.println("GIORNO " + giorno);
                                ArrayList<AssegnazioneClasseAula202021> r1 = a.assegnazioniEffettuate();
                                ArrayList<AssegnazioneClasseAula202021> r3 = a.assegnazioniAuleLibere();

                                System.out.println("     > assegnazioni: (" + r1.size() + ")" + r1);
                                System.out.println("     > non assegnate: (" + r2.size() + ")" + r2);
                                System.out.println("     > aula libera: (" + r3.size() + ")" + r3);
                                System.out.println("     -------------------------------------------------");
                            }
                            giorno++;
                        }


                    }
                }


                if (totNonAssegnate == 0) {


                    int peso = misuraOttimo.misura(assegnazioniSettimana);
                    if (assegnazioneMigliore == null || misuraOttimo.compareTo(peso, pesoOttimo) < 0) {
                        pesoOttimo = peso;
                        assegnazioneMigliore = assegnazioniSettimana;
                        soluzioneTrovata = true;
                    }
                    System.out.println("Peso: " + peso);
                    System.out.println("Test: " + testSuperato);
                }
            }

            System.out.println("======================================");


        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        {
            ArrayList<Classe202021> classi = RipartizioneAuleClassiData.singleton().classi();
            ArrayList<Aula202021> aule = RipartizioneAuleClassiData.singleton().aule();
            if (assegnazioneMigliore != null)
                printAssegnazioneSettimanaPerClasse(classi, assegnazioneMigliore);
            System.out.println();
            System.out.println("=============================");
            if (soluzioneTrovata) {
                printAssegnazioneSettimanaPerAula(aule, assegnazioneMigliore);
                System.out.println("Peso migliore: " + pesoOttimo);

                System.out.println("----- SOLUZIONE TROVATA!!");
            } else
                System.out.println("----- SOLUZIONE NON TROVATA!!");
        }

    }

    public static AssegnazioneClasseAula202021 cerca(
            ArrayList<AssegnazioneClasseAulaGiornaliera202021> aaa,
            Classe202021 classe,
            int giorno) {
        AssegnazioneClasseAulaGiornaliera202021 x = aaa.get(giorno);
        return x.cerca(classe);
    }

    public static AssegnazioneClasseAula202021 cerca(
            ArrayList<AssegnazioneClasseAulaGiornaliera202021> aaa,
            Aula202021 aula,
            int giorno) {
        AssegnazioneClasseAulaGiornaliera202021 x = aaa.get(giorno);
        return x.cerca(aula);
    }

    public static List<Aula202021> cercaAuleLibere(
            ArrayList<AssegnazioneClasseAulaGiornaliera202021> aaa,
            int giorno) {
        AssegnazioneClasseAulaGiornaliera202021 x = aaa.get(giorno);
        return x.cercaAuleLibere();
    }

    public static List<Classe202021> cercaClassiACasa(
            ArrayList<AssegnazioneClasseAulaGiornaliera202021> aaa,
            int giorno) {
        AssegnazioneClasseAulaGiornaliera202021 x = aaa.get(giorno);
        return x.cercaClassiACasa();
    }

    static void printAssegnazioneSettimanaPerClasse(ArrayList<Classe202021> classi, ArrayList<AssegnazioneClasseAulaGiornaliera202021> aaa) {
        String[] settimana = new String[]{"LUN", "MAR", "MER", "GIO", "VEN", "SAB", "DOM"};
        System.out.print("CLASSE");
        System.out.print("\t");
        for (int giorno = 0; giorno < aaa.size(); giorno++) {
            System.out.print(settimana[giorno]);
            System.out.print("\t");
        }
        System.out.print("Posti disponibili classe");
        System.out.println();
        for (Classe202021 classe : classi) {
            System.out.print(classe);
            int minPostiLiberi = classe.numerosita;
            for (int giorno = 0; giorno < aaa.size(); giorno++) {
                AssegnazioneClasseAula202021 assegnazione = cerca(aaa, classe, giorno);
                System.out.print("\t");
                if (assegnazione == null) {
                    System.out.print("-");
                } else if (assegnazione.aula == null) {
                    System.out.print("NO AULA");
                    minPostiLiberi = -1;
                } else {
                    int postiLiberi = assegnazione.aula.capienza - assegnazione.classe.numerosita;
                    System.out.print(assegnazione.aula.aula + "[" + assegnazione.aula.capienza + "] (+" + postiLiberi + ")");
                    minPostiLiberi = Math.min(minPostiLiberi, postiLiberi);
                }

            }
            System.out.print("\t" + minPostiLiberi);

            System.out.println();
        }

        System.out.print("AULE LIBERE\t");
        for (int giorno = 0; giorno < aaa.size(); giorno++) {
            List<Aula202021> lib = cercaAuleLibere(aaa, giorno);
            for (Aula202021 x : lib) {
                System.out.print(" " + x.aula + "[" +
                        x.capienza + "]");
            }
            System.out.print("\t");
        }


        System.out.println();
        System.out.println();
        System.out.println();
        printCodiceAssegnazioneSettimanaPerClasse(System.out, aaa);


    }

    private static String toString(EGiorno g) {
        return "EGiorno." + g.name();
    }

    private static String toString(RoomData r) {
        return "RoomData." + r.name();
    }

    private static String toString(ClassData r) {
        return "ClassData." + r.name();
    }

    public static void printCodiceAssegnazioneSettimanaPerClasse(PrintStream out, ArrayList<AssegnazioneClasseAulaGiornaliera202021> aaa) {
        out.println("ArrayList<UtilizzoClassi>assegnazioni=new ArrayList<>();");
        for (AssegnazioneClasseAulaGiornaliera202021 a : aaa) {
            for (AssegnazioneClasseAula202021 b : a.assegnazioni) {

                if (b.classe == null) continue;

                if (b.aula == null) {

                    out.println("assegnazioni.add(new UtilizzoClassi("
                            + toString(RoomData.DDI_da_casa) + ","

                            + toString(b.classe.toClassData()) + ","
                            + toString(b.giorno) + "));"
                    );
                    throw new IllegalArgumentException("Nessuna aula per " + b.giorno + " " + b.classe);

                } else {
                    out.println("assegnazioni.add(new UtilizzoClassi("
                            + toString(b.aula.toRoomData()) + ","

                            + toString(b.classe.toClassData()) + ","
                            + toString(b.giorno) + "));"
                    );
                }
            }
        }
    }

    static void printAssegnazioneSettimanaPerAula(ArrayList<Aula202021> aule, ArrayList<AssegnazioneClasseAulaGiornaliera202021> aaa) {
        String[] settimana = new String[]{"LUN", "MAR", "MER", "GIO", "VEN", "SAB", "DOM"};
        System.out.print("AULA");
        System.out.print("\t");
        for (int giorno = 0; giorno < aaa.size(); giorno++) {
            System.out.print(settimana[giorno]);
            System.out.print("\t");
        }
        System.out.print("Posti disponibili classe");
        System.out.println();

        ArrayList<String> utilizzate = new ArrayList<>();
        for (Aula202021 aula : aule) {
            System.out.print(aula.aula + "[" + aula.capienza + "]");
            int minPostiLiberi = aula.capienza;
            boolean found = false;
            for (int giorno = 0; giorno < aaa.size(); giorno++) {
                AssegnazioneClasseAula202021 assegnazione = cerca(aaa, aula, giorno);
                System.out.print("\t");
                if (assegnazione == null) {
                    System.out.print("-");
                } else if (assegnazione.classe == null) {

                    System.out.print("VUOTA");
                    minPostiLiberi = -1;
                } else {
                    found = true;
                    int postiLiberi = assegnazione.aula.capienza - assegnazione.classe.numerosita;
                    System.out.print(assegnazione.classe.classe + "[" + assegnazione.classe.numerosita + "] (+" + postiLiberi + ")");
                    minPostiLiberi = Math.min(minPostiLiberi, postiLiberi);
                }
            }
            if (found)
                utilizzate.add(aula.aula);
            System.out.print("\t" + minPostiLiberi);

            System.out.println();
        }

        System.out.print("CLASSI A CASA\t");
        for (int giorno = 0; giorno < aaa.size(); giorno++) {
            List<Classe202021> lib = cercaClassiACasa(aaa, giorno);
            for (Classe202021 x : lib) {
                System.out.print(" " + x.classe + "[" +
                        x.numerosita + "]");
            }
            System.out.print("\t");
        }


        System.out.println();
        System.out.println("CLASSI TOTALI: " + ClassData.values().length);
        System.out.println("AULE TOTALI: " + RoomData.values().length);
        System.out.println("AULE UTILIZZATE: " + utilizzate.size());
        System.out.println("AULE UTILIZZATE: " + utilizzate);
        System.out.println("AULE: " + Arrays.toString(RoomData.values()));

    }
}
