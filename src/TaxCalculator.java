
/*Java class to calculate sales tax and total price of items*/
import java.io.*;

public class TaxCalculator {

	public static void main(String[] args) {

		try {
			boolean isFirstTime = true;
			Double totalTax = 0.0;
			Double totalPrice = 0.0;
			InputDetails inpDet = null;
			// create a Buffered Reader object instance with a FileReader
			BufferedReader br = new BufferedReader(new FileReader("src/item_list.txt"));

			// read the first line from the text file
			String fileRead = br.readLine();

			// loop until all lines are read
			while (fileRead != null) {
				String[] tokenize = null;

				// if the line starts with #input, then it means this is another list
				// and if the total tax is > 0, output the tax and price computed for the
				// earlier list
				if (fileRead.startsWith("Input")) {
					if (totalTax > 0) {
						System.out.println("Salex Taxes: " + String.format("%.2f", totalTax));
						System.out.println("Total: " + String.format("%.2f", totalPrice));
					}
					// use string.split to load a string array with the values from each line of
					// the file, using a space as the delimiter to print the list number
					tokenize = fileRead.split(" ");
					totalTax = 0.0;
					totalPrice = 0.0;

					System.out.println((!isFirstTime ? "\n" : "") + "Output " + tokenize[1]);
					isFirstTime = false;

				} else if (!fileRead.trim().isEmpty()) {
					// use string.split to load a string array with the values from each line of
					// the file, using a comma as the delimiter to parse the item details.
					// assuming all values are valid and no null and empty validations are required

					tokenize = fileRead.trim().split(",");
					inpDet = new InputDetails(tokenize[1], Integer.parseInt(tokenize[0]), Float.parseFloat(tokenize[3]),
							tokenize[2], Boolean.parseBoolean(tokenize[4]));
					float tax = inpDet.calculateTax();
					// add the tax and total price for all items belonging to the same list
					totalTax += tax;
					totalPrice += inpDet.calculateTotalPrice();
					System.out.println(inpDet.toString());

				}

				// read next line before looping
				// if end of file reached
				fileRead = br.readLine();
			}

			// close file stream
			br.close();
			// output the total tax and price calculated for the last list
			if (totalTax > 0) {
				System.out.println("Salex Taxes: " + String.format("%.2f", totalTax));
				System.out.println("Total: " + String.format("%.2f", totalPrice));
			}
		}

		// handle exceptions
		catch (

		FileNotFoundException fnfe) {
			System.out.println("file not found");
		}

		catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

}