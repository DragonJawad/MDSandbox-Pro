package com.mdstudios.mdsandboxpro.activity;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mdstudios.mdsandboxpro.R;
import com.mdstudios.mdsandboxpro.users.DbUsers;
import com.mdstudios.mdsandboxpro.users.User;
import com.mdstudios.mdsandboxpro.users.UserListAdapter;

import java.util.List;

/**
 * Created by jawad on 21/08/14.
 */
public class WelcomeHome extends ListActivity {

    // Database that holds all the user data
    DbUsers db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        // Hide the actionBar
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        // Initialize the database
        db = new DbUsers(this);

        // Set up the listView
        initListView();
    }

    //--Initialize the list view and all its components as necessary--
    public void initListView(){
        // Get the data to pass to the adapter
        db.open();
        List<User> users = db.getAllDataList();
        db.close();

        // Set up the adapter
        UserListAdapter adapter = new UserListAdapter(this, users);

        // Set the listView to use the adapter
        setListAdapter(adapter);
    }

    // Launch activity to create a new user
        // Activated via UI button
    public void newUser(View view){
        Toast.makeText(this,"Making a new user!",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, NewUser.class);
        startActivity(intent);
    }
}
