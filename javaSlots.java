//JAVA SLOT MACHINE
import java.util.*;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // DECLARE VARIABLES
        int balance = 100;
        int bet;
        int payout;
        String[] row;

        // display welcome message
        System.out.println("*************************");
        System.out.println(" Welcome to Java Slots ");
        System.out.println("Symbols: 🍒🍉🍋🔔⭐️");
        System.out.println("*************************");

        while (balance > 0){
            System.out.println("Current Balance: $"+ balance);
            System.out.print("Place your bet amount: ");

            // Check if the input is not a number
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please try again.");
                scanner.next(); // Clear the invalid input
                continue; // Skip the rest of the loop and ask again
            }

            bet = scanner.nextInt(); // Now safely read the integer

            // Check if the bet is greater than the current balance
            if(bet > balance){
                System.out.println("INSUFFICIENT FUNDS"); // Let the user know they don't have enough balance
                continue;
            } else if (bet <= 0) {
                System.out.println("Bet must be greater than 0");
                continue;
            } else {
                balance -=bet;
            }

            // Simulate the slot machine spinning
            System.out.println("Spinning....");
            row = spinRow(); // Generate a random row of slot symbols
            printRow(row); // Display the symbols to the user
            payout = getPayout(row,bet); // Calculate the payout based on the row and bet

            // Determine whether the user won or lost
            if (payout > 0){
                balance += payout; // Add the payout to the balance
                System.out.println("You won $" + payout);
            }else if (payout < 0){
                System.out.println("You lost $" + Math.abs(payout));
            }

            // Ask the user if they want to play again
            System.out.println("Do you want to play again? (Y/N): ");
            String answer = scanner.next().toUpperCase();
            if (answer.equalsIgnoreCase("N")){
                System.out.println("Thanks for playing!");
                break;
            }else if (answer.equalsIgnoreCase("Y")){
                continue;
            } else if ( answer.equalsIgnoreCase("YES")) {
                continue;
            } else if ( answer.equalsIgnoreCase("NO")) {
                break;
            }
            else {
                System.out.println("Invalid input. Please try again.");
                continue;
            }
        }
        // Final game message
        System.out.println("Thanks for playing! + Final Balance: $"+ balance);
    scanner.close();
    }

    // Generate a random row of slot symbols
    static String[] spinRow(){
        String[] symbols = { "🍒","🍉","🍋","🔔","⭐️"};
        String[] row = new String[3]; // Array to store the generated row
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            // Pick a random symbol for each row position
            row[i] = symbols[random.nextInt(symbols.length)];
        }
        return row;
    }

    // Print the generated row to the user
    static void printRow(String[] row){
        System.out.println("****************");
        // Join the symbols with " | " separator
        System.out.println(" "+ String.join(" | ",row));
        System.out.println("***************");
    }
    // Get the payout based on the symbols in the row and the bet amount
    static int getPayout(String[] row, int bet){
        // Check if all three symbols in the row are the same
        if (row[0].equals(row[1]) && row[1].equals(row[2])){
            return switch (row[0]){
                case "🍒" -> bet * 3;
                case "🍉" -> bet * 4;
                case "🍋" -> bet * 5;
                case "🔔" -> bet * 10;
                case "⭐️" -> bet * 20;
                default -> 0;
            };
            // Check if the first two symbols are the same
        }else if (row[0].equals(row[1])) {
            return switch (row[0]) {
                case "🍒" -> bet * 2;
                case "🍉" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🔔" -> bet * 5;
                case "⭐️" -> bet * 10;
                default -> 0;
            };
        }
        // Check if the last two symbols are the same
        else if (row[1].equals(row[2])) {
            return switch (row[1]) {
                case "🍒" -> bet * 2;
                case "🍉" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🔔" -> bet * 5;
                case "⭐️" -> bet * 20;
                default -> 0;
            };
        }
        return 0;
    }
}
