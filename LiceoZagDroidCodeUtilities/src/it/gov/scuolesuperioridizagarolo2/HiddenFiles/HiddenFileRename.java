package it.gov.scuolesuperioridizagarolo2.HiddenFiles;

import java.io.File;

public class HiddenFileRename {
    public static void main(String[] args) {
        String path = "/Volumes/Transcend";

        File dir = new File(path);
        int i = 0;
        for (File file : dir.listFiles()) {
            i++;
            if (file.isDirectory() && file.getName().length() < 3) {
                System.out.println("<" + file.getName() + ">");
                file.renameTo(new File(path, "cartella_" + i));
            }
        }
    }
}
