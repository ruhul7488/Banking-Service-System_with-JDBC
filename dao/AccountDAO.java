package com.banking.dao;

import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.model.User;
import java.sql.*;
import java.util.List;

public interface AccountDAO {
    Account getAccountByUserId(int userId) throws SQLException;
    void deposit(int accountId, double amount) throws SQLException;
    void withdraw(int accountId, double amount) throws SQLException;
    List<Transaction> getTransactionHistory(int accountId) throws SQLException;
    void createAccount(  long account_number, String S_O_W_H_O, String address, long mobile, Date DOB, String customer_name, Date opening, long pin_code, String gender, String marriage_Status, double balance,long adhar_number,String pan_number, String password,String username)throws SQLException;
    User login_account(String password,String username)throws SQLException;
}
