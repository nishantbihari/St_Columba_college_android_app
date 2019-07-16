package com.sr7d.shubhamraja.StColumbasCollege;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.ads.AdView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String uname;
    String rigntone;
    NotificationManager notificationManager;

    ImageView pic;
    private AdView mAdView;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //preference Notification
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
         rigntone = sp.getString("ring1", "default ringtone");
         uname = sp.getString("name1", "Guest");
      //  Toast.makeText(MainActivity.this,"Logged in as : "+ uname,Toast.LENGTH_SHORT).show();
          showNotification();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();


                Intent i = new Intent(getApplicationContext(),Selection.class);
                startActivity(i);
            }
        });

        FloatingActionButton fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Gestures.class);
                startActivity(i);
            }
        });
        fab1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getApplicationContext(),Reachus.class);
                startActivity(i);
                return false;
            }
        });

        FirstFragment fragment = new FirstFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        pic = (ImageView)findViewById(R.id.imgLog);
//        storageReference = FirebaseStorage.getInstance().getReference();
//        StorageReference riversRef = storageReference.child("images/profile.jpg");
//        Context context = navigationView.getContext();
//        Glide.with(context /* context */).using(new FirebaseImageLoader()).load(riversRef).into(pic);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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
           AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("About App")
                    .setMessage(R.string.about_app)
                            .create()
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            // Handle the camera action
//            FragAbout fragment = new FragAbout();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container,fragment);
//            fragmentTransaction.commit();

            Intent i = new Intent(this,Home.class);
            i.putExtra("id","1");
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
//            SecondFragment fragment = new SecondFragment();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container,fragment);
//            fragmentTransaction.commit();

            Intent i = new Intent(this,Gallery.class);
            startActivity(i);

        } else if (id == R.id.nav_details) {
//            ThirdFragment fragment = new ThirdFragment();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container,fragment);
//            fragmentTransaction.commit();
            Intent i = new Intent(this,Website.class);
            startActivity(i);

        }
        else if (id == R.id.feedBack){
            Intent i = new Intent(this,Feed_back.class);
            startActivity(i);
        }
        else if (id == R.id.nav_notice){
            Intent i = new Intent(this,Notice.class);
            startActivity(i);
        }
        else if (id == R.id.nav_share){
            String msg = "Download this app form : www.googleplay.app/STCCHZB";

            Intent shareIntent = new Intent(Intent.ACTION_SEND);

            shareIntent.setType("text/plain");

            shareIntent.putExtra(Intent.EXTRA_TEXT, msg);

            startActivity(Intent.createChooser(shareIntent, "Share Via"));

        }
        else if(id == R.id.nav_register){
            Intent i = new Intent(this,Login.class);
            startActivity(i);
        }
        else if(id == R.id.nav_personalize){
            Intent i = new Intent(this,User.class);
            startActivity(i);
        }
        else if(id == R.id.nav_chat){
            Intent i = new Intent(this,Chat.class);
            startActivity(i);
        }
        else if(id == R.id.nav_status){
            Intent i = new Intent(this,Status.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void showNotification() {
        PendingIntent pi = PendingIntent.getActivity(this, 1, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Android")
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle("SCC App")
                .setSmallIcon(R.drawable.ic_scc)
                .setSound(Uri.parse(rigntone))
                .setContentText("College App Running")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationManager.cancel(1);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//    }
}
