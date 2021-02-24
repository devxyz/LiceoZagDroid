package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_classi_vuote_xml{
  public final RelativeLayout xLayout7;
  public final CheckBox checkBox_DDI;
  public final Button buttonPrev;
  public final Button buttonNext;
  public final Button button_giorno;
  public final ExpandableListView listView;

public LayoutObjs_fragment_classi_vuote_xml(Fragment f){
  View view=f.getView();
    xLayout7= (RelativeLayout)view.findViewById(R.id.xLayout7);
  checkBox_DDI= (CheckBox)view.findViewById(R.id.checkBox_DDI);
  buttonPrev= (Button)view.findViewById(R.id.buttonPrev);
  buttonNext= (Button)view.findViewById(R.id.buttonNext);
  button_giorno= (Button)view.findViewById(R.id.button_giorno);
  listView= (ExpandableListView)view.findViewById(R.id.listView);
}
public LayoutObjs_fragment_classi_vuote_xml(Activity view){
    xLayout7= (RelativeLayout)view.findViewById(R.id.xLayout7);
  checkBox_DDI= (CheckBox)view.findViewById(R.id.checkBox_DDI);
  buttonPrev= (Button)view.findViewById(R.id.buttonPrev);
  buttonNext= (Button)view.findViewById(R.id.buttonNext);
  button_giorno= (Button)view.findViewById(R.id.button_giorno);
  listView= (ExpandableListView)view.findViewById(R.id.listView);

}
public LayoutObjs_fragment_classi_vuote_xml(View view){
    xLayout7= (RelativeLayout)view.findViewById(R.id.xLayout7);
  checkBox_DDI= (CheckBox)view.findViewById(R.id.checkBox_DDI);
  buttonPrev= (Button)view.findViewById(R.id.buttonPrev);
  buttonNext= (Button)view.findViewById(R.id.buttonNext);
  button_giorno= (Button)view.findViewById(R.id.button_giorno);
  listView= (ExpandableListView)view.findViewById(R.id.listView);

}
public LayoutObjs_fragment_classi_vuote_xml(Dialog view){
    xLayout7= (RelativeLayout)view.findViewById(R.id.xLayout7);
  checkBox_DDI= (CheckBox)view.findViewById(R.id.checkBox_DDI);
  buttonPrev= (Button)view.findViewById(R.id.buttonPrev);
  buttonNext= (Button)view.findViewById(R.id.buttonNext);
  button_giorno= (Button)view.findViewById(R.id.button_giorno);
  listView= (ExpandableListView)view.findViewById(R.id.listView);

}
}
   
