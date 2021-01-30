package utility.quiz_moodle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class RaccoltaQuizMoodle {
    public final ArrayList<QuizMoodle_risposta_breve> quizzes = new ArrayList<>();

    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<quiz>\n");
        for (QuizMoodle_risposta_breve q : quizzes) {
            sb.append(q.toXml());
            sb.append("\n");
        }
        sb.append("</quiz>");
        return sb.toString();
    }

    public void toFile(File f) throws FileNotFoundException {
        PrintStream out = new PrintStream(f);
        out.println(toXml());
        out.close();
    }
}
