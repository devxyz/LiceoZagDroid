package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_listview_item_menu_home_xml{
  public final ImageView thumb;
  public final TextView title;

public LayoutObjs_listview_item_menu_home_xml(Fragment f){
  View view=f.getView();
    thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);
}
public LayoutObjs_listview_item_menu_home_xml(Activity view){
    thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);

}
public LayoutObjs_listview_item_menu_home_xml(View view){
    thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);

}
public LayoutObjs_listview_item_menu_home_xml(Dialog view){
    thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);

}
}
   
