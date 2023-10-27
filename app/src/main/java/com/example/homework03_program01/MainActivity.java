package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button ma_btn_j_addUser;
    ListView ma_lv_j_users;

    ArrayList<Users> userList;

    DatabaseHelper dbHelper;

    ArrayList<String> usernames;
    ArrayAdapter<String> adapter;
    Intent updateIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma_btn_j_addUser=findViewById(R.id.ma_btn_v_addUser);
        ma_lv_j_users=findViewById(R.id.ma_lv_v_users);


        userList=new ArrayList<Users>();


    }

    //Takes user to the addUser activity
    public void addUserButtonEvent()
    {
        ma_btn_j_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //add something here that will connect MainActivity and AddUser
            }
        });
    }

}