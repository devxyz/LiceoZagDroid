package utility.scrutini;

import java.util.ArrayList;

public class LeggiScrutini_Materia {
    ArrayList<LeggiScrutini_Testo> parole;
    public LeggiScrutini_ColonnaVotoAss colonnaVoto;
    public LeggiScrutini_ColonnaVotoAss colonnaAssenza;

    public String testo() {
        String p = "";
        for (LeggiScrutini_Testo z : parole) {
            if (p.length() > 0)
                p += " " + z.testo.trim();
            else
                p = z.testo.trim();
        }
        return p;
    }

    public LeggiScrutini_Materia(LeggiScrutini_Testo r) {
        parole = new ArrayList<>();
        parole.add(r);
    }

    public double mediaX() {
        if (parole.size() == 0) return -1;
        double media = 0;
        for (LeggiScrutini_Testo textRenderInfo : parole) {
            media += textRenderInfo.mediaX;
        }
        return media / parole.size();
    }

    public double mediaY() {
        if (parole.size() == 0) return -1;
        double media = 0;
        for (LeggiScrutini_Testo textRenderInfo : parole) {
            media += textRenderInfo.mediaY;
        }
        return media / parole.size();
    }

    public String toString() {
        return testo()+"-Voto:"+colonnaVoto+"-Assenza:"+colonnaAssenza;
    }
}
