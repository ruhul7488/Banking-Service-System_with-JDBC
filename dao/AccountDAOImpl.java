package com.banking.dao;

import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.model.User;
import com.banking.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

     
    public User login_account(String password,String username) throws SQLException{
        String sq=  "SELECT * FROM users WHERE username = ? AND password = ?";
           try {
            Connection con=DatabaseConnection.getConnection();
              PreparedStatement pstt=con.prepareStatement(sq);
              pstt.setString(1, username);
              pstt.setString(2, password);
              ResultSet rs=pstt.executeQuery();
              if(rs.next()){
                return new User(rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("customer_name"),
                rs.getLong("adhar_number"),
                rs.getString("pan_number"),
                rs.getLong("mobile"));
              }

              else {
                return null; 
            }

        
           } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        throw e;
           }
                   
    }
          




        public void createAccount(long account_number, String S_O_W_H_O, String address, long mobile, Date DOB, String customer_name, Date opening, long pin_code, String gender, String marriage_Status,double balance, long adhar_number, String pan_number, String password, String username) throws SQLException {

    String sql1 = "INSERT INTO users (username, password, customer_name, adhar_number, pan_number, mobile) VALUES (?, ?, ?, ?, ?, ?)";
    String sql2 = "INSERT INTO accounts (user_id, account_number, customer_name, gender, marriage_Status, S_O_W_H_O, mobile, DOB, address, opening, pin_code, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    Connection con = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    ResultSet rs = null;

    try {
        con = DatabaseConnection.getConnection();

       
        pst1 = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
        pst1.setString(1, username);
        pst1.setString(2, password);
        pst1.setString(3, customer_name);
        pst1.setLong(4, adhar_number);
        pst1.setString(5, pan_number);
        pst1.setLong(6, mobile);
        pst1.executeUpdate();

        
        rs = pst1.getGeneratedKeys();
        int userId = 0;
        if (rs.next()) {
            userId = rs.getInt(1); 
        }

        
        pst2 = con.prepareStatement(sql2);
        pst2.setInt(1, userId);  
        pst2.setLong(2, account_number);
        pst2.setString(3, customer_name);
        pst2.setString(4, gender);
        pst2.setString(5, marriage_Status);
        pst2.setString(6, S_O_W_H_O);
        pst2.setLong(7, mobile);
        pst2.setDate(8, DOB);
        pst2.setString(9, address);
        pst2.setDate(10, opening);
        pst2.setLong(11, pin_code);
        pst2.setDouble(12, balance);
        pst2.executeUpdate();

        System.out.println("Account created successfully with account number: " + account_number);

    } catch (SQLException e) {
        System.out.println("Error while creating account: " + e.getMessage());
    } finally {
       
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pst1 != null) {
            try {
                pst1.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pst2 != null) {
            try {
                pst2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


    public Account getAccountByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        Account account=null;
        try {
            Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    
                    account = new Account();
                    account.setId(rs.getInt("id"));
                    account.setAccount_number(rs.getLong("account_number"));
                    account.setBalance(rs.getDouble("balance"));
                    account.setIFSC_code(rs.getString("IFSC"));
                    account.setCustomer_name(rs.getString("customer_name"));
                    account.setS_O_W_H_O(rs.getString("S_O_W_H_O"));
                    account.setGender(rs.getString("gender"));
                    account.setAddress(rs.getString("address"));
                    account.setMobile(rs.getLong("mobile"));
                    account.setDOB(rs.getDate("dob"));
                    account.setPin_code(rs.getLong("pin_code"));
                    account.setBranch_code(rs.getString("branch_code"));
                    account.setBranch_location(rs.getString("branch_location"));
                    account.setOpening(rs.getDate("opening"));
                }
                else{
                    return null;
                }
            
        }
        catch(SQLException e){
            System.out.println("Error during login: " + e.getMessage());
            throw e;
        }
        return account;
        
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

