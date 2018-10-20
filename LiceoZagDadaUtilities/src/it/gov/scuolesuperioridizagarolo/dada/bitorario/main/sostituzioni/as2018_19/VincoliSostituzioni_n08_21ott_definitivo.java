package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2018_19;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.FilterClassData;
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
public class VincoliSostituzioni_n08_21ott_definitivo extends AbstractVincoliSostituzioni {
    public static void main(String[] args) throws IOException {
        final VincoliSostituzioni_n08_21ott_definitivo l = new VincoliSostituzioni_n08_21ott_definitivo();
        final File folderInput = new File("/Users/stefano/Dropbox/Circolari Scolastiche Liceo/AS 2018.19/Orario Scolastico/orario/07-2018.10.21-2019.06.10");
        MotoreSostituzioneAule3.doTask(l, folderInput, new File("/Users/stefano/Dropbox/Circolari Scolastiche Liceo/AS 2018.19/Orario Scolastico/orario/"), l.filtroAuleSpostamenti(), false);
    }





    protected void preOrarioBeforeAssignment(final BitOrarioGrigliaOrario orarioTotale) {


        //if (true)throw new IllegalArgumentException("DEBUG");
    }


    @Override
    protected void postOrarioBeforeFinalCheck(BitOrarioGrigliaOrario orarioTotale, LessonConstraintContainer l) {
    }

    @Override
    protected FilterAule[] filtroAuleSpostamenti() {
        return new FilterAule[]{FilterAule.LABORATORI_MAI};
    }

    public VincoliSostituzioni_n08_21ott_definitivo invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {
        dal = "21/10/2018";
        al = "09/06/2019";

        // if (true) return this;

        System.out.flush();

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());

        //compito marchese
//        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(_4F, _A3, EGiorno.LUNEDI, EOra.PRIMA));//2° ora

        //prove parallele scienze
  /*      l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(_2G, _A3, EGiorno.LUNEDI, EOra.SECONDA));//2° ora
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(_2A, _A4_INF, EGiorno.LUNEDI, EOra.QUARTA));//4° ora latini --> lab informatica
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(_2C, _A3, EGiorno.LUNEDI, EOra.TERZA));//3° ora
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(_2E, _A3, EGiorno.LUNEDI, EOra.QUINTA));//5° ora cambia con mattetti - scambia con 1H
*/

        //l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre(_3A, _F32_SCI, EGiorno.MARTEDI, EOra.TERZA));
        //l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre(_3D, _A4_INF, EGiorno.MARTEDI, EOra.SECONDA));
        //l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre(_4B, _A4_INF, EGiorno.SABATO, EOra.TERZA));

        //l.add(new LessonConstraint_AulaNonDisponibile(_A6_FIS, EGiorno.SABATO, EOra.SECONDA)); //fabroni

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
            if (r.location == ERoomArea.AREA_D || r.location == ERoomArea.AREA_E) {
                l.add(new LessonConstraint_AulaNonDisponibile(r, EGiorno.SABATO, EOra.values()));
            }

        }

        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C14, EGiorno.SABATO, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C15, EGiorno.SABATO, EOra.values()));
        //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C16, EGiorno.SABATO, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C17, EGiorno.SABATO, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C18, EGiorno.SABATO, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C19, EGiorno.SABATO, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C20, EGiorno.SABATO, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.C21, EGiorno.SABATO, EOra.values()));
        l.add(new LessonConstraint_AulaNonDisponibile(RoomData.F32_SCI, EGiorno.SABATO, EOra.values()));


        //==============================================================================================================
        // blocco aule....
        //==============================================================================================================
        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _A1, EGiorno.LUNEDI));
        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _A1, EGiorno.MARTEDI));
        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _A1, EGiorno.GIOVEDI));
        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_1B, _A1, EGiorno.VENERDI));

        //sabato in ala C solo classe quinta
        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(ClassData.CLASS_5A, _C16, EGiorno.SABATO));

        l.add(new LessonConstraint_AnnoClasseFermaInAulaDidattica_ignoreLabs(5, _C16, EGiorno.SABATO));


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

        //lavori aule medie e piano terra
        for (EGiorno g : EGiorno.values()) {
            if (g.flagGiornoDiLezione()) {
                // l.add(new LessonConstraint_AulaNonDisponibile(RoomData.B13sharp, g, EOra.values()));
            }
        }

        //====================================================================================================================================
        // FERRIGNO
        //====================================================================================================================================
        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(
                false, "FERRIGNO", new RoomData[]{RoomData.A6, RoomData.A7, RoomData.A5sharp}, EGiorno.values(), EOra.values()));


        return this;
    }
}
