package com.prismmobile.foodforthought;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Place> model = new ArrayList<>();
        model.add(new Place("Bob\'s Crab Shack",
                            "123 Main Street, Los Angeles, CA, 90038",
                            "Restaurant",
                            true));
        model.add(new Place("SturBacks Coffee",
                            "345 Cabrillo Ave, Torrance, CA, 90501",
                            "Coffee Shop",
                            false));
        model.add(new Place("DcMonald\'s Restaurant",
                            "1500 Minimum Wage Lane, Turtles, NE",
                            "Fast Food",
                            true));

        ListView list = (ListView) findViewById(R.id.listView);
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
