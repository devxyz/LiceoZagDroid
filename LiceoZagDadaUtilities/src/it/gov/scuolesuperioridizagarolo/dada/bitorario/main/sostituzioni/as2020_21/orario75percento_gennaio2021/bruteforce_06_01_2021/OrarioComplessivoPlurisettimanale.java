package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.sostituzioni.as2020_21.orario75percento_gennaio2021.bruteforce_06_01_2021;

import it.gov.scuolesuperioridizagarolo.model.bitorario.classes.ClassData;

import java.util.*;

class OrarioComplessivoPlurisettimanale {
    final List<OrarioClasse> orario;
    final Map<ClassData, OrarioClasse> orarioMap;

    public OrarioComplessivoPlurisettimanale(int numSettimaneOrario, int numGiorniAperturaScuola_PerSettimana) {
        orario = new ArrayList<>();
        orarioMap = new TreeMap<>();
        for (ClassData c : ClassData.values()) {
            OrarioClasse e = new OrarioClasse(c, numSettimaneOrario, numGiorniAperturaScuola_PerSettimana);
            orario.add(e);
            orarioMap.put(c, e);
        }
    }

    public OrarioClasse get(ClassData c) {
        return orarioMap.get(c);
    }

    public Set<ClassData> classiSenzaAssegnazione(int indexSettimana, int indexGiorno) {
        Set<ClassData> ris = new TreeSet<>();
        for (OrarioClasse o : orario) {
            if (o.settimane[indexSettimana].giorni[indexGiorno] == null)
                ris.add(o.classe);
        }
        return ris;
    }

    public int totClassiNonAssegnate(int indexSettimana, int indexGiorno) {
        return totClassiBy(null, indexSettimana, indexGiorno);
    }

    public int totClassiBy(TipoOrario t, int numSettimana, int numGiornoSettimana) {
        int count = 0;
        for (OrarioClasse c : orario) {
            TipoOrario tipoOrario = c.settimane[numSettimana].giorni[numGiornoSettimana];
            if (tipoOrario == t)
                count++;
        }
        return count;
    }

    public int totStudentiBy(TipoOrario t, int indexSettimana, int indexGiorno) {
        int count = 0;
        for (OrarioClasse c : orario) {
            TipoOrario tipoOrario = c.settimane[indexSettimana].giorni[indexGiorno];
            if (tipoOrario == t)
                count += c.classe.numberOfStudents;
        }
        return count;
    }

    //per ogni settimana ritorna il totale
    public int[] totStudentiByGiorno(TipoOrario t, int indexGiorno) {
        int[] count = new int[orario.get(0).settimane.length];
        for (OrarioClasse c : orario) {
            GiorniSettimana[] settimane = c.settimane;
            for (int i = 0, settimaneLength = settimane.length; i < settimaneLength; i++) {
                GiorniSettimana s = settimane[i];
                TipoOrario tipoOrario = s.giorni[indexGiorno];
                if (tipoOrario == t)
                    count[i] += c.classe.numberOfStudents;

            }
        }
        return count;
    }

    public int totStudentiBySettimana(TipoOrario t, int indexSettimana) {
        int count = 0;
        for (OrarioClasse c : orario) {
            for (TipoOrario tipoOrario : c.settimane[indexSettimana].giorni) {
                if (tipoOrario == t)
                    count += c.classe.numberOfStudents;

            }
        }
        return count;
    }


    public int totClassiBy(TipoOrario t) {
        int count = 0;
        for (OrarioClasse c : orario) {
            for (GiorniSettimana s : c.settimane) {
                for (TipoOrario g : s.giorni) {
                    if (g == t) count++;
                }
            }
        }
        return count;
    }

    public int totStudentiBy(TipoOrario t) {
        int count = 0;
        for (OrarioClasse c : orario) {
            for (GiorniSettimana s : c.settimane) {
                for (TipoOrario g : s.giorni) {
                    if (g == t) count += c.classe.numberOfStudents;
                }
            }
        }
        return count;
    }

    public void printReport() {
        for (OrarioClasse a : orario) {
            a.printReport();
        }
    }

    public int contaNumStudentiPresenti(int indexSettimana, int indexGiorno) {
        int count = 0;
        int i1 = this.totStudentiBy(TipoOrario.ORE8, indexSettimana, indexGiorno);
        int i2 = this.totStudentiBy(TipoOrario.ORE10, indexSettimana, indexGiorno);
        return i1 + i2;
    }


    public void printReportTotaliPerGiorno() {
        int numSettimane = orario.get(0).settimane.length;
        int numGiorni = orario.get(0).settimane[0].giorni.length;

        for (int indexSettimane = 0; indexSettimane < numSettimane; indexSettimane++) {
            for (int indexGiorni = 0; indexGiorni < numGiorni; indexGiorni++) {
                System.out.printf(" > Giorno %d di %d:\t", (indexGiorni + 1), (indexSettimane + 1));
                for (TipoOrario t : TipoOrario.values()) {
                    int i = this.totStudentiBy(t, indexSettimane, indexGiorni);
                    int i2 = this.totClassiBy(t, indexSettimane, indexGiorni);
                    System.out.printf("\t%3s (%3d) (n.%2d)", t.descrizione, i, i2);
                }
                System.out.println();
            }
            System.out.println("===============");
        }

    }

    public boolean verificaNumStudentiPresenti(int maxValue) {
        int numSettimane = orario.get(0).settimane.length;
        int numGiorni = orario.get(0).settimane[0].giorni.length;

        for (int indexSettimane = 0; indexSettimane < numSettimane; indexSettimane++) {
            for (int indexGiorni = 0; indexGiorni < numGiorni; indexGiorni++) {
                int i1 = this.totStudentiBy(TipoOrario.ORE8, indexSettimane, indexGiorni);
                int i2 = this.totStudentiBy(TipoOrario.ORE10, indexSettimane, indexGiorni);
                if (i1 + i2 > maxValue) return false;
            }
        }
        return true;
    }

    public boolean verificaVincoli(ArrayList<Main_03_Orario75percent_bruteforce_dad_settimana_intera_4settimane.VincoliAssegnazione> vincoli) {
        int numSettimane = orario.get(0).settimane.length;
        int numGiorni = orario.get(0).settimane[0].giorni.length;
        for (ClassData c : ClassData.values()) {
            OrarioClasse orarioClasse = this.get(c);
            for (int indexSettimane = 0; indexSettimane < numSettimane; indexSettimane++) {
                GiorniSettimana settimana = orarioClasse.getSettimana(indexSettimane);
                for (int indexGiorni = 0; indexGiorni < numGiorni; indexGiorni++) {
                    TipoOrario giorno = settimana.getGiorno(indexGiorni);
                    for (Main_03_Orario75percent_bruteforce_dad_settimana_intera_4settimane.VincoliAssegnazione x : vincoli) {
                        if (!x.accept(c, giorno, indexGiorni, indexGiorni)) return false;
                    }
                }
            }

        }
        return true;
    }

    public boolean verificaNumClassiPresenti(int maxValue) {
        int numSettimane = orario.get(0).settimane.length;
        int numGiorni = orario.get(0).settimane[0].giorni.length;

        for (int indexSettimane = 0; indexSettimane < numSettimane; indexSettimane++) {
            for (int indexGiorni = 0; indexGiorni < numGiorni; indexGiorni++) {
                int i1 = this.totClassiBy(TipoOrario.ORE8, indexSettimane, indexGiorni);
                int i2 = this.totClassiBy(TipoOrario.ORE10, indexSettimane, indexGiorni);
                if (i1 + i2 > maxValue) return false;
            }
        }
        return true;
    }

    public boolean verificaMaxNumeroEntrate10(int maxValue) {
        int numSettimane = orario.get(0).settimane.length;
        int numGiorni = orario.get(0).settimane[0].giorni.length;

        //per ogni classe
        for (ClassData classe : ClassData.values()) {

            for (int indexGiorni = 0; indexGiorni < numGiorni; indexGiorni++) {
                OrarioClasse orarioClasse = this.get(classe);
                int tot = orarioClasse.contaNumeroGiorni(TipoOrario.ORE10);
                if (tot > maxValue) return false;
            }

        }
        return true;
    }

    public boolean verificaMinMaxGIORNO_LIBERO_perOgniGiornoDelleSettimane(int minValue, int maxValue) {
        int numSettimane = orario.get(0).settimane.length;
        int numGiorni = orario.get(0).settimane[0].giorni.length;

        for (int indexSettimane = 0; indexSettimane < numSettimane; indexSettimane++) {
            for (int indexGiorni = 0; indexGiorni < numGiorni; indexGiorni++) {
                int i1 = this.totClassiBy(TipoOrario.GIORNO_LIBERO, indexSettimane, indexGiorni);
                if (i1 > maxValue) {
                    return false;
                }
                if (i1 < minValue) {
                    return false;
                }
            }
        }
        return true;
    }

    public void assegnaP8_sabato() {
        int numGiorni = orario.get(0).settimane[0].giorni.length;
        if (numGiorni < 6) throw new IllegalArgumentException("No sabato a scuola");
        //per ogni classe
        for (ClassData classe : ClassData.values()) {
            OrarioClasse o = this.get(classe);
            o.update_PerTutteLeSettimane_UnPrecisoGiorno(5, TipoOrario.ORE10, TipoOrario.ORE8);

        }
    }

    public boolean verificaNumStudentiDAD(int maxValue) {
        int numSettimane = orario.get(0).settimane.length;
        int numGiorni = orario.get(0).settimane[0].giorni.length;

        for (int indexSettimane = 0; indexSettimane < numSettimane; indexSettimane++) {
            for (int indexGiorni = 0; indexGiorni < numGiorni; indexGiorni++) {
                int i1 = this.totStudentiBy(TipoOrario.DAD, indexSettimane, indexGiorni);
                if (i1 > maxValue) return false;
            }
        }
        return true;
    }

}
