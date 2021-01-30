package utility.gsuite.moduli;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Deprecated
public class DownloadUtilities {
    private static final int BUFFER_SIZE = 4096 * 4096;

    /**
     * Downloads a file from a URL
     *
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws IOException
     */
    static final boolean PRINT = false;

    public static void downloadFile(String fileURL, File saveDir, String externalFilename, int progressivo)
            throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setInstanceFollowRedirects(true);
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            //System.out.println(httpConn.getHeaderFields());
            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();


            if (disposition != null) {
                fileName = disposition.replaceFirst("(?i)^.*filename=\"?([^\"]+)\"?.*$", "$1");

                /*
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
                else{
                    fileName="NONAME.pdf";
                }*/

            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
            }

            if (externalFilename != null) {
                fileName = externalFilename;
            }


            fileName = fileName.replaceAll("[/?&]", "").replaceAll("[ ]+", "");
            int i = fileName.lastIndexOf(".");
            if (i >= 0) {
                fileName = fileName.substring(0, i) + "(" + progressivo + ")" + fileName.substring(i);
            } else {
                fileName += "(" + progressivo + ")";
            }

            if (PRINT) {
                System.out.println("Content-Type = " + contentType);
                System.out.println("Content-Disposition = " + disposition);
                System.out.println("Content-Length = " + contentLength);
                System.out.println("fileName = " + fileName);
            }

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            if (PRINT)
                System.out.println("File downloaded");
        } else {
            throw new IllegalArgumentException("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }
}
