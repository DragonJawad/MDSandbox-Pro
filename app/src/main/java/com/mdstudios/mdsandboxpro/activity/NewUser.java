package com.mdstudios.mdsandboxpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mdstudios.mdsandboxpro.R;
import com.mdstudios.mdsandboxpro.forms.Form;
import com.mdstudios.mdsandboxpro.users.DbUsers;
import com.mdstudios.mdsandboxpro.utils.Base;

/**
 * Created by jawad on 24/08/14b
 *
 * TODO: Keep data saved if orientation changes
 */
public class NewUser extends Activity {
    // Tag to be used with logging
    private final String LOGTAG = Base.TAG_BASE+"NewUser";

    // User database instance
    DbUsers db = new DbUsers(this);

    // Respective form fields
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mUsername;
    private EditText mPassword;

    // Tag for fields that need special attention later
    private static final String TAG_USERNAME = "username";
    private static final String TAG_PASSWORD = "password";

    // Form holds all fields and validates them as necessary
    private Form mForm;

    // Toggled by UI checkbox, sets if password is necessary or not
    private boolean mUsePassword = false;

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
        mForm.addField(mUsername, Form.VALID_USERPASS, TAG_USERNAME);
        mForm.addField(mPassword, Form.VALID_USERPASS, TAG_PASSWORD);

        // Make sure the state of the mPassword field is disabled
        updatePasswordField();
    }

    //--Activated by UI checkbox--
    public void onCheckboxClicked(View view){
        // Pass the state of the checkbox to the proper variable
        mUsePassword = ((CheckBox)view).isChecked();

        // Update state of the password field
        updatePasswordField();

    }

    //--Activated by UI button, attempts to submit data--
    public void onSubmit(View view){
        Toast.makeText(this,"Not enabled yet",Toast.LENGTH_SHORT).show();

        /** Steps needed:
         *  1) Check user input for valid characters
         *  2) Check for duplicate username
         *  3) Save new user
         *
         */

        // If not using the password field, skip it when validating
        if(!mUsePassword){
            if(!mForm.validateAllFields(TAG_PASSWORD))
                return;
        }
        // Else validate all fields, and if any are invalid, abort submitting
        else{
            if(!mForm.validateAllFields(null))
                return;
        }

        // Open database that holds user information
        db.open();

        // Check if username exists already, and if so, abort with message
        if(db.checkIfUserExists(mUsername.getText().toString())){
            // Tell the user that the username is in use
            mForm.invalidateFieldByTag(TAG_USERNAME,
                    getResources().getString(R.string.error_duplicate_username));

            // Close database, else the world will end... eventually
            db.close();

            return;
        }


        // If skipping password, make sure to pass in null
            // TODO: TEST IF NULL WORKS AS EXPECTED IN DATABASE
        String password;
        if(mUsePassword)
            password = mPassword.getText().toString();
        else
            password = null;

        // Save the new user
        db.insertUser(
                mUsername.getText().toString(), // Username
                password,                       // Password
                mFirstName.getText().toString(),// First name
                mLastName.getText().toString()  // Last name
        );

        // Make sure to close the database
        db.close();
    }

    //--Update state of the password field, based off of mUsePassword variable--
    public void updatePasswordField(){
        // If using the password field, enable it
        if(mUsePassword){
            mPassword.setEnabled(true);
            mPassword.setBackgroundColor(getResources().getColor(R.color.white));
        }
        // Else, disable the field
        else{
            mPassword.setEnabled(false);
            mPassword.setBackgroundColor(getResources().getColor(R.color.gray));
        }
    }
}
