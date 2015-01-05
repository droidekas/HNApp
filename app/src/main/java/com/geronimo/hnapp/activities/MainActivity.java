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
import com.geronimo.hnapp.util.CommonUtil;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirections;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends Activity {
    private SwipeActionAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] content = new String[20];
        for (int i=0;i<20;i++) content[i] = "Row "+(i+1);
        ArrayAdapter<String> stringAdapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item,
                R.id.item_title,
                new ArrayList<String>(Arrays.asList(content))
        );

        // Wrap your content in a SwipeActionAdapter
        mAdapter = new SwipeActionAdapter(stringAdapter);

        // Pass a reference of your ListView to the SwipeActionAdapter
        mAdapter.setListView((ListView)findViewById(R.id.main_hn_items));

        // Set the SwipeActionAdapter as the Adapter for your ListView
       ((ListView) findViewById(R.id.main_hn_items)).setAdapter(mAdapter);
        mAdapter.addBackground(SwipeDirections.DIRECTION_NORMAL_LEFT, R.layout.row_bg_left)
                .addBackground(SwipeDirections.DIRECTION_NORMAL_RIGHT, R.layout.row_bg_right);
        mAdapter.setSwipeActionListener(new SwipeActionAdapter.SwipeActionListener(){
            @Override
            public boolean hasActions(int position){
                // All items can be swiped
                return true;
            }

            @Override
            public boolean shouldDismiss(int position, int direction){
                // Only dismiss an item when swiping normal left
                return direction == SwipeDirections.DIRECTION_NORMAL_LEFT;
            }

            @Override
            public void onSwipe(int[] positionList, int[] directionList){
                for(int i=0;i<positionList.length;i++) {
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

    public void  performApiCall(String url) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        CommonUtil.logger(response.body().string(),getClass());



    }


}
