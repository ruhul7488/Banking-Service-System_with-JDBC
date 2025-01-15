package com.banking.dao;

import com.banking.model.Account;
import com.banking.model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface AccountDAO {
    Account getAccountByUserId(int userId) throws SQLException;
    void deposit(int accountId, double amount) throws SQLException;
    void withdraw(int accountId, double amount) throws SQLException;
    List<Transaction> getTransactionHistory(int accountId) throws SQLException;
}
