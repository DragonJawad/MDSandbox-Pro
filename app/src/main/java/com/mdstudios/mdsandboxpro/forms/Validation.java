package com.mdstudios.mdsandboxpro.forms;

/**
 * Created by jawad on 25/08/14.
 */
public class Validation {

    //--Checks if source is A-Z|a-z|0-9|_|.|-| |--
    public static boolean isName(CharSequence source){
        for(int i = 0; i < source.length(); i++){
            // Get the character itself, to decrease repetition
            char check = source.charAt(i);

            // Checks if valid character
            if(!Character.isLetterOrDigit(check) || check == '.' || check == '_'
                    || check == '-' || check == ' '){
                return false;
            }
        }

        return true;
    }

    //--Checks if source is A-Z|a-z|0-9|_|.|--
    public static boolean isUsernameOrPass(CharSequence source){
        for(int i = 0; i < source.length(); i++){
            // Get the character itself, to decrease repetition
            char check = source.charAt(i);

            // Checks if valid character
            if(!Character.isLetterOrDigit(check) || check == '.' || check == '_'){
                return false;
            }
        }

        return true;
    }

    //--Simple check if source is empty--
    public static boolean isEmpty(CharSequence source){
        if(source == "")
            return true;

        return false;
    }
}
