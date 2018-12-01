package it.gov.scuolesuperioridizagarolo.model.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * docente bloccata in una aula per l'intera giornata
 */
public class LessonConstraint_MateriaConLIM extends AbstractLessonConstraint {
    private final String materia;
    private TreeSet<ClassData> escludiClassi = new TreeSet<>();
    private TreeSet<String> escludiDocentiUpperCase = new TreeSet<>();

    public LessonConstraint_MateriaConLIM(boolean ignoreLabs, BitOrarioGrigliaOrario o, String materia, ClassData[] escludiClassi, String[] escludiDocenti) {
        super(ignoreLabs);
        this.materia = materia;
        if (escludiClassi != null) {
            this.escludiClassi.addAll(Arrays.asList(escludiClassi));
        }
        if (escludiDocenti != null) {
            for (String s : escludiDocenti) {
                this.escludiDocentiUpperCase.add(s.toUpperCase());
            }
        }
    }

    @Override
    public String toString() {
        return "LessonConstraint_MateriaConLIM{" +
                "escludiClassi=" + escludiClassi +
                ", materia='" + materia + '\'' +
                ", escludiDocentiUpperCase=" + escludiDocentiUpperCase +
                '}';
    }

    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, String docenteSostegno, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {
        if (classe != null && escludiClassi.contains(classe)) return true;
        if (docentePrincipale != null && escludiDocentiUpperCase.contains(docentePrincipale.toUpperCase())) return true;
        if (docenteCompresenza != null && escludiDocentiUpperCase.contains(docenteCompresenza.toUpperCase()))
            return true;

        //skip aule speciali
        if (ignoreLabs && aula.flagAulaLaboratorioPalestra())
            return true;

        if (materiaPrincipale == null || !materiaPrincipale.equalsIgnoreCase(materia)) {
            return true;
        }

        if (aula != null) {
            if (!aula.flagLIM)
                return false;
        }
        return true;
    }


}
