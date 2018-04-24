package edu.illinois.cs.cs125.mp7;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
/** check if the ID exists. */
public final class Check {
    /** Constructor. */
    private Check() {
    }
    /**
     * Check availability of ID.
     * @param context context of activity
     * @param input ID to check
     * @return true for finding ID, false for not finding ID
     */
    public static boolean checkID(final Context context, final int input) {
        String json;
        try {
            InputStream is = context.getAssets().open("city.list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray root = new JSONArray(json);
            for (int i = 0; i < root.length(); i++) {
                JSONObject each = root.getJSONObject(i);
                if (each.getInt("id") == input) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Check availability of city name.
     * @param context context of activity
     * @param input city name to check
     * @return true for finding city, false for not finding city
     */
    public static boolean checkName(final Context context, final String input) {
        String json;
        try {
            InputStream is = context.getAssets().open("city.list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray root = new JSONArray(json);
            for (int i = 0; i < root.length(); i++) {
                JSONObject each = root.getJSONObject(i);
                if (each.getString("name").equals(input)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
