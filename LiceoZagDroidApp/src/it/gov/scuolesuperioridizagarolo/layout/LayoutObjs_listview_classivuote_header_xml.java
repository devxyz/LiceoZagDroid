package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_listview_classivuote_header_xml{
  public final TextView textView_ora;
  public final TextView textView_fascia;
  public final TextView textView_materie;

public LayoutObjs_listview_classivuote_header_xml(Fragment f){
  View view=f.getView();
    textView_ora= (TextView)view.findViewById(R.id.textView_ora);
  textView_fascia= (TextView)view.findViewById(R.id.textView_fascia);
  textView_materie= (TextView)view.findViewById(R.id.textView_materie);
}
public LayoutObjs_listview_classivuote_header_xml(Activity view){
    textView_ora= (TextView)view.findViewById(R.id.textView_ora);
  textView_fascia= (TextView)view.findViewById(R.id.textView_fascia);
  textView_materie= (TextView)view.findViewById(R.id.textView_materie);

}
public LayoutObjs_listview_classivuote_header_xml(View view){
    textView_ora= (TextView)view.findViewById(R.id.textView_ora);
  textView_fascia= (TextView)view.findViewById(R.id.textView_fascia);
  textView_materie= (TextView)view.findViewById(R.id.textView_materie);

}
public LayoutObjs_listview_classivuote_header_xml(Dialog view){
    textView_ora= (TextView)view.findViewById(R.id.textView_ora);
  textView_fascia= (TextView)view.findViewById(R.id.textView_fascia);
  textView_materie= (TextView)view.findViewById(R.id.textView_materie);

}
}
   
