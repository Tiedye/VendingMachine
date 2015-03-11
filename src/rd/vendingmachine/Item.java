
package rd.vendingmachine;

public class Item {
    public final double weight;
    public final String nutrition;
    public final String name;
    public final String ingredients; 
    
    public Item(double weight, String nutrition, String name, String ingredients){
        this.weight = weight;
        this.nutrition = nutrition;
        this.name = name;
        this.ingredients = ingredients;
    }
}
