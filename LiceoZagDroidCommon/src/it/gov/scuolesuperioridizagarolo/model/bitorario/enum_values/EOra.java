package it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values;

import java.util.Calendar;

/**
 * Created by stefano on 16/09/2017.
 */
public enum EOra {
    ENTRATA(0, 8, 0, 0, 0),
    PRIMA(1, 8, 0, 15, 5),
    SECONDA(2, 9, 0, 10, 10),
    TERZA(3, 10, 0, 5, 5),
    QUARTA(4, 10, 50, 5, 5),
    QUINTA(5, 11, 40, 5, 5),
    SESTA(6, 12, 30, 5, 5),
    SETTIMA(7, 13, 20, 5, 5),
    OTTAVA(8, 14, 10, 5, 0),
    USCITA(9, 15, 0, 0, 0);

    private final int progressivOra;
    private final int oraInizio;
    private final int minutiInizio;


    //minuti di ritardo inizio della DDI rispetto all'orario standard
    private final int minutiRitardoInizioDDI;

    //minuti di anticipo della fine della DDI rispetto all'orario standard
    private final int minutiAnticipoFineDDI;


    EOra(int progressivOra, int oraInizio, int minutiInizio, int minutiRitardoInizioDDI, int minutiAnticipoFineDDI) {
        this.progressivOra = progressivOra;
        this.oraInizio = oraInizio;
        this.minutiInizio = minutiInizio;
        this.minutiRitardoInizioDDI = minutiRitardoInizioDDI;
        this.minutiAnticipoFineDDI = minutiAnticipoFineDDI;
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

    /**
     * ritorna l'ora dal numero. 1--> 1° ora
     *
     * @param n
     * @return
     */
    public static EOra getByNumber(int n) {
        return values()[n];
    }


    public static int oreDiLezione() {
        int c = 0;
        for (EOra x : values()) {
            if (x.flagOraDiLezione())
                c++;
        }
        return c;
    }

    public String fasciaPresenza() {
        final EOra next = next();
        if (next == null) {
            return printOraInizioPresenza() + " - 23.59";
        }

        return printOraInizioPresenza() + " - " + printOraFinePresenza();
    }

    public String fasciaDDI() {
        final EOra next = next();
        if (next == null) {
            return "DDI " + printOraInizioDDI() + " - 23.59";
        }

        return "DDI " + printOraInizioDDI() + " - " + printOraFineDDI();
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
        if (ordinal >= values.length - 1)
            return null;
        return values[ordinal + 1];
    }

    public EOra prev() {
        final int ordinal = ordinal();
        final EOra[] values = values();
        if (ordinal == 0)
            return null;
        return values[ordinal - 1];
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

    public String printOraInizioPresenza() {
        return printOra(oraInizio, minutiInizio);
    }

    public String printOraFinePresenza() {
        return next().printOraInizioPresenza();
    }

    private String printOra(int ora, int min) {
        while (min < 0) {
            min = min + 60;
            ora = ora - 1;
        }
        while (min >= 60) {
            min = min - 60;
            ora++;
        }
        return String.format("%02d:%02d", ora, min);
    }

    public String printOraInizioDDI() {
        return printOra(oraInizio, minutiInizio + minutiRitardoInizioDDI);
    }

    public String printOraFineDDI() {
        EOra next = next();
        return printOra(next.oraInizio, next.minutiInizio - minutiAnticipoFineDDI);
    }
}
