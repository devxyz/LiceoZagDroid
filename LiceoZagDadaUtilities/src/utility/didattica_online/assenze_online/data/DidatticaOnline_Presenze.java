package utility.didattica_online.assenze_online.data;

import utility.didattica_online.assenze_online.util.StringSimilarity;

import java.util.TreeSet;

public class DidatticaOnline_Presenze {
    public final TreeSet<DidatticaOnline_StudenteClasse> tutti = new TreeSet<>();
    public final TreeSet<DidatticaOnline_StudenteClasse> presenti = new TreeSet<>();


    public TreeSet<DidatticaOnline_StudenteClasse> getTutti() {
        return tutti;
    }

    public TreeSet<DidatticaOnline_StudenteClasse> getFuorilista() {
        TreeSet<DidatticaOnline_StudenteClasse> ris = new TreeSet<>();
        for (DidatticaOnline_StudenteClasse x : presenti) {
            if (!tutti.contains(x))
                ris.add(x);
        }
        return ris;
    }


    public TreeSet<DidatticaOnline_StudenteClasse> getPresenti() {
        return presenti;
    }

    public TreeSet<DidatticaOnline_StudenteClasse> getAssenti() {
        TreeSet<DidatticaOnline_StudenteClasse> ris = new TreeSet<>();
        for (DidatticaOnline_StudenteClasse x : tutti) {
            if (!presenti.contains(x))
                ris.add(x);
        }
        return ris;
    }

    public TreeSet<DidatticaOnline_StudenteClasse> getPresentiUsandoFuorilista() {
        TreeSet<DidatticaOnline_StudenteClasse> ris = new TreeSet<>(tutti);
        ris.removeAll(getAssentiUsandoFuorilista());
        return ris;
    }

    public TreeSet<DidatticaOnline_StudenteClasse> getAssentiUsandoFuorilista() {
        TreeSet<DidatticaOnline_StudenteClasse> ris = new TreeSet<>(tutti);

        for (DidatticaOnline_StudenteClasse s : presenti) {
            if (ris.contains(s))
                ris.remove(s);
            else {
                //cerca il piu' somigliante

                double sim = -1;
                DidatticaOnline_StudenteClasse best = null;
                for (DidatticaOnline_StudenteClasse cerca : ris) {
                    double ss = StringSimilarity.similarity(s.cognome, cerca.cognome) * StringSimilarity.similarity(s.nome, cerca.nome);
                    if (ss > sim) {
                        sim = ss;
                        best = cerca;
                    }
                }
                if(best==null){
                    System.out.println(ris);
                    throw new NullPointerException();
                }
                ris.remove(best);
            }
        }

        return ris;
    }


}
