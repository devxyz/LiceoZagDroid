package it.gov.scuolesuperioridizagarolo.dialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.Toast;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.api.AbstractActivity;
import it.gov.scuolesuperioridizagarolo.api.AbstractDialog;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_dialog_view_html_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;
import it.gov.scuolesuperioridizagarolo.util.ScreenUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stefano on 25/02/15.
 */

public class HtmlPageDialog extends AbstractDialog {
    private LayoutObjs_dialog_view_html_xml LAYOUT_OBJs;

    public HtmlPageDialog(final AbstractActivity context, String title, final String htmlText, final String url) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_view_html);


        //**************************
        LAYOUT_OBJs = new LayoutObjs_dialog_view_html_xml(this);
        //**************************
        if (htmlText == null && url == null) {
            throw new NullPointerException("Specificare url o testo");
        }


        CookieSyncManager.createInstance(this.getMainActivity());
        CookieSyncManager.getInstance().startSync();
        final WebView webview = LAYOUT_OBJs.textViewDescrizione;

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.setInitialScale(1);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);


        setCancelable(true);
        setCanceledOnTouchOutside(true);
        //imageLoader.clearCache();

        if (htmlText != null) {
            LAYOUT_OBJs.textViewTitle.setText(title);
            LAYOUT_OBJs.textViewDescrizione.loadData(htmlText, "text/html", "UTF-8");
        } else {
            LAYOUT_OBJs.textViewTitle.setText(title);
            LAYOUT_OBJs.textViewDescrizione.loadUrl(url);

        }

        LAYOUT_OBJs.buttonOK.setOnClickListener(new OnClickListenerViewErrorCheck(context.getActivity()) {
            @Override
            public void onClickImpl(View v) {
                dismiss();
            }
        });

        LAYOUT_OBJs.buttonSALVA.setOnClickListener(new OnClickListenerViewErrorCheck(context.getActivity()) {
            @Override
            public void onClickImpl(View v) throws Throwable {
                final File downloadFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                SimpleDateFormat s = new SimpleDateFormat("yyyy.MM.dd HH_mm_ss");
                File f = new File(downloadFolder, "file_" + s.format(new Date()) + ".html");
                BufferedWriter out = new BufferedWriter(new FileWriter(f));
                out.write(htmlText);
                out.close();
                Toast.makeText(context, "File salvato in " + f.getName(), Toast.LENGTH_SHORT).show();

            }
        });

        if (url != null)
            LAYOUT_OBJs.buttonClose.setVisibility(View.VISIBLE);
        else
            LAYOUT_OBJs.buttonClose.setVisibility(View.GONE);

        LAYOUT_OBJs.buttonClose.setOnClickListener(new OnClickListenerViewErrorCheck(context.getActivity()) {
            @Override
            public void onClickImpl(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                getOwnerActivity().startActivity(intent);
                // dismiss();
            }
        });


        ScreenUtil.fullScreen(this);


    }


}
