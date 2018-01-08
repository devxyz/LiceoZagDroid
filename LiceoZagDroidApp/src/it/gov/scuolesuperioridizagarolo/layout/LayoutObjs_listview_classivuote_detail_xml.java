package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_listview_classivuote_detail_xml{
  public final TextView textView_aula;
  public final TextView textView_location;
  public final TextView textView5;

public LayoutObjs_listview_classivuote_detail_xml(Fragment f){
  View view=f.getView();
    textView_aula= (TextView)view.findViewById(R.id.textView_aula);
  textView_location= (TextView)view.findViewById(R.id.textView_location);
  textView5= (TextView)view.findViewById(R.id.textView5);
}
public LayoutObjs_listview_classivuote_detail_xml(Activity view){
    textView_aula= (TextView)view.findViewById(R.id.textView_aula);
  textView_location= (TextView)view.findViewById(R.id.textView_location);
  textView5= (TextView)view.findViewById(R.id.textView5);

}
public LayoutObjs_listview_classivuote_detail_xml(View view){
    textView_aula= (TextView)view.findViewById(R.id.textView_aula);
  textView_location= (TextView)view.findViewById(R.id.textView_location);
  textView5= (TextView)view.findViewById(R.id.textView5);

}
public LayoutObjs_listview_classivuote_detail_xml(Dialog view){
    textView_aula= (TextView)view.findViewById(R.id.textView_aula);
  textView_location= (TextView)view.findViewById(R.id.textView_location);
  textView5= (TextView)view.findViewById(R.id.textView5);

}
}
   
