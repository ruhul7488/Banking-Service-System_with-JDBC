package com.banking;

import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.service.BankingService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

    private static BankingService bankingService = new BankingService();
    private static Scanner scanner = new Scanner(System.in);
     public static int userId;
    public static void main(String[] args) {
        System.out.println("enter userId");
          userId = scanner.nextInt();

        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    viewAccountDetails(userId);
                    break;
                case 2:
                    depositFunds(userId);
                    break;
                case 3:
                    withdrawFunds(userId);
                    break;
                case 4:
                    transferFunds(userId);
                    break;
                case 5:
                    viewTransactionHistory(userId);
                    break;
                case 6:
                    System.out.println("Exiting the application. Thank you for using our banking system!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n==================== Banking System ====================");
        System.out.println("1. View Account Details");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Transfer Funds");
        System.out.println("5. View Transaction History");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear the invalid input
            System.out.println("Invalid input! Please enter a number.");
        }
        return choice;
    }

    private static void viewAccountDetails(int userId) {
        try {
            Account account = BankingService.getAccountDetails(userId);
            if (account != null) {
                System.out.println("Account ID: " + account.getId());
                System.out.println("User_ID: " + Account.getUserId());
                System.out.println("Balance: " + account.getBalance());

            } else {
                System.out.println("Account not found for user ID " + userId);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching account details: " + e.getMessage());
        }
    }

    private static void depositFunds(int userId) {
        try {
            Account account = BankingService.getAccountDetails(userId);
            if (account != null) {
                System.out.print("Enter amount to deposit: ");
                double amount = scanner.nextDouble();
                if (amount <= 0) {
                    System.out.println("Amount must be greater than 0.");
                } else {
                    
                    bankingService.deposit(account.getId(), amount);
                    System.out.println("Deposited " + amount + " into your account.");
                    Account account1 = BankingService.getAccountDetails(userId);
                    System.out.println("Updated Balance: " + account1.getBalance());
                }
            } else {
                System.out.println("Account not found for user ID " + userId);
            }
        } catch (SQLException e) {
            System.out.println("Error during deposit: " + e.getMessage());
        }
    }

    private static void withdrawFunds(int userId) {
        try {
            Account account = BankingService.getAccountDetails(userId);
            if (account != null) {
                System.out.print("Enter amount to withdraw: ");
                double amount = scanner.nextDouble();
                if (amount <= 0) {
                    System.out.println("Amount must be greater than 0.");
                } else if (amount > account.getBalance()) {
                    System.out.println("Insufficient balance!");
                } else {
                    bankingService.withdraw(account.getId(), amount);
                    System.out.println("Withdrawn " + amount + " from your account.");
                    Account account1 = BankingService.getAccountDetails(userId);
                    System.out.println("Updated Balance: " + account1.getBalance());
                }
            } else {
                System.out.println("Account not found for user ID " + userId);
            }
        } catch (SQLException e) {
            System.out.println("Error during withdrawal: " + e.getMessage());
        }
    }


    private static void transferFunds(int userId) {
        try {
            Account fromAccount = BankingService.getAccountDetails(userId);
            if (fromAccount != null) {
                int toAccountId;
                Account toAccount;
    
                while (true) {
                    System.out.print("Enter the recipient account ID: ");
                    toAccountId = scanner.nextInt();
                    toAccount = BankingService.getAccountDetails(toAccountId);
    
                    if (toAccountId == fromAccount.getId()) {
                        System.out.println("You cannot transfer money to your own account!");
                    } else if (toAccount != null) {
                        break;
                    } else {
                        System.out.println("Recipient account not found.");
                    }
                }
    
                System.out.print("Enter amount to transfer: ");
                double amount = scanner.nextDouble();
                if (amount <= 0) {
                    System.out.println("Amount must be greater than 0.");
                } else if (amount > fromAccount.getBalance()) {
                    System.out.println("Insufficient balance!");
                } 
                else { 
                    BankingService.transfer( toAccountId, amount);
                    System.out.println("Transferred " + amount + " to account ID " + toAccountId);
                    Account fromaccount11 = BankingService.getAccountDetails(userId);
                    System.out.println("Updated Balance: " + fromaccount11.getBalance());
                }
            } else {
                System.out.println("Account not found for user ID " + userId);
            }
        } catch (SQLException e) {
            System.out.println("Error during transfer: " + e.getMessage());
        }
    }
    

    private static void viewTransactionHistory(int userId) {
        try {
            Account account = BankingService.getAccountDetails(userId);
            if (account != null) {
                List<Transaction> transactions = bankingService.getTransactionHistory(account.getId());
                if (transactions.isEmpty()) {
                    System.out.println("No transactions found.");
                } else {
                    System.out.println("\nTransaction History for Account ID: " + account.getId());
                    for (Transaction transaction : transactions) {
                        System.out.println("Transaction ID: " + transaction.getId());
                        System.out.println("Type: " + transaction.getTransactionType());
                        System.out.println("Amount: " + transaction.getAmount());
                        System.out.println("Date: " + transaction.getTransactionDate());
                        System.out.println("-------------------------");
                    }
                }
            } else {
                System.out.println("Account not found for user ID " + userId);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching transaction history: " + e.getMessage());
        }
    }
}
