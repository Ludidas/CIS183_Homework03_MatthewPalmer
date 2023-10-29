package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AddUser extends AppCompatActivity {

    Button au_btn_j_back;
    Button au_btn_j_addUser;
    EditText au_et_j_uname;
    EditText au_et_j_pword;
    EditText au_et_j_fname;
    EditText au_et_j_lname;
    EditText au_et_j_email;
    EditText au_et_j_age;
    TextView au_tv_j_error;
    TextView au_tv_j_success;


    ArrayList<Users> userList;
    ArrayList<String> usernames;
    Intent mainActivity;
    DatabaseHelper dbHelper;

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
        au_tv_j_error=findViewById(R.id.au_tv_v_error);
        au_tv_j_success=findViewById(R.id.au_tv_v_success);

        dbHelper = new DatabaseHelper(this);

        mainActivity=new Intent(AddUser.this, MainActivity.class);


        //Initialize userList
        userList = new ArrayList<Users>();
        userList = dbHelper.getAllRows();

        //get all of the usernames from our table
        usernames = dbHelper.getAllUsernames();



        backButtonEvent();
        addNewUserButtonEvent();
    }

    public void backButtonEvent()
    {
        au_btn_j_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(mainActivity);
            }
        });
    }


    public void addNewUserButtonEvent()
    {
        au_btn_j_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //boolean that will get disabled if the user should not be added
                boolean addableUser=true;

                //Get the things put in for the datapoint
                String u = au_et_j_uname.getText().toString();
                String p = au_et_j_pword.getText().toString();
                String f = au_et_j_fname.getText().toString();
                String l = au_et_j_lname.getText().toString();
                String e = au_et_j_email.getText().toString();
                String a = au_et_j_age.getText().toString();

                //If the username equals any of the other usernames on our usernames arraylist OR
                //any of the fields are empty do not enter the function
                for(int i=0;i < usernames.size(); i++)
                {
                    if(u.equals(usernames.get(i)))
                    {
                        addableUser=false;
                        au_tv_j_error.setText("Username already taken");
                    }

                }
                if(u.equals("") || p.equals("") || f.equals("") || l.equals("") || e.equals("") || a.equals(""))
                {
                    addableUser=false;
                    au_tv_j_error.setText("Please fill in all fields");
                }


                if (addableUser==true)
                {
                    Log.d("Success", "added user!!");
                    //adding the user to the database and the arraylist
                    Users user = new Users(u,p,f,l,e,a);


                    //Add new user
                    addNewUser(user);
                    //add the username to the username arraylist
                    usernames.add(u);


                    au_tv_j_error.setVisibility(view.INVISIBLE);

                    au_tv_j_success.setText(u + " successfully added!");
                    au_tv_j_success.setVisibility(view.VISIBLE);

                    clearAllFields();
                }
                else
                {
                    au_tv_j_success.setVisibility(view.INVISIBLE);
                    au_tv_j_error.setVisibility(view.VISIBLE);
                }

            }
        });
    }

    public void clearAllFields()
    {
        au_et_j_uname.setText("");
        au_et_j_pword.setText("");
        au_et_j_fname.setText("");
        au_et_j_lname.setText("");
        au_et_j_email.setText("");
        au_et_j_age.setText("");
    }

    public void addNewUser(Users u)
    {
        //add user to the arraylist
        userList.add(u);
        //add user to the database.
        dbHelper.addNewUser(u);
    }


}