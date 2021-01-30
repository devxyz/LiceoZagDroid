package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_consigli_di_classe_xml{
  public final ExpandableListView listView;
  public final RelativeLayout linearLayout7;
  public final Button button_giorno;
  public final TextView textView13;

public LayoutObjs_fragment_consigli_di_classe_xml(Fragment f){
  View view=f.getView();
    listView= (ExpandableListView)view.findViewById(R.id.listView);
  linearLayout7= (RelativeLayout)view.findViewById(R.id.linearLayout7);
  button_giorno= (Button)view.findViewById(R.id.button_giorno);
  textView13= (TextView)view.findViewById(R.id.textView13);
}
public LayoutObjs_fragment_consigli_di_classe_xml(Activity view){
    listView= (ExpandableListView)view.findViewById(R.id.listView);
  linearLayout7= (RelativeLayout)view.findViewById(R.id.linearLayout7);
  button_giorno= (Button)view.findViewById(R.id.button_giorno);
  textView13= (TextView)view.findViewById(R.id.textView13);

}
public LayoutObjs_fragment_consigli_di_classe_xml(View view){
    listView= (ExpandableListView)view.findViewById(R.id.listView);
  linearLayout7= (RelativeLayout)view.findViewById(R.id.linearLayout7);
  button_giorno= (Button)view.findViewById(R.id.button_giorno);
  textView13= (TextView)view.findViewById(R.id.textView13);

}
public LayoutObjs_fragment_consigli_di_classe_xml(Dialog view){
    listView= (ExpandableListView)view.findViewById(R.id.listView);
  linearLayout7= (RelativeLayout)view.findViewById(R.id.linearLayout7);
  button_giorno= (Button)view.findViewById(R.id.button_giorno);
  textView13= (TextView)view.findViewById(R.id.textView13);

}
}
   
