package com.sr7d.shubhamraja.StColumbasCollege;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Shubham Raja on 12/29/2016.
 */

public class Contact extends AppCompatActivity {
    RelativeLayout cnt;
    TextView contactDetails;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        cnt = (RelativeLayout)findViewById(R.id.contactLayout);
        contactDetails = (TextView)findViewById(R.id.textContact);
        cnt.setVisibility(View.VISIBLE);

        contactDetails.setText("College Address\n" +
                "St Columba's College\n" +
                "Situated in the heart of Hazaribag\n" +
                "Jharkhand, India\n" +
                "\n" +
                "Contact Details\n" +
                "Phone : +916546222197 (O)\n" +
                "Phone : +916546223227 (Exam)\n" +
                "Email : info@stcchazaribag.org\n" +
                "www.stcchazaribag.org");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.GONE);
    }
}
