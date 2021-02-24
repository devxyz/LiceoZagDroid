package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_listview_consigli_classe_docente_xml{
  public final TextView textView_materia;
  public final TextView textView_docente;
  public final TextView textView5;

public LayoutObjs_listview_consigli_classe_docente_xml(Fragment f){
  View view=f.getView();
    textView_materia= (TextView)view.findViewById(R.id.textView_materia);
  textView_docente= (TextView)view.findViewById(R.id.textView_docente);
  textView5= (TextView)view.findViewById(R.id.textView5);
}
public LayoutObjs_listview_consigli_classe_docente_xml(Activity view){
    textView_materia= (TextView)view.findViewById(R.id.textView_materia);
  textView_docente= (TextView)view.findViewById(R.id.textView_docente);
  textView5= (TextView)view.findViewById(R.id.textView5);

}
public LayoutObjs_listview_consigli_classe_docente_xml(View view){
    textView_materia= (TextView)view.findViewById(R.id.textView_materia);
  textView_docente= (TextView)view.findViewById(R.id.textView_docente);
  textView5= (TextView)view.findViewById(R.id.textView5);

}
public LayoutObjs_listview_consigli_classe_docente_xml(Dialog view){
    textView_materia= (TextView)view.findViewById(R.id.textView_materia);
  textView_docente= (TextView)view.findViewById(R.id.textView_docente);
  textView5= (TextView)view.findViewById(R.id.textView5);

}
}
   
