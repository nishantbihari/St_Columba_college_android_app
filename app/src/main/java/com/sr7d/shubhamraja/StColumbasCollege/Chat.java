package com.sr7d.shubhamraja.StColumbasCollege;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;

public class Chat extends AppCompatActivity {

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Admin");

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        setTitle("Chat with Admin");
        try {
            int cht = getIntent().getExtras().getInt("chat");
            if (cht == 11) {
                setTitle("Alumni's Talk");
                root = FirebaseDatabase.getInstance().getReference().child("Alumni_talk");
            }
        } catch (Exception e) {
        }


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Chat.this);
        final String nam =sp.getString("name1", "Guest");

        ImageButton send = (ImageButton)findViewById(R.id.imageButton2);
        final EditText message = (EditText)findViewById(R.id.editText12);
        ListView messageList = (ListView)findViewById(R.id.listView12);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(),Register.class));
        }


        final FirebaseUser user = firebaseAuth.getCurrentUser();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ChatMessage chat = new ChatMessage(nam, message.getText().toString());
                    root.push().setValue(chat);
                    message.setText("");
            }
        });
        final List<ChatMessage> messages = new LinkedList<>();
        final ArrayAdapter<ChatMessage> adapter = new ArrayAdapter<ChatMessage>(
                this,android.R.layout.two_line_list_item,messages
        ){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(android.R.layout.two_line_list_item,parent,false);
                }
                ChatMessage chat = messages.get(position);
                ((TextView)convertView.findViewById(android.R.id.text1)).setText(chat.getName());
                ((TextView)convertView.findViewById(android.R.id.text2)).setText(chat.getMessage());
                return  convertView;

            }
        };
        messageList.setAdapter(adapter);

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatMessage chat = dataSnapshot.getValue(ChatMessage.class);
                messages.add(chat);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

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
            new AlertDialog.Builder(Chat.this)
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
