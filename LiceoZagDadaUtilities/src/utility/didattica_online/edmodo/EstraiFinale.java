package utility.didattica_online.edmodo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EstraiFinale {
    public static void main(String[] args) throws IOException {
        String content = new String(Files.readAllBytes(new File(EdmodoGenerator.root, "out.groups.json").toPath()));
        String[] split = content.split("\n");
        int conta = 1;
        for (String line : split) {
            System.out.println(line);
            String str = "\"join_url\":";
            int i = line.indexOf(str);
            int i2 = line.indexOf("\"" + str.length(), i);
            String linkURL = line.substring(i + str.length(), i2);

            str = "\"title\":";
            i = line.indexOf(str);
            i2 = line.indexOf("\"" + str.length(), i);
            String title = line.substring(i + str.length(), i2);

            str = "\"code\":";
            i = line.indexOf(str);
            i2 = line.indexOf("\"" + str.length(), i);
            String code = line.substring(i + str.length(), i2);

            System.out.println(conta + "\t" + title + "\t" + linkURL + "\t" + code);
            conta++;
        }
    }
}
