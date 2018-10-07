package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_dialog_view_html_xml{
  public final WebView textViewDescrizione;
  public final LinearLayout linearLayout2;
  public final Button buttonOK;
  public final Button buttonSALVA;
  public final ProgressBar progressBar7;
  public final RelativeLayout layout2;
  public final TextView textViewTitle;
  public final ImageButton buttonClose;

public LayoutObjs_dialog_view_html_xml(Fragment f){
  View view=f.getView();
    textViewDescrizione= (WebView)view.findViewById(R.id.textViewDescrizione);
  linearLayout2= (LinearLayout)view.findViewById(R.id.linearLayout2);
  buttonOK= (Button)view.findViewById(R.id.buttonOK);
  buttonSALVA= (Button)view.findViewById(R.id.buttonSALVA);
  progressBar7= (ProgressBar)view.findViewById(R.id.progressBar7);
  layout2= (RelativeLayout)view.findViewById(R.id.layout2);
  textViewTitle= (TextView)view.findViewById(R.id.textViewTitle);
  buttonClose= (ImageButton)view.findViewById(R.id.buttonClose);
}
public LayoutObjs_dialog_view_html_xml(Activity view){
    textViewDescrizione= (WebView)view.findViewById(R.id.textViewDescrizione);
  linearLayout2= (LinearLayout)view.findViewById(R.id.linearLayout2);
  buttonOK= (Button)view.findViewById(R.id.buttonOK);
  buttonSALVA= (Button)view.findViewById(R.id.buttonSALVA);
  progressBar7= (ProgressBar)view.findViewById(R.id.progressBar7);
  layout2= (RelativeLayout)view.findViewById(R.id.layout2);
  textViewTitle= (TextView)view.findViewById(R.id.textViewTitle);
  buttonClose= (ImageButton)view.findViewById(R.id.buttonClose);

}
public LayoutObjs_dialog_view_html_xml(View view){
    textViewDescrizione= (WebView)view.findViewById(R.id.textViewDescrizione);
  linearLayout2= (LinearLayout)view.findViewById(R.id.linearLayout2);
  buttonOK= (Button)view.findViewById(R.id.buttonOK);
  buttonSALVA= (Button)view.findViewById(R.id.buttonSALVA);
  progressBar7= (ProgressBar)view.findViewById(R.id.progressBar7);
  layout2= (RelativeLayout)view.findViewById(R.id.layout2);
  textViewTitle= (TextView)view.findViewById(R.id.textViewTitle);
  buttonClose= (ImageButton)view.findViewById(R.id.buttonClose);

}
public LayoutObjs_dialog_view_html_xml(Dialog view){
    textViewDescrizione= (WebView)view.findViewById(R.id.textViewDescrizione);
  linearLayout2= (LinearLayout)view.findViewById(R.id.linearLayout2);
  buttonOK= (Button)view.findViewById(R.id.buttonOK);
  buttonSALVA= (Button)view.findViewById(R.id.buttonSALVA);
  progressBar7= (ProgressBar)view.findViewById(R.id.progressBar7);
  layout2= (RelativeLayout)view.findViewById(R.id.layout2);
  textViewTitle= (TextView)view.findViewById(R.id.textViewTitle);
  buttonClose= (ImageButton)view.findViewById(R.id.buttonClose);

}
}
   
