package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2017_18;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule2;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassesAndRoomContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

import java.io.IOException;
import java.util.TreeSet;

//import dada.bitorario.data.constraint.*;

/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_n06_04giugno__08giugno extends AbstractVincoliSostituzioni {
    public static void main(String[] args) throws IOException {
        final VincoliSostituzioni_n06_04giugno__08giugno l = new VincoliSostituzioni_n06_04giugno__08giugno();
        MotoreSostituzioneAule2.doTask(l);

    }

    public VincoliSostituzioni_n06_04giugno__08giugno invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {
        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());

        //vincoli classi
        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(_4C, _A2, EGiorno.values()));
        l.add(new LessonConstraint_AuleDidatticheConLIM_IgnoreLabs(_1H, EGiorno.values()));

        //lavori
        l.add(new LessonConstraint_AulaNonDisponibile(_A7, EGiorno.LUNEDI, EOra.values()));

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


        //lavori aula fisica
        for (EGiorno g : EGiorno.values()) {
            if (g.flagGiornoDiLezione()) {
                l.add(new LessonConstraint_AulaNonDisponibile(_A6, g, EOra.values()));
                //   l.add(new LessonConstraint_AulaNonDisponibile(_C15, g, EOra.values()));
            }

        }


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

        //GLH
        l.add(new LessonConstraint_AulaNonDisponibile(_A5_DIS, EGiorno.MARTEDI, EOra.SECONDA, EOra.TERZA, EOra.QUARTA));

        //biennio in teatro
        orarioTotale.classeInVisitaDidattica("Teatro Biennio 4 giugno", _1A, EGiorno.LUNEDI, EOra.values());


        for (String c : classi) {
            final ClassData aClass = ClassesAndRoomContainer.getClass(c);
            if (aClass.name.startsWith("1") || aClass.name.startsWith("2")) {
                orarioTotale.classeInVisitaDidattica("Teatro Biennio - 4 giugno", aClass, EGiorno.LUNEDI, EOra.values());
            } else {
                orarioTotale.classeInVisitaDidattica("Teatro Triennio 5 giugno", aClass, EGiorno.MARTEDI, EOra.values());
            }

            orarioTotale.classeInVisitaDidattica("Chiusura scuola", aClass, EGiorno.SABATO, EOra.values());
        }
        //classeConStampelle(l, _2A);
        //classeConStampelle(l, _1E, EGiorno.LUNEDI, EGiorno.MARTEDI, EGiorno.MERCOLEDI);
        //classeConStampelle(l, _1E);
        classeConStampelle(l, _4F, EGiorno.MERCOLEDI, EGiorno.GIOVEDI, EGiorno.VENERDI, EGiorno.SABATO);
        classeConStampelle(l, _2E, EGiorno.GIOVEDI, EGiorno.VENERDI, EGiorno.SABATO);
        classeConStampelle(l, _5C);
        classeConStampelle(l, _1B);
        // classeConStampelle(l, _3D);
        //  classeConStampelle(l, _2G);

        //lab scienze chiuso il sabato
        l.add(new LessonConstraint_AulaNonDisponibile(_F32_SCI, EGiorno.SABATO, EOra.values()));


        //classeConStampelle(l, _4E);
        //classeConStampelle(l, _4D);
        //classeConStampelle(l, _3D);
        //classeConStampelle(l, _4C);
        //classeConStampelle(l, _5C);
        //classeConStampelle(l, _4F);

        //marchese
        //l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre(_5D, _A7, EGiorno.VENERDI, EOra.PRIMA, EOra.SECONDA));

        //perfetti
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(_5D, _E29, EGiorno.SABATO, EOra.QUARTA, EOra.QUINTA));
        l.add(new LessonConstraint_DocenteBloccatoInArea(true, orarioTotale, "CERULLO", new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B, ERoomArea.AREA_C, ERoomArea.AREA_D}, EGiorno.values()));

        //l.add(new LessonConstraint_DocenteBloccatoInArea(orarioTotale, "CERULLO", new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B, ERoomArea.AREA_C, ERoomArea.AREA_D}, new EGiorno[]{EGiorno.LUNEDI, EGiorno.MARTEDI, EGiorno.MERCOLEDI}));

        //l.add(new LessonConstraint_ClasseBloccataInArea("4B", ERoomArea.AREA_B, EGiorno.values()));

        //final EGiorno[] giorno = {EGiorno.LUNEDI};
        // l.add(new LessonConstraint_DocenteBloccatoInArea(orarioTotale, "FERRIGNO", new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B}, EGiorno.values()));
        //l.add(new LessonConstraint_DocenteBloccatoInArea(orarioTotale, "TAGLIAFERRI", new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B}, giorno));
        //l.add(new LessonConstraint_DocenteBloccatoInArea(orarioTotale, "BENEDETTI", new ERoomArea[]{ERoomArea.AREA_A, ERoomArea.AREA_B}, giorno));


        dal = "04/06/2018";
        al = "08/06/2018";
        return this;
    }
}
