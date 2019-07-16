package com.sr7d.shubhamraja.StColumbasCollege;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button btnLog;
    private EditText editTextEmail,editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private SignInButton mGoogleBtn;
    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "Login_Activity";
    FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLog = (Button)findViewById(R.id.buttonLogin);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail1);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword1);
        textViewSignup = (TextView)findViewById(R.id.textViewSignup);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {

            //proile activity
            String name = firebaseAuth.getCurrentUser().getEmail();
            new AlertDialog.Builder(Login.this)
                    .setTitle(name)
                    .setMessage("Already LoggedIn from this Account. \nDo you want to Logout?")
                    .setCancelable(false)
                    .setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    })
                    .setPositiveButton("LogOut", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            firebaseAuth.signOut();
                        }
                    })
                    .setNegativeButton("View", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            startActivity(new Intent(getApplicationContext(),User.class));
                        }
                    })
                    .show();

//            startActivity(new Intent(getApplicationContext(),User.class));
        }

        mGoogleBtn = (SignInButton)findViewById(R.id.sign_in_button);
        // Configure Google Sign In
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build();

        mAuth = FirebaseAuth.getInstance();
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(Login.this, "Connection problem, try again", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        mGoogleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        btnLog.setOnClickListener(Login.this);
        textViewSignup.setOnClickListener(Login.this);
    }
    private  void userLogin(){
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),"Email or Password can not be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Login User...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            // start the activity
                            finish();
                            startActivity(new Intent(getApplicationContext(),User.class));
                        }
                    }
                });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                     FirebaseUser user = firebaseAuth.getCurrentUser();
                     if (user != null) {
                    // User is signed in
                        android.util.Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                            } else {

                   android.util.Log.d(TAG, "onAuthStateChanged:signed_out");
                   }
               }
           };

    }

    private void signIn() {
             Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
             startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                 // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
                 if (requestCode == RC_SIGN_IN) {
                     GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                        if (result.isSuccess()) {
                             // Google Sign In was successful, authenticate with Firebase
                             GoogleSignInAccount account = result.getSignInAccount();
                            firebaseAuthWithGoogle(account);

                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        } else {
                             // Google Sign In failed, update UI appropriately
                            Toast.makeText(this, "Connection problem... try again", Toast.LENGTH_SHORT).show();
                         }
                 }
            }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                mAuth.signInWithCredential(credential)
                 .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                android.util.Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                 if (!task.isSuccessful()) {
                    android.util.Log.w(TAG, "signInWithCredential", task.getException());
                     Toast.makeText(Login.this, "Authentication failed.",
                             Toast.LENGTH_SHORT).show();
                     }

                 }
         });

    }

    @Override
    public void onClick(View v) {
        if (v == btnLog) {
            userLogin();
        }
        if (v == textViewSignup) {
            finish();
            startActivity(new Intent(this,Register.class));
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
            new AlertDialog.Builder(Login.this)
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
    public void onStop() {
        super.onStop();
       if (mAuthListener != null) {
           mAuth.removeAuthStateListener(mAuthListener);
           }
    }
}
