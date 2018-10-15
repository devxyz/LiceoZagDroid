package it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values;

import java.util.Calendar;

/**
 * Created by stefano on 16/09/2017.
 */
public enum EOra {
    ENTRATA(0, 8, 0),
    PRIMA(1, 8, 0),
    SECONDA(2, 9, 0),
    TERZA(3, 10, 0),
    QUARTA(4, 11, 0),
    QUINTA(5, 12, 0),
    SESTA(6, 13, 0),
    USCITA(7, 14, 0);

    private final int progressivOra;
    private final int oraInizio;
    private final int minutiInizio;


    EOra(int progressivOra, int oraInizio, int minutiInizio) {
        this.progressivOra = progressivOra;
        this.oraInizio = oraInizio;
        this.minutiInizio = minutiInizio;
    }

    public static EOra[] valuesOreDiLezione() {
        EOra[] ris = new EOra[oreDiLezione()];
        int i = 0;
        for (EOra e : values()) {
            if (e.flagOraDiLezione())
                ris[i++] = e;
        }
        return ris;
    }

    public static int oreDiLezione() {
        int c = 0;
        for (EOra x : values()) {
            if (x.flagOraDiLezione())
                c++;
        }
        return c;
    }

    public String fascia() {
        final EOra next = next();
        if (next == null) {
            return printOra() + " - 23.59";
        }

        return printOra() + " - " + next.printOra();
    }

    //true se corrisponde all'ora attuale
    public boolean isNowHour() {
        //todo:DEBUG
        //if (ordinal()==3)return true;

        //ora corrente
        final Calendar c = Calendar.getInstance();
        final int ora = c.get(Calendar.HOUR_OF_DAY);
        final int min = c.get(Calendar.MINUTE);
        final int t = min + ora * 60;

        //tempo inizio
        final int tempoInizio, tempoFine;


        final EOra next = next();
        if (this == ENTRATA) {
            tempoFine = minutiInizio + oraInizio * 60;
            tempoInizio = 0;//dalle 00:00
        } else if (this == USCITA) {
            tempoInizio = minutiInizio + oraInizio * 60;
            tempoFine = 59 + 23 * 60;//fino alle 23.59

        } else {
            tempoInizio = minutiInizio + oraInizio * 60;
            tempoFine = next.minutiInizio + next.oraInizio * 60;
        }

        return tempoInizio <= t && t < tempoFine;

    }

    /**
     * indica se e' un'ora finta
     *
     * @return
     */
    public boolean flagOraDiLezione() {
        return this != USCITA && this != ENTRATA;
    }

    public EOra next() {
        final int ordinal = ordinal();
        final EOra[] values = values();
        if (ordinal >= values.length-1)
            return null;
        return values[ordinal + 1];
    }

    public int getProgressivOra() {
        return progressivOra;
    }

    public int getOraInizio() {
        return oraInizio;
    }

    public int getMinutiInizio() {
        return minutiInizio;
    }

    public String printOra() {
        return String.format("%02d:%02d", oraInizio, minutiInizio);
    }
}
