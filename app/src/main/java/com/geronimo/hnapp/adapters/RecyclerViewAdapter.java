package com.geronimo.hnapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geronimo.hnapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import com.geronimo.hnapp.models.HNItem;
import com.geronimo.hnapp.views.VerticalTextView;

/**
 * Created by 5013003250 on 1/5/2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<HNItem> itemList;


    public RecyclerViewAdapter(ArrayList<HNItem> itemList) {
        this.itemList = itemList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView score;
        public TextView title;
        public TextView by;
        public TextView time;
        public VerticalTextView type;


        public ViewHolder(View v) {
            super(v);
            score = (TextView) v.findViewById(R.id.item_score);
            title = (TextView) v.findViewById(R.id.item_title);
            by = (TextView) v.findViewById(R.id.item_by);
            time = (TextView) v.findViewById(R.id.item_time);
            type = (VerticalTextView) v.findViewById(R.id.item_comments_count);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.score.setText(String.valueOf(itemList.get(position).getScore()));
        holder.title.setText(itemList.get(position).getTitle());
        holder.by.setText(itemList.get(position).getBy());
        String timeText = "";
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(itemList.get(position).getTime());
        timeText = (cal.get(Calendar.YEAR) + " " + (cal.get(Calendar.MONTH) + 1) + " "
                + cal.get(Calendar.DAY_OF_MONTH) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
                + cal.get(Calendar.MINUTE));
        holder.time.setText(timeText);
        holder.type.setText(itemList.get(position).getType());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
