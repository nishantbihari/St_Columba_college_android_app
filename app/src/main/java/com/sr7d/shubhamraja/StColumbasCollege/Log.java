package com.sr7d.shubhamraja.StColumbasCollege;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Log extends AppCompatActivity {
    EditText id,pass;
    Button ok,cancel;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_Dialog);
        setContentView(R.layout.activity_log);
        id = (EditText)findViewById(R.id.editText8);
        pass = (EditText)findViewById(R.id.editText9);

        ok = (Button)findViewById(R.id.btnOk);
        cancel = (Button)findViewById(R.id.btnCancel);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.getText().toString().equals("admin")&&pass.getText().toString().equals("admin")) {
                    i = new Intent(Log.this,Student_db.class);
                    i.putExtra("msg","ok");
                    startActivity(i);
                }
                else {
                    i = new Intent(Log.this,Student_db.class);
                    i.putExtra("msg","no");
                    startActivity(i);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Log.this,Student_db.class);
                i.putExtra("msg","no");
                startActivity(i);
            }
        });
    }
}
