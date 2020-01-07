package utility.estrai_video_vimeo_corso_scratch;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EstraiVideoVimeoCorsoScratch {
    private static void extract(File folder, ArrayList<String> out) throws IOException {
        //System.out.println("ANALIZZA CARTELLA: " + folder);
        File[] files = folder.listFiles();
        Arrays.sort(files);
        for (File file : files) {
            if (file.getName().equals("."))
                continue;
            if (file.getName().equals(".."))
                continue;
            if (file.getName().equals(".DS_Store"))
                continue;
            if (file.isDirectory()) {
                extract(file, out);
            }

            //System.out.println("ANALIZZA FILE: " + file);
            if (file.getName().endsWith(".html")) {
                BufferedReader in = new BufferedReader(new FileReader(file));
                String line;
                int i = 0;
                String title = file.getName().split("–")[0].trim();
                System.out.println("<li>" + title + "<ul>");
                while ((line = in.readLine()) != null) {
                    // System.out.println(line);
                    if (line.contains("player.vimeo.com")) {
                        i++;
                        String l2 = line.substring(line.indexOf("src=") + 5);
                        String l3 = l2.substring(0, l2.indexOf("\""));

                        System.out.println("<li><b>" + l3 + "</b><br><a target='_blank' href='" + l3 + "'>" + "Video " + i + "</a></li>");
                        out.add(l3);

                    }
                }
                System.out.println("</ul></li>");

                in.close();

            }

        }
    }

    public static void main(String[] args) throws IOException {
        File root = new File("/Users/stefano/DATA/scuola/insegnamento/scuola-AS-2019-20/coding");
        ArrayList<String> out = new ArrayList<>();
        System.out.println("<html><body><ul>");
        extract(root, out);
        System.out.println("</ul></body></html>");

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for (String s : out) {
            System.out.println(s);
        }

    }
}
