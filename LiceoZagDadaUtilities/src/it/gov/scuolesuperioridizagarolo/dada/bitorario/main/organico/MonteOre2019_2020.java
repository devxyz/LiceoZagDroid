package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.organico;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MonteOre2019_2020 {
    public static List<MateriaOraria2019_2020> oreList(Materia2019_2020 m) {
        List<MateriaOraria2019_2020> r = new LinkedList<>();
        r.addAll(oreDisegnoList_LICEO());
        r.addAll(oreItalianoList_LICEO_piu_IPIA());
        r.addAll(oreInformaticaList_LICEO_piu_IPIA());
        r.addAll(oreIngleseList_LICEO_piu_IPIA());
        r.addAll(oreStoriaFilosofiaList_LICEO());
        r.addAll(oreScienzeMotorieList_LICEO_piu_IPIA());
        r.addAll(oreScienzeNaturaliList_LICEO_piu_IPIA());
        r.addAll(oreMatList_LICEO_piu_IPIA());
        r.addAll(oreFisicaList_IPIA());
        r.addAll(oreDirittoList_IPIA());

        List<MateriaOraria2019_2020> r2 = new LinkedList<>();
        for (MateriaOraria2019_2020 x : r) {
            if (x.materia == m) {
                r2.add(x);
            }
        }
        return r2;
    }

    public static List<MateriaOraria2019_2020> oreItalianoList_LICEO_piu_IPIA() {
        List<MateriaOraria2019_2020> ris = new ArrayList<>();
        //biennio
        for (Classi2019_2020 c : Classi2019_2020.biennio(Ordinamento2019_2020.ORDINARIO)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A011_LETTERE_LATINO, 4, "italiano"));
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A011_LETTERE_LATINO, 3, "geostoria"));
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A011_LETTERE_LATINO, 3, "latino"));
        }

        for (Classi2019_2020 c : Classi2019_2020.biennio(Ordinamento2019_2020.SCIENZE_APPLICATE)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A011_LETTERE_LATINO, 4, "italiano"));
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A011_LETTERE_LATINO, 3, "geostoria"));
        }

        //triennio
        for (Classi2019_2020 c : Classi2019_2020.triennio(Ordinamento2019_2020.ORDINARIO)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A011_LETTERE_LATINO, 4, "italiano"));
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A011_LETTERE_LATINO, 3, "latino"));
        }

        for (Classi2019_2020 c : Classi2019_2020.triennio(Ordinamento2019_2020.SCIENZE_APPLICATE)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A011_LETTERE_LATINO, 4, "italiano"));
        }

        for (Classi2019_2020 c : Classi2019_2020.biennio(Ordinamento2019_2020.IPIA)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A012_ITALIANO_IPIA, 4, "italiano"));
        }
        for (Classi2019_2020 c : Classi2019_2020.triennio(Ordinamento2019_2020.IPIA)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A012_ITALIANO_IPIA, 4, "italiano"));
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A012_ITALIANO_IPIA, 2, "storia"));
        }

        for (Classi2019_2020 c : Classi2019_2020.classe(1, Ordinamento2019_2020.IPIA)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A012_ITALIANO_IPIA, 1, "storia"));
        }

        for (Classi2019_2020 c : Classi2019_2020.classe(2, Ordinamento2019_2020.IPIA)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A012_ITALIANO_IPIA, 2, "storia"));
        }

        for (Classi2019_2020 c : Classi2019_2020.classe(1, Ordinamento2019_2020.IPIA)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A021_GEOGRAFIA_IPIA, 1, "geografia"));
        }


        return ris;
    }

    public static List<MateriaOraria2019_2020> oreMatList_LICEO_piu_IPIA() {
        List<MateriaOraria2019_2020> ris = new ArrayList<>();
        //biennio
        for (Classi2019_2020 c : Classi2019_2020.biennio(Ordinamento2019_2020.ORDINARIO)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A026_MATEMATICA, 5, "Matematica Biennio"));
        }

        for (Classi2019_2020 c : Classi2019_2020.biennio(Ordinamento2019_2020.IPIA)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A026_MATEMATICA, 4, "Matematica e Informatica"));
        }

        for (Classi2019_2020 c : Classi2019_2020.triennio(Ordinamento2019_2020.IPIA)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A026_MATEMATICA, 3, "Matematica e Informatica"));
        }

        for (Classi2019_2020 c : Classi2019_2020.classe(1, Ordinamento2019_2020.SCIENZE_APPLICATE)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A026_MATEMATICA, 5, "Matematica Biennio"));
        }

        for (Classi2019_2020 c : Classi2019_2020.classe(2, Ordinamento2019_2020.SCIENZE_APPLICATE)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A026_MATEMATICA, 4, "Matematica Biennio"));
        }

        //triennio
        for (Classi2019_2020 c : Classi2019_2020.triennioLICEO()) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A027_MATEMATICA_FISICA, 4, "Matematica Triennio"));
        }

        //biennio
        for (Classi2019_2020 c : Classi2019_2020.biennioLICEO()) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A027_MATEMATICA_FISICA, 2, "Fisica Biennio"));
        }

        //triennio
        for (Classi2019_2020 c : Classi2019_2020.triennioLICEO()) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A027_MATEMATICA_FISICA, 3, "Fisica Triennio"));
        }


        return ris;
    }

    public static List<MateriaOraria2019_2020> oreFisicaList_IPIA() {
        List<MateriaOraria2019_2020> ris = new ArrayList<>();
        ris.add(new MateriaOraria2019_2020(Classi2019_2020._2A_IPIA, Materia2019_2020.A020_FISICA, 2));
        return ris;
    }

    public static List<MateriaOraria2019_2020> oreDisegnoList_LICEO() {
        List<MateriaOraria2019_2020> ris = new ArrayList<>();
        //biennio
        for (Classi2019_2020 c : Classi2019_2020.biennioLICEO()) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A017_DISEGNO_STORIAARTE, 2));
        }
        for (Classi2019_2020 c : Classi2019_2020.triennioLICEO()) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A017_DISEGNO_STORIAARTE, 2));
        }
        return ris;
    }

    public static List<MateriaOraria2019_2020> oreScienzeMotorieList_LICEO_piu_IPIA() {
        List<MateriaOraria2019_2020> ris = new ArrayList<>();
        //biennio
        for (Classi2019_2020 c : Classi2019_2020.values()) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A048_SCIENZE_MOTORIE, 2));

        }
        return ris;
    }

    public static List<MateriaOraria2019_2020> oreScienzeNaturaliList_LICEO_piu_IPIA() {
        List<MateriaOraria2019_2020> ris = new ArrayList<>();

        for (Classi2019_2020 c : Classi2019_2020.biennio(Ordinamento2019_2020.ORDINARIO)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A050_SCIENZE, 2));
        }

        for (Classi2019_2020 c : Classi2019_2020.triennio(Ordinamento2019_2020.ORDINARIO)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A050_SCIENZE, 3));
        }

        for (Classi2019_2020 c : Classi2019_2020.classe(1, Ordinamento2019_2020.SCIENZE_APPLICATE)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A050_SCIENZE, 3));
        }

        for (Classi2019_2020 c : Classi2019_2020.classe(2, Ordinamento2019_2020.SCIENZE_APPLICATE)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A050_SCIENZE, 4));
        }

        for (Classi2019_2020 c : Classi2019_2020.classe(1, Ordinamento2019_2020.IPIA)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A050_SCIENZE, 4));
        }

        for (Classi2019_2020 c : Classi2019_2020.triennio(Ordinamento2019_2020.SCIENZE_APPLICATE)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A050_SCIENZE, 5));
        }

        return ris;
    }

    public static List<MateriaOraria2019_2020> oreIngleseList_LICEO_piu_IPIA() {
        List<MateriaOraria2019_2020> ris = new ArrayList<>();
        //biennio
        for (Classi2019_2020 c : Classi2019_2020.values()) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.AB24_INGLESE, 3));

        }
        return ris;
    }

    public static List<MateriaOraria2019_2020> oreDirittoList_IPIA() {
        List<MateriaOraria2019_2020> ris = new ArrayList<>();
        //biennio
        for (Classi2019_2020 c : Classi2019_2020.biennio(Ordinamento2019_2020.IPIA)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A046_DIRITTO, 2));

        }
        return ris;
    }

    public static List<MateriaOraria2019_2020> oreInformaticaList_LICEO_piu_IPIA() {
        List<MateriaOraria2019_2020> ris = new ArrayList<>();
        //biennio
        for (Classi2019_2020 c : Classi2019_2020.biennio(Ordinamento2019_2020.SCIENZE_APPLICATE)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A041_INFORMATICA, 2));
        }
        for (Classi2019_2020 c : Classi2019_2020.triennio(Ordinamento2019_2020.SCIENZE_APPLICATE)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A041_INFORMATICA, 2));
        }

        for (Classi2019_2020 c : Classi2019_2020.classe(1, Ordinamento2019_2020.IPIA)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A041_INFORMATICA, 2, "TIC"));
        }

        return ris;
    }

    public static List<MateriaOraria2019_2020> oreStoriaFilosofiaList_LICEO() {
        List<MateriaOraria2019_2020> ris = new ArrayList<>();
        //biennio
        for (Classi2019_2020 c : Classi2019_2020.triennio(Ordinamento2019_2020.ORDINARIO)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A019_FILOSOFIA_STORIA, 2, "storia"));
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A019_FILOSOFIA_STORIA, 3, "filosofia"));
        }
        for (Classi2019_2020 c : Classi2019_2020.triennio(Ordinamento2019_2020.SCIENZE_APPLICATE)) {
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A019_FILOSOFIA_STORIA, 2, "storia"));
            ris.add(new MateriaOraria2019_2020(c, Materia2019_2020.A019_FILOSOFIA_STORIA, 2, "filosofia"));
        }
        return ris;
    }
}
