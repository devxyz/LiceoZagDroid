package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_fragment_news_rss_xml {
    public final MultiAutoCompleteTextView multiAutoCompleteTextView;
    public final ListView listView;
    public final ImageButton imageButton3;
    public final TextView textViewListaVuota;
    public final TextView textView18;

    public LayoutObjs_fragment_news_rss_xml(Fragment f) {
        View view = f.getView();
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) view.findViewById(R.id.multiAutoCompleteTextView);
        listView = (ListView) view.findViewById(R.id.listView);
        imageButton3 = (ImageButton) view.findViewById(R.id.imageButton3);
        textViewListaVuota = (TextView) view.findViewById(R.id.textViewListaVuota);
        textView18 = (TextView) view.findViewById(R.id.textView18);
    }

    public LayoutObjs_fragment_news_rss_xml(Activity view) {
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) view.findViewById(R.id.multiAutoCompleteTextView);
        listView = (ListView) view.findViewById(R.id.listView);
        imageButton3 = (ImageButton) view.findViewById(R.id.imageButton3);
        textViewListaVuota = (TextView) view.findViewById(R.id.textViewListaVuota);
        textView18 = (TextView) view.findViewById(R.id.textView18);

    }

    public LayoutObjs_fragment_news_rss_xml(View view) {
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) view.findViewById(R.id.multiAutoCompleteTextView);
        listView = (ListView) view.findViewById(R.id.listView);
        imageButton3 = (ImageButton) view.findViewById(R.id.imageButton3);
        textViewListaVuota = (TextView) view.findViewById(R.id.textViewListaVuota);
        textView18 = (TextView) view.findViewById(R.id.textView18);

    }

    public LayoutObjs_fragment_news_rss_xml(Dialog view) {
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) view.findViewById(R.id.multiAutoCompleteTextView);
        listView = (ListView) view.findViewById(R.id.listView);
        imageButton3 = (ImageButton) view.findViewById(R.id.imageButton3);
        textViewListaVuota = (TextView) view.findViewById(R.id.textViewListaVuota);
        textView18 = (TextView) view.findViewById(R.id.textView18);

    }
}
   
