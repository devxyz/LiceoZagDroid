package it.gov.scuolesuperioridizagarolo.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_barcode_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;

/**
 * Created by stefano on 01/01/2018.
 */
public class BarCodeFragment extends AbstractFragment {
    private LayoutObjs_fragment_barcode_xml LAYOUT_OBJs;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {

            Toast.makeText(getMainActivity(), "OK " + intentResult.getContents(), Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public View onCreateViewImpl(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState, Bundle p) {
        View rootView = inflater.inflate(R.layout.fragment_barcode, container, false);

        LAYOUT_OBJs = new LayoutObjs_fragment_barcode_xml(rootView);

        LAYOUT_OBJs.button3.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {

            @Override
            protected void onClickImpl(View v) throws Throwable {





                BitMatrix bitMatrix;
                try {
                    bitMatrix = new MultiFormatWriter().encode(
                            "Ciao a tutti jhgs adjdg aksjdgsa dashkgd sadksahjdg sakdjhsag dahksgd sadghsa dkaghs dksajdgsakjdagsdags",
                            BarcodeFormat.QR_CODE,
                            500, 500, null
                    );

                } catch (IllegalArgumentException Illegalargumentexception) {
                    Illegalargumentexception.printStackTrace();
                    return;
                }
                int bitMatrixWidth = bitMatrix.getWidth();

                int bitMatrixHeight = bitMatrix.getHeight();

                int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];
                final int black = getResources().getColor(R.color.color_black);
                final int white = getResources().getColor(R.color.color_white);


                for (int y = 0; y < bitMatrixHeight; y++) {
                    int offset = y * bitMatrixWidth;

                    for (int x = 0; x < bitMatrixWidth; x++) {
                        pixels[offset + x] = bitMatrix.get(x, y) ?
                                black : white;
                    }
                }
                Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

                bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);

                LAYOUT_OBJs.imageView2.setImageBitmap(bitmap);
            }
        });


        LAYOUT_OBJs.button2.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {


                IntentIntegrator integrator = new IntentIntegrator(BarCodeFragment.this);
                integrator.setMessage("Avvia riconoscimento");


                integrator.initiateScan();
            }
        });


        return rootView;
    }


}
