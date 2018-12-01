package it.gov.scuolesuperioridizagarolo.model.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.ArrayList;
import java.util.List;

public class LessonConstraintContainer implements Cloneable {
    private final ArrayList<AbstractLessonConstraint> filtri;

    public LessonConstraintContainer() {
        filtri = new ArrayList<>();
    }

    private static ArrayList<ClassData> _getAllClasses() {
        return ClassesAndRoomContainer.getAllClasses();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (AbstractLessonConstraint x : filtri) {
            if (sb.length() > 0) {
                sb.append("\n");

            }
            sb.append(x);
        }
        return sb.toString();
    }

    public LessonConstraintContainer clone() {
        try {
            return (LessonConstraintContainer) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * elenco di tutte le lezioni
     *
     * @return
     */
    public List<AbstractLessonConstraint> get() {
        return new ArrayList<>(filtri);
    }

    public void add(AbstractLessonConstraint l) {
        filtri.add(l);
    }

    public <T extends AbstractLessonConstraint> void addAll(List<T> l) {
        filtri.addAll(l);
    }

    public void remove(AbstractLessonConstraint l) {

        final boolean remove = filtri.remove(l);
        if (!remove) {
            throw new IllegalArgumentException("Vincolo non presente");
        }
    }


    public boolean checkAll(BitOrarioOraLezione l, BitOrarioGrigliaOrario orario) {

        for (AbstractLessonConstraint z : filtri) {
            if (!z.__check(l, orario)) return false;
        }
        return true;
    }

    public AbstractLessonConstraint checkAll_returnFirstConstraintNotSatisfied(BitOrarioOraLezione l, BitOrarioGrigliaOrario orario) {

        for (AbstractLessonConstraint z : filtri) {
            if (!z.__check(l, orario)) return z;
        }
        return null;
    }

    public boolean checkAll(List<BitOrarioOraLezione> lx, BitOrarioGrigliaOrario orario) {
        for (BitOrarioOraLezione l : lx) {
            for (AbstractLessonConstraint z : filtri) {
                if (!z.__check(l, orario))
                    return false;
            }
        }
        return true;
    }

    public List<String> checkAllNotPassed(List<BitOrarioOraLezione> lx, BitOrarioGrigliaOrario orario) {
        List<String> ris = new ArrayList<>();
        for (BitOrarioOraLezione l : lx) {
            //skip disposizioni
            if (l.getTipoLezione() == BitOrarioOraEnumTipoLezione.DISPOSIZIONE)
                continue;
            if (l.getAula() == null)
                continue;
            if (l.getAula() == RoomData.USCITA_DIDATTICA)
                continue;

            for (AbstractLessonConstraint z : filtri) {
                if (!z.__check(l, orario)) {
                    ris.add("Test not passed " + z.toString() + " for lesson " + l.toStringComplete());
                }
            }
        }
        return ris;
    }

    public boolean checkAll(String docentePrincipale
            , String materiaPrincipale
            , String docenteCompresenza
            , String materiaCompresenza
            , String docenteSostegno, RoomData aula
            , ClassData classe
            , EOra ora
            , EGiorno giorno
            , BitOrarioGrigliaOrario orario) {

        for (AbstractLessonConstraint z : filtri) {
            if (!z.__check(
                    docentePrincipale
                    , materiaPrincipale
                    , docenteCompresenza
                    , materiaCompresenza
                    , docenteSostegno, aula
                    , classe
                    , ora
                    , giorno, orario))
                return false;
        }
        return true;
    }
}
