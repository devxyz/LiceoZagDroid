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
public class LessonConstraint_ClasseNonInAula_labsToo extends AbstractLessonConstraint {
    private final ClassData classe;
    private final RoomData aula;

    private final ArrayList<EGiorno> giorno;


    public LessonConstraint_ClasseNonInAula_labsToo(String c, String aula, EGiorno[] giorno) {
        this(ClassesAndRoomContainer.parseClass(c), ClassesAndRoomContainer.parseRoom(aula), giorno);

    }

    public LessonConstraint_ClasseNonInAula_labsToo(ClassData c, RoomData aula, EGiorno[] giorno) {
        super(false);
        classe = c;
        this.aula = aula;
        this.giorno = new ArrayList<>(Arrays.asList(giorno));
    }

    @Override
    public String toString() {
        return "LessonConstraint_ClasseNonInAula{" +
                "aula=" + aula +
                ", classe=" + classe +
                ", giorno=" + giorno +
                '}';
    }

    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {
        //skip aule speciali


        if (this.classe == classe)
            if (this.aula == aula)
                if (this.giorno.contains(giorno)) {
                    return false;
                }
        return true;
    }
}
