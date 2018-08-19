package it.gov.scuolesuperioridizagarolo.model.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

/**
 * Created by stefano on 26/11/2017.
 */
public class LessonScontraint_OR extends AbstractLessonConstraint {
    private final AbstractLessonConstraint a;
    private final AbstractLessonConstraint b;

    public LessonScontraint_OR(AbstractLessonConstraint a, AbstractLessonConstraint b) {
        super(false);
        this.a = a;
        this.b = b;
    }

    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {
        return
                a.__check(docentePrincipale, materiaPrincipale, docenteCompresenza, materiaCompresenza, aula, classe, ora, giorno, orario) ||
                        b.__check(docentePrincipale, materiaPrincipale, docenteCompresenza, materiaCompresenza, aula, classe, ora, giorno, orario);
    }
}
