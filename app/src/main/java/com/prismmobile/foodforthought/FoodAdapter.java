package com.prismmobile.foodforthought;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
        ViewHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.view_place, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.place_title);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(mList.get(position).getName());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }
    @Override
    public int getCount() {
        return mList.size();
    }
    static class ViewHolder {
        TextView title;
    }
}

