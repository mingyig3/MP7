package edu.illinois.cs.cs125.mp7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class API_Activity extends AppCompatActivity {
    //API Key Constant
    private static final String KEY = "7ca2fc48d5d67aa1b06ce304bbb42591";
    /** Default logging tag for messages from the API activity. */
    private static final String TAG = "MP7:API";
    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up the queue for our API requests
        requestQueue = Volley.newRequestQueue(this);
        //Set content view
        setContentView(R.layout.activity_api_);
        //Get intent message for city name
        Intent intent = getIntent();
        if(intent.hasExtra("Name_button")) {
            String message = intent.getStringExtra("Name_button");
            Log.d(TAG, message);
            startNameAPICall(message);
        } else if (intent.hasExtra("ID_button")) {
            String message = intent.getStringExtra("ID_button");
            Log.d(TAG, message);
            startIDAPICall(message);
        } else if (intent.hasExtra("Lat_Lon_button")) {
            String[] message = intent.getStringArrayExtra("Lat_Lon_button");
            String latMessage = message[0];
            String lonMessage = message[1];
            startLat_LonAPICall(latMessage, lonMessage);
        } else {
            String message = intent.getStringExtra("ZIP_button");
            startZIPAPICall(message);
        }
    }
    /**
     * Make a call to the weather API base on city name.
     */
    void startNameAPICall(String input) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.openweathermap.org/data/2.5/weather?q="+input
                            +"&units=metric"+"&appid="+KEY,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            setValue(response);
                            Log.d(TAG, "Complete display");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Make a call to the weather API base on city ID.
     */
    void startIDAPICall(String input) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.openweathermap.org/data/2.5/weather?id="+input
                            +"&units=metric"+"&appid="+KEY,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            setValue(response);
                            Log.d(TAG, "Complete display");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Make a call to the weather API base on lat and lon.
     */
    void startLat_LonAPICall(String lat, String lon) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="
                            +lon+"&units=metric"+"appid="+KEY,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            setValue(response);
                            Log.d(TAG, "Complete display");
                        }
                    }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(final VolleyError error) {
                                Log.e(TAG, error.toString());
                            }
                    }
            );
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Make a call to the weather API base on ZIP.
     */
    void startZIPAPICall(String input) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.openweathermap.org/data/2.5/weather?zip="+input
                            +"&units=metric"+"&appid="+KEY,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            setValue(response);
                            Log.d(TAG, "Complete display");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Set values to display
    void setValue(final JSONObject input) {
        try {
            JsonProceed JsonProceed = new JsonProceed(input);
            final TextView Longitude = findViewById(R.id.Longitude);
            final TextView Latitude = findViewById(R.id.Latitude);
            final TextView Main_weather = findViewById(R.id.Main_weather);
            final TextView Description = findViewById(R.id.Description);
            final TextView Temperature = findViewById(R.id.Temperature);
            final TextView Min_temperature = findViewById(R.id.Min_Temperature);
            final TextView Max_temperature = findViewById(R.id.Max_Temperature);
            final TextView Humidity = findViewById(R.id.Humidity);
            final TextView Pressure = findViewById(R.id.Pressure);
            final TextView Wind_speed = findViewById(R.id.Wind_Speed);
            final TextView Wind_degree = findViewById(R.id.Wind_Degree);
            final TextView Country = findViewById(R.id.Country);
            final TextView City_name = findViewById(R.id.City_Name);
            final TextView City_ID = findViewById(R.id.City_ID);
            Longitude.setText(String.valueOf(JsonProceed.longitude)+" degrees");
            Latitude.setText(String.valueOf(JsonProceed.latitude)+" degrees");
            Main_weather.setText(JsonProceed.main_weather);
            Description.setText(JsonProceed.description);
            Temperature.setText(String.valueOf(JsonProceed.temperature)+" Celsius");
            Min_temperature.setText(String.valueOf(JsonProceed.min_temperature)+" Celsius");
            Max_temperature.setText(String.valueOf(JsonProceed.max_temperature)+" Celsius");
            Humidity.setText(String.valueOf(JsonProceed.humidity)+" %");
            Pressure.setText(String.valueOf(JsonProceed.pressure)+" hPa");
            Wind_speed.setText(String.valueOf(JsonProceed.wind_speed)+" meter/sec");
            Wind_degree.setText(String.valueOf(JsonProceed.wind_degree)+" degrees");
            Country.setText(JsonProceed.country);
            City_name.setText(JsonProceed.city_name);
            City_ID.setText(String.valueOf(JsonProceed.city_ID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }
}
