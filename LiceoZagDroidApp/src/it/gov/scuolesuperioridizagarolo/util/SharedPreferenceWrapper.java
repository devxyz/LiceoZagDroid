package it.gov.scuolesuperioridizagarolo.util;

import android.content.Context;
import android.content.SharedPreferences;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.model.AppUserType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by stefano on 09/06/15.
 */
public class SharedPreferenceWrapper {
    private static final String PREFIX_FRAGMENT_HELP = "PREFIX_FRAGMENT_HELP";
    private static final String KEY_LAST_USED_MENU = "KEY_LAST_USED_MENU";
    //private static final String KEY_NOME_CLASSE = "KEY_NOME_CLASSE";
    //private static final String KEY_NOME_DOCENTE = "KEY_NOME_DOCENTE";
    private static final String KEY_USER_TYPE = "KEY_USER_TYPE";
    private static final String KEY_USER_ADDITIONAL_FIELD = "KEY_USER_ADDITIONAL_FIELD";//campo addizionale
    private static final String KEY_DATA_UPDATE = "KEY_DATA_UPDATE";
    private final SharedPreferences preferences;

    private SharedPreferenceWrapper(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public static SharedPreferenceWrapper getCommonInstance(Context ctx) {
        return new SharedPreferenceWrapper(ctx.getSharedPreferences("liceo-zagarolo", Context.MODE_PRIVATE));
    }


    public String getAdditionalField() {
        return preferences.getString(KEY_USER_ADDITIONAL_FIELD, null);
    }

    public void setAdditionalField(String d) {
        SharedPreferences.Editor e = preferences.edit();
        e.putString(KEY_USER_ADDITIONAL_FIELD, d);
        e.apply();
    }

    public AppUserType getUserType() {
        final String string = preferences.getString(KEY_USER_TYPE, null);
        if (string == null) return null;
        return AppUserType.valueOf(string);
    }


    public void setUserType(AppUserType t) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(KEY_USER_TYPE, t.name());
        edit.apply();
    }

    public Date getLastDataUpdate() {
        final long aLong = preferences.getLong(KEY_DATA_UPDATE, 0);
        return new Date(aLong);
    }

    public void setLastDataUpdate(Date d) {
        SharedPreferences.Editor e = preferences.edit();
        e.putLong(KEY_DATA_UPDATE, d.getTime());
        e.apply();
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }


    public List<String> getLastUsedMenuId() {
        final String string = preferences.getString(KEY_LAST_USED_MENU, "");
        final String[] split = string.split("[, ]+");

        List<String> ris = new ArrayList<>(split.length);
        for (String s : split) {
            if (s.trim().length() > 0)
                ris.add(s.trim());
        }
        return ris;
    }

    public void setLastUsedMenuId(List<String> ll) {
        final String s = ll.toString().replace("[", "").replace("]", "");
        SharedPreferences.Editor e = preferences.edit();
        e.putString(KEY_LAST_USED_MENU, s);
        e.apply();
    }

    public void clear() {
        SharedPreferences.Editor e = preferences.edit();
        e.clear();
        e.apply();
    }

    public boolean getHelpShownForFragment(AbstractFragment fragment) {
        final String name = fragment.getClass().getName();
        return preferences.getBoolean(PREFIX_FRAGMENT_HELP + name, false);
    }

    public void setHelpShownForFragment(AbstractFragment fragment, boolean value) {
        final String name = fragment.getClass().getName();
        SharedPreferences.Editor e = preferences.edit();
        e.putBoolean(PREFIX_FRAGMENT_HELP + name, value);
        e.apply();

    }
}
