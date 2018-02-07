package it.gov.scuolesuperioridizagarolo.action;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import it.gov.scuolesuperioridizagarolo.action.api.ActivityAction;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.dao.DaoSession;
import it.gov.scuolesuperioridizagarolo.dao.ScuolaAppDbHelper;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by stefano on 02/02/2018.
 */
public class BackupDatabaseAction implements ActivityAction {

    public boolean exportDatabase(MainMenuActivity activity,DaoSession session) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());

        /**First of all we check if the external storage of the device is available for writing.
         * Remember that the external storage is not necessarily the sd card. Very often it is
         * the device storage.
         */
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return false;
        }
        else {
            //We use the Download directory for saving our .csv file.
            File exportDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!exportDir.exists())
            {
                exportDir.mkdirs();
            }

            /**This is our database connector class that reads the data from the database.
             * The code of this class is omitted for brevity.
             */
            SQLiteDatabase db = new ScuolaAppDbHelper(activity).getReadableDatabase(); //open the database for reading



            File file;
            PrintWriter printWriter = null;
            try
            {
                file = new File(exportDir, "MyCSVFile.csv");
                file.createNewFile();
                printWriter = new PrintWriter(new FileWriter(file));


                /**Let's read the first table of the database.
                 * getFirstTable() is a method in our DBCOurDatabaseConnector class which retrieves a Cursor
                 * containing all records of the table (all fields).
                 * The code of this class is omitted for brevity.
                 */
                Cursor curCSV = db.rawQuery("select * from contacts", null);


                for (int i=0;i<curCSV.getColumnCount();i++){

                }

                //Write the name of the table and the name of the columns (comma separated values) in the .csv file.
                printWriter.println("FIRST TABLE OF THE DATABASE");
                printWriter.println("DATE,ITEM,AMOUNT,CURRENCY");
                while(curCSV.moveToNext())
                {
                    Long date = curCSV.getLong(curCSV.getColumnIndex("date"));
                    String title = curCSV.getString(curCSV.getColumnIndex("title"));
                    Float amount = curCSV.getFloat(curCSV.getColumnIndex("amount"));
                    String description = curCSV.getString(curCSV.getColumnIndex("description"));

                    /**Create the line to write in the .csv file.
                     * We need a String where values are comma separated.
                     * The field date (Long) is formatted in a readable text. The amount field
                     * is converted into String.
                     */
                    String record = df.format(new Date(date)) + "," + title + "," + amount + "," + description;
                    printWriter.println(record); //write the record in the .csv file
                }

                curCSV.close();
                db.close();
            }

            catch(Exception exc) {
                //if there are any exceptions, return false
                return false;
            }
            finally {
                if(printWriter != null) printWriter.close();
            }

            //If there are no errors, return true.
            return true;
        }
    }


    @Override
    public void doTask(MainMenuActivity activity, DataMenuInfo item, Bundle bundle) {



    }

}
