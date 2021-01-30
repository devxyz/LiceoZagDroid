package utility.cdc_maggio2020_gsuite;

import java.io.File;
import java.util.LinkedList;

public class Test2RenameAndroid {
    public static void main(String[] args) {
        File root = new File("/Users/stefano/Google Drive/lezioni-a-distanza201920/LEZIONI/3B/XX_2_verifica maggio/3D");
        LinkedList<File> folders = new LinkedList<>();
        folders.add(root);
        while (!folders.isEmpty()) {
            File current = folders.removeFirst();
            for (File file : current.listFiles()) {
                if (file.isHidden()) continue;
                if (file.isDirectory()) {
                    folders.add(file);
                } else {
                    String s = file.getName().replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" ", "");
                    File f2 = new File(file.getParentFile(), s);
                    file.renameTo(f2);

                }
            }
        }
    }
}
