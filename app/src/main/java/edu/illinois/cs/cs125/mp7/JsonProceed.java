package edu.illinois.cs.cs125.mp7;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/** Class of proceeding Json. */
public class JsonProceed {
    /** Longitude. */
    private double longitude;
    /**
     * Get longitude.
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }
    /** Latitude. */
    private double latitude;
    /**
     * Get latitude.
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }
    /** Main weather. */
    private String mainWeather;
    /**
     * Get main weather.
     * @return main weather
     */
    public String getMainWeather() {
        return mainWeather;
    }
    /** Description of weather. */
    private String description;
    /**
     * Get description of weather.
     * @return description of weather
     */
    public String getDescription() {
        return description;
    }
    /** Temperature. */
    private double temperature;
    /**
     * Get temperature.
     * @return temperature
     */
    public double getTemperature() {
        return temperature;
    }
    /** Minimum temperature. */
    private double minTemperature;
    /**
     * Get minimum temperature.
     * @return minimum temperature
     */
    public double getMinTemperature() {
        return minTemperature;
    }
    /** Maximum temperature. */
    private double maxTemperature;
    /**
     * Get maximum temperature.
     * @return maximum temperature
     */
    public double getMaxTemperature() {
        return maxTemperature;
    }
    /** Humidity. */
    private int humidity;
    /**
     * Get humidity.
     * @return humidity
     */
    public int getHumidity() {
        return humidity;
    }
    /** Pressure. */
    private double pressure;
    /**
     * Get pressure.
     * @return pressure
     */
    public double getPressure() {
        return pressure;
    }
    /** Wind speed. */
    private double windSpeed;
    /**
     * Get wind speed.
     * @return wind speed
     */
    public double getWindSpeed() {
        return windSpeed;
    }
    /** Wind degree. */
    private double windDegree;
    /**
     * Get wind degree.
     * @return wind degree
     */
    public double getWindDegree() {
        return windDegree;
    }
    /** Country. */
    private String country;
    /**
     * Get country.
     * @return country
     */
    public String getCountry() {
        return country;
    }
    /** City name. */
    private String cityName;
    /**
     * Get city name.
     * @return city name
     */
    public String getCityName() {
        return cityName;
    }
    /** City ID. */
    private int cityID;
    /**
     * Get city ID.
     * @return city ID
     */
    public int getCityID() {
        return cityID;
    }
    /**
     * Create JsonProceed class.
     * @param input Json object
     */
    public JsonProceed(final JSONObject input) {
        try {
            this.cityID = input.getInt("id");
            this.cityName = input.getString("name");
            JSONObject sys = input.getJSONObject("sys");
            this.country = sys.getString("country");
            JSONObject wind = input.getJSONObject("wind");
            this.windSpeed = wind.getDouble("speed");
            this.windDegree = wind.getDouble("deg");
            JSONObject main = input.getJSONObject("main");
            this.pressure = main.getDouble("pressure");
            this.humidity = main.getInt("humidity");
            this.minTemperature = main.getDouble("temp_min");
            this.maxTemperature = main.getDouble("temp_max");
            this.temperature = main.getDouble("temp");
            JSONArray weather = input.getJSONArray("weather");
            JSONObject first = weather.getJSONObject(0);
            this.mainWeather = first.getString("main");
            this.description = first.getString("description");
            JSONObject coord = input.getJSONObject("coord");
            this.longitude = coord.getDouble("lon");
            this.latitude = coord.getDouble("lat");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
