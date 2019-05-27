package com.sachin.hooq.Base;

import org.json.JSONObject;


public class Presenter implements ResponseInterface.presenter{

    private final ResponseInterface.view mainview;

    public Presenter(ResponseInterface.view view) {
        mainview = view;
    }

    @Override
    public void displayDialogError(Throwable throwable) {

    }

    @Override
    public void getData(JSONObject jsonObject) {

    }
}
