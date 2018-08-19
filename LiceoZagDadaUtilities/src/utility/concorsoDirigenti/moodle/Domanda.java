package utility.concorsoDirigenti.moodle;

import java.util.ArrayList;

/**
 * Created by stefano on 26/06/2018.
 */
public class Domanda {
    public final String titolo;
    public final String testo;
    public final ArrayList<Risposta> risposte = new ArrayList<>();
    private final String rif;
    private final String categoria;
    private final Risposta[] rr;

    public Domanda(String rif, String categoria, String titolo, String testo, Risposta... rr) {
        this.rif = rif;
        this.categoria = categoria;
        this.titolo = titolo;
        this.testo = testo;
        this.rr = rr;
        for (Risposta risposta : rr) {
            risposte.add(risposta);
        }
    }

    public Domanda nuovaDomandaPerCategoria(String newcat) {
        Domanda d = new Domanda(rif, newcat, titolo, testo);
        d.risposte.addAll(risposte);
        return d;
    }

    public String toXml() {
        StringBuilder sb = new StringBuilder();

        sb.append("  <question type=\"category\">\n" +
                "    <category>\n" +
                "        <text>" + categoria + "</text>\n" +
                "\n" +
                "    </category>\n" +
                "  </question>\n");

        sb.append("<question type=\"multichoice\">\n" +
                "    <name>\n" +
                "      <text>" + rif + " - " + titolo + "</text>\n" +
                "    </name>\n" +
                "    <questiontext format=\"html\">\n" +
                "      <text><![CDATA[" + testo + "]]></text>\n" +
                "    </questiontext>\n" +
                "    <generalfeedback format=\"html\">\n" +
                "      <text></text>\n" +
                "    </generalfeedback>\n" +
                "    <defaultgrade>2.0000000</defaultgrade>\n" +
                "    <penalty>0.3333333</penalty>\n" +
                "    <hidden>0</hidden>\n" +
                "    <single>true</single>\n" +
                "    <shuffleanswers>true</shuffleanswers>\n" +
                "    <answernumbering>abc</answernumbering>\n" +
                "    <correctfeedback format=\"html\">\n" +
                "      <text><![CDATA[<p>Risposta corretta.</p>]]></text>\n" +
                "    </correctfeedback>\n" +
                "    <partiallycorrectfeedback format=\"html\">\n" +
                "      <text><![CDATA[<p>Risposta parzialmente corretta.</p>]]></text>\n" +
                "    </partiallycorrectfeedback>\n" +
                "    <incorrectfeedback format=\"html\">\n" +
                "      <text><![CDATA[<p>Risposta errata.</p>]]></text>\n" +
                "    </incorrectfeedback>\n" +
                "    <shownumcorrect/>\n");


        for (Risposta risposta : risposte) {
            sb.append(risposta.toXml()).append("\n");
        }

        sb.append("  </question>");

        return sb.toString();
    }
}
