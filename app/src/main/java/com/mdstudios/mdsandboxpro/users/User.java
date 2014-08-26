package com.mdstudios.mdsandboxpro.users;

/**
 * Created by jawad on 26/08/14.
 *
 * Purpose: Structured data to easily pass between different classes
 */
public class User {
    private int _id = -1;       // Row id from database
    private String mUsername;
    private String mPassword;
    private String mFirstName;
    private String mLastName;

    //--Basic constructor--
    public User(String username, String password, String firstName, String lastName){
        this.mUsername = username;
        this.mPassword = password;
        this.mFirstName = firstName;
        this.mLastName = lastName;
    }

    //--Preferred constructor with id--
    public User(int id, String username, String password, String firstName, String lastName){
        this(username, password, firstName, lastName);
        this._id = id;
    }

    public String getUsername(){ return mUsername; }
    public String getPassword(){ return mPassword; }
    public String getFirstName(){ return mFirstName; }
    public String getLastName(){ return mLastName; }
}
