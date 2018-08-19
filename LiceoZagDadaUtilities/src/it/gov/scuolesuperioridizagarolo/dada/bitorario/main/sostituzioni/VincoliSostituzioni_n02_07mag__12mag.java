package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.constraint.*;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;

import java.io.IOException;

//import dada.bitorario.data.constraint.*;

/**
 * Created by stefano on 27/04/2018.
 */
public class VincoliSostituzioni_n02_07mag__12mag extends AbstractVincoliSostituzioni {
    public static void main(String[] args) throws IOException {
        final VincoliSostituzioni_n02_07mag__12mag l = new VincoliSostituzioni_n02_07mag__12mag();
        MotoreSostituzioneAule2.doTask(l);

    }

    public VincoliSostituzioni_n02_07mag__12mag invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {

        //vincoli base
        l.add(new LessonConstraint_OccupazioneAule_labsToo());

        //vincoli classi
        l.add(new LessonConstraint_ClasseFermaInAulaDidattica_ignoreLabs(_4C, _A2, EGiorno.values()));
        l.add(new LessonConstraint_AuleDidatticheConLIM_IgnoreLabs(_1H, EGiorno.values()));

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


        //Simulazione terza prova
        l.add(new LessonConstraint_AulaNonDisponibile(_A5_DIS, EGiorno.MARTEDI, EOra.SECONDA, EOra.TERZA));
        l.add(new LessonConstraint_AulaNonDisponibile(_A4_INF, EGiorno.MARTEDI, EOra.SECONDA, EOra.TERZA));
        orarioTotale.classeInVisitaDidattica("Simulazione Terza Prova (Aula Disegno e Lab Info)", _5A, EGiorno.MARTEDI, EOra.SECONDA, EOra.TERZA);
        orarioTotale.classeInVisitaDidattica("Simulazione Terza Prova (Aula Disegno e Lab Info)", _5B, EGiorno.MARTEDI, EOra.SECONDA, EOra.TERZA);
        orarioTotale.classeInVisitaDidattica("Simulazione Terza Prova (Aula Disegno e Lab Info)", _5C, EGiorno.MARTEDI, EOra.SECONDA, EOra.TERZA);
        orarioTotale.classeInVisitaDidattica("Simulazione Terza Prova (Aula Disegno e Lab Info)", _5D, EGiorno.MARTEDI, EOra.SECONDA, EOra.TERZA);
        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(false, "Millozzi", _A3, EGiorno.MARTEDI, EOra.SECONDA, EOra.TERZA));

        //invalsi
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(_2F, _A4_INF, EGiorno.VENERDI, EOra.PRIMA, EOra.SECONDA));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(_2E, _A4_INF, EGiorno.VENERDI, EOra.TERZA, EOra.QUARTA));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(_2B, _A4_INF, EGiorno.SABATO, EOra.PRIMA, EOra.SECONDA));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(_2G, _A4_INF, EGiorno.SABATO, EOra.TERZA, EOra.QUARTA));
        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(false, "Millozzi", _A3, EGiorno.SABATO, EOra.PRIMA, EOra.SECONDA, EOra.TERZA, EOra.QUARTA));


        //verifiche in parallelo
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(_2B, _A3, EGiorno.GIOVEDI, EOra.SECONDA));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(_2D, _A3, EGiorno.GIOVEDI, EOra.TERZA));
        l.add(new LessonConstraint_ClasseFermaInAulaDidatticaPerOre_labsToo(_2F, _A3, EGiorno.GIOVEDI, EOra.QUINTA));


        //lavori aula fisica
        for (EGiorno g : EGiorno.values()) {
            if (g.flagGiornoDiLezione()) {
                l.add(new LessonConstraint_AulaNonDisponibile(_A6_FIS, g, EOra.values()));
            }

        }


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

        //classeConStampelle(l, _2A);
        //classeConStampelle(l, _1E, EGiorno.LUNEDI, EGiorno.MARTEDI, EGiorno.MERCOLEDI);
        classeConStampelle(l, _1E);
        classeConStampelle(l, _5C);
        classeConStampelle(l, _3D);
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


        dal = "07/05/2018";
        al = "12/05/2018";
        return this;
    }
}
