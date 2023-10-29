//===================================
//Name: Matthew Palmer
//Date: 10/29/2023
//Desc: Full CRUD Admin App
//===================================

package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button ma_btn_j_addUser;
    ListView ma_lv_j_users;



    DatabaseHelper dbHelper;

    ArrayList<Users> userList;
    ArrayList<String> usernames;
    UserListAdapter adapter;
    Intent addUserIntent;
    Intent userInfoIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma_btn_j_addUser=findViewById(R.id.ma_btn_v_addUser);
        ma_lv_j_users=findViewById(R.id.ma_lv_v_users);


        userList=new ArrayList<Users>();
        //make an instance of the databaseHelper and pass it this
        dbHelper = new DatabaseHelper(this);
        //call the initializeDB() function to fill the records into our table
        dbHelper.initializeDB();

        //test to make sure the records were inserted
        //we should see 4 when we run this
        Log.d("Number of records: ", dbHelper.numberOfRowsInTable() + "");
        userList = dbHelper.getAllRows();


        //get all of the usernames from our table
        usernames = dbHelper.getAllUsernames();


        addUserIntent = new Intent(MainActivity.this, AddUser.class);
        userInfoIntent= new Intent(MainActivity.this, UserInfo.class);

        //for testing purposes only
        displayUsers();


        ma_lv_j_users.setAdapter(adapter);


        fillListView();
        userInfoEvent();
        deleteUserEvent();
        addUserButtonEvent();
    }

    //Button Events============================================================================

    //Takes user to the addUser activity
    public void addUserButtonEvent()
    {
        ma_btn_j_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(addUserIntent);
            }
        });
    }

    public void userInfoEvent()
    {
        ma_lv_j_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                userInfoIntent.putExtra("USERS", userList.get(i));
                startActivity(userInfoIntent);
            }
        });
    }

    public void deleteUserEvent()
    {
        ma_lv_j_users.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //call the delete function in our dbHelpter and pass it username
                dbHelper.deleteUser(usernames.get(i));
                //remove the user from our userlist
                userList.remove(i);
                //remove the suer from our usernames
                usernames.remove(i);
                //update the listview to see the changes
                adapter.notifyDataSetChanged();


                return false;
            }
        });
    }
    //Miscellaneous Functions===================================================================

    public void displayUsers()
    {
        for(int i = 0; i < userList.size(); i++)
        {
            Log.d("User: ", userList.get(i).getUname() + "  " + userList.get(i).getFname());
            Log.d("User: ", usernames.get(i));
        }
    }

    public void fillListView()
    {
        adapter=new UserListAdapter(this, userList);
        //set the listview's adapter
        ma_lv_j_users.setAdapter(adapter);
    }
}