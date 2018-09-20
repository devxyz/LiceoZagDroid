package it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.impl;

import it.gov.scuolesuperioridizagarolo.dada.bitorario.constraint.CheckForClass;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.RoomData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EOra;

/**
 * Created by stefano on 27/09/2017.
 */
public class CheckForClassroom_CoerenzaCapacitàClassiAule extends CheckForClass {
    @Override
    protected String check(BitOrarioGrigliaOrario l, ClassData nomeClasse) {
        StringBuilder sb = new StringBuilder();
        for (EGiorno e : EGiorno.values()) {
            if (!e.flagGiornoDiLezione()) continue;
            for (EOra o : EOra.values()) {
                if (!o.flagOraDiLezione()) continue;

                final BitOrarioOraLezione lezioneClasse = l.getLezioneInClasse(o, e, nomeClasse);
                if (lezioneClasse == null) continue;
                if (lezioneClasse.getAula()== RoomData.USCITA_DIDATTICA)continue;

                final int stu = lezioneClasse.getClasse().numberOfStudents;
                final int aula = lezioneClasse.getAula().maxStudents;
                if (stu > aula) {
                    sb.append("Lezioni in aula " + lezioneClasse.getAula() + " della classe " + lezioneClasse.getClasse() + " con posti insufficienti: " + stu + " studenti, " + aula + " posti ");

                }

            }
        }
        return sb.toString();
    }
}
