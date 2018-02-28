package it.gov.scuolesuperioridizagarolo.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import it.gov.scuolesuperioridizagarolo.dao.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefano on 25/02/2018.
 */
public class DataBaseUtil {
    public static String exportHtml(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        //sb.append(exportHtml(db, ArticoloDBDao.TABLENAME));


        sb.append(exportHtml(db, CacheFileDBDao.TABLENAME));
        sb.append(exportHtml(db, TimetableDBDao.TABLENAME));
        sb.append(exportHtml(db, ArticoloDBDao.TABLENAME));

        sb.append(exportHtml(db, TagArticoloDBDao.TABLENAME));
        sb.append(exportHtml(db, AttachmentArticoloDBDao.TABLENAME));

        sb.append(exportHtml(db, CircolareDBDao.TABLENAME));

        sb.append(exportHtml(db, TermineDBDao.TABLENAME));
        sb.append(exportHtml(db, NewsDBDao.TABLENAME));
        sb.append(exportHtml(db, CircolareContieneTermineDBDao.TABLENAME));
        sb.append(exportHtml(db, NewsContieneTermineDBDao.TABLENAME));
        return "<html><body>" + sb + "</body></html>";
    }

    public static String exportHtml(SQLiteDatabase db, String tableName) {
        final ByteArrayOutputStream ris = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(ris);
        Cursor curCSV = db.rawQuery("select * from " + tableName, null);
        out.print("<h1>" + tableName + "</h1>");
        out.print("<table border='1'>");

        ArrayList<Object> data = new ArrayList<>();
        boolean first = true;
        while (curCSV.moveToNext()) {
            //intestazione
            if (first) {
                data.clear();

                for (int i = 0; i < curCSV.getColumnCount(); i++) {
                    data.add(curCSV.getColumnName(i));
                }

                out.print("<tr>");
                out.println(printHtmlRowTable(data));
                out.print("</tr>");
            }
            first = false;

            //valori
            data.clear();
            for (int i = 0; i < curCSV.getColumnCount(); i++) {
                switch (curCSV.getType(i)) {
                    case Cursor.FIELD_TYPE_FLOAT: {
                        double f = curCSV.getDouble(i);
                        data.add(f);
                        break;
                    }

                    case Cursor.FIELD_TYPE_NULL: {
                        data.add(null);
                        break;
                    }

                    case Cursor.FIELD_TYPE_BLOB: {
                        data.add("<BLOB...>");
                        break;
                    }

                    case Cursor.FIELD_TYPE_INTEGER: {
                        long f = curCSV.getLong(i);
                        data.add(f);

                        break;
                    }

                    case Cursor.FIELD_TYPE_STRING: {
                        String f = curCSV.getString(i);
                        data.add(f);

                        break;
                    }
                }
            }
            out.print("<tr>");
            out.println(printHtmlRowTable(data));
            out.print("</tr>");
        }

        out.print("</table>");
        out.flush();
        curCSV.close();
        return ris.toString();
    }

    private static String printHtmlRowTable(List<Object> ll) {
        StringBuilder sb = new StringBuilder();
        for (Object o : ll) {
            sb.append("<td>");
            sb.append(o);
            sb.append("</td>");
        }
        return sb.toString();
    }
}
