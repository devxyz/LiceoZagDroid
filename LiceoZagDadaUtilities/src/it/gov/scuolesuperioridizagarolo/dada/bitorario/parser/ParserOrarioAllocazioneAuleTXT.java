package it.gov.scuolesuperioridizagarolo.dada.bitorario.parser;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stefano on 27/09/2017.
 */
public class ParserOrarioAllocazioneAuleTXT {

    private static final String SEPARATORE_INTERNO = "@";

    public static List<BitOrarioOraLezione> parsingFileOrarioAuleClassi(File fileAllocazioneAule, boolean noAule) throws IOException {

        List<BitOrarioOraLezione> ris = new ArrayList<>();

        BufferedReader in = new BufferedReader(new FileReader(fileAllocazioneAule));
        String line;


        String classe = null;
        MatriceDinamicaStringhe orario = new MatriceDinamicaStringhe();
        int riga = 0;


        while ((line = in.readLine()) != null) {
            line = line.trim();

            //se ci sono aule non assegnate, salta il resto del file
            if (line.startsWith("|(non assegnato)     |Giorno              |       Ora|DOCENTE             |")) {
                break;
            }

            if (line.startsWith("=====")) {
                riga++;
                continue;
            }
            if (line.length() == 0)
                continue;
            if (line.startsWith("----"))
                continue;
            if (line.contains("intervallo")) {
                in.readLine();//salta la riga successiva
                continue;
            }
            if (line.startsWith("Ora|"))
                continue;
            if (line.contains("|CLASSE")) {
                if (classe != null) {
                    aggiungiClasse(classe, orario, ris, noAule);
                }

                orario = new MatriceDinamicaStringhe();
                riga = 0;
                classe = line.split("[ ]+")[2];

                continue;
            }

            final String[] a = line.split("[|]+");
            for (int i = 0; i < a.length; i++) {
                orario.append(riga, i, a[i].trim() + SEPARATORE_INTERNO);
            }
        }


        if (classe != null) {
            aggiungiClasse(classe, orario, ris, noAule);
        }

        return ris;
    }

    private static void aggiungiClasse(String classe, MatriceDinamicaStringhe orario, List<BitOrarioOraLezione> orarioTotale, boolean noAula) {
        //System.out.println("=======================================");
        //System.out.println("CLASSE " + classe);
        //System.out.println(orario);
        for (int ora = 1; ora < orario.getRowCount(); ora++) {
            for (int giorno = 1; giorno < orario.getColumnCount(); giorno++) {


                final String[] a = separaStringhe(orario, ora, giorno);


                final BitOrarioOraLezione l;

                String docentePrincipale;
                String materiaPrincipale;
                String nomeAula;

                final EOra[] values = EOra.valuesOreDiLezione();
                /*if (ora>=values.length)
                    continue;*/
                EOra oraX = values[ora - 1];
                EGiorno giornoX = EGiorno.valuesGiorniDiLezione()[giorno - 1];

                if (a.length == 0) continue;
                if (a.length == 1) {
                    throw new IllegalArgumentException("ERRORE " + Arrays.toString(a));
                }
                if (a.length == 3) {
                    docentePrincipale = a[1];

                    materiaPrincipale = a[0];
                    nomeAula = a[2];
                    if (nomeAula != null && noAula && !ClassesAndRoomContainer.getRoom(nomeAula).flagAulaLaboratorioPalestra()) {
                        nomeAula = RoomData.NON_ASSEGNATO.name;
                    }
                    l = BitOrarioOraLezione.creaOraDocenteSingolo(docentePrincipale, materiaPrincipale, nomeAula, classe, oraX, giornoX);

                } else {
                    materiaPrincipale = a[0];
                    docentePrincipale = a[1];
                    String docenteCompresenza;
                    String materiaCompresenza;
                    docenteCompresenza = a[2];
                    materiaCompresenza = "compresenza";
                    nomeAula = a[3];
                    if (nomeAula != null && noAula && !ClassesAndRoomContainer.getRoom(nomeAula).flagAulaLaboratorioPalestra()) {
                        nomeAula = RoomData.NON_ASSEGNATO.name;
                    }
                    l = BitOrarioOraLezione.creaOraCompresenza(docentePrincipale, materiaPrincipale, docenteCompresenza, materiaCompresenza, nomeAula, classe, oraX, giornoX);
                }

                orarioTotale.add(l);
            }
        }
    }

    private static String[] separaStringhe(MatriceDinamicaStringhe orario, int ora, int giorno) {
        final String s = SEPARATORE_INTERNO + orario.get(ora, giorno);
        final String[] split = s.trim().split("[" + SEPARATORE_INTERNO + "]+");
        ArrayList<String> aa = new ArrayList<>();
        for (String x : split) {
            x = x.trim();
            if (x.length() > 0)
                aa.add(x);
        }
        return aa.toArray(new String[aa.size()]);
    }

    public static class MatriceDinamicaStringhe {
        private final ArrayList<ArrayList<String>> matrice = new ArrayList<>();

        public int getRowCount() {
            return matrice.size();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < getRowCount(); i++) {
                for (int j = 0; j < getColumnCount(); j++) {
                    sb.append(get(i, j)).append("|\t");
                }
                sb.append("\n");
            }

            return sb.toString();
        }

        public String get(int r, int c) {
            if (r >= matrice.size()) return null;
            final ArrayList<String> x = matrice.get(r);
            if (c < x.size()) return x.get(c);
            return null;
        }

        public int getColumnCount() {
            int max = 0;
            for (ArrayList<String> x : matrice) {
                max = Math.max(max, x.size());
            }
            return max;
        }

        private void checkRow(int i) {
            while ((i >= matrice.size())) {
                matrice.add(new ArrayList<String>());
            }
        }

        public void append(int i, int j, String value) {
            String s = get(i, j);
            if (s == null)
                s = value;
            else
                s = s + value;
            set(i, j, s);

        }

        public void set(int i, int j, String value) {
            checkRow(i);
            final ArrayList<String> x = matrice.get(i);
            while (j >= x.size()) {
                x.add(null);
            }
            x.set(j, value);
        }
    }
}
