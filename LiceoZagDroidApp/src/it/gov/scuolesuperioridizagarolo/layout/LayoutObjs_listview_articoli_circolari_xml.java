package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_listview_articoli_circolari_xml{
  public final ImageView image;
  public final TextView textView_info_circolare;
  public final TextView textView14;
  public final TextView textView_oggetto;
  public final TextView textView19;
  public final TextView textView_tag;

public LayoutObjs_listview_articoli_circolari_xml(Fragment f){
  View view=f.getView();
    image= (ImageView)view.findViewById(R.id.image);
  textView_info_circolare= (TextView)view.findViewById(R.id.textView_info_circolare);
  textView14= (TextView)view.findViewById(R.id.textView14);
  textView_oggetto= (TextView)view.findViewById(R.id.textView_oggetto);
  textView19= (TextView)view.findViewById(R.id.textView19);
  textView_tag= (TextView)view.findViewById(R.id.textView_tag);
}
public LayoutObjs_listview_articoli_circolari_xml(Activity view){
    image= (ImageView)view.findViewById(R.id.image);
  textView_info_circolare= (TextView)view.findViewById(R.id.textView_info_circolare);
  textView14= (TextView)view.findViewById(R.id.textView14);
  textView_oggetto= (TextView)view.findViewById(R.id.textView_oggetto);
  textView19= (TextView)view.findViewById(R.id.textView19);
  textView_tag= (TextView)view.findViewById(R.id.textView_tag);

}
public LayoutObjs_listview_articoli_circolari_xml(View view){
    image= (ImageView)view.findViewById(R.id.image);
  textView_info_circolare= (TextView)view.findViewById(R.id.textView_info_circolare);
  textView14= (TextView)view.findViewById(R.id.textView14);
  textView_oggetto= (TextView)view.findViewById(R.id.textView_oggetto);
  textView19= (TextView)view.findViewById(R.id.textView19);
  textView_tag= (TextView)view.findViewById(R.id.textView_tag);

}
public LayoutObjs_listview_articoli_circolari_xml(Dialog view){
    image= (ImageView)view.findViewById(R.id.image);
  textView_info_circolare= (TextView)view.findViewById(R.id.textView_info_circolare);
  textView14= (TextView)view.findViewById(R.id.textView14);
  textView_oggetto= (TextView)view.findViewById(R.id.textView_oggetto);
  textView19= (TextView)view.findViewById(R.id.textView19);
  textView_tag= (TextView)view.findViewById(R.id.textView_tag);

}
}
   
