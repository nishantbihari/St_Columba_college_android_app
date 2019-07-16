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

public class FragHistory extends Fragment {
    public FragHistory(){

    }
    FloatingActionButton fab;
    TextView txt;
    TextToSpeech tts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.frag_cont,container,false);

        txt = (TextView)v.findViewById(R.id.txt);
        txt.setScrollContainer(true);
        txt.setMovementMethod(new ScrollingMovementMethod());
        txt.setText("The college since its inception in 1899 has created new milestones in scholastic and non-scholastic fields. It has completed 114 years of its service for the all-round development of the masses in the area. The joint effort of the Principal, Professors, Non-teaching staff, past and present students, has resulted in a new model of collaboration.\n" +
                    "\n" +
                    "The entrants as students remain for a short span but continue to be associated with the college for the rest of their lives as alumni. They are reminded of their duty and commitment to the society as ‘Ladies and gentlemen in the service of mankind’ which is the motto of ASCA. Association of St. Columban Alumni (ASCA) gives them a platform to serve the society by their constructive and collaborative role. It will provide an opportunity to meet the old ones and make new ones when they gather together in the alumni movement. We hope and pray for all the best for the alumni.\n");

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
