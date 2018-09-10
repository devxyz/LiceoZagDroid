package it.gov.scuolesuperioridizagarolo.model.bitorario.constraint;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * docente bloccata in una aula per l'intera giornata
 */
public class LessonConstraint_DocenteBloccatoInArea extends AbstractLessonConstraint {
    private final String docente;
    private final ArrayList<ERoomArea> area;
    private final ArrayList<EGiorno> giorno;


    public LessonConstraint_DocenteBloccatoInArea(boolean ignoreLabs,BitOrarioGrigliaOrario o, String docente, ERoomArea[] area, EGiorno[] giorno) {
        super(ignoreLabs);
        docente = docente.toUpperCase();
        if (!o.getDocenti().contains(docente))
            throw new IllegalArgumentException("Nome docente errato: " + docente);
        this.docente = docente;
        this.area = new ArrayList<>(Arrays.asList(area));
        this.giorno = new ArrayList<>(Arrays.asList(giorno));
    }


    @Override
    protected boolean __check(String docentePrincipale, String materiaPrincipale, String docenteCompresenza, String materiaCompresenza, RoomData aula, ClassData classe, EOra ora, EGiorno giorno, BitOrarioGrigliaOrario orario) {
        //skip aule speciali
        //if (aula.flagAulaLaboratorioPalestra()) return true;


        if (docentePrincipale != null)
            if (this.docente.equalsIgnoreCase(docentePrincipale))
                if (this.giorno.contains(giorno)) {
                    if (!area.contains(aula.location))
                        return false;
                }


        if (docenteCompresenza != null)
            if (this.docente.equalsIgnoreCase(docenteCompresenza))
                if (this.giorno.contains(giorno)) {
                    if (!area.contains(aula.location))
                        return false;
                }


        return true;
    }


}
