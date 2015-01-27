package com.geronimo.hnapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.geronimo.hnapp.R;
import com.geronimo.hnapp.models.HNItem;
import com.geronimo.hnapp.views.VerticalTextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by 5013003250 on 12/23/2014.
 */
public class HNItemAdpater extends BaseAdapter {


    private ArrayList<HNItem> itemList;

    public HNItemAdpater(ArrayList<HNItem> itemList) {

        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) parent.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
        }
        ViewHolder vh = new ViewHolder(convertView);
        vh.score.setText(String.valueOf(itemList.get(position).getScore()));
        vh.title.setText(itemList.get(position).getTitle());
        vh.by.setText(itemList.get(position).getBy());
        String timeText = "";
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(itemList.get(position).getTime());
        timeText = (cal.get(Calendar.YEAR) + " " + (cal.get(Calendar.MONTH) + 1) + " "
                + cal.get(Calendar.DAY_OF_MONTH) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
                + cal.get(Calendar.MINUTE));
        vh.time.setText(timeText);
        vh.type.setText(String.valueOf(itemList.get(position).getKids().length));
        return convertView;
    }

    public static class ViewHolder {
        // each data item is just a string in this case
        public TextView score;
        public TextView title;
        public TextView by;
        public TextView time;
        public VerticalTextView type;


        public ViewHolder(View v) {
            score = (TextView) v.findViewById(R.id.item_score);
            title = (TextView) v.findViewById(R.id.item_title);
            by = (TextView) v.findViewById(R.id.item_by);
            time = (TextView) v.findViewById(R.id.item_time);
            type = (VerticalTextView) v.findViewById(R.id.item_comments_count);
        }
    }
}
