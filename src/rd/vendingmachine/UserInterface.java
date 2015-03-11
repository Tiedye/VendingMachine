/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.vendingmachine;

/**
 *
 * @author ciara
 */
public class UserInterface {

    enum InterfaceMode {
        READY,
        ERROR
    }
    VendingMachine parent;
    InterfaceMode mode;
    String selection;
    public String display;
    
    public UserInterface(VendingMachine parent){
        mode = InterfaceMode.READY;
        display = "READY";
        selection = "";
        this.parent = parent;
    }

    public void pushButton(char button) {
        if(mode != InterfaceMode.ERROR){
            selection += button;
            if(parent.slots.containsKey(selection)){
                if(parent.currentBalance >= parent.slots.get(selection).price){
                    parent.currentBalance -= parent.slots.get(selection).price;
                    parent.selectSlot(selection);
                    parent.getChange();
                }
                else{
                    display = "NOT ENOUGH MONEY";
                    selection = "";
                }
            }else{
                display = "CURRENT SELECTION: " + selection;  
            }
                
        }
            
    }

    public String readDisplay() {
        return display;
    }
}
