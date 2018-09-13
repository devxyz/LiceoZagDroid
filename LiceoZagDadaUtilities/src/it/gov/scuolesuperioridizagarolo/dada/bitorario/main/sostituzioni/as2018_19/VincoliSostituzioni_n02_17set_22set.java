package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2018_19;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.engine.FilterAule;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule3;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraintContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_DocenteBloccatoInArea;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.LessonConstraint_OccupazioneAule_labsToo;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

import java.io.File;
import java.io.IOException;
import java.util.TreeSet;

//import dada.bitorario.data.constraint.*;

/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_n02_17set_22set extends AbstractVincoliSostituzioni {
    public static void main(String[] args) throws IOException {
        final VincoliSostituzioni_n02_17set_22set l = new VincoliSostituzioni_n02_17set_22set();
        final File folderInput = new File("/Users/stefano/Dropbox/Circolari Scolastiche Liceo/AS 2018.19/Orario Scolastico/orario/02-2018.09.17_2018.09.22");
        MotoreSostituzioneAule3.doTask(l, folderInput, new File("/Users/stefano/Dropbox/Circolari Scolastiche Liceo/AS 2018.19/Orario Scolastico/orario/"), l.filtroAuleSpostamenti());

    }

    @Override
    protected FilterAule[] filtroAuleSpostamenti() {
        return new FilterAule[]{FilterAule.LABORATORI_MAI};
    }

    public VincoliSostituzioni_n02_17set_22set invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {


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


            /*
            for (EGiorno g : EGiorno.values()) {
                l.add(new LessonConstraint_AulaNonDisponibile(_C14, g, EOra.values()));
            }*/


        //  orarioTotale.classeInVisitaDidattica("Incontro con Avvocati dell'Unione delle Camere Penali - aula disegno (4B e 4D)", _4D, EGiorno.GIOVEDI, EOra.SECONDA, EOra.TERZA);
        //orarioTotale.classeInVisitaDidattica("Incontro con Avvocati dell'Unione delle Camere Penali - aula disegno (4B e 4D)", _4B, EGiorno.GIOVEDI, EOra.SECONDA, EOra.TERZA);

        //      l.add(new LessonConstraint_AulaNonDisponibile(_F31_PALESTRA, EGiorno.GIOVEDI, EOra.SECONDA, EOra.TERZA, EOra.QUARTA, EOra.QUINTA));
        //    l.add(new LessonConstraint_AulaNonDisponibile(_A5_DIS, EGiorno.GIOVEDI, EOra.SECONDA, EOra.TERZA));

        //terza prova
        //          l.add(new LessonConstraint_AulaNonDisponibile(_A5_DIS, EGiorno.VENERDI, EOra.SECONDA, EOra.TERZA));


        //conferenza legalità
            /*orarioTotale.classeInVisitaDidattica("Conferenza sulla legalità - aula disegno - primo turno", _4A, EGiorno.VENERDI, EOra.SECONDA);
            orarioTotale.classeInVisitaDidattica("Conferenza sulla legalità - aula disegno - primo turno", _4B, EGiorno.VENERDI, EOra.SECONDA);

            orarioTotale.classeInVisitaDidattica("Conferenza sulla legalità - aula disegno - secondo turno", _4C, EGiorno.VENERDI, EOra.TERZA);
            orarioTotale.classeInVisitaDidattica("Conferenza sulla legalità - aula disegno - secondo turno", _4D, EGiorno.VENERDI, EOra.TERZA);

            orarioTotale.classeInVisitaDidattica("Conferenza sulla legalità - aula disegno - terzo turno", _4E, EGiorno.VENERDI, EOra.QUARTA);
            orarioTotale.classeInVisitaDidattica("Conferenza sulla legalità - aula disegno - terzo turno", _4F, EGiorno.VENERDI, EOra.QUARTA);
            */

        //orarioTotale.classeInVisitaDidattica("Uscita di Test",_1E,EGiorno.LUNEDI,EOra.values());
        //orarioTotale.classeInVisitaDidattica("Uscita di Test",_1G,EGiorno.LUNEDI,EOra.values());

        //lavori aule medie e piano terra
        for (EGiorno g : EGiorno.values()) {
            if (g.flagGiornoDiLezione()) {
                //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.F32_SCI, g, EOra.values()));
                //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.F31_PALESTRA, g, EOra.values()));
                //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.E27, g, EOra.values()));
                //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.E28, g, EOra.values()));
                //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.E29, g, EOra.values()));
                //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.E30, g, EOra.values()));
                //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.B10, g, EOra.values()));
                //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.B13sharp, g, EOra.values()));
                //l.add(new LessonConstraint_AulaNonDisponibile(RoomData.A5sharp, g, EOra.values()));
            }
        }
        /*for (ClassData classe : ClassData.values()) {
            orarioTotale.classeInSospensioneDidattica(classe, EGiorno.LUNEDI, EOra.values());
            orarioTotale.classeInSospensioneDidattica(classe, EGiorno.MARTEDI, EOra.values());
            if (classe._class > 2) {
                //triennio
                orarioTotale.classeInSospensioneDidattica(classe, EGiorno.MERCOLEDI, EOra.values());
                orarioTotale.classeInSospensioneDidattica(classe, EGiorno.VENERDI, EOra.values());
                orarioTotale.classeInSospensioneDidattica(classe, EGiorno.GIOVEDI, EOra.QUARTA, EOra.QUINTA, EOra.SESTA);
                orarioTotale.classeInSospensioneDidattica(classe, EGiorno.SABATO, EOra.QUARTA, EOra.QUINTA, EOra.SESTA);
            } else {
                //biennio
                if (classe._class == 2) {
                    orarioTotale.classeInSospensioneDidattica(classe, EGiorno.MERCOLEDI, EOra.QUARTA, EOra.QUINTA, EOra.SESTA, EOra.PRIMA);
                } else {
                    orarioTotale.classeInSospensioneDidattica(classe, EGiorno.MERCOLEDI, EOra.QUARTA, EOra.QUINTA, EOra.SESTA);
                }
                orarioTotale.classeInSospensioneDidattica(classe, EGiorno.VENERDI, EOra.QUARTA, EOra.QUINTA, EOra.SESTA);
                orarioTotale.classeInSospensioneDidattica(classe, EGiorno.GIOVEDI, EOra.values());
                orarioTotale.classeInSospensioneDidattica(classe, EGiorno.SABATO, EOra.values());

            }
        }
*/

        final TreeSet<String> classi = orarioTotale.getClassi();


        //lavori
/*            l.add(new LessonConstraint_AulaNonDisponibile(_A7, EGiorno.MARTEDI, EOra.values()));
            l.add(new LessonConstraint_AulaNonDisponibile(_A7, EGiorno.MERCOLEDI, EOra.values()));
            l.add(new LessonConstraint_AulaNonDisponibile(_A7, EGiorno.GIOVEDI, EOra.values()));
            l.add(new LessonConstraint_AulaNonDisponibile(_A7, EGiorno.VENERDI, EOra.values()));
            l.add(new LessonConstraint_AulaNonDisponibile(_A7, EGiorno.SABATO, EOra.values()));
*/
        //l.add(new LessonConstraint_AulaNonDisponibile(_A4_INF, EGiorno.MARTEDI, EOra.values()));

        //gara bocconi
        //l.add(new LessonConstraint_AulaNonDisponibile(_A5_DIS, EGiorno.MARTEDI, EOra.PRIMA, EOra.SECONDA, EOra.TERZA));


        //l.add(new LessonConstraint_ClasseBloccataInArea("1F", new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B, ERoomArea.AREA_C, ERoomArea.AREA_D}, EGiorno.values()));
        //l.add(new LessonConstraint_ClasseBloccataInArea("3C", new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B, ERoomArea.AREA_C, ERoomArea.AREA_D}, EGiorno.values()));


        //classeConStampelle(l, _4E);
        //classeConStampelle(l, _4D);
        //classeConStampelle(l, _3D);
        //classeConStampelle(l, _4C);
        //classeConStampelle(l, _5C);
        //classeConStampelle(l, _4F);

        //marchese
        //l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre(_5D, _A7, EGiorno.VENERDI, EOra.PRIMA, EOra.SECONDA));


        l.add(new LessonConstraint_DocenteBloccatoInArea(false, orarioTotale, "CERULLO", new ERoomArea[]{ERoomArea.AREA_A}, EGiorno.values()));
        for (EGiorno giorno : EGiorno.values()) {
            //l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(false, "FERRIGNO", RoomData.A1, giorno, EOra.values()));

        }
        l.add(new LessonConstraint_DocenteBloccatoInArea(false, orarioTotale, "FERRIGNO", new ERoomArea[]{ERoomArea.AREA_A}, EGiorno.values()));


        //l.add(new LessonConstraint_DocenteBloccatoInArea(orarioTotale, "CERULLO", new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B, ERoomArea.AREA_C, ERoomArea.AREA_D}, new EGiorno[]{EGiorno.LUNEDI, EGiorno.MARTEDI, EGiorno.MERCOLEDI}));

        //l.add(new LessonConstraint_ClasseBloccataInArea("4B", ERoomArea.AREA_B, EGiorno.values()));

        //final EGiorno[] giorno = {EGiorno.LUNEDI};
        // l.add(new LessonConstraint_DocenteBloccatoInArea(orarioTotale, "FERRIGNO", new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B}, EGiorno.values()));
        //l.add(new LessonConstraint_DocenteBloccatoInArea(orarioTotale, "TAGLIAFERRI", new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B}, giorno));
        //l.add(new LessonConstraint_DocenteBloccatoInArea(orarioTotale, "BENEDETTI", new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B}, giorno));


        dal = "17/09/2018";
        al = "22/09/2018";
        return this;
    }
}