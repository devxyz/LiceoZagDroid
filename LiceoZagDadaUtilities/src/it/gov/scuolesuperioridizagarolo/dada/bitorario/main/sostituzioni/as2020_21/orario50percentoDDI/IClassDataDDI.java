package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import utility.aule2020_21.UtilizzoClassi;

import java.util.*;

public interface IClassDataDDI {
    boolean isDDI(ClassData c, EGiorno giorno);

    IClassDataDDI inverso();

    public ArrayList<UtilizzoClassi> computeAssegnazioniPRESENZA_E_FISSE(final Set<RoomData> skippedRoom);

    public String generateCodeAssegnazioni(ArrayList<UtilizzoClassi> a);

    public String generateCodeClassDataDDI();
}
