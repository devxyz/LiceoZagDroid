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
 * classe bloccata in una aula per l'intera giornata (nei laboratori continua ad andarci)
 */
public class LessonConstraint_ClasseFermaInAulaDidattica_labsToo extends AbstractLessonConstraint {
    private final ClassData classe;
    private final RoomData aula;
    private final ArrayList<EGiorno> giorno;


    public LessonConstraint_ClasseFermaInAulaDidattica_labsToo(String c, String aula, EGiorno... giorno) {
        this(ClassesAndRoomContainer.getClass(c), ClassesAndRoomContainer.getRoom(aula), giorno);
    }

    public LessonConstraint_ClasseFermaInAulaDidattica_labsToo(ClassData c, RoomData aula, EGiorno... giorno) {
        super(false);
        if (c == null) throw new IllegalArgumentException("Classe non specificata");
        if (aula == null) throw new IllegalArgumentException("Aula non specificata");
        classe = c;
        this.aula = aula;
        this.giorno = new ArrayList<>(Arrays.asList(giorno));
    }


    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {

        if (this.classe == classe)
            if (this.giorno.contains(giorno)) {
                if (aula != this.aula)
                    return false;
            }
        return true;
    }
}
