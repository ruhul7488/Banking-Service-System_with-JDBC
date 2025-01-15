package com.banking.service;

import com.banking.dao.AccountDAO;
import com.banking.dao.AccountDAOImpl;
import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.App;
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
}
