package it.gov.scuolesuperioridizagarolo.model.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.List;

public abstract class AbstractLessonConstraint {
    protected final boolean ignoreLabs;

    protected AbstractLessonConstraint(boolean ignoreLabs) {
        this.ignoreLabs = ignoreLabs;
    }

    public static RoomData getRoom(String nome) {
        if (nome == null) return null;
        return ClassesAndRoomContainer.getRoom(nome);
    }

    public static ClassData getClass(String nome) {
        if (nome == null) return null;
        return ClassesAndRoomContainer.getClass(nome);
    }

    public boolean checkAll(List<BitOrarioOraLezione> lx, BitOrarioGrigliaOrario orario) {
        for (BitOrarioOraLezione x : lx) {
            if (!__check(x, orario)) return false;
        }
        return true;
    }


    protected abstract boolean __check(
            String docentePrincipale
            , String materiaPrincipale
            , String docenteCompresenza
            , String materiaCompresenza
            , RoomData aula
            , ClassData classe
            , EOra ora
            , EGiorno giorno, BitOrarioGrigliaOrario orario);

    public final boolean __check(
            String docentePrincipale
            , String materiaPrincipale
            , String docenteCompresenza
            , String materiaCompresenza
            , String nomeAula
            , String classe
            , EOra ora
            , EGiorno giorno, BitOrarioGrigliaOrario orario) {


        final RoomData room = getRoom(nomeAula);
        if (ignoreLabs && room != null && room.flagSpecial())
            return true;

        return __check(docentePrincipale, materiaPrincipale, docenteCompresenza, materiaCompresenza,
                room, getClass(classe), ora, giorno,
                orario);
    }

    public final boolean __check(BitOrarioOraLezione l, BitOrarioGrigliaOrario orario) {
        final RoomData room = getRoom(l.getNomeAula());
        if (ignoreLabs && room != null && room.flagSpecial())
            return true;


        return __check(l.getDocentePrincipale(), l.getMateriaPrincipale(), l.getDocenteCompresenza(), l.getMateriaCompresenza(),
                room, getClass(l.getClasse()), l.getOra(), l.getGiorno(),
                orario);
    }

    @Override
    public final boolean equals(Object o) {
        return super.equals(o);

    }

}
