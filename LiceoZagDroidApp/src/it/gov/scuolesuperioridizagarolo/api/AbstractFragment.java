package it.gov.scuolesuperioridizagarolo.api;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import it.gov.scuolesuperioridizagarolo.activity.HelpActivity;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.cache.AsyncUrlLoaderCallback;
import it.gov.scuolesuperioridizagarolo.util.SharedPreferenceWrapper;

import java.util.ArrayList;

/**
 * Created by stefano on 18/03/15.
 */
public abstract class AbstractFragment extends Fragment {
    private static final String KEY_AbstractFragment_BUNDLE = "KEY_AbstractFragment_BUNDLE";
    private final ArrayList<AsyncTask> task1 = new ArrayList<AsyncTask>();
    private final ArrayList<AsyncUrlLoaderCallback> task2 = new ArrayList<AsyncUrlLoaderCallback>();
    private Bundle parameter = null;

    protected AbstractFragment() {

    }

    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container,
                                      Bundle savedInstanceState, Bundle p);

    public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                                   Bundle savedInstanceState) {
        if (parameter == null)
            parameter = savedInstanceState.getBundle(KEY_AbstractFragment_BUNDLE);

        return onCreateView(inflater, container, savedInstanceState, getParameter());
    }

    protected Bundle getParameter() {
        return parameter;
    }

    public void setParameters(Bundle p) {
        this.parameter = p;
    }


    /**
     * chiamato quando è opportuno aggiornare l'interfaccia grafica per nuovi dati disponibili
     */
    public void updateUI() {
        //DialogUtil.openInfoDialog(getMainActivity(), "Aggiornamento", "Test di aggiornamento");
        //Toast.makeText(getMainActivity(), "Aggiornamento dati", Toast.LENGTH_LONG).show();
    }

    @Override
    public final void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        doHelp(false);
    }

    public final boolean doHelp(boolean force) {
        final SharedPreferenceWrapper sp = getMainActivity().getSharedPreferences();
        boolean read = force ? false : sp.getHelpShownForFragment(this);

        if (read) {
            return false;
        }

        final Integer helpScreen = getHelpScreen();
        if (helpScreen == null) {
            sp.setHelpShownForFragment(this, true);
            return false;
        } else {

            final Intent i = new Intent(getActivity(), HelpActivity.class);
            HelpActivity.setParameter(i, helpScreen);
            startActivity(i);
            sp.setHelpShownForFragment(this, true);
            return true;
        }


    }

    protected Integer getHelpScreen() {
        return null;
    }

    /**
     * aggiunge un task alla lista dei task asincroni
     */
    public <T extends AsyncTask> T addTask(T task) {
        task1.add(task);
        return task;
    }

    @Override
    public void onStop() {
        super.onStop();
        final int i = cancelAllTask();
        // Toast.makeText(getMainActivity(), "Cancellati " + i + " task pendenti", Toast.LENGTH_SHORT).show();
    }

    /**
     * aggiunge un task alla lista dei task asincroni
     */
    public <T extends AsyncUrlLoaderCallback> T addTask(T task) {
        task2.add(task);
        return task;
    }

    protected int cancelAllTask() {
        //interrompe tutti i task non terminati
        int i = 0;
        for (AsyncTask task : task1) {
            task.cancel(true);
            i++;
        }
        //interrompe tutti i task non terminati
        for (AsyncUrlLoaderCallback task : task2) {
            getMainActivity().getCache().cancel(task);
            i++;
        }
        return i;
    }

    public final MainMenuActivity getMainActivity() {
        return (MainMenuActivity) getActivity();
    }


}
