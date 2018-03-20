package com.codeb1ooded.digifest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by megha on 03/12/17.
 */

public class ItemsAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> myItems;

    public ItemsAdapter(Context context, ArrayList<String> objects) {
        super(context, 0, objects);
        myItems = objects;
        this.context = context;
    }

    static class ViewHolder{
        TextView titleTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.list_item, null);
            ViewHolder vh = new ViewHolder();
            vh.titleTextView = convertView.findViewById(R.id.title_text_view);
            convertView.setTag(vh);
        }
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.titleTextView.setText(myItems.get(position));
        return convertView;
    }
}
