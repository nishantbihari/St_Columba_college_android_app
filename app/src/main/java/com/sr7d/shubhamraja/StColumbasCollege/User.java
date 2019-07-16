package com.sr7d.shubhamraja.StColumbasCollege;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

/**
 * Created by Shubham Raja on 2/25/2017.
 */

public class User extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;
    RelativeLayout detailLayout;
    LinearLayout view,edit;

    private TextView tname,tAddress,tFname,tMname,tHSname,tMarks,tPercentage,tFOccupation,tuser;
    private EditText editTextName, editTextAddress,editTextFname,editTextMname,editTextHSname,editTextMarks,editTextPercentage,editTextFOccupation;
    private Button buttonSave;
    ImageView pic;
    String iRef;

    FloatingActionButton btnExp,btnEdit,btnChat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        detailLayout = (RelativeLayout)findViewById(R.id.layoutDetails);
        detailLayout.setVisibility(View.VISIBLE);
        edit = (LinearLayout)findViewById(R.id.linearLayoutEditBasic);
        view = (LinearLayout)findViewById(R.id.linearLayoutShowBasic);
        edit.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);

        pic= (ImageView)findViewById(R.id.imagePic);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        } else {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            String email = user.getEmail();
            String name = user.getDisplayName();

            Toast.makeText(this, "name = "+name, Toast.LENGTH_SHORT).show();

        }

        tname = (TextView)findViewById(R.id.textViewFullname);
        tAddress = (TextView)findViewById(R.id.textViewAddress);
        tFname = (TextView)findViewById(R.id.textViewFather);
        tMname = (TextView)findViewById(R.id.textViewMother);
        tHSname = (TextView)findViewById(R.id.textViewSchool);
        tMarks = (TextView)findViewById(R.id.textViewMarks);
        tPercentage = (TextView)findViewById(R.id.textViewPercentage);
        tFOccupation = (TextView)findViewById(R.id.textViewFatherOccupation);
        tuser = (TextView)findViewById(R.id.txtUser);

        editTextName = (EditText)findViewById(R.id.editTextFullname);
        editTextAddress = (EditText)findViewById(R.id.editTextAddress);
        editTextFname = (EditText)findViewById(R.id.editTextFather);
        editTextMname = (EditText)findViewById(R.id.editTextMother);
        editTextHSname = (EditText)findViewById(R.id.editTextSchool);
        editTextMarks = (EditText)findViewById(R.id.editTextMarks);
        editTextPercentage = (EditText)findViewById(R.id.editTextPercentage);
        editTextFOccupation = (EditText)findViewById(R.id.editTextFatherOccupation);

        buttonSave = (Button)findViewById(R.id.buttonSaveBasicInformation);
        buttonSave.setOnClickListener(this);
        btnExp = (FloatingActionButton)findViewById(R.id.fab);
        btnEdit = (FloatingActionButton)findViewById(R.id.fab2);
        btnChat = (FloatingActionButton)findViewById(R.id.fab1);
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Chat.class));
            }
        });

        btnEdit.setVisibility(View.GONE);
        btnChat.setVisibility(View.GONE);
        btnExp.setOnClickListener(this);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setVisibility(View.VISIBLE);
                view.setVisibility(View.GONE);
            }
        });

        if (firebaseAuth.getCurrentUser() != null) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            String email = user.getEmail();
            String name = user.getDisplayName();
            tuser.setText(name + "\n" + email);
            iRef = "images/profil" + user.getUid() + ".jpg";
        } else {
            iRef = "images/profil.jpg";
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        pic.setOnClickListener(this);


        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference riversRef = storageReference.child(iRef);
        Glide.with(this /* context */).using(new FirebaseImageLoader()).load(riversRef).into(pic);


        edit.setVisibility(View.GONE);
        user = firebaseAuth.getCurrentUser();

        if (firebaseAuth.getCurrentUser() != null) {
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
                    tPercentage.setText(add + " %");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }



    }

    private void saveUserInforation(){
        String name = editTextName.getText().toString();
        String Address = editTextAddress.getText().toString();

        UserInformation userInformation = new UserInformation(name,Address,editTextFname.getText().toString(),editTextMname.getText().toString(),editTextFOccupation.getText().toString(),editTextHSname.getText().toString(),editTextMarks.getText().toString(),editTextPercentage.getText().toString());
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this, "Information Saved", Toast.LENGTH_SHORT).show();
    }

    private void  ShowFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an Image"),PICK_IMAGE_REQUEST);
    }
    private void uploadFile(){

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading data");
            progressDialog.show();
            FirebaseUser user= firebaseAuth.getCurrentUser();
            String ref = "images/profil"+user.getUid()+".jpg";
            StorageReference riversRef = storageReference.child(ref);

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            progressDialog.dismiss();
                            Toast.makeText(User.this, "Details Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            progressDialog.dismiss();
                            Toast.makeText(User.this, "Failed... Can't upload the data", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int) progress)+ "% Uploaded...");
                        }
                    });
        } else {

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                pic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void onClick(View v) {
        if ( v == buttonSave) {
            uploadFile();
            saveUserInforation();
            view.setVisibility(View.VISIBLE);
            edit.setVisibility(View.GONE);
        }
        if( v == btnExp){
            if (btnChat.getVisibility() == View.GONE) {
                btnChat.setVisibility(View.VISIBLE);
            }
            else{
                btnChat.setVisibility(View.GONE);
            }
            if (btnEdit.getVisibility() == View.GONE) {
                btnEdit.setVisibility(View.VISIBLE);
            } else {
                btnEdit.setVisibility(View.GONE);
                view.setVisibility(View.VISIBLE);
                edit.setVisibility(View.GONE);
            }
        }
        if (v == pic) {
            if (edit.getVisibility() == View.VISIBLE) {
                ShowFileChooser();
            }
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
            new AlertDialog.Builder(User.this)
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
