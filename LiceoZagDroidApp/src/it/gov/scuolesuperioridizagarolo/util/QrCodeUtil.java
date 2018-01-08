package it.gov.scuolesuperioridizagarolo.util;

import android.content.Context;
import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import it.gov.scuolesuperioridizagarolo.R;

/**
 * Created by stefano on 06/01/2018.
 */
public class QrCodeUtil {
    public static Bitmap createQrCode(Context c, String text,int size) {
        BitMatrix bitMatrix;
        //final int size = 400;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    text,
                    BarcodeFormat.QR_CODE,
                    size, size, null
            );

        } catch (WriterException e) {
            throw new IllegalArgumentException(e);
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];
        final int black = c.getResources().getColor(R.color.color_black);
        final int white = c.getResources().getColor(R.color.color_white);


        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ?
                        black : white;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, size, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
