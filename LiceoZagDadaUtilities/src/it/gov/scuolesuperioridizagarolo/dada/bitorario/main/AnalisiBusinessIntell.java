package it.gov.scuolesuperioridizagarolo.dada.bitorario.main;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by stefano on 11/04/2018.
 */
public class AnalisiBusinessIntell {
    public static void main(String[] args) throws IOException {
        final BitOrarioGrigliaOrario orarioTotale = MainParserGeneraStampeOrario.parsingDefaultFileOrarioAuleClassi(new File(MainParserGeneraStampeOrario.DEBUG_FOLDER_INPUT));
        final TreeSet<String> docenti = orarioTotale.getDocenti();
        final int size = docenti.size();
        final int sizeA = orarioTotale.getAule().size();
        System.out.println("Docenti: " + size);
        System.out.println("Aule: " + sizeA);
        ArrayList<Riepilogo> r = new ArrayList<>();

        for (String d : docenti) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int ore = 0;
            final ArrayList<BitOrarioOraLezione> lezioneConDocente = orarioTotale.getLezioneConDocente(d);
            for (BitOrarioOraLezione l : lezioneConDocente) {
                final ClassData classe = l.getClasse();
                if (classe != null) {
                    final ClassData aClass = l.getClasse();
                    min = Math.min(min, aClass.numberOfStudents);
                    max = Math.max(max, aClass.numberOfStudents);
                    if (!l.getAula().flagAulaLaboratorioPalestra())
                        ore++;
                }
            }

            r.add(new Riepilogo(d, min, max, ore));
            // System.out.println(d + "\t" + min + "\t" + max);
        }


        Collections.sort(r, new Comparator<Riepilogo>() {
            @Override
            public int compare(Riepilogo o1, Riepilogo o2) {

                int i0 = -Integer.valueOf(o1.ore).compareTo(o2.ore);
                if (i0 != 0) return i0;
                int i = Integer.valueOf(o1.diff()).compareTo(o2.diff());
                if (i != 0) return i;
                return Integer.valueOf(o1.maxStudenti).compareTo(o2.maxStudenti);
            }
        });

        for (Riepilogo x : r) {
            System.out.println(x);
        }

    }

    static class Riepilogo {
        final String docente;
        final int minStudenti;
        final int maxStudenti;
        private final int ore;

        Riepilogo(String docente, int minStudenti, int maxStudenti, int ore) {
            this.docente = docente;
            this.minStudenti = minStudenti;
            this.maxStudenti = maxStudenti;
            this.ore = ore;
        }

        public String toString() {
            return (String.format("%15s\t%d\t%d\tore:%d\tdiff:%d", docente, minStudenti, maxStudenti, ore, diff()));
        }

        int diff() {
            return maxStudenti - minStudenti;
        }
    }
}
