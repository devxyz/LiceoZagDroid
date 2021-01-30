package utility.quiz_moodle;

public class QuizMoodle_risposta_breve {
    final String titolo;
    final String testo;
    final String risposta_corretta;

    public QuizMoodle_risposta_breve(String titolo, String testo, String risposta_corretta) {
        this.titolo = titolo;
        this.testo = testo;
        this.risposta_corretta = risposta_corretta;
    }

    public String toXml(){
        String s=" <question type=\"shortanswer\">\n" +
                "    <name>\n" +
                "      <text>#TITOLO#</text>\n" +
                "    </name>\n" +
                "    <questiontext format=\"html\">\n" +
                "      <text><![CDATA[<p>#TESTO_DOMANDA#</p>]]></text>\n" +
                "    </questiontext>\n" +
                "    <generalfeedback format=\"html\">\n" +
                "      <text></text>\n" +
                "    </generalfeedback>\n" +
                "    <defaultgrade>1.0000000</defaultgrade>\n" +
                "    <penalty>0.3333333</penalty>\n" +
                "    <hidden>0</hidden>\n" +
                "    <idnumber></idnumber>\n" +
                "    <usecase>0</usecase>\n" +
                "    <answer fraction=\"100\" format=\"moodle_auto_format\">\n" +
                "      <text>#RISPOSTA#</text>\n" +
                "      <feedback format=\"html\">\n" +
                "        <text></text>\n" +
                "      </feedback>\n" +
                "    </answer>\n" +
                "    <answer fraction=\"33.33333\" format=\"moodle_auto_format\">\n" +
                "      <text>*#RISPOSTA#*</text>\n" +
                "      <feedback format=\"html\">\n" +
                "        <text></text>\n" +
                "      </feedback>\n" +
                "    </answer>\n" +
                "    <answer fraction=\"5\" format=\"moodle_auto_format\">\n" +
                "      <text>*</text>\n" +
                "      <feedback format=\"html\">\n" +
                "        <text></text>\n" +
                "      </feedback>\n" +
                "    </answer>\n" +
                "  </question>";
        return s
                .replace("#TITOLO#",titolo)
                .replace("#TESTO_DOMANDA#",testo)
                .replace("#RISPOSTA#",risposta_corretta);
    }

}
