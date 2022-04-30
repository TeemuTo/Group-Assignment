package com.example.groupassignment;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;


public class UserData {

    public static int passwordLength = 12;
    public String user;

    //Singleton
    private static UserData instance = null;

    public static UserData getInstance(){
        if(instance == null) {
            instance = new UserData();
        }
        return instance;
    }

    private ArrayList<User> users;

    public UserData(){
        users = new ArrayList<>();

    }


    public boolean addUser(String username, String password, String city, String email) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-512");

        if(checkPassword(password) && !username.isEmpty()) {
            md.update(password.getBytes());

            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for(byte b : digest){
                sb.append(String.format("%02x",b & 0xff));
            }
            users.add(new User(username, sb.toString(), city, email));
            return true;
        }
        else{
            return false;
        }

    }

    public boolean findUser(String username, String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for(byte b : digest){
            sb.append(String.format("%02x",b & 0xff));
        }
        for(int i=0;i<users.size();i++){
            User user = users.get(i);
            if(user.getUsername().equals(username) && user.getPassword().equals(sb.toString())){
                return true;
            }
        }
        return false;
    }

    public void addUserList(ArrayList<User> list){
        if(list.size()==0){

        }
        else {
            for (int i = 0; i < list.size(); i++) {
                User item = list.get(i);
                users.add(new User(item.getUsername(), item.getPassword(), item.getCity(), item.getEmail()));
            }
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUser(String username){
        this.user = username;
    }

    public String getUser(){
        return user;
    }

    public static boolean checkPassword(String password){

        if(password.length() < passwordLength) return false;

        int countC = 0;
        int countI = 0;

        for(int i=0;i<password.length();i++){
            char c = password.charAt(i);

            if(checkNum(c)){countI++;}
            else if (checkCapital(c)){countC++;}
            else return false;
        }
        return (countC >=2 && countI >=2);
    }
    public static boolean checkCapital(char c){
        c = Character.toUpperCase(c);
        return (c>= 'A' && c<= 'Z');
    }
    public static boolean checkNum(char c){
        return (c>='0' && c<='9');
    }

}
