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
public class LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo extends AbstractLessonConstraint {
    public final ClassData classe;
    public final RoomData aula;
    public final ArrayList<EGiorno> giorno;
    public final ArrayList<EOra> ore;


    public LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(String c, String aula, EGiorno giorno, EOra... ore) {
        this(ClassesAndRoomContainer.parseClass(c), ClassesAndRoomContainer.parseRoom(aula), giorno, ore);
    }

    public LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData c, RoomData aula, EGiorno[] giorno, EOra[] ore) {
        super(false);
        if (c == null) throw new IllegalArgumentException("Classe non specificata");
        if (aula == null) throw new IllegalArgumentException("Aula non specificata");
        classe = c;
        this.aula = aula;
        this.giorno = new ArrayList<>(Arrays.asList(giorno));
        this.ore = new ArrayList<>(Arrays.asList(ore));

    }

    public LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(ClassData c, RoomData aula, EGiorno giorno, EOra... ore) {
        super(false);
        if (c == null) throw new IllegalArgumentException("Classe non specificata");
        if (aula == null) throw new IllegalArgumentException("Aula non specificata");
        classe = c;
        this.aula = aula;
        this.giorno = new ArrayList<>(Arrays.asList(giorno));
        this.ore = new ArrayList<>(Arrays.asList(ore));

    }


    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, String docenteSostegno, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {
        if (this.classe == classe)
            if (this.giorno.contains(giorno) && this.ore.contains(ora)) {
                if (aula != this.aula)
                    return false;
            }
        return true;
    }

    @Override
    public String toString() {
        return "LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo{" +
                "aula=" + aula +
                ", classe=" + classe +
                ", giorno=" + giorno +
                ", ore=" + ore +
                '}';
    }
}
