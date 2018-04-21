package edu.illinois.cs.cs125.mp7;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonProceed {
    public double longitude;
    public double latitude;
    public String main_weather;
    public String description;
    public double temperature;
    public double min_temperature;
    public double max_temperature;
    public int humidity;
    public double pressure;
    public double wind_speed;
    public double wind_degree;
    public String country;
    public String city_name;
    public int city_ID;
    public JsonProceed(JSONObject input) {
        try {
            this.city_ID = input.getInt("id");
            this.city_name = input.getString("name");
            JSONObject sys = input.getJSONObject("sys");
            this.country = sys.getString("country");
            JSONObject wind = input.getJSONObject("wind");
            this.wind_speed = wind.getDouble("speed");
            this.wind_degree = wind.getDouble("deg");
            JSONObject main = input.getJSONObject("main");
            this.pressure = main.getDouble("pressure");
            this.humidity = main.getInt("humidity");
            this.min_temperature = main.getDouble("temp_min");
            this.max_temperature = main.getDouble("temp_max");
            this.temperature = main.getDouble("temp");
            JSONArray weather = input.getJSONArray("weather");
            JSONObject first = weather.getJSONObject(0);
            this.main_weather = first.getString("main");
            this.description = first.getString("description");
            JSONObject coord = input.getJSONObject("coord");
            this.longitude = coord.getDouble("lon");
            this.latitude = coord.getDouble("lat");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
