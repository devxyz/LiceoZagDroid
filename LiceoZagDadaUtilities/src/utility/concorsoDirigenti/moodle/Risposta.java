package utility.concorsoDirigenti.moodle;

/**
 * Created by stefano on 26/06/2018.
 */
public class Risposta {
    public final String testo;
    public final int punteggio;

    public Risposta(String testo, int punteggio) {
        this.testo = testo;
        this.punteggio = punteggio;
    }

    public String toXml() {

        String s = "    <answer fraction=\"" + punteggio + "\" format=\"html\">\n" +
                "      <text><![CDATA[" + testo + "]]></text>\n" +
                "      <feedback format=\"html\">\n" +
                "        <text></text>\n" +
                "      </feedback>\n" +
                "    </answer>\n";
        return s;

    }
}
