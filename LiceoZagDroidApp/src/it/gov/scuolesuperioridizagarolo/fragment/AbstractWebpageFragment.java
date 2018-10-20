package it.gov.scuolesuperioridizagarolo.fragment;

/**
 * Created by stefano on 23/12/2017.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.*;
import android.widget.Toast;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_webpage_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;


public abstract class AbstractWebpageFragment extends AbstractFragment {

    private static String KEY_URL = "KEY_URL";
    private LayoutObjs_fragment_webpage_xml LAYOUT_OBJs;   //***************************
    private String currentUrl;

    public AbstractWebpageFragment() {
    }

    @Override
    public void showDetails(boolean show) {
        if (show) {
            LAYOUT_OBJs.layoutTop.setVisibility(View.VISIBLE);

        } else {
            LAYOUT_OBJs.layoutTop.setVisibility(View.GONE);
        }
    }

    @Override
    protected Integer getHelpScreen() {
        return null;
    }

    protected abstract String getURL();

    protected abstract String getTitile();

    @Override
    public void onStop() {
        super.onStop();
        CookieSyncManager.getInstance().startSync();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateViewImpl(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState, Bundle p) {

        View rootView = inflater.inflate(R.layout.fragment_webpage, container, false);
        currentUrl = getURL();

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_URL)) {
            currentUrl = savedInstanceState.getString(KEY_URL);
        }

        //ON CREATE method
        //**************************
        LAYOUT_OBJs = new LayoutObjs_fragment_webpage_xml(rootView);
        //**************************
        //**************************

        final CookieSyncManager instance = CookieSyncManager.createInstance(this.getMainActivity());
        CookieSyncManager.getInstance().startSync();
        final WebView webview = LAYOUT_OBJs.webViewHtml;

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.setInitialScale(1);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        final Activity activity = getMainActivity();
        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                super.onProgressChanged(view, progress);
                LAYOUT_OBJs.progressBar3.setProgress(progress);
                if (progress >= 100) {
                    //LAYOUT_OBJs.progressBarLoad.setVisibility(View.INVISIBLE);
                    LAYOUT_OBJs.textViewTitolo.setText(getTitile());
                    LAYOUT_OBJs.progressBar3.setVisibility(View.GONE);
                    LAYOUT_OBJs.imageViewBack.setVisibility(View.VISIBLE);

                } else {
                    //LAYOUT_OBJs.progressBarLoad.setVisibility(View.VISIBLE);
                    LAYOUT_OBJs.textViewTitolo.setText("Caricamento in corso...");
                    LAYOUT_OBJs.progressBar3.setVisibility(View.VISIBLE);
                    LAYOUT_OBJs.imageViewBack.setVisibility(View.GONE);
                }

            }
        });
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.endsWith(".pdf")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    // if want to download pdf manually create AsyncTask here
                    // and download file
                    return true;
                }
                // view.loadUrl(url);
                return false;
            }
        });

        LAYOUT_OBJs.webViewHtml.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {

            }
        });

        LAYOUT_OBJs.imageViewBack.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                if (LAYOUT_OBJs.webViewHtml.canGoBack()) {
                    LAYOUT_OBJs.webViewHtml.goBack();
                    //Toast.makeText(getMainActivity(),"Torno alla pagina precedente",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getMainActivity(), "Impossibile tornare ancora indietro", Toast.LENGTH_SHORT).show();
                }

            }
        });

        LAYOUT_OBJs.imageViewOpen.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(currentUrl));
                getMainActivity().startActivity(i);
            }
        });

        webview.loadUrl(currentUrl);


        //Ist.+Tec.+Stat.+E.+Fermi/@41.956178,12.806626
        return rootView;
    }

    @Override
    public void onSaveInstanceStateImpl(Bundle outState) {
        currentUrl = LAYOUT_OBJs.webViewHtml.getUrl();
        outState.putString(KEY_URL, currentUrl);
    }
}
