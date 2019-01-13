package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2018_19;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

import java.io.File;
import java.io.IOException;


/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_n16_settimana_14gen_20gen extends AbstractVincoliSostituzioni {
    public static void main(String[] args) throws IOException {
        final VincoliSostituzioni_n16_settimana_14gen_20gen l = new VincoliSostituzioni_n16_settimana_14gen_20gen();
        MotoreSostituzioneAule3.doTaskFromJSon(
                l,
                new File("/Users/stefano/Dropbox/DROPBOX LICEO/AS 2018.19/Orario Scolastico/orario-definitivo/DEFINITIVO_timetable_20181114222113_12.11.2018_09.06.2019.json.zip"),
                new File("/Users/stefano/Dropbox/DROPBOX LICEO/AS 2018.19/Orario Scolastico/orario-modifiche"),
                l.filtroAuleSpostamenti(), true);
    }


    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {
        /*final BitOrarioOraLezione saccenti = orarioTotale.getLezioneConDocente(EOra.TERZA, EGiorno.MERCOLEDI, "saccenti");
        orarioTotale.removeLezione(saccenti);
        orarioTotale.addLezione(BitOrarioOraLezione.creaOraDisposizione("saccenti", EOra.SECONDA, EGiorno.LUNEDI));

        //if (true)throw new IllegalArgumentException("DEBUG");

        ClassData[] BIORDI = new ClassData[]{
                null, null, ClassData.CLASS_2H, ClassData.CLASS_2H, ClassData.CLASS_2H, ClassData.CLASS_2H,
                ClassData.CLASS_2H, ClassData.CLASS_2H, ClassData.CLASS_2H, null, ClassData.CLASS_2H, ClassData.CLASS_2H,
                ClassData.CLASS_2H, ClassData.CLASS_2H, ClassData.CLASS_2H, null, ClassData.CLASS_2H, null,
                null, null, null, ClassData.CLASS_2H, ClassData.CLASS_2H, ClassData.CLASS_2H,
                null, null, null, ClassData.CLASS_2H, ClassData.CLASS_2H, null};
        ClassData[] BOTTEGA = new ClassData[]{
                null, null, ClassData.CLASS_1E, ClassData.CLASS_1E, ClassData.CLASS_1A, ClassData.CLASS_1A,
                null, null, ClassData.CLASS_1A, ClassData.CLASS_1A, ClassData.CLASS_1A, null,
                null, ClassData.CLASS_1A, ClassData.CLASS_1A, ClassData.CLASS_1A, null, null,
                ClassData.CLASS_1E, ClassData.CLASS_1E, ClassData.CLASS_1E, ClassData.CLASS_1E, ClassData.CLASS_1E, null,
                ClassData.CLASS_1E, ClassData.CLASS_1A, ClassData.CLASS_1A, null, null, null};
        ClassData[] DAMIANI = new ClassData[]{
                ClassData.CLASS_1A, ClassData.CLASS_1A, ClassData.CLASS_1A, ClassData.CLASS_1A, ClassData.CLASS_1E, null,
                ClassData.CLASS_1A, ClassData.CLASS_1E, null, ClassData.CLASS_1E, ClassData.CLASS_1E, ClassData.CLASS_1E,
                ClassData.CLASS_1E, ClassData.CLASS_1E, null, ClassData.CLASS_1E, ClassData.CLASS_1E, null,
                null, null, null, null, ClassData.CLASS_1A, ClassData.CLASS_1E,
                null, ClassData.CLASS_1E, ClassData.CLASS_1E, null, null, null};


        int i = 0;
        for (EGiorno giorno : EGiorno.valuesGiorniDiLezione()) {
            if (giorno == EGiorno.SABATO) continue;
            for (EOra ora : EOra.valuesOreDiLezione()) {
                System.out.println(i + " " + giorno + " " + ora);
                if (BIORDI[i] != null) {
                    orarioTotale.removeInsegnanteCompresenza(giorno, ora, BIORDI[i]);
                    orarioTotale.addInsegnanteSostegno(giorno, ora, BIORDI[i], "biordi");
                }
                if (BOTTEGA[i] != null) {
                    orarioTotale.removeInsegnanteCompresenza(giorno, ora, BOTTEGA[i]);
                    orarioTotale.addInsegnanteSostegno(giorno, ora, BOTTEGA[i], "bottega");
                }
                if (DAMIANI[i] != null) {
                    orarioTotale.removeInsegnanteCompresenza(giorno, ora, DAMIANI[i]);
                    orarioTotale.addInsegnanteSostegno(giorno, ora, DAMIANI[i], "damiani");
                }
                i++;
            }
        }


        orarioTotale.addInsegnanteCompresenza(EGiorno.LUNEDI, EOra.QUARTA, ClassData.CLASS_1A, "andreozzi", "arte");
        orarioTotale.addInsegnanteCompresenza(EGiorno.LUNEDI, EOra.QUINTA, ClassData.CLASS_1E, "andreozzi", "arte");


        {
            final ArrayList<BitOrarioOraLezione> lezioniDaCambiare = new ArrayList<>();
            final ArrayList<BitOrarioOraLezione> lezioni = orarioTotale.getLezioni();
            for (BitOrarioOraLezione x : lezioni) {
                if (x.getDocentePrincipale() != null && x.getDocentePrincipale().equalsIgnoreCase("XX_INFO_CEDUTE_10H")) {
                    lezioniDaCambiare.add(x);
                }
            }
            for (BitOrarioOraLezione x : lezioniDaCambiare) {
                orarioTotale.removeLezione(x);

                final BitOrarioOraLezione y = BitOrarioOraLezione.creaOraDocenteSingolo("Di Gioia", x.getMateriaPrincipale(), x.getAula(), x.getClasse(), x.getOra(), x.getGiorno());
                orarioTotale.addLezione(y);
            }
        }


        {
            final ArrayList<BitOrarioOraLezione> lezioniDaCambiare = new ArrayList<>();
            final ArrayList<BitOrarioOraLezione> lezioni = orarioTotale.getLezioni();
            for (BitOrarioOraLezione x : lezioni) {
                if (x.getDocentePrincipale() != null && x.getDocentePrincipale().equalsIgnoreCase("XX_ARTE_CATTEST_8H")) {
                    lezioniDaCambiare.add(x);
                }
            }
            for (BitOrarioOraLezione x : lezioniDaCambiare) {
                orarioTotale.removeLezione(x);

                final BitOrarioOraLezione y = BitOrarioOraLezione.creaOraDocenteSingolo("Di Giuseppe", x.getMateriaPrincipale(), x.getAula(), x.getClasse(), x.getOra(), x.getGiorno());
                orarioTotale.addLezione(y);
            }
        }


        orarioTotale.trim();

*/
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

    public VincoliSostituzioni_n16_settimana_14gen_20gen invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {
        dal = "14/1/2019";
        al = "20/1/2019";

        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());


        //==============================================================================================================
        //blocco sesta ora su aule richieste
        //==============================================================================================================
        for (EGiorno giorno : EGiorno.values()) {
            for (RoomData r : RoomData.values()) {
                if (r.location == ERoomArea.AREA_C || r.location == ERoomArea.AREA_E || r.location == ERoomArea.AREA_D) {
                    //l.add(new LessonConstraint_AulaNonDisponibile(r, giorno, EOra.SESTA));
                }
            }

        }

        //==============================================================================================================
        //BLOCCO SABATO
        //==============================================================================================================
        for (RoomData r : RoomData.values()) {
            if (r.location == ERoomArea.AREA_D) {
                l.add(new LessonConstraint_AulaNonDisponibile(r, EGiorno.SABATO, EOra.values()));
            }
            if (r.location == ERoomArea.AREA_E) {
                l.add(new LessonConstraint_AulaNonDisponibile(r, EGiorno.SABATO, EOra.values()));
            }

        }

        /*
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C14, EGiorno.SABATO, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C15, EGiorno.SABATO, EOra.values()));
        //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C16, EGiorno.SABATO, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C17, EGiorno.SABATO, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C18, EGiorno.SABATO, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C19, EGiorno.SABATO, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C20, EGiorno.SABATO, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C21, EGiorno.SABATO, EOra.values()));
        */

        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.F32_SCI, EGiorno.SABATO, EOra.values()));


        //==============================================================================================================
        // blocco aule....
        //==============================================================================================================
        /*l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _A1, EGiorno.LUNEDI));
        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _A1, EGiorno.MARTEDI));
        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _A1, EGiorno.GIOVEDI));
        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _A1, EGiorno.VENERDI));
*/

        //sabato in ala C solo classe quinta
        //l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_5A, _C16, EGiorno.SABATO));

        //l.add(new LessonConstraint_AnnoClasseFermaInAulaDidattica_ignoreLabs(5, _C16, EGiorno.SABATO));


        // scienze
        /*
        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "scienze",
                new ClassData[]{ClassData.CLASS_1B},
                new String[]{},
                new RoomData[]{RoomData.D22, RoomData.D23, RoomData.D24, RoomData.D25, RoomData.D26, RoomData.E27, RoomData.E28,}
        ));
*/

        // INGLESE
        l.add(new LessonConstraint_MateriaBloccataAule(true, orarioTotale,
                "inglese",
                new ClassData[]{ClassData.CLASS_1B},
                new String[]{"schiarea"},
                new RoomData[]{RoomData.B8, RoomData.B9, RoomData.B10, RoomData.B11, RoomData.B13,}
        ));

        l.add(new LessonConstraint_MateriaConLIM(true, orarioTotale, "inglese",
                new ClassData[]{},
                new String[]{}
        ));


        //=================== VINCOLI VARI

        //lunedi' giornata violenza donne circolare n.64
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.A5_DIS, EGiorno.LUNEDI, EOra.QUARTA));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.A5_DIS, EGiorno.VENERDI, EOra.SECONDA, EOra.TERZA, EOra.QUARTA));

        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(
                false, "SCHIAREA", new RoomData[]{RoomData.C20}, new EGiorno[]{EGiorno.GIOVEDI}, EOra.values()));

        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(
                false, "SANTELLI", new RoomData[]{RoomData.C17}, new EGiorno[]{EGiorno.GIOVEDI}, new EOra[]{EOra.SESTA}));


       /* orarioTotale.classeInVisitaDidattica("Incontro in aula disegno A5 - Il linguaggio non basta", ClassData.CLASS_5A, EGiorno.LUNEDI, EOra.QUARTA);
        orarioTotale.classeInVisitaDidattica("Incontro in aula disegno A5 - Il linguaggio non basta", ClassData.CLASS_5F, EGiorno.LUNEDI, EOra.QUARTA);

        orarioTotale.classeInVisitaDidattica("Incontro in aula disegno A5 - Il linguaggio non basta", ClassData.CLASS_5A, EGiorno.VENERDI, EOra.SECONDA, EOra.TERZA, EOra.QUARTA);
        orarioTotale.classeInVisitaDidattica("Incontro in aula disegno A5 - Il linguaggio non basta", ClassData.CLASS_5F, EGiorno.VENERDI, EOra.SECONDA, EOra.TERZA, EOra.QUARTA);
*/

        return this;
    }
}
