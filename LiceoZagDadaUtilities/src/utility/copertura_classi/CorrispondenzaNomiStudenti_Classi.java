package utility.copertura_classi;

import utility.scrutini.engine.util.LevenghsteinDistance;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class CorrispondenzaNomiStudenti_Classi {
    static String s1 = "PAPUC\t2a\n" +
            "Piscopo\t2a\n" +
            "Scacco\t2a\n" +
            "Begaj\t2A\n" +
            "RUSSO\t2A\n" +
            "trovo\t2a\n" +
            "tamburrini\t2a\n" +
            "Dervishaliaj\t2a\n" +
            "DIGIARO\t2a\n" +
            "piccolo\t2a\n" +
            "Piretti\t2a\n" +
            "conti\t2a\n" +
            "Ionascu\t2a\n" +
            "petrinca\t2a\n" +
            "Bascandura\t2b\n" +
            "Cristofari\t2b\n" +
            "Scaramella\t2b\n" +
            "sgro\t2b\n" +
            "federici\t2b\n" +
            "nibile\t2b\n" +
            "mistura\t2b\n" +
            "mancinotti\t2b\n" +
            "Carbone Andrea\t2b\n" +
            "Carbone Matteo\t2b\n" +
            "esposito\t2b\n" +
            "chiapparelli simone\t2b\n" +
            "zimpi\t2b\n" +
            "foschi\t2b\n" +
            "tamiro\t2b\n" +
            "lucarelli\t2b\n" +
            "Michelessi\t2b\n" +
            "leone\t2b\n" +
            "boccia\t2b\n" +
            "basto\t2b\n" +
            "Giordano\t4d\n" +
            "D'Alessandro\t4d\n" +
            "Nicolaus\t4d\n" +
            "Marignoli\t4d\n" +
            "glielmi\t4d\n" +
            "frusta\t4d\n" +
            "delle fratte\t4d\n" +
            "Giuliani\t4d\n" +
            "Beltrami\t4d\n" +
            "cicerchia\t4d\n" +
            "gaetano\t4d\n" +
            "ferracci\t4d\n" +
            "wehbe\t4d\n" +
            "pop\t4d\n" +
            "mastrofini\t4d\n" +
            "giuliani\t4d\n" +
            "donca\t4d\n" +
            "romagnoli\t4d\n" +
            "bonafede\t4d\n" +
            "GHEORGHIU\t4d\n" +
            "correnti\t4d\n" +
            "marcelli\t2b\n" +
            "curzi\t4d\n" +
            "ascenzi\t4d\n" +
            "chiapparelli paolo\t2b";
    static String s2 =
            "[01] Begaj Marko\t2a\n" +
                    "[02] Conti Veronica\t2a\n" +
                    "[03] Cusano Dalila\t2a\n" +
                    "[04] Danaj Matilda\t2a\n" +
                    "[05] Dervishaliaj Isabela\t2a\n" +
                    "[06] Digiaro Arianna\t2a\n" +
                    "[07] Galluccio Ludovica\t2a\n" +
                    "[08] Ionascu Valentina\t2a\n" +
                    "[09] Melli Aurora\t2a\n" +
                    "[10] Papuc Alessio Gabriele\t2a\n" +
                    "[11] Petrinca Alessandro\t2a\n" +
                    "[12] Piretti Davide\t2a\n" +
                    "[13] Piscopo Angela\t2a\n" +
                    "[14] Russo Lorenzo\t2a\n" +
                    "[15] Scacco Alessandro\t2a\n" +
                    "[16] Tamburrini Camilla\t2a\n" +
                    "[17] Tassa Paolo\t2a\n" +
                    "[18] Trovo' Valeria\t2a\n" +
                    "[19] Verzino Alice\t2a\n" +
                    "[21] Piccolo Rocco\t2a\n" +
                    "[01] Bascandura Dennis Andrei\t2b\n" +
                    "[02] Basto Martina\t2b\n" +
                    "[03] Boccia Gabriele\t2b\n" +
                    "[04] Carbone Andrea\t2b\n" +
                    "[05] Carbone Matteo\t2b\n" +
                    "[06] Chiapparelli Paolo\t2b\n" +
                    "[07] Chiapparelli Simone\t2b\n" +
                    "[08] Cristofari Alessandro\t2b\n" +
                    "[09] Danaj Mateo\t2b\n" +
                    "[10] Esposito Alessio\t2b\n" +
                    "[11] Federici Gianluca\t2b\n" +
                    "[12] Foschi Francesco\t2b\n" +
                    "[13] Leone Francesca\t2b\n" +
                    "[14] Lucarelli Gabriele\t2b\n" +
                    "[15] Mammetti Filippo\t2b\n" +
                    "[16] Mancinotti Christian\t2b\n" +
                    "[17] Marcelli Alessandro\t2b\n" +
                    "[18] Michelessi Francesco\t2b\n" +
                    "[19] Mistura Diego\t2b\n" +
                    "[20] Nobile Luca\t2b\n" +
                    "[21] Orsi Angelica\t2b\n" +
                    "[22] Penna Isabel\t2b\n" +
                    "[23] Sabellico Giovanna\t2b\n" +
                    "[25] Scaramella Federico\t2b\n" +
                    "[26] Sgro' Lorenzo\t2b\n" +
                    "[27] Tamiro Federico\t2b\n" +
                    "[28] Zimpi Tommaso\t2b\n" +
                    "[01] Ascenzi Gianmarco\t4d\n" +
                    "[02] Beltrami Lorenzo\t4d\n" +
                    "[03] Bitsch Flaviano\t4d\n" +
                    "[04] Bonafede Giuseppe\t4d\n" +
                    "[05] Cicerchia Nicolas\t4d\n" +
                    "[06] Correnti Asia\t4d\n" +
                    "[07] Curzi Nicolo'\t4d\n" +
                    "[08] D'Alessandro Antonio\t4d\n" +
                    "[09] Delle Fratte Flavio\t4d\n" +
                    "[10] Di Carlo Francesca\t4d\n" +
                    "[11] Donca Andrea Denisa\t4d\n" +
                    "[12] Ferracci Ludovica\t4d\n" +
                    "[13] Frusta Alessandro\t4d\n" +
                    "[14] Gaetano Davide\t4d\n" +
                    "[15] Gheorghiu Marco Davide\t4d\n" +
                    "[16] Giordano Matteo\t4d\n" +
                    "[17] Giuliani Federica\t4d\n" +
                    "[18] Glielmi Mattia\t4d\n" +
                    "[19] Ligori Paolo\t4d\n" +
                    "[20] Marignoli Mario\t4d\n" +
                    "[21] Mastrofini Valerio\t4d\n" +
                    "[22] Nicolaus Leonardo\t4d\n" +
                    "[23] Pop Maximilian Ioan\t4d\n" +
                    "[24] Romagnoli Nicolo'\t4d\n" +
                    "[25] Schioppa Matteo\t4d\n" +
                    "[26] Wehbe Latifa\t4d";


    public static void main(String[] args) {
        String[] n1 = s1.toUpperCase().split("\n");
        String[] n2 = s2.toUpperCase().split("\n");
        Set<String> set1 = new TreeSet<>(Arrays.asList(n1));
        Set<String> set2 = new TreeSet<>(Arrays.asList(n2));
        Set<String> comuni = new TreeSet<>();


        //controllo uguaglianza nomi
        for (int x = 0; x <= 1; x++) {

            for (Iterator<String> it1 = set1.iterator(); it1.hasNext(); ) {
                String s1 = it1.next();
                if (s1.trim().length() == 0) continue;
                String[] split1 = s1.split("\t");
                String nome1 = split1[0].trim();
                String classe1 = split1[1].trim();


                String bestS2 = null;
                if (x == 0) {
                    //match esatto
                    for (String s2 : set2) {
                        if (s2.trim().length() == 0) continue;
                        String[] split2 = s2.split("\t");
                        String nome2 = split2[0].substring(4).trim();
                        String classe2 = split2[1].trim();

                        if (classe1.equals(classe2) &&
                                (nome2.startsWith(nome1) || nome1.startsWith(nome2))
                        ) {
                            bestS2 = s2;
                            break;
                        }
                    }
                }


                if (x == 1) {
                    //match similitudine
                    int bestS2distance = 1000000000;
                    for (String s2 : set2) {
                        if (s2.trim().length() == 0) continue;
                        String[] split2 = s2.split("\t");
                        String nome2 = split2[0].substring(4).trim();
                        String classe2 = split2[1].trim();

                        if (classe1.equals(classe2)) {
                            int i = LevenghsteinDistance.calculate(nome1, nome2);
                            if (i < bestS2distance) {
                                bestS2distance = i;
                                bestS2 = s2;
                            }
                        }
                    }
                    bestS2=bestS2;
                }

                if (bestS2 != null) {
                    comuni.add(s1 + " \t ==== \t " + bestS2 + "\tFASE " + x);
                    it1.remove();
                    set2.remove(bestS2);
                }
            }
        }


        System.out.println("COMUNI");
        for (String s : comuni) {
            System.out.println(s);
        }
        System.out.println();

        System.out.println("RIMASTI FUORI DA S1");
        for (String s : set1) {
            System.out.println(s);
        }
        System.out.println();

        System.out.println("RIMASTI FUORI DA S2");
        for (String s : set2) {
            System.out.println(s);
        }


    }
}
