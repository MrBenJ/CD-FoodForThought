package com.prismmobile.foodforthought;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Place> model = new ArrayList<>();
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
                    getString(R.string.apiKey) +
                    "location=" + latitude + "," + longitude +
                    "radius=" + 500 +
                    "type=food" +
                    "rankby=distance") //  500 meters
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

                    JsonParser parser = new JsonParser();
                    JsonElement data = parser.parse(response.body().toString());
                    Log.v(TAG, data.toString());
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
