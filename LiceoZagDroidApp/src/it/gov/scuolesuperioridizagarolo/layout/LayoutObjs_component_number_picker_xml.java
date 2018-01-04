package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_component_number_picker_xml{
  public final Button decrement;
  public final EditText display;
  public final Button increment;

public LayoutObjs_component_number_picker_xml(Fragment f){
  View view=f.getView();
    decrement= (Button)view.findViewById(R.id.decrement);
  display= (EditText)view.findViewById(R.id.display);
  increment= (Button)view.findViewById(R.id.increment);
}
public LayoutObjs_component_number_picker_xml(Activity view){
    decrement= (Button)view.findViewById(R.id.decrement);
  display= (EditText)view.findViewById(R.id.display);
  increment= (Button)view.findViewById(R.id.increment);

}
public LayoutObjs_component_number_picker_xml(View view){
    decrement= (Button)view.findViewById(R.id.decrement);
  display= (EditText)view.findViewById(R.id.display);
  increment= (Button)view.findViewById(R.id.increment);

}
public LayoutObjs_component_number_picker_xml(Dialog view){
    decrement= (Button)view.findViewById(R.id.decrement);
  display= (EditText)view.findViewById(R.id.display);
  increment= (Button)view.findViewById(R.id.increment);

}
}
   
