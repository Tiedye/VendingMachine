//Solely Ciara
package rd.vendingmachine;

public class UserInterface {

    enum InterfaceMode {
        READY,
        ERROR
    }
    
    private final VendingMachine parent;
    private InterfaceMode mode;
    private String selection;
    String display;
    
    public UserInterface(VendingMachine parent){
        mode = InterfaceMode.READY;
        display = "READY";
        selection = "";
        this.parent = parent;
    }

    public void pushButton(char button) {
        if(mode != InterfaceMode.ERROR){
            selection += Character.toLowerCase(button);
            boolean isPrefix = false;
            for (String slot : parent.slots.keySet()){
                if (slot.startsWith(selection)) {
                    isPrefix = true;
                    break;
                }
            }
            if(!isPrefix) {
                display = "INVALID SELECTION";
                selection = "";
                return;
            }
            if(parent.slots.containsKey(selection)){
                if(parent.currentBalance >= parent.slots.get(selection).price){
                    parent.currentBalance -= parent.slots.get(selection).price;
                    parent.selectSlot(selection);
                    parent.getChange();
                    selection = "";
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
        return "Display: " + display;
    }
}
