package com.sachin.hooq.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sachin.hooq.R;

public class HomeFragment extends Fragment {
    private Context mcontext;
    private RecyclerView hq_recyclerview;
    private TextView hq_lbl_tv;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialization
        hq_lbl_tv = (TextView) view.findViewById(R.id.hq_lbl_tv);
        hq_recyclerview = (RecyclerView) view.findViewById(R.id.hq_recyclerview);

    }
}
