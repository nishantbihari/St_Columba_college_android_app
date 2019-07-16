package com.sr7d.shubhamraja.StColumbasCollege;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Shubham Raja on 12/30/2016.
 */

public class Program_content extends AppCompatActivity {
    ListView simpleList;
    ArrayList<Item> programListContent=new ArrayList();
    int choice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        simpleList = (ListView) findViewById(R.id.listView);

        choice = getIntent().getExtras().getInt("choice");
        switch (choice) {
            case 0:
                programListContent.add(new Item("Intermediate of Science",R.drawable.program));
                programListContent.add(new Item("Intermediate of Arts",R.drawable.program));
                break;
            case 1:
                programListContent.add(new Item("Bachelor of Science(Honours)",R.drawable.program));
                programListContent.add(new Item("Bachelor of Arts(Honours)",R.drawable.program));
                programListContent.add(new Item("Bachelor of Arts(General)",R.drawable.program));
                programListContent.add(new Item("Bachelor of Science(General)",R.drawable.program));
                break;
            case 2:
                programListContent.add(new Item("Post-Graduation Courses",R.drawable.program));
                break;
            case 3:
                programListContent.add(new Item("Bachelor of Education(B.Ed.)",R.drawable.program));
                break;
            case 4:
                programListContent.add(new Item("Bachelor of Computer Application(BCA)",R.drawable.program));
                programListContent.add(new Item("Bachelor of Bio-Technology(Bio-Tech)",R.drawable.program));
                break;

        }
        MyAdapter2 myAdapter2=new MyAdapter2(this,R.layout.princi_model,programListContent);
        simpleList.setAdapter(myAdapter2);
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
            new AlertDialog.Builder(Program_content.this)
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
