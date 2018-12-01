package it.gov.scuolesuperioridizagarolo.model.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

/**
 * Created by stefano on 26/11/2017.
 */
public class LessonConstraint_OccupazioneAule_labsToo extends AbstractLessonConstraint {
    public LessonConstraint_OccupazioneAule_labsToo() {
        super(false);
    }

    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, String docenteSostegno, RoomData aula,
                              ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {
        if (aula==RoomData.AULA_SCONOSCIUTA)return false;
        if (aula==RoomData.NON_ASSEGNATO)return false;
        return classe.numberOfStudents <= aula.maxStudents;
    }

    @Override
    public String toString() {
        return "LessonConstraint_OccupazioneAule_labsToo{}";
    }
}
