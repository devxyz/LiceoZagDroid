package utility.gsuite.moduli;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static utility.gsuite.moduli.DownloadModuloFromCSVGoogle.extractDownloadLink;

public class TestGoogleDrive {


    public static void main(String[] args) throws IOException {


        String linktest = "https://drive.google.com/u/0/open?usp=forms_web&id=1kA4mpcVxJmYs0rZkNEzR2UVKuhoW9TMy";

        //String link = "https://drive.google.com/uc?id=1kA4mpcVxJmYs0rZkNEzR2UVKuhoW9TMy&export=download";
        String fileId = "1kA4mpcVxJmYs0rZkNEzR2UVKuhoW9TMy";
        String link = extractDownloadLink(linktest);
        System.out.println(linktest);
        System.out.println(link);
        FileUtils.copyURLToFile(new URL(link), new File("/Users/stefano/Downloads/xxx.pdf"));
/*        Drive.Apps.Get.
        driveService.files().get(fileId)
                .executeMediaAndDownloadTo(outputStream);*/
    }
}
