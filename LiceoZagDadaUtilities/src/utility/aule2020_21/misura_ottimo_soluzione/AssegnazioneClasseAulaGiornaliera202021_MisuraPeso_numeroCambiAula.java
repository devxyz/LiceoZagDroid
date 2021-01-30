package utility.aule2020_21.misura_ottimo_soluzione;

import utility.aule2020_21.AssegnazioneClasseAula202021;
import utility.aule2020_21.AssegnazioneClasseAulaGiornaliera202021;
import utility.aule2020_21.Classe202021;
import utility.aule2020_21.MainPIanificazioneAule_V2;
import utility.aule2020_21.data.RipartizioneAuleClassiData;

import java.util.ArrayList;

public class AssegnazioneClasseAulaGiornaliera202021_MisuraPeso_numeroCambiAula implements AssegnazioneClasseAulaGiornaliera202021_MisuraPeso {

    @Override
    public int misura(ArrayList<AssegnazioneClasseAulaGiornaliera202021> a) {
        int num = 0;
        ArrayList<Classe202021> classi = RipartizioneAuleClassiData.singleton().classi();

        for (Classe202021 c : classi) {
            AssegnazioneClasseAula202021 prec = null;
            for (int giorno = 0; giorno < a.size(); giorno++) {
                AssegnazioneClasseAula202021 assegnazione = MainPIanificazioneAule_V2.cerca(a, c, giorno);
                if (assegnazione == null) continue;
                if (assegnazione.aula == null) continue;

                if (prec == null || prec.aula == null) {
                    prec = assegnazione;
                } else {
                    if (!prec.aula.aula.equals(assegnazione.aula.aula))
                        num++;
                    prec = assegnazione;
                }

            }
        }
        return num;

    }

    @Override
    public int compareTo(int a, int b) {
        if (a < b) return -1;
        if (a > b) return 1;
        return 0;
    }
}
