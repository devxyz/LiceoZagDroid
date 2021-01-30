package it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values;

import java.util.Calendar;
import java.util.Date;

public enum EGiorno {
    LUNEDI(1, "lunedi'"),
    MARTEDI(2, "martedi'"),
    MERCOLEDI(3, "mercoledi'"),
    GIOVEDI(4, "giovedi'"),
    VENERDI(5, "venerdi'"),
    SABATO(6, "sabato"),
    DOMENICA(7, "domenica"),
    ;


    private final int progressivo;
    private final String nome;


    EGiorno(int progressivo, String nome) {
        this.progressivo = progressivo;
        this.nome = nome;
    }

    public static EGiorno valueOf(Date d) {
        final Calendar instance = Calendar.getInstance();
        instance.setTime(d);


        /*
        <code>SUNDAY</code>,
      <code>MONDAY</code>, <code>TUESDAY</code>, <code>WEDNESDAY</code>,
      <code>THURSDAY</code>, <code>FRIDAY</code>, and <code>SATURDAY</code
         */
        final int i = instance.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case Calendar.MONDAY:
                return EGiorno.LUNEDI;
            case Calendar.TUESDAY:
                return EGiorno.MARTEDI;
            case Calendar.WEDNESDAY:
                return EGiorno.MERCOLEDI;
            case Calendar.THURSDAY:
                return EGiorno.GIOVEDI;
            case Calendar.FRIDAY:
                return EGiorno.VENERDI;
            case Calendar.SATURDAY:
                return EGiorno.SABATO;
            case Calendar.SUNDAY:
                return EGiorno.DOMENICA;
            default:
                return null;
        }
    }

    public static int numeroGiorniDiLezione() {
        int c = 0;
        for (EGiorno g : values()) {
            if (g.flagGiornoDiLezione()) c++;
        }
        return c;
    }

    public static EGiorno[] valuesGiorniDiLezione() {
        EGiorno[] ris = new EGiorno[numeroGiorniDiLezione()];
        int i = 0;
        for (EGiorno x : values()) {
            if (x.flagGiornoDiLezione()) {
                ris[i++] = x;
            }
        }
        return ris;
    }

    public static EGiorno getToday() {
        return valueOf(new Date());


    }

    public String shortName() {
        return nome.substring(0, 3).toUpperCase();
    }

    public boolean flagGiornoDiLezione() {
        return this != DOMENICA && this != SABATO;
    }

    public boolean isToday() {
        return this == getToday();
    }

    public int getProgressivo() {
        return progressivo;
    }

    public String getNome() {
        return nome;
    }

    public EGiorno next() {
        if (ordinal() == values().length - 1)
            return values()[0];
        return values()[ordinal() + 1];
    }


    /**
     * ritorna il giorno 1-> LUN, 2 MAR ecc
     *
     * @param n
     * @return
     */
    public static EGiorno getByNumber_LUN_DOM(int n) {
        return values()[n - 1];
    }


    public EGiorno nextGiornoLezione() {
        switch (this) {
            case LUNEDI:
                return MARTEDI;
            case MARTEDI:
                return MERCOLEDI;
            case MERCOLEDI:
                return GIOVEDI;
            case GIOVEDI:
                return VENERDI;
            case VENERDI:
                return LUNEDI;
            default:
                return LUNEDI;
        }
    }

    public EGiorno prev() {
        if (ordinal() == 0)
            return values()[values().length - 1];
        return values()[ordinal() - 1];
    }
}
