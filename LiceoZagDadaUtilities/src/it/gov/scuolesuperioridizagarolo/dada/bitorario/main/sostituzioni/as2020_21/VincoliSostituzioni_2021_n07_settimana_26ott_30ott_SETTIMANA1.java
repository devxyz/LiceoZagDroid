package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_OccupazioneAule_labsToo;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import utility.aule2020_21.UtilizzoClassi;
import utility.copertura_classi.ControlloCoperturaClassi_COVID;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_2021_n07_settimana_26ott_30ott_SETTIMANA1 extends AbstractVincoliSostituzioni {

    static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/" +
            "scuolesuperioridizagarolo/dada/bitorario/main/sostituzioni/as2020_21/dati_orario/");

    static class DATA_ASSEGNAZIONI {
        //SETTIMANA 1
        private IClassDataDDI partizioneIClassDataDDISettimana1() {
            IClassDataDDI cd = new ClassDataDDI_SettimanaFissa(
                    new ClassData[]{ClassData.CLASS_2B, ClassData.CLASS_2C, ClassData.CLASS_2H, ClassData.CLASS_3B, ClassData.CLASS_3C, ClassData.CLASS_3D, ClassData.CLASS_3H, ClassData.CLASS_4B, ClassData.CLASS_4C, ClassData.CLASS_4D, ClassData.CLASS_4G, ClassData.CLASS_4H, ClassData.CLASS_5B, ClassData.CLASS_5C, ClassData.CLASS_5D},
                    new ClassData[]{ClassData.CLASS_2A, ClassData.CLASS_2D, ClassData.CLASS_2E, ClassData.CLASS_2F, ClassData.CLASS_2G, ClassData.CLASS_3A, ClassData.CLASS_3E, ClassData.CLASS_3F, ClassData.CLASS_4A, ClassData.CLASS_4E, ClassData.CLASS_4F, ClassData.CLASS_5A, ClassData.CLASS_5E, ClassData.CLASS_5F, ClassData.CLASS_5G},
                    new ClassData[]{ClassData.CLASS_1A, ClassData.CLASS_1B, ClassData.CLASS_1C, ClassData.CLASS_1D, ClassData.CLASS_1E, ClassData.CLASS_1F}
            );

            return cd;
        }

        //SETTIMANA 1
        private ArrayList<UtilizzoClassi> assegnazioneAuleSettimana1() {
            ArrayList<UtilizzoClassi> assegnazioni = new ArrayList<>();
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5F, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_5G, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2A, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_2G, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_3E, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_4A, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_5E, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_4F, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_2E, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_3A, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_5A, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_3F, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_4E, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_1C, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_1D, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_1E, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_1F, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1B, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_2F, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_1A, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.PAL1, ClassData.CLASS_2D, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5F, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_5G, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2A, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_2G, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_3E, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_4A, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_5E, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_4F, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_2E, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_3A, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_5A, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_3F, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_4E, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_1C, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_1D, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_1E, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_1F, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1B, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_2F, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_1A, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.PAL1, ClassData.CLASS_2D, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5F, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_5G, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2A, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_2G, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_3E, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_4A, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_5E, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_4F, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_2E, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_3A, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_5A, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_3F, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_4E, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_1C, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_1D, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_1E, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_1F, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1B, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_2F, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_1A, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.PAL1, ClassData.CLASS_2D, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5F, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_5G, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2A, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_2G, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_3E, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_4A, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_5E, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_4F, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_2E, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_3A, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_5A, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_3F, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_4E, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_1C, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_1D, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_1E, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_1F, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1B, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_2F, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_1A, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.PAL1, ClassData.CLASS_2D, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5F, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_5G, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2A, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_2G, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_3E, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_4A, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_5E, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_4F, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_2E, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_3A, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_5A, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_3F, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_4E, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_1C, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_1D, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_1E, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_1F, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1B, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_2F, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_1A, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.PAL1, ClassData.CLASS_2D, EGiorno.VENERDI));

            AbstractVincoliSostituzioni.quarantena(assegnazioni, ClassData.CLASS_5G, EGiorno.values());
            AbstractVincoliSostituzioni.quarantena(assegnazioni, ClassData.CLASS_4E, EGiorno.values());
            AbstractVincoliSostituzioni.quarantena(assegnazioni, ClassData.CLASS_5E, EGiorno.values());
            AbstractVincoliSostituzioni.quarantena(assegnazioni, ClassData.CLASS_5A, EGiorno.values());
            AbstractVincoliSostituzioni.quarantena(assegnazioni, ClassData.CLASS_5F, EGiorno.LUNEDI, EGiorno.MARTEDI, EGiorno.MERCOLEDI, EGiorno.GIOVEDI);
            AbstractVincoliSostituzioni.quarantena(assegnazioni, ClassData.CLASS_3B, EGiorno.LUNEDI, EGiorno.MARTEDI, EGiorno.MERCOLEDI);


            return assegnazioni;
        }


        //SETTIMANA 2
        private IClassDataDDI partizioneIClassDataDDISettimana2() {
            IClassDataDDI cd = new ClassDataDDI_SettimanaFissa(
                    new ClassData[]{ClassData.CLASS_2A, ClassData.CLASS_2D, ClassData.CLASS_2E, ClassData.CLASS_2F, ClassData.CLASS_2G, ClassData.CLASS_3A, ClassData.CLASS_3E, ClassData.CLASS_3F, ClassData.CLASS_4A, ClassData.CLASS_4E, ClassData.CLASS_4F, ClassData.CLASS_5A, ClassData.CLASS_5E, ClassData.CLASS_5F, ClassData.CLASS_5G},
                    new ClassData[]{ClassData.CLASS_2B, ClassData.CLASS_2C, ClassData.CLASS_2H, ClassData.CLASS_3B, ClassData.CLASS_3C, ClassData.CLASS_3D, ClassData.CLASS_3H, ClassData.CLASS_4B, ClassData.CLASS_4C, ClassData.CLASS_4D, ClassData.CLASS_4G, ClassData.CLASS_4H, ClassData.CLASS_5B, ClassData.CLASS_5C, ClassData.CLASS_5D},
                    new ClassData[]{ClassData.CLASS_1A, ClassData.CLASS_1B, ClassData.CLASS_1C, ClassData.CLASS_1D, ClassData.CLASS_1E, ClassData.CLASS_1F}
            );

            return cd;
        }

        //SETTIMANA 2
        private ArrayList<UtilizzoClassi> assegnazioneAuleSettimana2() {
            ArrayList<UtilizzoClassi> assegnazioni = new ArrayList<>();
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5D, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_3H, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2C, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4B, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_4C, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_3C, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_4H, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_5B, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_5C, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_2H, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_3B, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_1C, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1D, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_1E, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_1F, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_3D, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_4G, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1B, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_4D, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_1A, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.PAL1, ClassData.CLASS_2B, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5D, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_3H, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2C, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4B, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_4C, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_3C, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_4H, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_5B, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_5C, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_2H, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_3B, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_1C, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1D, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_1E, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_1F, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_3D, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_4G, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1B, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_4D, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_1A, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.PAL1, ClassData.CLASS_2B, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5D, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_3H, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2C, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4B, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_4C, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_3C, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_4H, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_5B, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_5C, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_2H, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_3B, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_1C, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1D, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_1E, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_1F, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_3D, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_4G, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1B, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_4D, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_1A, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.PAL1, ClassData.CLASS_2B, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5D, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_3H, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2C, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4B, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_4C, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_3C, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_4H, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_5B, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_5C, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_2H, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_3B, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_1C, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1D, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_1E, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_1F, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_3D, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_4G, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1B, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_4D, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_1A, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.PAL1, ClassData.CLASS_2B, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5D, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_3H, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2C, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4B, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_4C, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_3C, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_4H, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_5B, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_5C, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_2H, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_3B, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E4, ClassData.CLASS_1C, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1D, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C4, ClassData.CLASS_1E, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D2, ClassData.CLASS_1F, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D3, ClassData.CLASS_3D, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D4, ClassData.CLASS_4G, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1B, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_4D, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B7, ClassData.CLASS_1A, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.PAL1, ClassData.CLASS_2B, EGiorno.VENERDI));


            return assegnazioni;
        }

    }


    public static void main(String[] args) throws IOException {


        final VincoliSostituzioni_2021_n07_settimana_26ott_30ott_SETTIMANA1 l = new VincoliSostituzioni_2021_n07_settimana_26ott_30ott_SETTIMANA1();

        //if (true) throw new IllegalArgumentException("STOP DEBUG");
        MotoreSostituzioneAule3.doTaskFromTXT_CSV(
                l,
                new File(root, "Orario Classi Completo.txt"),
                new File(root, "Orario Tabella Globale.csv"),
                new File(root, "Orario Sostegno Orizzontale Semplice.txt"),
                null, new File(root, "01-output"),
                l.filtroAuleSpostamenti(), false, true);
    }


    static final boolean CALCOLA_DDI_PRESENZA = true;

    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {
        Map<String, String> map = new TreeMap<>();
        map.put("A027_POTENZ>SUPPL", "Anti");
        map.put("CERULLO", "MARGUCCIO");
        map.put("MARCHESE>SUPPL", "MARCHESE**");
        map.put("TASSAN>SUPPL", "TASSAN**");
        renameDocente(orarioTotale, map);


        if (CALCOLA_DDI_PRESENZA) {
            //elaborazione DDI/PRESENZA OTTIMA
            int numCicliRicerca = 10000;
            IClassDataDDI[] bestDDIArray = OrarioDDIEngine_SettimaneAlterne.dosomething(orarioTotale, numCicliRicerca, new OrarioDDIEngine_SettimaneAlterne.OrarioDDIEngine_SettimaneAlterne_DDIAssign_2settimane());

            //PRIMA SETTIMANA SI USANO LE DDI
            final Set<RoomData> skippedRoom = new TreeSet<>(Arrays.asList(
                    RoomData.E5, RoomData.B1, RoomData.B2, RoomData.B3, RoomData.B4, RoomData.B5,
                    RoomData.F2, RoomData.PAL2,
                    RoomData.C6,
                    RoomData.A8,
                    RoomData.D5
            ));

            for (int i = 0, bestDDIArrayLength = bestDDIArray.length; i < bestDDIArrayLength; i++) {
                IClassDataDDI classiSettimana = bestDDIArray[i];
                ArrayList<UtilizzoClassi> assegnaule_settimana1;
                assegnaule_settimana1 = classiSettimana.computeAssegnazioniPRESENZA_E_FISSE(skippedRoom);


                System.out.println();
                System.out.println("//SETTIMANA " + (i + 1));
                System.out.println("private IClassDataDDI partizioneIClassDataDDISettimana" + (i + 1) + "() {");
                System.out.println(classiSettimana.generateCodeClassDataDDI());
                System.out.println("return cd;}");
                System.out.println();
                System.out.println("//SETTIMANA " + (i + 1));
                System.out.println("private ArrayList<UtilizzoClassi> assegnazioneAuleSettimana" + (i + 1) + "() {");

                System.out.println(classiSettimana.generateCodeAssegnazioni(assegnaule_settimana1));
                System.out.println("return assegnazioni;");
                System.out.println("}");
                System.out.println();

            }

            if (true) {
                throw new IllegalArgumentException("Utilizza la modalità statica per elaborare l'orario (CALCOLA_DDI_PRESENZA=false)");
            }
        } else {
            ArrayList<UtilizzoClassi> assegnaule;
            assegnaule = new DATA_ASSEGNAZIONI().assegnazioneAuleSettimana1();
            IClassDataDDI iClassDataDDI = new DATA_ASSEGNAZIONI().partizioneIClassDataDDISettimana1();
            OrarioDocentiComplessivo_SkipAlertReportDisposizioni d = new OrarioDocentiComplessivo_SkipAlertReportDisposizioni();

            //spostamenti disposizioni
            d.ignore(spostaDisposizioni(orarioTotale, "BIAZZO", EGiorno.MARTEDI, EOra.SECONDA, EGiorno.MARTEDI, EOra.PRIMA));
            d.ignore(spostaDisposizioni(orarioTotale, "CARONI", EGiorno.MERCOLEDI, EOra.TERZA, EGiorno.MARTEDI, EOra.PRIMA));
            d.ignore(spostaDisposizioni(orarioTotale, "MARGUCCIO", EGiorno.VENERDI, EOra.SECONDA, EGiorno.LUNEDI, EOra.QUARTA));
            d.ignore(spostaDisposizioni(orarioTotale, "DEL SIGNORE", EGiorno.LUNEDI, EOra.TERZA, EGiorno.MARTEDI, EOra.SECONDA));
            d.ignore(spostaDisposizioni(orarioTotale, "FUSANI", EGiorno.VENERDI, EOra.TERZA, EGiorno.GIOVEDI, EOra.QUARTA));
            d.ignore(spostaDisposizioni(orarioTotale, "PAGLIARI", EGiorno.MERCOLEDI, EOra.SECONDA, EGiorno.MARTEDI, EOra.TERZA));
            d.ignore(spostaDisposizioni(orarioTotale, "ROSINI", EGiorno.LUNEDI, EOra.TERZA, EGiorno.MARTEDI, EOra.TERZA));
            d.ignore(spostaDisposizioni(orarioTotale, "ROSINI", EGiorno.VENERDI, EOra.QUARTA, EGiorno.MARTEDI, EOra.SECONDA));
            d.ignore(spostaDisposizioni(orarioTotale, "SACCENTI", EGiorno.LUNEDI, EOra.SECONDA, EGiorno.VENERDI, EOra.SECONDA));
            d.ignore(spostaDisposizioni(orarioTotale, "SACCENTI", EGiorno.MARTEDI, EOra.TERZA, EGiorno.VENERDI, EOra.PRIMA));


            //controllo disposizioni da spostare
            System.out.println("DISPOSIZIONI DA SPOSTARE SETTIMANA 1");
            OrarioDocentiComplessivo o = new OrarioDocentiComplessivo();
            o.addAll(orarioTotale.getLezioni());
            o.assignDDI(iClassDataDDI);
            System.out.println(o.computeReportDisposizioniDaSpostare(d, true));
            System.out.println();
            System.out.println("Suddivisione classi:");
            System.out.println(iClassDataDDI);
            System.out.println();
            System.out.println("LEZIONI DA CASA");
            MatriceGiorniOre matriceGiorniOreDaCasa_sett1 = o.computeDDI_daCasa();
            System.out.println(matriceGiorniOreDaCasa_sett1);
            System.out.println();
            System.out.println("LEZIONI DA SCUOLA");
            MatriceGiorniOre matriceGiorniOreDaScuola_sett1 = o.computeDDI_daScuola();
            System.out.println(matriceGiorniOreDaScuola_sett1);

            assegnaAuleAdOrario(orarioTotale, assegnaule);

        }

    }


    @Override
    public Map<ClassData, ControlloCoperturaClassi_COVID.CoperturaClasse_COVID> reportQuarantene(BitOrarioGrigliaOrario orarioTotale) {
        List<ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID> assenti = new ArrayList<>();
      /*  assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Millozzi", EGiorno.valuesGiorniDiLezione()));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Acciai", EGiorno.valuesGiorniDiLezione()));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Benedetti", EGiorno.valuesGiorniDiLezione()));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Corsi", EGiorno.valuesGiorniDiLezione()));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Di Rosa", EGiorno.valuesGiorniDiLezione()));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Gentile", EGiorno.valuesGiorniDiLezione()));
        assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Santori", EGiorno.valuesGiorniDiLezione()));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Cerullo", EGiorno.valuesGiorniDiLezione()));//Marguccio
        assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Saccenti", EGiorno.valuesGiorniDiLezione()));
        assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Taraborrelli", EGiorno.valuesGiorniDiLezione()));
        assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Centracchio", EGiorno.valuesGiorniDiLezione()));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("De angelis", EGiorno.valuesGiorniDiLezione()));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Volpi", EGiorno.valuesGiorniDiLezione()));
        assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Carli", EGiorno.valuesGiorniDiLezione()));
        assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Fabroni", EGiorno.valuesGiorniDiLezione()));
        assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Borrello", EGiorno.valuesGiorniDiLezione()));
        assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Lio", EGiorno.valuesGiorniDiLezione()));
        assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Zappia", EGiorno.valuesGiorniDiLezione()));
        assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Pagliari", EGiorno.valuesGiorniDiLezione()));
        assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Mattetti", EGiorno.valuesGiorniDiLezione()));
        */

        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("MONARDO>SUPPL", EGiorno.MERCOLEDI, EGiorno.GIOVEDI));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("TASSAN>SUPPL", EGiorno.MERCOLEDI, EGiorno.GIOVEDI));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("DEL SIGNORE", EGiorno.MERCOLEDI));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Ferrigno", EGiorno.valuesGiorniDiLezione()));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Stecca", EGiorno.valuesGiorniDiLezione()));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Cocci", EGiorno.valuesGiorniDiLezione()));
        //assenti.add(new ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID("Cerri", EGiorno.valuesGiorniDiLezione()));

        Map<ClassData, ControlloCoperturaClassi_COVID.CoperturaClasse_COVID> report = ControlloCoperturaClassi_COVID.report(orarioTotale, assenti);
        return report;
    }


    @Override
    protected void postOrarioBeforeFinalCheck(BitOrarioGrigliaOrario orarioTotale, LessonConstraintContainer l) {
/*
        if (true)
            throw new IllegalArgumentException("STOP");
*/
    }

    @Override
    protected FilterAule[] filtroAuleSpostamenti() {
        return new FilterAule[]{
                FilterAule.LABORATORI_MAI,
                FilterAule.LABORATORI_SOLO_COMPATIBILI,
                FilterAule.LABORATORI_SEMPRE,
        };
    }

    public VincoliSostituzioni_2021_n07_settimana_26ott_30ott_SETTIMANA1 invoke(final BitOrarioGrigliaOrario orarioTotale,
                                                                                final LessonConstraintContainer l) {
        dal = "26/10/2020";
        al = "30/10/2020";
        progressivo = "7";

        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());


        return this;
    }
}
