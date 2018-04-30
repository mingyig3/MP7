package edu.illinois.cs.cs125.mp7;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
/** Activity of API. */
public class APIActivity extends AppCompatActivity {
    /** API Key Constant. */
    private static final String KEY = "7ca2fc48d5d67aa1b06ce304bbb42591";
    /** Default logging tag for messages from the API activity. */
    private static final String TAG = "MP7:API";
    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;
    /** Background of API. */
    private ScrollView background;
    /** Progress bar. */
    private ProgressBar progressBar;
    /** Progress status. */
    private int progressStatus = 0;
    /** Help handler. */
    private Handler useHandler = new Handler();
    /**
     * Creating API activity.
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up the queue for our API requests
        requestQueue = Volley.newRequestQueue(this);
        //Set content view
        setContentView(R.layout.activity_api_);
        //Get intent message for city name
        Intent intent = getIntent();
        if (intent.hasExtra("Name_button")) {
            String message = intent.getStringExtra("Name_button");
            Log.d(TAG, message);
            barProgress();
            startNameAPICall(message);
        } else if (intent.hasExtra("ID_button")) {
            String message = intent.getStringExtra("ID_button");
            Log.d(TAG, message);
            barProgress();
            startIDAPICall(message);
        } else if (intent.hasExtra("Lat_Lon_button")) {
            String[] message = intent.getStringArrayExtra("Lat_Lon_button");
            String latMessage = message[0];
            String lonMessage = message[1];
            barProgress();
            startLatLonAPICall(latMessage, lonMessage);
        } else if (intent.hasExtra("Location_button")) {
            double[] message = intent.getDoubleArrayExtra("Location_button");
            String latMessage = Double.toString(message[0]);
            String lonMessage = Double.toString(message[1]);
            barProgress();
            startLatLonAPICall(latMessage, lonMessage);
        } else {
            String message = intent.getStringExtra("ZIP_button");
            barProgress();
            startZIPAPICall(message);
        }
    }
    /**
     * Make a call to the weather API base on city name.
     * @param input string to call API by name
     */
    void startNameAPICall(final String input) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "http://api.openweathermap.org/data/2.5/weather?q=" + input
                        + "&units=metric" + "&appid=" + KEY,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        setValue(response);
                        Log.d(TAG, "Complete Name API display");
                    }
                    }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        Log.e(TAG, error.toString());
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
    /**
     * Make a call to the weather API base on city ID.
     * @param input ID input
     */
    void startIDAPICall(final String input) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "http://api.openweathermap.org/data/2.5/weather?id=" + input
                        + "&units=metric" + "&appid=" + KEY,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        setValue(response);
                        Log.d(TAG, "Complete ID API display");
                    }
                    }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        Log.e(TAG, error.toString());
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
    /**
     * Make a call to the weather API base on lat and lon.
     * @param lat latitude input
     * @param lon longitude input
     */
    void startLatLonAPICall(final String lat, final String lon) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "http://api.openweathermap.org/data/2.5/weather?lat=" + lat
                        + "&lon=" + lon + "&units=metric" + "&appid=" + KEY,
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
    }
    /**
     * Make a call to the weather API base on ZIP.
     * @param input ZIP input
     */
    void startZIPAPICall(final String input) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.openweathermap.org/data/2.5/weather?zip="
                            + input + "&units=metric" + "&appid=" + KEY,
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
     * Set values to display.
     * @param input JSONObject input
     */
    void setValue(final JSONObject input) {
        JsonProceed jsonProceed = new JsonProceed(input);
        final TextView longitude = findViewById(R.id.Longitude);
        final TextView latitude = findViewById(R.id.Latitude);
        final TextView mainWeather = findViewById(R.id.Main_weather);
        final TextView description = findViewById(R.id.Description);
        final TextView temperature = findViewById(R.id.Temperature);
        final TextView minTemperature = findViewById(R.id.Min_Temperature);
        final TextView maxTemperature = findViewById(R.id.Max_Temperature);
        final TextView humidity = findViewById(R.id.Humidity);
        final TextView pressure = findViewById(R.id.Pressure);
        final TextView windSpeed = findViewById(R.id.Wind_Speed);
        final TextView windDegree = findViewById(R.id.Wind_Degree);
        final TextView country = findViewById(R.id.Country);
        final TextView cityName = findViewById(R.id.City_Name);
        final TextView cityID = findViewById(R.id.City_ID);
        longitude.setText(String.valueOf(jsonProceed.getLongitude()) + " degrees");
        latitude.setText(String.valueOf(jsonProceed.getLatitude()) + " degrees");
        mainWeather.setText(jsonProceed.getMainWeather());
        description.setText(jsonProceed.getDescription());
        temperature.setText(String.valueOf(jsonProceed.getTemperature())
                + " Celsius");
        minTemperature.setText(String.valueOf(jsonProceed.getMinTemperature())
                + " Celsius");
        maxTemperature.setText(String.valueOf(jsonProceed.getMaxTemperature())
                + " Celsius");
        humidity.setText(String.valueOf(jsonProceed.getHumidity()) + " %");
        pressure.setText(String.valueOf(jsonProceed.getPressure()) + " hPa");
        windSpeed.setText(String.valueOf(jsonProceed.getWindSpeed())
                + " meter/sec");
        windDegree.setText(String.valueOf(jsonProceed.getWindDegree())
                + " degrees");
        country.setText(jsonProceed.getCountry());
        cityName.setText(jsonProceed.getCityName());
        cityID.setText(String.valueOf(jsonProceed.getCityID()));
    }
    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }
    /** Make a call to progress bar. */
    private void barProgress() {
        progressBar = findViewById(R.id.ProgressBar);
        background = findViewById(R.id.Background);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus++;
                    android.os.SystemClock.sleep(50);
                    useHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                }
                useHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        background.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();

    }
}
