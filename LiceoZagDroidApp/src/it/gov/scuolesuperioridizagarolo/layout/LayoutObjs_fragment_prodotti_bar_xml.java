package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_prodotti_bar_xml{
  public final TextView textView_titolo;
  public final ExpandableListView listView_ordini;
  public final LinearLayout linearLayout5;
  public final ImageButton imageButton_plus;
  public final ImageButton imageButton_Minus;
  public final ImageButton imageButton_people;
  public final TextView textViewNumeroUtenti;
  public final ImageButton imageButton_Filter;

public LayoutObjs_fragment_prodotti_bar_xml(Fragment f){
  View view=f.getView();
    textView_titolo= (TextView)view.findViewById(R.id.textView_titolo);
  listView_ordini= (ExpandableListView)view.findViewById(R.id.listView_ordini);
  linearLayout5= (LinearLayout)view.findViewById(R.id.linearLayout5);
  imageButton_plus= (ImageButton)view.findViewById(R.id.imageButton_plus);
  imageButton_Minus= (ImageButton)view.findViewById(R.id.imageButton_Minus);
  imageButton_people= (ImageButton)view.findViewById(R.id.imageButton_people);
  textViewNumeroUtenti= (TextView)view.findViewById(R.id.textViewNumeroUtenti);
  imageButton_Filter= (ImageButton)view.findViewById(R.id.imageButton_Filter);
}
public LayoutObjs_fragment_prodotti_bar_xml(Activity view){
    textView_titolo= (TextView)view.findViewById(R.id.textView_titolo);
  listView_ordini= (ExpandableListView)view.findViewById(R.id.listView_ordini);
  linearLayout5= (LinearLayout)view.findViewById(R.id.linearLayout5);
  imageButton_plus= (ImageButton)view.findViewById(R.id.imageButton_plus);
  imageButton_Minus= (ImageButton)view.findViewById(R.id.imageButton_Minus);
  imageButton_people= (ImageButton)view.findViewById(R.id.imageButton_people);
  textViewNumeroUtenti= (TextView)view.findViewById(R.id.textViewNumeroUtenti);
  imageButton_Filter= (ImageButton)view.findViewById(R.id.imageButton_Filter);

}
public LayoutObjs_fragment_prodotti_bar_xml(View view){
    textView_titolo= (TextView)view.findViewById(R.id.textView_titolo);
  listView_ordini= (ExpandableListView)view.findViewById(R.id.listView_ordini);
  linearLayout5= (LinearLayout)view.findViewById(R.id.linearLayout5);
  imageButton_plus= (ImageButton)view.findViewById(R.id.imageButton_plus);
  imageButton_Minus= (ImageButton)view.findViewById(R.id.imageButton_Minus);
  imageButton_people= (ImageButton)view.findViewById(R.id.imageButton_people);
  textViewNumeroUtenti= (TextView)view.findViewById(R.id.textViewNumeroUtenti);
  imageButton_Filter= (ImageButton)view.findViewById(R.id.imageButton_Filter);

}
public LayoutObjs_fragment_prodotti_bar_xml(Dialog view){
    textView_titolo= (TextView)view.findViewById(R.id.textView_titolo);
  listView_ordini= (ExpandableListView)view.findViewById(R.id.listView_ordini);
  linearLayout5= (LinearLayout)view.findViewById(R.id.linearLayout5);
  imageButton_plus= (ImageButton)view.findViewById(R.id.imageButton_plus);
  imageButton_Minus= (ImageButton)view.findViewById(R.id.imageButton_Minus);
  imageButton_people= (ImageButton)view.findViewById(R.id.imageButton_people);
  textViewNumeroUtenti= (TextView)view.findViewById(R.id.textViewNumeroUtenti);
  imageButton_Filter= (ImageButton)view.findViewById(R.id.imageButton_Filter);

}
}
   
