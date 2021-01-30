package it.gov.scuolesuperioridizagarolo.model.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * l'aula e' utilizzabile solo dalla materia specificata
 */
public class LessonConstraint_AulaSoloPerMateria extends AbstractLessonConstraint {
    private final TreeSet<String> materie;
    private final RoomData aula;
    private final TreeSet<EGiorno> giorni;

    @Override
    public String toString() {
        return "LessonConstraint_AulaSoloPerMateria{" +
                ", materia='" + materie + '\'' +
                ", aula=" + aula +
                '}';
    }

    public LessonConstraint_AulaSoloPerMateria(boolean ignoreLabs, String[] materie, RoomData aula, EGiorno[] giorni) {
        super(ignoreLabs);
        this.materie = new TreeSet<>();
        for (String s : materie) {
            this.materie.add(s.toUpperCase());
        }
        this.aula = aula;
        this.giorni = new TreeSet<>(Arrays.asList(giorni));
    }

    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, String docenteSostegno, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {
        if (aula == null) return true;
        if (materiaPrincipale == null) return true;

        if (!aula.equals(this.aula))
            return true;

        if (!giorni.contains(giorno))
            return true;

        //skip aule speciali
        if (ignoreLabs && aula.isAulaLaboratorioPalestra())
            return true;

        if (!materie.contains(materiaPrincipale)) {
            return false;
        }


        return true;
    }


}
