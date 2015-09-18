package com.prismmobile.foodforthought;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    OkHttpClient okHttpClient;

    private final static String TAG = MainActivity.class.getSimpleName();
    ArrayList<Place> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = new ArrayList<>();
        okHttpClient = new OkHttpClient();
        getPlaces();

        ListView list = (ListView) findViewById(R.id.listView);
        FoodAdapter adapter = new FoodAdapter(this, model);
        list.setAdapter(adapter);

    }

    private void getPlaces() {
        // Get some cool places!

        // TODO: Get user location

        double latitude = 51.5033630;
        double longitude = -0.1276250;
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
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    // SOMETHING AWESOME HAPPENED
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    String data = response.body().string();
                    JsonElement element = parser.parse(data);

                    gson.fromJson(element, Place.class);

                    if(element.isJsonObject()) {
                        JsonArray results = element.getAsJsonObject()
                                .get("results")
                                .getAsJsonArray();

                        for(int i = 0; i < results.size(); i++) {
                            String name;
                            String address;
                            boolean icon;
                            JsonObject place = results.get(i).getAsJsonObject();
                            name = place.get("name").getAsString();
                            address = place.get("vicinity").getAsString();
                            icon = place.get("opening_hours").getAsJsonObject().get("open_now").getAsBoolean();
                            Place item = new Place(
                                    name,
                                    address,
                                    null,
                                    false
                            );

                            model.add(item);
                        }
                    }




                }
            });

        }
        catch(Exception e) {
            e.printStackTrace();
        }

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
