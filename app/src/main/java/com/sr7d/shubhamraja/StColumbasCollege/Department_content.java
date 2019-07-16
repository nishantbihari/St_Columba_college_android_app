package com.sr7d.shubhamraja.StColumbasCollege;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Shubham Raja on 12/30/2016.
 */

public class Department_content extends AppCompatActivity {
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
                programListContent.add(new Item("Botany",R.drawable.academic));
                programListContent.add(new Item("Zoology",R.drawable.academic));
                programListContent.add(new Item("Physics",R.drawable.physics));
                programListContent.add(new Item("Mathematics",R.drawable.maths));
                programListContent.add(new Item("Chemistry",R.drawable.chemistry));
                break;
            case 1:
                programListContent.add(new Item("Psychology",R.drawable.program));
                programListContent.add(new Item("Geography",R.drawable.geography));
                programListContent.add(new Item("Geology",R.drawable.geology));
                programListContent.add(new Item("History",R.drawable.history));
                programListContent.add(new Item("Economics",R.drawable.economics));
                programListContent.add(new Item("Philosophy",R.drawable.philosophy));
                programListContent.add(new Item("Political Science",R.drawable.pol_science));
                break;
            case 2:
                programListContent.add(new Item("Hindi",R.drawable.hindi));
                programListContent.add(new Item("Sanskrit",R.drawable.sanskrit));
                programListContent.add(new Item("Urdu",R.drawable.urdu));
                programListContent.add(new Item("English",R.drawable.english));
                break;
            case 3:
                programListContent.add(new Item("BCA",R.drawable.bca));
                programListContent.add(new Item("Bio-Tech",R.drawable.program));
                programListContent.add(new Item("BEd",R.drawable.program));
                break;

        }
        MyAdapter2 myAdapter2=new MyAdapter2(this,R.layout.princi_model,programListContent);
        simpleList.setAdapter(myAdapter2);
    }

}
