package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_listview_bar_prodotto_dettaglio_xml{
  public final RelativeLayout layout;
  public final TextView textView_Titolo;
  public final TextView textView_Prezzo;
  public final com.travijuu.numberpicker.library.NumberPicker number_picker;

public LayoutObjs_listview_bar_prodotto_dettaglio_xml(Fragment f){
  View view=f.getView();
    layout= (RelativeLayout)view.findViewById(R.id.layout);
  textView_Titolo= (TextView)view.findViewById(R.id.textView_Titolo);
  textView_Prezzo= (TextView)view.findViewById(R.id.textView_Prezzo);
  number_picker= (com.travijuu.numberpicker.library.NumberPicker)view.findViewById(R.id.number_picker);
}
public LayoutObjs_listview_bar_prodotto_dettaglio_xml(Activity view){
    layout= (RelativeLayout)view.findViewById(R.id.layout);
  textView_Titolo= (TextView)view.findViewById(R.id.textView_Titolo);
  textView_Prezzo= (TextView)view.findViewById(R.id.textView_Prezzo);
  number_picker= (com.travijuu.numberpicker.library.NumberPicker)view.findViewById(R.id.number_picker);

}
public LayoutObjs_listview_bar_prodotto_dettaglio_xml(View view){
    layout= (RelativeLayout)view.findViewById(R.id.layout);
  textView_Titolo= (TextView)view.findViewById(R.id.textView_Titolo);
  textView_Prezzo= (TextView)view.findViewById(R.id.textView_Prezzo);
  number_picker= (com.travijuu.numberpicker.library.NumberPicker)view.findViewById(R.id.number_picker);

}
public LayoutObjs_listview_bar_prodotto_dettaglio_xml(Dialog view){
    layout= (RelativeLayout)view.findViewById(R.id.layout);
  textView_Titolo= (TextView)view.findViewById(R.id.textView_Titolo);
  textView_Prezzo= (TextView)view.findViewById(R.id.textView_Prezzo);
  number_picker= (com.travijuu.numberpicker.library.NumberPicker)view.findViewById(R.id.number_picker);

}
}
   