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
 * Created by stefano on 26/11/2017.
 */
public class LessonConstraint_AulaNonDisponibile extends AbstractLessonConstraint {
    private final RoomData aula;
    private final EGiorno giorno;
    private final ArrayList<EOra> ore;

    public LessonConstraint_AulaNonDisponibile(String aula, EGiorno giorno, EOra... ore) {
        super(false);
        this.aula = ClassesAndRoomContainer.getRoom(aula);
        this.giorno = giorno;
        this.ore = new ArrayList<>(Arrays.asList(ore));
        ;
    }

    public LessonConstraint_AulaNonDisponibile(RoomData aula, EGiorno giorno, EOra... ore) {
        super(false);
        this.aula = aula;
        this.giorno = giorno;
        this.ore = new ArrayList<>(Arrays.asList(ore));
    }


    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {
        if (aula.progressive == this.aula.progressive) {
            if (giorno == this.giorno && ore.contains(ora))
                return false;
        }

        return true;
    }
}
