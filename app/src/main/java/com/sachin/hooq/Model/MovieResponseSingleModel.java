package com.sachin.hooq.Model;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

public class MovieResponseSingleModel {


    public int _id;

    public String title;

    public String overview;

    public String release_date;

    public String poster_path;

    public JSONArray genres;

    public MovieResponseSingleModel(JSONObject jsonObject) {
        _id = jsonObject.optInt("id");
        title = jsonObject.optString("title");
        overview = jsonObject.optString("overview");
        release_date = jsonObject.optString("release_date");
        poster_path = jsonObject.optString("poster_path");
        genres = jsonObject.optJSONArray("genres");
    }


}


