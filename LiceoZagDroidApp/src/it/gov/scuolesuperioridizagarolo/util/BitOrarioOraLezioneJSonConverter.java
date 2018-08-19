package it.gov.scuolesuperioridizagarolo.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioOraLezione;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class BitOrarioOraLezioneJSonConverter {
    private ArrayList<BitOrarioOraLezione> lezioni;
    private String titolo;


    public BitOrarioOraLezioneJSonConverter(BitOrarioGrigliaOrario t) {
        this(t.getTitolo(), t.getLezioni());
    }

    public BitOrarioOraLezioneJSonConverter(String titolo, List<BitOrarioOraLezione> l) {
        this.titolo = titolo;
        lezioni = new ArrayList<>(l);
    }

    public static BitOrarioGrigliaOrario fromJSon(String s) {
        final BitOrarioOraLezioneJSonConverter o = new GsonBuilder().create().fromJson(s, BitOrarioOraLezioneJSonConverter.class);
        return o.createNew();
    }

    public static BitOrarioGrigliaOrario fromJSon(Reader s) {
        final BitOrarioOraLezioneJSonConverter o = new GsonBuilder().create().fromJson(s, BitOrarioOraLezioneJSonConverter.class);
        return o.createNew();
    }

    public static BitOrarioGrigliaOrario fromJSon(InputStream s) {
        InputStreamReader in = new InputStreamReader(s);
        final BitOrarioOraLezioneJSonConverter o = new GsonBuilder().create().fromJson(in, BitOrarioOraLezioneJSonConverter.class);
        return o.createNew();
    }

    public static String toJSon(BitOrarioGrigliaOrario o) {
        BitOrarioOraLezioneJSonConverter x = new BitOrarioOraLezioneJSonConverter(o);
        return x.toJSon();
    }

    public ArrayList<BitOrarioOraLezione> getLezioni() {
        return lezioni;
    }

    public BitOrarioGrigliaOrario createNew() {
        BitOrarioGrigliaOrario o = new BitOrarioGrigliaOrario(titolo);
        o.addLezione(lezioni);
        return o;
    }

    public String toJSon() {
        final Gson g = new GsonBuilder().setPrettyPrinting().create();
        return g.toJson(this);
    }


}
