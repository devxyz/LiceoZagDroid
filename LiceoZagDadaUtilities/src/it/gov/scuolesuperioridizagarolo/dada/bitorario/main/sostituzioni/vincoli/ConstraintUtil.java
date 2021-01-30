package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.vincoli;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.FastSetRoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.FastSetRoomData_ImplTreeSet;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ConstraintUtil {


    public static void sortLezioniPerDocente(List<BitOrarioOraLezione> orario) {
        Comparator<BitOrarioOraLezione> comparatorDocente = Comparator
                .comparing(BitOrarioOraLezione::getDocentePrincipale, Comparator
                        .nullsFirst(String::compareToIgnoreCase))
                .thenComparing(BitOrarioOraLezione::getDocenteCompresenza, Comparator
                        .nullsFirst(String::compareToIgnoreCase))
                .thenComparing(BitOrarioOraLezione::getDocenteSostegno, Comparator
                        .nullsFirst(String::compareToIgnoreCase))
                .thenComparing(BitOrarioOraLezione::getGiorno, Comparator
                        .nullsFirst(EGiorno::compareTo))
                .thenComparing(BitOrarioOraLezione::getOra, Comparator
                        .nullsFirst(EOra::compareTo));
        Collections.sort(orario, comparatorDocente);
    }

    public static void sortLezioniPerClasse(List<BitOrarioOraLezione> orario) {
        Comparator<BitOrarioOraLezione> comparatorAule = Comparator
                .comparing(BitOrarioOraLezione::getClasse, Comparator
                        .nullsFirst(ClassData::compareTo))
                .thenComparing(BitOrarioOraLezione::getGiorno, Comparator
                        .nullsFirst(EGiorno::compareTo))
                .thenComparing(BitOrarioOraLezione::getOra, Comparator
                        .nullsFirst(EOra::compareTo));
        Collections.sort(orario, comparatorAule);
    }


    public static List<FastSetRoomData> validRoom(LessonConstraintContainer vincoli, BitOrarioGrigliaOrario orario, List<BitOrarioOraLezione> lezioni) {
        List<FastSetRoomData> ris = new ArrayList<>(lezioni.size());
        for (BitOrarioOraLezione l : lezioni) {
            ris.add(validRoom(vincoli, orario, l));
        }
        return ris;
    }

    public static FastSetRoomData validRoom(LessonConstraintContainer vincoli, BitOrarioGrigliaOrario orario, BitOrarioOraLezione lezione) {

        FastSetRoomData f = new FastSetRoomData_ImplTreeSet();
        if (lezione.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE) {
            return null;//skip
        }

        for (RoomData room : RoomData.values()) {
            boolean b = vincoli.checkAll(
                    lezione.getDocentePrincipale(), lezione.getMateriaPrincipale(),
                    lezione.getDocenteCompresenza(), lezione.getMateriaCompresenza(),
                    lezione.getDocenteSostegno(), room, lezione.getClasse(),
                    lezione.getOra(), lezione.getGiorno(), orario);
            if (b) {
                f.useRoom(room);
            }
        }
        return f;
    }

    public static void main(String[] args) {

    }
}
