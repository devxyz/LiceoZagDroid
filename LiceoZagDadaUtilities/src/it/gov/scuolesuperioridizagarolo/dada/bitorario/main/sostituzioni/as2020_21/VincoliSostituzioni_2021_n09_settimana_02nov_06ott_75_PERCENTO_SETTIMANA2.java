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
public class VincoliSostituzioni_2021_n09_settimana_02nov_06ott_75_PERCENTO_SETTIMANA2 extends AbstractVincoliSostituzioni {

    static final File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/" +
            "scuolesuperioridizagarolo/dada/bitorario/main/sostituzioni/as2020_21/dati_orario/");


    public static void main(String[] args) throws IOException {


        final VincoliSostituzioni_2021_n09_settimana_02nov_06ott_75_PERCENTO_SETTIMANA2 l = new VincoliSostituzioni_2021_n09_settimana_02nov_06ott_75_PERCENTO_SETTIMANA2();

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
        map.put("A027_POTENZ>SUPPL", "GATTO [QUARESIMA*]");
        map.put("CERULLO", "MARGUCCIO");
        map.put("MILLOZZI", "MILLOZZI S.");
        map.put("A011_PART TIME", "*SUPPL A011");
        map.put("A011_RESIDUE/CEDUTE*", "BIONDI (provvisoria)");//elisa biondi - completa con tivoli - annuale
        map.put("A019_PART_TIME_3E*", "*SUPPL1 A019");
        map.put("A019_PART_TIME_4G*", "*SUPPL2 A019");
        map.put("A027_PART TIME", "CARA");
        map.put("A048_RESIDUE", "*SUPPL A048");
        map.put("A050_PART TIME", "*SUPPL A050");
        map.put("EX_BIONDI", "*SUPPL1 A050");
        map.put("EX_LATINI", "MILLOZZI C.");
        map.put("EX_LIBERATI", "TURRIZIANI");//valeria.turriziani@scuolesuperioridizagarolo.edu.it - annuale
        map.put("EX_MARTINI", "*SUPPL2 A050");
        map.put("FERRIGNO", "MASTROCESARE [FERRIGNO*]");
        //map.put("MARCHESE>SUPPL", "MARCHESE**");
        //map.put("TASSAN>SUPPL", "TASSAN**");
        renameDocente(orarioTotale, map);


        ArrayList<UtilizzoClassi> assegnaule;
        assegnaule = VincoliSostituzioni_2021_n08_settimana_26ott_30ott_75_PERCENTO_SETTIMANA1.DATA_ASSEGNAZIONI.assegnazioneAuleSettimana2();
        IClassDataDDI iClassDataDDI = VincoliSostituzioni_2021_n08_settimana_26ott_30ott_75_PERCENTO_SETTIMANA1.DATA_ASSEGNAZIONI.partizioneIClassDataDDISettimana2();
        OrarioDocentiComplessivo_SkipAlertReportDisposizioni d = new OrarioDocentiComplessivo_SkipAlertReportDisposizioni();

        AbstractVincoliSostituzioni.quarantena(assegnaule, ClassData.CLASS_5G, EGiorno.LUNEDI, EGiorno.MARTEDI, EGiorno.MERCOLEDI, EGiorno.GIOVEDI);
        for (ClassData c : ClassData.values()) {
            AbstractVincoliSostituzioni.quarantena(assegnaule, c, EGiorno.GIOVEDI, EGiorno.VENERDI);
        }


        //Docente: BENEDETTI
        // >> GIORNI DISPOSIZIONI DA SPOSTARE: [VENERDI]
        // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): [LUNEDI, MARTEDI, MERCOLEDI, GIOVEDI]
        d.ignore(spostaDisposizioni(orarioTotale, "BENEDETTI", EGiorno.VENERDI, EOra.QUARTA, EGiorno.MERCOLEDI, EOra.TERZA));
        //Docente: CARABELLA
        // >> GIORNI DISPOSIZIONI DA SPOSTARE: [LUNEDI, MERCOLEDI, VENERDI]
        // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): []
        d.ignore("CARABELLA", EGiorno.LUNEDI, EOra.SECONDA);
        d.ignore("CARABELLA", EGiorno.MERCOLEDI, EOra.TERZA);
        d.ignore("CARABELLA", EGiorno.VENERDI, EOra.QUARTA);
        //Docente: CARONI
        // >> GIORNI DISPOSIZIONI DA SPOSTARE: [MARTEDI]
        // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): [LUNEDI, GIOVEDI]
        d.ignore(spostaDisposizioni(orarioTotale, "CARONI", EGiorno.MARTEDI, EOra.SECONDA, EGiorno.LUNEDI, EOra.QUINTA));
        //Docente: CENTURIONI
        // >> GIORNI DISPOSIZIONI DA SPOSTARE: [MERCOLEDI, VENERDI]
        // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): []
        d.ignore(spostaDisposizioni(orarioTotale, "CENTURIONI", EGiorno.MERCOLEDI, EOra.SECONDA, EGiorno.VENERDI, EOra.PRIMA));
        d.ignore(spostaDisposizioni(orarioTotale, "CENTURIONI", EGiorno.VENERDI, EOra.QUARTA, EGiorno.VENERDI, EOra.SECONDA));
        //Docente: DE ANGELIS
        // >> GIORNI DISPOSIZIONI DA SPOSTARE: [MARTEDI, GIOVEDI]
        // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): [LUNEDI, MERCOLEDI, VENERDI]
        d.ignore("DE ANGELIS", EGiorno.MARTEDI, EOra.SECONDA);
        d.ignore("DE ANGELIS", EGiorno.MARTEDI, EOra.QUARTA);
        d.ignore(spostaDisposizioni(orarioTotale, "DE ANGELIS", EGiorno.GIOVEDI, EOra.TERZA, EGiorno.VENERDI, EOra.TERZA));
        d.ignore(spostaDisposizioni(orarioTotale, "DE ANGELIS", EGiorno.GIOVEDI, EOra.QUARTA, EGiorno.VENERDI, EOra.SECONDA));
        //Docente: LIO
        // >> GIORNI DISPOSIZIONI DA SPOSTARE: [MARTEDI, GIOVEDI]
        // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): []
        d.ignore("LIO", EGiorno.MARTEDI, EOra.SECONDA);
        d.ignore(spostaDisposizioni(orarioTotale, "LIO", EGiorno.MARTEDI, EOra.QUARTA, EGiorno.MARTEDI, EOra.PRIMA));
        d.ignore("LIO", EGiorno.GIOVEDI, EOra.QUINTA);

        //Docente: MATTEUCCI
        // >> GIORNI DISPOSIZIONI DA SPOSTARE: [MARTEDI, GIOVEDI, VENERDI]
        // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): []
        d.ignore("MATTEUCCI", EGiorno.MARTEDI, EOra.TERZA);
        d.ignore("MATTEUCCI", EGiorno.GIOVEDI, EOra.TERZA);
        d.ignore(spostaDisposizioni(orarioTotale, "MATTEUCCI", EGiorno.VENERDI, EOra.QUARTA, EGiorno.GIOVEDI, EOra.QUARTA));
        //Docente: ROSINI
        // >> GIORNI DISPOSIZIONI DA SPOSTARE: [LUNEDI, MARTEDI, MERCOLEDI, GIOVEDI, VENERDI]
        // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): []
        /*
        spostaDisposizioni(orarioTotale, "ROSINI", EGiorno.LUNEDI, EOra.QUARTA, EGiorno., EOra.);
        spostaDisposizioni(orarioTotale, "ROSINI", EGiorno.MARTEDI, EOra.QUINTA, EGiorno., EOra.);
        spostaDisposizioni(orarioTotale, "ROSINI", EGiorno.MERCOLEDI, EOra.QUARTA, EGiorno., EOra.);
        spostaDisposizioni(orarioTotale, "ROSINI", EGiorno.GIOVEDI, EOra.QUINTA, EGiorno., EOra.);
        spostaDisposizioni(orarioTotale, "ROSINI", EGiorno.VENERDI, EOra.QUARTA, EGiorno., EOra.);
        */

        //Docente: SACCENTI
        // >> GIORNI DISPOSIZIONI DA SPOSTARE: [LUNEDI, MARTEDI, MERCOLEDI]
        // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): []
        d.ignore("SACCENTI", EGiorno.LUNEDI, EOra.TERZA);
        d.ignore("SACCENTI", EGiorno.MARTEDI, EOra.SECONDA);
        d.ignore(spostaDisposizioni(orarioTotale, "SACCENTI", EGiorno.MERCOLEDI, EOra.QUARTA, EGiorno.LUNEDI, EOra.SECONDA));
        //Docente: SCHIAREA
        // >> GIORNI DISPOSIZIONI DA SPOSTARE: [MARTEDI]
        // >> GIORNI IN CUI SPOSTARE DISPOSIZIONI (IN PRESENZA): [LUNEDI, MERCOLEDI, GIOVEDI, VENERDI]
        spostaDisposizioni(orarioTotale, "SCHIAREA", EGiorno.MARTEDI, EOra.TERZA, EGiorno.LUNEDI, EOra.QUARTA);
        spostaDisposizioni(orarioTotale, "SCHIAREA", EGiorno.MARTEDI, EOra.QUARTA, EGiorno.GIOVEDI, EOra.QUARTA);


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

    public VincoliSostituzioni_2021_n09_settimana_02nov_06ott_75_PERCENTO_SETTIMANA2 invoke(final BitOrarioGrigliaOrario orarioTotale,
                                                                                            final LessonConstraintContainer l) {
        dal = "02/11/2020";
        al = "06/11/2020";
        progressivo = "9";

        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());


        return this;
    }
}
