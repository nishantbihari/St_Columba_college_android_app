package com.sr7d.shubhamraja.StColumbasCollege;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Shubham Raja on 12/28/2016.
 */

public class Feed_back extends AppCompatActivity {
    EditText name,roll,session,suggestion;
    Button send_feed;
    RatingBar ratingBar;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    String sname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_back);
        setTitle("Feed Back Form");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (EditText)findViewById(R.id.editText);
        roll = (EditText)findViewById(R.id.editText2);
        session = (EditText)findViewById(R.id.editText3);
        suggestion = (EditText)findViewById(R.id.editText4);
        send_feed = (Button)findViewById(R.id.button);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(),Register.class));

        } else {

            FirebaseUser user = firebaseAuth.getCurrentUser();
            sname = user.getDisplayName();
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();


            send_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AlertDialog.Builder alert= new AlertDialog.Builder(Feed_back.this);
                        alert.setTitle("Your Feed")
                        .setMessage("Name : "+name.getText().toString() + "\n"+
                                    "Roll no : "+roll.getText().toString()+ "\n"+
                                    "Session : "+ session.getText().toString() + "\n"+
                                    "Suggestion : "+suggestion.getText().toString()+"\n"+
                                    "Rating : "+ratingBar.getRating())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Feed_back.this, "Feed Back Sent", Toast.LENGTH_SHORT).show();
                                String sroll = roll.getText().toString();
                                String ssession = session.getText().toString();
                                String ssuggestion = suggestion.getText().toString();
                                String srating = String.valueOf( ratingBar.getRating());
                                SaveFeedBack saveFeedBack = new SaveFeedBack(sname,sroll,ssession,ssuggestion,srating);
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                databaseReference.child("feed_by_"+user.getUid()).setValue(saveFeedBack);
                                Toast.makeText(Feed_back.this, "feedback sent", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create()
                        .show();
            }

        });
    }

//    private void send_feed(){
//        String sroll = roll.getText().toString();
//        String ssession = session.getText().toString();
//        String ssuggestion = suggestion.getText().toString();
//        String srating = String.valueOf( ratingBar.getRating());
//        SaveFeedBack saveFeedBack = new SaveFeedBack(sname,sroll,ssession,ssuggestion,srating){
//            FirebaseUser user = firebaseAuth.getCurrentUser();
//            databaseReference.child(user.getUid()).setValue(saveFeedBack);
//
//        };
//    }

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
            i.putExtra("id","1");
            startActivity(i);
            return true;
        }
        if (id == R.id.action_login) {

            Intent i = new Intent(this,User.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.item1) {
            new AlertDialog.Builder(getApplicationContext())
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
