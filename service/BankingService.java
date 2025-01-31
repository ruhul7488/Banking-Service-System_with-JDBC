package com.banking.service;

import com.banking.App;
import com.banking.dao.AccountDAO;
import com.banking.dao.AccountDAOImpl;
import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.model.User;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
public class BankingService {

    private static AccountDAO accountDAO = new AccountDAOImpl();
    
        public static Account getAccountDetails(int userId) throws SQLException {
            return accountDAO.getAccountByUserId(userId);
    }

    public void deposit(int accountId, double amount) throws SQLException {
        accountDAO.deposit(accountId, amount);
    }

    public void withdraw(int accountId, double amount) throws SQLException {
        accountDAO.withdraw(accountId, amount);
    }

    public  static void transfer( int toAccountId, double amount) throws SQLException {
        
        Account fromAccountId=accountDAO.getAccountByUserId(App.userId);
        accountDAO.withdraw(fromAccountId.getId(), amount);
        accountDAO.deposit(toAccountId, amount);
    }

    public List<Transaction> getTransactionHistory(int id) throws SQLException{
        return accountDAO.getTransactionHistory(id); 
    }
     public static User login_account(String password,String username) throws SQLException{
        return accountDAO.login_account(password, username);
      }

      public static void createAccount(  long account_number, String S_O_W_H_O, String address, long mobile, Date DOB, String customer_name, Date opening, long pin_code, String gender, String marriage_Status, double balance,long adhar_number,String pan_number, String password,String username)throws SQLException{

        accountDAO.createAccount( account_number, S_O_W_H_O, address, mobile, DOB, customer_name, opening, pin_code, gender, marriage_Status, balance, adhar_number, pan_number,  password, username);
      }

    }

