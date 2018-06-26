package it.gov.scuolesuperioridizagarolo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;
import org.greenrobot.greendao.database.Database;

/**
 * Created by stefano on 24/06/2018.
 */
public class ScuolaAppOpenHelper extends DaoMaster.OpenHelper {
    private final Context context;

    public ScuolaAppOpenHelper(Context context, String name) {
        super(context, name);
        this.context = context;
    }

    public ScuolaAppOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
        this.context = context;
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
        DaoMaster.dropAllTables(db, true);

        //cancella dati cache
        SharedPreferenceWrapper.getCommonInstance(context).clear();


        onCreate(db);
    }

}
