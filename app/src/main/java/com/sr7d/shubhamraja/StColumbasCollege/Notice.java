package com.sr7d.shubhamraja.StColumbasCollege;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notice extends AppCompatActivity {

    TextView textViewNotice;
    LinearLayout editNotice;
    EditText editDate,editMsg;
    Button done;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mNoticeRef = mRootRef.child("notice");

    String rigntone;
    NotificationManager notificationManager;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        textViewNotice = (TextView)findViewById(R.id.textNotice);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            new AlertDialog.Builder(Notice.this)
                    .setTitle("You are not Logged In")
                    .setMessage("Do You want to LogIn")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            startActivity( new Intent(getApplicationContext(),MainActivity.class));
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }
                    })
                    .create()
                    .show();
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Notice.this);
        rigntone = sp.getString("ring1", "default ringtone");

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab);
//        fab2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (editNotice.getVisibility() == View.GONE) {
//                    editNotice.setVisibility(View.VISIBLE);
//                    textViewNotice.setVisibility(View.GONE);
//                } else {
//                    editNotice.setVisibility(View.GONE);
//                    textViewNotice.setVisibility(View.VISIBLE);
//                }
//
//            }
//        });
        fab2.setVisibility(View.GONE);
        mNoticeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String date = dataSnapshot.child("date").getValue().toString();
                String msg = dataSnapshot.child("notice").getValue().toString();
                textViewNotice.setText(date+"\n"+msg);
                showNotification();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    public void showNotification() {
        PendingIntent pi = PendingIntent.getActivity(this, 1, new Intent(this, Notice.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Android")
                .setContentTitle("New Notice")
                .setSmallIcon(R.drawable.ic_scc)
                .setSound(Uri.parse(rigntone))
                .setContentText("click here to see the notice")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}
