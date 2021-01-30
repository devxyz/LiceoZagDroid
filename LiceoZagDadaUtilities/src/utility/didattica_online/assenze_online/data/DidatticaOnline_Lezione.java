package utility.didattica_online.assenze_online.data;

import it.gov.scuolesuperioridizagarolo.model.dto.C_MyDate;

import java.util.Comparator;

public class DidatticaOnline_Lezione implements Comparable<DidatticaOnline_Lezione> {
    public final C_MyDate data;
    public final String classe;

    public DidatticaOnline_Lezione(C_MyDate data, String classe) {
        this.data = data;
        this.classe = classe.trim();
    }

    public C_MyDate getData() {
        return data;
    }

    public String getClasse() {
        return classe;
    }


    private static final Comparator<DidatticaOnline_Lezione> lezioneCompara = Comparator.comparing(DidatticaOnline_Lezione::getClasse).thenComparing(DidatticaOnline_Lezione::getData);

    @Override
    public int compareTo(DidatticaOnline_Lezione o) {
        return lezioneCompara.compare(this, o);
    }

    @Override
    public String toString() {
        return "DidatticaOnline_Lezione{" +
                "data=" + data +
                ", classe='" + classe + '\'' +
                '}';
    }
}
