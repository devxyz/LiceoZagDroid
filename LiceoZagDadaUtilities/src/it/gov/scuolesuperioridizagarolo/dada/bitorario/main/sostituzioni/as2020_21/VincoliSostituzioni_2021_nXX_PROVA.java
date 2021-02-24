package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraEnumTipoLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_OccupazioneAule_labsToo;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import utility.aule2020_21.UtilizzoClassi;
import utility.consigliDiClasse.ConsigliDiClasseEngine;
import utility.consigliDiClasse.DocentiComuniCdC;
import utility.copertura_classi.ControlloCoperturaClassi_COVID;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by stefano on 27/04/2018.
 */
@Deprecated
public class VincoliSostituzioni_2021_nXX_PROVA extends AbstractVincoliSostituzioni {


    public static void main(String[] args) throws IOException {


        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/" +
                "scuolesuperioridizagarolo/dada/bitorario/main/sostituzioni/as2020_21/dati_orario");
        final VincoliSostituzioni_2021_nXX_PROVA l = new VincoliSostituzioni_2021_nXX_PROVA();

        //if (true) throw new IllegalArgumentException("STOP DEBUG");
        MotoreSostituzioneAule3.doTaskFromTXT_CSV(
                l,
                new File(root, "Orario Classi Completo.txt"),
                new File(root, "Orario Tabella Globale.csv"),
                new File(root, "Orario Sostegno Orizzontale Semplice.txt"),
                null, new File(root, "01-output"),
                l.filtroAuleSpostamenti(), false, true);
    }


    public ArrayList<UtilizzoClassi> assegnaule() {
        int progressivoDDI = 3;
        ArrayList<UtilizzoClassi> assegnazioni = new ArrayList<>();
        assegnazioni.add(new UtilizzoClassi(RoomData.A8, ClassData.CLASS_5D, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5F, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6, ClassData.CLASS_5G, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2A, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2C, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_3E, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5, ClassData.CLASS_4A, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_4B, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2, ClassData.CLASS_4C, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_5E, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_3C, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1, ClassData.CLASS_4F, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2, ClassData.CLASS_4H, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3, ClassData.CLASS_5B, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5, ClassData.CLASS_2E, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_3A, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4, ClassData.CLASS_3F, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_5A, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_5C, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_1D, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_1C, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5, ClassData.CLASS_1E, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1F, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_4G, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_1B, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_2F, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_4D, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1A, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_2B, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_2D, EGiorno.LUNEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A8, ClassData.CLASS_5D, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5F, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6, ClassData.CLASS_5G, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_3H, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2A, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_2G, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5, ClassData.CLASS_3E, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_4A, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2, ClassData.CLASS_4C, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_5E, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_4F, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1, ClassData.CLASS_4H, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2, ClassData.CLASS_5B, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3, ClassData.CLASS_2E, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5, ClassData.CLASS_3F, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_5A, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4, ClassData.CLASS_5C, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_4E, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_1D, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_2H, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_3B, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5, ClassData.CLASS_1C, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1E, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_1F, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_3D, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_4G, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_1B, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_2F, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_1A, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_2B, EGiorno.MARTEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A8, ClassData.CLASS_5D, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5F, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6, ClassData.CLASS_3H, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2C, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2G, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_3E, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5, ClassData.CLASS_4A, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_4B, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2, ClassData.CLASS_4C, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_3C, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_5B, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1, ClassData.CLASS_2E, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2, ClassData.CLASS_3A, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3, ClassData.CLASS_3F, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5, ClassData.CLASS_5A, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_4E, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4, ClassData.CLASS_1D, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_2H, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_3B, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_1C, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_1E, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5, ClassData.CLASS_1F, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_3D, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_4G, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_1B, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_2F, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_4D, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1A, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_2B, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_2D, EGiorno.MERCOLEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A8, ClassData.CLASS_5F, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5G, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6, ClassData.CLASS_3H, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2A, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2C, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_2G, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5, ClassData.CLASS_4A, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_4B, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2, ClassData.CLASS_4C, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_5E, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_3C, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1, ClassData.CLASS_4F, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2, ClassData.CLASS_4H, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3, ClassData.CLASS_3A, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5, ClassData.CLASS_3F, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_5A, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4, ClassData.CLASS_5C, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_4E, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_1D, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_2H, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_3B, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5, ClassData.CLASS_1C, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1E, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_1F, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_3D, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_4G, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_1B, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_4D, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_1A, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_2D, EGiorno.GIOVEDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A8, ClassData.CLASS_5D, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5G, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6, ClassData.CLASS_3H, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2A, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2C, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_2G, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5, ClassData.CLASS_3E, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_4B, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2, ClassData.CLASS_5E, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_3C, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_4F, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1, ClassData.CLASS_4H, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2, ClassData.CLASS_5B, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3, ClassData.CLASS_2E, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5, ClassData.CLASS_3A, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_5C, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4, ClassData.CLASS_4E, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_1D, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_2H, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_3B, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_1C, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5, ClassData.CLASS_1E, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1F, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_3D, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_1B, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_2F, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_4D, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1A, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_2B, EGiorno.VENERDI).successivo(progressivoDDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_2D, EGiorno.VENERDI).successivo(progressivoDDI));


        // quarantena(assegnazioni, ClassData.CLASS_2B, ClassData.CLASS_3B);
/*
        System.out.println("Classi in DDI");

        for (ClassData c : ClassData.values()) {
            EGiorno eGiorno = cercaDDI(assegnazioni, c);
            if (eGiorno != null) {
                System.out.println(c + "\t" + eGiorno);
            }
        }*/
        return assegnazioni;

    }

    static EGiorno cercaDDI(ArrayList<UtilizzoClassi> assegnazioni, ClassData c) {
        for (EGiorno d : EGiorno.valuesGiorniDiLezione()) {
            if (!cerca(assegnazioni, c, d)) return d;
        }
        return null;
    }

    static boolean cerca(ArrayList<UtilizzoClassi> assegnazioni, ClassData c, EGiorno day) {
        for (UtilizzoClassi a : assegnazioni) {
            if (a.day == day && a.clazz == c) return false;
        }
        return true;
    }


    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {
        ArrayList<UtilizzoClassi> assegnaule = assegnaule();
        ArrayList<BitOrarioOraLezione> lezioni = new ArrayList<>(orarioTotale.getLezioni());

        /*for (BitOrarioOraLezione l : lezioni) {
            if (l.getTipoLezione() == BitOrarioOraEnumTipoLezione.LEZIONE_CON_COMPRESENZA
                    || l.getTipoLezione() == BitOrarioOraEnumTipoLezione.LEZIONE_SINGOLA) {
                ClassData classe = l.getClasse();
                EGiorno giorno = l.getGiorno();


                orarioTotale.removeLezione(l);
                UtilizzoClassi search = search(classe, giorno, assegnaule);
                orarioTotale.addLezione(l.clonaLezioneInAltraAula(search.room));
            }
        }*/


    }


    @Override
    protected void postOrarioBeforeFinalCheck(BitOrarioGrigliaOrario orarioTotale, LessonConstraintContainer l) {


        Map<ClassData, Set<ClassData>> a = ConsigliDiClasseEngine.cercaClassiCompatibili(orarioTotale, ClassData.values());
        for (ClassData classData : a.keySet()) {

            System.out.println(classData + "(" + orarioTotale.getDocenti(classData) + ") ");
            for (ClassData c2 : a.get(classData)) {
                System.out.println("   >>> " + c2 + "(" + orarioTotale.getDocenti(c2) + ") ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();


        for (ClassData classData : a.keySet()) {
            System.out.println(classData + ":" + a.get(classData));
        }

        if (true)
            throw new IllegalArgumentException("STOP");

    }

    @Override
    protected FilterAule[] filtroAuleSpostamenti() {
        return new FilterAule[]{
                FilterAule.LABORATORI_MAI,
                FilterAule.LABORATORI_SOLO_COMPATIBILI,
                FilterAule.LABORATORI_SEMPRE,
        };
    }

    public VincoliSostituzioni_2021_nXX_PROVA invoke(final BitOrarioGrigliaOrario orarioTotale,
                                                     final LessonConstraintContainer l) {
        dal = "4/1/2021";
        al = "8/1/2021";
        progressivo = "99";

        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());

        //if (true) throw new IllegalArgumentException("STOP");
        return this;
    }
}