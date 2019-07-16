package com.sr7d.shubhamraja.StColumbasCollege;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Shubham Raja on 2/25/2017.
 */

public class FragAbout extends Fragment {
    public FragAbout(){

    }
    FloatingActionButton fab;
    TextView txt;
    TextToSpeech tts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.frag_cont,container,false);

         tts = new TextToSpeech(getContext(),null);
         txt = (TextView)v.findViewById(R.id.txt);
        txt.setScrollContainer(true);
        txt.setMovementMethod(new ScrollingMovementMethod());
        txt.setText("A scion of Dublin University Mission, Ireland, since 1899, has a glorious trajectory of excellence in imparting higher education, running a long gamut of 110 years towards the fulfillment of great tasks and realization of noble ideals, blazing the trail for great evocative innovations in the field of education, with distinction of being premier Degree College of Eastern India.\n" +
                "\n" +
                "The college started off with its affiliation from Calcutta University in 1899 as Grade ‘B’ College. But it was soon upgraded in 1904 to Grade ‘A’ College on the merit of its spectacular performance and excellence. In the year 1906-1907, it was anointed as “St. Columba’s College”, the present name, after the name of the famous Irish Saint Columba.\n" +
                "\n" +
                "In 1952, the college became a part of Bihar University and 12 years hence in 1964, St. Columba’s College became a constituent unit of Ranchi University, which came into existence in 1960. Continuing its unending glorious journey, it turned into a glaring constituent unit of Vinoba Bhave University, Hazaribag, Jharkhand, in 1992. Meanwhile the college took a leaf of Post Graduate affiliation from University Grants Commission, New Delhi, in 1987.");


         fab = (FloatingActionButton)getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tts.isSpeaking())
                {
                    // tts.shutdown();
                    tts.speak("  ", TextToSpeech.QUEUE_FLUSH, null);
                    fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
                    fab.setImageResource(R.drawable.ic_action_speak);
                }
                else
                {
                     String data = txt.getText().toString();
                    fab.setImageResource(R.drawable.ic_action_pause);
                    tts.setPitch(-100);
                    tts.setSpeechRate(0);
                    tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);
                }
                tts.setPitch(-100);
                tts.setSpeechRate(0);
            }
        });
        return v;
    }
}
