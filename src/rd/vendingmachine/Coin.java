/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rd.vendingmachine;

import java.util.Objects;

/**
 *
 * @author 335580965
 */
public class Coin {
    /**
     * The value of the coin, this cannot be changed
     */
    public final int value;
    /**
     * The name of the coin, this cannot be changed
     */
    public final String name;
    
    /**
     * Initialize the coin
     * @param name the name of the coin
     * @param value the value in base currency units
     */
    public Coin(String name, int value){
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }
    
    /**
     * Created an identical copy of the coin
     * @return the copy
     */
    public Coin copy(){
        return new Coin(name, value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.value;
        hash = 47 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coin other = (Coin) obj;
        if (this.value != other.value) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
}
