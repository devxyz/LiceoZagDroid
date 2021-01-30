package utility.didattica_online.edmodo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CodiciCorsi {
    //https://api.edmodo.com/groups/31289056.pdf?pdf_format=intl
    public static void main(String[] args) throws IOException {



        String content = new String(Files.readAllBytes(new File(EdmodoGenerator.root, "classi.html").toPath()));
        int i = 0;
        int i2;


        System.out.println("ArrayList<String> s=new ArrayList<>();");
        do {
            i2 = content.indexOf("containerclass", i);
            if (i2 > 0) {
                int i3 = content.indexOf("\"", i2);
                String sub = content.substring(i2 + "containerclass".length(), i3);
                i = i3;
                System.out.println("s.add(\""+sub+"\");");
                //System.out.println("https://api.edmodo.com/groups/" + sub + ".pdf?pdf_format=intl");

            }
        }
        while (i2 > 0);




    }
}
