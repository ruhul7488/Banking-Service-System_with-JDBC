
package com.banking.BankOps;

import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.model.User;
import com.banking.service.BankingService;
import java.sql.Date;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BankOps {
    private static BankingService bankingService = new BankingService();
    private static Scanner scanner = new Scanner(System.in);
            
           

       public static void Opps(int userId) {
       

        while (true) {
            System.out.println("\n==================== Banking System ====================");
            System.out.println("1. View Account Details");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. Transfer Funds");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine(); 
                System.out.println("Invalid input! Please enter a number.");
                continue;  
            }

            
            if (choice == 1) {
                viewAccountDetails(userId);
            } else if (choice == 2) {
                depositFunds(userId);
            } else if (choice == 3) {
                withdrawFunds(userId);
            } else if (choice == 4) {
                transferFunds(userId);
            } else if (choice == 5) {
                viewTransactionHistory(userId);
            } else if (choice == 6) {
                System.out.println("Exiting the application. Thank you for using our banking system!");
                System.exit(0);
            } else {
                System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }
    
 
   
       
public static int login(String password, String username) {
    int userId = -1; 
    try {
        User user = bankingService.login_account(password, username);
        if (user != null) {
            userId = user.getId(); 
        } else {
            System.out.println("Invalid login credentials. Please try again.");
        }
    } catch (SQLException e) {
        System.out.println("Error during login: " + e.getMessage());
    }
    return userId;
}



public static void createNewAccount() {
    try {
         long account_number=accountNumber();
        System.out.println("Enter account details to create a new account:");

        System.out.println("======= Enter Full Name: =============");
        String customer_name = scanner.nextLine();

        System.out.println("======= Enter your Gender: =============");
         String gender=scanner.nextLine();

        System.out.println("========= Enter S_O_W_H_O: ==============");
          String S_O_W_H_O=scanner.nextLine();
        
        System.out.println("============= Enter Username: ==============");
        String username = scanner.nextLine();
        
        System.out.println("========== Create Password: ===============");
        String password = scanner.nextLine();
        
        System.out.println("=========== Enter Mobile Number: ============");
        long mobile = scanner.nextLong();
        scanner.nextLine();
        
        System.out.println("==== Enter Date of Birth (yyyy-mm-dd): =========");
        String dobInput = scanner.next();
        Date DOB = Date.valueOf(dobInput);  
        
        System.out.println("========== Enter PAN Card Number: ============");
        String pan_number = scanner.next();
        
        System.out.println("========= Enter Aadhaar Number: ==============");
        long adhar_number = scanner.nextLong();
        scanner.nextLine();

        System.out.println("========= Enter  marriage_Status: ==============");
        String  marriage_Status=scanner.nextLine();

        System.out.println("========= Enter Address: ==============");
         String address=scanner.nextLine();

        System.out.println("========= Enter  Pin Code: ==============");
        long pin_code =scanner.nextLong();
        scanner.nextLine();

        System.out.println("========= Enter Opening Date (yyy-mm-dd): ==============");
        String dobInput2 = scanner.next();
        Date opening= Date.valueOf(dobInput);
        
        System.out.println("========= Enter Initial Amount: ==============");
        double balance=scanner.nextDouble();

        System.out.println("==========================================");
        System.out.println("|                                         |");
        System.out.println("|       Click 1 for Save                  |");
        System.out.println("|              or                         |");
        System.out.println("|        Click 0 for Cancel               |");
        System.out.println("|                                         |");
        System.out.println("|=========================================");
         
        int op=scanner.nextInt();

         if(op==1){

        BankingService.createAccount( account_number, S_O_W_H_O, address, mobile, DOB, customer_name, opening, pin_code, gender, marriage_Status, balance, adhar_number, pan_number,  password, username);
        
        System.out.println("Account created successfully!");
        System.out.println("===============================================");
          System.out.println("| Full Name:        "+customer_name+"          ");
          System.out.println("|    Gender:        "+gender+"                 ");
          System.out.println("|  S_O_W_H_O:       "+S_O_W_H_O+"              ");
          System.out.println("|  Username  :      "+username+"               ");
          System.out.println("|    Password:      "+password+"               ");
          System.out.println("| Mobile Number:    "+mobile+"                ");
          System.out.println("|  Date of Birth:   "+DOB+"                   ");
          System.out.println("|  PanCard Number:  "+pan_number+"             ");
          System.out.println("| AdharCard Number: "+adhar_number+"           ");
          System.out.println("|  Marriage Status: "+marriage_Status+"        ");
          System.out.println("|    Address:       "+address+"                ");
          System.out.println("|    Pin Code:      "+pin_code+"               ");
          System.out.println("|   Opening Date:   "+opening+"                ");
          System.out.println("|  Current Balance: "+balance+"                ");
        System.out.println("|===============================================");


         }
         else{
            System.exit(0);

         }
        
    } catch (SQLException e) {
        System.out.println("Error while creating account: " + e.getMessage());
    }
}


public static long accountNumber() {
    Random random = new Random();
    
    long accountNumber = 0;
    
    for (int i = 0; i < 10; i++) {
        accountNumber = accountNumber * 10 + random.nextInt(10); 
    }
    
    return accountNumber;
}







    public static void viewAccountDetails(int userId) {
        try {
            Account account = bankingService.getAccountDetails(userId);
            if (account != null) {
    System.out.println("===============================================");
    System.out.println("               Accounts Details       ");
      System.out.println("  Account ID: " + account.getId());
      System.out.println("  User_ID: " + userId);
      System.out.println("  Account Number: "+account.getAccount_number());
      System.out.println("  Current Balance: " +account.getBalance());
      System.out.println("  IFSC Code :   " +account.getIFSC_code());
      System.out.println("  Account holder: " +account.getCustomer_name()+" ");
      System.out.println("   S/O: " + account.getS_O_W_H_O());
     System.out.println("   Phone Number: " + account.getMobile());
     System.out.println("   Date of Birth:" + account.getDOB());
     System.out.println("   Address:" + account.getAddress());
     System.out.println("   Issue Date: " +account.getOpening());
     System.out.println("   Pin code : " +account.getPin_code());
     System.out.println("   Branch code: " +account.getBranch_code());
     System.out.println("   Branch Location: " +account.getBranch_location());
    System.out.println("========================================================");


            } else {
                System.out.println("Account not found for user ID " + userId);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching account details: " + e.getMessage());
        }
    }

    public static void depositFunds(int userId) {
        try {
            Account account = bankingService.getAccountDetails(userId);
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

    public static void withdrawFunds(int userId) {
        try {
            Account account = bankingService.getAccountDetails(userId);
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

    public  static void transferFunds(int userId) {
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

    public static void viewTransactionHistory(int userId) {
        try {
            Account account = bankingService.getAccountDetails(userId);
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

    private static class Static {

        public Static() {
        }
    }
}

