package it.gov.scuolesuperioridizagarolo.dada.bitorario.engine;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.main.MainParserGeneraStampeOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by stefano on 01/05/2018.
 */
public class CompatibilitaLaboratorio {
    public final ClassData classe;
    public final String materia;
    public final RoomData room;

    public CompatibilitaLaboratorio(ClassData classe, String materia, RoomData room) {
        this.classe = classe;
        this.materia = materia.toLowerCase();
        this.room = room;

    }

    public static Set<CompatibilitaLaboratorio> estrai(BitOrarioGrigliaOrario o) {
        Set<CompatibilitaLaboratorio> ris = new HashSet<>();

        for (BitOrarioOraLezione l : o.getLezioni()) {
            final RoomData a = l.getAula();
            if (a != null && a.isAulaLaboratorioPalestra()) {
                ris.add(new CompatibilitaLaboratorio(l.getClasse(), l.getMateriaPrincipale(), l.getAula()));
            }
        }

        return ris;
    }

    public static void main(String[] args) throws IOException {
        final BitOrarioGrigliaOrario orarioTotale = MainParserGeneraStampeOrario.parsingDefaultFileOrarioAuleClassi(new File(MainParserGeneraStampeOrario.DEBUG_FOLDER_INPUT));
        final Set<CompatibilitaLaboratorio> estrai = estrai(orarioTotale);
        for (CompatibilitaLaboratorio compatibilitaLaboratorio : estrai) {
            System.out.println(compatibilitaLaboratorio);
        }


    }

    @Override
    public String toString() {
        return "CompatibilitaLaboratorio{" +
                "classe='" + classe + '\'' +
                ", materia='" + materia + '\'' +
                ", room='" + room + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompatibilitaLaboratorio that = (CompatibilitaLaboratorio) o;

        if (classe != null ? !classe.equals(that.classe) : that.classe != null) return false;
        if (materia != null ? !materia.equals(that.materia) : that.materia != null) return false;
        return room != null ? room.equals(that.room) : that.room == null;

    }

    @Override
    public int hashCode() {
        int result = classe != null ? classe.hashCode() : 0;
        result = 31 * result + (materia != null ? materia.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        return result;
    }
}
