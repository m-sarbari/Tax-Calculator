
/* Java Bean class to store the item details*/
import java.util.Arrays;

public class InputDetails {
	// display name for the item
	private String itemName;
	// quantity of items purchased
	private int quantity;
	// base price of each item
	private float price;
	// category type to denote whether the item belongs to the food or medical or
	// books category
	private String itemCategory;
	// flag to denote if the item is imported
	private boolean isImported;
	// total sales tax calculated per item basis
	public float tax;
	// total price calculated per item basis
	public float totalPrice;

	private final String[] catList = { "books", "medicine", "food" };

	public InputDetails(String itemName, int quantity, float price, String itemCategory, boolean isImported) {
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.itemCategory = itemCategory;
		this.isImported = isImported;
	}
	
	/* Function to calculate the sales tax based on import and category conditions*/
	public float calculateTax() {
		tax = 0;
		//if item does not belong to the category of food or medicine or books and it is imported, sales tax is 15%
		if (!Arrays.asList(catList).contains(itemCategory) && isImported) {
			tax = .15f * price * quantity;
		} //if item does not belong to the category of food or medicine or books and it is not imported, sales tax is 10%
		else if (!Arrays.asList(catList).contains(itemCategory) && !isImported) {
			tax = .10f * price * quantity;
		} //if item does belong to the category of food or medicine or books and it is imported, sales tax is 5%
		else if (Arrays.asList(catList).contains(itemCategory) && isImported) {
			tax = .05f * price * quantity;
		}
		//convert the tax value to the next higher decimal value of 0.05
		tax = (float) Math.ceil(tax * 20.00) / 20.00f;
		totalPrice = price + tax;
		return tax;
	}
	
	/* Function to get the total price calculated in the above function*/
	public float calculateTotalPrice() {
		return totalPrice;
	}
	/* Override the to.String() function to display the output as per the required format*/
	public String toString() {
		return quantity + " " + itemName + ": " + String.format("%.2f", totalPrice);
	}
}
