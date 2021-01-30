package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.elezioni30ottobre;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class ElezioneGenitori {
    static class NomeCandidato {
        public final TreeSet<String> termini;
        public final String nomeOriginale;
        public final String classe;
        public final String note;

        @Override
        public String toString() {
            return "NomeCandidato{" +
                    "nome=" + termini +
                    ", nomeOriginale='" + nomeOriginale + '\'' +
                    '}';
        }


        public NomeCandidato(String s, String classe, String note) {
            this.note = note == null ? " " : note;
            s = s.trim();
            nomeOriginale = s;
            this.classe = classe;
            String[] split = s.replace("'", "").
                    replace("à", "a")
                    .replace("é", "e")
                    .replace("è", "e")
                    .replace("ì", "i")
                    .replace("ò", "o")
                    .replace("Ì", "i")
                    .toUpperCase()
                    .split("[ ]+");
            termini = new TreeSet<>(Arrays.asList(split));
        }
    }

    static ArrayList<NomeCandidato> parseCandidati() {
        ArrayList<NomeCandidato> ris = new ArrayList<>();
        String[] split = candidati.split("\n");
        for (String s : split) {
            String[] split1 = s.split("\t");

            //no candidati
            if (split1.length <= 1) continue;

            String classe = split1[0];

            String cc = split1[1];
            String[] candidati = cc.split(",");

            for (String c : candidati) {
                if (c.contains("*"))
                    ris.add(new NomeCandidato(c, classe, "Nome con errori"));
                else
                    ris.add(new NomeCandidato(c, classe, null));
            }

        }
        return ris;
    }

    static ArrayList<String> votati() throws IOException {
        File f = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/scuolesuperioridizagarolo/dada/bitorario/main/sostituzioni/as2020_21/elezioni30ottobre/votati.txt");
        ArrayList<String> ris = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(f));
        String s;
        while ((s = in.readLine()) != null) {
            ris.add(s);
        }
        in.close();
        return ris;
    }


    static ArrayList<NomeCandidato> parseVotati() throws IOException {
        ArrayList<NomeCandidato> ris = new ArrayList<>();
        for (String s : votati()) {
            s = s.trim();

            //no candidati
            if (s.length() == 0) continue;

            String[] candidati = s.split(",");
            int conta = 0;
            for (String c : candidati) {
                conta++;
                if (conta > 1)
                    ris.add(new NomeCandidato(c, null, "Voto multiplo (rispetto al precedente)"));
                else
                    ris.add(new NomeCandidato(c, null, null));
                //prende solo il primo voto

            }

        }
        return ris;
    }


    static String candidati =
            //extra
            "2F (Liceo Scientifico)\tCicerchia Claudia,Bukhanova Alona, Bukhanova Alonso *\n" +


                    "1A (IPIA)\tSacco Angela, Grossi Marco,\n" +
                    "1A (Liceo Scientifico)\tFlavia Saccomandi, Ernesto Fiasco\n" +
                    "1B (Liceo Scientifico)\tBisconti Ermida, Cerboni Alessandro, Migliaccio Daniela\n" +
                    "1C (Liceo Scientifico)\tSabrina Fatello,  Giovanna Del Moro\n" +
                    "1D (Liceo Scientifico)\tMonica Cenciotti,Monica Cecciotti *, \n" +
                    "1E (Liceo Scientifico)\tDella Valle Luciano, Dalla Valle Luciano *, Paolucci Tamara\n" +
                    "1F (Liceo Scientifico)\tCaico Teresa,Cairo Teresa *\n" +
                    "2A (IPIA)\tElena Orita, DOROTA NOWAK\n" +
                    "2A (Liceo Scientifico)\tVincenzo Galluccio, Sandra Della Portella, Roberta Santini\n" +
                    "2B (IPIA)\tStefania Stagliano', Nicoletta Di Berti \n" +
                    "2B (Liceo Scientifico)\tAnnamaria Rosicarelli, Silvia Fornari\n" +
                    "2C (Liceo Scientifico)\tMACARRA PIERA, IZZO GRAZIELLA, IZZO GABRIELLA *\n" +
                    "2D (Liceo Scientifico)\tCarmela Fatello, Francesca Nocera\n" +
                    "2E (Liceo Scientifico)\tLanzi Loredana, Ferrara Adelaide\n" +
                    "2G (Liceo Scientifico)\tDuca Sabrina, Mereu Barbara\n" +
                    "2H (Liceo Scientifico)\tOmbretta Brigida, Emanuela Trifogli\n" +
                    "3A (IPIA)\tnessun candidato\n" +
                    "3A (Liceo Scientifico)\tCuri Sandra, Celletti Valentina\n" +
                    "3B (Liceo Scientifico)\tSabrina Fatello, Rosalba Macchi\n" +
                    "3C (Liceo Scientifico)\tBISCONTI ERMIDA, VISCONTI ERMIDA *, CARRETTA SONIA, CARRETTA SONI *\n" +
                    "3D (Liceo Scientifico)\tDe Angelis Giovanna ,Loreti Fabiola,Loreta Fabiola *\n" +
                    "3E (Liceo Scientifico)\tClaudia Petrinca, Arvaria Prioreschi,Arvalia Prioreschi *,\n" +
                    "3F (Liceo Scientifico)\tCaccianini Celeste , D'Ambrosi Luisa\n" +
                    "3H (Liceo Scientifico)\tGalmi D'Offizi Stefania,Galmi d'offizzi stefania *\n" +
                    "4A (IPIA)\t \n" +
                    "4A (Liceo Scientifico)\tPasqualini Gianluca, Piras Tonino\n" +
                    "4B (Liceo Scientifico)\tSoldati Giampiera ,Soldati Gianpiera *,Ricci Elisa\n" +
                    "4C (Liceo Scientifico)\tCianfriglia Maria Grazia,Cianfriglia MariaGrazia,\n" +
                    "4D (Liceo Scientifico)\tFabrizia Bartoli , Angela Sacco\n" +
                    "4E (Liceo Scientifico)\t\n" +
                    "4F (Liceo Scientifico)\tDaniela Fabrianesi\n" +
                    "4G (Liceo Scientifico)\tHarta Havaraj , arta Havaraj *, Grazia Tassiello\n" +
                    "4H (Liceo Scientifico)\tChiapparelli Rita, Chiappareli Rita *, Ridori Sonia, Marziali Mara\n" +
                    "5A (IPIA)\tNowak Dorota\n" +
                    "5A (Liceo Scientifico)\tKatia Siciliano, Carla Di Pietro\n" +
                    "5B (Liceo Scientifico)\tVITTORIO MENASCI', MATILDE SAVIONI SERRA\n" +
                    "5C (Liceo Scientifico)\tStrino Paola, Zammarrelli Diego , Zamarrelli Diego * , Zammarelli Diego *\n" +
                    "5D (Liceo Scientifico)\tCALLEGARI EMANUELE\n" +
                    "5E (Liceo Scientifico)\tLulli Alessandro, Salvati Fabiola\n" +
                    "5F (Liceo Scientifico)\tPaola Vinattieri, Alessandro Cerboni\n" +
                    "5G (Liceo Scientifico)\tDidomenicantonio Romina, Pascolo Iolanda, Pascoli Iole";


    private static NomeCandidato cercaCandidatoEsatto(ArrayList<NomeCandidato> candidati, NomeCandidato voto) {
        for (NomeCandidato c : candidati) {
            if (c.termini.equals(voto.termini)) {
                return c;
            }
        }
        return null;
    }

    private static NomeCandidato cercaCandidatoSottoinsieme(ArrayList<NomeCandidato> candidati, NomeCandidato voto) {
        for (NomeCandidato c : candidati) {
            if (c.termini.containsAll(voto.termini)) {
                return c;
            }
        }
        return null;
    }


    public static void main(String[] args) throws IOException {
        final ArrayList<NomeCandidato> candidati = parseCandidati();
        for (NomeCandidato c : candidati) {
            System.out.println(c);
        }

        System.out.println();
        System.out.println();
        System.out.println();


        //controllo duplicati
        TreeSet<NomeCandidato> rr = new TreeSet<>(new Comparator<NomeCandidato>() {
            @Override
            public int compare(NomeCandidato o1, NomeCandidato o2) {
                return o1.termini.toString().compareTo(o2.toString());
            }
        });
        rr.addAll(candidati);


        System.out.println("Candidati= " + candidati.size());
        System.out.println("Candidati UNICI= " + rr.size());

        System.out.println("VOTATI:");
        ArrayList<NomeCandidato> _votati = parseVotati();
        System.out.println(_votati.size());
        System.out.println("RIGHE:");
        System.out.println(votati().size());

        //cerca per ogni voto il corrispondente candidato
        for (NomeCandidato v : _votati) {
            NomeCandidato matchEsatto = cercaCandidatoEsatto(candidati, v);
            if (matchEsatto != null) {
                System.out.println("MATCH ESATTO\t" + v.note + " " + matchEsatto.note + "\t" + v.nomeOriginale + "\t" + matchEsatto.nomeOriginale + "\t" + matchEsatto.classe + "");
                continue;
            }

            NomeCandidato matchSottoinsieme = cercaCandidatoSottoinsieme(candidati, v);
            if (matchSottoinsieme != null) {
                System.out.println("MATCH PARZIALE\t" + v.note + " " + matchSottoinsieme.note+"\t" + v.nomeOriginale + "\t" + matchSottoinsieme.nomeOriginale + "\t" + matchSottoinsieme.classe + "");
                continue;
            }

            System.out.println("NON TROVATO\t" + v.note + "\t" + v.nomeOriginale + " " + v.termini + " NON TROVATO  NON TROVATO  NON TROVATO  NON TROVATO ");

        }

    }
}
