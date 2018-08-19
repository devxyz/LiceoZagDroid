package it.gov.scuolesuperioridizagarolo.model;

import it.gov.scuolesuperioridizagarolo.model.bitorario.enum_values.EGiorno;
import it.gov.scuolesuperioridizagarolo.util.C_DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by stefano on 31/12/2017.
 */
public class OnlyDate extends Date {
    public OnlyDate(Date d) {
        setTime(d.getTime());
    }

    public OnlyDate() {
        setTime(this.getTime());
    }

    public OnlyDate(int g, int m, int a) {
        setTime(C_DateUtil.newDate(g, m, a).getTime());
    }

    public OnlyDate(long milliseconds) {
        this(new Date(milliseconds));
    }

    public static void main(String[] args) {
        OnlyDate d = new OnlyDate();
        d = d.nextDay();
        System.out.println(d);
        System.out.println(d.toStringFull());

    }

    public int getGG(){
        final Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.DAY_OF_MONTH);
    }

    public int getMM(){
        final Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.MONTH);
    }

    public int getAAAA(){
        final Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.YEAR);
    }

    @Override
    public void setTime(long milliseconds) {
        super.setTime(C_DateUtil.deleteTimeFromDate(new Date(milliseconds)).getTime());

    }

    public OnlyDate nextDay() {
        return new OnlyDate(C_DateUtil.aggiungiGiorni(this, 1));
    }

    public OnlyDate prevDay() {
        return new OnlyDate(C_DateUtil.sottraiGiorni(this, 1));
    }

    public String toString() {
        return C_DateUtil.toDDMMYYY(this);

    }

    public String toDDMMYYY() {
        return C_DateUtil.toDDMMYYY(this);

    }

    public String toDDMMYY() {
        return C_DateUtil.toDDMMY(this);

    }

    public String toDDMM() {
        return C_DateUtil.toDDMM(this);

    }

    public String toStringFull() {
        return super.toString();

    }

    public boolean isToday() {
        return this.equals(new OnlyDate());
    }

    public EGiorno getGiorno() {
        return EGiorno.valueOf(this);
    }

}
