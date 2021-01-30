package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario75percento_gennaio2021;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.util.*;
@Deprecated
public class _01_SimulazioneOrarioPresenza75percento_gennaio2021 {
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
        {
            System.out.println("SOLUZIONE SU 6 giorni ===============");
            int numgiorni = 6;
            Collection<ClassData[]> cc = new ArrayList<>();
            cc.add(ClassData.values());
            //cc.add(toArray(ClassData.searchPerAnnoCorso(1)));
            //cc.add(toArray(ClassData.searchPerAnnoCorso(2)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(3)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(4)));
            cc.add(toArray(ClassData.searchPerAnnoCorso(5)));

            doSomething(numgiorni, cc);
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

    private static void doSomething(int numgiorni, Collection<ClassData[]> cc) {
        ClassData[] classi = ClassData.values();

        Random r = new Random(13);

        int totStudenti = 0;
        for (ClassData c : classi) {
            totStudenti += c.numberOfStudents;
        }
        int presenza_75 = (int) (totStudenti * 0.75);
        int casa_75 = totStudenti - presenza_75;

        Map<ClassData, Integer> numLezioniInPresenzaPerClasse = new TreeMap<>();
        {
            for (ClassData c : classi) {
                numLezioniInPresenzaPerClasse.put(c, numgiorni);
            }
            for (ClassData[] x : cc) {
                for (ClassData c : x) {
                    Integer v = numLezioniInPresenzaPerClasse.get(c);
                    v = v - 1;
                    numLezioniInPresenzaPerClasse.put(c, v);
                }
            }
        }

        //RIPETE VARIE VOLTE...
        for (int i = 0; i < 1000000; i++) {
            System.out.println("==================================");
            System.out.printf("%4d - TENTATIVO", (i + 1));
            //shuffle
            LinkedList<ClassData> classiChePossonoStareAriposo = new LinkedList<>();
            //1 giorno per ogni classe
            for (ClassData[] classData : cc) {
                classiChePossonoStareAriposo.addAll(suffle(r, classData));
            }
            /*
            classiChePossonoStareAriposo.addAll(suffle(r, ClassData.values()));//2° giorno
            classiChePossonoStareAriposo.addAll(suffle(r, ClassData.searchPerAnnoCorso(2)));//2° giorno
            classiChePossonoStareAriposo.addAll(suffle(r, ClassData.searchPerAnnoCorso(3)));//2° giorno
            classiChePossonoStareAriposo.addAll(suffle(r, ClassData.searchPerAnnoCorso(4)));//2° giorno
            */


            //classi a riposo per ogni giorno
            TreeSet<ClassData>[] classiARiposo = new TreeSet[numgiorni];
            TreeSet<ClassData>[] classiARiposoExtra = new TreeSet[numgiorni];//riposo per le classi che rimangono non utilizzate

            int[] numStudentiAScuolaPerGiorno = new int[numgiorni];
            for (int giorno = 0; giorno < numgiorni; giorno++) {
                classiARiposo[giorno] = new TreeSet<>();
                classiARiposoExtra[giorno] = new TreeSet<>();
                int studentiAScuolaOggi = totStudenti;

                //tiene a casa le classi fino a raggiungere la quota del 75%
                while (studentiAScuolaOggi > presenza_75 && classiChePossonoStareAriposo.size() > 0) {
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
                    classiARiposoExtra[k % numgiorni].add(c);
                    k++;
                }
            }

            //verifica soluzione:
            boolean soluzione = numStudentiAScuolaPerGiorno[numgiorni - 1] < presenza_75;
            System.out.println(" > soluzione: " + soluzione);
            if (soluzione) {
                // System.out.println(" > classi sempre a scuola: " + classiChePossonoStareAriposo);
                System.out.println("Organizzazione DAD:");
                System.out.println(" Giorni apertura scuola:" + numgiorni);
                for (int j = 0; j < classiARiposo.length; j++) {
                    TreeSet<ClassData> classData = classiARiposo[j];
                    int somma = 0;
                    for (ClassData data : classiARiposoExtra[j]) {
                        somma += data.numberOfStudents;
                    }
                    System.out.printf("(%d)  > (%6.2f) %50s - extra %s\n", (j + 1), 100 * (numStudentiAScuolaPerGiorno[j] - (float) somma) / totStudenti, classData, classiARiposoExtra[j]);
                }


                Map<String, ArrayList<ClassData>> report = new TreeMap<>();
                Map<String, TreeSet<String>> report2 = new TreeMap<>();
                for (Map.Entry<ClassData, Integer> s : numLezioniInPresenzaPerClasse.entrySet()) {
                    String kk;
                    if (s.getValue() < 5) {
                        kk = "" + s.getValue();
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
                    System.out.println(e.getKey() + " in presenza su " + numgiorni + " totali" + "\t" + e.getValue());
                }

                System.out.println("Report giorni presenza/giorni totali per classe");
                for (Map.Entry<String, ArrayList<ClassData>> e : report.entrySet()) {
                    System.out.println(e.getKey() + " in presenza su " + numgiorni + " totali" + "\t" + e.getValue());
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
                break;
            } else {

            }
        }
    }


}
