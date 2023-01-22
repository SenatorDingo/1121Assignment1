import java.io.File;
import java.util.Scanner;

/**
 * @author Mehrdad Sabetzadeh, University of Ottawa
 */
public class ParkingLot {
	/**
	 * The delimiter that separates values
	 */
	private static final String SEPARATOR = ",";

	/**
	 * The delimiter that separates the parking lot design section from the parked
	 * car data section
	 */
	private static final String SECTIONER = "###";

	/**
	 * Instance variable for storing the number of rows in a parking lot
	 */
	private int numRows;

	/**
	 * Instance variable for storing the number of spaces per row in a parking lot
	 */
	private int numSpotsPerRow;

	/**
	 * Instance variable (two-dimensional array) for storing the lot design
	 */
	private CarType[][] lotDesign;

	/**
	 * Instance variable (two-dimensional array) for storing occupancy information
	 * for the spots in the lot
	 */
	private Car[][] occupancy;

	/**
	 * Constructs a parking lot by loading a file
	 * 
	 * @param strFilename is the name of the file
	 */
	public ParkingLot(String strFilename) throws Exception {

		if (strFilename == null) {
			System.out.println("File name cannot be null.");
			return;
		}
		// determine numRows and numSpotsPerRow; you can do so by
		// writing your own code or alternatively completing the 
		// private calculateLotDimensions(...) that I have provided
		calculateLotDimensions(strFilename);

		// instantiate the lotDesign and occupancy variables!
		lotDesign = new CarType[numRows][numSpotsPerRow];
		occupancy = new Car[numRows][numSpotsPerRow];
		
		// populate lotDesign and occupancy; you can do so by
		// writing your own code or alternatively completing the 
		// private populateFromFile(...) that I have provided
		populateFromFile(strFilename);
	}

	/**
	 * Parks a car (c) at a give location (i, j) within the parking lot.
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @param c is the car to be parked
	 */
	public void park(int i, int j, Car c) {
		// WRITE YOUR CODE HERE!
	}

	/**
	 * Removes the car parked at a given location (i, j) in the parking lot
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @return the car removed; the method returns null when either i or j are out
	 *         of range, or when there is no car parked at (i, j)
	 */
	public Car remove(int i, int j) {
		// WRITE YOUR CODE HERE!
		return null; // REMOVE THIS STATEMENT AFTER IMPLEMENTING THIS METHOD

	}

	/**
	 * Checks whether a car (which has a certain type) is allowed to park at
	 * location (i, j)
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @return true if car c can park at (i, j) and false otherwise
	 */
	public boolean canParkAt(int i, int j, Car c) {
		// WRITE YOUR CODE HERE!
		return false; // REMOVE THIS STATEMENT AFTER IMPLEMENTING THIS METHOD

	}

	/**
	 * @return the total capacity of the parking lot excluding spots that cannot be
	 *         used for parking (i.e., excluding spots that point to CarType.NA)
	 */
	public int getTotalCapacity() {
		// WRITE YOUR CODE HERE!
		return -1; // REMOVE THIS STATEMENT AFTER IMPLEMENTING THIS METHOD

	}

	/**
	 * @return the total occupancy of the parking lot (i.e., the total number of
	 *         cars parked in the lot)
	 */
	public int getTotalOccupancy() {
		// WRITE YOUR CODE HERE!
		return -1; // REMOVE THIS STATEMENT AFTER IMPLEMENTING THIS METHOD		
	}

	private void calculateLotDimensions(String strFilename) throws Exception {

		Scanner scanner = new Scanner(new File(strFilename));
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			str = str.replaceAll(" ", "");
			for(int i=0; i<str.length();i++){
				if(str.charAt(i) == ','){
					continue;
				}else{
					numSpotsPerRow++;
				}
			}
			if(str.equals("") || str.equals("###")){
				numSpotsPerRow = numSpotsPerRow / numRows;
				break;
			}
			numRows++;
		}

		scanner.close();
	}

	private void populateFromFile(String strFilename) throws Exception {

		Scanner scanner = new Scanner(new File(strFilename));

		int currentRow = 0;
		int currentSpot = 0;
		int occupiedRow = -1;
		int occupiedSpot = -1;

		// while loop for reading the lot design
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			for(int i=0; i<str.length();i++){
				if(str.charAt(i)=='E'){
					lotDesign[currentRow][currentSpot] = CarType.ELECTRIC;
					currentSpot++;
				}else if(str.charAt(i)=='S'){
					lotDesign[currentRow][currentSpot] = CarType.SMALL;
					currentSpot++;
				}else if(str.charAt(i)=='R'){
					lotDesign[currentRow][currentSpot] = CarType.REGULAR;
					currentSpot++;
				}else if(str.charAt(i)=='L'){
					lotDesign[currentRow][currentSpot] = CarType.LARGE;
					currentSpot++;
				}else if(str.charAt(i)=='N'){
					lotDesign[currentRow][currentSpot] = CarType.NA;
					currentSpot++;
				}
			}
			if(str.equals("") || str.equals("###")){
				break;
			}
			currentRow++;
			currentSpot = 0;
		}

		// while loop for reading occupancy data
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			str = str.replaceAll(" ", "");
			if(!str.equals("") && Character.isDigit(str.charAt(0))){
				occupiedRow = Character.getNumericValue(str.charAt(0));
				occupiedSpot = Character.getNumericValue(str.charAt(2));
				if(str.charAt(4)=='E'){
					occupancy[occupiedRow][occupiedSpot] = new Car(CarType.ELECTRIC, str.substring(6,9));
				}else if(str.charAt(4)=='S'){
					occupancy[occupiedRow][occupiedSpot] = new Car(CarType.SMALL, str.substring(6,9));
				}else if(str.charAt(4)=='R'){
					occupancy[occupiedRow][occupiedSpot] = new Car(CarType.REGULAR, str.substring(6,9));
				}else if(str.charAt(4)=='L'){
					occupancy[occupiedRow][occupiedSpot] = new Car(CarType.LARGE, str.substring(6,9));
				}else if(str.charAt(4)=='N'){
					occupancy[occupiedRow][occupiedSpot] = new Car(CarType.NA, str.substring(6,9));
				}
			}
			
		}

		scanner.close();
	}

	/**
	 * Produce string representation of the parking lot
	 * 
	 * @return String containing the parking lot information
	 */
	public String toString() {
		// NOTE: The implementation of this method is complete. You do NOT need to
		// change it for the assignment.
		StringBuffer buffer = new StringBuffer();
		buffer.append("==== Lot Design ====").append(System.lineSeparator());

		for (int i = 0; i < lotDesign.length; i++) {
			for (int j = 0; j < lotDesign[0].length; j++) {
				buffer.append((lotDesign[i][j] != null) ? Util.getLabelByCarType(lotDesign[i][j])
						: Util.getLabelByCarType(CarType.NA));
				if (j < numSpotsPerRow - 1) {
					buffer.append(", ");
				}
			}
			buffer.append(System.lineSeparator());
		}

		buffer.append(System.lineSeparator()).append("==== Parking Occupancy ====").append(System.lineSeparator());

		for (int i = 0; i < occupancy.length; i++) {
			for (int j = 0; j < occupancy[0].length; j++) {
				buffer.append(
						"(" + i + ", " + j + "): " + ((occupancy[i][j] != null) ? occupancy[i][j] : "Unoccupied"));
				buffer.append(System.lineSeparator());
			}

		}
		return buffer.toString();
	}

	/**
	 * <b>main</b> of the application. The method first reads from the standard
	 * input the name of the file to process. Next, it creates an instance of
	 * ParkingLot. Finally, it prints to the standard output information about the
	 * instance of the ParkingLot just created.
	 * 
	 * @param args command lines parameters (not used in the body of the method)
	 * @throws Exception
	 */

	public static void main(String args[]) throws Exception {

		StudentInfo.display();

		System.out.print("Please enter the name of the file to process: ");

		Scanner scanner = new Scanner(System.in);

		String strFilename = scanner.nextLine();

		ParkingLot lot = new ParkingLot(strFilename);

		System.out.println("Total number of parkable spots (capacity): " + lot.getTotalCapacity());

		System.out.println("Number of cars currently parked in the lot: " + lot.getTotalOccupancy());

		System.out.print(lot);

	}
}