package it.gov.scuolesuperioridizagarolo.model.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * classe bloccata in area per l'intera giornata (anche per i laboratori)
 */
public class LessonConstraint_ClasseBloccataInArea extends AbstractLessonConstraint {
    private final ClassData classe;
    private final ArrayList<ERoomArea> area;
    private final ArrayList<EGiorno> giorno;


    public LessonConstraint_ClasseBloccataInArea(boolean ignoreLabs, String c, ERoomArea[] area, EGiorno[] giorno) {

        this(ignoreLabs, ClassesAndRoomContainer.parseClass(c), area, giorno);
    }

    public LessonConstraint_ClasseBloccataInArea(boolean ignoreLabs, ClassData c, ERoomArea[] area, EGiorno[] giorno) {
        super(ignoreLabs);
        classe = c;
        this.area = new ArrayList<>(Arrays.asList(area));
        this.giorno = new ArrayList<>(Arrays.asList(giorno));
    }


    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {
        if (this.classe == classe)
            if (this.giorno.contains(giorno)) {
                if (!area.contains(aula.location))
                    return false;
            }
        return true;
    }
}
