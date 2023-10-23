package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    Button ma_btn_j_addUser;
    ListView ma_lv_j_users;

    ArrayList<Users> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma_btn_j_addUser=findViewById(R.id.ma_btn_v_addUser);
        ma_lv_j_users=findViewById(R.id.ma_lv_v_users);


        userList=new ArrayList<Users>();
    }


}