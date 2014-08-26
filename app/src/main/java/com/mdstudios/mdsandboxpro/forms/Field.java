package com.mdstudios.mdsandboxpro.forms;

import android.widget.EditText;

/**
 * Created by jawad on 25/08/14.
 *
 * Purpose: Easily add other types of fields other than EditText without breaking other code
 *              --> Self-contains actual "fields"
 */
public class Field {
    // The editText this field manages
    private EditText mEditText;
    // Specifies how this field should be validated
    private int mValidationMode;
    // Holds an optional tag for the field
    private String mTag;

    //--Constructor, set basic values
    public Field(EditText editText, int validationMode){
        // Set passed in values
        this.mEditText = editText;
        this.mValidationMode = validationMode;
    }

    //--Constructor with optional tag--
    public Field(EditText editText, int validationMode, String tag){
        // Call original constructor
        this(editText, validationMode);

        // Add the optional tag
        mTag = tag;
    }

    //--Displays an error next to an invalid EditText--
    public void setError(String error){
        mEditText.setError(error);
    }

    public String getFieldInput(){
        return mEditText.getText().toString();
    }

    public int getValidMode(){
        return mValidationMode;
    }

    public String getTag() { return mTag; }

}
