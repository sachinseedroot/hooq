package com.sachin.hooq.Utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.Toast;

import com.sachin.hooq.Activity.MainActivity;
import com.sachin.hooq.Model.MovieResponseModel;
import com.sachin.hooq.Model.MovieResponseSingleModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

public class AppUtilities {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showAlertDialog(Context context, String title, String msg) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(msg)
                    .setCancelable(false)
                    .setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });


            //Creating dialog box
            AlertDialog alert = builder.create();
            alert.setTitle(title);
            if (MainActivity.AppInForeground) {
                alert.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Typeface applyTypeFace(Context context, String fontName) {
        Typeface typeface = Typeface.createFromAsset(context.getApplicationContext().getAssets(), fontName);
        return typeface;
    }

    public static void showToast(Context mc, String msg) {
        Toast.makeText(mc, msg, Toast.LENGTH_SHORT).show();
    }

    public static String getDate(String date) {

        String dateTime = "";
        if (!TextUtils.isEmpty(date)) {
            try {
                String format = "yyyy-MM-dd";
                SimpleDateFormat format1 = new SimpleDateFormat("d MMM yyyy");
                SimpleDateFormat format2 = new SimpleDateFormat(format, Locale.ENGLISH);
                format2.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
                Date convertedDate = format2.parse(date);
                dateTime = format1.format(convertedDate);

            } catch (Exception e) {
                dateTime = "";
            }
        }
        return dateTime;
    }

    public static ArrayList<MovieResponseModel> movieResponseModels(ArrayList<MovieResponseModel> movieResponseModels, MovieResponseSingleModel generidObjects) {
        ArrayList<MovieResponseModel> movieResponseModelsNeo = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();
        JSONArray jsonArray2 = generidObjects.genres;
        try {
            if (generidObjects != null) {
                for (int i = 0; i < jsonArray2.length(); i++) {
                    JSONObject jsonObject = jsonArray2.optJSONObject(i);
                    integers.add(jsonObject.optInt("id", 0));
                }
            }
            if (movieResponseModels != null && integers != null) {
                for (int i = 0; i < movieResponseModels.size(); i++) {
                    MovieResponseModel movieResponseModel = movieResponseModels.get(i);
                    JSONArray jsonArray = new JSONArray(movieResponseModel.genre_ids);
                    for (int k = 0; k < jsonArray.length(); k++) {
                        int value = jsonArray.optInt(k, 0);
                        if (integers.contains(value)
                                && !movieResponseModelsNeo.contains(movieResponseModel)
                                && movieResponseModel._id != generidObjects._id) {
                            movieResponseModelsNeo.add(movieResponseModel);
                        }
                    }

                }
            }
        } catch (Exception e) {
            movieResponseModelsNeo = null;
        }

        return movieResponseModelsNeo;
    }

    public static String getGenre(JSONArray jsonArray) {
        try {
            StringBuilder str = new StringBuilder();
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    if (i == 0) {
                        str.append(jsonObject.optString("name", ""));
                    } else {
                        str.append(", " + jsonObject.optString("name", ""));
                    }
                }
            }
            return str.toString();
        } catch (Exception e) {

        }
        return "";
    }
}
