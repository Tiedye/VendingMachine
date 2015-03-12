/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.vendingmachine;

import java.text.NumberFormat;
import java.util.Stack;

/**
 *
 * @author Daniel Whitney <tiedye1@hotmail.com>
 */
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
        return (size() != 0 ? firstElement().name : "") + " (" + NumberFormat.getCurrencyInstance().format(price / 100.0) + ")";
    }
}
