package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario75percento_gennaio2021.bruteforce_06_01_2021;

class GiorniSettimana {
    public int count(TipoOrario t) {
        int count = 0;
        for (TipoOrario g : giorni) {
            if (g != null && g == t)
                count++;
        }
        return count;
    }

    TipoOrario getGiorno(int indexGiorno) {
        return giorni[indexGiorno];
    }

    final TipoOrario[] giorni;

    public void set_PerTuttiIGiorniDellaSettimana(TipoOrario t, boolean skipNotNULL) {
        for (int i = 0, giorniLength = giorni.length; i < giorniLength; i++) {
            if (skipNotNULL) {
                if (giorni[i] != null) continue;
            }
            giorni[i] = t;

        }
    }

    public GiorniSettimana(int numGiorniAperturaScuola_PerSettimana) {
        giorni = new TipoOrario[numGiorniAperturaScuola_PerSettimana];
    }
}
