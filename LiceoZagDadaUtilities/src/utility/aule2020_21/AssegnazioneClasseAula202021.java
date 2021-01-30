package utility.aule2020_21;

import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;

public class AssegnazioneClasseAula202021 {
    public final Classe202021 classe;
    public final Aula202021 aula;
    public final EGiorno giorno;

    public UtilizzoClassi toUtilizzoClassi() {
        if (classe == null || aula == null) return null;
        return new UtilizzoClassi(aula.toRoomData(), classe.toClassData(), giorno);
    }

    AssegnazioneClasseAula202021(Classe202021 classe, Aula202021 aula, EGiorno giorno) {
        this.classe = classe;
        this.aula = aula;
        this.giorno = giorno;
        if (aula != null && classe != null) {
            if (aula.capienza < classe.numerosita) throw new IllegalArgumentException("Capienza insufficiente");
        }
    }

    public String toString() {
        if (classe != null && aula != null) {
            return classe.classe + "(" + classe.numerosita + ") -> " + aula.aula + "(" + aula.capienza + ")";
        }
        if (classe == null) {
            return aula.aula + " (" + aula.capienza + ") AULA LIBERA";
        }
        return classe.classe + "(" + classe.numerosita + ") " + " CLASSE NON ALLOCATA";
    }
}
