package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_cerca_circolari_xml{
  public final MultiAutoCompleteTextView searchTextView;
  public final ListView listView;
  public final ImageButton imageButtonSearch;
  public final TextView textViewListaVuota;
  public final ImageButton imageButtonPlus;
  public final TextView textView16;

public LayoutObjs_fragment_cerca_circolari_xml(Fragment f){
  View view=f.getView();
    searchTextView= (MultiAutoCompleteTextView)view.findViewById(R.id.searchTextView);
  listView= (ListView)view.findViewById(R.id.listView);
  imageButtonSearch= (ImageButton)view.findViewById(R.id.imageButtonSearch);
  textViewListaVuota= (TextView)view.findViewById(R.id.textViewListaVuota);
  imageButtonPlus= (ImageButton)view.findViewById(R.id.imageButtonPlus);
  textView16= (TextView)view.findViewById(R.id.textView16);
}
public LayoutObjs_fragment_cerca_circolari_xml(Activity view){
    searchTextView= (MultiAutoCompleteTextView)view.findViewById(R.id.searchTextView);
  listView= (ListView)view.findViewById(R.id.listView);
  imageButtonSearch= (ImageButton)view.findViewById(R.id.imageButtonSearch);
  textViewListaVuota= (TextView)view.findViewById(R.id.textViewListaVuota);
  imageButtonPlus= (ImageButton)view.findViewById(R.id.imageButtonPlus);
  textView16= (TextView)view.findViewById(R.id.textView16);

}
public LayoutObjs_fragment_cerca_circolari_xml(View view){
    searchTextView= (MultiAutoCompleteTextView)view.findViewById(R.id.searchTextView);
  listView= (ListView)view.findViewById(R.id.listView);
  imageButtonSearch= (ImageButton)view.findViewById(R.id.imageButtonSearch);
  textViewListaVuota= (TextView)view.findViewById(R.id.textViewListaVuota);
  imageButtonPlus= (ImageButton)view.findViewById(R.id.imageButtonPlus);
  textView16= (TextView)view.findViewById(R.id.textView16);

}
public LayoutObjs_fragment_cerca_circolari_xml(Dialog view){
    searchTextView= (MultiAutoCompleteTextView)view.findViewById(R.id.searchTextView);
  listView= (ListView)view.findViewById(R.id.listView);
  imageButtonSearch= (ImageButton)view.findViewById(R.id.imageButtonSearch);
  textViewListaVuota= (TextView)view.findViewById(R.id.textViewListaVuota);
  imageButtonPlus= (ImageButton)view.findViewById(R.id.imageButtonPlus);
  textView16= (TextView)view.findViewById(R.id.textView16);

}
}
   
