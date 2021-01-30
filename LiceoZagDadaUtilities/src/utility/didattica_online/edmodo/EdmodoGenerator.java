package utility.didattica_online.edmodo;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.*;

public class EdmodoGenerator {
    static String classi =
            "VB-IPIA Storia  AS 2019/20\n" +
                    "VB-IPIA Scienze motorie e sportive AS 2019/20\n" +
                    "VB-IPIA Religione AS 2019/20\n" +
                    "VB-IPIA Matematica ed Informatica AS 2019/20\n" +
                    "VB-IPIA Lingua inglese AS 2019/20\n" +
                    "VB-IPIA Laboratori tecnologici ed esercitazioni AS 2019/20\n" +
                    "VA-IPIA Storia  AS 2019/20\n" +
                    "VA-IPIA Scienze motorie e sportive AS 2019/20\n" +
                    "VA-IPIA Religione AS 2019/20\n" +
                    "VA-IPIA Matematica ed Informatica AS 2019/20\n" +
                    "VA-IPIA Lingua inglese AS 2019/20\n" +
                    "VA-IPIA Laboratori tecnologici ed esercitazioni AS 2019/20\n" +
                    "IVA-IPIA Storia  AS 2019/20\n" +
                    "IVA-IPIA Scienze motorie e sportive AS 2019/20\n" +
                    "IVA-IPIA Religione AS 2019/20\n" +
                    "IVA-IPIA Matematica ed Informatica AS 2019/20\n" +
                    "IVA-IPIA Lingua inglese AS 2019/20\n" +
                    "IVA-IPIA Laboratori tecnologici ed esercitazioni AS 2019/20\n" +
                    "IIIA-IPIA Storia  AS 2019/20\n" +
                    "IIIA-IPIA Scienze motorie e sportive AS 2019/20\n" +
                    "IIIA-IPIA Religione AS 2019/20\n" +
                    "IIIA-IPIA Matematica ed Informatica AS 2019/20\n" +
                    "IIIA-IPIA Lingua inglese AS 2019/20\n" +
                    "IIIA-IPIA Laboratori tecnologici ed esercitazioni AS 2019/20\n" +
                    "IIA-IPIA Storia  AS 2019/20\n" +
                    "IIA-IPIA Scienze motorie e sportive AS 2019/20\n" +
                    "IIA-IPIA Religione AS 2019/20\n" +
                    "IIA-IPIA Matematica ed Informatica AS 2019/20\n" +
                    "IIA-IPIA Lingua inglese AS 2019/20\n" +
                    "IIA-IPIA Laboratori tecnologici ed esercitazioni AS 2019/20\n" +
                    "IB-IPIA Tecnologie e Tecniche di Rappresentazioen Grafica AS 2019/20\n" +
                    "IB-IPIA Tecnologie dell’Informazione  della Comunicazione AS 2019/20\n" +
                    "IB-IPIA Storia  AS 2019/20\n" +
                    "IB-IPIA Scienze motorie e sportive AS 2019/20\n" +
                    "IB-IPIA Religione AS 2019/20\n" +
                    "IB-IPIA Matematica ed Informatica AS 2019/20\n" +
                    "IB-IPIA Lingua inglese AS 2019/20\n" +
                    "IB-IPIA Laboratori tecnologici ed esercitazioni AS 2019/20\n" +
                    "IB-IPIA Fisica AS 2019/20\n" +
                    "IB-IPIA Diritto ed Economia AS 2019/20\n" +
                    "IA-IPIA Tecnologie e Tecniche di Rappresentazioen Grafica AS 2019/20\n" +
                    "IA-IPIA Tecnologie dell’Informazione  della Comunicazione AS 2019/20\n" +
                    "IA-IPIA Storia  AS 2019/20\n" +
                    "IA-IPIA Scienze motorie e sportive AS 2019/20\n" +
                    "IA-IPIA Scienze della Terra,Chimica e Biologia AS 2019/20\n" +
                    "IA-IPIA Religione AS 2019/20\n" +
                    "IA-IPIA Matematica ed Informatica AS 2019/20\n" +
                    "IA-IPIA Lingua inglese AS 2019/20\n" +
                    "IA-IPIA Laboratori tecnologici ed esercitazioni AS 2019/20\n" +
                    "IA-IPIA Geografia AS 2019/20\n" +
                    "IA-IPIA Diritto ed Economia AS 2019/20";

    static String generateID(int id) {
        //413fe138-bd0a-4964-ad28-e56a21ba7a4e
        Formatter f = new Formatter();
        return f.format("%08d-%04d-%04d-%012d", id, id, id, id).toString();

    }

    private static int global_id = 1;

    static String generateCommand(String classe, String descrizione) throws IOException {
        String command = new String(Files.readAllBytes(new File(root, "edmodo.side.command.json").toPath()));
        command = command.replace("#nome-test#", classe);
        command = command.replace("#nome-classe#", classe);
        command = command.replace("#descrizione-classe#", descrizione);
        String[] linee = command.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String s : linee) {
            if (s.trim().startsWith("\"id\":")) {
                sb.append("\"id\":\"").append(generateID(global_id)).append("\",\n");
                global_id++;
            } else
                sb.append(s).append("\n");
        }
        return sb.toString();
    }

   public static File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2017-18/FalconeBorsellino-Zagarolo-17-18/Development/LiceoZagDroid/LiceoZagDadaUtilities/src/it/gov/scuolesuperioridizagarolo/dada/bitorario/main/sostituzioni/as2019_20/didattica_online");

    public static void main(String[] args) throws IOException {
        Set<String> cl = new TreeSet<>(Arrays.asList(classi.split("\n")));
        String content = new String(Files.readAllBytes(new File(root, "edmodo.side.headerfooter.json").toPath()));


        //#####
        int file = 1;
        int conta = 0;
        StringBuilder commands = new StringBuilder();
        for (String classe : cl) {

            String s = generateCommand(classe, classe);
            if (commands.length() > 0) {
                commands.append(",\n");
            }
            commands.append(s);
            conta++;
            if (conta > 50) {
                PrintStream out = new PrintStream(new File(root, file + "_out_ipia.side"));
                out.println(content.replace("#####", commands));
                out.close();
                commands.setLength(0);
                file++;
                conta = 0;
            }
        }

        if (conta > 0) {
            PrintStream out = new PrintStream(new File(root, file + "_out.side"));
            out.println(content.replace("#####", commands));
            out.close();
            file++;
        }

    }
}
