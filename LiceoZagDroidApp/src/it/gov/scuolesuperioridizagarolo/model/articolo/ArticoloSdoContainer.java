package it.gov.scuolesuperioridizagarolo.model.articolo;

import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetails;
import it.gov.scuolesuperioridizagarolo.dao.customType.ArticoloDetailsCircolare;
import it.gov.scuolesuperioridizagarolo.model.dto.C_MyDate;

import java.util.*;

/**
 * Created by stefano on 08/03/2018.
 */
public class ArticoloSdoContainer<T extends ArticoloDetails> {

    public final List<ArticoloSdo<T>> articoli;

    public ArticoloSdoContainer() {
        articoli = new ArrayList<>();
    }

    public static Comparator<ArticoloSdo<ArticoloDetailsCircolare>> getArticoloTypeCircolareComparator() {
        return new Comparator<ArticoloSdo<ArticoloDetailsCircolare>>() {
            @Override
            public int compare(ArticoloSdo<ArticoloDetailsCircolare> a, ArticoloSdo<ArticoloDetailsCircolare> b) {


                final ArticoloDetailsCircolare dA = a.getDetails();
                final ArticoloDetailsCircolare dB = b.getDetails();

                final C_MyDate d1 = getDataGiornoMeseAnno(a.articolo.getDate());
                final C_MyDate d2 = getDataGiornoMeseAnno(b.articolo.getDate());
                final int i = d1.compareTo(d2);
                if (i != 0) return -i;
                if (dA==null){
                    throw new IllegalArgumentException("Errore codice A "+a);
                }
                if (dB==null){
                    throw new IllegalArgumentException("Errore codice B "+b);
                }
                if (dA.numeroCircolare < dB.numeroCircolare)
                    return 1;
                else if (dA.numeroCircolare > dB.numeroCircolare)
                    return -1;

                return dA.oggetto.compareTo(dB.oggetto);
            }

            public C_MyDate getDataGiornoMeseAnno(Date data) {
                return new C_MyDate(data);
            }
        };
    }

    public void sortBy(Comparator<ArticoloSdo<T>> c) {
        Collections.sort(articoli, c);
    }


    public Set<String> parole() {
        TreeSet<String> p = new TreeSet<>();
        for (ArticoloSdo<T> a : articoli) {
            p.addAll(a.articolo.getDetails().getWordsLowercase());
        }
        return p;
    }


}
