package it.gov.scuolesuperioridizagarolo.dada.bitorario.main.organico;

import java.util.ArrayList;

public enum Classi2019_2020 {
    //30+60

    _1A(Ordinamento2019_2020.ORDINARIO),
    _1B(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _1C(Ordinamento2019_2020.ORDINARIO),
    _1D(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _1E(Ordinamento2019_2020.ORDINARIO),
    _1F(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _1G(Ordinamento2019_2020.ORDINARIO),
    _1H(Ordinamento2019_2020.SCIENZE_APPLICATE),


    _2A(Ordinamento2019_2020.ORDINARIO),
    _2B(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _2C(Ordinamento2019_2020.ORDINARIO),
    _2D(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _2E(Ordinamento2019_2020.ORDINARIO),
    _2F(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _2H(Ordinamento2019_2020.SCIENZE_APPLICATE),

    _3A(Ordinamento2019_2020.ORDINARIO),
    _3B(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _3C(Ordinamento2019_2020.ORDINARIO),
    _3D(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _3E(Ordinamento2019_2020.ORDINARIO),
    _3F(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _3G(Ordinamento2019_2020.ORDINARIO),
    _3H(Ordinamento2019_2020.SCIENZE_APPLICATE),

    _4A(Ordinamento2019_2020.ORDINARIO),
    _4B(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _4C(Ordinamento2019_2020.ORDINARIO),
    _4D(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _4E(Ordinamento2019_2020.ORDINARIO),
    _4F(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _4G(Ordinamento2019_2020.ORDINARIO),

    _5A(Ordinamento2019_2020.ORDINARIO),
    _5B(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _5C(Ordinamento2019_2020.ORDINARIO),
    _5D(Ordinamento2019_2020.SCIENZE_APPLICATE),
    _5E(Ordinamento2019_2020.ORDINARIO),

    _1A_IPIA(Ordinamento2019_2020.IPIA),
    _1B_IPIA(Ordinamento2019_2020.IPIA),
    _2A_IPIA(Ordinamento2019_2020.IPIA),
    _3A_IPIA(Ordinamento2019_2020.IPIA),
    _4A_IPIA(Ordinamento2019_2020.IPIA),

    _5A_IPIA(Ordinamento2019_2020.IPIA),
    _5B_IPIA(Ordinamento2019_2020.IPIA),


    ;
    public final Ordinamento2019_2020 ordinamento;

    Classi2019_2020(Ordinamento2019_2020 ordinamento) {
        this.ordinamento = ordinamento;
    }

    public static ArrayList<Classi2019_2020> biennio(Ordinamento2019_2020 tipo) {
        ArrayList<Classi2019_2020> ris = new ArrayList<>(values().length);
        for (Classi2019_2020 x : values()) {
            if (x.ordinamento == tipo && x.classe() < 3) {
                ris.add(x);
            }
        }
        return ris;
    }

    public static ArrayList<Classi2019_2020> biennioLICEO() {
        ArrayList<Classi2019_2020> ris = new ArrayList<>(values().length);
        for (Classi2019_2020 x : values()) {
            if (x.classe() < 3 && (x.ordinamento == Ordinamento2019_2020.ORDINARIO || x.ordinamento == Ordinamento2019_2020.SCIENZE_APPLICATE)) {
                ris.add(x);
            }
        }
        return ris;
    }

    public static ArrayList<Classi2019_2020> classe(int classe, Ordinamento2019_2020 ordinamento) {
        ArrayList<Classi2019_2020> ris = new ArrayList<>(values().length);
        for (Classi2019_2020 x : values()) {
            if (x.classe() == classe && x.ordinamento == ordinamento) {
                ris.add(x);
            }
        }
        return ris;
    }

    public static ArrayList<Classi2019_2020> triennioLICEO() {
        ArrayList<Classi2019_2020> ris = new ArrayList<>(values().length);
        for (Classi2019_2020 x : values()) {
            if (x.classe() >= 3 && (x.ordinamento == Ordinamento2019_2020.ORDINARIO || x.ordinamento == Ordinamento2019_2020.SCIENZE_APPLICATE)) {
                ris.add(x);
            }
        }
        return ris;
    }

    public static ArrayList<Classi2019_2020> triennioIPIA() {
        ArrayList<Classi2019_2020> ris = new ArrayList<>(values().length);
        for (Classi2019_2020 x : values()) {
            if (x.classe() >= 3 && x.ordinamento == Ordinamento2019_2020.IPIA) {
                ris.add(x);
            }
        }
        return ris;
    }

    public static ArrayList<Classi2019_2020> biennioIPIA() {
        ArrayList<Classi2019_2020> ris = new ArrayList<>(values().length);
        for (Classi2019_2020 x : values()) {
            if (x.classe() < 3 && x.ordinamento == Ordinamento2019_2020.IPIA) {
                ris.add(x);
            }
        }
        return ris;
    }

    public static ArrayList<Classi2019_2020> triennio(Ordinamento2019_2020 tipo) {
        ArrayList<Classi2019_2020> ris = new ArrayList<>(values().length);
        for (Classi2019_2020 x : values()) {
            if (x.ordinamento == tipo && x.classe() >= 3) {
                ris.add(x);
            }
        }
        return ris;
    }

    public String sezione() {
        return name().charAt(2) + "";
    }

    public int classe() {
        return Integer.parseInt(name().charAt(1) + "");
    }

    public String nomeClasse() {
        return classe() + sezione();
    }
}
