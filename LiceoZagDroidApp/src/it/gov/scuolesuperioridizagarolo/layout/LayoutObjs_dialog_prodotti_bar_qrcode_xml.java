package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_dialog_prodotti_bar_qrcode_xml{
  public final ImageView image_qrcode;

public LayoutObjs_dialog_prodotti_bar_qrcode_xml(Fragment f){
  View view=f.getView();
    image_qrcode= (ImageView)view.findViewById(R.id.image_qrcode);
}
public LayoutObjs_dialog_prodotti_bar_qrcode_xml(Activity view){
    image_qrcode= (ImageView)view.findViewById(R.id.image_qrcode);

}
public LayoutObjs_dialog_prodotti_bar_qrcode_xml(View view){
    image_qrcode= (ImageView)view.findViewById(R.id.image_qrcode);

}
public LayoutObjs_dialog_prodotti_bar_qrcode_xml(Dialog view){
    image_qrcode= (ImageView)view.findViewById(R.id.image_qrcode);

}
}
   
