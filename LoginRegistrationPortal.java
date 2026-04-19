package loginregistrationportal;
import java.util.Scanner;
import java.util.regex.Pattern;

public class LoginRegistrationPortal {

    static Scanner input = new Scanner(System.in);

    static String fullName, lastName, username, cellNumber, password;

    public static void main(String[] args) {

        System.out.println("=== ACCOUNT REGISTRATION ===");
        registerUser();

        System.out.println("\n=== LOGIN PORTAL ===");
        loginUser();
    }

    // METHOD: Prevent empty input
    public static String getNonEmptyInput(String message) {
        String value;

        while (true) {
            System.out.print(message);
            value = input.nextLine();

            if (value != null && !value.trim().isEmpty()) {
                return value.trim();
            } else {
                System.out.println("Field cannot be empty! Please try again.");
            }
        }
    }

    // REGISTRATION
    public static void registerUser() {

        fullName = getNonEmptyInput("Enter Full Names: ");
        lastName = getNonEmptyInput("Enter Last Name: ");

        // Username validation
        while (true) {
            username = getNonEmptyInput("Enter Username (5 characters & must contain '_'): ");

            if (username.length() == 5 && username.contains("_")) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Invalid username! Must be exactly 5 characters and include '_'.");
            }
        }

        // SA Cell number validation
        while (true) {
            cellNumber = getNonEmptyInput("Enter SA Cell Number (e.g. 0821234567 or +27821234567): ");

            if (isValidSACell(cellNumber)) {
                System.out.println("Cell number successfully captured.");
                break;
            } else {
                System.out.println("Invalid SA cell number!");
            }
        }

        // Password validation
        while (true) {
            String pass1 = getNonEmptyInput("Create Password: ");
            String pass2 = getNonEmptyInput("Confirm Password: ");

            if (!pass1.equals(pass2)) {
                System.out.println("Passwords do not match!");
                continue;
            }

            if (isValidPassword(pass1)) {
                password = pass1;
                System.out.println("Password successfully created.");
                break;
            } else {
                System.out.println("Password must be at least 8 characters, include a capital letter, number, and special character.");
            }
        }

        System.out.println("\nRegistration successful!");
    }

    // LOGIN WITH 5 ATTEMPTS
    public static void loginUser() {

        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < 5) {

            String user = getNonEmptyInput("Enter Username: ");
            String pass = getNonEmptyInput("Enter Password: ");

            if (user.equals(username) && pass.equals(password)) {
                System.out.println("Login successful! Welcome " + fullName + " " + lastName);
                loggedIn = true;
                break;
            } else {
                attempts++;
                System.out.println("Incorrect username or password!");

                if (attempts < 5) {
                    System.out.println("Attempts remaining: " + (5 - attempts));
                }
            }
        }

        if (!loggedIn) {
            System.out.println("Account locked after 5 failed attempts.");
        }
    }

    // SA CELL VALIDATION
    public static boolean isValidSACell(String number) {
        return Pattern.matches("^(\\+27|0)[6-8][0-9]{8}$", number);
    }

    // PASSWORD VALIDATION
    public static boolean isValidPassword(String pass) {
        return Pattern.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$", pass);
    }
}
