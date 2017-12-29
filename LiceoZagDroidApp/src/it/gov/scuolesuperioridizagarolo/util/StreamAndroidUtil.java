package it.gov.scuolesuperioridizagarolo.util;

import java.io.*;

/**
 * Created by stefano on 11/03/15.
 */
public class StreamAndroidUtil {
    public static String loadFileContentString(Reader r) throws IOException {
        StringBuilder sb = new StringBuilder();

        char[] buf = new char[1024];
        int amt = r.read(buf);
        while (amt > 0) {
            sb.append(buf, 0, amt);
            amt = r.read(buf);
        }
        return sb.toString();
    }

    public static byte[] loadFileContentByteArray(InputStream r) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(1000);

        byte[] buf = new byte[1024];
        int amt = r.read(buf);
        while (amt > 0) {
            out.write(buf, 0, amt);
            amt = r.read(buf);
        }
        return out.toByteArray();
    }

    public static String loadFileContentString(InputStream r) throws IOException {
        return loadFileContentString(new InputStreamReader(r));
    }
}
