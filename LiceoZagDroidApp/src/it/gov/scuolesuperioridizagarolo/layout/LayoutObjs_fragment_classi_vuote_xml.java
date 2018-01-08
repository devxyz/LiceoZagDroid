package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_classi_vuote_xml{
  public final TextView textViewGiorni;
  public final ExpandableListView listView;
  public final ImageView imageViewGiorno;

public LayoutObjs_fragment_classi_vuote_xml(Fragment f){
  View view=f.getView();
    textViewGiorni= (TextView)view.findViewById(R.id.textViewGiorni);
  listView= (ExpandableListView)view.findViewById(R.id.listView);
  imageViewGiorno= (ImageView)view.findViewById(R.id.imageViewGiorno);
}
public LayoutObjs_fragment_classi_vuote_xml(Activity view){
    textViewGiorni= (TextView)view.findViewById(R.id.textViewGiorni);
  listView= (ExpandableListView)view.findViewById(R.id.listView);
  imageViewGiorno= (ImageView)view.findViewById(R.id.imageViewGiorno);

}
public LayoutObjs_fragment_classi_vuote_xml(View view){
    textViewGiorni= (TextView)view.findViewById(R.id.textViewGiorni);
  listView= (ExpandableListView)view.findViewById(R.id.listView);
  imageViewGiorno= (ImageView)view.findViewById(R.id.imageViewGiorno);

}
public LayoutObjs_fragment_classi_vuote_xml(Dialog view){
    textViewGiorni= (TextView)view.findViewById(R.id.textViewGiorni);
  listView= (ExpandableListView)view.findViewById(R.id.listView);
  imageViewGiorno= (ImageView)view.findViewById(R.id.imageViewGiorno);

}
}
   
