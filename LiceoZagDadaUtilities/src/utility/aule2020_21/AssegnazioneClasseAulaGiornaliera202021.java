package utility.aule2020_21;

import utility.aule2020_21.data.RipartizioneAuleClassiData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AssegnazioneClasseAulaGiornaliera202021 {
    public final ArrayList<AssegnazioneClasseAula202021> assegnazioni = new ArrayList<>();

    public ArrayList<UtilizzoClassi> toUtilizzoClassi() {
        ArrayList<UtilizzoClassi> ris = new ArrayList<>();
        for (AssegnazioneClasseAula202021 a : assegnazioni) {
            if (a.aula == null) {
                throw new IllegalArgumentException("Aula non assegnata PER LA CLASSE " + a.classe);
            }
            UtilizzoClassi e = a.toUtilizzoClassi();
            if (e != null)
                ris.add(e);
        }
        return ris;
    }

    public AssegnazioneClasseAula202021 cerca(Classe202021 classe) {
        for (AssegnazioneClasseAula202021 a : assegnazioni) {
            if (a.classe != null && a.classe.equals(classe))
                return a;
        }
        return null;
    }

    public List<Aula202021> cercaAuleLibere() {
        List<Aula202021> ris = new ArrayList<>();
        for (AssegnazioneClasseAula202021 a : assegnazioni) {
            if (a.classe == null && a.aula != null)
                ris.add(a.aula);
        }
        return ris;
    }

    public List<Classe202021> cercaClassiACasa() {
        Collection<Classe202021> classi = RipartizioneAuleClassiData.singleton().classi();
        List<Classe202021> ris = new ArrayList<>();
        for (Classe202021 c : classi) {
            AssegnazioneClasseAula202021 cerca = cerca(c);
            if (cerca == null || cerca.aula == null) ris.add(c);
        }
        return ris;
    }

    public AssegnazioneClasseAula202021 cerca(Aula202021 aula) {
        for (AssegnazioneClasseAula202021 a : assegnazioni) {
            if (a.aula != null && a.aula.equals(aula))
                return a;
        }
        return null;
    }

    public AssegnazioneClasseAulaGiornaliera202021() {
    }

    public AssegnazioneClasseAulaGiornaliera202021(ArrayList<AssegnazioneClasseAula202021> a) {
        assegnazioni.addAll(a);
    }

    public ArrayList<AssegnazioneClasseAula202021> assegnazioniEffettuate() {
        ArrayList<AssegnazioneClasseAula202021> ris = new ArrayList<>();
        for (AssegnazioneClasseAula202021 a : assegnazioni) {
            if (a.classe != null && a.aula != null)
                ris.add(a);
        }
        return ris;
    }

    public ArrayList<AssegnazioneClasseAula202021> assegnazioniNonEffettuate() {
        ArrayList<AssegnazioneClasseAula202021> ris = new ArrayList<>();
        for (AssegnazioneClasseAula202021 a : assegnazioni) {
            if (a.classe != null && a.aula == null)
                ris.add(a);
        }
        return ris;
    }

    public ArrayList<AssegnazioneClasseAula202021> assegnazioniAuleLibere() {
        ArrayList<AssegnazioneClasseAula202021> ris = new ArrayList<>();
        for (AssegnazioneClasseAula202021 a : assegnazioni) {
            if (a.classe == null && a.aula != null)
                ris.add(a);
        }
        return ris;
    }
}
