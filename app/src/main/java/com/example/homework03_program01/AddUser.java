package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUser extends AppCompatActivity {

    Button au_btn_j_back;
    Button au_btn_j_addUser;
    EditText au_et_j_uname;
    EditText au_et_j_pword;
    EditText au_et_j_fname;
    EditText au_et_j_lname;
    EditText au_et_j_email;
    EditText au_et_j_age;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        au_btn_j_back=findViewById(R.id.au_btn_v_back);
        au_btn_j_addUser=findViewById(R.id.au_btn_v_addUser);
        au_et_j_uname=findViewById(R.id.au_et_v_uname);
        au_et_j_pword=findViewById(R.id.au_et_v_pword);
        au_et_j_fname=findViewById(R.id.au_et_v_fname);
        au_et_j_lname=findViewById(R.id.au_et_v_lname);
        au_et_j_email=findViewById(R.id.au_et_v_email);
        au_et_j_age=findViewById(R.id.au_et_v_age);


    }

    public void addNewUserButtonEvent()
    {
        au_btn_j_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Log.d("Button Press", "added user!!");

                String u = au_et_j_uname.getText().toString();
                String p = au_et_j_pword.getText().toString();
                String f = au_et_j_fname.getText().toString();
                String l = au_et_j_lname.getText().toString();
                String e = au_et_j_email.getText().toString();
                String a = au_et_j_age.getText().toString();

                //adding the user to the database and the arraylist
                Users user = new Users(u,p,f,l,e,a);

                //add the username to the username arraylist
                usernames.add(u);


            }
        });
    }



}