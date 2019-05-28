package com.sachin.hooq.Controller;

import android.app.Application;
//import android.arch.persistence.room.Room;
import android.content.Context;

//import com.sachin.hooq.DB.AppDataBase;


public class MainApplication extends Application {

    public static Context mcontext;
//    public static AppDataBase appDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = this;
    }

//    public static synchronized AppDataBase getDBinstance() {
//
//        try {
//            if (appDataBase == null) {
//                appDataBase = Room.databaseBuilder(mcontext, AppDataBase.class, "kavokerr").allowMainThreadQueries().build(); ///data/user/0/com.dhr.kavokerrindia/databases/kavokerr
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return appDataBase;
//    }
//
//    public static void destroyInstance() {
//        if (appDataBase != null && appDataBase.isOpen()) {
//            appDataBase.close();
//            appDataBase = null;
//        }
//    }


}
