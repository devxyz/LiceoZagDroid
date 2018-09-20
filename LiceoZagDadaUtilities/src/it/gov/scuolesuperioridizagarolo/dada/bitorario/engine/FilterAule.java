package it.gov.scuolesuperioridizagarolo.dada.bitorario.engine;

import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;

import java.util.Set;

/**
 * Created by stefano on 02/05/2018.
 */
public enum FilterAule {
    LABORATORI_MAI {
        @Override
        boolean accept(String materia, ClassData classe, String docente, RoomData aula, Set<CompatibilitaLaboratorio> c) {
            if (!aula.flagAulaLaboratorioPalestra()) return true;
            return false;
        }
    },

    LABORATORI_SEMPRE {
        @Override
        boolean accept(String materia, ClassData classe, String docente, RoomData aula, Set<CompatibilitaLaboratorio> c) {
            return true;
        }
    },

    LABORATORI_SOLO_SE_LIBERI {
        @Override
        boolean accept(String materia, ClassData classe, String docente, RoomData aula, Set<CompatibilitaLaboratorio> c) {
            return true;
        }
    },

    LABORATORI_SOLO_COMPATIBILI {
        @Override
        boolean accept(String materia, ClassData classe, String docente, RoomData aula, Set<CompatibilitaLaboratorio> c) {
            if (!aula.flagAulaLaboratorioPalestra()) return true;
            return c.contains(new CompatibilitaLaboratorio(classe, materia, aula));
            //return true;
        }
    };

    abstract boolean accept(String materia, ClassData classe, String docente, RoomData aula, Set<CompatibilitaLaboratorio> c);

    public boolean accept(BitOrarioOraLezione l, Set<CompatibilitaLaboratorio> c) {
        return accept(l.getMateriaPrincipale(), l.getClasse(), l.getDocentePrincipale(), l.getAula(), c);

    }

}
