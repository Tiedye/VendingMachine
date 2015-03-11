/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.vendingmachine;

/**
 *
 * @author 335580965
 */
public class Coin {
    public final int value;
    public final String name;
    
    public Coin(String name, int value){
        this.name = name;
        this.value = value;
    }
}
