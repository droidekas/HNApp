package com.geronimo.hnapp.models;

import com.geronimo.hnapp.util.CommonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by 5013003250 on 12/23/2014.
 */
public class HNItem {
    private String title;
    private String by;
    private int id;
    private int[] kids;
    private int score;
    private long time;
    private String type;
    private String url;

    public HNItem() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getKids() {
        return kids;
    }

    public void setKids(int[] kids) {
        this.kids = kids;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public HNItem(JSONObject jsonHn) throws Exception {


        String title = "";
        String by = "";
        int id = 0;
        int[] kids = new int[5];
        int score = 0;
        long time = 0l;
        String type = "";
        String url = "";

        title = getString(jsonHn, "title");
        by = getString(jsonHn, "by");
        type = getString(jsonHn, "type");
        url = getString(jsonHn, "url");
        id = getInt(jsonHn, "id");
        time = getLong(jsonHn, "time");
        kids = getIntArray(jsonHn, "kids");
        score = getInt(jsonHn, "score");


        this.title = title;
        this.by = by;
        this.id = id;
        this.kids = kids;
        this.score = score;
        this.time = time;
        this.type = type;
        this.url = url;
    }

    private String getString(JSONObject j, String ppty) throws JSONException {
        if (j.has(ppty))
            return j.getString(ppty);
        return "";
    }

    private int getInt(JSONObject j, String ppty) throws JSONException {
        if (j.has(ppty))
            return j.getInt(ppty);
        return 0;
    }

    private long getLong(JSONObject j, String ppty) throws JSONException {
        if (j.has(ppty))
            return j.getLong(ppty);
        return 0;
    }

    private int[] getIntArray(JSONObject j, String ppty) throws JSONException {
        int[] arr = new int[5];
        if (j.has(ppty)) {
            JSONArray ja = j.getJSONArray(ppty);
            arr = new int[ja.length()];
            for (int q = 0; q < ja.length(); q++)
                arr[q] = ja.getInt(q);
        }
        return arr;
    }

    @Override
    public String toString() {
        return "HNItem{" +
                "title='" + title + '\'' +
                ", by='" + by + '\'' +
                ", id=" + id +
                ", kids=" + Arrays.toString(kids) +
                ", score=" + score +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
