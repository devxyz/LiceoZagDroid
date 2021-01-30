package it.gov.scuolesuperioridizagarolo2.backoffice.utilities.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.Vector;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by stefano on 06/04/15.
 */
public class MainTest {
    public static void main(String[] args) throws IOException {
        File f = new File("/Users/stefano/Dropbox/Circolari_Scolastiche_LICEO/AS 2019.20/circolari scolastiche 2019.20/INVALSI/FILES/");
        Stream<Path> pathStream = Files.find(f.toPath(), 100, new BiPredicate<Path, BasicFileAttributes>() {
                    @Override
                    public boolean test(Path path, BasicFileAttributes basicFileAttributes) {


                        String name = path.getFileName().toFile().getAbsolutePath();
                        if (name.contains("cpdf-binaries-master")) return false;
                        boolean b = basicFileAttributes.isRegularFile() && name.endsWith(".pdf");

                        return b;
                    }
                },
                FileVisitOption.FOLLOW_LINKS);

        System.out.print("./cpdf ");

        Stream<Path> sorted = pathStream.sorted(new Comparator<Path>() {
            @Override
            public int compare(Path o1, Path o2) {
                return o1.toFile().getAbsolutePath().compareTo(o2.toFile().getAbsolutePath());
            }
        });


        sorted.forEach(new Consumer<Path>() {
            Path lastPath = null;

            @Override
            public void accept(Path path) {
                if (lastPath == null || !path.getParent().equals(lastPath)) {
                    lastPath = path.getParent();
                    System.out.print("../../../INVALSI/bianco.pdf ");
                }
                String s = path.toFile().getAbsolutePath().replaceAll("/Users/stefano/Dropbox/Circolari_Scolastiche_LICEO/AS 2019.20/circolari scolastiche 2019.20", "");
                System.out.print("../../..");
                System.out.print(s);
                System.out.print(" ");

            }
        });
        System.out.println("-o x.pdf");

    }
}
