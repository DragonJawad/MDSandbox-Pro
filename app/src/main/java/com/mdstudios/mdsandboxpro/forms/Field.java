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
    private int mValidationMode;

    //--Constructor, set basic values
    public Field(EditText editText, int validationMode){
        // Set passed in values
        this.mEditText = editText;
        this.mValidationMode = validationMode;
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

}
