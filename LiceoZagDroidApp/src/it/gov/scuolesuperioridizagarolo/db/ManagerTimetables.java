package it.gov.scuolesuperioridizagarolo.db;

import android.database.Cursor;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.TimetableDB;
import it.gov.scuolesuperioridizagarolo.dao.TimetableDBDao;
import it.gov.scuolesuperioridizagarolo.model.BitOrarioGrigliaOrarioContainer;
import it.gov.scuolesuperioridizagarolo.model.bitorario.BitOrarioGrigliaOrario;
import it.gov.scuolesuperioridizagarolo.util.BitOrarioOraLezioneJSonConverter;
import it.gov.scuolesuperioridizagarolo.util.StreamAndroidUtil;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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

    public int removeTimetablesOlderThan(Date minDate) {
        final QueryBuilder<TimetableDB> q = session.getTimetableDBDao().queryBuilder();
        q.orderDesc(TimetableDBDao.Properties.EndDate);
        q.where(TimetableDBDao.Properties.EndDate.le(minDate));
        final List<TimetableDB> list = new ArrayList<>(q.list());
        int count = 0;
        for (TimetableDB t : list) {
            session.getTimetableDBDao().delete(t);
            count++;
        }
        return count;
    }

    public int removeTimetablesWithNameNotInCollection(ArrayList<String> validNames) {
        final QueryBuilder<TimetableDB> q = session.getTimetableDBDao().queryBuilder();
        final List<TimetableDB> list = new ArrayList<>(q.list());
        int count = 0;
        for (TimetableDB t : list) {
            if (!validNames.contains(t.getFilename())) {
                session.getTimetableDBDao().delete(t);
                count++;
            }
        }
        return count;
    }


    /**
     * restituisce tutte le url dei timetables memorizzati in locale
     *
     * @return
     */
    public Set<String> getAllUrls() {
        String query = "SELECT " + TimetableDBDao.Properties.Filename.columnName + " from " + TimetableDBDao.TABLENAME;
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

    public TimetableDB createNew(String fullUrl, String fileName, byte[] data) throws IOException {
        // *timetable_0_20.09.2017_20.06.2018.json

        final String[] split = fileName.split("_");
        String base = split[0];
        long id = Long.parseLong(split[1]);
        String fromDateString = split[2];
        String endDateString = split[3];

        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
        final Date fromDate;
        final Date endDate;
        try {
            fromDate = f.parse(fromDateString);
            endDate = f.parse(endDateString);
        } catch (ParseException e) {
            throw new IOException(e);
        }

        TimetableDB t = new TimetableDB(null, fullUrl, id, new Date(), fileName, fromDate, endDate, data);
        session.getTimetableDBDao().insert(t);
        return t;
    }

    public BitOrarioGrigliaOrarioContainer loadBitOrarioGrigliaOrarioContainer() throws IOException {
        final QueryBuilder<TimetableDB> q = session.getTimetableDBDao().queryBuilder();
        q.orderDesc(TimetableDBDao.Properties.RemoteId);
        BitOrarioGrigliaOrarioContainer c = new BitOrarioGrigliaOrarioContainer();
        for (TimetableDB t : q.list()) {

            ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(t.getData()));
            final ZipEntry nextEntry = in.getNextEntry();
            final String s = StreamAndroidUtil.loadFileContentString(in);
            in.close();
            final BitOrarioGrigliaOrario o = BitOrarioOraLezioneJSonConverter.fromJSon(s);
            c.add(t.getStartDate(), t.getEndDate(), t.getRemoteId(), o);
        }
        return c;
    }

    public BitOrarioGrigliaOrario getActiveTimetable() throws IOException {
        final Date now = nowDate();

        final QueryBuilder<TimetableDB> q = session.getTimetableDBDao().queryBuilder();
        q.orderDesc(TimetableDBDao.Properties.RemoteId);
        q.where(TimetableDBDao.Properties.StartDate.le(now));
        q.where(TimetableDBDao.Properties.EndDate.ge(now));
        q.limit(1);
        final TimetableDB list = q.unique();
        if (list == null) {
            return new BitOrarioGrigliaOrario("orario vuoto");
        } else {
            ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(list.getData()));
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
