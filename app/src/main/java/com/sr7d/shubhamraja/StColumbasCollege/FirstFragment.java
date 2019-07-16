package com.sr7d.shubhamraja.StColumbasCollege;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment{

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myView =  inflater.inflate(R.layout.fragment_first, container, false);
        ImageView bck = (ImageView)myView.findViewById(R.id.imgWal);
        int[] rse = new int[] {R.drawable.princi18,R.drawable.homescreen1,R.drawable.coleg,R.drawable.back,R.drawable.bck,R.drawable.screen,R.drawable.volleymen,R.drawable.balsir2,R.drawable.balsir3,R.drawable.colgevent};
        Random rnd = new Random();
        int r = rnd.nextInt(rse.length);
        bck.setImageDrawable(getResources().getDrawable(rse[r]));

        return myView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
