package com.sachin.hooq.Fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.sachin.hooq.Activity.MainActivity;
import com.sachin.hooq.Base.Presenter;
import com.sachin.hooq.Base.ResponseInterface;
import com.sachin.hooq.Controller.MainApplication;
import com.sachin.hooq.Model.MovieResponseModel;
import com.sachin.hooq.R;
import com.sachin.hooq.Utilities.AppConstants;
import com.sachin.hooq.Utilities.AppUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

public class SplashFragment extends Fragment {
    private Context mcontext;
    private ProgressBar progressbar_loading;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_splash, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialization
        progressbar_loading = (ProgressBar) view.findViewById(R.id.progressbar_loading);
        progressbar_loading.setVisibility(View.VISIBLE);
    }

}
