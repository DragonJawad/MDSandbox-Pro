package com.mdstudios.mdsandboxpro.activity;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mdstudios.mdsandboxpro.R;

/**
 * Created by jawad on 21/08/14.
 */
public class WelcomeHome extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        // Hide the actionBar
        ActionBar actionBar = getActionBar();
        actionBar.hide();

    }

    // Launch activity to create a new user
        // Activated via UI button
    public void newUser(View view){
        Toast.makeText(this,"Making a new user!",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, NewUser.class);
        startActivity(intent);
    }
}
