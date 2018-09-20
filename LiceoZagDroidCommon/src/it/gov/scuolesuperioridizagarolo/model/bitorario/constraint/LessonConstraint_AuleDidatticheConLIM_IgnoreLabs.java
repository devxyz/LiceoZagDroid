package it.gov.scuolesuperioridizagarolo.model.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * classe bloccata in una aula per l'intera giornata
 */
public class LessonConstraint_AuleDidatticheConLIM_IgnoreLabs extends AbstractLessonConstraint {
    private final ClassData classe;

    private final ArrayList<EGiorno> giorno;


    public LessonConstraint_AuleDidatticheConLIM_IgnoreLabs(String c, EGiorno[] giorno) {
        this(ClassesAndRoomContainer.parseClass(c), giorno);

    }

    public LessonConstraint_AuleDidatticheConLIM_IgnoreLabs(ClassData c, EGiorno[] giorno) {
        super(true);
        classe = c;
        this.giorno = new ArrayList<>(Arrays.asList(giorno));
    }


    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {

        if (this.classe == classe)
            if (this.giorno.contains(giorno)) {
                if (!aula.flagLIM && !aula.flagAulaLaboratorioPalestra())
                    return false;
            }
        return true;
    }
}
