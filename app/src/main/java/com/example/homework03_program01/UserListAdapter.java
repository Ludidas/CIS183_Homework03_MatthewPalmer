package com.example.homework03_program01;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Users> listOfUsers;

    public UserListAdapter(Context c, ArrayList<Users> ls)
    {
        //Passed to the class from MainActivity.java
        context=c;
        listOfUsers=ls;
    }


    @Override
    public int getCount() {return listOfUsers.size();}

    @Override
    public Object getItem(int i) {return listOfUsers.get(i);}

    @Override
    public long getItemId(int i) {return i;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view==null)
        {
            LayoutInflater mInflater=(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view=mInflater.inflate(R.layout.custom_cell, null);
        }

        //find the GUI elements in my custom_cell
        TextView username=view.findViewById(R.id.cc_tv_v_username);
        TextView fullName=view.findViewById(R.id.cc_tv_v_fullName);

        Users user=listOfUsers.get(i);

        //set the GUI for the custom_cell.xml
        username.setText(user.getUname());
        fullName.setText(user.getFname() + " " + user.getLname());

        return view;
    }
}
