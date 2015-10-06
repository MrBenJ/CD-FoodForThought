package com.prismmobile.foodforthought;

import android.app.AlertDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    OkHttpClient okHttpClient;

    private final static String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Place> model;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = new ArrayList<>();
        okHttpClient = new OkHttpClient();
        getPlaces();

        list = (ListView) findViewById(R.id.listView);


    }

    private void getPlaces() {
        // Get some cool places!

        Location location = LocationHelper.getLocation(this);

        if(location != null) {
            Log.i(TAG, "Location!");
        }
        double latitude = 33.8303090;
        double longitude = -118.3068440;

        try {
            Request request = new Request.Builder()
                    .url("https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=" +
                    getString(R.string.apiKey) + "&" +
                    "location=" + latitude + "," + longitude + "&" +
                    "radius=" + 500 + "&" +
                    "type=food" + "&" +
                    "rankBy=distance") //  500 meters
                    .build();

            Log.i(TAG, request.toString());

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    // SOMETHING WENT WRONG
                    // TODO: Insert AlertDialog here!

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    // SOMETHING AWESOME HAPPENED
                    Handler uiThread = new Handler(getBaseContext().getMainLooper());
                    JsonParser parser = new JsonParser();
                    String data = response.body().string();
                    JsonElement element = parser.parse(data);



                    if(element.isJsonObject()) {
                        JsonArray results = element.getAsJsonObject()
                                .get("results")
                                .getAsJsonArray();

                        for(int i = 0; i < results.size(); i++) {
                            JsonObject place = results.get(i).getAsJsonObject();
                            String name = place.get("name").getAsString();
                            String address = place.get("vicinity").getAsString();
                            boolean isOpen;

                            if(place.has("opening_hours")) {
                                JsonObject openHours = place.get("opening_hours").getAsJsonObject();
                                isOpen = openHours.get("open_now").getAsBoolean();
                            }
                            else {
                                isOpen = false;
                            }

                            Place item = new Place(
                                    name,
                                    address,
                                    isOpen
                            );

                            model.add(item);
                        }

                        uiThread.post(new Runnable() {
                            @Override
                            public void run() {
                                buildListview();
                            }
                        });
                    }
                    else {
                        Log.e(TAG, "=====FAILURE=====");
                        Log.e(TAG, "element is not a JsonElement");
                    }



                }
            });

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    private void buildListview() {
        FoodAdapter adapter = new FoodAdapter(this, model);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
