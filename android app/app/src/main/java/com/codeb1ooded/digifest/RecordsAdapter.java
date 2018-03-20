package com.codeb1ooded.digifest;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by megha on 03/12/17.
 */

public class RecordsAdapter extends ArrayAdapter<BlockData> {

    Context context;
    ArrayList<BlockData> myItems;

    public RecordsAdapter(Context context, ArrayList<BlockData> objects) {
        super(context, 0, objects);
        myItems = objects;
        this.context = context;
    }

    static class RecordsViewHolder{
        TextView titleTextView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.list_item, null);
            RecordsViewHolder vh = new RecordsViewHolder();
            vh.titleTextView = convertView.findViewById(R.id.title_text_view);
            convertView.setTag(vh);
        }
        RecordsViewHolder vh = (RecordsViewHolder) convertView.getTag();
        vh.titleTextView.setText(myItems.get(position).getTitle());
        vh.titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecordDetailsActivity.class);
                intent.putExtra("BlockData", myItems.get(position));
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
