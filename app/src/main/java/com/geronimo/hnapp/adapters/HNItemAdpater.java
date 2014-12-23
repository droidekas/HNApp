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

import java.util.ArrayList;

/**
 * Created by 5013003250 on 12/23/2014.
 */
public class HNItemAdpater extends BaseAdapter{

    private Context _context;
    private ArrayList<HNItem> itemList;

    public HNItemAdpater(Context _context, ArrayList<HNItem> itemList) {
        this._context = _context;
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

        if(convertView==null){
            LayoutInflater mInflater=(LayoutInflater) _context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.list_item,parent,false);
        }

        TextView titleView=(TextView) convertView.findViewById(R.id.title);
        titleView.setText(itemList.get(position).getTitle());
        return convertView;
    }
}
