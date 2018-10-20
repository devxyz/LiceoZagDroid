package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_orario_classe_xml{
  public final ListView listView;
  public final RelativeLayout linearLayout6;
  public final ImageButton button_filtro;
  public final Button buttonPrev;
  public final ImageButton button_giorno;
  public final Button buttonNext;
  public final TextView textViewNomeData;

public LayoutObjs_fragment_orario_classe_xml(Fragment f){
  View view=f.getView();
    listView= (ListView)view.findViewById(R.id.listView);
  linearLayout6= (RelativeLayout)view.findViewById(R.id.linearLayout6);
  button_filtro= (ImageButton)view.findViewById(R.id.button_filtro);
  buttonPrev= (Button)view.findViewById(R.id.buttonPrev);
  button_giorno= (ImageButton)view.findViewById(R.id.button_giorno);
  buttonNext= (Button)view.findViewById(R.id.buttonNext);
  textViewNomeData= (TextView)view.findViewById(R.id.textViewNomeData);
}
public LayoutObjs_fragment_orario_classe_xml(Activity view){
    listView= (ListView)view.findViewById(R.id.listView);
  linearLayout6= (RelativeLayout)view.findViewById(R.id.linearLayout6);
  button_filtro= (ImageButton)view.findViewById(R.id.button_filtro);
  buttonPrev= (Button)view.findViewById(R.id.buttonPrev);
  button_giorno= (ImageButton)view.findViewById(R.id.button_giorno);
  buttonNext= (Button)view.findViewById(R.id.buttonNext);
  textViewNomeData= (TextView)view.findViewById(R.id.textViewNomeData);

}
public LayoutObjs_fragment_orario_classe_xml(View view){
    listView= (ListView)view.findViewById(R.id.listView);
  linearLayout6= (RelativeLayout)view.findViewById(R.id.linearLayout6);
  button_filtro= (ImageButton)view.findViewById(R.id.button_filtro);
  buttonPrev= (Button)view.findViewById(R.id.buttonPrev);
  button_giorno= (ImageButton)view.findViewById(R.id.button_giorno);
  buttonNext= (Button)view.findViewById(R.id.buttonNext);
  textViewNomeData= (TextView)view.findViewById(R.id.textViewNomeData);

}
public LayoutObjs_fragment_orario_classe_xml(Dialog view){
    listView= (ListView)view.findViewById(R.id.listView);
  linearLayout6= (RelativeLayout)view.findViewById(R.id.linearLayout6);
  button_filtro= (ImageButton)view.findViewById(R.id.button_filtro);
  buttonPrev= (Button)view.findViewById(R.id.buttonPrev);
  button_giorno= (ImageButton)view.findViewById(R.id.button_giorno);
  buttonNext= (Button)view.findViewById(R.id.buttonNext);
  textViewNomeData= (TextView)view.findViewById(R.id.textViewNomeData);

}
}
   
