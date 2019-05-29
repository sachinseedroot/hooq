package com.sachin.hooq.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sachin.hooq.Activity.MainActivity;
import com.sachin.hooq.Adapter.MovieRecListAdapter;
import com.sachin.hooq.Base.Presenter;
import com.sachin.hooq.Base.ResponseInterface;
import com.sachin.hooq.Model.MovieResponseModel;
import com.sachin.hooq.Model.MovieResponseSingleModel;
import com.sachin.hooq.R;
import com.sachin.hooq.Utilities.AppConstants;
import com.sachin.hooq.Utilities.AppUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailFragment extends Fragment implements ResponseInterface.view {
    private Context mcontext;
    private int singleKey;
    private TextView titleTV, yearTV, overTV, genreTV;
    private TextView titlelb, yearlb, overlb, genrelb, related_movies_TV;
    private ImageView detail_img;
    private ProgressDialog mProgressDialog;
    private ArrayList<MovieResponseModel> movieResponseModels;
    private RecyclerView related_recyclerview;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }

    public static DetailFragment newInstance(Bundle key) {
        DetailFragment f = new DetailFragment();
        f.setArguments(key);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        singleKey = getArguments().getInt("key", 0);
        movieResponseModels = getArguments().getParcelableArrayList("data");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (singleKey == 0) {
            AppUtilities.showAlertDialog(mcontext, "No record found!", "Please go back and try another movie.");
            return;
        }

        //Initialization
        initProgressbar();
        titleTV = (TextView) view.findViewById(R.id.titleTV);
        yearTV = (TextView) view.findViewById(R.id.yearTV);
        overTV = (TextView) view.findViewById(R.id.overTV);
        detail_img = (ImageView) view.findViewById(R.id.detail_img);
        genreTV = (TextView) view.findViewById(R.id.genreTV);
        related_recyclerview = (RecyclerView) view.findViewById(R.id.related_recyclerview);

        yearlb = (TextView) view.findViewById(R.id.yearlb);
        overlb = (TextView) view.findViewById(R.id.overlb);
        titlelb = (TextView) view.findViewById(R.id.titlelb);
        genrelb = (TextView) view.findViewById(R.id.genrelb);
        related_movies_TV = (TextView) view.findViewById(R.id.related_movies_TV);

        titleTV.setTypeface(AppUtilities.applyTypeFace(mcontext, AppConstants.font_Roboto_Reg));
        yearTV.setTypeface(AppUtilities.applyTypeFace(mcontext, AppConstants.font_Roboto_Reg));
        overTV.setTypeface(AppUtilities.applyTypeFace(mcontext, AppConstants.font_Roboto_Reg));
        genreTV.setTypeface(AppUtilities.applyTypeFace(mcontext, AppConstants.font_Roboto_Reg));

        yearlb.setTypeface(AppUtilities.applyTypeFace(mcontext, AppConstants.font_Roboto_Bold));
        overlb.setTypeface(AppUtilities.applyTypeFace(mcontext, AppConstants.font_Roboto_Bold));
        titlelb.setTypeface(AppUtilities.applyTypeFace(mcontext, AppConstants.font_Roboto_Bold));
        genrelb.setTypeface(AppUtilities.applyTypeFace(mcontext, AppConstants.font_Roboto_Bold));
        related_movies_TV.setTypeface(AppUtilities.applyTypeFace(mcontext, AppConstants.font_Roboto_Bold));


        if (AppUtilities.isNetworkAvailable(mcontext)) {
            Presenter presenter = new Presenter(this);
            presenter.getData(AppConstants.API_GETDETAIL
                    .replace("@movie_id", Integer.toString(singleKey))
                    .replace("@api_key", mcontext.getResources().getString(R.string.api_key)));
            showProgress("Loading, please wait...");
        } else {
            AppUtilities.showAlertDialog(mcontext, "No internet!", "Please check your connection.");
        }
    }

    @Override
    public void displayDialogError(Throwable throwable) {
        stopProgress();
        AppUtilities.showAlertDialog(mcontext, "Error!", throwable.getMessage());
    }

    @Override
    public void sendResult(Object jsonObject) {
        new LoadMovies().execute(jsonObject);
    }

    public class LoadMovies extends AsyncTask<Object, Void, MovieResponseSingleModel> {

        private ArrayList<MovieResponseModel> ms;
        private String genre;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ms = new ArrayList<>();
        }

        @Override
        protected MovieResponseSingleModel doInBackground(Object... objects) {
            try {
                JSONObject jsonObject = new JSONObject(objects[0].toString());
                if (jsonObject != null && jsonObject.length() > 0) {
                    MovieResponseSingleModel movieResponseModel = new MovieResponseSingleModel(jsonObject);
                    if (movieResponseModel != null && movieResponseModel.genres != null) {
                        genre = AppUtilities.getGenre(movieResponseModel.genres);
                        ms = AppUtilities.movieResponseModels(movieResponseModels, movieResponseModel);
                    }
                    return movieResponseModel;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(MovieResponseSingleModel movieResponseMod) {
            super.onPostExecute(movieResponseMod);
            stopProgress();
            if (movieResponseMod != null) {
                titleTV.setText(movieResponseMod.title);
                yearTV.setText(AppUtilities.getDate(movieResponseMod.release_date));
                overTV.setText(movieResponseMod.overview);
                genreTV.setText(genre);
                String imageURL = "https://image.tmdb.org/t/p/w500/" + movieResponseMod.poster_path;
                Glide.with(mcontext).load(imageURL).into(detail_img);

                MovieRecListAdapter movieRecListAdapter = new MovieRecListAdapter(mcontext, ms);
                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false);
                related_recyclerview.setLayoutManager(linearLayoutManager);
                related_recyclerview.setAdapter(movieRecListAdapter);
                related_recyclerview.scrollToPosition(0);
            } else {
                AppUtilities.showAlertDialog(mcontext, "Error", "Sorry, coudn't load at this time.");
            }
        }
    }

    public void initProgressbar() {
        mProgressDialog = new ProgressDialog(mcontext);
        //  mProgressDialog.setMessage("Loading, please wait...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setCanceledOnTouchOutside(true);
    }

    public void showProgress(String msg) {
        try {
            if (mProgressDialog != null && !mProgressDialog.isShowing() && MainActivity.AppInForeground == true) {
                mProgressDialog.setMessage(msg);
                mProgressDialog.show();
            }
        } catch (Exception e) {

        }
    }

    public void stopProgress() {

        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        } catch (Exception e) {

        }
    }
}
