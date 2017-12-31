package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_webpage_xml{
  public final TextView textViewTitolo;
  public final ImageView imageViewOpen;
  public final ProgressBar progressBarLoad;
  public final WebView webViewHtml;

public LayoutObjs_fragment_webpage_xml(Fragment f){
  View view=f.getView();
    textViewTitolo= (TextView)view.findViewById(R.id.textViewTitolo);
  imageViewOpen= (ImageView)view.findViewById(R.id.imageViewOpen);
  progressBarLoad= (ProgressBar)view.findViewById(R.id.progressBarLoad);
  webViewHtml= (WebView)view.findViewById(R.id.webViewHtml);
}
public LayoutObjs_fragment_webpage_xml(Activity view){
    textViewTitolo= (TextView)view.findViewById(R.id.textViewTitolo);
  imageViewOpen= (ImageView)view.findViewById(R.id.imageViewOpen);
  progressBarLoad= (ProgressBar)view.findViewById(R.id.progressBarLoad);
  webViewHtml= (WebView)view.findViewById(R.id.webViewHtml);

}
public LayoutObjs_fragment_webpage_xml(View view){
    textViewTitolo= (TextView)view.findViewById(R.id.textViewTitolo);
  imageViewOpen= (ImageView)view.findViewById(R.id.imageViewOpen);
  progressBarLoad= (ProgressBar)view.findViewById(R.id.progressBarLoad);
  webViewHtml= (WebView)view.findViewById(R.id.webViewHtml);

}
public LayoutObjs_fragment_webpage_xml(Dialog view){
    textViewTitolo= (TextView)view.findViewById(R.id.textViewTitolo);
  imageViewOpen= (ImageView)view.findViewById(R.id.imageViewOpen);
  progressBarLoad= (ProgressBar)view.findViewById(R.id.progressBarLoad);
  webViewHtml= (WebView)view.findViewById(R.id.webViewHtml);

}
}
   
