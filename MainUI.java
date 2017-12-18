package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

public class MainUI {

	public static void main(String[] args) throws ParseException, IOException {
		boolean loop = true;
		Scanner scanNumber = new Scanner(System.in);
		Scanner scanString = new Scanner(System.in);
		CityList cities = new CityList();
		while (loop) {

			System.out.println("1. Search city for weather data");
			System.out.println("2. Exit");

			int menuChoice = scanNumber.nextInt();

			if (menuChoice == 1) {
				System.out.println("Please enter city name: ");
				List<String> result = null;
				boolean incorrectSize = true;
				while(incorrectSize) {
					String city = scanString.nextLine();
					result = cities.searchCities(city);
					if(result.size() > 25){
						System.out.println("Narrow search input please");
					}else if(result.size() < 1){
						System.out.println("No cities found, please try again!");
					}else{
						incorrectSize = false;
					}
				}
					int counter = 1;
					String finalChoice = null;
					for (String cityChoice : result) {
						System.out.println(counter + ". " + cityChoice);
						counter++;
					}
					boolean choice = true;
					while (choice) {
						System.out.println("Select city with a number:");
						int selection = scanNumber.nextInt();
						if (selection < 1 || selection > result.size()) {
							System.out.println("Invalid choice");
						} else {
							finalChoice = result.get(selection - 1);
							choice = false;
						}
					}
					System.out.println("Selected city: " + finalChoice);
					Weather selectedCity = new Weather(finalChoice);
					System.out.println("Current temperature: " + selectedCity.getCurrentTemp());
					System.out.println("Maximum temperature: " + selectedCity.getMaxTemp());
					System.out.println("Minimum temperature: " + selectedCity.getMinTemp());
					System.out.println("Cloud percentage: " + selectedCity.getCloudPercentage());
					System.out.println("Wind speed: " + selectedCity.getWindSpeed());

			} else if (menuChoice == 2) {
				loop = false;
			} else {
				System.out.println("Invalid choice!");
			}

		}

	}

}
