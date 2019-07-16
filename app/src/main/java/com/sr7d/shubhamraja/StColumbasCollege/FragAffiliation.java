package com.sr7d.shubhamraja.StColumbasCollege;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Shubham Raja on 2/25/2017.
 */

public class FragAffiliation extends Fragment {
    public FragAffiliation(){

    }
    FloatingActionButton fab;
    TextView txt;
    TextToSpeech tts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.frag_cont,container,false);

        txt =(TextView)v.findViewById(R.id.txt);
        txt.setScrollContainer(true);
        txt.setMovementMethod(new ScrollingMovementMethod());
        txt.setText("The St. Columba’s College is affiliated to the Vinoba Bhave University (VBU), which is a permanent member of the Association of Indian Universities, Commonwealth Universities (London) and is registered under section 2F and 12B of UGC Act 1956.\n" +
                    "\n" +
                    "The VBU was established as teaching cum affiliating university on 17th September 1992, after the bifurcation of Ranchi University. It is located in Hazaribag, a town of sylvan surrounding and tranquility. The post graduate campus sprawls over 70 acres of land on the Patna-Ranchi National Highway, about 3kms from North of the town.\n" +
                    "\n" +
                    "The VBU has 19 post graduate departments, 19 constituent colleges and more than 40 affiliated colleges including Medical, Engineering, Ayurveda, Law, Education, Homeopathy and Sanskrit colleges.\n");

        tts = new TextToSpeech(getContext(),null);
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
