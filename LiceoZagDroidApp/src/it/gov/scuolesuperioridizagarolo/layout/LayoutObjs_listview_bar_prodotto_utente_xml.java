package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_listview_bar_prodotto_utente_xml{
  public final TextView textView_Utente;

public LayoutObjs_listview_bar_prodotto_utente_xml(Fragment f){
  View view=f.getView();
    textView_Utente= (TextView)view.findViewById(R.id.textView_Utente);
}
public LayoutObjs_listview_bar_prodotto_utente_xml(Activity view){
    textView_Utente= (TextView)view.findViewById(R.id.textView_Utente);

}
public LayoutObjs_listview_bar_prodotto_utente_xml(View view){
    textView_Utente= (TextView)view.findViewById(R.id.textView_Utente);

}
public LayoutObjs_listview_bar_prodotto_utente_xml(Dialog view){
    textView_Utente= (TextView)view.findViewById(R.id.textView_Utente);

}
}
   
