//Shared section b/w Daniel and Ciara (authorship of functions noted below)
package rd.vendingmachine;

import java.text.NumberFormat;
import java.util.*;

public class VendingMachine {
    //Ciara's section begins (all code within she has written)
    int currentBalance;
    List<Item> itemsInTray;
    List<Coin> coinsInTray;
    Map<String, ItemSlot> slots;
    private final String password;
    private Map<Coin, Integer> bank;
    private boolean open;
    private ChangeMode changeMode;
    public final UserInterface keypad;

    /**
     * selects the slot that the user chooses
     * @param slot slot choice
     */
    void selectSlot(String slot) {
        Item item = slots.get(slot.toLowerCase()).removeItem();
        if(item != null) itemsInTray.add(item);
    }
    
    
    /**
     * generates a list of coins that the bank accepts
     * @param coin 
     * @return a list of coins that the bank accepts
     */
    public boolean acceptsCoin(Coin coin) {
        return bank.containsKey(coin);
    }
    
    
    /**
     * Gets all of the coins accepted by the machine.
     * @return 
     */
    public Set<Coin> getAcceptedCoins(){
        return bank.keySet();
    }

    /**
     * puts a coin into the bank and adds its value into the currentBalance
     * @param coin the coin entered
     */
    public void insertCoin(Coin coin) {
        if (acceptsCoin(coin)) {
            currentBalance += coin.value;
            bank.put(coin, bank.get(coin) + 1);
            keypad.display = "CURRENT BALANCE: " + NumberFormat.getCurrencyInstance().format(currentBalance / 100.0);
        } else {
            coinsInTray.add(coin);
        }
    }

    /**
     * clears the itemsInTray and gives them to the buyer
     * @return the items in the tray
     */
    public List<Item> takeItems() {
        List<Item> items = itemsInTray;
        itemsInTray = new ArrayList<>();
        return items;
    }
    
    /**
     * clears the coinsInTray and gives them to the buyer
     * @return the coins in the tray
     */
    public List<Coin> takeChange() {
        //ciara
        List<Coin> coins = coinsInTray;
        coinsInTray = new ArrayList<>();
        return coins;
    }
    
    /**
     * clears the bank and adds the coin types into the bank
     * @param coins a set of coins that the bank will then accept
     * @throws DoorClosedException 
     */
    public void setDenominations(Set<Coin> coins) throws DoorClosedException {
    if (!open) {
        throw new DoorClosedException();
    }
    bank = new HashMap<>();
    for (Coin coin : coins) {
        bank.put(coin, 0);
    }
    }
    
    /**
     * initializes the vending machine
     * @param code password to access the vending machine
     */
    public VendingMachine(String code) {
        currentBalance = 0;
        bank = new HashMap<>();
        itemsInTray = new ArrayList<>();
        coinsInTray = new ArrayList<>();
        password = code;
        slots = new HashMap<>();
        keypad = new UserInterface(this);
    }
    
    //Ciara's section Ends
    //Daniel's section Begins
    
    /**
     * Converts the current balance into coins and adds those coins into the coin tray
     */
    public void getChange() {
        //daniel
        List<Set<Map<Coin, Integer>>> state = new ArrayList<>(currentBalance);
        for (int i = 0; i < currentBalance; i++){
            Set<Map<Coin, Integer>> currentState = new HashSet<>();
            for (Coin coin : bank.keySet()){
                if (i-coin.value > 0)
            }
        }
        keypad.display = "READY";
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
