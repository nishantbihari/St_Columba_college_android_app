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
 * Created by Shubham Raja on 12/29/2016.
 */

public class Principal extends AppCompatActivity {
    ListView simpleList;
    ArrayList<Item> principalList=new ArrayList();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Principal List");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        simpleList = (ListView) findViewById(R.id.listView);
        principalList.add(new Item("Rev. J. A. Murray \t1899 to 1904",R.drawable.princi1));
        principalList.add(new Item("Rev. S. L. Thompson\t1904  to 1915",R.drawable.princi2));
        principalList.add(new Item("Rev. F. H. W. Kerr\t1915 to 1917",R.drawable.princi3));
        principalList.add(new Item("Rev. H. G. S. Kennedy\t1918 to 1919",R.drawable.princi4));
        principalList.add(new Item("Rev. A. O. Hardy\t1920 to 1924",R.drawable.princi5));
        principalList.add(new Item("Rev. F. H. W. Kerr\t1925 to 1928",R.drawable.princi6));
        principalList.add(new Item("Rev. G. C. P. Stevens\t1928 to 1930",R.drawable.princi7));
        principalList.add(new Item("Dr. A. F. Markham\t1930 to 1965",R.drawable.princi8));
        principalList.add(new Item("Rev.Sri K. C. Varghese\t1965 to 1966",R.drawable.princi9));
        principalList.add(new Item("Rev. Dr. Anuj Kumar Dhan\t1966 to 1969 September",R.drawable.princi10));
        principalList.add(new Item("Sri A. A. K. Mandal (In charge)\tSeptember 1968 to 1972 June.",R.drawable.princi11));
        principalList.add(new Item("Rev. Dr. Satish Chandra Banvar \t1 july 1972 to 13-9-1978",R.drawable.princi12));
        principalList.add(new Item("Prof. Bindeshwari Prasad (In Charge)\tSeptember 1979 to 18 Nov 1979.",R.drawable.princi13));
        principalList.add(new Item("Rev. Dr. J. S. Shaw\t1979 to 31-01-2001",R.drawable.princi14));
        principalList.add(new Item("Dr. Shane Ahmad Siddiqi  (In Charge)\t10 February 1986 to April 1986",R.drawable.princi15));
        principalList.add(new Item("Dr. J. K. Dhan\tMay 1986 to June 1986.",R.drawable.student));
        principalList.add(new Item("Rev. Dr. A. Hans\t February 1999 to July 2000\n" +
                "and \n" +
                "February 2002 to December 2008\n",R.drawable.princi17));
        principalList.add(new Item("Rev. Dr. S. K. Toppo \t current",R.drawable.princi18));





        MyAdapter2 myAdapter2=new MyAdapter2(this,R.layout.princi_model,principalList);
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
            new AlertDialog.Builder(Principal.this)
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