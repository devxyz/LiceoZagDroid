package it.gov.scuolesuperioridizagarolo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by stefano on 24/02/15.
 */
public class C_DateUtil {
    public static String toDDMMYYY_HHMMSS(Date d) {
        if (d == null) return "--/--/---";
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ITALIAN);

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(d);
    }

    public static String toHHMM(Date d) {
        if (d == null) return "--:--";
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("HH:mm", Locale.ITALIAN);

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(d);
    }

    public static String toDDMMYYY(Date d) {
        if (d == null) return "--/--/----";
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(d);
    }

    public static String toDDMMY(Date d) {
        if (d == null) return "--/--/--";
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("dd/MM/yy", Locale.ITALIAN);

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(d);
    }

    public static String toDDMM(Date d) {
        if (d == null) return "--/--";
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("dd/MM", Locale.ITALIAN);

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(d);
    }

    public static String toYYYYMMDD(Date d) {
        if (d == null) return "--/--/---";
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.ITALIAN);

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(d);
    }

    public static String toYYYYMMDDhhmmss(Date d) {
        if (d == null) return "--/--/---";
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ITALIAN);

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(d);
    }

    public static Date sottraiMinuti(Date d, int minutes) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MINUTE, -minutes);
        return c.getTime();
    }

    public static Date sottraiGiorni(Date d, int giorni) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DAY_OF_MONTH, -giorni);
        return c.getTime();
    }

    public static Date aggiungiGiorni(Date d, int giorni) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DAY_OF_MONTH, giorni);
        return c.getTime();
    }

    /**
     * differenza in secondi tra la data fine e la data inizio
     *
     * @param inizio
     * @param fine
     * @return
     */
    public static long differenzaInSecondi(Date inizio, Date fine) {
        return (fine.getTime() - inizio.getTime()) / 1000;
    }

    /**
     * differenza in secondi tra la data fine e la data inizio
     *
     * @param inizio
     * @param fine
     * @return
     */
    public static long differenzaInMinuti(Date inizio, Date fine) {
        return differenzaInSecondi(inizio, fine) / 60;
    }

    public static Date sottraiSecondi(Date d, int secondi) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.SECOND, -secondi);
        return c.getTime();
    }


    public static Date newDate(int g, int m, int a) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.DAY_OF_MONTH, g);
        c.set(Calendar.MONTH, m);
        c.set(Calendar.YEAR, a);
        return c.getTime();
    }

    public static Date deleteTimeFromDate(Date d) {
        final Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

}
