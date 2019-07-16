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
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Shubham Raja on 12/29/2016.
 */

public class Department extends AppCompatActivity {
    GridView simpleList;
    ArrayList<Item> departList=new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection);
        setTitle("Departments");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        simpleList = (GridView) findViewById(R.id.gridView);
        departList.add(new Item("Science",R.drawable.department));
        departList.add(new Item("Arts",R.drawable.department));
        departList.add(new Item("Humanities",R.drawable.department));
        departList.add(new Item("Vocational",R.drawable.department));

        MyAdapter myAdapter=new MyAdapter(this,R.layout.model,departList);
        simpleList.setAdapter(myAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i;
                switch (arg2){
                    case 0:
                        i = new Intent(Department.this,Department_content.class);
                        i.putExtra("choice",0);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(Department.this,Department_content.class);
                        i.putExtra("choice",1);
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(Department.this,Department_content.class);
                        i.putExtra("choice",2);
                        startActivity(i);
                        break;
                    case 3:
                        i = new Intent(Department.this,Department_content.class);
                        i.putExtra("choice",3);
                        startActivity(i);
                        break;


                }
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
            new AlertDialog.Builder(Department.this)
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
