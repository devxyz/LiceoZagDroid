package it.gov.scuolesuperioridizagarolo.layout;
import android.app.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import it.gov.scuolesuperioridizagarolo.R;
public class LayoutObjs_fragment_webpage_xml{
  public final WebView webViewHtml;
  public final ProgressBar progressBarLoad;
  public final RelativeLayout layoutTop;
  public final TextView textViewTitolo;
  public final ImageView imageViewOpen;
  public final ImageButton imageViewBack;

public LayoutObjs_fragment_webpage_xml(Fragment f){
  View view=f.getView();
    webViewHtml= (WebView)view.findViewById(R.id.webViewHtml);
  progressBarLoad= (ProgressBar)view.findViewById(R.id.progressBarLoad);
  layoutTop= (RelativeLayout)view.findViewById(R.id.layoutTop);
  textViewTitolo= (TextView)view.findViewById(R.id.textViewTitolo);
  imageViewOpen= (ImageView)view.findViewById(R.id.imageViewOpen);
  imageViewBack= (ImageButton)view.findViewById(R.id.imageViewBack);
}
public LayoutObjs_fragment_webpage_xml(Activity view){
    webViewHtml= (WebView)view.findViewById(R.id.webViewHtml);
  progressBarLoad= (ProgressBar)view.findViewById(R.id.progressBarLoad);
  layoutTop= (RelativeLayout)view.findViewById(R.id.layoutTop);
  textViewTitolo= (TextView)view.findViewById(R.id.textViewTitolo);
  imageViewOpen= (ImageView)view.findViewById(R.id.imageViewOpen);
  imageViewBack= (ImageButton)view.findViewById(R.id.imageViewBack);

}
public LayoutObjs_fragment_webpage_xml(View view){
    webViewHtml= (WebView)view.findViewById(R.id.webViewHtml);
  progressBarLoad= (ProgressBar)view.findViewById(R.id.progressBarLoad);
  layoutTop= (RelativeLayout)view.findViewById(R.id.layoutTop);
  textViewTitolo= (TextView)view.findViewById(R.id.textViewTitolo);
  imageViewOpen= (ImageView)view.findViewById(R.id.imageViewOpen);
  imageViewBack= (ImageButton)view.findViewById(R.id.imageViewBack);

}
public LayoutObjs_fragment_webpage_xml(Dialog view){
    webViewHtml= (WebView)view.findViewById(R.id.webViewHtml);
  progressBarLoad= (ProgressBar)view.findViewById(R.id.progressBarLoad);
  layoutTop= (RelativeLayout)view.findViewById(R.id.layoutTop);
  textViewTitolo= (TextView)view.findViewById(R.id.textViewTitolo);
  imageViewOpen= (ImageView)view.findViewById(R.id.imageViewOpen);
  imageViewBack= (ImageButton)view.findViewById(R.id.imageViewBack);

}
}
   
