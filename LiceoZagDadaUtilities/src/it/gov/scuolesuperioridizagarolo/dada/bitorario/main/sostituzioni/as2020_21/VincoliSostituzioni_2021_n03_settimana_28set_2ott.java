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
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import utility.aule2020_21.UtilizzoClassi;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_2021_n03_settimana_28set_2ott extends AbstractVincoliSostituzioni {


    public static void main(String[] args) throws IOException {


        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/" +
                "scuolesuperioridizagarolo/dada/bitorario/main/sostituzioni/as2020_21/dati_orario");
        final VincoliSostituzioni_2021_n03_settimana_28set_2ott l = new VincoliSostituzioni_2021_n03_settimana_28set_2ott();

        //if (true) throw new IllegalArgumentException("STOP DEBUG");
        MotoreSostituzioneAule3.doTaskFromTXT(
                l,
                new File(root, "Orario Allocazione Aule.txt"),
                null,
                new File(root, "Orario Professori Orizzontale Semplice.txt"),
                new File(root, "Orario Sostegno Orizzontale Semplice.txt"),
                null, new File(root, "01-output"),
                l.filtroAuleSpostamenti(), false, true);
    }


    public ArrayList<UtilizzoClassi> assegnaule() {


        ArrayList<UtilizzoClassi>assegnazioni=new ArrayList<>();
        assegnazioni.add(new UtilizzoClassi(RoomData.A8,ClassData.CLASS_5D,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2,ClassData.CLASS_5F,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6,ClassData.CLASS_5G,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1,ClassData.CLASS_2A,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1,ClassData.CLASS_2C,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5,ClassData.CLASS_3E,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5,ClassData.CLASS_4A,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1,ClassData.CLASS_4B,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2,ClassData.CLASS_4C,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1,ClassData.CLASS_5E,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3,ClassData.CLASS_3C,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1,ClassData.CLASS_4F,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2,ClassData.CLASS_4H,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3,ClassData.CLASS_5B,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5,ClassData.CLASS_2E,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7,ClassData.CLASS_3A,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4,ClassData.CLASS_3F,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6,ClassData.CLASS_5A,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2,ClassData.CLASS_5C,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3,ClassData.CLASS_1D,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4,ClassData.CLASS_1C,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5,ClassData.CLASS_1E,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3,ClassData.CLASS_1F,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4,ClassData.CLASS_4G,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2,ClassData.CLASS_1B,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3,ClassData.CLASS_2F,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4,ClassData.CLASS_4D,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2,ClassData.CLASS_1A,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6,ClassData.CLASS_2B,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7,ClassData.CLASS_2D,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A8,ClassData.CLASS_5D,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2,ClassData.CLASS_5F,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6,ClassData.CLASS_5G,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1,ClassData.CLASS_3H,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1,ClassData.CLASS_2A,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5,ClassData.CLASS_2G,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5,ClassData.CLASS_3E,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1,ClassData.CLASS_4A,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2,ClassData.CLASS_4C,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1,ClassData.CLASS_5E,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3,ClassData.CLASS_4F,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1,ClassData.CLASS_4H,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2,ClassData.CLASS_5B,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3,ClassData.CLASS_2E,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5,ClassData.CLASS_3F,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7,ClassData.CLASS_5A,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4,ClassData.CLASS_5C,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6,ClassData.CLASS_4E,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2,ClassData.CLASS_1D,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3,ClassData.CLASS_2H,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4,ClassData.CLASS_3B,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5,ClassData.CLASS_1C,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3,ClassData.CLASS_1E,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4,ClassData.CLASS_1F,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2,ClassData.CLASS_3D,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3,ClassData.CLASS_4G,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4,ClassData.CLASS_1B,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2,ClassData.CLASS_2F,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6,ClassData.CLASS_1A,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7,ClassData.CLASS_2B,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A8,ClassData.CLASS_5D,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2,ClassData.CLASS_5F,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6,ClassData.CLASS_3H,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1,ClassData.CLASS_2C,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1,ClassData.CLASS_2G,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5,ClassData.CLASS_3E,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5,ClassData.CLASS_4A,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1,ClassData.CLASS_4B,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2,ClassData.CLASS_4C,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1,ClassData.CLASS_3C,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3,ClassData.CLASS_5B,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1,ClassData.CLASS_2E,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2,ClassData.CLASS_3A,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3,ClassData.CLASS_3F,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5,ClassData.CLASS_5A,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7,ClassData.CLASS_4E,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4,ClassData.CLASS_1D,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6,ClassData.CLASS_2H,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2,ClassData.CLASS_3B,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3,ClassData.CLASS_1C,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4,ClassData.CLASS_1E,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5,ClassData.CLASS_1F,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3,ClassData.CLASS_3D,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4,ClassData.CLASS_4G,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2,ClassData.CLASS_1B,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3,ClassData.CLASS_2F,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4,ClassData.CLASS_4D,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2,ClassData.CLASS_1A,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6,ClassData.CLASS_2B,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7,ClassData.CLASS_2D,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A8,ClassData.CLASS_5F,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2,ClassData.CLASS_5G,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6,ClassData.CLASS_3H,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1,ClassData.CLASS_2A,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1,ClassData.CLASS_2C,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5,ClassData.CLASS_2G,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5,ClassData.CLASS_4A,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1,ClassData.CLASS_4B,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2,ClassData.CLASS_4C,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1,ClassData.CLASS_5E,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3,ClassData.CLASS_3C,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1,ClassData.CLASS_4F,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2,ClassData.CLASS_4H,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3,ClassData.CLASS_3A,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5,ClassData.CLASS_3F,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7,ClassData.CLASS_5A,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4,ClassData.CLASS_5C,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6,ClassData.CLASS_4E,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2,ClassData.CLASS_1D,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3,ClassData.CLASS_2H,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4,ClassData.CLASS_3B,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5,ClassData.CLASS_1C,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3,ClassData.CLASS_1E,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4,ClassData.CLASS_1F,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2,ClassData.CLASS_3D,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3,ClassData.CLASS_4G,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4,ClassData.CLASS_1B,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2,ClassData.CLASS_4D,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6,ClassData.CLASS_1A,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7,ClassData.CLASS_2D,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A8,ClassData.CLASS_5D,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2,ClassData.CLASS_5G,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6,ClassData.CLASS_3H,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1,ClassData.CLASS_2A,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1,ClassData.CLASS_2C,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5,ClassData.CLASS_2G,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5,ClassData.CLASS_3E,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1,ClassData.CLASS_4B,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2,ClassData.CLASS_5E,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1,ClassData.CLASS_3C,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3,ClassData.CLASS_4F,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1,ClassData.CLASS_4H,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2,ClassData.CLASS_5B,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3,ClassData.CLASS_2E,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5,ClassData.CLASS_3A,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7,ClassData.CLASS_5C,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4,ClassData.CLASS_4E,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6,ClassData.CLASS_1D,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2,ClassData.CLASS_2H,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3,ClassData.CLASS_3B,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4,ClassData.CLASS_1C,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5,ClassData.CLASS_1E,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3,ClassData.CLASS_1F,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4,ClassData.CLASS_3D,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2,ClassData.CLASS_1B,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3,ClassData.CLASS_2F,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4,ClassData.CLASS_4D,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2,ClassData.CLASS_1A,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6,ClassData.CLASS_2B,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7,ClassData.CLASS_2D,EGiorno.VENERDI));
        return assegnazioni;

    }


    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {
        ArrayList<UtilizzoClassi> assegnaule = assegnaule();
        ArrayList<BitOrarioOraLezione> lezioni = new ArrayList<>(orarioTotale.getLezioni());

        for (BitOrarioOraLezione l : lezioni) {
            if (l.getTipoLezione() == BitOrarioOraEnumTipoLezione.LEZIONE_CON_COMPRESENZA
                    || l.getTipoLezione() == BitOrarioOraEnumTipoLezione.LEZIONE_SINGOLA) {
                ClassData classe = l.getClasse();
                EGiorno giorno = l.getGiorno();


                orarioTotale.removeLezione(l);
                UtilizzoClassi search = search(classe, giorno, assegnaule);
                orarioTotale.addLezione(l.clonaLezioneInAltraAula(search.room));
            }
        }


    }


    @Override
    protected void postOrarioBeforeFinalCheck(BitOrarioGrigliaOrario orarioTotale, LessonConstraintContainer l) {
    }

    @Override
    protected FilterAule[] filtroAuleSpostamenti() {
        return new FilterAule[]{
                FilterAule.LABORATORI_MAI,
                FilterAule.LABORATORI_SOLO_COMPATIBILI,
                FilterAule.LABORATORI_SEMPRE,
        };
    }

    public VincoliSostituzioni_2021_n03_settimana_28set_2ott invoke(final BitOrarioGrigliaOrario orarioTotale,
                                                                    final LessonConstraintContainer l) {
        dal = "28/09/2020";
        al = "02/10/2020";
        progressivo = "3";

        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());


        return this;
    }
}
