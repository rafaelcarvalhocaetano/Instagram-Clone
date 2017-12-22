package com.back4app.quickstartexampleapp;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.app.Application;
import android.util.Log;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Habilite armazenamento local.
        //Parse.enableLocalDatastore(this);

        // Codigo de configuraÃ§Ã£o do App
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("zkxnVnPH7SySNX7MZF0UrwHx2fSygvoO3WoTrbvl")
                .clientKey("HL2TbO7HRhRgLNCxTJmRSLymXE9FeYmKDUZ1Ho22")
                .server("https://parseapi.back4app.com/")
                .build()
        );

        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL( defaultACL, true );
    }
}
