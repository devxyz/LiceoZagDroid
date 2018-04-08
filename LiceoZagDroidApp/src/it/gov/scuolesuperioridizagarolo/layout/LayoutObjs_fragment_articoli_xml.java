package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_articoli_xml{
  public final MultiAutoCompleteTextView searchTextView;
  public final ListView listView;
  public final ImageButton imageButtonSearch;
  public final ImageButton imageButtonPlus;
  public final TextView textView16;

public LayoutObjs_fragment_articoli_xml(Fragment f){
  View view=f.getView();
    searchTextView= (MultiAutoCompleteTextView)view.findViewById(R.id.searchTextView);
  listView= (ListView)view.findViewById(R.id.listView);
  imageButtonSearch= (ImageButton)view.findViewById(R.id.imageButtonSearch);
  imageButtonPlus= (ImageButton)view.findViewById(R.id.imageButtonPlus);
  textView16= (TextView)view.findViewById(R.id.textView16);
}
public LayoutObjs_fragment_articoli_xml(Activity view){
    searchTextView= (MultiAutoCompleteTextView)view.findViewById(R.id.searchTextView);
  listView= (ListView)view.findViewById(R.id.listView);
  imageButtonSearch= (ImageButton)view.findViewById(R.id.imageButtonSearch);
  imageButtonPlus= (ImageButton)view.findViewById(R.id.imageButtonPlus);
  textView16= (TextView)view.findViewById(R.id.textView16);

}
public LayoutObjs_fragment_articoli_xml(View view){
    searchTextView= (MultiAutoCompleteTextView)view.findViewById(R.id.searchTextView);
  listView= (ListView)view.findViewById(R.id.listView);
  imageButtonSearch= (ImageButton)view.findViewById(R.id.imageButtonSearch);
  imageButtonPlus= (ImageButton)view.findViewById(R.id.imageButtonPlus);
  textView16= (TextView)view.findViewById(R.id.textView16);

}
public LayoutObjs_fragment_articoli_xml(Dialog view){
    searchTextView= (MultiAutoCompleteTextView)view.findViewById(R.id.searchTextView);
  listView= (ListView)view.findViewById(R.id.listView);
  imageButtonSearch= (ImageButton)view.findViewById(R.id.imageButtonSearch);
  imageButtonPlus= (ImageButton)view.findViewById(R.id.imageButtonPlus);
  textView16= (TextView)view.findViewById(R.id.textView16);

}
}
   
