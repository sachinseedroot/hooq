package com.sachin.hooq.Utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import com.sachin.hooq.Activity.MainActivity;

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
}
