package utility.didattica_online.presentazione_1B;

import java.io.File;

public class Folders {

    public static void main(String[] args) {
        x1();
    }

    public static void x2() {
        File root = new File("/Users/stefano/Google Drive/lezioni-a-distanza201920/LEZIONI/1B/XX2_compito_presentazione/esercizio1");
        for (File file : root.listFiles()) {
            if (file.isDirectory()) {
                file.renameTo(new File(file.getParentFile(), file.getName().replace("_ES1", "")));
            }
        }
    }

    public static void x1() {
        File root = new File("/Users/stefano/Google Drive/lezioni-a-distanza201920/LEZIONI/1B/XX2_compito_presentazione/esercizio1");
        for (File file : root.listFiles()) {
            if (file.isFile()) {
                File f2 = new File(root, file.getName()
                        .replace("COMPITO-1_", "")
                        .replace("COMPITO-2_", "")
                        .replace("COMPITO-3_", "")
                        .split("[.]")[0]);
                f2.mkdirs();
                file.renameTo(new File(f2, file.getName()));
            }
        }
    }
}
