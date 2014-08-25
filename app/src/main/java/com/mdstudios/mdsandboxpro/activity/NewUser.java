package com.mdstudios.mdsandboxpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mdstudios.mdsandboxpro.R;
import com.mdstudios.mdsandboxpro.forms.Form;
import com.mdstudios.mdsandboxpro.utils.Base;

/**
 * Created by jawad on 24/08/14.
 *
 * TODO: Keep data saved if orientation changes
 */
public class NewUser extends Activity {
    // Tag to be used with logging
    private final String TAG = Base.TAG_BASE+"NewUser";

    // Respective form fields
    EditText mFirstName;
    EditText mLastName;
    EditText mUsername;
    EditText mPassword;

    // Form holds all fields and validates them as necessary
    Form mForm;

    // Toggled by UI checkbox, sets
    boolean skipPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_newuser);

        initViews(); // Initialize views
        initForm(); // Initialize form
    }

    //--Find and set the EditTexts
    public void initViews(){
        mFirstName = (EditText) findViewById(R.id.editFirstName);
        mLastName = (EditText) findViewById(R.id.editLastName);
        mUsername = (EditText) findViewById(R.id.editUsername);
        mPassword = (EditText) findViewById(R.id.editPassword);
    }

    //--Initialize the form--
    public void initForm(){
        mForm = new Form(getResources());

        // Add the EditTexts as fields and specify validation mode
        mForm.addField(mFirstName, Form.VALID_NAME);
        mForm.addField(mLastName, Form.VALID_NAME);
        mForm.addField(mUsername, Form.VALID_USERPASS);
        mForm.addField(mPassword, Form.VALID_USERPASS);
    }

    //--Activated by UI checkbox--
    public void onCheckboxClicked(View view){

    }
    //--Activated by UI button, attempts to submit data--
    public void onSubmit(View view){
        Toast.makeText(this,"Not enabled yet",Toast.LENGTH_SHORT).show();

        /** Steps needed:
         *  1) Check user input for valid characters
         *  2) Check for duplicate username
         *  3) Save new user
         *
         *  TODO: Check if fields are empty first
         */

        // Check if input is valid
        if(mForm.validateAllFields()){
            Log.d(TAG,"All fields are valid");
        }
        else{
            Log.d(TAG,"Invalid field detected!");
        }
    }
}
