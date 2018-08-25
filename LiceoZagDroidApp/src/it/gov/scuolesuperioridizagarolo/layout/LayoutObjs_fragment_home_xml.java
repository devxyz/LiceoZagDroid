package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_home_xml{
  public final ImageView imageView;
  public final GridView listView4;
  public final TextView textViewTitolo;
  public final TextView textViewTipoUtente;
  public final TextView textView15;

public LayoutObjs_fragment_home_xml(Fragment f){
  View view=f.getView();
    imageView= (ImageView)view.findViewById(R.id.imageView);
  listView4= (GridView)view.findViewById(R.id.listView4);
  textViewTitolo= (TextView)view.findViewById(R.id.textViewTitolo);
  textViewTipoUtente= (TextView)view.findViewById(R.id.textViewTipoUtente);
  textView15= (TextView)view.findViewById(R.id.textView15);
}
public LayoutObjs_fragment_home_xml(Activity view){
    imageView= (ImageView)view.findViewById(R.id.imageView);
  listView4= (GridView)view.findViewById(R.id.listView4);
  textViewTitolo= (TextView)view.findViewById(R.id.textViewTitolo);
  textViewTipoUtente= (TextView)view.findViewById(R.id.textViewTipoUtente);
  textView15= (TextView)view.findViewById(R.id.textView15);

}
public LayoutObjs_fragment_home_xml(View view){
    imageView= (ImageView)view.findViewById(R.id.imageView);
  listView4= (GridView)view.findViewById(R.id.listView4);
  textViewTitolo= (TextView)view.findViewById(R.id.textViewTitolo);
  textViewTipoUtente= (TextView)view.findViewById(R.id.textViewTipoUtente);
  textView15= (TextView)view.findViewById(R.id.textView15);

}
public LayoutObjs_fragment_home_xml(Dialog view){
    imageView= (ImageView)view.findViewById(R.id.imageView);
  listView4= (GridView)view.findViewById(R.id.listView4);
  textViewTitolo= (TextView)view.findViewById(R.id.textViewTitolo);
  textViewTipoUtente= (TextView)view.findViewById(R.id.textViewTipoUtente);
  textView15= (TextView)view.findViewById(R.id.textView15);

}
}
   
