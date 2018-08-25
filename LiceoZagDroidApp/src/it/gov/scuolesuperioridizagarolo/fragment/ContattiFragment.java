package it.gov.scuolesuperioridizagarolo.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import it.gov.scuolesuperioridizagarolo.R;
import it.gov.scuolesuperioridizagarolo.api.AbstractFragment;
import it.gov.scuolesuperioridizagarolo.layout.LayoutObjs_fragment_contatti_xml;
import it.gov.scuolesuperioridizagarolo.listener.OnClickListenerViewErrorCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContattiFragment extends AbstractFragment {


    private LayoutObjs_fragment_contatti_xml LAYOUT_OBJs;   //***************************

    public ContattiFragment() {
    }

    @Override
    public View onCreateViewImpl(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState, Bundle p) {

        View rootView = inflater.inflate(R.layout.fragment_contatti, container, false);

        //ON CREATE method
        //**************************
        LAYOUT_OBJs = new LayoutObjs_fragment_contatti_xml(rootView);
        //**************************
        //**************************
        LAYOUT_OBJs.imageButtonWWW.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            public void onClickImpl(View v) {
                String url = getResources().getString(R.string.url_website_scuola);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });




        LAYOUT_OBJs.imageButtonEmail.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            public void onClickImpl(View v) {

                String mailto = "mailto:" + getResources().getString(R.string.email_scuola) +
                        "?subject=" + Uri.encode("Informazioni") +
                        "&body=" + Uri.encode("Scrivi il tuo messaggio");

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));
                startActivity(emailIntent);
            }
        });

        LAYOUT_OBJs.imageButtonTelefono.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            public void onClickImpl(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + Uri.encode(getResources().getString(R.string.tel_scuola).trim())));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(callIntent);
            }
        });

        LAYOUT_OBJs.imageButtonMap.setOnClickListener(new OnClickListenerViewErrorCheck(getMainActivity()) {
            @Override
            public void onClickImpl(View v) {
                Intent callIntent = new Intent(Intent.ACTION_VIEW);
                callIntent.setData(Uri.parse(getResources().getString(R.string.intent_maps).trim()));
                startActivity(callIntent);
            }
        });


        final String text = getMainActivity().getResources().getString(R.string.contatto_scuola);
        final String textNorm = text.replaceAll("#T#", "").replaceAll("#I#", "").replaceAll("#E#", "");

        final List<String> numeriTelefono = extract(text, Pattern.compile("#T#([0-9]+)#T#"));
        final List<String> email = extract(text, Pattern.compile("#E#(.)+#E#"));

        LAYOUT_OBJs.textView2.setText(textNorm);


        for (String num : numeriTelefono) {
            Pattern pattern2 = Pattern.compile(num);
            //LAYOUT_OBJs.textView2.setText("press one of these words to search it on google: Android Linkify dzone");
            final Linkify.MatchFilter mf = new Linkify.MatchFilter() {
                @Override
                public boolean acceptMatch(CharSequence s, int start, int end) {
                    return true;
                }
            };
            Linkify.addLinks(LAYOUT_OBJs.textView2, pattern2, "tel:", mf, null);

        }


        for (String e : email) {
            Pattern pattern2 = Pattern.compile(e);
            //LAYOUT_OBJs.textView2.setText("press one of these words to search it on google: Android Linkify dzone");
            final Linkify.MatchFilter mf = new Linkify.MatchFilter() {
                @Override
                public boolean acceptMatch(CharSequence s, int start, int end) {
                    return true;
                }
            };
            Linkify.addLinks(LAYOUT_OBJs.textView2, pattern2, "mailto:", mf, null);

        }


        //Ist.+Tec.+Stat.+E.+Fermi/@41.956178,12.806626
        return rootView;
    }

    private List<String> extract(String s, Pattern pattern2) {
        final Matcher matcher = pattern2.matcher(s);
        List<String> ris = new ArrayList<>();
        while (matcher.find()) {
            final String group = matcher.group();
            ris.add(group.replaceAll("#T#", "").replaceAll("#I#", "").replaceAll("#E#", ""));
        }
        return ris;
    }


}
