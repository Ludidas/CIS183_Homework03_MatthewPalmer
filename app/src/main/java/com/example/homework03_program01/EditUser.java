package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditUser extends AppCompatActivity {

    Button eu_btn_j_back;
    Button eu_btn_j_save;
    TextView eu_tv_j_uname;
    EditText eu_et_j_pword;
    EditText eu_et_j_fname;
    EditText eu_et_j_lname;
    EditText eu_et_j_email;
    EditText eu_et_j_age;
    TextView eu_tv_j_error;

    Intent mainActivity;
    Users userPassed;
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        eu_btn_j_back=findViewById(R.id.eu_btn_v_back);
        eu_btn_j_save=findViewById(R.id.eu_btn_v_save);
        eu_tv_j_uname=findViewById(R.id.eu_tv_v_uname);
        eu_et_j_pword=findViewById(R.id.eu_et_v_pword);
        eu_et_j_fname=findViewById(R.id.eu_et_v_fname);
        eu_et_j_lname=findViewById(R.id.eu_et_v_lname);
        eu_et_j_email=findViewById(R.id.eu_et_v_email);
        eu_et_j_age=findViewById(R.id.eu_et_v_age);
        eu_tv_j_error=findViewById(R.id.eu_tv_v_error);

        dbHelper = new DatabaseHelper(this);

        mainActivity = new Intent(EditUser.this, MainActivity.class);

        //Get user from UserInfo
        Intent cameFrom = getIntent();
        userPassed = (Users) cameFrom.getSerializableExtra("USER");

        //Set textboxes with current data
        eu_tv_j_uname.setText(userPassed.getUname());
        eu_et_j_pword.setText(userPassed.getPword());
        eu_et_j_fname.setText(userPassed.getFname());
        eu_et_j_lname.setText(userPassed.getLname());
        eu_et_j_email.setText(userPassed.getEmail());
        eu_et_j_age.setText(userPassed.getAge());


        backButtonEvent();
        saveButtonEvent();
    }

    public void backButtonEvent()
    {
        eu_btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(mainActivity);
            }
        });
    }

    public void saveButtonEvent()
    {
        eu_btn_j_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String p=eu_et_j_pword.getText().toString();
                String f=eu_et_j_fname.getText().toString();
                String l=eu_et_j_lname.getText().toString();
                String e=eu_et_j_email.getText().toString();
                String a=eu_et_j_age.getText().toString();


                //If all fields are filled in, successfully update the user
                if(p.equals("") || f.equals("") || l.equals("") || e.equals("") || a.equals(""))
                {
                    eu_tv_j_error.setText("Please fill in all fields");
                    eu_tv_j_error.setVisibility(view.VISIBLE);
                }
                else
                {
                    //change the information about the user that was passed.
                    userPassed.setPword(p);
                    userPassed.setFname(f);
                    userPassed.setLname(l);
                    userPassed.setEmail(e);
                    userPassed.setAge(a);

                    //pass new updated user to update
                    dbHelper.updateUser(userPassed);
                    //start main activity
                    startActivity(mainActivity);
                }
            }
        });
    }
}