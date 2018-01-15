package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_listview_orario_aula_lezione_xml{
  public final RelativeLayout layout;
  public final TextView textViewAula;
  public final TextView textViewDocenteClasse;
  public final TextView textViewLezione;
  public final TextView textViewOra;
  public final TextView textViewFasciaOraria;
  public final TextView textView_Note;

public LayoutObjs_listview_orario_aula_lezione_xml(Fragment f){
  View view=f.getView();
    layout= (RelativeLayout)view.findViewById(R.id.layout);
  textViewAula= (TextView)view.findViewById(R.id.textViewAula);
  textViewDocenteClasse= (TextView)view.findViewById(R.id.textViewDocenteClasse);
  textViewLezione= (TextView)view.findViewById(R.id.textViewLezione);
  textViewOra= (TextView)view.findViewById(R.id.textViewOra);
  textViewFasciaOraria= (TextView)view.findViewById(R.id.textViewFasciaOraria);
  textView_Note= (TextView)view.findViewById(R.id.textView_Note);
}
public LayoutObjs_listview_orario_aula_lezione_xml(Activity view){
    layout= (RelativeLayout)view.findViewById(R.id.layout);
  textViewAula= (TextView)view.findViewById(R.id.textViewAula);
  textViewDocenteClasse= (TextView)view.findViewById(R.id.textViewDocenteClasse);
  textViewLezione= (TextView)view.findViewById(R.id.textViewLezione);
  textViewOra= (TextView)view.findViewById(R.id.textViewOra);
  textViewFasciaOraria= (TextView)view.findViewById(R.id.textViewFasciaOraria);
  textView_Note= (TextView)view.findViewById(R.id.textView_Note);

}
public LayoutObjs_listview_orario_aula_lezione_xml(View view){
    layout= (RelativeLayout)view.findViewById(R.id.layout);
  textViewAula= (TextView)view.findViewById(R.id.textViewAula);
  textViewDocenteClasse= (TextView)view.findViewById(R.id.textViewDocenteClasse);
  textViewLezione= (TextView)view.findViewById(R.id.textViewLezione);
  textViewOra= (TextView)view.findViewById(R.id.textViewOra);
  textViewFasciaOraria= (TextView)view.findViewById(R.id.textViewFasciaOraria);
  textView_Note= (TextView)view.findViewById(R.id.textView_Note);

}
public LayoutObjs_listview_orario_aula_lezione_xml(Dialog view){
    layout= (RelativeLayout)view.findViewById(R.id.layout);
  textViewAula= (TextView)view.findViewById(R.id.textViewAula);
  textViewDocenteClasse= (TextView)view.findViewById(R.id.textViewDocenteClasse);
  textViewLezione= (TextView)view.findViewById(R.id.textViewLezione);
  textViewOra= (TextView)view.findViewById(R.id.textViewOra);
  textViewFasciaOraria= (TextView)view.findViewById(R.id.textViewFasciaOraria);
  textView_Note= (TextView)view.findViewById(R.id.textView_Note);

}
}
   
