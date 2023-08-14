//cHRISTIAN PALMA
//CS 145
// LETTER INVENTORY LAB


import java.util.Scanner;

// Letter inventory will take a count of different strings from 
// user input. The classes will check and organize the characters
// in alphabetical order. The letter inventory and strings will be
// displayed in the console.

public class LetterInventory {
    private int[] inventory;
    private int size;
    private static Scanner scan = new Scanner(System.in);
    // The main interacts with the user and guieds the user 
    //throug the oiptions of the program
    public static void main(String[] args) {
        char command = ' ';
        LetterInventory inventory1 = null;
        String s = "";
        String ss = "";
        LetterInventory inventory2 = null;

        System.out.println("Welcome to LetterInventory!");

        while (command != 'q') {
            System.out.println("Please choose an option:");
            System.out.println("(s) Enter a string");
            System.out.println("(d) Display the current string");
            System.out.println("(l) Display the letter inventory");
            System.out.println("(a) Add a string to the current inventory");
            System.out.println("(i) Combine and print inventories");
            System.out.println("(q) Quit");

            //The scanner checks the user input
            command = scan.next().toLowerCase().charAt(0);
            scan.nextLine(); 
            switch (command) {
                case 's':
                    System.out.println("Please enter your string here:");
                    s = scan.nextLine();
                    inventory1 = new LetterInventory(s); 
                    break;
                case 'd':
                    if (inventory1 != null) {
                        System.out.println("Current string: " + s + " " + ss);
                    } else {
                        System.out.println("No string entered yet.");
                    }
                    break;
                case 'l':
                    if (inventory1 != null) {
                        inventory1.printInventory();
                    } else {
                        System.out.println("No string entered yet.");
                    }
                    break;
                case 'a':
                    if (inventory1 != null) {
                        System.out.println("Please enter the string to add:");
                        ss = scan.nextLine();
                        // Create inventory for the second string
                        inventory2 = new LetterInventory(ss); 
                        // Add the inventories of the two strings
                        inventory1 = inventory1.add(inventory2); 
                        System.out.println("String added.");
                    } else {
                        System.out.println("No string entered yet.");
                    }
                    break;
                case 'i':
                    if (inventory1 != null) {
                        // Create inventory for the first string
                        LetterInventory inventory3 = new LetterInventory(s);
                         // Create inventory for the second string 
                        LetterInventory inventory4 = new LetterInventory(ss);
                        System.out.print("Combined inventories: ");
                        System.out.println( inventory3.add(inventory4).toString());
                    } else {
                        System.out.println("No string entered yet.");
                    }
                    break;
                case 'q':
                    System.out.println("Exiting LetterInventory...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        System.out.println("Thank you for using LetterInventory!");
    }

    // Constructs a LetterInventory object for the given string.
    public LetterInventory(String data) {
        inventory = new int[26];
        size = 0;

        // Calculate the letter inventory for the given string
        for (char c : data.toCharArray()) {
            if (Character.isLetter(c)) {
                char lowercase = Character.toLowerCase(c);
                inventory[lowercase - 'a']++;
                size++;
            }
        }
    }
    //Returns the count of the given letter in the inventory.
    public int get(char letter) {
        validateLetter(letter);

        char lowercase = Character.toLowerCase(letter);
        return inventory[lowercase - 'a'];
    }

   // Sets the count of the given letter in the inventory to the specified value.
    public void set(char letter, int value) {
        validateLetter(letter);
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative.");
        }

        char lowercase = Character.toLowerCase(letter);
        int index = lowercase - 'a';
        size -= inventory[index];
        inventory[index] = value;
        size += value;
    }

    
     // Returns the total count of letters in the inventory.
    public int size() {
        return size;
    }

    
    //Checks if the inventory is empty (contains no letters)
    public boolean isEmpty() {
        return size == 0;
    }

     //Returns a string representation of the letter inventory.
    public String toString() {
        StringBuilder result = new StringBuilder();

        
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i]; j++) {
                char c = (char) (i + 'a');
                result.append(c);
            }
        }

        return "[" + result.toString() + "]";
    }

    
      //Adds the counts of letters from another inventory to this 
      //inventory and returns a new inventory.
    public LetterInventory add(LetterInventory other) {
        LetterInventory sum = new LetterInventory("");

        for (int i = 0; i < inventory.length; i++) {
            sum.inventory[i] = this.inventory[i] + other.inventory[i];
            sum.size += sum.inventory[i];
        }

        return sum;
    }

    
     // Subtracts the counts of letters from another inventory from this inventory 
     //and returns a new inventory.
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory difference = new LetterInventory("");

        
        for (int i = 0; i < inventory.length; i++) {
            difference.inventory[i] = this.inventory[i] - other.inventory[i];
            if (difference.inventory[i] < 0) {
                return null;
            }
            difference.size += difference.inventory[i];
        }

        return difference;
    }

    
     //Validates if the given character is a letter.
    private void validateLetter(char letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("Invalid letter: " + letter);
        }
    }

     // Print the letter inventory
    public void printInventory() {
       
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] > 0) {
                char c = (char) (i + 'a');
                System.out.println(c + ": " + inventory[i]);
            }
        }
    }
}
