package com.sr7d.shubhamraja.StColumbasCollege;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    private static final String[] TEXTS = { "Image #1", "Image #2", "Image #3" };
    private static final int[] IMAGES = { R.drawable.coleg, R.drawable.department,R.drawable.program
    };
    private int mPosition = 0;
    private TextSwitcher mTextSwitcher;
    private ImageSwitcher mImageSwitcher;


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mTextSwitcher = (TextSwitcher)getView().findViewById(R.id.textSwitcher);
        mImageSwitcher = (ImageSwitcher)getView().findViewById(R.id.imageSwitcher);

        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory()
        {
            @Override
            public View makeView() {
                TextView textView = new TextView(getActivity());
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });

        mTextSwitcher.setInAnimation(getContext(), android.R.anim.slide_in_left);
        mTextSwitcher.setOutAnimation(getContext(), android.R.anim.slide_out_right);


        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getActivity());
                return imageView;
            }
        });
        mImageSwitcher.setInAnimation(getContext(), android.R.anim.slide_in_left);
        mImageSwitcher.setOutAnimation(getContext(), android.R.anim.slide_out_right);

        onSwitch(null);



    return inflater.inflate(R.layout.fragment_second, container, false);
    }
    public void onSwitch(View view)
    {
        mTextSwitcher.setText(TEXTS[mPosition]);
        mImageSwitcher.setBackgroundResource(IMAGES[mPosition]);
        mPosition = (mPosition + 1) % TEXTS.length;
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

