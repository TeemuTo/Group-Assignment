package com.example.groupassignment;

public class User {
    private String city;
    private String email;
    private String username;
    private String password;

    public User(String u, String p, String c, String e){
        username = u;
        password = p;
        city = c;
        email = e;
    }

    public String getUsername(){return username;}
    public String getPassword(){return password;}
    public String getCity(){return city;}
    public String getEmail(){return email;}
}
