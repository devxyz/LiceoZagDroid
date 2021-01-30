package utility.cdc_maggio2020_gsuite;

import java.io.File;
import java.io.FileFilter;

public class TestRename {
    public static void main(String[] args) {
        File root = new File("/Users/stefano/Google Drive/lezioni-a-distanza201920/LEZIONI/2B/XX2_verifica_maggio/2B/2b-Verifica AppInventor - Maggio-65");
        File[] folders = root.listFiles();
        for (File t : folders) {
            if (t.isHidden()) continue;
            if (t.isFile()) {
                String name = t.getName().replace("2b.","").replace("2d.","").replace(".aia","").replace(".","_")+".aia";
                File f2=new File(t.getParent(),name);
                t.renameTo(f2);
                continue;
            }
            String name = t.getName().split(" ")[0];
            System.out.println("===========================");
            System.out.println(name);
            File[] files = t.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().toLowerCase().endsWith(".aia");
                }
            });
            for (File file : files) {
                if (file.isHidden()) continue;
                System.out.println(">>  " + file);
                File dest = new File(t.getParent(), name + "_" + file.getName().replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("[ ]+", ""));
                file.renameTo(dest);
                System.out.println("Rename:" + dest);
            }
            t.delete();
        }
    }
}
