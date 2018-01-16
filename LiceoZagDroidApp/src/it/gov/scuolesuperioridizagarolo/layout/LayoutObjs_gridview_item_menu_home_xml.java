package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_gridview_item_menu_home_xml{
  public final LinearLayout layout;
  public final LinearLayout layout2;
  public final ImageView thumb;
  public final TextView title;

public LayoutObjs_gridview_item_menu_home_xml(Fragment f){
  View view=f.getView();
    layout= (LinearLayout)view.findViewById(R.id.layout);
  layout2= (LinearLayout)view.findViewById(R.id.layout2);
  thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);
}
public LayoutObjs_gridview_item_menu_home_xml(Activity view){
    layout= (LinearLayout)view.findViewById(R.id.layout);
  layout2= (LinearLayout)view.findViewById(R.id.layout2);
  thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);

}
public LayoutObjs_gridview_item_menu_home_xml(View view){
    layout= (LinearLayout)view.findViewById(R.id.layout);
  layout2= (LinearLayout)view.findViewById(R.id.layout2);
  thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);

}
public LayoutObjs_gridview_item_menu_home_xml(Dialog view){
    layout= (LinearLayout)view.findViewById(R.id.layout);
  layout2= (LinearLayout)view.findViewById(R.id.layout2);
  thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);

}
}
   
