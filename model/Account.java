package com.banking.model;

import java.util.Date;

public class Account {

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        Account.userId = userId;
    }
    private int id;
    private static int userId;
    private long account_number;
    private  String IFSC_code;
    private String customer_name;
    private String gender;
    private String marriage_Status;
    private String S_O_W_H_O;
    private long mobile;
    private Date DOB;
     private String address;
     private Date opening;
     private long pin_code;
     private String branch_code;
     private String branch_location;
        private double balance;

    public Account() {
        this.DOB = DOB;
        this.IFSC_code = IFSC_code;
        this.S_O_W_H_O = S_O_W_H_O;
        this.account_number = account_number;
        this.address = address;
        this.balance = balance;
        this.branch_code = branch_code;
        this.branch_location = branch_location;
        this.customer_name = customer_name;
        this.gender = gender;
        this.id = id;
        this.marriage_Status = marriage_Status;
        this.mobile = mobile;
        this.opening = opening;
        this.pin_code = pin_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(long account_number) {
        this.account_number = account_number;
    }

    public String getIFSC_code() {
        return IFSC_code;
    }

    public void setIFSC_code(String IFSC_code) {
        this.IFSC_code = IFSC_code;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarriage_Status() {
        return marriage_Status;
    }

    public void setMarriage_Status(String marriage_Status) {
        this.marriage_Status = marriage_Status;
    }

    public String getS_O_W_H_O() {
        return S_O_W_H_O;
    }

    public void setS_O_W_H_O(String S_O_W_H_O) {
        this.S_O_W_H_O = S_O_W_H_O;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOpening() {
        return opening;
    }

    public void setOpening(Date opening) {
        this.opening = opening;
    }

    public long getPin_code() {
        return pin_code;
    }

    public void setPin_code(long pin_code) {
        this.pin_code = pin_code;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getBranch_location() {
        return branch_location;
    }

    public void setBranch_location(String branch_location) {
        this.branch_location = branch_location;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    
       
    
}
