package com.sachin.hooq.Base;

import com.sachin.hooq.Network.API;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class Presenter implements ResponseInterface.presenter {

    private final ResponseInterface.view mainview;

    public Presenter(ResponseInterface.view view) {
        mainview = view;
    }

    @Override
    public void displayDialogError(Throwable throwable) {

    }

    @Override
    public void getData(String url) {
        final Object[] reposneObject = {null};
        API.getWebService().getMovieData(url)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        reposneObject[0] = o;
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainview.displayDialogError(e);
                    }

                    @Override
                    public void onComplete() {
                        mainview.sendResult(reposneObject);
                    }
                });
    }
}
