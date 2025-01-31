package com.banking.model;

public class User {
    private static int id;
    private String username;
    private String password;
    private String name;
    private long adhar_number;
    private String pan_number;
    private long mobile;

    public User(int id,String username,String password,String name,long adhar_number,String pan_number, long mobile) {
        this.adhar_number = adhar_number;
        this.id = id;
        this.mobile = mobile;
        this.name = name;
        this.pan_number = pan_number;
        this.password = password;
        this.username = username;
    }


    public int getId() {
        return id;
    }

    public static void setId(int id) {
        User.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAdhar_number() {
        return adhar_number;
    }

    public void setAdhar_number(long adhar_number) {
        this.adhar_number = adhar_number;
    }

    public String getPan_number() {
        return pan_number;
    }

    public void setPan_number(String pan_number) {
        this.pan_number = pan_number;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }


    
}
