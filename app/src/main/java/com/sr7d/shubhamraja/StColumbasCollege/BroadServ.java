package com.sr7d.shubhamraja.StColumbasCollege;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class BroadServ extends AppCompatActivity {

    private String message;
    private TextToSpeech tts;
    FloatingActionButton fab;
    TextView txtMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_serv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Notification");
        txtMsg = (TextView)findViewById(R.id.textNotification);
        message = getIntent().getExtras().get("message").toString();
        txtMsg.setText(message);
        tts = new TextToSpeech(BroadServ.this,null);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                String data = txtMsg.getText().toString();
                if(tts!=null){
                    tts.shutdown();
                }
                if(tts.isSpeaking())
                {
                   // tts.shutdown();
                    tts.speak("  ", TextToSpeech.QUEUE_FLUSH, null);
                    fab = (FloatingActionButton) findViewById(R.id.fab);
                    fab.setImageResource(R.drawable.ic_action_speak);
                }
                else
                {
                    data = txtMsg.getText().toString();
                    fab.setImageResource(R.drawable.ic_action_pause);
                    tts.setPitch(-100);
                    tts.setSpeechRate(0);
                    tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);
                }
                tts.setPitch(-100);
                tts.setSpeechRate(0);
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
            new AlertDialog.Builder(BroadServ.this)
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
}
