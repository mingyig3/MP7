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
/** Main activity of the app. */
public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "MP7:Main";
    /** maximum latitude. */
    private static final int MAX_LATITUDE = 90;
    /** minimum latitude. */
    private static final int MIN_LATITUDE = -90;
    /** maximum longitude. */
    private static final int MAX_LONGITUDE = 180;
    /** minimum longitude. */
    private static final int MIN_LONGITUDE = -180;
    /**
     * Run when this activity comes to the foreground.
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set content view
        setContentView(R.layout.activity_main);
        final EditText cityName = findViewById(R.id.City_Name);
        final Button nameButton = findViewById(R.id.Name_Button);
        final EditText cityID = findViewById(R.id.City_ID);
        final Button idButton = findViewById(R.id.ID_Button);
        final EditText lat = findViewById(R.id.lat);
        final EditText lon = findViewById(R.id.lon);
        final Button latLonButton = findViewById(R.id.Lat_Lon_Button);
        final EditText zipLocation = findViewById(R.id.ZIP_Location);
        final Button zipButton = findViewById(R.id.ZIP_Button);
        final Button locationButton = findViewById(R.id.Location_Button);
        // Click city button
        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Start Name button clicked");
                String test = cityName.getText().toString();
                if (test.length() == 0) {
                    Log.d(TAG, "Not enter a city name");
                    Toast.makeText(MainActivity.this,
                            "You did not enter a city name",
                            Toast.LENGTH_SHORT).show();
                } else if (!Check.checkName(MainActivity.this, test)) {
                    Log.d(TAG, "Not enter a valid city name");
                    Toast.makeText(MainActivity.this,
                            "You did not enter a valid city name",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this,
                            APIActivity.class);
                    intent.putExtra("Name_button", test);
                    startActivity(intent);
                }
            }
        });
        // Click id button
        idButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Start ID button clicked");
                String test = cityID.getText().toString();
                if (test.length() == 0) {
                    Log.d(TAG, "Not enter a city ID");
                    Toast.makeText(MainActivity.this,
                            "You did not enter a city ID",
                            Toast.LENGTH_SHORT).show();
                } else if (!StringUtils.isNumeric(test)
                        || !Check.checkID(MainActivity.this,
                        Integer.parseInt(test))) {
                    Log.d(TAG, "Not enter a valid city ID");
                    Toast.makeText(MainActivity.this,
                            "You did not enter a valid city ID",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this,
                            APIActivity.class);
                    intent.putExtra("ID_button", test);
                    startActivity(intent);
                }
            }
        });
        // Click Lat_Lon button
        latLonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Start Lat_Lon button clicked");
                String latTest = lat.getText().toString();
                String lonTest = lon.getText().toString();
                if (latTest.length() == 0 || lonTest.length() == 0) {
                    Log.d(TAG, "Not enter latitude or longitude");
                    Toast.makeText(MainActivity.this,
                            "You did not enter latitude or longitude",
                            Toast.LENGTH_SHORT).show();
                } else if (!NumberUtils.isCreatable(latTest)
                        || !NumberUtils.isCreatable(lonTest)
                        || Float.parseFloat(latTest) > MAX_LATITUDE
                        || Float.parseFloat(latTest) < MIN_LATITUDE
                        || Float.parseFloat(lonTest) < MIN_LONGITUDE
                        || Float.parseFloat(lonTest) > MAX_LONGITUDE) {
                    Log.d(TAG, "Not enter valid latitude or longitude");
                    Toast.makeText(MainActivity.this,
                            "You did not enter a valid latitude or longitude",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this,
                            APIActivity.class);
                    String[] send = {latTest, lonTest};
                    intent.putExtra("Lat_Lon_button", send);
                    startActivity(intent);
                }
            }
        });
        // Click ZIP button
        zipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Start ZIP button clicked");
                String test = zipLocation.getText().toString();
                if (test.length() == 0) {
                    Log.d(TAG, "Not enter a ZIP code");
                    Toast.makeText(MainActivity.this,
                            "You did not enter a ZIP code",
                            Toast.LENGTH_SHORT).show();
                } else if (!StringUtils.isNumeric(test)) {
                    Log.d(TAG, "Not enter a valid ZIP code");
                    Toast.makeText(MainActivity.this,
                            "You did not enter a valid ZIP code",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this,
                            APIActivity.class);
                    intent.putExtra("ZIP_button", test);
                    startActivity(intent);
                }
            }
        });
        // Click Location button
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Start Location button clicked");
                Intent intent = new Intent(MainActivity.this,
                        GoogleActivity.class);
                startActivity(intent);
            }
        });
    }
    /** Run when this activity is no longer visible. */
    @Override
    protected void onPause() {
        super.onPause();
    }
}
