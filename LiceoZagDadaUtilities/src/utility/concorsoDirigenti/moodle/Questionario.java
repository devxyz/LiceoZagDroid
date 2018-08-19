package utility.concorsoDirigenti.moodle;

import java.util.ArrayList;

/**
 * Created by stefano on 26/06/2018.
 */
public class Questionario {
    public final ArrayList<Domanda> domande = new ArrayList<>();

    public Questionario(Domanda... dd) {
        for (Domanda domanda : dd) {
            domande.add(domanda);
        }
    }

    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<quiz>");
        for (Domanda d : domande) {
            sb.append(d.toXml()).append("\n");

        }


        sb.append("</quiz>");
        return sb.toString();
    }
}
