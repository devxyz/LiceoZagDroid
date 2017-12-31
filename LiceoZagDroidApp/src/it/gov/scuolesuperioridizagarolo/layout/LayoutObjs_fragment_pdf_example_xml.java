package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_fragment_pdf_example_xml {
    public final TextView messaggio2;
    public final Button button;

    public LayoutObjs_fragment_pdf_example_xml(Fragment f) {
        View view = f.getView();
        messaggio2 = (TextView) view.findViewById(R.id.messaggio2);
        button = (Button) view.findViewById(R.id.button);
    }

    public LayoutObjs_fragment_pdf_example_xml(Activity view) {
        messaggio2 = (TextView) view.findViewById(R.id.messaggio2);
        button = (Button) view.findViewById(R.id.button);

    }

    public LayoutObjs_fragment_pdf_example_xml(View view) {
        messaggio2 = (TextView) view.findViewById(R.id.messaggio2);
        button = (Button) view.findViewById(R.id.button);

    }

    public LayoutObjs_fragment_pdf_example_xml(Dialog view) {
        messaggio2 = (TextView) view.findViewById(R.id.messaggio2);
        button = (Button) view.findViewById(R.id.button);

    }
}
   
