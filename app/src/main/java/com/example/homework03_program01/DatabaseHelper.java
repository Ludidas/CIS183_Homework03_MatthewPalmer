package com.example.homework03_program01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME="UserAccounts.db";
    private static final String TABLE_NAME="Users";

    public DatabaseHelper (Context context)
    {
        //We will use this to create the database
        //it accepts the context, the name, factory (leave null), and version number
        //if your database becomes corrupt or the information in the database is wrong
        //change the version number
        //super is used to call the functionality of the base class SQLiteOpenHelper and
        //then executes the extended (DatabaseHelper)

        super(context,DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Create table in the database
        //Execute the SQL statement on the database that wass passed to the function OnCreated called db
        //This can be tricky becuase we have to write our SQL statements as strings

        //3 attributes: username, first name, and last name
        //all 3 attributes will be TEXT and username will be the primary key
        //username has to be unique

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (username TEXT PRIMARY KEY NOT NULL, password TEXT, firstname TEXT, lastname TEXT, email TEXT, age TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        //This is used if we change the version number of the database.

        //delete the table if you upgrade the database (change the version number in the constructor)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");

        //create a new table once the old table has been deleted.
        onCreate(db);
    }

    public boolean initializeDB()
    {
        if(numberOfRowsInTable() == 0)
        {
            //connect to the database
            //notice we are getting a writable database because we need to insert information into our database
            SQLiteDatabase db = this.getWritableDatabase();

            //execute insert statements
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('mpalmer','gamerfart2004','Matthew','Palmer','mpalmer2@my.monroeccc.edu','20');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('rbaker','jackstauber20','Renee','Baker','rbaker@my.monroeccc.edu','16');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('jpalmer','enalover2254','Jessica','Palmer','jpalmer4@my.monroeccc.edu','18');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('rravengard','shadowsamongus67','Rose','Ravengard','rravengard@gmail.com','21');");

            db.close();

            return true;
        }
        else
        {
            return false;
        }
    }

    public int numberOfRowsInTable()
    {
        //Look at the database we created
        //get a readable version
        SQLiteDatabase db =this.getReadableDatabase();
        //store the number of records in the table called TABLE_NAME
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        //close the database
        db.close();

        return numRows;
    }

    @SuppressLint("Range")
    public ArrayList<Users> getAllRows()
    {
        //This will be used to store the info that the table returns
        ArrayList<Users> listUsers = new ArrayList<Users>();

        //query to get all rows and attributes from our table
        //select * means get all attributes
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY username;";

        //get an instance of a readable database and store it in db
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query. Cursor will be used to cycle through the results
        Cursor cursor = db.rawQuery(selectQuery, null);

        //used to store attributes
        String fname;
        String pword;
        String lname;
        String uname;
        String email;
        String age;

        //if there was something returned move the cursor to the beginning of the list
        if(cursor.moveToFirst())
        {
            do
            {
                //find the username column from the returned results
                uname = cursor.getString(cursor.getColumnIndex("username"));

                //find the password column from the returned results
                pword = cursor.getString(cursor.getColumnIndex("password"));

                //find the firstname column from the returned results
                fname = cursor.getString(cursor.getColumnIndex("firstname"));

                //find the lastname column from the returned results
                lname = cursor.getString(cursor.getColumnIndex("lastname"));

                //find the email column from the returned results
                email = cursor.getString(cursor.getColumnIndex("email"));

                //find the age column from the returned results
                age = cursor.getString(cursor.getColumnIndex("age"));

                //add the returned results to my list
                listUsers.add(new Users(uname, pword, fname, lname, email, age));
            }
            while(cursor.moveToNext());
        }

        db.close();

        return listUsers;
    }

    public void addNewUser(Users u)
    {
        //get an instance of a writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //This line is a little complicated the sql statement should look as follows:
        //INSERT INTO users VALUES('zmoore','Zack','Moore');

        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('" + u.getUname() + "','" + u.getPword() + "','" + u.getFname() + "','" + u.getLname() + "','" + u.getEmail() + "','" + u.getAge() + "');");
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllUsernames()
    {
        ArrayList<String> usernames = new ArrayList<String>();

        //query to get all usernames from table
        String selectUserNames = "SELECT username FROM " + TABLE_NAME + " ORDER BY username;";

        //get instance of a readable database and store in db
        SQLiteDatabase db = this.getReadableDatabase();

        //execute query.  Cursor will be used to cycle through the results
        Cursor cursor = db.rawQuery(selectUserNames, null);

        String username;

        //if there was something returned move the cursor to the beginning of the list
        if(cursor.moveToFirst())
        {
            do
            {
                username = cursor.getString(cursor.getColumnIndex("username"));

                usernames.add(username);
            }
            while(cursor.moveToNext());
        }
        //close database.
        db.close();

        return usernames;
    }


    //used to delete a specific user
    //this will be passed a username because it is our primary key
    //you MUST delete off the primary key
    public void deleteUser(String uName)
    {
        //get an instance of our database
        //need to be writeable
        SQLiteDatabase db = this.getWritableDatabase();

        //create our delete command
        //DELETE FROM users WHERE username = 'zmoore';
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE username = '" + uName + "';");

        //close the database
        db.close();
    }

    public void updateUser(Users u)
    {
        //get writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create out update command
        //needs to look like this:
        //UPDATE users SET firstname = 'Zack' , lastname = 'Moore' WHERE username = 'zmoore';
        String updateCommand = "UPDATE " + TABLE_NAME + " SET password = '" + u.getPword() + "' , firstname = '" + u.getFname() + "' , lastname = '"
                + u.getLname() + "' , email = '" + u.getEmail() + "' , age = '" + u.getAge() + "' WHERE username = '" + u.getUname() + "';";

        db.execSQL(updateCommand);
        db.close();
    }
}
