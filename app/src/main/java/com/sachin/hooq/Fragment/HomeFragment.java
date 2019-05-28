package com.sachin.hooq.Fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sachin.hooq.Activity.MainActivity;
import com.sachin.hooq.Adapter.MovieRecListAdapter;
import com.sachin.hooq.Controller.MainApplication;
import com.sachin.hooq.Model.MovieResponseModel;
import com.sachin.hooq.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment {
    private Context mcontext;
    private RecyclerView hq_recyclerview;
    private TextView hq_lbl_tv;
    private ArrayList<MovieResponseModel> movieResponseModels;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }

    public static HomeFragment newInstance(Bundle data) {
        HomeFragment f = new HomeFragment();
        f.setArguments(data);
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        movieResponseModels = getArguments().getParcelableArrayList("data");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialization
        hq_lbl_tv = (TextView) view.findViewById(R.id.hq_lbl_tv);
        hq_recyclerview = (RecyclerView) view.findViewById(R.id.hq_recyclerview);

        final MovieRecListAdapter movieRecListAdapter = new MovieRecListAdapter(mcontext, movieResponseModels);
        final GridLayoutManager linearLayoutManager = new GridLayoutManager(mcontext, 2);
        hq_recyclerview.setLayoutManager(linearLayoutManager);

        linearLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return movieRecListAdapter.isPositionFooter(position) ? linearLayoutManager.getSpanCount() : 1;
            }
        });
        movieRecListAdapter.setWithFooter(true);
        movieRecListAdapter.setWithHeader(true);
        hq_recyclerview.setAdapter(movieRecListAdapter);
    }
}
