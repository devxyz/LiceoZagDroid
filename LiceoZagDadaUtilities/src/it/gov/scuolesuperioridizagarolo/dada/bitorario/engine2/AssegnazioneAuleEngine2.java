package it.gov.scuolesuperioridizagarolo.dada.bitorario.engine2;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezioneManipulation;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.FastSetRoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.FastSetRoomDataGrid;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.FastSetRoomData_ImplBits;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AssegnazioneAuleEngine2 {
    static final boolean DEBUG = true;
    static final boolean DEBUG_FINE = true;
    static int numElaborazioni = 0;

    public static ArrayList<BitOrarioOraLezione> assegnazioneAule(
            BitOrarioGrigliaOrario o,
            LessonConstraintContainer vincoliStandard,
            boolean vincoliOreConsecutive) {

        //lezioniDaAssegnare classiche
        final ArrayList<BitOrarioOraLezione> lezioniDaAssegnare = new ArrayList<>();

        //disposizione
        final ArrayList<BitOrarioOraLezione> lezioniAltre = new ArrayList<>();


        //**************************************************************************************************************
        // individua le lezioni da analizzare
        //**************************************************************************************************************
        for (BitOrarioOraLezione l : o.getLezioni()) {
            BitOrarioOraLezione e = BitOrarioOraLezioneManipulation.clonaFuoriOrario(l);

            //TODO DEBUG - annulla aule
            if (e.getAula() != null && !e.getAula().isAulaLaboratorioPalestra()) {
                BitOrarioOraLezioneManipulation.setAula(e, null);
            }
            //TODO ===============================

            if (e.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
                lezioniAltre.add(e);
            } else {
                lezioniDaAssegnare.add(e);
            }
        }


        //**************************************************************************************************************
        // individua i vincoli ed eventualmente rimuove aule che violano i vincoli
        //**************************************************************************************************************
        final ArrayList<AssegnazioneAuleEngine2Util.BitOrarioOraLezione_AuleCompatibili> aule_compatibili =
                AssegnazioneAuleEngine2Util.auleCompatibili(o, lezioniDaAssegnare, vincoliStandard);

        //**************************************************************************************************************
        // ordinamento delle lezioni
        //**************************************************************************************************************
        //TODO: DA MODIFICARE IN BASE ALLE ESIGENZE (x aulunni, docenti ecc)
        //ordina per docente
        Collections.sort(aule_compatibili, Comparator.comparing(
                AssegnazioneAuleEngine2Util.BitOrarioOraLezione_AuleCompatibili::getAuleSize)
                .thenComparing(AssegnazioneAuleEngine2Util.BitOrarioOraLezione_AuleCompatibili::getGiorno)
                .thenComparing(AssegnazioneAuleEngine2Util.BitOrarioOraLezione_AuleCompatibili::getOra)
        );

        //**************************************************************************************************************
        // fissa l'utilizzo delle aule
        //**************************************************************************************************************
        final FastSetRoomDataGrid grigliaAuleUsate = new FastSetRoomDataGrid() {
            @Override
            protected FastSetRoomData newInstance() {
                return new FastSetRoomData_ImplBits();
            }
        };
        for (BitOrarioOraLezione l : lezioniDaAssegnare) {
            if (l.getAula() != null) {
                grigliaAuleUsate.get(l.getGiorno(), l.getOra()).useRoom(l.getAula());
            }
        }

        System.out.println("Lezioni totali: " + lezioniDaAssegnare.size());

        //**************************************************************************************************************
        // elaborazione
        //**************************************************************************************************************
        numElaborazioni = 0;
        boolean execute = executeRicorsivo(o, aule_compatibili, vincoliStandard, grigliaAuleUsate, vincoliOreConsecutive, 0);
        if (!execute) {
            throw new IllegalArgumentException("Soluzione non trovata");
        }

        ArrayList<BitOrarioOraLezione> ris = new ArrayList<>(lezioniDaAssegnare.size() + lezioniAltre.size());
        ris.addAll(lezioniDaAssegnare);
        ris.addAll(lezioniAltre);

        //controllo lezioni non assegnate
        for (BitOrarioOraLezione l : lezioniDaAssegnare) {
            if (l.getAula() == null) {
                BitOrarioOraLezioneManipulation.setAula(l, RoomData.AULA_SCONOSCIUTA);
                //throw new IllegalArgumentException("Lezione " + l + ": aula non assegnata");
            }
        }
        return ris;
    }

    public static boolean executeRicorsivo(
            BitOrarioGrigliaOrario orario,
            ArrayList<AssegnazioneAuleEngine2Util.BitOrarioOraLezione_AuleCompatibili> lezioni,
            LessonConstraintContainer vincoliStandard,
            FastSetRoomDataGrid grigliaAuleUsate, boolean vincoliOreConsecutive, int index) {
        numElaborazioni++;
        if (numElaborazioni % 10000 == 0) {
            System.out.println("Elaborazioni: " + numElaborazioni + " index " + index);
        }

        //System.out.println(index);
        if (index >= lezioni.size()) {
            System.out.println("FINE");
            return true;
        }

        BitOrarioOraLezione lezione = lezioni.get(index).lezione;
        if (lezione.getAula() != null) {
            if (DEBUG_FINE) {
                System.out.println("Lezione (" + index + "): " + lezione);
                System.out.println(" >Aula già assegnata");
            }
            //se già assegnata si va avanti
            return executeRicorsivo(orario, lezioni, vincoliStandard, grigliaAuleUsate, vincoliOreConsecutive, index + 1);
        }

        final ArrayList<RoomData> aule_da_analizzare;

        final FastSetRoomData roomData = grigliaAuleUsate.get(lezione.getGiorno(), lezione.getOra());

        if (vincoliOreConsecutive && index > 0 && AssegnazioneAuleEngine2Util.adiacenti(lezioni.get(index - 1).lezione, lezione)) {

            //se lezioni consecutive, assegna l'aula precedente solo se presente tra le aule compatibili
            BitOrarioOraLezione prec = lezioni.get(index - 1).lezione;
            BitOrarioOraLezioneManipulation.setAula(lezione, prec.getAula());
            RoomData precAula = prec.getAula();
            aule_da_analizzare = new ArrayList<>();
            if (lezioni.get(index).aule.contains(precAula)) {
                aule_da_analizzare.add(precAula);
            }

            if (DEBUG) {
                System.out.println("Lezione (" + index + "): " + lezione);
                System.out.println(" >Lezioni consecutive: " + prec.getAula());
                System.out.println(" >Aule compatibili: " + aule_da_analizzare);
            }

        } else {
            aule_da_analizzare = lezioni.get(index).aule;
            if (DEBUG) {
                System.out.println("Lezione (" + index + "): " + lezione);
                System.out.println(" >Aula da cercare");
                System.out.println(" >Aule compatibili: " + aule_da_analizzare);
            }
        }


        for (RoomData aula : aule_da_analizzare) {
            if (!roomData.isRoomUsed(aula)) {
                roomData.useRoom(aula);
                BitOrarioOraLezioneManipulation.setAula(lezione, aula);

                boolean execute = executeRicorsivo(orario, lezioni, vincoliStandard, grigliaAuleUsate, vincoliOreConsecutive, index + 1);
                if (execute)
                    return true;

                BitOrarioOraLezioneManipulation.setAula(lezione, null);
                roomData.freeRoom(aula);

            }

        }

        if (DEBUG) {
            System.out.println("Lezione (" + index + "): " + lezione);
            System.out.println(" >Soluzioni non trovate");
        }

        return false;


    }
}
