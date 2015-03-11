/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.vendingmachine;

/**
 *
 * @author 068616374
 */
public class DoorClosedException extends Exception {

    /**
     * Creates a new instance of <code>DoorClosedException</code> without detail
     * message.
     */
    public DoorClosedException() {
        super("You must open the door before accessing the innards!");
    }
}
