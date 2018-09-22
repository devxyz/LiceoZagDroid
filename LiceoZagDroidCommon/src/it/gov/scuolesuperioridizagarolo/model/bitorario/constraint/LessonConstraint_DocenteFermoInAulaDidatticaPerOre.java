package it.gov.scuolesuperioridizagarolo.model.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * classe bloccata in una aula per l'intera giornata
 */
public class LessonConstraint_DocenteFermoInAulaDidatticaPerOre extends AbstractLessonConstraint {
    private final String docente;
    private final ArrayList<RoomData> aule;
    private final ArrayList<EGiorno> giorno;
    private final ArrayList<EOra> ore;

    public LessonConstraint_DocenteFermoInAulaDidatticaPerOre(boolean ignoreLabs, String docente, RoomData[] aule, EGiorno[] giorno, EOra[] ore) {
        super(ignoreLabs);
        this.aule = new ArrayList<>(Arrays.asList(aule));
        this.docente = docente;
        this.giorno = new ArrayList<>(Arrays.asList(giorno));
        this.ore = new ArrayList<>(Arrays.asList(ore));
    }

    public LessonConstraint_DocenteFermoInAulaDidatticaPerOre(boolean ignoreLabs, String docente, RoomData aule, EGiorno giorno, EOra... ore) {
        super(ignoreLabs);
        this.docente = docente;
        if (docente == null) throw new IllegalArgumentException("Docente non specificato");
        if (aule == null) throw new IllegalArgumentException("Aula non specificata");
        this.aule = new ArrayList<>(Arrays.asList(aule));
        this.giorno = new ArrayList<>(Arrays.asList(giorno));
        this.ore = new ArrayList<>(Arrays.asList(ore));

    }


    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {

        if ((docentePrincipale != null && this.docente.equalsIgnoreCase(docentePrincipale) || (docenteCompresenza != null && this.docente.equalsIgnoreCase(docenteCompresenza))))
            if (this.giorno.contains(giorno) && this.ore.contains(ora)) {
                if (!this.aule.contains(aula))
                    return false;
            }
        return true;
    }
}
