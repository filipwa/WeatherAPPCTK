package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CityList {

	private List<String> cities;

	public List<String> getCities() {
		return cities;
	}

	CityList() throws ParseException{
		cities = new ArrayList<String>();
		getJSONData();

	}

	public List<String> searchCities(String city){

		List<String> resultCities = new ArrayList<String>();
		for(String s : this.cities){
			if(s.contains(city)){
				resultCities.add(s);
			}
		}

		return resultCities;
	}

	private void getJSONData() throws ParseException {
		String jsonCityList = readFile("/Users/fwall/workspace/WeatherApp/src/Main/city.list.json");


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonCityList);

		JSONArray jsonarray = (JSONArray) obj;

		for(Object c : jsonarray){
			JSONObject jsonObject = (JSONObject) c;
			cities.add(jsonObject.get("name").toString());
		}

		// add elements to al, including duplicates
		Set<String> hs = new HashSet<>();
		hs.addAll(this.cities);
		this.cities.clear();
		this.cities.addAll(hs);
	}

	public static String readFile(String filename) {
	    String result = "";
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(filename));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	        }
	        result = sb.toString();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
}


