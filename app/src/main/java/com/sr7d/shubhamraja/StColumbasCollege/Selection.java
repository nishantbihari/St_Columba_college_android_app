package com.sr7d.shubhamraja.StColumbasCollege;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

/**
 * Created by Shubham Raja on 12/27/2016.
 */

public class Selection extends AppCompatActivity{

    private AdView mAdView;

    GridView simpleList;
    ArrayList<Item> menuList=new ArrayList();

    public String[] text = {
            "About", "Academic",
            "Program", "Admission",
            "Department", "Association",
            "Alumni", "Reach Us",
            "Contact"
    };
    public Integer[] img = {
            R.drawable.colglogo, R.drawable.academic,
            R.drawable.program, R.drawable.admission,
            R.drawable.department, R.drawable.association,
            R.drawable.alumni,R.drawable.reachus,
            R.drawable.contact
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection);
        setTitle("Menu");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        simpleList = (GridView) findViewById(R.id.gridView);
        menuList.add(new Item("About",R.drawable.about));
        menuList.add(new Item("Principal",R.drawable.principal));
        menuList.add(new Item("Program",R.drawable.program));
        menuList.add(new Item("Department",R.drawable.department));
        menuList.add(new Item("Admission",R.drawable.admission));
        menuList.add(new Item("Attendance",R.drawable.academic));
        menuList.add(new Item("Students",R.drawable.student));
        menuList.add(new Item("Association",R.drawable.association));
        menuList.add(new Item("Alumni",R.drawable.alumni));
        menuList.add(new Item("Reach Us",R.drawable.reachus));
        menuList.add(new Item("Contact",R.drawable.contact));
        menuList.add(new Item("Facilities",R.drawable.facilities));

        MyAdapter myAdapter=new MyAdapter(this,R.layout.model,menuList);
        simpleList.setAdapter(myAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i;
                switch (arg2){
                    case 0:
                        i = new Intent(Selection.this,Home.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(Selection.this,Principal.class);
                        startActivity(i);
                        break;
                    case 2 :
                        i = new Intent(Selection.this,Program.class);
                        startActivity(i);
                        break;
                    case 3:
                        i = new Intent(Selection.this,Department.class);
                        startActivity(i);
                        break;
                    case 4:
                        i = new Intent(Selection.this,Admission.class);
                        startActivity(i);
                        break;
                    case 5 :
                        i = new Intent(Selection.this,Status.class);
                        startActivity(i);
                        break;
                    case 6:
                        i = new Intent(Selection.this,Student_db.class);
                        startActivity(i);
                        break;
                    case 7:
                        i = new Intent(Selection.this,Association.class);
                        startActivity(i);
                        break;
                    case 8:
                        i = new Intent(Selection.this,Alumn.class);
                        startActivity(i);
                        break;
                    case 9:
                        i = new Intent(Selection.this,Reachus.class);
                        startActivity(i);
                        break;
                    case 10 :
                        i = new Intent(Selection.this,Contact.class);
                        startActivity(i);
                        break;
                    case 11 :
                        i = new Intent(Selection.this,Facilities.class);
                        startActivity(i);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Invalid selection", Toast.LENGTH_LONG).show();


                }
                String item=arg0.getAdapter().getItem(arg2).toString();
                Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_LONG).show();
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
            new AlertDialog.Builder(Selection.this)
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
//    private ArrayList<Data> getDatas(){
//        ArrayList<Data> datas = new ArrayList<>();
//
//        datas.add(new Data(text[0],img[0]));
//        datas.add(new Data(text[1],img[1]));
//        datas.add(new Data(text[2],img[2]));
//        datas.add(new Data(text[3],img[3]));
//        datas.add(new Data(text[4],img[4]));
//        datas.add(new Data(text[5],img[5]));
//        datas.add(new Data(text[6],img[6]));
//        datas.add(new Data(text[7],img[7]));
//        datas.add(new Data(text[8],img[8]));
//
//        return datas;
//    }
}
