
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import rd.vendingmachine.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ciara
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachine vm = new VendingMachine("12345");
        vm.openDoor("12345");
        try {
            vm.addSlot("A1");
            vm.addSlot("A2");
            vm.addSlot("A3");
            vm.addSlot("B1");
            vm.addSlot("B2");
            vm.addSlot("B3");
            vm.addSlot("C1");
            vm.addSlot("C2");
            vm.addSlot("C3");
            vm.slotPrice("A1", 150);
            vm.slotPrice("A2", 250);
            vm.slotPrice("A3", 150);
            vm.slotPrice("B1", 200);
            vm.slotPrice("B2", 125);
            vm.slotPrice("B3", 175);
            vm.slotPrice("C1", 100);
            vm.slotPrice("C2", 50);
            vm.slotPrice("C3", 150);
            for (int i = 0; i < 10; i++) {
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

            Set<Coin> coins = new HashSet<>();
            coins.add(new Coin("q", 25));
            coins.add(new Coin("n", 5));
            coins.add(new Coin("d", 10));
            coins.add(new Coin("l", 100));
            coins.add(new Coin("t", 200));
        } catch (DoorClosedException e) {
            System.err.println("The door is closed.  You can't do that!");
        }

        System.out.println("READY");
        while (true) {
            String input = scanner.nextLine();
            String command = input.substring(0, input.indexOf(" ")).toLowerCase();
            switch (command) {

            }

        }

    }

}
