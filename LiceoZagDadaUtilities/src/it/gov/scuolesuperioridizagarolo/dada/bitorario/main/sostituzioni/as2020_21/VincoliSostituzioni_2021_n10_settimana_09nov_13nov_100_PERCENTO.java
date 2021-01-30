package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI.IClassDataDDI;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI.MatriceGiorniOre;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI.OrarioDocentiComplessivo;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario50percentoDDI.OrarioDocentiComplessivo_SkipAlertReportDisposizioni;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_OccupazioneAule_labsToo;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import utility.aule2020_21.UtilizzoClassi;
import utility.copertura_classi.ControlloCoperturaClassi_COVID;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_2021_n10_settimana_09nov_13nov_100_PERCENTO extends AbstractVincoliSostituzioni {

    static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/" +
            "scuolesuperioridizagarolo/dada/bitorario/main/sostituzioni/as2020_21/dati_orario/");


    public static void main(String[] args) throws IOException {


        final VincoliSostituzioni_2021_n10_settimana_09nov_13nov_100_PERCENTO l = new VincoliSostituzioni_2021_n10_settimana_09nov_13nov_100_PERCENTO();

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
        //map.put("A027_POTENZ>SUPPL", "GATTO [QUARESIMA*]");
        //map.put("CERULLO", "MARGUCCIO");
        //map.put("MILLOZZI", "MILLOZZI S.");
        //map.put("A011_PART TIME", "*SUPPL A011");
        //map.put("A011_RESIDUE/CEDUTE*", "BIONDI (provvisoria)");//elisa biondi - completa con tivoli - annuale
        //map.put("A019_PART_TIME_3E*", "*SUPPL1 A019");
        //map.put("A019_PART_TIME_4G*", "*SUPPL2 A019");
        //map.put("A027_PART TIME", "CARA");
        //map.put("A048_RESIDUE", "*SUPPL A048");
        map.put("BIONDI", "BIONDI E.");
        map.put("FERRIGNO", "MASTROCESARE [FERRIGNO*]");
        map.put("CERULLO", "MARGUCCIO [CERULLO*]");
        map.put("QUARESIMA", "GATTO [QUARESIMA*]");
        map.put("A050_PART TIME", "LEGGERI");
        map.put("EX_BIONDI", "BIONDI M.");
        //map.put("EX_LATINI", "MILLOZZI C.");
        //map.put("EX_LIBERATI", "TURRIZIANI");//valeria.turriziani@scuolesuperioridizagarolo.edu.it - annuale
        map.put("EX_MARTINI", "*SUPPL/2 (ARTE)");
        //map.put("FERRIGNO", "MASTROCESARE [FERRIGNO*]");
        //map.put("MARCHESE>SUPPL", "MARCHESE**");
        //map.put("TASSAN>SUPPL", "TASSAN**");
        renameDocente(orarioTotale, map);


        ArrayList<UtilizzoClassi> assegnaule;
        assegnaule = VincoliSostituzioni_2021_n08_settimana_26ott_30ott_75_PERCENTO_SETTIMANA1.DATA_ASSEGNAZIONI.assegnazioneAuleSettimana2();
        IClassDataDDI iClassDataDDI = VincoliSostituzioni_2021_n08_settimana_26ott_30ott_75_PERCENTO_SETTIMANA1.DATA_ASSEGNAZIONI.partizioneIClassDataDDISettimana2();
        OrarioDocentiComplessivo_SkipAlertReportDisposizioni d = new OrarioDocentiComplessivo_SkipAlertReportDisposizioni();

        for (ClassData c : ClassData.values()) {
            AbstractVincoliSostituzioni.quarantena(assegnaule, c, EGiorno.values());
        }




        //controllo disposizioni da spostare
        System.out.println("DISPOSIZIONI DA SPOSTARE SETTIMANA 2");
        OrarioDocentiComplessivo o = new OrarioDocentiComplessivo();
        o.addAll(orarioTotale.getLezioni());
        o.assignDDI(iClassDataDDI);
        System.out.println(o.computeReportDisposizioniDaSpostare(d, true));
        System.out.println();

        //suddivisione classi
        System.out.println("Suddivisione classi 2");
        System.out.println(iClassDataDDI);
        System.out.println();

        //lezioni
        System.out.println("LEZIONI DA CASA 2");
        MatriceGiorniOre matriceGiorniOreDaCasa_sett1 = o.computeDDI_daCasa();
        System.out.println(matriceGiorniOreDaCasa_sett1);
        System.out.println();
        System.out.println("LEZIONI DA SCUOLA 2");
        MatriceGiorniOre matriceGiorniOreDaScuola_sett1 = o.computeDDI_daScuola();
        System.out.println(matriceGiorniOreDaScuola_sett1);

        assegnaAuleAdOrario(orarioTotale, assegnaule);

    }


    @Override
    public Map<ClassData, ControlloCoperturaClassi_COVID.CoperturaClasse_COVID> reportQuarantene(BitOrarioGrigliaOrario orarioTotale) {
        List<ControlloCoperturaClassi_COVID.AssenzaDocenti_COVID> assenti = new ArrayList<>();
        return ControlloCoperturaClassi_COVID.report(orarioTotale, assenti);
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

    public VincoliSostituzioni_2021_n10_settimana_09nov_13nov_100_PERCENTO invoke(final BitOrarioGrigliaOrario orarioTotale,
                                                                                  final LessonConstraintContainer l) {
        dal = "09/11/2020";
        al = "13/11/2020";
        progressivo = "10";

        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());


        return this;
    }
}
