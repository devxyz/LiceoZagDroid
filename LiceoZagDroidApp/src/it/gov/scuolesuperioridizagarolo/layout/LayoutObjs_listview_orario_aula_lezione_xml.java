package it.gov.scuolesuperioridizagarolo.layout;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import it.gov.scuolesuperioridizagarolo.R;

public class LayoutObjs_listview_orario_aula_lezione_xml {
    public final RelativeLayout layout;
    public final TextView textViewAula;
    public final TextView textViewDocenteClasse;
    public final TextView textViewLezione;
    public final TextView textViewOra;
    public final TextView textViewFasciaOraria;

    public LayoutObjs_listview_orario_aula_lezione_xml(Fragment f) {
        View view = f.getView();
        layout = (RelativeLayout) view.findViewById(R.id.layout);
        textViewAula = (TextView) view.findViewById(R.id.textViewAula);
        textViewDocenteClasse = (TextView) view.findViewById(R.id.textViewDocenteClasse);
        textViewLezione = (TextView) view.findViewById(R.id.textViewLezione);
        textViewOra = (TextView) view.findViewById(R.id.textViewOra);
        textViewFasciaOraria = (TextView) view.findViewById(R.id.textViewFasciaOraria);
    }

    public LayoutObjs_listview_orario_aula_lezione_xml(Activity view) {
        layout = (RelativeLayout) view.findViewById(R.id.layout);
        textViewAula = (TextView) view.findViewById(R.id.textViewAula);
        textViewDocenteClasse = (TextView) view.findViewById(R.id.textViewDocenteClasse);
        textViewLezione = (TextView) view.findViewById(R.id.textViewLezione);
        textViewOra = (TextView) view.findViewById(R.id.textViewOra);
        textViewFasciaOraria = (TextView) view.findViewById(R.id.textViewFasciaOraria);

    }

    public LayoutObjs_listview_orario_aula_lezione_xml(View view) {
        layout = (RelativeLayout) view.findViewById(R.id.layout);
        textViewAula = (TextView) view.findViewById(R.id.textViewAula);
        textViewDocenteClasse = (TextView) view.findViewById(R.id.textViewDocenteClasse);
        textViewLezione = (TextView) view.findViewById(R.id.textViewLezione);
        textViewOra = (TextView) view.findViewById(R.id.textViewOra);
        textViewFasciaOraria = (TextView) view.findViewById(R.id.textViewFasciaOraria);

    }

    public LayoutObjs_listview_orario_aula_lezione_xml(Dialog view) {
        layout = (RelativeLayout) view.findViewById(R.id.layout);
        textViewAula = (TextView) view.findViewById(R.id.textViewAula);
        textViewDocenteClasse = (TextView) view.findViewById(R.id.textViewDocenteClasse);
        textViewLezione = (TextView) view.findViewById(R.id.textViewLezione);
        textViewOra = (TextView) view.findViewById(R.id.textViewOra);
        textViewFasciaOraria = (TextView) view.findViewById(R.id.textViewFasciaOraria);

    }
}
   
