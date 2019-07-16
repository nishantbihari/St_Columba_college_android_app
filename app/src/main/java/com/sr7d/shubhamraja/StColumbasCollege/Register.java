package com.sr7d.shubhamraja.StColumbasCollege;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private Button btnReg;
    private EditText editTextEmail,editTextPassword,editTextPassword1;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
//    private Button chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Verification");

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnReg = (Button)findViewById(R.id.buttonRegister);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextPassword1 = (EditText)findViewById(R.id.editTextPassword1);
        textViewSignin = (TextView)findViewById(R.id.textViewSignin);
//        chat = (Button)findViewById(R.id.buttonChat);
//
//        chat.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(),Login.class));
        }

        btnReg.setOnClickListener(Register.this);
        textViewSignin.setOnClickListener(Register.this);
    }
    private void registerUser(){
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirm = editTextPassword1.getText().toString();

        if (TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),"Email or Password can not be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(password.equals(confirm)) {
            progressDialog.setMessage("Registering User...");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registered Succeccfully", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), Login.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Cant register.please try again", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
        }

    }

    @Override
    public void onClick(View v) {
        if (v==btnReg) {
            registerUser();
        }
        if (v == textViewSignin) {
            finish();
            startActivity(new Intent(getApplicationContext(),Login.class));
        }
//        if (v == chat) {
//            finish();
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//        }
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
            new AlertDialog.Builder(Register.this)
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
