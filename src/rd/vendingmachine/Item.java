
package rd.vendingmachine;

public class Item {
    //ciara
    /**
     * The weight of the item, this cannot be changed
     */
    public final double weight;
    /**
     * The name of the item, this cannot be changed
     */
    public final String name;
    /**
     * The nutrients in the item, this cannot be changed
     */
    public final String nutrition;
    /**
     * The ingredients in the item, this cannot be changed
     */
    public final String ingredients; 
    
    /**
     * initialized the item
     * @param weight the weight
     * @param name the name
     * @param nutrition the nutritional facts
     * @param ingredients the ingredients
     */
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
