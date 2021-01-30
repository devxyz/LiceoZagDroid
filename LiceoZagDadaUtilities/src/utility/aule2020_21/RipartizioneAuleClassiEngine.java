package utility.aule2020_21;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;
import utility.aule2020_21.data.RipartizioneAuleClassiData;
import utility.aule2020_21.data.RipartizioneAuleClassiData_03_7prime_senzaAulaExtra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class RipartizioneAuleClassiEngine {
    public static void main(String[] args) {
        RipartizioneAuleClassiData.selectDataSource(RipartizioneAuleClassiData_03_7prime_senzaAulaExtra.class);

        ArrayList<Aula202021> aule = RipartizioneAuleClassiData.singleton().aule();
        sortAuleCrescenti(aule);

        ArrayList<Classe202021> classi = RipartizioneAuleClassiData.singleton().classi();
        sortClassiCrescenti(classi);

        System.out.println(Aula202021.toString(aule));
        System.out.println(Classe202021.toString(classi));


        System.out.println();
        System.out.println("Assegnazione");

        ArrayList<Classe202021> classiNonAssegnate = new ArrayList<>();

        for (Classe202021 c : classi) {
            //cerca la prima aula disponibile:
            int i_trovato = -1;
            for (int i = 0; i < aule.size(); i++) {
                Aula202021 a = aule.get(i);
                if (a == null) continue;
                if (a.capienza >= c.numerosita) {
                    i_trovato = i;
                    break;
                }
            }

            if (i_trovato >= 0) {
                Aula202021 aula = aule.get(i_trovato);
                aule.remove(i_trovato);
                System.out.println(c + "\t" + aula);
            } else {
                System.out.println(c + "\t NON TROVATO");
                classiNonAssegnate.add(c);
            }
        }

        System.out.println("Aule inutilizzate:");
        for (Aula202021 a : aule) {
            if (a != null) {
                System.out.println(a);
            }
        }
    }

    public static void main2(String[] args) {

        ArrayList<Aula202021> aule = RipartizioneAuleClassiData.singleton().aule();
        ArrayList<Classe202021> classi = RipartizioneAuleClassiData.singleton().classi();
        AssegnazioneClasseAulaGiornaliera202021 assegnazioni = assegnaClassiAule(aule, classi, EGiorno.LUNEDI);

        System.out.println(assegnazioni);

    }

    public static AssegnazioneClasseAulaGiornaliera202021 assegnaClassiAule(
            ArrayList<Aula202021> aule,
            ArrayList<Classe202021> classi,
            EGiorno giorno) {

        sortAuleCrescenti(aule);
        sortClassiCrescenti(classi);


        ArrayList<AssegnazioneClasseAula202021> assegnazioni = new ArrayList<>();

        for (Classe202021 c : classi) {
            //cerca la prima aula disponibile:
            int i_trovato = -1;
            for (int i = 0; i < aule.size(); i++) {
                Aula202021 a = aule.get(i);
                if (a == null) continue;
                if (!accetta(c, a)) {
                    continue;
                }
                if (a.capienza >= c.numerosita) {
                    i_trovato = i;
                    break;
                }
            }

            if (i_trovato >= 0) {
                Aula202021 aula = aule.get(i_trovato);
                aule.remove(i_trovato);
                assegnazioni.add(new AssegnazioneClasseAula202021(c, aula, giorno));
            } else {
                assegnazioni.add(new AssegnazioneClasseAula202021(c, null, giorno));
            }
        }

        //aggiunge le aule inutilizzate (se con capienza >0)
        for (Aula202021 a : aule) {
            if (a.capienza > 0) {
                assegnazioni.add(new AssegnazioneClasseAula202021(null, a, giorno));
            }
        }
        return new AssegnazioneClasseAulaGiornaliera202021(assegnazioni);
    }

    //assegna le aule casualmente
    public static AssegnazioneClasseAulaGiornaliera202021 assegnaClassiAuleCasuale(
            Random r,
            ArrayList<Aula202021> aule,
            ArrayList<Classe202021> classi,
            EGiorno giorno,
            int tentativi
    ) {


        ArrayList<AssegnazioneClasseAula202021> assegnazioni = new ArrayList<>();

        //fa mille tentativi
        boolean soluzioneTrovata = false;
        for (int j = 0; j < tentativi && !soluzioneTrovata; j++) {

            Collections.shuffle(aule, r);
            Collections.shuffle(classi, r);

            soluzioneTrovata = true;
            for (Classe202021 c : classi) {

                //cerca la prima aula disponibile:
                if (aule.size() == 0) return null;

                int count = 0;
                Aula202021 a;
                do {
                    a = aule.get(r.nextInt(aule.size()));
                    if (!accetta(c, a)) {
                        a = null;
                    }
                    count++;
                } while (a == null && count < aule.size() * 2);


                if (a != null) {
                    aule.remove(a);
                    assegnazioni.add(new AssegnazioneClasseAula202021(c, a, giorno));
                } else {
                    soluzioneTrovata = false;
                    assegnazioni.add(new AssegnazioneClasseAula202021(c, null, giorno));
                    break;
                }
            }
        }
        if (!soluzioneTrovata) return null;

        //aggiunge le aule inutilizzate (se con capienza >0)
        for (Aula202021 a : aule) {
            if (a.capienza > 0) {
                assegnazioni.add(new AssegnazioneClasseAula202021(null, a, giorno));
            }
        }
        return new AssegnazioneClasseAulaGiornaliera202021(assegnazioni);
    }

    private static boolean accetta(Classe202021 c, Aula202021 a) {
        RoomData roomData = a.toRoomData();
        ClassData classData = c.toClassData();

        if (roomData.maxStudents < classData.numberOfStudents) return false;

        if (roomData == RoomData.F2) {

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

        if (classData._section.equals("D") ||
                (classData._section.equals("B")) ||
                (classData._section.equals("F")) ||
                (classData._section.equals("H"))) {
            //if (roomData.location == ERoomArea.AREA_D) return false;
            //if (roomData.location == ERoomArea.AREA_F) return false;
            //if (roomData.location == ERoomArea.AREA_A) return false;
        }

        return true;
    }

    public static int assegnati(ArrayList<AssegnazioneClasseAula202021> assegnazioni) {
        int conta = 0;
        for (AssegnazioneClasseAula202021 a : assegnazioni) {
            if (a.aula != null && a.classe != null) conta++;
        }
        return conta;
    }

    public static String toStringAssegnazioni(ArrayList<AssegnazioneClasseAula202021> assegnazioni) {
        StringBuilder sb = new StringBuilder();
        for (AssegnazioneClasseAula202021 a : assegnazioni) {
            sb.append(a.toString()).append("\n");
        }

        return sb.toString();
    }

    public static int nonAssegnati(ArrayList<AssegnazioneClasseAula202021> assegnazioni) {
        int conta = 0;
        for (AssegnazioneClasseAula202021 a : assegnazioni) {
            if (a.aula == null && a.classe != null) conta++;
        }
        return conta;
    }

    public static void sortAuleCrescenti(ArrayList<Aula202021> aule) {
        Collections.sort(aule, Comparator.comparing(Aula202021::getCapienza).thenComparing(Aula202021::getAula));
    }

    public static void sortAuleCrescentiRandom(ArrayList<Aula202021> aule) {
        Collections.sort(aule, Comparator.comparing(Aula202021::getCapienza)
                .thenComparing(Aula202021::getIdCasuale)
                .thenComparing(Aula202021::getAula))
        ;
    }

    public static void sortClassiCrescenti(ArrayList<Classe202021> aule) {
        Collections.sort(aule, Comparator.comparing(Classe202021::getNumerosita).thenComparing(Classe202021::getClasse));
    }
}
