package it.gov.scuolesuperioridizagarolo.fragment.api;

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
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    protected Integer getHelpScreen() {
        return null;
    }

    protected abstract String getURL();

    protected abstract String getTitile();

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        CookieSyncManager.createInstance(this.getMainActivity());
        CookieSyncManager.getInstance().startSync();
        final WebView webview = LAYOUT_OBJs.webViewHtml;

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.setInitialScale(1);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);


        final Activity activity = getMainActivity();
        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

                LAYOUT_OBJs.progressBarLoad.setProgress(progress);
                if (progress >= 100) {
                    LAYOUT_OBJs.progressBarLoad.setVisibility(View.GONE);
                    LAYOUT_OBJs.textViewTitolo.setText(getTitile());
                } else {
                    LAYOUT_OBJs.progressBarLoad.setVisibility(View.VISIBLE);
                    LAYOUT_OBJs.textViewTitolo.setText("Caricamento in corso...");
                }

            }
        });
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
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
    public void onSaveInstanceState(Bundle outState) {
        currentUrl = LAYOUT_OBJs.webViewHtml.getUrl();
        outState.putString(KEY_URL, currentUrl);
    }
}
