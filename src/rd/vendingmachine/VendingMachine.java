package rd.vendingmachine;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;

public class VendingMachine {

    int currentBalance;
    List<Item> itemsInTray;
    List<Coin> coinsInTray;
    Map<String, ItemSlot> slots;
    private final String password;
    private Map<Coin, Integer> bank;
    private boolean open;
    private ChangeMode changeMode;
    public final UserInterface keypad;

    void selectSlot(String slot) {
        //ciara
        Item item = slots.get(slot.toLowerCase()).removeItem();
        if(item != null) itemsInTray.add(item);
    }

    public boolean acceptsCoin(Coin coin) {
        //ciara
        return bank.containsKey(coin);
    }
    
    
    /**
     * Gets all of the coins accepted by the machine.
     * @return 
     */
    public Set<Coin> getAcceptedCoins(){
        return bank.keySet();
    }

    public void insertCoin(Coin coin) {
        //ciara
        //puts that coin into the bank and adds the coins value to the current balance
        if (acceptsCoin(coin)) {
            currentBalance += coin.value;
            bank.put(coin, bank.get(coin) + 1);
            keypad.display = "CURRENT BALANCE: " + NumberFormat.getCurrencyInstance().format(currentBalance / 100.0);
        } else {
            coinsInTray.add(coin);
        }
    }

    public List<Item> takeItems() {
        //ciara
        List<Item> items;
        items = itemsInTray;
        itemsInTray = new ArrayList<>();
        return items;
    }

    /**
     * Converts the current balance into coins and adds those coins into the coin tray
     */
    public void getChange() {
        //daniel
        //puts coins equal to the current balance into the coin tray
    }
    
    public List<Coin> takeChange() {
        //ciara
        List<Coin> coins;
        coins = coinsInTray;
        coinsInTray = new ArrayList<>();
        return coins;
    }
    
    /**
     * Adds an item into an item slot
     * @param slot the slot that the item will be added to
     * @param item the item to be added
     * @throws DoorClosedException 
     */
    public void addItem(String slot, Item item) throws DoorClosedException {
        //daniel
        if (!open) {
            // this can only be done if the vending machine is open
            throw new DoorClosedException();
        }
        slots.get(slot.toLowerCase()).addItem(item);
    }

    public void setDenominations(Set<Coin> coins) throws DoorClosedException {
        //ciara
        // this first clears the bank, then adds each coin type to the bank
        // this allows the bank to accept those types of coins
        if (!open) {
            // this can only be done if the vending machine is open
            throw new DoorClosedException();
        }
        bank = new HashMap<>();
        for (Coin coin : coins) {
            bank.put(coin, 0);
        }
    }
    
    /**
     * Adds coins to the machines bank
     * @param coins coins to be added to the machine
     * @throws DoorClosedException 
     */
    public void addCoins(List<Coin> coins) throws DoorClosedException{
        //daniel
        if (!open) {
            // this can only be done if the vending machine is open
            throw new DoorClosedException();
        }
        for(Coin coin:coins){
            if (acceptsCoin(coin)){
                bank.put(coin, bank.get(coin) + 1);
            } else {
                coinsInTray.add(coin);
            }
        }
    }
    
    /**
     * Gets all of the coins in the machines bank, the coins are removed from the machine
     * @return the coins in the machine
     * @throws DoorClosedException 
     */
    public List<Coin> removeCoins() throws DoorClosedException{
        //daniel
        if (!open) {
            // this can only be done if the vending machine is open
            throw new DoorClosedException();
        }
        List<Coin> coins = new ArrayList<>();
        for(Coin coin:bank.keySet()){
            for(int i = 0; i < bank.get(coin); i++){
                coins.add(coin.copy());
            }
            bank.put(coin, 0);
        }
        return coins;
    }

    /**
     * Adds a item slot to the machine
     * @param position the position of the slot
     * @param price the price of the slot in base currency units
     * @throws DoorClosedException 
     */
    public void addSlot(String position, int price) throws DoorClosedException {
        //daniel
        if (!open) {
            // this can only be done if the vending machine is open
            throw new DoorClosedException();
        }
        slots.put(position.toLowerCase(), new ItemSlot(price));
    }
    
    /**
     * Checks if a slot exists in the machine
     * @param slot the slot to check
     * @return if it exists
     */
    public boolean hasSlot(String slot) {
        return slots.containsKey(slot.toLowerCase());
    }
    
    /**
     * Sets the price associated with a slot on the machine.
     * @param slot the slot to be modified
     * @param price the new price in base currency units
     * @throws DoorClosedException 
     */
    public void slotPrice(String slot, int price) throws DoorClosedException {
        //daniel
        if (!open) {
            // this can only be done if the vending machine is open
            throw new DoorClosedException();
        }
        slots.get(slot.toLowerCase()).price = price;
    }

    /**
     * Opens the door on the machine
     * @param code the code to open the door
     * @return weather the door open was successful
     */
    public boolean openDoor(String code) {
        //daniel
        if (code.equals(password) && !open) {
            open = true;
            return true;
        }
        return false;
    }
    
    /**
     * Closed the door on the machine
     * @return if the door close was successful
     */
    public boolean closeDoor() {
        //daniel
        if (open){
            open = false;
            return true;
        }
        return false;
    }
    
    /**
     * Sets the way in which change is given from the machine
     * @param mode the mode
     * @throws DoorClosedException 
     */
    public void setChangeMode(ChangeMode mode) throws DoorClosedException {
        //daniel
        if (!open) {
            // this can only be done if the vending machine is open
            throw new DoorClosedException();
        }
        changeMode = mode;
    }

    public VendingMachine(String code) {
        //ciara
        this.currentBalance = 0;
        this.bank = new HashMap<>();
        this.itemsInTray = new ArrayList<>();
        this.coinsInTray = new ArrayList<>();
        this.password = code;
        this.slots = new HashMap<>();
        this.keypad = new UserInterface(this);
    }

    public enum ChangeMode {
        MAXIMIZE_COIN_COUNT, MINIMIZE_COINS_GIVEN
    }
    
    /**
     * The string representation of the machine.  All slots are listed with their prices and top items.
     * @return the representation
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        ArrayList<String> sortedSlots = new ArrayList<>(slots.keySet());
        Collections.sort(sortedSlots);
        for (String slot:sortedSlots){
            string.append(slot).append(": ").append(slots.get(slot).toString()).append("\n");
        }
        return string.toString();
    }
    
    

}
