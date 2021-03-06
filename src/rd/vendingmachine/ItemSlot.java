//Solely by Daniel
package rd.vendingmachine;

import java.text.NumberFormat;
import java.util.Stack;

public class ItemSlot extends Stack<Item> {
    
    /**
     * The price associated with the item slot
     */
    public int price;
    
    /**
     * Initializes the slot with a cost
     * @param price the price in base currency units
     */
    public ItemSlot(int price) {
        super();
        this.price = price;
    }
    
    /**
     * Adds an item to the item slot
     * @param item 
     */
    public void addItem(Item item){
        push(item);
    }
    
    /**
     * Removes an item from the slot
     * @return the removed item
     */
    public Item removeItem(){
        if (!empty()){
            return pop();
        }else{
            return null;
        }
    }

    /**
     * The string representation of the slot, including its price and the top item.
     * @return the representation
     */
    @Override
    public String toString() {
        return (size() != 0 ? lastElement().name : "") + " (" + NumberFormat.getCurrencyInstance().format(price / 100.0) + ")";
    }
}
