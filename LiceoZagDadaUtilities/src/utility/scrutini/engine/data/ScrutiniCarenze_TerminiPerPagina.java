package utility.scrutini.engine.data;

import java.util.ArrayList;

public class ScrutiniCarenze_TerminiPerPagina {
    public final int numeroPagina;
    public final ArrayList<ArrayList<ScrutiniCarenze_Termine>> terminiPerRiga;

    public ScrutiniCarenze_TerminiPerPagina(int numeroPagina, ArrayList<ArrayList<ScrutiniCarenze_Termine>> terminiPerRiga) {
        this.numeroPagina = numeroPagina;
        this.terminiPerRiga = terminiPerRiga;
    }
}
