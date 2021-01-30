package utility.scrutini.engine.main;

import java.io.File;
import java.io.FileFilter;

public class OrdanizzaInCartelle {
    public static void main(String[] args) {
        File[] files = AnalisiAlunniScrutini.root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && !pathname.isHidden() && pathname.getName().toLowerCase().endsWith(".pdf");
            }
        });
        for (File file : files) {
            String[] split = file.getName().split("-");
            String classe = split[0].trim().toUpperCase();
            String nome = split[1].trim().toUpperCase();
            System.out.println(classe + "\t" + nome);
            File nuova_cartella = new File(AnalisiAlunniScrutini.root, classe);
            nuova_cartella.mkdir();
            boolean b = file.renameTo(new File(nuova_cartella, file.getName()));
            if(!b){
                System.out.println("Spostamento non consentito per "+file.getName());
            }
        }
    }
}
