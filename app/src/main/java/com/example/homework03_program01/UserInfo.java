package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserInfo extends AppCompatActivity {

    Button ui_btn_j_back;
    Button ui_btn_j_edit;
    TextView ui_tv_j_uname;
    TextView ui_tv_j_pword;
    TextView ui_tv_j_fullName;
    TextView ui_tv_j_email;
    TextView ui_tv_j_age;


    Intent mainActivity;
    Intent editUser;
    Users userPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        ui_btn_j_back=findViewById(R.id.ui_btn_v_back);
        ui_btn_j_edit=findViewById(R.id.ui_btn_v_edit);
        ui_tv_j_uname=findViewById(R.id.ui_tv_v_uname);
        ui_tv_j_pword=findViewById(R.id.ui_tv_v_pword);
        ui_tv_j_fullName=findViewById(R.id.ui_tv_v_fullName);
        ui_tv_j_email=findViewById(R.id.ui_tv_v_email);
        ui_tv_j_age=findViewById(R.id.ui_tv_v_age);


        mainActivity=new Intent(UserInfo.this, MainActivity.class);
        editUser= new Intent(UserInfo.this, EditUser.class);

        //Get user from mainActivity
        Intent cameFrom = getIntent();
        userPassed = (Users) cameFrom.getSerializableExtra("USERS");

        ui_tv_j_uname.setText(userPassed.getUname());
        ui_tv_j_pword.setText(userPassed.getPword());
        ui_tv_j_fullName.setText(userPassed.getFname() + " " + userPassed.getLname());
        ui_tv_j_email.setText(userPassed.getEmail());
        ui_tv_j_age.setText(userPassed.getAge());



        editUserButtonEvent();
        backButtonEvent();
    }

    public void editUserButtonEvent()
    {
        ui_btn_j_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                editUser.putExtra("USER", userPassed);
                startActivity(editUser);
            }
        });
    }

    public void backButtonEvent()
    {
        ui_btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(mainActivity);
            }
        });
    }
}