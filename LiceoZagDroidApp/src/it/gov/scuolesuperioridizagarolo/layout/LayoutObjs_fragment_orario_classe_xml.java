package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_orario_classe_xml{
  public final TextView textViewGiorni;
  public final ListView listView;
  public final TextView textViewClasse;

public LayoutObjs_fragment_orario_classe_xml(Fragment f){
  View view=f.getView();
    textViewGiorni= (TextView)view.findViewById(R.id.textViewGiorni);
  listView= (ListView)view.findViewById(R.id.listView);
  textViewClasse= (TextView)view.findViewById(R.id.textViewClasse);
}
public LayoutObjs_fragment_orario_classe_xml(Activity view){
    textViewGiorni= (TextView)view.findViewById(R.id.textViewGiorni);
  listView= (ListView)view.findViewById(R.id.listView);
  textViewClasse= (TextView)view.findViewById(R.id.textViewClasse);

}
public LayoutObjs_fragment_orario_classe_xml(View view){
    textViewGiorni= (TextView)view.findViewById(R.id.textViewGiorni);
  listView= (ListView)view.findViewById(R.id.listView);
  textViewClasse= (TextView)view.findViewById(R.id.textViewClasse);

}
public LayoutObjs_fragment_orario_classe_xml(Dialog view){
    textViewGiorni= (TextView)view.findViewById(R.id.textViewGiorni);
  listView= (ListView)view.findViewById(R.id.listView);
  textViewClasse= (TextView)view.findViewById(R.id.textViewClasse);

}
}
   
