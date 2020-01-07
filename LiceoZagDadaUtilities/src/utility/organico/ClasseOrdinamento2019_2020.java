package utility.organico;

import java.util.*;

public class ClasseOrdinamento2019_2020 {
    public final Map<Integer, LinkedList<MateriaOraria2019_2020>> materiePerOra = new HashMap<>();

    public final LinkedList<MateriaOraria2019_2020> materiePerClasse(Classi2019_2020 c) {
        LinkedList<MateriaOraria2019_2020> ris = new LinkedList<>();
        for (LinkedList<MateriaOraria2019_2020> value : materiePerOra.values()) {
            for (MateriaOraria2019_2020 m : value) {
                if (m.classe == c) {
                    ris.add(m);
                }
            }
        }
        return ris;
    }


    public int totaliOre() {
        int t = 0;
        for (LinkedList<MateriaOraria2019_2020> v1 : materiePerOra.values()) {
            for (MateriaOraria2019_2020 v2 : v1) {
                t += v2.ore;
            }
        }

        return t;
    }

    public ClasseOrdinamento2019_2020(List<MateriaOraria2019_2020> ll) {
        for (MateriaOraria2019_2020 x : ll) {
            add(x);
        }
    }

    public int size() {
        return materiePerOra.size();
    }

    public void add(MateriaOraria2019_2020 x) {
        LinkedList<MateriaOraria2019_2020> rr = materiePerOra.get(x.ore);
        if (rr == null) {
            rr = new LinkedList<>();
            materiePerOra.put(x.ore, rr);
        }
        rr.add(x);
    }

    public Set<Integer> ore() {
        return materiePerOra.keySet();
    }

    public ArrayList<Integer> oreFinoA(int max) {
        ArrayList<Integer> ris = new ArrayList<>();
        for (Integer o : materiePerOra.keySet()) {
            if (o <= max) {
                ris.add(o);
            }
        }
        return ris;
    }

    public MateriaOraria2019_2020 use(int ore) {
        LinkedList<MateriaOraria2019_2020> m = materiePerOra.get(ore);
        if (m == null) return null;
        if (m.size() == 0) return null;
        MateriaOraria2019_2020 riss = m.removeFirst();
        if (m.size() == 0) {
            materiePerOra.remove(ore);
        }
        return riss;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, LinkedList<MateriaOraria2019_2020>> entry : materiePerOra.entrySet()) {
            sb.append("Ore:" + entry.getKey()).append("\n");
            sb.append("  (" + entry.getValue().size() + ") ->");
            for (MateriaOraria2019_2020 m : entry.getValue()) {
                sb.append(" " + m);
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    public static ArrayList<Cattedra2019_2020> compute(Materia2019_2020 materia) {
        //compone le cattedre riempiendo le ore in blocchi da 18 e minimizzando le cattedre incomplete
        Random r = new Random(0);
        System.out.println("MATERIA: " + materia);
        ArrayList<Cattedra2019_2020> cattedreMigliori = null;
        int nonCompletiMigliore = Integer.MAX_VALUE;
        int i = 0;
        for (i = 1; i <= 100000; i++) {
            if (i % 1000 == 0)
                System.out.print("*");
            ClasseOrdinamento2019_2020 o = new ClasseOrdinamento2019_2020(MonteOre2019_2020.oreList(materia));

            ArrayList<Cattedra2019_2020> cattedre = new ArrayList<>();
            Cattedra2019_2020 corrente = new Cattedra2019_2020();
            while (o.size() > 0) {
                ArrayList<Integer> ore = new ArrayList<>(o.oreFinoA(corrente.oreMancanti()));
                if (ore.size() == 0) {
                    cattedre.add(corrente);
                    corrente = new Cattedra2019_2020();
                    continue;
                }
                int numOreScelto = ore.get(r.nextInt(ore.size()));
                corrente.add(o.use(numOreScelto));
            }
            cattedre.add(corrente);

            int nonCompleti = 0;
            for (Cattedra2019_2020 z : cattedre) {
                if (!z.completa()) {
                    nonCompleti++;
                }
            }
            if (nonCompletiMigliore > nonCompleti) {
                cattedreMigliori = cattedre;
                nonCompletiMigliore = nonCompleti;
            }
            // System.out.println("Non completi= " + nonCompleti + " Completi= " + (cattedre.size() - nonCompleti));
            //System.out.println(cattedre);

        }

        System.out.println(i + " ");

        System.out.println("========================================== BEST");
        System.out.println("Non completi= " + nonCompletiMigliore + " Completi= " + (cattedreMigliori.size() - nonCompletiMigliore));
        Collections.sort(cattedreMigliori);
        System.out.println(cattedreMigliori);
        int tot = 0;
        for (Cattedra2019_2020 x : cattedreMigliori) {
            tot += x.numOreTotaliAssegnate;
        }
        System.out.println("Ore totali assegnate: " + tot);
        return cattedreMigliori;
    }


    public static void main(String[] args) {

    }

}
