package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario75percento_gennaio2021.bruteforce_06_01_2021;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

class OrarioClasse {
    final ClassData classe;
    final GiorniSettimana[] settimane;

    public GiorniSettimana getSettimana(int indexSettimana) {
        return settimane[indexSettimana];
    }

    //per tutti i giorni della settimana specificata assegna quel tipo di orario, eventualmente ignorando i giorni gia' qassegnati
    public void set_PerTuttiIGiorni_UnaPrecisaSettimana(int indexSettimana, TipoOrario t, boolean skipNotNULL) {
        settimane[indexSettimana].set_PerTuttiIGiorniDellaSettimana(t, skipNotNULL);
    }

    //imposta il tipo in un preciso giorno di tutte le settimane
    public void set_PerTutteLeSettimane_UnPrecisoGiorno(int indexGiorno, TipoOrario t, boolean skipNotNULL) {
        for (GiorniSettimana s : settimane) {
            if (skipNotNULL) {
                if (s.giorni[indexGiorno] != null) continue;
            }
            s.giorni[indexGiorno] = t;
        }
    }

    //imposta il tipo in un preciso giorno di tutte le settimane
    public void update_PerTutteLeSettimane_UnPrecisoGiorno(int indexGiorno, TipoOrario t_prec, TipoOrario t_new) {
        for (GiorniSettimana s : settimane) {
            if (s.giorni[indexGiorno] == t_prec) {
                s.giorni[indexGiorno] = t_new;
            }

        }
    }

    public int contaNumeroGiorni(TipoOrario t) {
        int i = 0;
        for (GiorniSettimana g : settimane) {
            i += g.count(t);
        }
        return i;
    }

    public OrarioClasse(ClassData classe, int numSettimaneOrario, int numGiorniAperturaScuola_PerSettimana) {
        this.classe = classe;
        settimane = new GiorniSettimana[numSettimaneOrario];
        for (int i = 0; i < numSettimaneOrario; i++) {
            settimane[i] = new GiorniSettimana(numGiorniAperturaScuola_PerSettimana);
        }
    }

    void printReport() {
        System.out.printf("%3s\t", classe.className);
        for (GiorniSettimana s : settimane) {
            System.out.print("|\t");
            for (TipoOrario g : s.giorni) {
                System.out.printf("%3s\t", g == null ? "NLL" : g.descrizione);
            }
        }
        System.out.println();
    }


    @Deprecated
    public void scegliGiornoLiberoCasuale(Random r) {

        int i = r.nextInt(numGiorniAperturaScuola_PerSettimana());
        set_PerTutteLeSettimane_UnPrecisoGiorno(i, TipoOrario.GIORNO_LIBERO, false);
    }


    public void scegliSettimaneDAD(Random r, int numSettimaneDAD) {
        if (numSettimaneDAD > settimane.length)
            throw new IllegalArgumentException("Numero settimane DAD maggiore delle settimane");
        Set<Integer> indexSettimaneDAD = new TreeSet<>();
        while (indexSettimaneDAD.size() < numSettimaneDAD) {
            int i = r.nextInt(settimane.length);
            indexSettimaneDAD.add(i);
        }
        for (Integer i : indexSettimaneDAD) {
            //assegna la DAD a tutti i giorni vuoti
            settimane[i].set_PerTuttiIGiorniDellaSettimana(TipoOrario.DAD, true);
        }
    }

    public int numGiorniAperturaScuola_PerSettimana() {
        return settimane[0].giorni.length;
    }

    public int numSettimaneOrario() {
        return settimane.length;
    }
}
