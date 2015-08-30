package com.prismmobile.foodforthought;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjunya on 8/25/15.
 *
 *
 */
public class FoodAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Place> mList;

    public FoodAdapter(Context context, ArrayList<Place> list) {
        this.inflater = LayoutInflater.from(context);
        this.mList = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
