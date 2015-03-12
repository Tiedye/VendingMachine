package rd.vendingmachine;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class VendingMachine {

    int currentBalance;
    List<Item> itemsInTray;
    List<Coin> coinsInTray;
    Map<String, ItemSlot> slots;
    private String password;
    private Map<Coin, Integer> bank;
    private boolean open;
    private ChangeMode changeMode;
    public final UserInterface keypad;

    void selectSlot(String slot) {
        //ciara
        itemsInTray.add(slots.get(slot).removeItem());
    }

    public boolean acceptsCoin(Coin coin) {
        //ciara
        if (bank.containsKey(coin)) {
            return true;
        }
        return false;
    }
    
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

    public void addItem(String slot, Item item) throws DoorClosedException {
        //daniel
        if (!open) {
            // this can only be done if the vending machine is open
            throw new DoorClosedException();
        }
        slots.get(slot).addItem(item);
    }

    public void setDenominations(Set<Coin> coins) throws DoorClosedException {
        //daniel
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
 
    public void addSlot(String position) throws DoorClosedException {
        //daniel
        if (!open) {
            // this can only be done if the vending machine is open
            throw new DoorClosedException();
        }
        slots.put(position, new ItemSlot(0));
    }

    public void addSlot(String position, int price) throws DoorClosedException {
        //daniel
        if (!open) {
            // this can only be done if the vending machine is open
            throw new DoorClosedException();
        }
        slots.put(position, new ItemSlot(price));
    }
    
    public boolean hasSlot(String slot) {
        return slots.containsKey(slot);
    }

    public void slotPrice(String slot, int price) throws DoorClosedException {
        //daniel
        if (!open) {
            // this can only be done if the vending machine is open
            throw new DoorClosedException();
        }
        slots.get(slot).price = price;
    }

    public boolean openDoor(String code) {
        //daniel
        if (code == password && !open) {
            open = true;
            return true;
        }
        return false;
    }

    public boolean closeDoor() {
        //daniel
        if (open){
            open = false;
            return true;
        }
        return false;
    }

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

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        ArrayList<String> sortedSlots = new ArrayList<String>(slots.keySet());
        Collections.sort(sortedSlots);
        for (String slot:sortedSlots){
            string.append(slot + ": " + slots.get(slot).toString() + "\n");
        }
        return string.toString();
    }
    
    

}
