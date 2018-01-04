package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_barcode_xml{
  public final Button button2;
  public final Button button3;
  public final ImageView imageView2;
  public final TextView textView4;

public LayoutObjs_fragment_barcode_xml(Fragment f){
  View view=f.getView();
    button2= (Button)view.findViewById(R.id.button2);
  button3= (Button)view.findViewById(R.id.button3);
  imageView2= (ImageView)view.findViewById(R.id.imageView2);
  textView4= (TextView)view.findViewById(R.id.textView4);
}
public LayoutObjs_fragment_barcode_xml(Activity view){
    button2= (Button)view.findViewById(R.id.button2);
  button3= (Button)view.findViewById(R.id.button3);
  imageView2= (ImageView)view.findViewById(R.id.imageView2);
  textView4= (TextView)view.findViewById(R.id.textView4);

}
public LayoutObjs_fragment_barcode_xml(View view){
    button2= (Button)view.findViewById(R.id.button2);
  button3= (Button)view.findViewById(R.id.button3);
  imageView2= (ImageView)view.findViewById(R.id.imageView2);
  textView4= (TextView)view.findViewById(R.id.textView4);

}
public LayoutObjs_fragment_barcode_xml(Dialog view){
    button2= (Button)view.findViewById(R.id.button2);
  button3= (Button)view.findViewById(R.id.button3);
  imageView2= (ImageView)view.findViewById(R.id.imageView2);
  textView4= (TextView)view.findViewById(R.id.textView4);

}
}
   
