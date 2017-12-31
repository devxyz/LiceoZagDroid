package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_fragment_orario_classe_xml {
    public final TextView textViewGiorni;
    public final ListView listView;
    public final TextView textViewFiltro;
    public final ImageView imageViewGiorno;
    public final ImageView imageViewFiltro;

    public LayoutObjs_fragment_orario_classe_xml(Fragment f) {
        View view = f.getView();
        textViewGiorni = (TextView) view.findViewById(R.id.textViewGiorni);
        listView = (ListView) view.findViewById(R.id.listView);
        textViewFiltro = (TextView) view.findViewById(R.id.textViewFiltro);
        imageViewGiorno = (ImageView) view.findViewById(R.id.imageViewGiorno);
        imageViewFiltro = (ImageView) view.findViewById(R.id.imageViewFiltro);
    }

    public LayoutObjs_fragment_orario_classe_xml(Activity view) {
        textViewGiorni = (TextView) view.findViewById(R.id.textViewGiorni);
        listView = (ListView) view.findViewById(R.id.listView);
        textViewFiltro = (TextView) view.findViewById(R.id.textViewFiltro);
        imageViewGiorno = (ImageView) view.findViewById(R.id.imageViewGiorno);
        imageViewFiltro = (ImageView) view.findViewById(R.id.imageViewFiltro);

    }

    public LayoutObjs_fragment_orario_classe_xml(View view) {
        textViewGiorni = (TextView) view.findViewById(R.id.textViewGiorni);
        listView = (ListView) view.findViewById(R.id.listView);
        textViewFiltro = (TextView) view.findViewById(R.id.textViewFiltro);
        imageViewGiorno = (ImageView) view.findViewById(R.id.imageViewGiorno);
        imageViewFiltro = (ImageView) view.findViewById(R.id.imageViewFiltro);

    }

    public LayoutObjs_fragment_orario_classe_xml(Dialog view) {
        textViewGiorni = (TextView) view.findViewById(R.id.textViewGiorni);
        listView = (ListView) view.findViewById(R.id.listView);
        textViewFiltro = (TextView) view.findViewById(R.id.textViewFiltro);
        imageViewGiorno = (ImageView) view.findViewById(R.id.imageViewGiorno);
        imageViewFiltro = (ImageView) view.findViewById(R.id.imageViewFiltro);

    }
}
   
