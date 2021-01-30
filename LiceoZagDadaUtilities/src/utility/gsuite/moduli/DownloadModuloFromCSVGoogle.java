package utility.gsuite.moduli;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public abstract class DownloadModuloFromCSVGoogle {

    static String extractDownloadLink(String link) {
        String[] split = link.split("\\?");
        String params = split[1];
        //cerca il parametro che inizia per id=
        String[] split1 = params.split("&");
        for (String s : split1) {
            if (s.startsWith("id=")) {
                String id = s.substring(3);
                return "https://drive.google.com/uc?id=" + id + "&export=download";
            }
        }
        return null;
    }


    protected abstract String getName(String[] row);

    protected abstract String getLinkFileName(String[] row, List<String> links, int linkIndex);

    protected abstract String getLinkFolderName(String[] row, List<String> links, int linkIndex);

    protected abstract String getEmail(String[] row);

    protected abstract String getSurname(String[] row);

    protected abstract List<String> getLinks(String[] row);

    private int idFile = 1;

    public int getIdFile() {
        return idFile;
    }


    protected abstract int skipLinkes();

    public static boolean saveFile(URL fileURL, File fileSavePath) {

        boolean isSucceed = true;

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(fileURL.toString());
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
        httpGet.addHeader("Referer", "https://www.google.com");

        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity fileEntity = httpResponse.getEntity();

            if (fileEntity != null) {
                FileUtils.copyInputStreamToFile(fileEntity.getContent(), (fileSavePath));
            }

        } catch (IOException e) {
            isSucceed = false;
        }

        httpGet.releaseConnection();

        return isSucceed;
    }

    public void download(File csv, File download_root) throws Exception {
        CSVReader r = new CSVReaderBuilder(new FileReader(csv)).withSkipLines(skipLinkes()).build();
        List<String[]> strings = r.readAll();
        download_root.mkdirs();
        idFile = 1;
        for (String[] line : strings) {
            System.out.println(this.getClass().getSimpleName() + " ROW:" + Arrays.toString(line));
            String name = getName(line);
            String surname = getSurname(line);
            String email = getEmail(line);
            List<String> links = getLinks(line);

            for (int i = 0; i < links.size(); i++) {
                String link = links.get(i);
                //URL downloadLink = new URL(extractDownloadLink(link));
                //File out = new File(root2, "file_"+i+".pdf");
                String subfolderName = getLinkFolderName(line, links, i);
                String fileName = getLinkFileName(line, links, i);
                File folderName = new File((download_root), subfolderName);
                folderName.mkdirs();
                String spec = extractDownloadLink(link);
                File destination = new File(folderName, fileName);
                if (destination.exists())
                    System.out.println(" - FILE ALREADY EXISTS");
                else {

                    System.out.println(" - Download link:" + spec);
                    System.out.println(" - Folder name:" + subfolderName);
                    System.out.println(" - File Name:" + fileName);
                    System.out.println(" - Full File Path:" + destination.getAbsolutePath());
                    System.out.println(" - FILE DOWNLOAD");
                    try {
                        saveFile(new URL(link),new File(download_root,"tmp.txt"));

                        saveFile(new URL(spec), destination);
                        if (destination.length() < 2000) {
                            System.out.println(" - FILE DOWNLOAD NOT ALLOWED");
                            System.out.flush();
                            System.err.println(" - FILE DOWNLOAD NOT ALLOWED");
                            System.err.flush();
                            destination.delete();
                            //interrompe
                            return;
                        }
                        //FileUtils.copyURLToFile(new URL(spec), destination);
                    } catch (IOException e) {
                        System.out.println(" - FILE DOWNLOAD ERROR - SKIP");
                        destination.delete();
                        e.printStackTrace();
                    }
                }
                idFile++;
            }
        }
    }


}
