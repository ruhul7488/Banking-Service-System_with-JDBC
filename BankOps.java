// package com.banking;

// import com.banking.model.Account;
// import com.banking.model.Transaction;
// import com.banking.service.BankingService;

// import java.sql.SQLException;
// import java.util.InputMismatchException;
// import java.util.List;
// import java.util.Scanner;

//  public class BankOps 
//   {

//     private static BankingService bankingService = new BankingService();
//     private static Scanner scanner = new Scanner(System.in);
       
//     static void ops(){
//         System.out.println("\n==================== Banking System ====================");
//         System.out.println("1. View Account Details");
//         System.out.println("2. Deposit Funds");
//         System.out.println("3. Withdraw Funds");
//         System.out.println("4. Transfer Funds");
//         System.out.println("5. View Transaction History");
//         System.out.println("6. Exit");
//         System.out.print("Enter your choice: ");

//        int choise=scanner.nextInt();

//        while (true) {
        
//         switch (choise) {
//             case 1:
//             BankOps.viewAccountDetails();
//                 break;
//             case 2:
//                 depositFunds();
//                 break;
//             case 3:
//                 withdrawFunds();
//                 break;
//             case 4:
//                 transferFunds();
//                 break;
//             case 5:
//                 viewTransactionHistory();
//                 break;
//             case 6:
//                 System.out.println("Exiting the application. Thank you for using our banking system!");
//                 System.exit(0);
//                 break;
//             default:
//                 System.out.println("Invalid choice! Please select a valid option.");
//         }
//     }

//             private static  viewAccountDetails() {
//         System.out.println("enter user_id");
//         int userId=scanner.nextInt();
//         try {
//             Account account = bankingService.getAccountDetails(userId);
//             if (account != null) {
//                 System.out.println("Account ID: " + account.getId());
//                 System.out.println("Balance: " + account.getBalance());
//             } else {
//                 System.out.println("Account not found for user ID " + userId);
//             }
//         } catch (SQLException e) {
//             System.out.println("Error fetching account details: " + e.getMessage());
//         }
//     }


//     private static void depositFunds(int userId) {
//         System.out.println("enter user_id");
//         int userId=scanner.nextInt();
//         try {
//             Account account = bankingService.getAccountDetails(userId);
//             if (account != null) {
//                 System.out.print("Enter amount to deposit: ");
//                 double amount = scanner.nextDouble();
//                 if (amount <= 0) {
//                     System.out.println("Amount must be greater than 0.");
//                 } else {
//                     bankingService.deposit(account.getId(), amount);
//                     System.out.println("Deposited " + amount + " into your account.");
//                     System.out.println("Updated Balance: " + account.getBalance());
//                 }
//             } else {
//                 System.out.println("Account not found for user ID " + userId);
//             }
//         } catch (SQLException e) {
//             System.out.println("Error during deposit: " + e.getMessage());
//         }
//     }


//     private static void withdrawFunds(int userId) {
//         System.out.println("enter user_id");
//         int userId=scanner.nextInt();
//         try {
//             Account account = bankingService.getAccountDetails(userId);
//             if (account != null) {
//                 System.out.print("Enter amount to withdraw: ");
//                 double amount = scanner.nextDouble();
//                 if (amount <= 0) {
//                     System.out.println("Amount must be greater than 0.");
//                 } else if (amount > account.getBalance()) {
//                     System.out.println("Insufficient balance!");
//                 } else {
//                     bankingService.withdraw(account.getId(), amount);
//                     System.out.println("Withdrawn " + amount + " from your account.");
//                     System.out.println("Updated Balance: " + account.getBalance());
//                 }
//             } else {
//                 System.out.println("Account not found for user ID " + userId);
//             }
//         } catch (SQLException e) {
//             System.out.println("Error during withdrawal: " + e.getMessage());
//         }
//     }


//     private static void transferFunds(int userId) {
//         System.out.println("enter user_id");
//         int userId=scanner.nextInt();
//         try {
//             Account fromAccount = bankingService.getAccountDetails(userId);
//             if (fromAccount != null) {
//                 System.out.print("Enter the recipient account ID: ");
//                 int toAccountId = scanner.nextInt();
//                 Account toAccount = bankingService.getAccountDetails(toAccountId);
//                 if (toAccount != null) {
//                     System.out.print("Enter amount to transfer: ");
//                     double amount = scanner.nextDouble();
//                     if (amount <= 0) {
//                         System.out.println("Amount must be greater than 0.");
//                     } else if (amount > fromAccount.getBalance()) {
//                         System.out.println("Insufficient balance!");
//                     } else {
//                         bankingService.transfer(fromAccount.getId(), toAccountId, amount);
//                         System.out.println("Transferred " + amount + " to account ID " + toAccountId);
//                         System.out.println("Updated Balance: " + fromAccount.getBalance());
//                     }
//                 } else {
//                     System.out.println("Recipient account not found.");
//                 }
//             } else {
//                 System.out.println("Account not found for user ID " + userId);
//             }
//         } catch (SQLException e) {
//             System.out.println("Error during transfer: " + e.getMessage());
//         }
//     }


//     private static void viewTransactionHistory(int userId) {
//         System.out.println("enter user_id");
//         int userId=scanner.nextInt();
//         try {
//             Account account = bankingService.getAccountDetails(userId);
//             if (account != null) {
//                 List<Transaction> transactions = bankingService.getTransactionHistory(account.getId());
//                 if (transactions.isEmpty()) {
//                     System.out.println("No transactions found.");
//                 } else {
//                     System.out.println("\nTransaction History for Account ID: " + account.getId());
//                     for (Transaction transaction : transactions) {
//                         System.out.println("Transaction ID: " + transaction.getId());
//                         System.out.println("Type: " + transaction.getTransactionType());
//                         System.out.println("Amount: " + transaction.getAmount());
//                         System.out.println("Date: " + transaction.getTransactionDate());
//                         System.out.println("-------------------------");
//                     }
//                 }
//             } else {
//                 System.out.println("Account not found for user ID " + userId);
//             }
//         } catch (SQLException e) {
//             System.out.println("Error fetching transaction history: " + e.getMessage());
//         }
//     }





//     }   
// }
    

