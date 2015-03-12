//Solely Ciara
package rd.vendingmachine;

public class UserInterface {

    //creates the possible states for the vending machine
    enum InterfaceMode {
        READY,
        ERROR
    }
    
    //creates a variable that points to the vending machine
    private final VendingMachine parent;
    private InterfaceMode mode;
    //what buttons the user has pressed thus far
    private String selection;
    //display to the screen
    String display;
    
    /**
     * initializes the vending machine
     * @param parent vending machine currently being used
     */
    public UserInterface(VendingMachine parent){
        mode = InterfaceMode.READY;
        display = "READY";
        selection = "";
        this.parent = parent;
    }

    /**
     * determines the course of action when a button is pushed
     * @param button a letter or number 1-9 on the keypad
     */
    public void pushButton(char button) {
        //only works if the machine is ready
        if(mode != InterfaceMode.ERROR){
            //adds the button's value (character) to selection
            selection += Character.toLowerCase(button);
            boolean isPrefix = false;
            //determines if there is a possible slot for what the user is typing
            for (String slot : parent.slots.keySet()){
                if (slot.startsWith(selection)) {
                    isPrefix = true;
                    break;
                }
            }
            //if there isnt a selection (say the user inputs a 1 first) the selection resets
            if(!isPrefix) {
                display = "INVALID SELECTION";
                selection = "";
                return;
            }
            //determine whether or not the slot actually exists
            if(parent.slots.containsKey(selection)){
                //checks to see if they have enough money
                if(parent.currentBalance >= parent.slots.get(selection).price){
                    //subtracts the cost of the item from their balance
                    parent.currentBalance -= parent.slots.get(selection).price;
                    //selects their selection and returns change
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
    /**
     * displays the display
     * @return what the screen outputs
     */
    public String readDisplay() {
        return "Display: " + display;
    }
}
