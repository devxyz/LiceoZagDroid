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
public class LessonConstraint_MateriaBloccataAule extends AbstractLessonConstraint {
    private final String materia;

    private TreeSet<ClassData> escludiClassi = new TreeSet<>();
    private TreeSet<RoomData> aule = new TreeSet<>();
    private TreeSet<String> escludiDocentiUpperCase = new TreeSet<>();

    @Override
    public String toString() {
        return "LessonConstraint_MateriaBloccataAule{" +
                "aule=" + aule +
                ", materia='" + materia + '\'' +
                ", escludiClassi=" + escludiClassi +
                ", escludiDocentiUpperCase=" + escludiDocentiUpperCase +
                '}';
    }

    public LessonConstraint_MateriaBloccataAule(boolean ignoreLabs, BitOrarioGrigliaOrario o, String materia, ClassData[] escludiClassi, String[] escludiDocenti, RoomData[] aule) {
        super(ignoreLabs);
        this.materia = materia;
        this.aule = new TreeSet<>(Arrays.asList(aule));
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
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {
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
            if (!aule.contains(aula))
                return false;
            if (!aula.flagLIM)
                return false;
        }
        return true;
    }


}
