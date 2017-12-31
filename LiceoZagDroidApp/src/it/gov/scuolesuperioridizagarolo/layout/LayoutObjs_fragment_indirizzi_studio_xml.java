package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_fragment_indirizzi_studio_xml {
    public final ListView listView3;
    public final ImageButton imageButton;

    public LayoutObjs_fragment_indirizzi_studio_xml(Fragment f) {
        View view = f.getView();
        listView3 = (ListView) view.findViewById(R.id.listView3);
        imageButton = (ImageButton) view.findViewById(R.id.imageButton);
    }

    public LayoutObjs_fragment_indirizzi_studio_xml(Activity view) {
        listView3 = (ListView) view.findViewById(R.id.listView3);
        imageButton = (ImageButton) view.findViewById(R.id.imageButton);

    }

    public LayoutObjs_fragment_indirizzi_studio_xml(View view) {
        listView3 = (ListView) view.findViewById(R.id.listView3);
        imageButton = (ImageButton) view.findViewById(R.id.imageButton);

    }

    public LayoutObjs_fragment_indirizzi_studio_xml(Dialog view) {
        listView3 = (ListView) view.findViewById(R.id.listView3);
        imageButton = (ImageButton) view.findViewById(R.id.imageButton);

    }
}
   
