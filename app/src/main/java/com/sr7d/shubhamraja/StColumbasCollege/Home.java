package com.sr7d.shubhamraja.StColumbasCollege;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.ads.AdView;

public class Home extends AppCompatActivity {

    TextToSpeech tts;
    FloatingActionButton fab;
    TextView txt;

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;


    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("St. Columba's College");

        tts = new TextToSpeech(Home.this,null);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt = (TextView)findViewById(R.id.txt);
//        txt.setScrollContainer(true);
//        txt.setMovementMethod(new ScrollingMovementMethod());

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.frame_container, new TabFragment()).commit();

//        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3293121401536725/2879322092");

        
         fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                String data = txt.getText().toString();
//                if(tts!=null){
//                    tts.shutdown();
//                }
//                if(tts.isSpeaking())
//                {
//                   // tts.shutdown();
//                    tts.speak("  ", TextToSpeech.QUEUE_FLUSH, null);
//                    fab = (FloatingActionButton) findViewById(R.id.fab);
//                    fab.setImageResource(R.drawable.ic_action_speak);
//                }
//                else
//                {
//                    String data = txt.getText().toString();
//                    fab.setImageResource(R.drawable.ic_action_pause);
//                    tts.setPitch(-100);
//                    tts.setSpeechRate(0);
//                    tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);
//                }
//                tts.setPitch(-100);
//                tts.setSpeechRate(0);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent i = new Intent(this,MyPref.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_login) {

            Intent i = new Intent(this,User.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.item1) {
            new AlertDialog.Builder(Home.this)
                    .setTitle("About App")
                    .setMessage(R.string.about_app)
                    .show();
        }
        if (id == R.id.item2) {
            startActivity(new Intent(getApplicationContext(),Me.class));

        }
        if (id == R.id.item3) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        AdRequest adRequest;
//        mAdView = (AdView) findViewById(R.id.adView);
//         adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//    }
}


