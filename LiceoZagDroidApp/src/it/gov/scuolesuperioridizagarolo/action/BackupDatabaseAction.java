package it.gov.scuolesuperioridizagarolo.action;

import android.os.Bundle;
import android.os.Environment;
import it.gov.scuolesuperioridizagarolo.action.api.ActivityAction;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.dialog.HtmlPageDialog;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.util.DataBaseUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by stefano on 02/02/2018.
 */
public class BackupDatabaseAction implements ActivityAction {


    @Override
    public void doTask(MainMenuActivity activity, DataMenuInfo item, Bundle bundle) {
        ScuolaAppDbHelper s = new ScuolaAppDbHelper(activity);
        final String html = DataBaseUtil.exportHtml(s.getReadableDatabase());
        HtmlPageDialog d = new HtmlPageDialog(activity, "Dettaglio tabelle", html, null);
        final File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File out = new File(externalStoragePublicDirectory, "database_zagarolo.html");
        try {
            PrintStream o = new PrintStream(out);
            o.print(html);
            o.close();

        } catch (FileNotFoundException e) {

        }
        d.show();


    }

}
