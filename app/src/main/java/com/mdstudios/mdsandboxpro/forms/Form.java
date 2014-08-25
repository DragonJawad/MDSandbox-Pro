package com.mdstudios.mdsandboxpro.forms;

import android.content.res.Resources;
import android.util.Log;
import android.widget.EditText;

import com.mdstudios.mdsandboxpro.R;
import com.mdstudios.mdsandboxpro.utils.Base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jawad on 25/08/14.
 *
 * Purpose: Manages all fields associated with a single "form"
 *
 */
public class Form {
    // Log constant
    private final String TAG = Base.TAG_BASE+"Form";

    // Validation mode constants
    public static final int VALID_NAME = 1;
    public static final int VALID_USERPASS = 2;

    // Error message constants
    private String INVALID_BLANK;
    private String INVALID_NAME;
    private String INVALID_USERPASS;

    // Holds every field under this form
    private List<Field> mFields = new ArrayList<Field>();

    //--Constructor--
    public Form(Resources resources){
        // Get the UI error strings
        INVALID_BLANK = resources.getString(R.string.error_blank);
        INVALID_NAME = resources.getString(R.string.error_invalid_name);
        INVALID_USERPASS = resources.getString(R.string.error_invalid_userpass);
    }

    //--Adds a new field to this form--
    public void addField(EditText editText, int validationMode){

        // Create the field and set its parameters
        Field field = new Field(editText, validationMode);
        // Add to the form's list of fields to manage
        mFields.add(field);
    }

    /**--Validates each field one by one--
    *  -> Returns true if all valid, false if at least one is false
    *  -> Tells each failed EditText to show error on itself
     */
    public boolean validateAllFields(){
        // Determines whether or not all fields are valid
        boolean valid = true;

        // Loops through all fields and validates one by one
        for(Field field : mFields){
            // Get the field input and validation mode
            String input = field.getFieldInput();
            int validMode = field.getValidMode();

            // Check if field is blank first
            if(input.equals("")){
                // Indicate at least one field is invalid
                valid = false;

                // Set the error to state that the field is blank
                field.setError(INVALID_BLANK);
            }
            // Then check if input has only valid characters
            else if(!validateText(input, validMode)){
                valid = false;

                // Set the error message for the field
                switch(validMode){
                    case VALID_NAME:
                        field.setError(INVALID_NAME);
                        break;
                    case VALID_USERPASS:
                        field.setError(INVALID_USERPASS);
                        break;
                    default:
                        field.setError("System Error! Please report!");
                        break;
                }
            }
        }

        return valid;
    }

    // Validates the text based off of specified validation mode
    public boolean validateText(String text, int validMode){
        // Returned value
        boolean valid = true;

        switch(validMode){
            case VALID_NAME:
                valid = Validation.isName(text);
                break;
            case VALID_USERPASS:
                valid = Validation.isUsernameOrPass(text);
                break;
            default:
                Log.e(TAG,"Invalid Mode: "+validMode);
                break;
        }

        return valid;
    }
}
