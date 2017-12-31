package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_gridview_item_menu_home_xml{
  public final RelativeLayout layout;
  public final ImageView thumb;
  public final TextView title;

public LayoutObjs_gridview_item_menu_home_xml(Fragment f){
  View view=f.getView();
    layout= (RelativeLayout)view.findViewById(R.id.layout);
  thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);
}
public LayoutObjs_gridview_item_menu_home_xml(Activity view){
    layout= (RelativeLayout)view.findViewById(R.id.layout);
  thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);

}
public LayoutObjs_gridview_item_menu_home_xml(View view){
    layout= (RelativeLayout)view.findViewById(R.id.layout);
  thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);

}
public LayoutObjs_gridview_item_menu_home_xml(Dialog view){
    layout= (RelativeLayout)view.findViewById(R.id.layout);
  thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);

}
}
   
