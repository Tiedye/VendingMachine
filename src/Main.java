//Shared section b/w Daniel and Ciara (authorship noted below)
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import rd.vendingmachine.*;

public class Main {
    //Ciara's section begins
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //creates a new vending machine with passcode
        VendingMachine vm = new VendingMachine("12345");
        vm.openDoor("12345");
        //adds slots to the vending machine
        try {
            vm.addSlot("A1", 150);
            vm.addSlot("A2", 250);
            vm.addSlot("A3", 150);
            vm.addSlot("B1", 200);
            vm.addSlot("B2", 125);
            vm.addSlot("B3", 175);
            vm.addSlot("C1", 100);
            vm.addSlot("C2", 50);
            vm.addSlot("C3", 150);
            //adds items into the vending machine
            for (int i = 0; i < 2; i++) {
                vm.addItem("A1", new Item(50, "Mars", "calories: 242, fat: 9g, carbs: 37g, protein: 2g", "RD go ahead and google it"));
                vm.addItem("A2", new Item(54, "Smarties", "calories: 140, fat: 4g, carbs: 24g, protein: 1g", "RD go ahead and google it"));
                vm.addItem("A3", new Item(60, "Twix", "calories: 280, fat: 14g, carbs: 37g, protein: 3g", "RD go ahead and google it"));
                vm.addItem("B1", new Item(57, "Oh Henry", "calories: 263, fat: 13g, carbs: 37g, protein: 4g", "RD go ahead and google it"));
                vm.addItem("B2", new Item(50, "Aero", "calories: 223, fat: 12g, carbs: 25g, protein: 3g", "RD go ahead and google it"));
                vm.addItem("B3", new Item(40, "Lays Classic", "calories: 150, fat: 10g, carbs: 15g, protein: 2g", "RD go ahead and google it"));
                vm.addItem("C1", new Item(27, "Dortitos", "calories: 140, fat: 7g, carbs: 18g, protein: 2g", "RD go ahead and google it"));
                vm.addItem("C2", new Item(34, "Peanut M&M", "calories: 180, fat: 9g, carbs: 22g, protein: 3g ", "RD go ahead and google it"));
                vm.addItem("C3", new Item(35, "Twizzler", "calories: 100, fat: 1g, carbs: 33g, protein: 1g", "RD go ahead and google it"));
            }
            //creates the coins that are accepted
            Set<Coin> coins = new HashSet<>();
            coins.add(new Coin("quarter", 25));
            coins.add(new Coin("nickel", 5));
            coins.add(new Coin("dime", 10));
            coins.add(new Coin("loonie", 100));
            coins.add(new Coin("toonie", 200));
            //puts those coins into the bank
            vm.setDenominations(coins);
            List<Coin> coinsToAdd = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                coinsToAdd.add(new Coin("quarter", 25));
                coinsToAdd.add(new Coin("nickel", 5));
                coinsToAdd.add(new Coin("dime", 10));
                coinsToAdd.add(new Coin("loonie", 100));
                coinsToAdd.add(new Coin("toonie", 200));
            }
            vm.addCoins(coinsToAdd);
            vm.closeDoor();
            
            //end of Ciara's contribution for the main method
            //Daniel's section begins
            
            
        } catch (DoorClosedException e) {
            System.err.println("The door is closed.  You can't do that!");
        }

        // prints the instructions for using the commands
        System.out.println("To interact with the vending machine, type a command from the list:\n"
                + "-insertcoin [coin name]\n"
                + "  |inserts coin into vending machine\n\n"
                + "-pushbutton [button]\n"
                + "  |pushes a button on the vending machine keypad (A-Z, 0-9).  If the selection matches a slot, the item in that slot will be purchased\n\n"
                + "-getchange\n"
                + "  |puts your change into the change tray\n\n"
                + "-getitemsintray\n"
                + "  |gets the items out of the item tray\n\n"
                + "-getcoinsintray\n"
                + "  |gets the coins out of the coin tray\n\n"
                + "-readdisplay\n"
                + "  |reads the text currently on the display\n\n"
                + "-viewslots\n"
                + "  |shows all of the slots in the machine, and the items on the top of that slot\n\n\n"
                + "The remaining commands are for a vendor who wishes to reload or modify the vending machine.\n"
                + "-opendoor [code]\n"
                + "  |open the vending machine door so you can change it\n\n"
                + "-closedoor\n"
                + "  |close it so other people can't steal stuff\n\n"
                + "-additem [slot] [name] <weight> <nutrition> <ingredients>\n"
                + "  |add an item to the vending machine\n\n"
                + "-addslot [slot to add]\n"
                + "  |add a slot to the vending machine\n\n"
                + "-setslotprice [slot] [price]\n"
                + "  |change the price on a slot in the vending machine\n\n"
                + "-addcoins [coin name] <coin name> <coin name> ...\n"
                + "  |add coins to the machine\n\n"
                + "-removecoins\n"
                + "  |remove all the coins from the machine\n\n");

        while (true) {
            // gets the users command
            System.out.print(">");
            String input = scanner.nextLine();
            // splits up the command into terms and groups terms that are in quotations into single terms
            String[] command = combineLinkedTerms(input.split(" "));
            // selects the command and checks if there are the correct number of arguments for the command
            // if a command requires the door to be open and its closed, output an error
            switch (command[0]) {
                case "insertcoin":
                    if (command.length == 2) {
                        // checks if the paramater is a valid coin, if it is output it, otherwise print an error
                        search:
                        {
                            for (Coin coin : vm.getAcceptedCoins()) {
                                if (coin.name.toLowerCase().equals(command[1].toLowerCase())) {
                                    vm.insertCoin(coin.copy());
                                    System.out.println("The " + command[1] + " coin was inserted to the machine.");
                                    break search;
                                }
                            }
                            System.err.println(command[1] + " is not a valid coin.");
                        }
                    } else {
                        System.err.println("This command takes 2 arguments.");
                    }
                    break;
                case "pushbutton":
                    if (command.length == 2) {
                        // checks if the button is valid, if it is push it, otherwise print an error
                        if (command[1].length() == 1 && (Character.isLetter(command[1].charAt(0)) || Character.isDigit(command[1].charAt(0)))) {
                            vm.keypad.pushButton(command[1].charAt(0));
                            System.out.println("The " + command[1].toUpperCase() + " button was pushed.");
                        } else {
                            System.err.println("That is not a valid button.");
                        }
                    } else {
                        System.err.println("Invalid number of arguments.");
                    }
                    break;
                case "getchange":
                    if (command.length == 1) {
                        // puts the users change into the change tray
                        vm.getChange();
                        System.out.println("Your change has been put in the coin tray.");
                    } else {
                        System.err.println("That command doesn't take arguments.");
                    }
                    break;
                case "getitemsintray":
                    if (command.length == 1) {
                        // takes the items out of the item tray and displays the items on screen
                        System.out.println("Items taken:");
                        for (Item item : vm.takeItems()) {
                            System.out.println("  " + item);
                        }
                    } else {
                        System.err.println("That command doesn't take arguments.");
                    }
                    break;
                case "getcoinsintray":
                    if (command.length == 1) {
                        // takes the items out of the item tray and displays the items on screen
                        System.out.println("Coins taken:");
                        for (Coin coin : vm.takeChange()) {
                            System.out.println("  " + coin);
                        }
                    } else {
                        System.err.println("That command doesn't take arguments.");
                    }
                    break;
                case "readdisplay":
                    if (command.length == 1) {
                        // outputs the display to the screen
                        System.out.println(vm.keypad.readDisplay());
                    } else {
                        System.err.println("That command doesn't take arguments.");
                    }
                    break;
                case "viewslots":
                    if (command.length == 1) {
                        // output vending machine to the screen
                        System.out.println(vm);
                    } else {
                        System.err.println("That command doesn't take arguments.");
                    }
                    break;
                case "opendoor":
                    if (command.length == 2) {
                        // tries to open the door, if it fails print an error
                        if (vm.openDoor(command[1])) {
                            System.out.println("The door has been opened.");
                        } else {
                            System.err.println("The door could not be opened.");
                        }
                    } else {
                        System.err.println("This command takes 2 arguments.");
                    }
                    break;
                case "closedoor":
                    if (command.length == 1) {
                        // tries to close door, if it fails print an error
                        if (vm.closeDoor()) {
                            System.out.println("The door has been closed.");
                        } else {
                            System.err.println("The door is already closed.");
                        }
                    } else {
                        System.err.println("That command doesn't take arguments.");
                    }
                    break;
                case "additem":
                    if (command.length >= 3 && command.length <= 6) {
                        // reads the first two commands as the slot and item name
                        String slot = command[1];
                        if (!vm.hasSlot(slot)) {
                            // if the slot doesn't exist output an error
                            System.err.println("Slot " + slot + " does not exist.");
                            break;
                        }
                        String name = command[2];
                        // read the weight if given, output an error if its not a valid number
                        double weight = 0.0;
                        if (command.length >= 4) {
                            try {
                                weight = Double.parseDouble(command[3]);
                            } catch (NumberFormatException e) {
                                System.err.println(command[3] + " is not a number.");
                                break;
                            }
                        }
                        // read the nutrition if given
                        String nutrition = "";
                        if (command.length >= 5) {
                            nutrition = command[4];
                        }
                        // read the ingredients if given
                        String ingredients = "";
                        if (command.length >= 6) {
                            ingredients = command[5];
                        }
                        try {
                            // adds the item to the specified slot
                            Item item = new Item(weight, name, nutrition, ingredients);
                            vm.addItem(slot, item);
                            System.out.println("Item " + item + " added to slot " + slot + ".");
                        } catch (DoorClosedException e) {
                            System.err.println("The door is closed, you can't do that.");
                        }
                    } else {
                        System.err.println("This command takes between 2 and 5 arguments.");
                    }
                    break;
                case "addslot":
                    if (command.length == 2 || command.length == 3) {
                        // check if the slot already exists
                        if (!vm.hasSlot(command[1])) {
                            // read the price if given, and ouput an error if the price is not an integer
                            int price = 0;
                            if (command.length == 3) {
                                try {
                                    price = Integer.parseInt(command[2]);
                                } catch (NumberFormatException e) {
                                    System.err.println(command[2] + " is not a valid value (must be an integer without a prefix).");
                                    break;
                                }
                            }
                            try {
                                // add the slot to the machine
                                vm.addSlot(command[1], price);
                                System.out.println("Slot " + command[1] + " added to the machine at a cost of " + price + ".");
                            } catch (DoorClosedException e) {
                                System.err.println("The door is closed, you can't do that.");
                            }
                        } else {
                            System.err.println("That slot already exists.");
                        }
                    } else {
                        System.err.println("This command takes 2 arguments.");
                    }
                    break;
                case "setslotprice":
                    if (command.length == 3) {
                        // check if the slot exists
                        if (vm.hasSlot(command[1])) {
                            int price;
                            // checks if the input is a valid integer
                            try {
                                price = Integer.parseInt(command[2]);
                            } catch (NumberFormatException e) {
                                System.err.println(command[2] + " is not a valid value (must be an integer without a prefix).");
                                break;
                            }
                            try {
                                // set the slots price to the given value
                                vm.slotPrice(input, price);
                                System.out.println("Slot " + command[1] + " set to price " + price + ".");
                            } catch (DoorClosedException e) {
                                System.err.println("The door is closed, you can't do that.");
                            }
                        } else {
                            System.err.println("That slot doesn't exist.");
                        }
                    } else {
                        System.err.println("This command takes 2 arguments.");
                    }
                    break;
                case "addcoins":
                    if (command.length > 1) {
                        // processes each paramater, checks if its a coin if so add it to a list of coins to be added
                        paramaterLoop:
                        {
                            List<Coin> coins = new ArrayList<>();
                            for (int i = 1; i < command.length; i++) {
                                search:
                                {
                                    for (Coin coin : vm.getAcceptedCoins()) {
                                        if (coin.name.toLowerCase().equals(command[i].toLowerCase())) {
                                            coins.add(coin.copy());
                                            break search;
                                        }
                                    }
                                    // if any coin is not a valid coin, dont add any of the coins to the machine
                                    System.err.println(command[i] + " is not a valid coin.");
                                    break paramaterLoop;
                                }
                            }
                            try {
                                // add the coins to the machine
                                vm.addCoins(coins);
                            } catch (DoorClosedException e) {
                                System.err.println("The door is closed, you can't do that.");
                            }
                        }
                    } else {
                        System.err.println("That command takes at least argument.");
                    }
                    break;
                case "removecoins":
                    if (command.length == 1) {
                        // removes all the coins and prints them to the screen
                        List<Coin> coins;
                        try {
                            coins = vm.removeCoins();
                        } catch (DoorClosedException e) {
                            System.err.println("The door is closed, you can't do that.");
                            break;
                        }
                        System.out.println("Coins removed:");
                        for (Coin coin : coins) {
                            System.out.println("  " + coin);
                        }
                    } else {
                        System.err.println("That command doesn't take arguments.");
                    }
                    break;
                default:
                    // if its not a command print an error
                    System.err.println(command[0] + " is not a command.");
            }

        }

    }

    static String[] combineLinkedTerms(String[] terms) {
        //ciara
        ArrayList<String> newTerms = new ArrayList<>();
        boolean inQuote = false;
        for (String term : terms) {
            if (inQuote) {
                if (term.endsWith("\"")) {
                    inQuote = false;
                    term = term.substring(0, term.length() - 1);
                }
                newTerms.add(newTerms.size() - 1, newTerms.get(newTerms.size() - 1) + " " + term);
                newTerms.remove(newTerms.size() - 1);
            } else if (term.startsWith("\"")) {
                inQuote = true;
                term = term.substring(1);
                newTerms.add(term);
            } else {
                newTerms.add(term);
            }
        }
        return newTerms.toArray(new String[newTerms.size()]);
    }

}
