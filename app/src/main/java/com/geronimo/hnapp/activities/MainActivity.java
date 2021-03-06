package com.geronimo.hnapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.geronimo.hnapp.R;
import com.geronimo.hnapp.adapters.HNItemAdpater;
import com.geronimo.hnapp.models.HNItem;
import com.geronimo.hnapp.util.CommonUtil;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirections;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends Activity {
    private SwipeActionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] content = new String[20];
        HNItemAdpater adapter = new HNItemAdpater(getData());

        // Wrap your content in a SwipeActionAdapter
        mAdapter = new SwipeActionAdapter(adapter);

        // Pass a reference of your ListView to the SwipeActionAdapter
        mAdapter.setListView((ListView) findViewById(R.id.main_hn_items));

        // Set the SwipeActionAdapter as the Adapter for your ListView
        ((ListView) findViewById(R.id.main_hn_items)).setAdapter(mAdapter);
        mAdapter.addBackground(SwipeDirections.DIRECTION_NORMAL_LEFT, R.layout.row_bg_left)
                .addBackground(SwipeDirections.DIRECTION_NORMAL_RIGHT, R.layout.row_bg_right);
        mAdapter.setSwipeActionListener(new SwipeActionAdapter.SwipeActionListener() {
            @Override
            public boolean hasActions(int position) {
                // All items can be swiped
                return true;
            }

            @Override
            public boolean shouldDismiss(int position, int direction) {
                // Only dismiss an item when swiping normal left
                return direction == SwipeDirections.DIRECTION_NORMAL_LEFT;
            }

            @Override
            public void onSwipe(int[] positionList, int[] directionList) {
                for (int i = 0; i < positionList.length; i++) {
                    int direction = directionList[i];
                    int position = positionList[i];
                    String dir = "";

                    switch (direction) {
                        case SwipeDirections.DIRECTION_FAR_LEFT:
                            dir = "Far left";
                            break;
                        case SwipeDirections.DIRECTION_NORMAL_LEFT:
                            dir = "Left";
                            break;
                        case SwipeDirections.DIRECTION_FAR_RIGHT:
                            dir = "Far right";
                            break;
                        case SwipeDirections.DIRECTION_NORMAL_RIGHT:
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Test Dialog").setMessage("You swiped right").create().show();
                            dir = "Right";
                            break;
                    }
                    Toast.makeText(
                            MainActivity.this,
                            dir + " swipe Action triggered on " + mAdapter.getItem(position),
                            Toast.LENGTH_SHORT
                    ).show();
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
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

    public void performApiCall(String url) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        CommonUtil.logger(response.body().string(), getClass());


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

                8821212,

                8821966,
                8818376,
                8821015};
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
