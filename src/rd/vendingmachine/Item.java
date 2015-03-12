
package rd.vendingmachine;

public class Item {
    public final double weight;
    public final String name;
    public final String nutrition;
    public final String ingredients; 
    
    public Item(double weight, String name, String nutrition, String ingredients){
        this.weight = weight;
        this.nutrition = nutrition;
        this.name = name;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return name + " (weight: " + weight + ", nutrition: " + nutrition + ", ingredients: " + ingredients + ')';
    }
    
}
