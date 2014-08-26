package com.mdstudios.mdsandboxpro.users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jawad on 22/08/14.
 */
public class DbUsers {
    // Database constants
    private static final String DATABASE_NAME = "Users";
    private static final int DATABASE_VERSION = 1;
    /** Database Version Control
     * 1: @Keys: ROWID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME
     */

    private static final String DATABASE_TABLE = "tblUsers";
    public static final String KEY_ROWID = "_id";
    public static final String KEY_USERNAME = "Username";
    public static final String KEY_PASSWORD = "Password";   // If null, = no need for password
    public static final String KEY_FIRSTNAME = "FirstName";
    public static final String KEY_LASTNAME = "LastName";

    private static final String DATABASE_CREATE =
            "create table "+DATABASE_NAME+" ("+KEY_ROWID+" integer primary key autoincrement, "+
            KEY_USERNAME+" text not null, "+KEY_PASSWORD+" text, "+KEY_FIRSTNAME+" text not null, " +
                    KEY_LASTNAME+" text not null );";

    private DatabaseHelper mDbHelper; // Used to directly handle database
    private SQLiteDatabase mDb; // The actual database instance

    private final Context mContext; // Store a passed in context for future use

    private static final String TAG = "MD/"+DATABASE_NAME;


    //--Constructor to create a new database adapter--
    public DbUsers(Context context)
    {
        this.mContext = context;
        mDbHelper = new DatabaseHelper(mContext);
    }

    //--Manages the direct. basic database management--
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion
                    + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS tblRandomQuotes");

        }
    }

    //--Opens the database--
    // TODO: Figure out why originally returned DbUsers instance
    public DbUsers open() throws SQLException {
        mDbHelper = new DatabaseHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    //--Closes the database--
    public void close() {
        mDbHelper.close();
    }

    //--Add a user to the database, returns id of row--
    public long insertUser(String username, String password, String firstName, String lastName){
        ContentValues values = new ContentValues();

        // Insert values in key:value format
        values.put(KEY_USERNAME,username);
        values.put(KEY_PASSWORD,password);
        values.put(KEY_FIRSTNAME,firstName);
        values.put(KEY_LASTNAME,lastName);

        // Finally add the values to the database itself and return the id
        return mDb.insert(DATABASE_TABLE, null, values);
    }

    //--Check if a user exists in the database--
    public boolean checkIfUserExists(String username){
        // equivalent: "SELECT * FROM table WHERE key_username = username

        // Column to be used for the query
        String[] columns = {KEY_USERNAME};
        // Where portion statement
        String where = KEY_USERNAME+"="+username;

        // Query for the username, and the results are in a cursor object
        Cursor cursor = mDb.query(DATABASE_TABLE, columns, where, null, null, null, null);

        // If there is at least one row in the cursor, then the username exists
        if(cursor.getCount() > 0){
            return true;
        }
        else
            return false;
    }

}
