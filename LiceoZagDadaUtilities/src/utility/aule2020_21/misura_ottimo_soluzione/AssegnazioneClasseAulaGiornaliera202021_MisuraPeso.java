package utility.aule2020_21.misura_ottimo_soluzione;

import utility.aule2020_21.AssegnazioneClasseAulaGiornaliera202021;

import java.util.ArrayList;

public interface AssegnazioneClasseAulaGiornaliera202021_MisuraPeso {
    //effettua la misura del valore di interesse
    int misura(ArrayList<AssegnazioneClasseAulaGiornaliera202021> a);

    //indica quale è il valore migliore (-1 il primo, 1 il secondo, 0 uguali)
    int compareTo(int a, int b);
}
