package it.gov.scuolesuperioridizagarolo.dialog;

import android.util.DisplayMetrics;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.api.AbstractActivity;
import it.gov.scuolesuperioridizagarolo.api.AbstractDialog;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_dialog_prodotti_bar_qrcode_xml;
import it.gov.scuolesuperioridizagarolo.util.QrCodeUtil;

/**
 * Created by stefano on 06/01/2018.
 */
public class DialogProdottiBarQrCode extends AbstractDialog {

    private LayoutObjs_dialog_prodotti_bar_qrcode_xml LAYOUT_OBJs;

    public DialogProdottiBarQrCode(AbstractActivity context, String qrcodeString) {
        super(context);
        setContentView(R.layout.dialog_prodotti_bar_qrcode);
        LAYOUT_OBJs = new LayoutObjs_dialog_prodotti_bar_qrcode_xml(this);

        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        LAYOUT_OBJs.image_qrcode.setImageBitmap(QrCodeUtil.createQrCode(context, qrcodeString, Math.min(height, width)-10));
        show();
    }
}
