/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.vendingmachine;

import java.util.Stack;

/**
 *
 * @author Daniel Whitney <tiedye1@hotmail.com>
 */
public class ItemSlot extends Stack<Item> {
    
    public int price;
    
    public ItemSlot(int price) {
        super();
        this.price = price;
    }
    
    public void addItem(Item item){
        push(item);
    }
    
    public Item removeItem(){
        return pop();
    }
}
