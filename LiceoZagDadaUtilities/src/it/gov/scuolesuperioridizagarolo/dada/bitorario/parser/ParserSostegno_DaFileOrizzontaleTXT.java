package it.gov.scuolesuperioridizagarolo.dada.bitorario.parser;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefano on 27/09/2017.
 */
public class ParserSostegno_DaFileOrizzontaleTXT {
    public static void parsingOreSostegnoDocenti(File fileDisposizioniDocenti, BitOrarioGrigliaOrario orario) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileDisposizioniDocenti));
        String line;


        ArrayList<BitOrarioOraLezione> ris = new ArrayList<>();
        ArrayList<ArrayList<String[]>> tot = new ArrayList<>();
        ArrayList<String[]> testoFormattato = new ArrayList<>();

        while ((line = in.readLine()) != null) {
            if (line.trim().startsWith("----")) continue;
            if (line.trim().length() == 0) continue;
            if (line.contains("@0DOCENTE")) {
                if (testoFormattato.size() > 0) {
                    tot.add(testoFormattato);
                }
                testoFormattato = new ArrayList<>();
            } else {
                final String[] split = line.split("[|]");
                final String[] split2 = new String[split.length];
                for (int i = 0; i < split.length; i++) {
                    split2[i] = split[i].trim();
                }
                testoFormattato.add(split2);
            }
        }
        if (testoFormattato.size() > 0) {
            tot.add(testoFormattato);
        }
/*
        for (ArrayList<String[]> x : tot) {
            for (String[] strings : x) {
                System.out.println(Arrays.toString(strings) + " ");
            }
            System.out.println();
        }
*/

        //analizza i docenti
        for (ArrayList<String[]> docente : tot) {
            String nomeDocente = docente.get(0)[0];
            if (nomeDocente.trim().length() == 0) continue;

            //System.out.println(nomeDocente);
            EOra o = EOra.PRIMA;
            for (String[] r : docente) {

                EGiorno g = EGiorno.LUNEDI;
                for (int i = 2; i < r.length; i++) {
                    String s = r[i].trim();
                    if (s.equalsIgnoreCase("D")) {
                        BitOrarioOraLezione nuovaLezione = BitOrarioOraLezione.creaOraDisposizione(nomeDocente, o, g);
                        orario.addLezione(nuovaLezione);

                    }
                    else if (s.length() > 0) {
                        ClassData classe = ClassData.search(s);
                        BitOrarioOraLezione lezioneInClasse = orario.getLezioneInClasse(o, g, classe);
                        if (lezioneInClasse == null) {
                            throw new IllegalArgumentException("Manca lezione " + classe + " " + g + " " + o);
                        }
                        if (lezioneInClasse.getDocenteSostegno() != null) {
                            throw new IllegalArgumentException("Insegnante di sostegno doppio per lezione " + classe + " " + g + " " + o + " " + lezioneInClasse);
                        }
                        orario.removeLezione(lezioneInClasse);
                        BitOrarioOraLezione nuovaLezione = BitOrarioOraLezione.aggiungiInsegnanteSostegno(lezioneInClasse, nomeDocente);
                        orario.addLezione(nuovaLezione);

                    } else {

                    }
                    g = g.next();
                }
                o = o.next();

            }
        }


    }

}
