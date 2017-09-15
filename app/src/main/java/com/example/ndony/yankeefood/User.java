package com.example.ndony.yankeefood;

/**
 * Created by ndony on 7/26/2017.
 */

public class User {
    String FullName, username,password, location;

    public User( String FullName,String username, String password,String location){
        this.FullName=FullName;
        this.location=location;
        this.password=password;
        this.username=username;
    }
    public User(String username,String password){
        this.username=username;
        this.password=password;
        this.location="";
        this.FullName="";
    }
}
