package it.gov.scuolesuperioridizagarolo.model.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * docente bloccata in una aula per l'intera giornata
 */
public class LessonConstraint_DocenteInAulaLIM extends AbstractLessonConstraint {
    private final String docente;
    private final ArrayList<EGiorno> giorno;
    private final ArrayList<EOra> ore;


    public LessonConstraint_DocenteInAulaLIM(boolean ignoreLabs, BitOrarioGrigliaOrario o, String docente, EGiorno giorno, EOra... ore) {
        super(ignoreLabs);
        this.ore = new ArrayList<>(Arrays.asList(ore));
        docente = docente.toUpperCase();
        if (!o.getDocenti().contains(docente))
            throw new IllegalArgumentException("Nome docente errato: " + docente);
        this.docente = docente;
        this.giorno = new ArrayList<>();
        this.giorno.add(giorno);
    }


    @Override
    public String toString() {
        return "LessonConstraint_DocenteInAulaLIM{" +
                "docente='" + docente + '\'' +
                ", giorno=" + giorno +
                '}';
    }

    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, String docenteSostegno, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {
        //skip aule speciali
        //if (aula.flagAulaLaboratorioPalestra()) return true;


        if (docentePrincipale != null)
            if (this.docente.equalsIgnoreCase(docentePrincipale))
                if (this.ore.contains(ora)) {
                    if (this.giorno.contains(giorno)) {
                        if (!aula.flagLIM)
                            return false;
                    }
                }

        if (docenteCompresenza != null)
            if (this.docente.equalsIgnoreCase(docenteCompresenza))
                if (this.giorno.contains(giorno)) {
                    if (this.ore.contains(ora)) {
                        if (!aula.flagLIM)
                            return false;
                    }
                }


        return true;
    }


}
