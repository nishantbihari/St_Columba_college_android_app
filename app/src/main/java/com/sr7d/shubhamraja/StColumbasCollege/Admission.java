package com.sr7d.shubhamraja.StColumbasCollege;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Shubham Raja on 12/29/2016.
 */

public class Admission extends AppCompatActivity {
    Spinner spn;
    TextView txt;
    RelativeLayout admLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        setTitle("Admission Details");

        admLayout = (RelativeLayout)findViewById(R.id.layoutAdm);
        admLayout.setVisibility(View.VISIBLE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.GONE);

        spn= (Spinner)findViewById(R.id.spinnerAdm);
        txt = (TextView)findViewById(R.id.txtAdm);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        txt.setText("A student may be admitted to the course of instructions for the I.A. " +
                                "if S/he has passed the Matriculation examination of a Board/Council established or incorporated by law, or any other examination recognized by the University as equivalent thereto. " +
                                "Provided that for admission to the course of instructions for the I.A it shall be necessary for a student to have obtained not less than 45% marks at the Matriculation examination in the subjects.");
                        break;
                    case 1:
                        txt.setText("A student may be admitted to the course of instructions for the I. Sc. " +
                                "if S/he has passed the Matriculation examination of a Board/Council established or incorporated by law, or any other examination recognized by the University as equivalent thereto. Provided that for admission to the course of instructions for the I. Sc. " +
                                "it shall be necessary for a student to have obtained not less than 45% marks at the Matriculation examination in the subjects.");
                        break;
                    case 2:
                        txt.setText("A student may be admitted to the course of instructions for the degree of Bachelor of Science (General/ Honours) if S/he has passed the Intermediate examination in Science of a Board/Council University established or incorporated. by Law, or any other examination recognized by the University as equivalent thereto.\n" +
                                "Provided that for admission to the course of instructions for the Honours degree it shall be necessary for a student to have obtained not less than 45% marks at the Intermediate examination in the subject offered for Honours study, or in an allied subject as determined by the Academic Council in case the subject was not prescribed for study at the Intermediate level. " +
                                "In such case a student must have passed Intermediate /Board/ Council or any equivalent examination with an aggregate of 45% marks. Provided that a candidate shall not choose: -\n" +
                                "1.Mathematics or Statistics unless S/he passed the I.Sc. Examination or equivalent in Maths.\n" +
                                "2.Physics unless S/he passed the I.Sc. Examination or equivalent in Physics and Maths.\n" +
                                "3.Chemistry unless S/he has passed the I.Sc. Examination or equivalent in Chemistry with Maths or Biology.\n" +
                                "4.Botany/Zoology unless S/he has passed I.Sc. examination or equivalent in Chemistry and Botany/Zoology\n" +
                                "5.Geology unless S/he has passed the I.Sc. Examination or equivalent in Geology or S/he has passed Intermediate examination or equivalent with 45% marks in aggregate. A student may be admitted to Bachelor of Science (General/Honours) in Geology, if S/he has secured at least 45% marks in aggregate at the Intermediate Examination in Science or equivalent thereto.");
                        break;
                    case 3:
                        txt.setText("A student may be admitted to the course of instructions for the degree of Bachelor of Arts (General/Honours) if S/he has passed the Intermediate examination in Arts of a Board/University established or incorporated by Law, or any other examination recognized by the University as equivalent thereto. Provided that for admission to the course of instructions for the Honours degree it shall be necessary for a student to have obtained not less than 45% marks at the Intermediate examination in the subject offered for Honours study, or in an allied subject as determined by the Academic Council in case the subject was not prescribed for study at the Intermediate Level. In such case a student must have passed Intermediate/Board/Councilor any equivalent examination with an aggregate of 45% marks. Provided that a student who has passed the Intermediate examination in Science or Commerce shall also be eligible for the admission to the Bachelor of arts (Honours/General course) if S/he has passed with a minimum of (50% / 45%) of marks in the aggregate at the I.Sc. or I.Com. examination. Provided that a candidate shall not choose\n" +
                                "1.Mathematics or Statistics unless S/he has passed the Intermediate examination or equivalent in Mathematics.\n" +
                                "2.Geography unless S/he has passed the LA. examination in Geography\n" +
                                "3.Psychology Unless S/he has passed the I.A examination in Psychology.");
                        break;
                    case 4:
                        txt.setText("A student may be admitted to the course of instructions for the degree of Bachelor of Computer Applications if S/he has passed the Intermediate examination in Arts/Science/Commerce/+2 (with mathematics as one of the paper) of a Board/University established or incorporated by Law, or any other examination recognized by the University as equivalent thereto, with minimum of 45% marks in Physics, Mathematics & Econimics.\n" +
                                "The admission will be made entirely on the basis of performance of the candidate at the entrance aptitude test followed by Interview to be conducted by the college. Aptitude test will be of 90 minutes duration and will comprise multiple choice/ objective type questions in the following sections:\n" +
                                "Section-I (For Science students) – Physics & Mathematics.\n" +
                                "Section-II (For Arts/Com. Students) – Mathematics & Current Affairs.\n" +
                                "Half of the total marks of each question will be deducted for wrong answers.\n" +
                                "Final selection will be made after interview of screened candidates in person.");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
            startActivity(i);
            return true;
        }
        if (id == R.id.action_login) {

            Intent i = new Intent(this,User.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.item1) {
            new AlertDialog.Builder(Admission.this)
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
