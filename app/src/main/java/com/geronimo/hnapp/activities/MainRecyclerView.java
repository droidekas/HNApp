package com.geronimo.hnapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.geronimo.hnapp.R;
import com.geronimo.hnapp.adapters.HNItemAdpater;
import com.geronimo.hnapp.adapters.RecyclerViewAdapter;
import com.geronimo.hnapp.models.HNItem;
import com.geronimo.hnapp.util.CommonUtil;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MainRecyclerView extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter hnAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        hnAdapter = new RecyclerViewAdapter(getData());
//        mAdapter = new SwipeActionAdapter(hnAdapter);
        mRecyclerView.setAdapter(hnAdapter);


    }


    ArrayList<HNItem> getData() {
        ArrayList<HNItem> data = new ArrayList<>();
        int[] storyList = new int[]{8825375,
                8823938,
                8824752,
                8824691,
                8825728,
                8825456,
                8823085,
                8825096,
                8825308,
                8823733,
                8825002,
                8825244,
                8824789,
                8823443,
                8822573,
                8825017,
                8824544,
                8824671,
                8824303,
                8823150,
                8824931,
                8825074,
                8825421,
                8825750,
                8824361,
                8824921,
                8825403,
                8824026,
                8822974,
                8825315,
                8824282,
                8823385,
                8824915,
                8824072,
                8823487,
                8822839,
                8824708,
                8825566,
                8823532,
                8825037,
                8822705,
                8825696,
                8823472,
                8822816,
                8823760,
                8823624,
                8825103,
                8825131,
                8823248,
                8824312,
                8824374,
                8823174,
                8823711,
                8822062,
                8822808,
                8822952,
                8824986,
                8824927,
                8824726,
                8824256,
                8824760,
                8820763,
                8823225,
                8823105,
                8823981,
                8821808,
                8824515,
                8823719,
                8821138,
                8819165,
                8823494,
                8824592,
                8824440,
                8819085,
                8823873,
                8824115,
                8821068,
                8822851,
                8823687,
                8823107,
                8821931,
                8819811,
                8824956,
                8823921,
                8819622,
                8820040,
                8822098,
                8819614,
                8821722,
                8823078,
                8822892,
                8819350,
                8822797,
                8823722,
                8821212,
                8823984,
                8821966,
                8818376,
                8821015,
                8820941};
        for (int i : storyList) {
            try {
                JSONObject jsonHn = loadJSONFromAsset(i);
                data.add(new HNItem(jsonHn));
            } catch (Exception e) {
                CommonUtil.logger(e, getClass());
            }
        }
        return data;

    }


    public JSONObject loadJSONFromAsset(int filename) throws Exception {
        JSONObject jsonOp = new JSONObject();
        String jsonString = null;
        InputStream is = getAssets().open(String.valueOf(filename) + ".json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        jsonString = new String(buffer, "UTF-8");
        return jsonOp = new JSONObject(jsonString);

    }
}
