package com.banking.dao;

import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.service.BankingService;
import com.banking.util.DatabaseConnection;
import java.util.Scanner;
// import com.banking.service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    @Override
    // public Account getAccountByUserId(int userId) throws SQLException {
    //     String sql = "SELECT * FROM accounts WHERE user_id = ?";
    //     try (Connection conn = DatabaseConnection.getConnection();
    //          PreparedStatement stmt = conn.prepareStatement(sql)) {
    //         stmt.setInt(1, userId);
    //         try (ResultSet rs = stmt.executeQuery()) {
    //             if (rs.next()) {
    //                 return new Account(rs.getInt("id"), rs.getInt("user_id"), rs.getDouble("balance"));
    //             }
    //         }
    //     }
    //     return null;
    // }

    public Account getAccountByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Account(rs.getInt("id"), rs.getInt("user_id"), rs.getDouble("balance"));
                }
            
        }
        catch(SQLException e){

        }
        return null;
    }


    @Override
    public void deposit(int accountId, double amount) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        String sql1="insert into transactions (account_id,transaction_type,amount) values(?,?,?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
        //    updating the transaction table for getting transaction history in future
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setInt(1,accountId);
            stmt1.setString(2,"Deposit");
            stmt1.setDouble(3,amount);
           int rowsAffected= stmt1.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Transaction recorded successfully.");
            } else {
                System.out.println(" transaction Failed!!!.");
            }

        }
          catch(SQLException e) {
            System.err.println("Error during deposit: " + e.getMessage());
          }


    }

    // public void deposit(int accountId, double amount) throws SQLException {
    //     String sql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
    //     try (Connection conn = DatabaseConnection.getConnection();
    //          PreparedStatement stmt = conn.prepareStatement(sql)) {
    //         stmt.setDouble(1, amount);
    //         stmt.setInt(2, accountId);
    //         stmt.executeUpdate();
    //     }
    // }
    

    @Override
    public void withdraw(int accountId, double amount) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        String sql1="insert into transactions (account_id,transaction_type,amount) values(?,?,?)";

        try{

            Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();

             //    updating the transaction table for getting transaction history in future
             PreparedStatement stmt1 = conn.prepareStatement(sql1);
             stmt1.setInt(1,accountId);
             stmt1.setString(2,"Withdraw");
             stmt1.setDouble(3,amount);
             int rowsAffected=stmt1.executeUpdate();

             if (rowsAffected > 0) {
                System.out.println("Transaction recorded successfully.");
            } else {
                System.out.println(" transaction Failed!!!.");
            }

        }
        catch(SQLException e) {
            System.err.println("Error during deposit: " + e.getMessage());
          }
    }

    @Override
    public List<Transaction> getTransactionHistory(int accountId) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE account_id = ? ORDER BY transaction_date DESC";
        List<Transaction> transactions = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountId);
            
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    transactions.add(new Transaction(rs.getInt("id"), rs.getInt("account_id"),
                            rs.getString("transaction_type"), rs.getDouble("amount"),
                            rs.getTimestamp("transaction_date")));
            
            }
        }
        catch(SQLException e) {
            System.err.println("Error during deposit: " + e.getMessage());
          }
        return transactions;
    }


}

