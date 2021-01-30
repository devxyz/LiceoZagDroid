package utility.aule2020_21.misura_ottimo_soluzione;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;
import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.ERoomArea;
import utility.aule2020_21.AssegnazioneClasseAula202021;
import utility.aule2020_21.AssegnazioneClasseAulaGiornaliera202021;
import utility.aule2020_21.Classe202021;
import utility.aule2020_21.MainPIanificazioneAule_V2;
import utility.aule2020_21.data.RipartizioneAuleClassiData;

import java.util.ArrayList;

public class AssegnazioneClasseAulaGiornaliera202021_MisuraPeso_classiScienzeAppPiano1 implements AssegnazioneClasseAulaGiornaliera202021_MisuraPeso {

    @Override
    public int misura(ArrayList<AssegnazioneClasseAulaGiornaliera202021> a) {
        int num = 0;
        for (ClassData classe : ClassData.values()) {
            if (classe.isOrdinario()) continue;

            for (int giorno = 0; giorno < a.size(); giorno++) {
                AssegnazioneClasseAula202021 assegnazione = MainPIanificazioneAule_V2.cerca(a, new Classe202021(classe), giorno);
                if (assegnazione == null) continue;
                if (assegnazione.aula == null) continue;
                if (
                        assegnazione.classe.toClassData()._section.equalsIgnoreCase("B") ||
                                assegnazione.classe.toClassData()._section.equalsIgnoreCase("D")
                )
                    if (assegnazione.aula.toRoomData().location == ERoomArea.AREA_A ||
                            assegnazione.aula.toRoomData().location == ERoomArea.AREA_B) {
                        num++;
                    }


            }
        }
        return num;

    }

    @Override
    public int compareTo(int a, int b) {
        if (a > b) return -1;
        if (a < b) return 1;
        return 0;
    }
}
