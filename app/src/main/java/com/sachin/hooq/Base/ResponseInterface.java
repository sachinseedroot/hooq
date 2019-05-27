package com.sachin.hooq.Base;

import org.json.JSONObject;


public interface ResponseInterface {
    interface view{
        void displayDialogError(Throwable throwable);
        void sendResult(JSONObject jsonObject);
    }

    interface presenter{
        void displayDialogError(Throwable throwable);
        void getData(JSONObject jsonObject);
    }
}
