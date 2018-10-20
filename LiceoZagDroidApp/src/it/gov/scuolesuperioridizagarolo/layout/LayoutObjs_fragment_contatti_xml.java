package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_contatti_xml{
  public final LinearLayout linearLayout;
  public final ImageButton imageButtonEmail;
  public final ImageButton imageButtonTelefono;
  public final TextView textView2;
  public final TextView textView;
  public final ImageButton imageButtonWWW;
  public final ImageButton imageButtonMap;
  public final TextView textViewContattiScuola;

public LayoutObjs_fragment_contatti_xml(Fragment f){
  View view=f.getView();
    linearLayout= (LinearLayout)view.findViewById(R.id.linearLayout);
  imageButtonEmail= (ImageButton)view.findViewById(R.id.imageButtonEmail);
  imageButtonTelefono= (ImageButton)view.findViewById(R.id.imageButtonTelefono);
  textView2= (TextView)view.findViewById(R.id.textView2);
  textView= (TextView)view.findViewById(R.id.textView);
  imageButtonWWW= (ImageButton)view.findViewById(R.id.imageButtonWWW);
  imageButtonMap= (ImageButton)view.findViewById(R.id.imageButtonMap);
  textViewContattiScuola= (TextView)view.findViewById(R.id.textViewContattiScuola);
}
public LayoutObjs_fragment_contatti_xml(Activity view){
    linearLayout= (LinearLayout)view.findViewById(R.id.linearLayout);
  imageButtonEmail= (ImageButton)view.findViewById(R.id.imageButtonEmail);
  imageButtonTelefono= (ImageButton)view.findViewById(R.id.imageButtonTelefono);
  textView2= (TextView)view.findViewById(R.id.textView2);
  textView= (TextView)view.findViewById(R.id.textView);
  imageButtonWWW= (ImageButton)view.findViewById(R.id.imageButtonWWW);
  imageButtonMap= (ImageButton)view.findViewById(R.id.imageButtonMap);
  textViewContattiScuola= (TextView)view.findViewById(R.id.textViewContattiScuola);

}
public LayoutObjs_fragment_contatti_xml(View view){
    linearLayout= (LinearLayout)view.findViewById(R.id.linearLayout);
  imageButtonEmail= (ImageButton)view.findViewById(R.id.imageButtonEmail);
  imageButtonTelefono= (ImageButton)view.findViewById(R.id.imageButtonTelefono);
  textView2= (TextView)view.findViewById(R.id.textView2);
  textView= (TextView)view.findViewById(R.id.textView);
  imageButtonWWW= (ImageButton)view.findViewById(R.id.imageButtonWWW);
  imageButtonMap= (ImageButton)view.findViewById(R.id.imageButtonMap);
  textViewContattiScuola= (TextView)view.findViewById(R.id.textViewContattiScuola);

}
public LayoutObjs_fragment_contatti_xml(Dialog view){
    linearLayout= (LinearLayout)view.findViewById(R.id.linearLayout);
  imageButtonEmail= (ImageButton)view.findViewById(R.id.imageButtonEmail);
  imageButtonTelefono= (ImageButton)view.findViewById(R.id.imageButtonTelefono);
  textView2= (TextView)view.findViewById(R.id.textView2);
  textView= (TextView)view.findViewById(R.id.textView);
  imageButtonWWW= (ImageButton)view.findViewById(R.id.imageButtonWWW);
  imageButtonMap= (ImageButton)view.findViewById(R.id.imageButtonMap);
  textViewContattiScuola= (TextView)view.findViewById(R.id.textViewContattiScuola);

}
}
   
