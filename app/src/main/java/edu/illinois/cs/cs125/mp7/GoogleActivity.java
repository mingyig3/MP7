package edu.illinois.cs.cs125.mp7;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import org.json.JSONObject;

public class GoogleActivity extends AppCompatActivity {
    private String url = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyBKk02I_YCN5C-mZ_qmORWZc1VIAWUtgx4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL httpUrl = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("homeMobileCountryCode", getMobileCountryCode());
                    jsonObject.put("homeMobileNetworkCode", getMobileNetworkCode());
                    jsonObject.put("considerIp", "true");
                    Log.i("MP7:Main", jsonObject.toString());
                    DataOutputStream is = new DataOutputStream(conn.getOutputStream());
                    is.writeBytes(jsonObject.toString());
                    is.flush();
                    is.close();
                    DataInputStream ba = new DataInputStream(conn.getInputStream());
                    double[] send = fromInputToResult(ba);
                    Log.i("MP7:Main", String.valueOf(conn.getResponseCode()));
                    Log.i("MP7:Main" , conn.getResponseMessage());
                    conn.disconnect();
                    Intent intent = new Intent(GoogleActivity.this,
                            APIActivity.class);
                    intent.putExtra("Location_button", send);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private int getMobileCountryCode() {
        TelephonyManager tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String networkOperator = tel.getNetworkOperator();
        return Integer.parseInt(networkOperator.substring(0, 3));
    }
    private int getMobileNetworkCode() {
        TelephonyManager tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String networkOperator = tel.getNetworkOperator();
        return Integer.parseInt(networkOperator.substring(3));
    }
    private double[] fromInputToResult(InputStream input) {
        double[] back = new double[2];
        try {
            BufferedReader bR = new BufferedReader(new InputStreamReader(input));
            String line;
            StringBuilder responseStrBuilder = new StringBuilder();
            while ((line = bR.readLine()) != null) {
                responseStrBuilder.append(line);
            }
            input.close();
            JSONObject result= new JSONObject(responseStrBuilder.toString());
            if (result.has("location")) {
                JSONObject location = result.getJSONObject("location");
                back[0] = location.getDouble("lat");
                back[1] = location.getDouble("lng");
                Log.d("MP7:Main", Arrays.toString(back));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return back;
    }
}
