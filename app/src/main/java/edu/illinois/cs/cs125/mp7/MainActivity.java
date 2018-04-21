package edu.illinois.cs.cs125.mp7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "MP7:Main";
    /**
     * Run when this activity comes to the foreground.
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set content view
        setContentView(R.layout.activity_main);
        final EditText City_Name = findViewById(R.id.City_Name);
        final Button Name_Button = findViewById(R.id.Name_Button);
        final EditText City_ID = findViewById(R.id.City_ID);
        final Button ID_Button = findViewById(R.id.ID_Button);
        final EditText lat = findViewById(R.id.lat);
        final EditText lon = findViewById(R.id.lon);
        final Button Lat_Lon_Button = findViewById(R.id.Lat_Lon_Button);
        final EditText ZIP_Location = findViewById(R.id.ZIP_Location);
        final Button ZIP_Button = findViewById(R.id.ZIP_Button);
        // Initialize check class
        final Check check = new Check();
        // Click city button
        Name_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Start Name button clicked");
                String test = City_Name.getText().toString();
                if (test.length() == 0 || test.matches("City Name")) {
                    Log.d(TAG, "Not enter a city name");
                    Toast.makeText(MainActivity.this, "You did not enter a city name", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, API_Activity.class);
                    intent.putExtra("Name_button",test);
                    startActivity(intent);
                }
            }
        });
        // Click id button
        ID_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Start ID button clicked");
                String test = City_ID.getText().toString();
                if (test.length() == 0 || test.matches("City ID")) {
                    Log.d(TAG, "Not enter a city ID");
                    Toast.makeText(MainActivity.this, "You did not enter a city ID", Toast.LENGTH_SHORT).show();
                } else if (!StringUtils.isNumeric(test) || !check.CheckID(MainActivity.this, Integer.parseInt(test))) {
                    Log.d(TAG, "Not enter a valid city ID");
                    Toast.makeText(MainActivity.this, "You did not enter a valid city ID", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, API_Activity.class);
                    intent.putExtra("ID_button",test);
                    startActivity(intent);
                }
            }
        });
        // Click Lat_Lon button
        Lat_Lon_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Start Lat_Lon button clicked");
                String latTest = lat.getText().toString();
                String lonTest = lon.getText().toString();
                if (latTest.length() == 0 || latTest.matches("Latitude") || lonTest.length() == 0 || lonTest.matches("Longitude")) {
                    Log.d(TAG, "Not enter latitude or longitude");
                    Toast.makeText(MainActivity.this, "You did not enter latitude or longitude", Toast.LENGTH_SHORT).show();
                } else if (!NumberUtils.isCreatable(latTest) || !NumberUtils.isCreatable(lonTest) || Float.parseFloat(latTest) > 90 || Float.parseFloat(latTest) < 0 || Float.parseFloat(lonTest) < -180 || Float.parseFloat(lonTest) > 180) {
                    Log.d(TAG, "Not enter valid latitude or longitude");
                    Toast.makeText(MainActivity.this, "You did not enter a valid latitude or longitude", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, API_Activity.class);
                    String[] send = {latTest, lonTest};
                    intent.putExtra("Lat_Lon_button",send);
                    startActivity(intent);
                }
            }
        });
        // Click ZIP button
        ZIP_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Start ZIP button clicked");
                String test = ZIP_Location.getText().toString();
                if (test.length() == 0 || test.matches("ZIP Code")) {
                    Log.d(TAG, "Not enter a ZIP code");
                    Toast.makeText(MainActivity.this, "You did not enter a ZIP code", Toast.LENGTH_SHORT).show();
                } else if (!StringUtils.isNumeric(test)) {
                    Log.d(TAG, "Not enter a valid ZIP code");
                    Toast.makeText(MainActivity.this, "You did not enter a valid ZIP code", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, API_Activity.class);
                    intent.putExtra("ZIP_button",test);
                    startActivity(intent);
                }
            }
        });
    }
    //Run when this activity is no longer visible.
    @Override
    protected void onPause() {
        super.onPause();
    }
}
