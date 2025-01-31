

package com.banking;

import com.banking.BankOps.BankOps;
import java.util.Scanner;

public class App {

    private static Scanner scanner = new Scanner(System.in);
    public static int userId = -1;
    public static String password;
    public static String username;

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println("|                                       |");
        System.out.println("|          For LOGIN Click 1            |");
        System.out.println("|                or                     |");
        System.out.println("|   Create New Account  Click 2         |");
        System.out.println("|                                       |");
        System.out.println("| ======================================|");

        int ch = scanner.nextInt();
        scanner.nextLine(); 

        while (true) {
            switch (ch) {
                case 1:
                    
                    while (userId == -1) {
                        System.out.println("Enter username:");
                        username = scanner.nextLine();  

                        System.out.println("Enter password:");
                        password = scanner.nextLine();  

                        
                        userId = BankOps.login( password , username);
                    }
                        if (userId != -1) {
                           
                            BankOps.Opps(userId);
                        } else {
                            
                            System.out.println("Login failed. Please try again.");
                        }
                    
                    break;

                    case 2:
                    BankOps.createNewAccount();
                    System.exit(0);
                    break;

                default:
                    throw new AssertionError();  
            }
        }
    }
}
