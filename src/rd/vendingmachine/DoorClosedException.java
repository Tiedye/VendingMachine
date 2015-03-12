//Solely Daniels section
package rd.vendingmachine;

public class DoorClosedException extends Exception {

    /**
     * Creates a new instance of <code>DoorClosedException</code> without detail
     * message.
     */
    public DoorClosedException() {
        super("You must open the door before accessing the innards!");
    }
}
