package utility.corso_sicurezza;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class ImportFromJSONSicurezza {
    private static final Type REVIEW_TYPE = new TypeToken<List<StudenteCorsoSicurezza>>() {
    }.getType();

    public static String getFileContent(File f) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(f));
        StringBuilder sb = new StringBuilder();

        char[] buf = new char[1024];
        int amt = r.read(buf);
        while (amt > 0) {
            sb.append(buf, 0, amt);
            amt = r.read(buf);
        }
        return sb.toString();
    }




    public static StudenteCorsoSicurezzaCollection parse(File f) throws IOException {
        Gson g = new Gson();

        String s = getFileContent(f);
        List<StudenteCorsoSicurezza> arrayList = g.fromJson(s, REVIEW_TYPE);
        StudenteCorsoSicurezzaCollection ris = new StudenteCorsoSicurezzaCollection();
        ris.add(arrayList);
        return ris;
    }
}
