package it.gov.scuolesuperioridizagarolo.action;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
import it.gov.scuolesuperioridizagarolo.action.api.ActivityAction;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by stefano on 02/02/2018.
 */
public class BackupDatabaseAction implements ActivityAction {
    @Override
    public void doTask(MainMenuActivity activity, DataMenuInfo item, Bundle bundle) {


        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File dataDirectory = Environment.getDataDirectory();

        FileChannel source = null;
        FileChannel destination = null;


        String currentDBPath = activity.getApplicationContext().getDatabasePath("it.gov.scuolesuperioridizagarolo.dao").getAbsolutePath();
        System.err.println("DATABASE PATH: " + currentDBPath);
        String backupDBPath = "SampleDB.sqlite";
        File currentDB = new File(dataDirectory, currentDBPath);
        File backupDB = new File(externalStorageDirectory, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());

            Toast.makeText(activity, "DB Exported!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (source != null) source.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (destination != null) destination.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
