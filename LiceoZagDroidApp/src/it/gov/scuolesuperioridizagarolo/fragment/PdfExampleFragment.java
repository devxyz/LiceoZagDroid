package it.gov.scuolesuperioridizagarolo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_pdf_example_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;

import java.io.InputStream;

public class PdfExampleFragment extends AbstractFragment {


    private LayoutObjs_fragment_pdf_example_xml LAYOUT_OBJs;   //***************************

    public PdfExampleFragment() {
    }

    @Override
    public View onCreateViewImpl(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState, Bundle p) {

        View rootView = inflater.inflate(R.layout.fragment_pdf_example, container, false);
        LAYOUT_OBJs = new LayoutObjs_fragment_pdf_example_xml(rootView);

        LAYOUT_OBJs.button.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            protected void onClickImpl(View v) throws Throwable {
                InputStream in = getResources().openRawResource(R.raw.prova);

                PdfReader reader = new PdfReader(in);
                PdfReaderContentParser parser = new PdfReaderContentParser(reader);
                StringBuffer sb = new StringBuffer();
                TextExtractionStrategy ex;
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    ex = parser.processContent(i, new SimpleTextExtractionStrategy());
                    sb.append(ex.getResultantText());
                }
                reader.close();
                in.close();
                LAYOUT_OBJs.messaggio2.setText(sb.toString());

            }
        });

        return rootView;
    }


}
