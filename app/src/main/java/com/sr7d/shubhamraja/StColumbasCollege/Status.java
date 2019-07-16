package com.sr7d.shubhamraja.StColumbasCollege;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Status extends AppCompatActivity {

    TextView mConditionTextView;
    Button mButtonSunny,mButtonFoggy;

    EditText date,roll;

    Spinner depart,year;

    Button attendance;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef = mRootRef.child("condition");
    DatabaseReference mAttendanceRef = mRootRef.child("Attendance");

    DatabaseReference todaysRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        setTitle("Todays's Status");

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        depart = (Spinner)findViewById(R.id.spinnerDepartment2);
        year = (Spinner)findViewById(R.id.spinnerYear2);

        mConditionTextView = (TextView)findViewById(R.id.textView);
        attendance = (Button)findViewById(R.id.btn3);

        date = (EditText)findViewById(R.id.editTextDate);
        roll = (EditText)findViewById(R.id.editTextRoll);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        date.setText(dateFormat.format(new Date()));


        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAttendanceRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(date.getText().toString())) {
                            final DatabaseReference todaysDepRef = mAttendanceRef.child(date.getText().toString());
                            todaysDepRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.hasChild(depart.getSelectedItem().toString())) {
                                        final DatabaseReference todaysDeptYearRef = todaysDepRef.child(depart.getSelectedItem().toString());
                                        todaysDeptYearRef.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild(year.getSelectedItem().toString())) {
                                                    todaysRef = todaysDeptYearRef.child(year.getSelectedItem().toString());

                                                    todaysRef.addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                            if (roll.getText().toString().equals("")) {
                                                                new AlertDialog.Builder(Status.this)
                                                                        .setMessage("Enter Roll Number")
                                                                        .create()
                                                                        .show();
                                                            } else {
                                                                if (dataSnapshot.hasChild(roll.getText().toString())) {
//                                        Toast.makeText(Status.this, "Absent", Toast.LENGTH_SHORT).show();
                                                                    new AlertDialog.Builder(Status.this)
                                                                            .setMessage("Absent")
                                                                            .create()
                                                                            .show();
                                                                } else {
//                                        Toast.makeText(Status.this, "Present", Toast.LENGTH_SHORT).show();
                                                                    new AlertDialog.Builder(Status.this)
                                                                            .setMessage("Present")
                                                                            .create()
                                                                            .show();
                                                                }
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {

                                                        }
                                                    });
                                                } else {
//                            Toast.makeText(Status.this, "Non-Working Day", Toast.LENGTH_SHORT).show();
                                                    new AlertDialog.Builder(Status.this)
                                                            .setMessage("This year is not taken the attendance")
                                                            .create()
                                                            .show();
                                                }

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    } else {
                                        new AlertDialog.Builder(Status.this)
                                                .setMessage("Department not updated the value")
                                                .create()
                                                .show();

                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        } else {
                            new AlertDialog.Builder(Status.this)
                                    .setMessage("Non-working Day Selected")
                                    .create()
                                    .show();
                        }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

//                            Toast.makeText(Status.this, "Record Found", Toast.LENGTH_SHORT).show();
//                             todaysRef = mAttendanceRef.child(date.getText().toString()).child(depart.getSelectedItem().toString()).child(year.getSelectedItem().toString());
//                todaysRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        String temp;
//                       try{ temp = dataSnapshot.getValue(String.class);
//                           if (temp == roll.getText().toString()) {
//                               Toast.makeText(Status.this, "Absent", Toast.LENGTH_SHORT).show();
//                           } else {
//                               Toast.makeText(Status.this, "Present", Toast.LENGTH_SHORT).show();
//                           }}
//                       catch (Exception e){
//                           Toast.makeText(Status.this, "No Record Found", Toast.LENGTH_SHORT).show();
//                       }
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        Toast.makeText(Status.this, "No Record Found", Toast.LENGTH_SHORT).show();
//                    }
//                });




            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mConditionTextView.setText(text);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
            new AlertDialog.Builder(Status.this)
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
