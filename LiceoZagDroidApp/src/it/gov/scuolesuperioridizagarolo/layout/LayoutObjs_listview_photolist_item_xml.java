package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_listview_photolist_item_xml{
  public final ImageView thumb;
  public final TextView title;
  public final ImageView arrow;

public LayoutObjs_listview_photolist_item_xml(Fragment f){
  View view=f.getView();
    thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);
  arrow= (ImageView)view.findViewById(R.id.arrow);
}
public LayoutObjs_listview_photolist_item_xml(Activity view){
    thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);
  arrow= (ImageView)view.findViewById(R.id.arrow);

}
public LayoutObjs_listview_photolist_item_xml(View view){
    thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);
  arrow= (ImageView)view.findViewById(R.id.arrow);

}
public LayoutObjs_listview_photolist_item_xml(Dialog view){
    thumb= (ImageView)view.findViewById(R.id.thumb);
  title= (TextView)view.findViewById(R.id.title);
  arrow= (ImageView)view.findViewById(R.id.arrow);

}
}
   
