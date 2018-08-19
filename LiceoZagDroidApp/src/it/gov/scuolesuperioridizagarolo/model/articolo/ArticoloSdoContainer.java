package it.gov.scuolesuperioridizagarolo.model.articolo;

import it.gov.scuolesuperioridizagarolo.model.dto.C_MyDate;

import java.util.*;

/**
 * Created by stefano on 08/03/2018.
 */
public class ArticoloSdoContainer<T extends ArticoloType> {

    public final List<ArticoloSdo<T>> articoli;

    public ArticoloSdoContainer() {
        articoli = new ArrayList<>();
    }

    public static Comparator<ArticoloSdo<ArticoloTypeCircolare>> getArticoloTypeCircolareComparator() {
        return new Comparator<ArticoloSdo<ArticoloTypeCircolare>>() {
            @Override
            public int compare(ArticoloSdo<ArticoloTypeCircolare> a, ArticoloSdo<ArticoloTypeCircolare> b) {


                final ArticoloTypeCircolare dA = a.wrapperArticolo.articoloDettagli;
                final ArticoloTypeCircolare dB = b.wrapperArticolo.articoloDettagli;

                final C_MyDate d1 = getDataGiornoMeseAnno(dA.dataCircolare);
                final C_MyDate d2 = getDataGiornoMeseAnno(dB.dataCircolare);
                final int i = d1.compareTo(d2);
                if (i != 0) return -i;

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
            p.addAll(a.parole());
        }
        return p;
    }


}
