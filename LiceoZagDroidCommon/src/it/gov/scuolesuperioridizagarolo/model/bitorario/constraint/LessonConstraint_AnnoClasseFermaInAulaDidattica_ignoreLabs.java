package it.gov.scuolesuperioridizagarolo.model.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * classe bloccata in una aula per l'intera giornata (nei laboratori continua ad andarci)
 */
public class LessonConstraint_AnnoClasseFermaInAulaDidattica_ignoreLabs extends AbstractLessonConstraint {
    private final int annoClasse;
    private final RoomData aula;
    private final ArrayList<EGiorno> giorno;


    public LessonConstraint_AnnoClasseFermaInAulaDidattica_ignoreLabs(int annoClasse, RoomData aula, EGiorno... giorno) {
        super(true);

        if (aula == null) throw new IllegalArgumentException("Aula non specificata");
        this.annoClasse = annoClasse;
        this.aula = aula;
        this.giorno = new ArrayList<>(Arrays.asList(giorno));
    }

    @Override
    public String toString() {
        return "LessonConstraint_AnnoClasseFermaInAulaDidattica_ignoreLabs{" +
                "annoClasse=" + annoClasse +
                ", aula=" + aula +
                ", giorno=" + giorno +
                '}';
    }

    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {


        if (this.giorno.contains(giorno)) {
            if (aula == this.aula)
                if (this.annoClasse != classe._class)
                    return false;
        }
        return true;
    }
}
