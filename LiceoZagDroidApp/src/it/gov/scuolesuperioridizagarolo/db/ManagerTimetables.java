package it.gov.scuolesuperioridizagarolo.db;

import android.database.Cursor;
import dada.bitorario.data.BitOrarioGrigliaOrario;
import dada.bitorario.data.BitOrarioOraLezioneJSonConverter;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.TimetableDB;
import it.gov.scuolesuperioridizagarolo.dao.TimetableDBDao;
import it.gov.scuolesuperioridizagarolo.util.StreamAndroidUtil;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by stefano on 28/12/2017.
 */
public class ManagerTimetables {
    private final DaoSession session;

    public ManagerTimetables(DaoSession session) {
        this.session = session;
    }

    /**
     * restituisce tutte le url dei timetables memorizzati in locale
     *
     * @return
     */
    public Set<String> getAllUrls() {
        String query = "SELECT " + TimetableDBDao.Properties.Filename + " from " + TimetableDBDao.TABLENAME;
        final Database db = session.getDatabase();
        final Cursor c = db.rawQuery(query, new String[0]);
        TreeSet<String> url = new TreeSet<>();
        if (c.moveToFirst()) {
            do {
                url.add(c.getString(0));
            } while (c.moveToNext());
        }
        return url;
    }

    public BitOrarioGrigliaOrario getActiveTimetable() throws IOException {
        final Date now = nowDate();

        final QueryBuilder<TimetableDB> q = session.getTimetableDBDao().queryBuilder();
        q.orderDesc(TimetableDBDao.Properties.Timestamp);
        q.where(TimetableDBDao.Properties.Start_date.ge(now));
        q.where(TimetableDBDao.Properties.End_date.le(now));
        q.limit(1);
        final TimetableDB list = q.unique();
        if (list == null) {
            return new BitOrarioGrigliaOrario("orario vuoto");
        } else {
            ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(list.getFiledata()));
            final ZipEntry nextEntry = in.getNextEntry();
            final String s = StreamAndroidUtil.loadFileContentString(in);
            in.close();
            return BitOrarioOraLezioneJSonConverter.fromJSon(s);
        }
    }

    private java.util.Date nowDate() {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
}
