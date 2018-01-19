package it.gov.scuolesuperioridizagarolo.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.activity.MainMenuActivity;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_gridview_item_menu_home_xml;
import it.gov.scuolesuperioridizagarolo.model.AppUserType;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfo;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuInfoType;
import it.gov.scuolesuperioridizagarolo.model.menu.DataMenuLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by stefano on 04/03/15.
 */
public class MenuHomeListAdapter extends BaseAdapter implements IMenuListAdapter {

    private final List<DataMenuInfo> items;

    private MainMenuActivity activity;
    private LayoutInflater layoutInflater;

    public MenuHomeListAdapter(MainMenuActivity f) {
        items = new ArrayList<>();
        activity = f;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _populate();
    }

    private void _populate() {
        items.clear();

        final TypedArray navMenuInfo;
        AppUserType userType = activity.getSharedPreferences().getUserType();
        if (userType == null) {
            userType = AppUserType.FAMIGLIA;
        }
        switch (userType) {
            case STUDENTE:
                navMenuInfo = activity.getResources().obtainTypedArray(R.array.menu_home_studente);
                break;
            case DOCENTE:
                navMenuInfo = activity.getResources().obtainTypedArray(R.array.menu_home_docente);
                break;
            case FAMIGLIA:
                navMenuInfo = activity.getResources().obtainTypedArray(R.array.menu_home_famiglia);
                break;
            default:
                navMenuInfo = activity.getResources().obtainTypedArray(R.array.menu_home_ata);
                break;
        }


        final List<DataMenuInfo> load = DataMenuLoader.load(navMenuInfo);
        for (DataMenuInfo x : load) {
            if (x.type() != DataMenuInfoType.LABEL_ONLY)
                items.add(x);
        }

    }

    public void update() {
        items.clear();
        _populate();
        super.notifyDataSetChanged();
    }

    public DataMenuInfo searchByMenuID(String menuID) {
        for (DataMenuInfo m : items) {
            if (Objects.equals(m.getMenuID(), menuID)) return m;
        }
        return null;
    }

    public int positionByMenu(DataMenuInfo d) {
        return items.indexOf(d);
    }

    public DataMenuInfo getDataMenuInfo(int pos) {
        return items.get(pos);
    }

    @Override
    public int getCount() {

        // Set the total list item count
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Inflate the item layout and set the views
        View listItem = convertView;
        int pos = position;
        if (listItem == null) {
            listItem = layoutInflater.inflate(R.layout.gridview_item_menu_home, null);
        }

        LayoutObjs_gridview_item_menu_home_xml LAYOUT_OBJs;   //***************************
        LAYOUT_OBJs = new LayoutObjs_gridview_item_menu_home_xml(listItem);
        // Initialize the views in the layout
        ImageView iv = LAYOUT_OBJs.thumb;
        TextView tvTitle = LAYOUT_OBJs.title;

        String menuLabel = items.get(pos).getMenuLabel();
        final Integer imageId = items.get(pos).getImageId();


        tvTitle.setText(menuLabel);
        iv.setImageResource(imageId);
        return listItem;
    }


}
