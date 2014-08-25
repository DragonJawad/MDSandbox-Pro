package com.mdstudios.mdsandboxpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mdstudios.mdsandboxpro.R;

/**
 * Created by jawad on 24/08/14.
 *
 * TODO: Keep data saved if orientation changes
 */
public class NewUser extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_newuser);


    }

    // Activated by UI button
    public void submit(View view){
        Toast.makeText(this,"Not enabled yet",Toast.LENGTH_SHORT).show();

        /** Steps needed:
         *   1) Check user input for valid characters
         *  2) Check for duplicate username
         *  3) Save new user
         */



    }
}
