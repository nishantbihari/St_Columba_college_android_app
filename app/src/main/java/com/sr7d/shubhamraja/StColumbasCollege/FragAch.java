package com.sr7d.shubhamraja.StColumbasCollege;

import android.os.Bundle;
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

public class FragAch extends Fragment {
    public FragAch(){

    }
    TextView txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.frag_cont,container,false);
        FloatingActionButton fab = (FloatingActionButton)getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        txt = (TextView)v.findViewById(R.id.txt);
        txt.setScrollContainer(true);
        txt.setMovementMethod(new ScrollingMovementMethod());
        txt.setText("S. No.\t \t" +
                "Name\t \t" +
                "Subject\t \t" +
                "Session\t \t" +
                "Achievement\n" +
                "1\n" +
                "Naina Kumari\n" +
                "Intermediate Arts\n" +
                "2010 – 2012\n" +
                "College Topper (337/500)\n" +
                "2\n" +
                "Shashi Kant Mehta\n" +
                "Intermediate Science\n" +
                "2010 – 2012\n" +
                "College Topper (378/500)\n" +
                "3\n" +
                "Pallavi\n" +
                "English Hons.\n" +
                "2009 – 2012\n" +
                "College Topper (531/800)\n" +
                "4\n" +
                "Abhishek Kumar Das\n" +
                "Economics Hons.\n" +
                "2009 – 2012\n" +
                "College Topper (560/800)\n" +
                "5\n" +
                "Rajendra Thakur\n" +
                "Geography Hons.\n" +
                "2009 – 2012\n" +
                "College Topper (550/800)\n" +
                "6\n" +
                "Dipincy Anjalo\n" +
                "History Hons.\n" +
                "2009 – 2012\n" +
                "College Topper (527/800)\n" +
                "7\n" +
                "Santosh Kumar Mandal\n" +
                "Hindi Hons.\n" +
                "2009 – 2012\n" +
                "College Topper (560/800)\n" +
                "8\n" +
                "Rajesh Ranjan\n" +
                "Philosophy Hons.\n" +
                "2009 – 2012\n" +
                "1st Class with Distinction (608/800)\n" +
                "9\n" +
                "Birendra Patel\n" +
                "Pol. Science Hons.\n" +
                "2009 – 2012\n" +
                "College Topper (526/800)\n" +
                "10\n" +
                "Sandhya Kumari\n" +
                "Psychology Hons.\n" +
                "2009 – 2012\n" +
                "College Topper (567/800)");

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        FloatingActionButton fab = (FloatingActionButton)getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

    }
}
