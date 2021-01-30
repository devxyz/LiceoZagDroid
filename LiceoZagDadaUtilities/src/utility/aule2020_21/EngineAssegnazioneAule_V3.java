package utility.aule2020_21;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;
import utility.aule2020_21.misura_ottimo_soluzione.AssegnazioneClasseAulaGiornaliera202021_MisuraPeso;
import utility.aule2020_21.misura_ottimo_soluzione.AssegnazioneClasseAulaGiornaliera202021_MisuraPeso_classiScienzeAppPiano1;

import java.io.PrintStream;
import java.util.*;

public class EngineAssegnazioneAule_V3 {

    static final boolean PRINT1 = false;
    static final boolean PRINT2 = true;
    static final boolean PRINT3 = false;


    public static void main(String[] args) {
        String[] classiS = ("1A\n" +
                "1C\n" +
                "1F\n" +
                "2D\n" +
                "2H\n" +
                "3A\n" +
                "3B\n" +
                "3F\n" +
                "4C\n" +
                "4E\n" +
                "4F\n" +
                "4G\n" +
                "5B\n" +
                "5C\n" +
                "5D\n" +
                "5F\n" +
                "5G").split("\n");


        EGiorno giorno = EGiorno.GIOVEDI;
        int num_tentativi = 1000;
        ArrayList<ClassData> classiInPresenza = new ArrayList<>(Arrays.asList());
        for (String s : classiS) {
            classiInPresenza.add(ClassData.search(s));
        }
        Set<RoomData> auleNonUtilizzabili = new TreeSet<>();
        auleNonUtilizzabili.addAll(RoomData.filter(new RoomData.RoomDataFilter() {
            @Override
            public boolean accept(RoomData c) {
                if (c == RoomData.B7) return false;
                if (c == RoomData.B6) return false;
                if (c == RoomData.B5) return false;
                if (c == RoomData.B4) return false;
                if (c == RoomData.B3) return false;
                if (c.location == ERoomArea.AREA_F) return true;
                if (c.location == ERoomArea.AREA_D) return true;
                if (c.location == ERoomArea.AREA_A) return true;
                if (c.location == ERoomArea.AREA_B) return true;
                return false;
            }
        }));
        //=====================
        //=====================
        //=====================
        Random r = new Random(13);


        ArrayList<AssegnazioneClasseAulaGiornaliera202021> assegnazioneMigliore = calcolaAssegnazioneGiornaliera(giorno, num_tentativi, classiInPresenza, auleNonUtilizzabili, r);


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        if (assegnazioneMigliore != null) {
            ArrayList<Classe202021> classi = new ArrayList<>();
            for (ClassData c : classiInPresenza) {
                classi.add(new Classe202021(c));
            }

            printAssegnazioneSettimanaPerClasse(classi, assegnazioneMigliore);
            ArrayList<Aula202021> aule = new ArrayList<>();
            for (RoomData rr : RoomData.values()) {
                aule.add(new Aula202021(rr));
            }
            //printAssegnazioneSettimanaPerAula(aule, assegnazioneMigliore);


            System.out.println("----- SOLUZIONE TROVATA!!");
        } else
            System.out.println("----- SOLUZIONE NON TROVATA!!");

    }

    public static ArrayList<AssegnazioneClasseAulaGiornaliera202021> calcolaAssegnazioneSettimanale(
            int num_tentativi,
            Map<EGiorno, ArrayList<ClassData>> classiInPresenza,
            Map<EGiorno, Set<RoomData>> auleNonUtilizzabili,
            Random r) {

        Set<EGiorno> giorni = new TreeSet<>();
        giorni.addAll(classiInPresenza.keySet());
        giorni.addAll(auleNonUtilizzabili.keySet());

        ArrayList<AssegnazioneClasseAulaGiornaliera202021> ris = new ArrayList<>();

        for (EGiorno g : giorni) {
            ArrayList<ClassData> cp = classiInPresenza.get(g);
            if (cp == null) cp = new ArrayList<>();

            Set<RoomData> au = auleNonUtilizzabili.get(g);
            if (au == null) au = new TreeSet<>();
            ArrayList<AssegnazioneClasseAulaGiornaliera202021> a = calcolaAssegnazioneGiornaliera(g, num_tentativi, cp, au, r);
            if (a == null) {
                throw new IllegalArgumentException("Assegnazione non trovata per " + g);
                //return null;
            }
            ris.addAll(a);
        }
        return ris;
    }

    public static ArrayList<AssegnazioneClasseAulaGiornaliera202021> calcolaAssegnazioneSettimanale_resetSeed(
            int num_tentativi,
            Map<EGiorno, ArrayList<ClassData>> classiInPresenza,
            Map<EGiorno, Set<RoomData>> auleNonUtilizzabili,
            int random_seed) {

        Set<EGiorno> giorni = new TreeSet<>();
        giorni.addAll(classiInPresenza.keySet());
        giorni.addAll(auleNonUtilizzabili.keySet());

        ArrayList<AssegnazioneClasseAulaGiornaliera202021> ris = new ArrayList<>();

        for (EGiorno g : giorni) {
            Random r = new Random(random_seed);
            ArrayList<ClassData> cp = classiInPresenza.get(g);
            if (cp == null) cp = new ArrayList<>();

            Set<RoomData> au = auleNonUtilizzabili.get(g);
            if (au == null) au = new TreeSet<>();
            ArrayList<AssegnazioneClasseAulaGiornaliera202021> a = calcolaAssegnazioneGiornaliera(g, num_tentativi, cp, au, r);
            if (a == null) {
                throw new IllegalArgumentException("Assegnazione non trovata per " + g);
                //return null;
            }
            ris.addAll(a);
        }
        return ris;
    }

    public static ArrayList<AssegnazioneClasseAulaGiornaliera202021> calcolaAssegnazioneGiornaliera(EGiorno giorno, int num_tentativi, ArrayList<ClassData> classiInPresenza, Set<RoomData> auleNonUtilizzabili, Random r) {
        ArrayList<AssegnazioneClasseAulaGiornaliera202021> assegnazioneMigliore = null;
        boolean soluzioneTrovata;
        int pesoOttimo = Integer.MIN_VALUE;

        for (int i = 0; i < num_tentativi; i++) {
            System.out.println("Test n." + i);

            ArrayList<Classe202021> classi = new ArrayList<>();
            for (ClassData c : classiInPresenza) {
                classi.add(new Classe202021(c));
            }

            //assegna le aule per ogni giorno
            int totNonAssegnate = 0;
            ArrayList<AssegnazioneClasseAulaGiornaliera202021> assegnazioniSettimana = new ArrayList<>();
            {
                ArrayList<Classe202021> presente = new ArrayList<>();
                for (ClassData c : classiInPresenza) {
                    presente.add(new Classe202021(c));
                }

                ArrayList<Aula202021> aule = new ArrayList<>();
                for (RoomData room : RoomData.values()) {
                    if (auleNonUtilizzabili.contains(room))
                        continue;
                    if (room.isAulaFittizia())
                        continue;
                    aule.add(new Aula202021(room));

                }


                AssegnazioneClasseAulaGiornaliera202021 assegnazioni = RipartizioneAuleClassiEngine.assegnaClassiAuleCasuale(r, aule, presente, giorno, Math.max(1000, num_tentativi));
                if (assegnazioni == null)
                    continue;
                int nonAssegnate = (assegnazioni.assegnazioniNonEffettuate().size());
                totNonAssegnate += nonAssegnate;
                if (PRINT1) {
                    System.out.println("  GIORNO:" + giorno);
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
            System.out.println("Peso migliore: " + pesoOttimo);
        }
        return assegnazioneMigliore;
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
