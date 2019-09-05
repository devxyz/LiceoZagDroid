package utility.scrutini.engine.util;

import utility.scrutini.engine.data.ScrutiniCarenze_Termine;

import java.util.*;

public class ScrutiniCarenze_MergeTermini {
    private static double minDistanzaMerge = 12;
    private static double maxAltezzaMinRigheY = 12;

    public static ArrayList<ArrayList<ScrutiniCarenze_Termine>> disponiInGriglia(ArrayList<ScrutiniCarenze_Termine> termini, double minDistanzaMerge, double maxAltezzaMinRigheY) {
        ArrayList<ScrutiniCarenze_Termine> termini2 = new ArrayList<>(termini);
        _mergeTerminiViciniGruppi(termini2, minDistanzaMerge);
        return _posizioneInGriglia(termini2, maxAltezzaMinRigheY);

    }

    public static ArrayList<ArrayList<ScrutiniCarenze_Termine>> disponiInGriglia(ArrayList<ScrutiniCarenze_Termine> termini) {
        return disponiInGriglia(termini, minDistanzaMerge, maxAltezzaMinRigheY);
    }

    private static double distanzaMinima(ArrayList<ScrutiniCarenze_Termine> gruppo, ScrutiniCarenze_Termine termine) {
        double min = Integer.MAX_VALUE;
        if (gruppo.size() == 0) throw new IllegalArgumentException("GRUPPO VUOTO");
        for (ScrutiniCarenze_Termine x : gruppo) {
            double d = termine.distanza(x);
            min = Math.min(min, d);
        }
        return min;
    }

    private static void _mergeTerminiViciniGruppi(ArrayList<ScrutiniCarenze_Termine> termini, final double minDistanza) {
        //elenco termini da lavorare
        LinkedList<ScrutiniCarenze_Termine> daLavorare = new LinkedList<>(termini);
        ArrayList<ScrutiniCarenze_Termine> gruppo = new ArrayList<>();

        //in termini metto i termini aggregati
        termini.clear();

        while (daLavorare.size() > 0) {

            //creo un gruppo il piu' grande possibile attorno al primo valore della lista
            gruppo.add(daLavorare.removeFirst());

            boolean aggiuntoAlmenoUnElemento;
            do {
                aggiuntoAlmenoUnElemento = false;
                for (Iterator<ScrutiniCarenze_Termine> iterator = daLavorare.iterator(); iterator.hasNext(); ) {
                    ScrutiniCarenze_Termine termine = iterator.next();
                    if (distanzaMinima(gruppo, termine) <= minDistanza) {
                        aggiuntoAlmenoUnElemento = true;
                        iterator.remove();
                        gruppo.add(termine);
                    }
                }
            } while (daLavorare.size() > 0 && aggiuntoAlmenoUnElemento);

            sortYX(gruppo);
            termini.add(ScrutiniCarenze_Termine.unione(gruppo));
            gruppo.clear();

        }
       // System.out.println("FINE");
    }


    private static void sortYX(ArrayList<ScrutiniCarenze_Termine> termini2) {
        //ordina in se
        Collections.sort(termini2, new Comparator<ScrutiniCarenze_Termine>() {
            @Override
            public int compare(ScrutiniCarenze_Termine o1, ScrutiniCarenze_Termine o2) {
                int x = Double.compare(o1.avgY, o2.avgY);
                if (x != 0) return x;
                return Double.compare(o1.avgX, o2.avgX);
            }
        });
    }

    //posiziona in griglia i termini considerando i valori con un minDeltaY nella stessa riga
    private static ArrayList<ArrayList<ScrutiniCarenze_Termine>> _posizioneInGriglia(ArrayList<ScrutiniCarenze_Termine> termini, final double minDeltaY) {
        //ordina in se
        sortYX(termini);

        ArrayList<ArrayList<ScrutiniCarenze_Termine>> ris = new ArrayList<>();
        if (termini.size() == 0) return ris;
        ris.add(new ArrayList<>());
        ScrutiniCarenze_Termine prec = termini.get(0);
        ris.get(ris.size() - 1).add(prec);

        for (int i = 1; i < termini.size(); i++) {
            ScrutiniCarenze_Termine t = termini.get(i);
            if (Math.abs(t.avgY - prec.avgY) > minDeltaY) {
                ris.add(new ArrayList<>());
            }
            ris.get(ris.size() - 1).add(t);
            prec = t;
        }

        //ordina in base alla X
        for (ArrayList<ScrutiniCarenze_Termine> riga : ris) {
            sortX(riga);

        }


        return ris;

    }

    private static void sortX(ArrayList<ScrutiniCarenze_Termine> riga) {
        Collections.sort(riga, new Comparator<ScrutiniCarenze_Termine>() {
            @Override
            public int compare(ScrutiniCarenze_Termine o1, ScrutiniCarenze_Termine o2) {
                return Double.compare(o1.avgX, o2.avgX);
            }
        });
    }


}
