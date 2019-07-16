package com.sr7d.shubhamraja.StColumbasCollege;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Student_db extends AppCompatActivity implements View.OnClickListener
{

    EditText editRollno,editName,editMarks;
    Button btnAdd,btnDelete,btnModify,btnView,btnViewAll;
    SQLiteDatabase db;
    FloatingActionButton fab;
    String name;                        //Main.this.adapter.getFilter().filter(cs)
    Spinner sp;
    String yr;

    private String[] years;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_db);

        this.years = new String[]{
                "First Year","Second Year","Third Year"
        };
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sp=(Spinner)findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,years);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int p = position;
                switch (p){
                    case 0:
                        yr = "First Year";
                        break;
                    case 1:
                        yr = "Second Year";
                        break;
                    case 2:
                        yr = "Third Year";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editMarks = (EditText) findViewById(R.id.editText7);
        editRollno = (EditText) findViewById(R.id.editText6);
        editName = (EditText) findViewById(R.id.editText5);

        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnModify=(Button)findViewById(R.id.btnModify);
        btnView=(Button)findViewById(R.id.btnView);
        btnViewAll=(Button)findViewById(R.id.btnViewAll);

        btnAdd.setVisibility(View.INVISIBLE);
        btnModify.setVisibility(View.INVISIBLE);
        btnDelete.setVisibility(View.INVISIBLE);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);

        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(id VARCHAR,rollno VARCHAR,name VARCHAR,marks VARCHAR);");

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Student_db.this);
        name = sp.getString("name1", "Guest");
        String roll = sp.getString("roll1", "00");
        editName.setText(name);
        editRollno.setText(roll);

        try {
            String validate = getIntent().getExtras().getString("msg");
            if (validate.equals("ok")) {
                btnAdd.setVisibility(View.VISIBLE);
                btnModify.setVisibility(View.VISIBLE);
                btnDelete.setVisibility(View.VISIBLE);
                fab.setImageResource(R.drawable.ic_happy);
                setTitle("Student DB @ Admin");
            }
            else {

                btnAdd.setVisibility(View.INVISIBLE);
                btnModify.setVisibility(View.INVISIBLE);
                btnDelete.setVisibility(View.INVISIBLE);
                setTitle("Student DB @ "+name);
            }
        }
        catch (Exception e){

        }



        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent i = new Intent(Student_db.this,Log.class);
                startActivity(i);


            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view==btnAdd)
        {
            setTitle("Student DB @ Admin");
            if(editRollno.getText().toString().trim().length()==0||
                    editName.getText().toString().trim().length()==0||
                    editMarks.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO student VALUES('"+yr +"','"+editRollno.getText()+"','"+editName.getText()+
                    "','"+editMarks.getText()+"');");
            showMessage("Success", "Record added");
            clearText();
        }
        if(view==btnDelete)
        {
            setTitle("Student DB @ Admin");
            if(editRollno.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+editRollno.getText()+"'", null);
            if(c.moveToFirst())
            {
                db.execSQL("DELETE FROM student WHERE rollno='"+editRollno.getText()+"'");
                showMessage("Success", "Record Deleted");
            }
            else
            {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
        if(view==btnModify)
        {
            setTitle("Student DB @ Admin");
            if(editRollno.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+editRollno.getText()+"'", null);
            if(c.moveToFirst())
            {
                db.execSQL("UPDATE student SET id ='"+yr+"',name='"+editName.getText()+"',marks='"+editMarks.getText()+
                        "' WHERE rollno='"+editRollno.getText()+"'");

                showMessage("Success", "Record Modified");
            }
            else
            {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
        if(view==btnView)
        {
            setTitle("Student DB @ "+editName.getText().toString());
            if(editRollno.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+editRollno.getText()+"'", null);
            if(c.moveToFirst())
            {
                editName.setText(c.getString(2));
                editMarks.setText(c.getString(3));
                if(c.getString(0).equals("First Year")){
                    sp.setSelection(0);
                } else if (c.getString(0).equals("Second Year")) {
                    sp.setSelection(1);
                }
                else if (c.getString(0).equals("Third Year")) {
                    sp.setSelection(2);
                }
            }
            else
            {
                showMessage("Error", "Invalid Rollno");
                clearText();
            }
        }
        if(view==btnViewAll)
        {
            setTitle("Student DB ");
            Cursor c=db.rawQuery("SELECT * FROM student", null);
            if(c.getCount()==0)
            {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("Year :"+c.getString(0)+"\n");
                buffer.append("Rollno: "+c.getString(1)+"\n");
                buffer.append("Name: "+c.getString(2)+"\n");
                buffer.append("Marks: "+c.getString(3)+"\n\n");
            }
            showMessage("Student Details", buffer.toString());
        }

    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        editRollno.setText("");
        editName.setText("");
        editMarks.setText("");
        editRollno.requestFocus();
    }
}
