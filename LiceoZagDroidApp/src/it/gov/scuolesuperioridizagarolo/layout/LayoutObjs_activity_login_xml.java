package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_activity_login_xml{
  public final TextView login_label_profilo;
  public final Spinner login_spinner_tipo_profilo;
  public final Button login_button_accedi;
  public final Button login_button_esci;
  public final TextView textView21;
  public final TextView textView22;
  public final TextView activity_login_textViewMsg;

public LayoutObjs_activity_login_xml(Fragment f){
  View view=f.getView();
    login_label_profilo= (TextView)view.findViewById(R.id.login_label_profilo);
  login_spinner_tipo_profilo= (Spinner)view.findViewById(R.id.login_spinner_tipo_profilo);
  login_button_accedi= (Button)view.findViewById(R.id.login_button_accedi);
  login_button_esci= (Button)view.findViewById(R.id.login_button_esci);
  textView21= (TextView)view.findViewById(R.id.textView21);
  textView22= (TextView)view.findViewById(R.id.textView22);
  activity_login_textViewMsg= (TextView)view.findViewById(R.id.activity_login_textViewMsg);
}
public LayoutObjs_activity_login_xml(Activity view){
    login_label_profilo= (TextView)view.findViewById(R.id.login_label_profilo);
  login_spinner_tipo_profilo= (Spinner)view.findViewById(R.id.login_spinner_tipo_profilo);
  login_button_accedi= (Button)view.findViewById(R.id.login_button_accedi);
  login_button_esci= (Button)view.findViewById(R.id.login_button_esci);
  textView21= (TextView)view.findViewById(R.id.textView21);
  textView22= (TextView)view.findViewById(R.id.textView22);
  activity_login_textViewMsg= (TextView)view.findViewById(R.id.activity_login_textViewMsg);

}
public LayoutObjs_activity_login_xml(View view){
    login_label_profilo= (TextView)view.findViewById(R.id.login_label_profilo);
  login_spinner_tipo_profilo= (Spinner)view.findViewById(R.id.login_spinner_tipo_profilo);
  login_button_accedi= (Button)view.findViewById(R.id.login_button_accedi);
  login_button_esci= (Button)view.findViewById(R.id.login_button_esci);
  textView21= (TextView)view.findViewById(R.id.textView21);
  textView22= (TextView)view.findViewById(R.id.textView22);
  activity_login_textViewMsg= (TextView)view.findViewById(R.id.activity_login_textViewMsg);

}
public LayoutObjs_activity_login_xml(Dialog view){
    login_label_profilo= (TextView)view.findViewById(R.id.login_label_profilo);
  login_spinner_tipo_profilo= (Spinner)view.findViewById(R.id.login_spinner_tipo_profilo);
  login_button_accedi= (Button)view.findViewById(R.id.login_button_accedi);
  login_button_esci= (Button)view.findViewById(R.id.login_button_esci);
  textView21= (TextView)view.findViewById(R.id.textView21);
  textView22= (TextView)view.findViewById(R.id.textView22);
  activity_login_textViewMsg= (TextView)view.findViewById(R.id.activity_login_textViewMsg);

}
}
   
