package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Weather {

	private String name;
	private double currentTemp;
	private double maxTemp;
	private double minTemp;
	private double windSpeed;
	private double cloudPercentage;

	private String jsonData;

	Weather(String cityName) throws ParseException, IOException {
		this.name = cityName;
		String JSONData = getJSONWeather(cityName);

		this.jsonData = JSONData;
		JSONObject jsonDataTemperature = getJSONData(JSONData, "main");
		JSONObject jsonDataWind = getJSONData(JSONData, "wind");
		JSONObject jsonDataCloud = getJSONData(JSONData, "clouds");

		this.currentTemp = castToDouble(jsonDataTemperature.get("temp"));
		this.maxTemp = castToDouble(jsonDataTemperature.get("temp_max"));
		this.minTemp = castToDouble(jsonDataTemperature.get("temp_min"));

		this.windSpeed = castToDouble(jsonDataWind.get("speed"));
		this.cloudPercentage = castToDouble(jsonDataCloud.get("all"));

	}

	// Checks if object is a double or long, if double cast to double if long convert to double
	private double castToDouble(Object obj){
		double re = 0;
		if(obj instanceof Double){
			re = (Double) obj;
		}else if(obj instanceof Long){
			String temp = "" + (Long) obj;
			re = Double.parseDouble(temp);
		}

		return re;
	}


	// takes a JSONText and a category and parses the JSON to get the chosen "json block"
	private JSONObject getJSONData(String jsonData, String category) throws ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonData);

		JSONObject jsonObject = (JSONObject) obj;

		JSONObject jsonBlock = (JSONObject) jsonObject.get(category);

		Object jsonInfo = parser.parse(jsonBlock.toString());
		JSONObject jsonReturn = (JSONObject) jsonInfo;

		return jsonReturn;

	}

	// Retrevies the source code of the API of the given city name
	private String getJSONWeather(String cityName) throws IOException {
		String jsonData = "";
		String city = cityName;
		String api = "http://api.openweathermap.org/data/2.5/weather?q=";
		String appID = "&units=metric&APPID=9a34289852fc0b0edf46186786471a81";
		String getAPI = api + city + appID;
		URL url = new URL(getAPI);
		HttpURLConnection yc = (HttpURLConnection) url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			jsonData += inputLine;
		in.close();

		return jsonData;
	}

	public String getName() {
		return name;
	}

	public String getJSONData() {
		return jsonData;
	}

	public double getCurrentTemp() {
		return currentTemp;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public double getCloudPercentage() {
		return cloudPercentage;
	}

	public double getMaxTemp() {
		return maxTemp;
	}

	public double getMinTemp() {
		return minTemp;
	}
}




