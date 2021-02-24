package utility.consigliDiClasse;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.util.*;
@Deprecated
public class ConsigliDiClasseEngine {
    static boolean docentiInComune(TreeSet<String> d1, TreeSet<String> d2) {
        for (String s1 : d1) {
            if (d2.contains(s1)) return true;
        }
        return false;
    }

    public static Map<ClassData, Set<ClassData>> cercaClassiCompatibili(BitOrarioGrigliaOrario orario, ClassData... classi) {
        Map<ClassData, Set<ClassData>> ris = new TreeMap<>();
        for (ClassData c1 : classi) {
            ris.put(c1, new TreeSet<>());
            TreeSet<String> d1 = orario.getDocenti(c1);
            for (ClassData c2 : classi) {
                TreeSet<String> d2 = orario.getDocenti(c2);
                if (!docentiInComune(d1, d2)) {
                    ris.get(c1).add(c2);
                }
            }
        }

        return ris;
    }

    //
    public static ArrayList<ArrayList<ClassData>> cercaConsigli(BitOrarioGrigliaOrario orario, ClassData... classi) {
        DocentiComuniCdC c = new DocentiComuniCdC(orario);
        Random r = new Random(99);

        ArrayList<ArrayList<ClassData>> best = null;

        for (int tentativi = 0; tentativi < 1000; tentativi++) {

            LinkedList<ClassData> classiArray = new LinkedList<>(Arrays.asList(classi));
            ArrayList<ArrayList<ClassData>> soluzione = new ArrayList<>();
            while (classiArray.size() > 0) {
                ClassData classeDaAggiungere = classiArray.remove(r.nextInt(classiArray.size()));
                boolean trovato = false;
                for (ArrayList<ClassData> classData : soluzione) {
                    //aggiunge in coda
                    classData.add(classeDaAggiungere);
                    if (!c.docentiInComune(classData)) {
                        trovato = true;
                        break;
                    } else {
                        //rimuove l'ultima
                        classData.remove(classData.size() - 1);
                    }
                }
                if (!trovato) {
                    soluzione.add(new ArrayList<>(Collections.singletonList(classeDaAggiungere)));
                }
            }
            System.out.println("Tentativo " + (tentativi + 1) + ". SOLUZIONE:" + soluzione.size());
            if (best == null || best.size() > soluzione.size()) {
                best = soluzione;
            }

        }

        System.out.println("CONSIGLI");
        for (ArrayList<ClassData> row : best) {
            System.out.println(row);
        }
        System.out.println("============================");

        System.out.println("CONSIGLI + DOCENTI");
        for (ArrayList<ClassData> row : best) {

            System.out.println(row);
            for (ClassData classData : row) {
                System.out.println(" > " + classData + " " + orario.getDocenti(classData));
            }
        }

        return best;
    }
}
