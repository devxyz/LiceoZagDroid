package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_contatti_xml{
  public final TextView textViewGiorni;
  public final LinearLayout linearLayout;
  public final ImageButton imageButtonEmail;
  public final ImageButton imageButtonTelefono;
  public final ImageButton imageButtonWWW;
  public final ImageButton imageButtonMap;
  public final TextView textView2;

public LayoutObjs_fragment_contatti_xml(Fragment f){
  View view=f.getView();
    textViewGiorni= (TextView)view.findViewById(R.id.textViewGiorni);
  linearLayout= (LinearLayout)view.findViewById(R.id.linearLayout);
  imageButtonEmail= (ImageButton)view.findViewById(R.id.imageButtonEmail);
  imageButtonTelefono= (ImageButton)view.findViewById(R.id.imageButtonTelefono);
  imageButtonWWW= (ImageButton)view.findViewById(R.id.imageButtonWWW);
  imageButtonMap= (ImageButton)view.findViewById(R.id.imageButtonMap);
  textView2= (TextView)view.findViewById(R.id.textView2);
}
public LayoutObjs_fragment_contatti_xml(Activity view){
    textViewGiorni= (TextView)view.findViewById(R.id.textViewGiorni);
  linearLayout= (LinearLayout)view.findViewById(R.id.linearLayout);
  imageButtonEmail= (ImageButton)view.findViewById(R.id.imageButtonEmail);
  imageButtonTelefono= (ImageButton)view.findViewById(R.id.imageButtonTelefono);
  imageButtonWWW= (ImageButton)view.findViewById(R.id.imageButtonWWW);
  imageButtonMap= (ImageButton)view.findViewById(R.id.imageButtonMap);
  textView2= (TextView)view.findViewById(R.id.textView2);

}
public LayoutObjs_fragment_contatti_xml(View view){
    textViewGiorni= (TextView)view.findViewById(R.id.textViewGiorni);
  linearLayout= (LinearLayout)view.findViewById(R.id.linearLayout);
  imageButtonEmail= (ImageButton)view.findViewById(R.id.imageButtonEmail);
  imageButtonTelefono= (ImageButton)view.findViewById(R.id.imageButtonTelefono);
  imageButtonWWW= (ImageButton)view.findViewById(R.id.imageButtonWWW);
  imageButtonMap= (ImageButton)view.findViewById(R.id.imageButtonMap);
  textView2= (TextView)view.findViewById(R.id.textView2);

}
public LayoutObjs_fragment_contatti_xml(Dialog view){
    textViewGiorni= (TextView)view.findViewById(R.id.textViewGiorni);
  linearLayout= (LinearLayout)view.findViewById(R.id.linearLayout);
  imageButtonEmail= (ImageButton)view.findViewById(R.id.imageButtonEmail);
  imageButtonTelefono= (ImageButton)view.findViewById(R.id.imageButtonTelefono);
  imageButtonWWW= (ImageButton)view.findViewById(R.id.imageButtonWWW);
  imageButtonMap= (ImageButton)view.findViewById(R.id.imageButtonMap);
  textView2= (TextView)view.findViewById(R.id.textView2);

}
}
   
