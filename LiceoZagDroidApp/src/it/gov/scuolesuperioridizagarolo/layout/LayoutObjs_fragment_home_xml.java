package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_home_xml{
  public final GridView listView4;

public LayoutObjs_fragment_home_xml(Fragment f){
  View view=f.getView();
    listView4= (GridView)view.findViewById(R.id.listView4);
}
public LayoutObjs_fragment_home_xml(Activity view){
    listView4= (GridView)view.findViewById(R.id.listView4);

}
public LayoutObjs_fragment_home_xml(View view){
    listView4= (GridView)view.findViewById(R.id.listView4);

}
public LayoutObjs_fragment_home_xml(Dialog view){
    listView4= (GridView)view.findViewById(R.id.listView4);

}
}
   
