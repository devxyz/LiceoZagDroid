package it.gov.scuolesuperioridizagarolo.activity;

import android.app.ActivityManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.*;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.action.api.ActivityAction;
import it.gov.scuolesuperioridizagarolo.adapter.MainMenuExpandibleListAdapter;
import it.gov.scuolesuperioridizagarolo.api.AbstractActivity;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.dao.*;
import it.gov.scuolesuperioridizagarolo.dialog.HtmlPageDialog;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerDialogErrorCheck;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfoFlag;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfoLatestUsed;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfoStack;
import it.gov.scuolesuperioridizagarolo.services.UpdateService;
import it.gov.scuolesuperioridizagarolo.util.C_DateUtil;
import it.gov.scuolesuperioridizagarolo.util.DialogUtil;
import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;
import it.gov.scuolesuperioridizagarolo.util.ThreadUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainMenuActivity extends AbstractActivity {
    public static final String RECEIVER_ACTION_UPDATE = "it.gov.scuolesuperioridizagarolo.DATA_UPDATE";
    private static final String KEY_MENU_ID_INTENT = "KEY_MENU_ID_INTENT";
    private static final String KEY_STACK = "KEY_STACK";
    public ListView mDrawerList;
    private DataMenuInfoStack stack;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    // nav drawer title
    private CharSequence mDrawerTitle;
    // used to store app title
    private CharSequence currentFragmentTitle;
    private MainMenuExpandibleListAdapter menuMainAdapter;
    private DataMenuInfoLatestUsed lastUsedMenu;
    private Drawable currentFragmentIcon;
    private AbstractFragment currentFragment;
    private int currentSelectionIndex = -1;
    private BroadcastReceiver receiver;


    public static void startMainActivity(Context ctx, DataMenuInfo m) {
        Intent i = new Intent(ctx, MainMenuActivity.class);
        if (m != null) {
            final String menuID = m.getMenuID();
            i.putExtra(KEY_MENU_ID_INTENT, menuID);
        }
        ctx.startActivity(i);
    }

    public static void startMainActivity(Context ctx) {
        startMainActivity(ctx, null);
    }

    public DataMenuInfoLatestUsed getLastUsedMenu() {
        return lastUsedMenu;
    }

    public MainMenuExpandibleListAdapter getMenuMainAdapter() {
        return menuMainAdapter;
    }

    public Drawable getCurrentFragmentIcon() {
        return currentFragmentIcon;
    }

    public AbstractFragment getCurrentFragment() {
        return currentFragment;
    }

    public String getCurrentFragmentTitle() {
        return currentFragmentTitle.toString();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);//NECESSARIO per salvare i dati dei fragment!!!!
        outState.putSerializable(KEY_STACK, stack);
        if (currentFragment != null) {
            //currentFragment.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_STACK)) {
            stack = (DataMenuInfoStack) savedInstanceState.getSerializable(KEY_STACK);
        } else {
            stack = new DataMenuInfoStack();
        }

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                //elimina eventuali notifiche appese...
                //NotificationUtil.errorMessage(null).cancel(getActivity());
                //NotificationUtil.newDataAvailableMessage(0).cancel(getActivity());
                String message = intent.getExtras().getString(UpdateService.KEY_MESSAGGIO_UPDATE, "No message");
                boolean shouldUpdate = intent.getExtras().getBoolean(UpdateService.KEY_SHOULD_UPDATE, false);

                Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                if (shouldUpdate) {
                    ThreadUtil.runOnUiThreadAsync(MainMenuActivity.this, new Runnable() {
                        @Override
                        public void run() {
                            if (currentFragment != null) {
                                currentFragment.updateUI();
                            }
                        }
                    });
                }
            }
        };
        registerReceiver(receiver, new IntentFilter(RECEIVER_ACTION_UPDATE));


        // Possible work around for market launches. See http://code.google.com/p/android/issues/detail?id=2373
        // for more details. Essentially, the market launches the main activity on top of other activities.
        // we never want this to happen. Instead, we check if we are the root and if not, we finish.


        //start service automatically
        if (UpdateService.shouldRunUpdateServiceAutomatically(getActivity())) {
            Intent serviceIntent = new Intent(this, UpdateService.class);
            startService(serviceIntent);
        }

        lastUsedMenu = new DataMenuInfoLatestUsed(getSharedPreferences().getLastUsedMenuId());


        //cancella i file troppo vecchi
        //cache.clear(DateUtil.sottraiGiorni(new Date(), 2));


        currentFragmentTitle = mDrawerTitle = getTitle();


        //=============================================================

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        // Recycle the typed array

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list menuAdapter
        menuMainAdapter = MainMenuExpandibleListAdapter.getInstanceForMainMenu(getApplicationContext(), InitActivity.getCurrentUser(this));
        mDrawerList.setAdapter(menuMainAdapter);


        // enabling action bar app icon and behaving it as toggle button
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable._actionbar_3_red_lines, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.nome_actionbar // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                if (getActionBar() != null) {
                    getActionBar().setTitle(currentFragmentTitle);
                    getActionBar().setIcon(currentFragmentIcon);
                }
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                if (getActionBar() != null) {
                    getActionBar().setTitle(mDrawerTitle);
                    Drawable d = getResources().getDrawable(R.drawable.logo_100x100);
                    getActionBar().setIcon(d);
                }
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            //controlla se è stato specificato un menu particolare
            final Intent intent1 = getIntent();
            if (intent1 != null && intent1.getExtras() != null && intent1.getExtras().getInt(KEY_MENU_ID_INTENT, -1) > 0) {
                final DataMenuInfo m = menuMainAdapter.getDataMenuInfoByMenuID(intent1.getExtras().getString(KEY_MENU_ID_INTENT, ""));
                if (m != null) {
                    doAction(0, null);
                    doAction(m, null);
                } else
                    doAction(0, null);
            } else {
                doAction(0, null);
                //openMenu();
            }
        } else {
            //restore in caso di rotazione schermo NON FACCIO NULLA, il restore lo fa il motore android
            /*final DataMenuInfoStack.DataMenuInfoCall back = stack.last();
            if (back != null)
                doAction(back.menu, back.parameter);
            else
                doAction(0, null);*/
        }


    }

    //azzera le varie info in base al cambiamento di utente
    public void reInitUser() {
        stack.clear();
        menuMainAdapter = MainMenuExpandibleListAdapter.getInstanceForMainMenu(getApplicationContext(), InitActivity.getCurrentUser(this));
        mDrawerList.setAdapter(menuMainAdapter);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            super.onKeyDown(keyCode, event);

            //do whatever you need for the hardware 'back' button
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void openMenu() {
        mDrawerLayout.openDrawer(mDrawerList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_informazioni:
                String nome_scuola = getResources().getString(R.string.nome_scuola);
                String indirizzo = getResources().getString(R.string.contatto_scuola_plain);

                ScuolaAppDbHelper r = new ScuolaAppDbHelper(getActivity());
                String version = "##";

                version = "" + DaoMaster.SCHEMA_VERSION;


                DialogUtil.openInfoDialog(getActivity(), "About", nome_scuola + "\n" + indirizzo +
                        "\n------------------------------\n" +
                        "Versione: " + version + "\n" +
                        "Realizzato da: Stefano Millozzi"
                );
                return true;
            case R.id.action_menu:
                openMenu();
                return true;
            case R.id.action_view_debug_fragment:
                if (currentFragment != null) {
                    final String htmlText = "<html><body>" + currentFragment.toString().replace("\n", "<br>") + "</body></html>";
                    HtmlPageDialog d = new HtmlPageDialog(this, "Report Fragment", htmlText, null);
                    d.show();

                }
                return true;
            case R.id.action_update:
                //start service
                Intent serviceIntent = new Intent(this, UpdateService.class);
                startService(serviceIntent);
                return true;
            case R.id.action_test_speech:
                Intent i = new Intent(MainMenuActivity.this, Text2SpeechActivity.class);
                startActivity(i);
                return true;
            case R.id.action_esci:
                finish();
                return true;
            case R.id.action_view_timetables: {

                final StringBuilder sb = new StringBuilder();

                try {
                    _composeReportOrariScaricati(sb);
                    HtmlPageDialog d = new HtmlPageDialog(this, "Report orari", sb.toString(), null);
                    d.show();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

                return true;
            }
            case R.id.action_usertype:
                InitActivity.chooseUserType(this, new OnClickListenerDialogErrorCheck(this) {
                    @Override
                    protected void onClickImpl(DialogInterface dialog, int which) throws Throwable {
                        MainMenuActivity.this.reInitUser();
                        MainMenuActivity.this.doAction(0, null);
                    }
                }, true);
                return true;
            case R.id.action_reset: {
                _doResetApplication();
                finish();
                return true;
            }
            case R.id.action_view_stack: {
                StringBuilder sb = new StringBuilder();
                final ArrayList<DataMenuInfoStack.DataMenuInfoCall> s = stack.getStack();
                for (DataMenuInfoStack.DataMenuInfoCall dataMenuInfo : s) {
                    sb.append(dataMenuInfo.menu.getMenuLabel() + " " + dataMenuInfo.parameter).append("\n");
                }
                DialogUtil.openInfoDialog(this, "Stack fragment", sb.toString());
                return true;
            }
            case R.id.action_view_preferences: {
                final SharedPreferenceWrapper commonInstance = SharedPreferenceWrapper.getCommonInstance(getActivity());
                final Map<String, ?> all = commonInstance.getPreferences().getAll();
                StringBuilder sb = new StringBuilder();

                sb.append("<html><body><table border=1>");
                sb.append("<tr><td>key</td><td>Value</td>");
                for (Map.Entry<String, ?> e : all.entrySet()) {
                    sb.append("<tr><td>" + e.getKey() + "</td><td>" + e.getValue() + "</td>");
                }
                sb.append("</table></body></html>");
                HtmlPageDialog d = new HtmlPageDialog(this, "Report shared prefs", sb.toString(), null);
                d.show();

                return true;
            }

            case R.id.action_utilizzo_cache: {
                File[] files = getCache().getFiles();
                long size = 0;
                if (files != null) {
                    for (File f : files) {
                        if (f.exists())
                            size += f.length();
                    }
                }
                int numFiles = files == null ? 0 : files.length;

                DialogUtil.openInfoDialog(this, "Occupazione cache", "Numero file:" + numFiles + "\nOccupazione totale:" + (size / 1024) + " KB");
                return true;
            }
            case R.id.action_elenco_cache: {
                Map<String, String> files = getCache().getReport();
                DialogUtil.openInfoDialog(this, "File in cache:", files);
                return true;
            }
            case R.id.action_help: {
                final AbstractFragment c = getCurrentFragment();
                if (c != null) {
                    final boolean b = c.doHelp(true);
                    if (!b)
                        Toast.makeText(getActivity(), "Help non disponibile", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            case R.id.action_cancella_cache:
                getCache().clear();
                File[] files = getCache().getFiles();
                for (File x : files) {
                    x.delete();
                }
                Toast.makeText(getApplication(), "Cache cancellata", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void _doResetApplication() {
        File[] files = getCache().getFiles();
        final int countFiles = files.length;
        long size = 0;
        for (File f : files) {
            size += f.length();
            f.delete();
        }
        final long totalSize = size;

        getSharedPreferences().clear();


        ScuolaAppDbHelper.runOneTransactionAsync(this, new ScuolaAppDBHelperRunAsync() {
            long numCircolari, numTermini, numFileCache, numNews;

            @Override
            public void run(DaoSession session, Context ctx) throws Throwable {
                numCircolari = session.getCircolareDBDao().queryBuilder().count();
                numTermini = session.getTermineDBDao().queryBuilder().count();
                numFileCache = session.getCacheFileDBDao().queryBuilder().count();
                numFileCache = session.getNewsDBDao().queryBuilder().count();

                session.getCircolareDBDao().deleteAll();
                session.getTermineDBDao().deleteAll();
                session.getCircolareContieneTermineDBDao().deleteAll();
                session.getCacheFileDBDao().deleteAll();
                session.getNewsDBDao().deleteAll();
            }

            @Override
            public void onPostExecuteRunUI_OnError(Throwable e) {
                DialogUtil.openErrorDialog(getActivity(), "Errore", "Impossibile cancellare i file del database", e);
            }

            @Override
            public void onPostExecuteRunUI_OnSuccess() {
/*                        DialogUtil.openInfoDialog(getActivity(), "Successo", "Dati cancellati correttamente dal database.\nCircolari: " + numCircolari + "\nNotizie: " + numNews +
                        "\nTermini: " + numTermini + "\nFile cache: " + numFileCache + "\nDimensione tot: " + totalSize + "\nNum files:" + countFiles);*/
            }

            @Override
            public void onCancelledRunUI() {

            }
        }, "Azzeramento", "Cancellazione");
    }

    private void _composeReportOrariScaricati(final StringBuilder sb) throws Throwable {
        sb.append("<html><body><table border=1>");

        final ScuolaAppDbHelper db = new ScuolaAppDbHelper(this);
        db.runInTransaction(new ScuolaAppDbHelperCallable<ArrayList<String>>() {
            @Override
            public ArrayList<String> call(DaoSession session, Context ctx) throws Throwable {
                final List<TimetableDB> list = session.getTimetableDBDao().queryBuilder().orderAsc(TimetableDBDao.Properties.RemoteId).list();
                ArrayList<String> ris = new ArrayList<String>();
                for (TimetableDB x : list) {
                    sb.append("<tr><td style='background-color:red'>##  </td><td style='background-color:red'></td></tr>");
                    sb.append("<tr><td>ID:</td><td>" + x.getRemoteId() + "</td></tr>" +
                            "<tr><td>Data Inizio</td><td>" + C_DateUtil.toDDMMYYY(x.getStartDate()) + "</td>" +
                            "<tr><td>Data Fine</td><td>" + C_DateUtil.toDDMMYYY(x.getEndDate()) + "</td></tr>" +
                            "<tr><td>Filename:</td><td>" + x.getFilename() + "</td></tr>" +
                            "<tr><td>Size BYTE:</td><td>" + x.getData().length + "</td></tr>" +
                            "<tr><td>Data Creazione:</td><td>" + x.getCreateDate() + "</td></tr>");
                }

                return ris;
            }
        });
        db.close();

        sb.append("</table></body></html>");
    }

    public int howManyRunningInstances(Context ctx) {
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        int count = 0;
        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (ctx.getPackageName().equalsIgnoreCase(task.baseActivity.getPackageName()))
                count++;
        }

        return count;
    }

    @Override
    public void onBackPressed() {
        if (stack.getStack().size() <= 1) {
            DialogUtil.openAskDialog(this, "Chiusura applicazione", "Vuoi chiudere l'applicazione?", new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    },
                    new Runnable() {
                        @Override
                        public void run() {

                        }
                    }
            );

        } else {
            final DataMenuInfoStack.DataMenuInfoCall last = stack.back();
            if (last != null)
                doAction(last.menu, last.parameter);
            else
                finish();
        }
        //Toast.makeText(getActivity(), "BACK", Toast.LENGTH_LONG).show();
    }

    /* *
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }


    protected void onPause() {
        super.onPause();
        getSharedPreferences().setLastUsedMenuId(lastUsedMenu.getMenuIdSortByUse());
    }


    @Override

    protected void onDestroy() {
        super.onDestroy();
        //cancella
        //cache.clear(DateUtil.sottraiGiorni(new Date(), 2));

        //stop service
        Intent serviceIntent = new Intent(this, UpdateService.class);
        stopService(serviceIntent);


        getCache().closeAsync();
        getDatabase().close();

        if (receiver != null)
            unregisterReceiver(receiver);
        receiver = null;

    }

    public void doAction(int position, Bundle parameters) {
        DataMenuInfo menu = menuMainAdapter.getDataMenuInfo(position);
        Log.d("selectFromMainMenu", "Imposta menu " + position + " - fragment:" + menu.getAction());
        doAction(menu, parameters);
    }

    private void selectMenu(int position) {
        if (position < 0 || position >= menuMainAdapter.getCount()) return;
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        currentSelectionIndex = position;
    }

    public void doAction(DataMenuInfo menu, Bundle parameter) {
        //System.out.println("MENUUUUUUU " + menu.getMenuLabel());

        int posizione = menuMainAdapter.positionByMenu(menu);

        if (!menu.getFlags().contains(DataMenuInfoFlag.DONT_SHOW_IN_HOME))
            lastUsedMenu.add(menu);


        switch (menu.type()) {
            case COMMAND: {
                closeMenu();
                try {
                    ActivityAction r = (ActivityAction) menu.getAction().build();
                    __doTask_ActivityAction(r, menu, parameter);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }

                //chiude submenu se voce presa da liv 1
                if (!menu.isSubItem())
                    getMenuMainAdapter().closeAll();
                break;
            }

            case FRAGMENT: {
                closeMenu();
                selectMenu(posizione);
                // istanzia il fragment con il costruttore senza argomenti
                AbstractFragment fragment = null;
                try {
                    fragment = (AbstractFragment) menu.getAction().build();
                    fragment.setParameters(parameter);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }

                Drawable d = getResources().getDrawable(menu.getImageId());
                String title = menu.getLongLabel();
                currentFragment = fragment;

                setTitle(title);
                setIcon(d);

                //salva stack
                {

                    stack.add(menu, parameter);
                    final FragmentManager fm = getFragmentManager();


                    final FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frame_container, fragment)
                            //           .addToBackStack(title)
                            .commit();
                }

                if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                    closeMenu();
                }


                //chiude submenu se voce presa da liv 1
                if (!menu.isSubItem())
                    getMenuMainAdapter().closeAll();
                break;
            }


            case LABEL_ONLY: {
                selectMenu(posizione);
                final DataMenuInfo prevSelectedItem;
                if (currentSelectionIndex >= 0) {
                    prevSelectedItem = menuMainAdapter.getDataMenuInfo(currentSelectionIndex);
                } else {
                    prevSelectedItem = null;
                }
                unselectMenu();
                getMenuMainAdapter().switchMenu(menu);

                if (prevSelectedItem != null) {
                    final int i = getMenuMainAdapter().positionByMenu(prevSelectedItem);
                    if (i >= 0)
                        selectMenu(i);
                }

                //si posiziona sull'elemento della lista selezionato
                mDrawerList.smoothScrollToPosition(getMenuMainAdapter().positionByMenu(menu));
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid class (no command or fragment): " + menu.getAction());

        }


    }

    private void closeMenu() {
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    public void unselectMenu() {
        currentSelectionIndex = -1;
        mDrawerList.clearChoices();
    }

    private void __doTask_ActivityAction(final ActivityAction runnable, final DataMenuInfo menu, final Bundle b) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                runnable.doTask(MainMenuActivity.this, menu, b);
            }
        });
    }

    @Override
    public void setTitle(CharSequence title) {
        if (title == null) return;
        currentFragmentTitle = title;
        if (getActionBar() != null) {
            getActionBar().setTitle(currentFragmentTitle);
        }
    }

    public void setIcon(Drawable d) {
        if (d == null) return;
        currentFragmentIcon = d;
        if (getActionBar() != null) {
            getActionBar().setIcon(d);
        }
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Slide menu item click listener
     */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            doAction(position, null);
        }
    }

}
