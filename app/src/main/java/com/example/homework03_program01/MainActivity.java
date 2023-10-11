package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Button ma_btn_j_addUser;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma_btn_j_addUser=findViewById(R.id.ma_btn_v_addUser);

        //changeButtonColor();
    }

    public void changeButtonColor()
    {
        ma_btn_j_addUser.setTextColor(0xFFFAAA);
    }
}