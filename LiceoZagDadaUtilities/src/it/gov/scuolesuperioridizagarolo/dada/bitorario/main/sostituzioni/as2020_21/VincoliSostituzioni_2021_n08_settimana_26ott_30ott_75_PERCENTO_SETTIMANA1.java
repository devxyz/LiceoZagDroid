package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_OccupazioneAule_labsToo;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import utility.aule2020_21.UtilizzoClassi;
import utility.clipboard.ClipboardUtil;
import utility.copertura_classi.ControlloCoperturaClassi_COVID;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;


/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_2021_n08_settimana_26ott_30ott_75_PERCENTO_SETTIMANA1 extends AbstractVincoliSostituzioni {

    static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/" +
            "scuolesuperioridizagarolo/dada/bitorario/main/sostituzioni/as2020_21/dati_orario/");


    public static class DATA_ASSEGNAZIONI {

        //SETTIMANA 1
        public static IClassDataDDI partizioneIClassDataDDISettimana1() {
            IClassDataDDI cd = new ClassDataDDI_SettimanaFissa(
                    new ClassData[]{ClassData.CLASS_1A, ClassData.CLASS_1B, ClassData.CLASS_1C, ClassData.CLASS_1E, ClassData.CLASS_2A, ClassData.CLASS_2B, ClassData.CLASS_2C, ClassData.CLASS_2E, ClassData.CLASS_2F, ClassData.CLASS_2G, ClassData.CLASS_2H, ClassData.CLASS_3A, ClassData.CLASS_3B, ClassData.CLASS_3C, ClassData.CLASS_3E, ClassData.CLASS_3F, ClassData.CLASS_3H, ClassData.CLASS_4B, ClassData.CLASS_4C, ClassData.CLASS_4E, ClassData.CLASS_4G, ClassData.CLASS_4H, ClassData.CLASS_5A, ClassData.CLASS_5B, ClassData.CLASS_5C, ClassData.CLASS_5E, ClassData.CLASS_5G},
                    new ClassData[]{ClassData.CLASS_1D, ClassData.CLASS_1F, ClassData.CLASS_2D, ClassData.CLASS_3D, ClassData.CLASS_4A, ClassData.CLASS_4D, ClassData.CLASS_4F, ClassData.CLASS_5D, ClassData.CLASS_5F},
                    new ClassData[]{}
            );

            return cd;
        }

        //SETTIMANA 1
        public static ArrayList<UtilizzoClassi> assegnazioneAuleSettimana1() {
            ArrayList<UtilizzoClassi> assegnazioni = new ArrayList<>();
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5D, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_5F, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4A, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4F, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_1D, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_1F, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_3D, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_4D, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_2D, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5D, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_5F, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4A, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4F, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_1D, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_1F, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_3D, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_4D, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_2D, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5D, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_5F, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4A, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4F, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_1D, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_1F, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_3D, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_4D, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_2D, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5D, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_5F, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4A, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4F, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_1D, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_1F, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_3D, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_4D, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_2D, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5D, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_5F, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4A, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4F, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_1D, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_1F, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E3, ClassData.CLASS_3D, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_4D, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A6, ClassData.CLASS_2D, EGiorno.VENERDI));

            return assegnazioni;
        }


        //SETTIMANA 2
        public static IClassDataDDI partizioneIClassDataDDISettimana2() {
            IClassDataDDI cd = new ClassDataDDI_SettimanaFissa(
                    new ClassData[]{ClassData.CLASS_1A, ClassData.CLASS_1B, ClassData.CLASS_1D, ClassData.CLASS_1F, ClassData.CLASS_2C, ClassData.CLASS_2D, ClassData.CLASS_2H, ClassData.CLASS_3A, ClassData.CLASS_3B, ClassData.CLASS_3C, ClassData.CLASS_3D, ClassData.CLASS_3E, ClassData.CLASS_3F, ClassData.CLASS_3H, ClassData.CLASS_4A, ClassData.CLASS_4B, ClassData.CLASS_4C, ClassData.CLASS_4D, ClassData.CLASS_4E, ClassData.CLASS_4F, ClassData.CLASS_4G, ClassData.CLASS_4H, ClassData.CLASS_5A, ClassData.CLASS_5B, ClassData.CLASS_5C, ClassData.CLASS_5D, ClassData.CLASS_5F},
                    new ClassData[]{ClassData.CLASS_1C, ClassData.CLASS_1E, ClassData.CLASS_2A, ClassData.CLASS_2B, ClassData.CLASS_2E, ClassData.CLASS_2F, ClassData.CLASS_2G, ClassData.CLASS_5E, ClassData.CLASS_5G},
                    new ClassData[]{}
            );

            return cd;
        }

        //SETTIMANA 2
        public static ArrayList<UtilizzoClassi> assegnazioneAuleSettimana2() {
            ArrayList<UtilizzoClassi> assegnazioni = new ArrayList<>();
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5G, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2A, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2G, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_5E, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_2E, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_1C, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_1E, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_2F, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_2B, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5G, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2A, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2G, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_5E, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_2E, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_1C, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_1E, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_2F, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_2B, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5G, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2A, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2G, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_5E, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_2E, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_1C, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_1E, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_2F, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_2B, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5G, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2A, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2G, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_5E, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_2E, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_1C, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_1E, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_2F, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_2B, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_5G, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2A, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_2G, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_5E, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_2E, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_1C, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E2, ClassData.CLASS_1E, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_2F, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_2B, EGiorno.VENERDI));

            return assegnazioni;
        }


        //SETTIMANA 3
        public static IClassDataDDI partizioneIClassDataDDISettimana3() {
            IClassDataDDI cd = new ClassDataDDI_SettimanaFissa(
                    new ClassData[]{ClassData.CLASS_1A, ClassData.CLASS_1C, ClassData.CLASS_1D, ClassData.CLASS_1E, ClassData.CLASS_1F, ClassData.CLASS_2A, ClassData.CLASS_2B, ClassData.CLASS_2D, ClassData.CLASS_2E, ClassData.CLASS_2F, ClassData.CLASS_2G, ClassData.CLASS_2H, ClassData.CLASS_3A, ClassData.CLASS_3D, ClassData.CLASS_3E, ClassData.CLASS_3F, ClassData.CLASS_3H, ClassData.CLASS_4A, ClassData.CLASS_4D, ClassData.CLASS_4E, ClassData.CLASS_4F, ClassData.CLASS_4H, ClassData.CLASS_5A, ClassData.CLASS_5D, ClassData.CLASS_5E, ClassData.CLASS_5F, ClassData.CLASS_5G},
                    new ClassData[]{ClassData.CLASS_1B, ClassData.CLASS_2C, ClassData.CLASS_3B, ClassData.CLASS_3C, ClassData.CLASS_4B, ClassData.CLASS_4C, ClassData.CLASS_4G, ClassData.CLASS_5B, ClassData.CLASS_5C},
                    new ClassData[]{}
            );

            return cd;
        }

        //SETTIMANA 3
        public static ArrayList<UtilizzoClassi> assegnazioneAuleSettimana3() {
            ArrayList<UtilizzoClassi> assegnazioni = new ArrayList<>();
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2C, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4B, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4C, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_3C, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_5B, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_5C, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_3B, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_4G, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1B, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2C, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4B, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4C, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_3C, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_5B, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_5C, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_3B, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_4G, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1B, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2C, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4B, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4C, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_3C, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_5B, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_5C, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_3B, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_4G, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1B, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2C, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4B, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4C, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_3C, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_5B, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_5C, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_3B, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_4G, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1B, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_2C, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4B, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_4C, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_3C, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_5B, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_5C, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_3B, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.B6, ClassData.CLASS_4G, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C3, ClassData.CLASS_1B, EGiorno.VENERDI));

            return assegnazioni;
        }


        //SETTIMANA 4
        public static IClassDataDDI partizioneIClassDataDDISettimana4() {
            IClassDataDDI cd = new ClassDataDDI_SettimanaFissa(
                    new ClassData[]{ClassData.CLASS_1B, ClassData.CLASS_1C, ClassData.CLASS_1D, ClassData.CLASS_1E, ClassData.CLASS_1F, ClassData.CLASS_2A, ClassData.CLASS_2B, ClassData.CLASS_2C, ClassData.CLASS_2D, ClassData.CLASS_2E, ClassData.CLASS_2F, ClassData.CLASS_2G, ClassData.CLASS_3B, ClassData.CLASS_3C, ClassData.CLASS_3D, ClassData.CLASS_4A, ClassData.CLASS_4B, ClassData.CLASS_4C, ClassData.CLASS_4D, ClassData.CLASS_4F, ClassData.CLASS_4G, ClassData.CLASS_5B, ClassData.CLASS_5C, ClassData.CLASS_5D, ClassData.CLASS_5E, ClassData.CLASS_5F, ClassData.CLASS_5G},
                    new ClassData[]{ClassData.CLASS_1A, ClassData.CLASS_2H, ClassData.CLASS_3A, ClassData.CLASS_3E, ClassData.CLASS_3F, ClassData.CLASS_3H, ClassData.CLASS_4E, ClassData.CLASS_4H, ClassData.CLASS_5A},
                    new ClassData[]{}
            );

            return cd;
        }

        //SETTIMANA 4
        public static ArrayList<UtilizzoClassi> assegnazioneAuleSettimana4() {
            ArrayList<UtilizzoClassi> assegnazioni = new ArrayList<>();
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_3H, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_3E, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4H, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_3A, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_5A, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_3F, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_4E, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_2H, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1A, EGiorno.LUNEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_3H, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_3E, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4H, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_3A, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_5A, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_3F, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_4E, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_2H, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1A, EGiorno.MARTEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_3H, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_3E, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4H, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_3A, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_5A, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_3F, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_4E, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_2H, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1A, EGiorno.MERCOLEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_3H, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_3E, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4H, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_3A, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_5A, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_3F, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_4E, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_2H, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1A, EGiorno.GIOVEDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A2, ClassData.CLASS_3H, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C1, ClassData.CLASS_3E, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.D1, ClassData.CLASS_4H, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C5, ClassData.CLASS_3A, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.E1, ClassData.CLASS_5A, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A1, ClassData.CLASS_3F, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.A3, ClassData.CLASS_4E, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C7, ClassData.CLASS_2H, EGiorno.VENERDI));
            assegnazioni.add(new UtilizzoClassi(RoomData.C2, ClassData.CLASS_1A, EGiorno.VENERDI));

            return assegnazioni;
        }

    }


    public static void main(String[] args) throws IOException {


        final VincoliSostituzioni_2021_n08_settimana_26ott_30ott_75_PERCENTO_SETTIMANA1 l = new VincoliSostituzioni_2021_n08_settimana_26ott_30ott_75_PERCENTO_SETTIMANA1();

        //if (true) throw new IllegalArgumentException("STOP DEBUG");
        MotoreSostituzioneAule3.doTaskFromTXT_CSV(
                l,
                new File(root, "Orario Classi Completo.txt"),
                new File(root, "Orario Tabella Globale.csv"),
                new File(root, "Orario Sostegno Orizzontale Semplice.txt"),
                null, new File(root, "01-output"),
                l.filtroAuleSpostamenti(), false, true);


    }


    static final boolean CALCOLA_DDI_PRESENZA = false;

    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {
        Map<String, String> map = new TreeMap<>();
        map.put("A027_POTENZ>SUPPL", "SUPPL12");
        map.put("CERULLO", "MARGUCCIO");
        map.put("A011_PART TIME", "*SUPPL1");
        map.put("A011_RESIDUE/CEDUTE*", "*SUPPL2");//elisa biondi - completa con tivoli
        map.put("A019_PART_TIME_3E*", "*SUPPL3");
        map.put("A019_PART_TIME_4G*", "*SUPPL4");
        map.put("A027_PART TIME", "*SUPPL5");
        map.put("A048_RESIDUE", "*SUPPL6");
        map.put("A050_PART TIME", "*SUPPL7");
        map.put("EX_BIONDI", "*SUPPL8");
        map.put("EX_LATINI", "*SUPPL9");
        map.put("EX_LIBERATI", "*SUPPL10");//valeria.turriziani@scuolesuperioridizagarolo.edu.it
        map.put("EX_MARTINI", "*SUPPL11");
        map.put("FERRIGNO", "MASTROCESARE [FERRIGNO*]");
        //map.put("MARCHESE>SUPPL", "MARCHESE**");
        //map.put("TASSAN>SUPPL", "TASSAN**");
        renameDocente(orarioTotale, map);


        if (CALCOLA_DDI_PRESENZA) {
            //elaborazione DDI/PRESENZA OTTIMA
            int numCicliRicerca = 500000;
            IClassDataDDI[] bestDDIArray = OrarioDDIEngine_SettimaneAlterne.dosomething(orarioTotale, numCicliRicerca, new OrarioDDIEngine_SettimaneAlterne.OrarioDDIEngine_SettimaneAlterne_DDIAssign_4settimane());

            //PRIMA SETTIMANA SI USANO LE DDI
            final Set<RoomData> skippedRoom = new TreeSet<>(Arrays.asList(
                    RoomData.E5, RoomData.B1, RoomData.B2, RoomData.B3, RoomData.B4, RoomData.B5,
                    RoomData.F2, RoomData.PAL2,
                    RoomData.C6,
                    RoomData.A8,
                    RoomData.D5
            ));

            //controlla se intersezioni presenza
            ClassDataDDI_SettimanaFissa.checkPresenzeNonDistinti(bestDDIArray);

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(bout);
            out.println("public static class DATA_ASSEGNAZIONI {");
            for (int i = 0, bestDDIArrayLength = bestDDIArray.length; i < bestDDIArrayLength; i++) {
                ClassDataDDI_SettimanaFissa classiSettimana = (ClassDataDDI_SettimanaFissa) bestDDIArray[i];
                ArrayList<UtilizzoClassi> assegnaule_settimana1;
                assegnaule_settimana1 = classiSettimana.computeAssegnazioniPRESENZA_E_FISSE(skippedRoom);


                out.println();
                out.println("//SETTIMANA " + (i + 1));
                out.println("public static IClassDataDDI partizioneIClassDataDDISettimana" + (i + 1) + "() {");
                out.println(classiSettimana.generateCodeClassDataDDI());
                out.println("return cd;}");
                out.println();
                out.println("//SETTIMANA " + (i + 1));
                out.println("public static ArrayList<UtilizzoClassi> assegnazioneAuleSettimana" + (i + 1) + "() {");

                out.println(classiSettimana.generateCodeAssegnazioni(assegnaule_settimana1));
                out.println("return assegnazioni;");
                out.println("}");
                out.println();

            }
            out.println("}");
            out.close();
            System.out.println(bout.toString());

            ClipboardUtil.copyClipboard(bout.toString());

            //TODO DEBUG - assegnazione settimana 1
            ArrayList<UtilizzoClassi> assegnaAule = bestDDIArray[0].computeAssegnazioniPRESENZA_E_FISSE(skippedRoom);



            assegnaAuleAdOrario(orarioTotale, assegnaAule);

            if (true) {
                throw new IllegalArgumentException("Utilizza la modalità statica per elaborare l'orario (CALCOLA_DDI_PRESENZA=false)");
            }
        } else {
            ArrayList<UtilizzoClassi> assegnaule;
            assegnaule = DATA_ASSEGNAZIONI.assegnazioneAuleSettimana1();

            AbstractVincoliSostituzioni.quarantena(assegnaule, ClassData.CLASS_5G, EGiorno.values());
            AbstractVincoliSostituzioni.quarantena(assegnaule, ClassData.CLASS_4E, EGiorno.values());
            AbstractVincoliSostituzioni.quarantena(assegnaule, ClassData.CLASS_5E, EGiorno.values());
            AbstractVincoliSostituzioni.quarantena(assegnaule, ClassData.CLASS_5A, EGiorno.values());
            AbstractVincoliSostituzioni.quarantena(assegnaule, ClassData.CLASS_5F, EGiorno.LUNEDI, EGiorno.MARTEDI, EGiorno.MERCOLEDI, EGiorno.GIOVEDI);
            AbstractVincoliSostituzioni.quarantena(assegnaule, ClassData.CLASS_3B, EGiorno.LUNEDI, EGiorno.MARTEDI, EGiorno.MERCOLEDI);



            IClassDataDDI iClassDataDDI = DATA_ASSEGNAZIONI.partizioneIClassDataDDISettimana1();
            OrarioDocentiComplessivo_SkipAlertReportDisposizioni d = new OrarioDocentiComplessivo_SkipAlertReportDisposizioni();


            //Docente: ALESSANDRONI
            // >> GIORNI DISPOSIZIONI DA SPOSTARE: [LUNEDI]
            // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): []
            d.ignore("ALESSANDRONI", EGiorno.LUNEDI, EOra.QUARTA);
            //Docente: CARABELLA
            // >> GIORNI DISPOSIZIONI DA SPOSTARE: [VENERDI]
            // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): [LUNEDI, MARTEDI, MERCOLEDI, GIOVEDI]
            spostaDisposizioni(orarioTotale, "CARABELLA", EGiorno.VENERDI, EOra.QUARTA, EGiorno.LUNEDI, EOra.PRIMA);
            //Docente: CARONI
            // >> GIORNI DISPOSIZIONI DA SPOSTARE: [LUNEDI, MARTEDI, GIOVEDI]
            // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): []
            d.ignore(spostaDisposizioni(orarioTotale, "CARONI", EGiorno.LUNEDI, EOra.SECONDA, EGiorno.MARTEDI, EOra.TERZA));
            d.ignore("CARONI", EGiorno.MARTEDI, EOra.SECONDA);
            d.ignore(spostaDisposizioni(orarioTotale, "CARONI", EGiorno.GIOVEDI, EOra.QUINTA, EGiorno.MARTEDI, EOra.SESTA));
            //Docente: CENTURIONI
            // >> GIORNI DISPOSIZIONI DA SPOSTARE: [VENERDI]
            // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): [LUNEDI, MERCOLEDI, GIOVEDI]
            d.ignore(spostaDisposizioni(orarioTotale, "CENTURIONI", EGiorno.VENERDI, EOra.QUARTA, EGiorno.GIOVEDI, EOra.TERZA));
            //Docente: CORTONI
            // >> GIORNI DISPOSIZIONI DA SPOSTARE: [LUNEDI, MARTEDI, MERCOLEDI]
            // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): []
            d.ignore("CORTONI", EGiorno.LUNEDI, EOra.TERZA);
            d.ignore(spostaDisposizioni(orarioTotale, "CORTONI", EGiorno.MARTEDI, EOra.QUARTA, EGiorno.LUNEDI, EOra.QUARTA));
            d.ignore(spostaDisposizioni(orarioTotale, "CORTONI", EGiorno.MERCOLEDI, EOra.SECONDA, EGiorno.LUNEDI, EOra.SESTA));
            //Docente: ROSINI
            // >> GIORNI DISPOSIZIONI DA SPOSTARE: [LUNEDI, MARTEDI, GIOVEDI]
            // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): [MERCOLEDI, VENERDI]
            d.ignore("ROSINI", EGiorno.LUNEDI, EOra.QUARTA);
            d.ignore(spostaDisposizioni(orarioTotale, "ROSINI", EGiorno.MARTEDI, EOra.QUINTA, EGiorno.VENERDI, EOra.TERZA   ));
            d.ignore(spostaDisposizioni(orarioTotale, "ROSINI", EGiorno.GIOVEDI, EOra.QUINTA, EGiorno.MERCOLEDI, EOra.QUINTA));
            //Docente: SACCENTI
            // >> GIORNI DISPOSIZIONI DA SPOSTARE: [LUNEDI, MARTEDI, MERCOLEDI]
            // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): []
            d.ignore("SACCENTI", EGiorno.LUNEDI, EOra.TERZA);
            spostaDisposizioni(orarioTotale, "SACCENTI", EGiorno.MARTEDI, EOra.SECONDA, EGiorno.LUNEDI, EOra.SECONDA);
            d.ignore(spostaDisposizioni(orarioTotale, "SACCENTI", EGiorno.MERCOLEDI, EOra.QUARTA, EGiorno.MARTEDI, EOra.TERZA));
            //Docente: SCHIAREA
            // >> GIORNI DISPOSIZIONI DA SPOSTARE: [LUNEDI, GIOVEDI]
            // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): [MARTEDI, VENERDI]
            d.ignore("SCHIAREA", EGiorno.LUNEDI, EOra.TERZA);
            d.ignore(spostaDisposizioni(orarioTotale, "SCHIAREA", EGiorno.GIOVEDI, EOra.TERZA, EGiorno.VENERDI, EOra.TERZA));

            //stampa report
            {
                System.out.println("PRESENZA SETTIMANA 1");
                System.out.println(((ClassDataDDI_SettimanaFissa) DATA_ASSEGNAZIONI.partizioneIClassDataDDISettimana1()).presenza);

                System.out.println("PRESENZA SETTIMANA 2");
                System.out.println(((ClassDataDDI_SettimanaFissa) DATA_ASSEGNAZIONI.partizioneIClassDataDDISettimana2()).presenza);

                System.out.println("PRESENZA SETTIMANA 3");
                System.out.println(((ClassDataDDI_SettimanaFissa) DATA_ASSEGNAZIONI.partizioneIClassDataDDISettimana3()).presenza);

                System.out.println("PRESENZA SETTIMANA 4");
                System.out.println(((ClassDataDDI_SettimanaFissa) DATA_ASSEGNAZIONI.partizioneIClassDataDDISettimana4()).presenza);

            }


            //controllo disposizioni da spostare
            System.out.println("DISPOSIZIONI DA SPOSTARE SETTIMANA 1");
            OrarioDocentiComplessivo o = new OrarioDocentiComplessivo();
            o.addAll(orarioTotale.getLezioni());
            o.assignDDI(iClassDataDDI);
            System.out.println(o.computeReportDisposizioniDaSpostare(d, true));
            System.out.println();

            //suddivisione classi
            System.out.println("Suddivisione classi 1");
            System.out.println(iClassDataDDI);
            System.out.println();

            //lezioni
            System.out.println("LEZIONI DA CASA 1");
            MatriceGiorniOre matriceGiorniOreDaCasa_sett1 = o.computeDDI_daCasa();
            System.out.println(matriceGiorniOreDaCasa_sett1);
            System.out.println();
            System.out.println("LEZIONI DA SCUOLA 1");
            MatriceGiorniOre matriceGiorniOreDaScuola_sett1 = o.computeDDI_daScuola();
            System.out.println(matriceGiorniOreDaScuola_sett1);

            assegnaAuleAdOrario(orarioTotale, assegnaule);

        }

    }


    @Override
    public Map<ClassData, ControlloCoperturaClassi_COVID.CoperturaClasse_COVID> reportQuarantene(BitOrarioGrigliaOrario orarioTotale) {
        List<ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID> assenti = new ArrayList<>();
        return ControlloCoperturaClassi_COVID.report(orarioTotale, assenti);
    }


    @Override
    protected void postOrarioBeforeFinalCheck(BitOrarioGrigliaOrario orarioTotale, LessonConstraintContainer l) {

        for (ClassData classe : ClassData.values()) {
            BitOrarioOraLezione lezioneInClasse = orarioTotale.getLezioneInClasse(EOra.SECONDA, EGiorno.VENERDI, classe);
            System.out.println(classe+"\t"+lezioneInClasse.getOra()+"\t"+lezioneInClasse.getDocentePrincipale());
        }

        for (ClassData classe : ClassData.values()) {
            BitOrarioOraLezione lezioneInClasse = orarioTotale.getLezioneInClasse(EOra.TERZA, EGiorno.VENERDI, classe);
            System.out.println(classe+"\t"+lezioneInClasse.getOra()+"\t"+lezioneInClasse.getDocentePrincipale());
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

    public VincoliSostituzioni_2021_n08_settimana_26ott_30ott_75_PERCENTO_SETTIMANA1 invoke(final BitOrarioGrigliaOrario orarioTotale,
                                                                                            final LessonConstraintContainer l) {
        dal = "26/10/2020";
        al = "30/10/2020";
        progressivo = "8";

        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());


        return this;
    }
}
