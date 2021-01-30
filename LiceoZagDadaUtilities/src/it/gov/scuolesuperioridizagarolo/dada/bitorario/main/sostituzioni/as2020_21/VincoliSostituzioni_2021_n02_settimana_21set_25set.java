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
public class VincoliSostituzioni_2021_n02_settimana_21set_25set extends AbstractVincoliSostituzioni {
    private static Map<String, Integer> oreDisposizioneDaTogliere() {
        String s = "COCCI\t4\n" +
                "\t\n" +
                "\t\n" +
                "CERRI\t3\n" +
                "\t\n" +
                "\t\n" +
                "GENTILE\t6\n" +
                "\t\n" +
                "\t\n" +
                "CORSI\t3\n" +
                "\t\n" +
                "\t\n" +
                "MATTOZZI\t4\n" +
                "\t\n" +
                "\t\n" +
                "MATTEUCCI\t4\n" +
                "\t\n" +
                "\t\n" +
                "PANEPUCCIA\t4\n" +
                "\t\n" +
                "\t\n" +
                "FUSANI\t2\n" +
                "\t\n" +
                "\t\n" +
                "BALZAROTTI\t3\n" +
                "\t\n" +
                "\t\n" +
                "MONEGO\t4\n" +
                "\t\n" +
                "\t\n" +
                "ALESSANDRONI\t4\n" +
                "\t\n" +
                "\t\n" +
                "MATTETTI\t5\n" +
                "\t\n" +
                "\t\n" +
                "CENTRACCHIO\t5\n" +
                "\t\n" +
                "\t\n" +
                "MARCHESE\t2\n" +
                "\t\n" +
                "\t\n" +
                "CENTURIONI\t6\n" +
                "\t\n" +
                "\t\n" +
                "BORRELLO\t4\n" +
                "\t\n" +
                "\t\n" +
                "FABRONI\t3\n" +
                "\t\n" +
                "\t\n" +
                "DI_ROSA\t1\n" +
                "\t\n" +
                "\t\n" +
                "CARLI\t1\n" +
                "\t\n" +
                "\t\n" +
                "PAGLIARI\t6\n" +
                "\t\n" +
                "\t\n" +
                "GUGLIELMOTTI\t4\n" +
                "\t\n" +
                "\t\n" +
                "VOLPI\t2\n" +
                "\t";
        String[] righe = s.split("\n");
        Map<String, Integer> ris = new TreeMap<>();
        for (String r : righe) {

            String[] split = r.split("[ \t]+");
            if (split.length <= 1) continue;
            String docente = split[0].toUpperCase().replace("_", " ");
            int ore = Integer.parseInt(split[1].toUpperCase().replace("H", ""));
            ris.put(docente, ore);
        }
        return ris;
    }


    public static void main(String[] args) throws IOException {


        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/" +
                "scuolesuperioridizagarolo/dada/bitorario/main/sostituzioni/as2020_21/dati_orario");
        final VincoliSostituzioni_2021_n02_settimana_21set_25set l = new VincoliSostituzioni_2021_n02_settimana_21set_25set();

        //if (true) throw new IllegalArgumentException("STOP DEBUG");
        MotoreSostituzioneAule3.doTaskFromTXT(
                l,
                new File(root, "Orario Allocazione Aule.txt"),
                null,
                new File(root, "Orario Professori Orizzontale Semplice.txt"),
                null, null, new File(root, "01-output"),
                l.filtroAuleSpostamenti(), false, true);
    }

    public ArrayList<UtilizzoClassi> assegnaule() {
        ArrayList<UtilizzoClassi>assegnazioni=new ArrayList<>();
        assegnazioni.add(new UtilizzoClassi(RoomData.A8,ClassData.CLASS_5D,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2,ClassData.CLASS_5F,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6,ClassData.CLASS_5G,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5,ClassData.CLASS_2C,EGiorno.LUNEDI));
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
        assegnazioni.add(new UtilizzoClassi(RoomData.E2,ClassData.CLASS_4E,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3,ClassData.CLASS_1D,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4,ClassData.CLASS_2H,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5,ClassData.CLASS_3B,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2,ClassData.CLASS_1C,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3,ClassData.CLASS_1E,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4,ClassData.CLASS_1F,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1,ClassData.CLASS_3D,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2,ClassData.CLASS_4G,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3,ClassData.CLASS_1B,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4,ClassData.CLASS_2F,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1,ClassData.CLASS_4D,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6,ClassData.CLASS_1A,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7,ClassData.CLASS_2D,EGiorno.LUNEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A8,ClassData.CLASS_5D,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2,ClassData.CLASS_5G,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6,ClassData.CLASS_3H,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5,ClassData.CLASS_2A,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5,ClassData.CLASS_2C,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1,ClassData.CLASS_2G,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2,ClassData.CLASS_3E,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1,ClassData.CLASS_4B,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3,ClassData.CLASS_5E,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1,ClassData.CLASS_3C,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2,ClassData.CLASS_4F,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3,ClassData.CLASS_4H,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5,ClassData.CLASS_5B,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7,ClassData.CLASS_2E,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4,ClassData.CLASS_3F,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6,ClassData.CLASS_5C,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2,ClassData.CLASS_4E,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3,ClassData.CLASS_1D,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4,ClassData.CLASS_2H,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5,ClassData.CLASS_3B,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2,ClassData.CLASS_1C,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3,ClassData.CLASS_1E,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4,ClassData.CLASS_1F,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1,ClassData.CLASS_4G,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2,ClassData.CLASS_1B,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3,ClassData.CLASS_2F,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4,ClassData.CLASS_4D,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1,ClassData.CLASS_1A,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6,ClassData.CLASS_2B,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7,ClassData.CLASS_2D,EGiorno.MARTEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A8,ClassData.CLASS_5D,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2,ClassData.CLASS_5F,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6,ClassData.CLASS_5G,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5,ClassData.CLASS_3H,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5,ClassData.CLASS_2A,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1,ClassData.CLASS_2G,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2,ClassData.CLASS_3E,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1,ClassData.CLASS_4A,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3,ClassData.CLASS_4B,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1,ClassData.CLASS_4C,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2,ClassData.CLASS_3C,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3,ClassData.CLASS_4H,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5,ClassData.CLASS_5B,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7,ClassData.CLASS_2E,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4,ClassData.CLASS_3A,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6,ClassData.CLASS_5A,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2,ClassData.CLASS_5C,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3,ClassData.CLASS_1D,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4,ClassData.CLASS_2H,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5,ClassData.CLASS_3B,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2,ClassData.CLASS_1C,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3,ClassData.CLASS_1E,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4,ClassData.CLASS_1F,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1,ClassData.CLASS_3D,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2,ClassData.CLASS_1B,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3,ClassData.CLASS_2F,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4,ClassData.CLASS_4D,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1,ClassData.CLASS_1A,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6,ClassData.CLASS_2B,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7,ClassData.CLASS_2D,EGiorno.MERCOLEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A8,ClassData.CLASS_5D,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2,ClassData.CLASS_5F,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6,ClassData.CLASS_3H,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5,ClassData.CLASS_2A,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5,ClassData.CLASS_2C,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1,ClassData.CLASS_2G,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2,ClassData.CLASS_3E,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1,ClassData.CLASS_4A,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3,ClassData.CLASS_4B,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1,ClassData.CLASS_4C,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2,ClassData.CLASS_5E,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3,ClassData.CLASS_4F,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5,ClassData.CLASS_4H,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7,ClassData.CLASS_3A,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4,ClassData.CLASS_3F,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6,ClassData.CLASS_5A,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2,ClassData.CLASS_5C,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3,ClassData.CLASS_4E,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4,ClassData.CLASS_1D,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5,ClassData.CLASS_2H,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2,ClassData.CLASS_1C,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3,ClassData.CLASS_1E,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4,ClassData.CLASS_1F,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1,ClassData.CLASS_3D,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2,ClassData.CLASS_4G,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3,ClassData.CLASS_1B,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4,ClassData.CLASS_2F,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1,ClassData.CLASS_1A,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6,ClassData.CLASS_2B,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7,ClassData.CLASS_2D,EGiorno.GIOVEDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A8,ClassData.CLASS_5F,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A2,ClassData.CLASS_5G,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C6,ClassData.CLASS_3H,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C5,ClassData.CLASS_2A,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D5,ClassData.CLASS_2C,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E1,ClassData.CLASS_2G,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.F2,ClassData.CLASS_3E,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A1,ClassData.CLASS_4A,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A3,ClassData.CLASS_4C,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B1,ClassData.CLASS_5E,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B2,ClassData.CLASS_3C,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B3,ClassData.CLASS_4F,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B5,ClassData.CLASS_5B,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C7,ClassData.CLASS_2E,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B4,ClassData.CLASS_3A,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B6,ClassData.CLASS_3F,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E2,ClassData.CLASS_5A,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E3,ClassData.CLASS_5C,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E4,ClassData.CLASS_4E,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.E5,ClassData.CLASS_1D,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C2,ClassData.CLASS_3B,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C3,ClassData.CLASS_1C,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C4,ClassData.CLASS_1E,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D1,ClassData.CLASS_1F,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D2,ClassData.CLASS_3D,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D3,ClassData.CLASS_4G,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.D4,ClassData.CLASS_1B,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.C1,ClassData.CLASS_4D,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.A6,ClassData.CLASS_1A,EGiorno.VENERDI));
        assegnazioni.add(new UtilizzoClassi(RoomData.B7,ClassData.CLASS_2B,EGiorno.VENERDI));
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



        //ore disposizione da rimuovere
        Map<String, Integer> disposizioneDaTogliere = oreDisposizioneDaTogliere();
        TreeSet<String> docenti = orarioTotale.getDocenti();
        for (String s : disposizioneDaTogliere.keySet()) {
            if (!docenti.contains(s)) {
                throw new IllegalArgumentException("Docente " + s + " non trovato in " + docenti);
            }
        }

        //rimuove eventuali disposizioni
        for (Map.Entry<String, Integer> e : disposizioneDaTogliere.entrySet()) {
            String docente = e.getKey();
            Integer ore = e.getValue();
            for (int i = 1; i <= ore; i++) {
                if (!rimuoviDisposizione(orarioTotale, docente)) {
                     throw new IllegalArgumentException("Impossibile rimuovere una disposizione per " + docente);
                }
            }
        }


    }

    private static boolean rimuoviDisposizione(final BitOrarioGrigliaOrario orarioTotale, String docente) {
        List<EGiorno> eGiornos = new ArrayList<>(Arrays.asList(EGiorno.valuesGiorniDiLezione()));
        for (EGiorno giorno : eGiornos) {
            int oreLezione = 0;
            for (EOra ora : EOra.valuesOreDiLezione()) {
                BitOrarioOraLezione l = orarioTotale.getLezioneConDocente(docente, giorno, ora);
                if (l != null)
                    oreLezione++;
            }
            if (oreLezione > 2) {
                EOra[] ore = EOra.valuesOreDiLezione();
                for (EOra ora : ore) {
                    BitOrarioOraLezione l = orarioTotale.getLezioneConDocente(docente, giorno, ora);
                    if (l != null) {
                        if (l.isDisposizionePura()) {
                            orarioTotale.removeLezione(l);
                            // orarioTotale.addLezione(BitOrarioOraLezione.creaOraProgetto(l.getDocentePrincipale(), "-", l.getOra(), l.getGiorno()));
                            return true;
                        }
                        //se non disposizione salta al giorno seguente
                        break;
                    }
                }
                List<EOra> oreInvertite = new ArrayList<>(Arrays.asList(ore));
                Collections.reverse(oreInvertite);
                for (EOra ora : oreInvertite) {
                    BitOrarioOraLezione l = orarioTotale.getLezioneConDocente(docente, giorno, ora);
                    if (l != null) {
                        if (l.isDisposizionePura()) {
                            orarioTotale.removeLezione(l);
                            // orarioTotale.addLezione(BitOrarioOraLezione.creaOraProgetto(l.getDocentePrincipale(), "-", l.getOra(), l.getGiorno()));
                            return true;
                        }
                        //se non disposizione salta al giorno seguente
                        break;
                    }
                }
            }
        }

        for (EGiorno giorno : eGiornos) {
            int oreLezione = 0;
            for (EOra ora : EOra.valuesOreDiLezione()) {
                BitOrarioOraLezione l = orarioTotale.getLezioneConDocente(docente, giorno, ora);
                if (l != null)
                    oreLezione++;
            }
            EOra[] ore = EOra.valuesOreDiLezione();
            for (EOra ora : ore) {
                BitOrarioOraLezione l = orarioTotale.getLezioneConDocente(docente, giorno, ora);
                if (l != null) {
                    if (l.isDisposizionePura()) {
                        orarioTotale.removeLezione(l);
                        // orarioTotale.addLezione(BitOrarioOraLezione.creaOraProgetto(l.getDocentePrincipale(), "-", l.getOra(), l.getGiorno()));
                        return true;
                    }
                }
            }
            List<EOra> oreInvertite = new ArrayList<>(Arrays.asList(ore));
            Collections.reverse(oreInvertite);
            for (EOra ora : oreInvertite) {
                BitOrarioOraLezione l = orarioTotale.getLezioneConDocente(docente, giorno, ora);
                if (l != null) {
                    if (l.isDisposizionePura()) {
                        orarioTotale.removeLezione(l);
                        // orarioTotale.addLezione(BitOrarioOraLezione.creaOraProgetto(l.getDocentePrincipale(), "-", l.getOra(), l.getGiorno()));
                        return true;
                    }
                }
            }
        }


        return false;

    }

    private void sostituisciConDisposizione(BitOrarioGrigliaOrario orarioTotale, BitOrarioOraLezione l) {
        orarioTotale.removeLezione(l);
        String docente;
        docente = l.getDocentePrincipale();
        if (docente != null)
            orarioTotale.addLezione(BitOrarioOraLezione.creaOraDisposizione(docente, l.getOra(), l.getGiorno()));
        docente = l.getDocenteCompresenza();
        if (docente != null)
            orarioTotale.addLezione(BitOrarioOraLezione.creaOraDisposizione(docente, l.getOra(), l.getGiorno()));
        docente = l.getDocenteSostegno();
        if (docente != null)
            orarioTotale.addLezione(BitOrarioOraLezione.creaOraDisposizione(docente, l.getOra(), l.getGiorno()));
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

    public VincoliSostituzioni_2021_n02_settimana_21set_25set invoke(final BitOrarioGrigliaOrario orarioTotale,
                                                                     final LessonConstraintContainer l) {
        dal = "21/09/2020";
        al = "25/09/2020";

        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());





        return this;
    }
}
