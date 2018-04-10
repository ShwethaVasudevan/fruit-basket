package com.example.shwethavasudevan.model;

/**
 * Created by shwethavasudevan on 07/04/18.
 */

public class User {
    public String username;
    public String password;
    public String email;
    public String dob;
    public String address;

    public User(String username,String password, String email, String dob, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.address = address;
    }

}
