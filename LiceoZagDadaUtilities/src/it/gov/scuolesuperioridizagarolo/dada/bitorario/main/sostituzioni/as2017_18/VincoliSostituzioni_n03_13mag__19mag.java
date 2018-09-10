package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2017_18;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.AbstractVincoliSostituzioni;
import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.MotoreSostituzioneAule2;
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
public class VincoliSostituzioni_n03_13mag__19mag extends AbstractVincoliSostituzioni {
    public static void main(String[] args) throws IOException {
        final VincoliSostituzioni_n03_13mag__19mag l = new VincoliSostituzioni_n03_13mag__19mag();
        MotoreSostituzioneAule2.doTask(l);

    }

    public VincoliSostituzioni_n03_13mag__19mag invoke(final BitOrarioGrigliaOrario orarioTotale, final LessonConstraintContainer l) {
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


        //INVALSI
        //--lun
        final EGiorno lunedi = EGiorno.LUNEDI;
        l.add(new LessonConstraint_AulaNonDisponibile(_A4_INF, lunedi, EOra.PRIMA, EOra.SECONDA, EOra.TERZA, EOra.QUARTA, EOra.QUINTA));
        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(false, "Millozzi", _A3_FIS, EGiorno.LUNEDI, EOra.PRIMA, EOra.SECONDA, EOra.TERZA, EOra.QUARTA, EOra.QUINTA));
        orarioTotale.classeInVisitaDidattica("PROVA INVALSI IN LAB. INFORMATICA 2F ITALIANO", _2F, lunedi, EOra.PRIMA, EOra.SECONDA);
        orarioTotale.classeInVisitaDidattica("PROVA INVALSI IN LAB. INFORMATICA 2G ITALIANO", _2G, lunedi, EOra.TERZA, EOra.QUARTA);
        orarioTotale.classeInVisitaDidattica("PROVA INVALSI IN LAB. INFORMATICA 2E ITALIANO", _2E, lunedi, EOra.QUINTA);

        //--mar
        final EGiorno martedi = EGiorno.MARTEDI;
        l.add(new LessonConstraint_AulaNonDisponibile(_A4_INF, martedi, EOra.PRIMA, EOra.SECONDA, EOra.TERZA, EOra.QUARTA, EOra.QUINTA));
        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(false, "Millozzi", _A3_FIS, martedi, EOra.PRIMA, EOra.SECONDA, EOra.TERZA, EOra.QUARTA, EOra.QUINTA));
        orarioTotale.classeInVisitaDidattica("PROVA INVALSI IN LAB. INFORMATICA 2B ITALIANO", _2B, martedi, EOra.PRIMA, EOra.SECONDA);
        orarioTotale.classeInVisitaDidattica("PROVA INVALSI IN LAB. INFORMATICA 2C ITALIANO", _2C, martedi, EOra.TERZA, EOra.QUARTA);
        orarioTotale.classeInVisitaDidattica("PROVA INVALSI IN LAB. INFORMATICA 2A ITALIANO", _2A, martedi, EOra.QUINTA);

        //--mer
        final EGiorno mercoledi = EGiorno.MERCOLEDI;
        l.add(new LessonConstraint_AulaNonDisponibile(_A4_INF, mercoledi, EOra.PRIMA, EOra.SECONDA, EOra.TERZA, EOra.QUARTA));
        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(false, "Millozzi", _A3_FIS, mercoledi, EOra.PRIMA, EOra.SECONDA, EOra.TERZA, EOra.QUARTA));
        orarioTotale.classeInVisitaDidattica("PROVA INVALSI IN LAB. INFORMATICA 2D ITALIANO", _2D, mercoledi, EOra.PRIMA, EOra.SECONDA);
        orarioTotale.classeInVisitaDidattica("PROVA INVALSI IN LAB. INFORMATICA 2C MATEMATICA", _2C, mercoledi, EOra.TERZA, EOra.QUARTA);


        //--gio
        final EGiorno giovedi = EGiorno.GIOVEDI;
        l.add(new LessonConstraint_AulaNonDisponibile(_A4_INF, giovedi, EOra.PRIMA, EOra.SECONDA, EOra.TERZA, EOra.QUARTA));
        l.add(new LessonConstraint_DocenteFermoInAulaDidatticaPerOre(false, "Millozzi", _A3_FIS, giovedi, EOra.PRIMA, EOra.SECONDA, EOra.TERZA, EOra.QUARTA));
        orarioTotale.classeInVisitaDidattica("PROVA INVALSI IN LAB. INFORMATICA 2D ITALIANO", _2D, giovedi, EOra.PRIMA, EOra.SECONDA);
        orarioTotale.classeInVisitaDidattica("PROVA INVALSI IN LAB. INFORMATICA 2A MATEMATICA", _2A, giovedi, EOra.TERZA, EOra.QUARTA);


        //simulazione seconda prova
        orarioTotale.classeInVisitaDidattica("Simulazione Seconda Prova (POMERIGGIO)", _5A, EGiorno.LUNEDI, EOra.values());
        orarioTotale.classeInVisitaDidattica("Simulazione Seconda Prova (POMERIGGIO)", _5B, EGiorno.LUNEDI, EOra.values());
        orarioTotale.classeInVisitaDidattica("Simulazione Seconda Prova (POMERIGGIO)", _5C, EGiorno.LUNEDI, EOra.values());
        orarioTotale.classeInVisitaDidattica("Simulazione Seconda Prova (POMERIGGIO)", _5D, EGiorno.LUNEDI, EOra.values());

        l.add(new LessonConstraint_AulaNonDisponibile(_C14, EGiorno.LUNEDI, EOra.QUINTA));
        l.add(new LessonConstraint_AulaNonDisponibile(_C15, EGiorno.LUNEDI, EOra.QUINTA));
        l.add(new LessonConstraint_AulaNonDisponibile(_C16, EGiorno.LUNEDI, EOra.QUINTA));
        l.add(new LessonConstraint_AulaNonDisponibile(_D26, EGiorno.LUNEDI, EOra.QUINTA));
        l.add(new LessonConstraint_AulaNonDisponibile(_D25, EGiorno.LUNEDI, EOra.QUINTA));
        l.add(new LessonConstraint_AulaNonDisponibile(_D24, EGiorno.LUNEDI, EOra.QUINTA));

        //lavori aula fisica
        for (EGiorno g : EGiorno.values()) {
            if (g.flagGiornoDiLezione()) {
                l.add(new LessonConstraint_AulaNonDisponibile(_A6, g, EOra.values()));
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


        dal = "13/05/2018";
        al = "19/05/2018";
        return this;
    }
}
