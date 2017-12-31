package it.gov.scuolesuperioridizagarolo.util;

import android.content.Context;
import android.content.Intent;
import android.text.Html;

/**
 * Created by stefano on 31/12/2017.
 */
public class ShareUtil {

    public static void viewHtml(Context c, String titolo, String html) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/html");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(html));
        c.startActivity(Intent.createChooser(sharingIntent, titolo));
    }
}
