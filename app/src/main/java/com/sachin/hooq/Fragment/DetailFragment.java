package com.sachin.hooq.Fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sachin.hooq.Base.Presenter;
import com.sachin.hooq.Base.ResponseInterface;
import com.sachin.hooq.Model.MovieResponseModel;
import com.sachin.hooq.R;
import com.sachin.hooq.Utilities.AppConstants;
import com.sachin.hooq.Utilities.AppUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailFragment extends Fragment implements ResponseInterface.view {
    private Context mcontext;
    private int singleKey;
    private TextView titleTV, yearTV, overTV;
    private TextView titlelb, yearlb, overlb;
    private ImageView detail_img;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }

    public static DetailFragment newInstance(int key) {
        DetailFragment f = new DetailFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("key", key);
        f.setArguments(args);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        singleKey = getArguments().getInt("key", 0);
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
        titleTV = (TextView) view.findViewById(R.id.titleTV);
        yearTV = (TextView) view.findViewById(R.id.yearTV);
        overTV = (TextView) view.findViewById(R.id.overTV);
        detail_img = (ImageView) view.findViewById(R.id.detail_img);


        Presenter presenter = new Presenter(this);
        presenter.getData(AppConstants.API_GETDETAIL
                .replace("@movie_id", Integer.toString(singleKey))
                .replace("@api_key", mcontext.getResources().getString(R.string.api_key)));

    }

    @Override
    public void displayDialogError(Throwable throwable) {
        AppUtilities.showAlertDialog(mcontext, "Error!", throwable.getMessage());
    }

    @Override
    public void sendResult(Object jsonObject) {
        new LoadMovies().execute(jsonObject);
    }

    public class LoadMovies extends AsyncTask<Object, Void, MovieResponseModel> {

        @Override
        protected MovieResponseModel doInBackground(Object... objects) {
            try {
                JSONObject jsonObject = new JSONObject(objects[0].toString());
                if (jsonObject != null && jsonObject.length() > 0) {
                    MovieResponseModel movieResponseModel = new MovieResponseModel(jsonObject);
                    return movieResponseModel;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(MovieResponseModel movieResponseMod) {
            super.onPostExecute(movieResponseMod);
            if (movieResponseMod != null) {
                titleTV.setText(movieResponseMod.title);
                yearTV.setText(movieResponseMod.release_date);
                overTV.setText(movieResponseMod.overview);
                String imageURL = "https://image.tmdb.org/t/p/w500/" + movieResponseMod.poster_path;
                Glide.with(mcontext).load(imageURL).into(detail_img);
            } else {
                AppUtilities.showAlertDialog(mcontext, "Error", "Sorry, coudn't load at this time.");
            }
        }
    }
}
