
package rd.vendingmachine;

import java.util.Objects;

public class Coin {
    //ciara
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
    
    /**
     * Created an identical copy of the coin
     * @return the copy
     */
    public Coin copy(){
        return new Coin(name, value);
    }
    
    //automatically generated for maps and display
    @Override
    public String toString() {
        return name;
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
        return Objects.equals(this.name, other.name);
    }
    
    
}
