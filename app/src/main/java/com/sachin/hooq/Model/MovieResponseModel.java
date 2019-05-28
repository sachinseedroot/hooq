package com.sachin.hooq.Model;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

public class MovieResponseModel implements Parcelable {


    public int _id;

    public String title;

    public String overview;

    public String release_date;

    public String poster_path;

    public String genre_ids;

//
//    public int getId() {
//        return _id;
//    }
//
//    public void setId(int id) {
//        this._id = id;
//    }
//
//    public String gettitle() {
//        return title;
//    }
//
//    public void settitle(String id) {
//        this.title = id;
//    }
//
//    public String getoverview() {
//        return overview;
//    }
//
//    public void setoverview(String id) {
//        this.overview = id;
//    }
//
//    public String getrelease_date() {
//        return release_date;
//    }
//
//    public void setrelease_date(String id) {
//        this.release_date = id;
//    }
//
//    public String getposter_path() {
//        return poster_path;
//    }
//
//    public void setposter_path(String id) {
//        this.poster_path = id;
//    }
//
//    public String getgenre_ids() {
//        return genre_ids;
//    }
//
//    public void setgenre_ids(JSONArray id) {
//        this.genre_ids = id.toString();
//    }

    public MovieResponseModel(JSONObject jsonObject) {
        _id = jsonObject.optInt("_id");
        title = jsonObject.optString("title");
        overview = jsonObject.optString("overview");
        release_date = jsonObject.optString("release_date");
        poster_path = jsonObject.optString("poster_path");
        genre_ids = jsonObject.optJSONArray("genre_ids").toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(poster_path);
        dest.writeString(genre_ids);
    }

    public MovieResponseModel(Parcel in) {
        this._id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.poster_path = in.readString();
        this.genre_ids = in.readString();
    }

    public static final Creator<MovieResponseModel> CREATOR = new Creator<MovieResponseModel>() {
        public MovieResponseModel createFromParcel(Parcel source) {
            return new MovieResponseModel(source);
        }

        public MovieResponseModel[] newArray(int size) {
            return new MovieResponseModel[size];
        }
    };
}


