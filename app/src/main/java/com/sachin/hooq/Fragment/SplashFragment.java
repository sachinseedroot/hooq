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
import com.sachin.hooq.Network.API;
import com.sachin.hooq.R;
import com.sachin.hooq.Utilities.AppConstants;
import com.sachin.hooq.Utilities.AppUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SplashFragment extends Fragment implements ResponseInterface.view {
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
        Presenter presenter = new Presenter(this);
        presenter.getData(AppConstants.API_GETLIST
                .replace("@pagenumber", "1")
                .replace("@api_key", mcontext.getResources().getString(R.string.api_key)));
    }

    @Override
    public void displayDialogError(Throwable throwable) {
        AppUtilities.showAlertDialog(mcontext, "Error!", throwable.getMessage());
    }

    @Override
    public void sendResult(Object o) {
        new LoadMovies().execute(o);
    }

    public class LoadMovies extends AsyncTask<Object, Void, ArrayList<MovieResponseModel>> {

        @Override
        protected ArrayList<MovieResponseModel> doInBackground(Object... objects) {
            ArrayList<MovieResponseModel> movieResponseModels = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(objects[0].toString());
                if (jsonObject != null && jsonObject.length() > 0) {
                    JSONArray jsonArray = jsonObject.optJSONArray("results");
                    if (jsonArray != null && jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1 != null) {
                                MovieResponseModel movieResponseModel = new MovieResponseModel(jsonObject1);
                                if (movieResponseModel != null) {
                                    movieResponseModels.add(movieResponseModel);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return movieResponseModels;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieResponseModel> movieResponseModels) {
            super.onPostExecute(movieResponseModels);
            progressbar_loading.setVisibility(View.GONE);
            if (movieResponseModels != null && movieResponseModels.size() > 0) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("data", movieResponseModels);
                ((MainActivity) mcontext).loadHomePage(bundle);
            } else {
                AppUtilities.showAlertDialog(mcontext, "Error", "Sorry, coudn't load at this time.");
            }
        }
    }
}
