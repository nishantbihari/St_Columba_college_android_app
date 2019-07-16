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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView emailuser;
    private TextView tname,tAddress,tFname,tMname,tHSname,tMarks,tPercentage,tFOccupation;
    private Button logOut,save,show;
    private DatabaseReference databaseReference;

    private EditText editTextName, editTextAddress,editTextFname,editTextMname,editTextHSname,editTextMarks,editTextPercentage,editTextFOccupation;
    private Button buttonSave;

    private LinearLayout layoutSave, layoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutSave = (LinearLayout)findViewById(R.id.linearLayoutEdit);
        layoutView = (LinearLayout)findViewById(R.id.linearLayoutShow);

        tname = (TextView)findViewById(R.id.textViewFullname);
        tAddress = (TextView)findViewById(R.id.textViewAddress);
        tFname = (TextView)findViewById(R.id.textViewFather);
        tMname = (TextView)findViewById(R.id.textViewMother);
        tHSname = (TextView)findViewById(R.id.textViewSchool);
        tMarks = (TextView)findViewById(R.id.textViewMarks);
        tPercentage = (TextView)findViewById(R.id.textViewPercentage);
        tFOccupation = (TextView)findViewById(R.id.textViewOccupation);

        editTextName = (EditText)findViewById(R.id.editTextFullname);
        editTextAddress = (EditText)findViewById(R.id.editTextAddress);
        editTextFname = (EditText)findViewById(R.id.editTextFather);
        editTextMname = (EditText)findViewById(R.id.editTextMother);
        editTextHSname = (EditText)findViewById(R.id.editTextSchool);
        editTextMarks = (EditText)findViewById(R.id.editTextMarks);
        editTextPercentage = (EditText)findViewById(R.id.editTextPercentage);
        editTextFOccupation = (EditText)findViewById(R.id.editTextOccupation);

        buttonSave = (Button)findViewById(R.id.buttonSaveInformation);
        save = (Button)findViewById(R.id.buttonsave);
        save.setOnClickListener(this);
        show = (Button)findViewById(R.id.buttonView);
        show.setOnClickListener(this);


        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), Register.class));
        } else {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            String name = user.getDisplayName();
            Toast.makeText(this, "name = "+name, Toast.LENGTH_SHORT).show();

        }


        FirebaseUser user = firebaseAuth.getCurrentUser();


        databaseReference = FirebaseDatabase.getInstance().getReference();

        buttonSave.setOnClickListener(this);

        emailuser = (TextView)findViewById(R.id.textViewUserEmail);

        emailuser.setText("welcome : " + user.getEmail());
        logOut = (Button)findViewById(R.id.buttonLogOut);

        logOut.setOnClickListener(ProfileActivity.this);

    }

    private void saveUserInforation(){
        String name = editTextName.getText().toString();
        String Address = editTextAddress.getText().toString();

        UserInformation userInformation = new UserInformation(name,Address,editTextFname.getText().toString(),editTextMname.getText().toString(),editTextFOccupation.getText().toString(),editTextHSname.getText().toString(),editTextMarks.getText().toString(),editTextPercentage.getText().toString());
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v == logOut) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(),Login.class));
        }
        if ( v == buttonSave) {
            saveUserInforation();
        }
        if ( v == save) {
            layoutSave.setVisibility(View.VISIBLE);
            layoutView.setVisibility(View.INVISIBLE);
        }
        if ( v == show) {
            layoutView.setVisibility(View.VISIBLE);
            layoutSave.setVisibility(View.INVISIBLE);
            FirebaseUser user = firebaseAuth.getCurrentUser();
             DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child(user.getUid());
            DatabaseReference name = databaseReference2.child("name");
            name.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String nn = dataSnapshot.getValue(String.class);
                    tname.setText(nn);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            name = databaseReference2.child("Address");
            name.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String add = dataSnapshot.getValue(String.class);
                    tAddress.setText(add);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            name = databaseReference2.child("fName");
            name.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String fnm = dataSnapshot.getValue(String.class);
                    tFname.setText(fnm);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            name = databaseReference2.child("mName");
            name.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String add = dataSnapshot.getValue(String.class);
                    tMname.setText(add);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            name = databaseReference2.child("fOccupation");
            name.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String add = dataSnapshot.getValue(String.class);
                    tFOccupation.setText(add);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            name = databaseReference2.child("hsName");
            name.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String add = dataSnapshot.getValue(String.class);
                    tHSname.setText(add);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            name = databaseReference2.child("marks");
            name.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String add = dataSnapshot.getValue(String.class);
                    tMarks.setText(add);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            name = databaseReference2.child("percentage");
            name.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String add = dataSnapshot.getValue(String.class);
                    tPercentage.setText(add+" %");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
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
            new AlertDialog.Builder(ProfileActivity.this)
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
