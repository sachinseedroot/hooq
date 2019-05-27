package com.sachin.hooq.Model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONArray;

import java.util.Arrays;

@Entity(tableName = "MovieResponseModelTable")
public class MovieResponseModel {


    @NonNull
    @PrimaryKey
    public int _id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "overview")
    public String overview;

    @ColumnInfo(name = "release_date")
    public String release_date;

    @ColumnInfo(name = "poster_path")
    public String poster_path;

    @ColumnInfo(name = "genre_ids")
    public String genre_ids;


    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String id) {
        this.title = id;
    }

    public String getoverview() {
        return overview;
    }

    public void setoverview(String id) {
        this.overview = id;
    }

    public String getrelease_date() {
        return release_date;
    }

    public void setrelease_date(String id) {
        this.release_date = id;
    }

    public String getposter_path() {
        return poster_path;
    }

    public void setposter_path(String id) {
        this.poster_path = id;
    }

    public String getgenre_ids() {
        return genre_ids;
    }

    public void setgenre_ids(JSONArray id) {
        this.genre_ids = id.toString();
    }

}


