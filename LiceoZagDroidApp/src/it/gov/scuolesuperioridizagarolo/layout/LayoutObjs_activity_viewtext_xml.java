package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_activity_viewtext_xml {
    public final TextView textView11;
    public final ScrollView scrollView3;
    public final TextView textView12;
    public final ImageButton imageButton2;

    public LayoutObjs_activity_viewtext_xml(Fragment f) {
        View view = f.getView();
        textView11 = (TextView) view.findViewById(R.id.textView11);
        scrollView3 = (ScrollView) view.findViewById(R.id.scrollView3);
        textView12 = (TextView) view.findViewById(R.id.textView12);
        imageButton2 = (ImageButton) view.findViewById(R.id.imageButton2);
    }

    public LayoutObjs_activity_viewtext_xml(Activity view) {
        textView11 = (TextView) view.findViewById(R.id.textView11);
        scrollView3 = (ScrollView) view.findViewById(R.id.scrollView3);
        textView12 = (TextView) view.findViewById(R.id.textView12);
        imageButton2 = (ImageButton) view.findViewById(R.id.imageButton2);

    }

    public LayoutObjs_activity_viewtext_xml(View view) {
        textView11 = (TextView) view.findViewById(R.id.textView11);
        scrollView3 = (ScrollView) view.findViewById(R.id.scrollView3);
        textView12 = (TextView) view.findViewById(R.id.textView12);
        imageButton2 = (ImageButton) view.findViewById(R.id.imageButton2);

    }

    public LayoutObjs_activity_viewtext_xml(Dialog view) {
        textView11 = (TextView) view.findViewById(R.id.textView11);
        scrollView3 = (ScrollView) view.findViewById(R.id.scrollView3);
        textView12 = (TextView) view.findViewById(R.id.textView12);
        imageButton2 = (ImageButton) view.findViewById(R.id.imageButton2);

    }
}
   
