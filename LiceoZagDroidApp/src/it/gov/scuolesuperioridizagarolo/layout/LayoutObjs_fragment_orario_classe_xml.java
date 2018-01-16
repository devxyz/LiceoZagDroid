package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_orario_classe_xml{
  public final ListView listView;
  public final RelativeLayout linearLayout6;
  public final Button button_filtro;
  public final Button button_giorno;

public LayoutObjs_fragment_orario_classe_xml(Fragment f){
  View view=f.getView();
    listView= (ListView)view.findViewById(R.id.listView);
  linearLayout6= (RelativeLayout)view.findViewById(R.id.linearLayout6);
  button_filtro= (Button)view.findViewById(R.id.button_filtro);
  button_giorno= (Button)view.findViewById(R.id.button_giorno);
}
public LayoutObjs_fragment_orario_classe_xml(Activity view){
    listView= (ListView)view.findViewById(R.id.listView);
  linearLayout6= (RelativeLayout)view.findViewById(R.id.linearLayout6);
  button_filtro= (Button)view.findViewById(R.id.button_filtro);
  button_giorno= (Button)view.findViewById(R.id.button_giorno);

}
public LayoutObjs_fragment_orario_classe_xml(View view){
    listView= (ListView)view.findViewById(R.id.listView);
  linearLayout6= (RelativeLayout)view.findViewById(R.id.linearLayout6);
  button_filtro= (Button)view.findViewById(R.id.button_filtro);
  button_giorno= (Button)view.findViewById(R.id.button_giorno);

}
public LayoutObjs_fragment_orario_classe_xml(Dialog view){
    listView= (ListView)view.findViewById(R.id.listView);
  linearLayout6= (RelativeLayout)view.findViewById(R.id.linearLayout6);
  button_filtro= (Button)view.findViewById(R.id.button_filtro);
  button_giorno= (Button)view.findViewById(R.id.button_giorno);

}
}
   
