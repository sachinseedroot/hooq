package com.sachin.hooq.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sachin.hooq.R;
import com.sachin.hooq.Utilities.AppUtilities;

public class DetailFragment extends Fragment {
    private Context mcontext;
    private String singleKey;
    private TextView titleTV,yearTV,overTV;
    private TextView titlelb,yearlb,overlb;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
    }

    public static DetailFragment newInstance(String key) {
        DetailFragment f = new DetailFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("key", key);
        f.setArguments(args);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        singleKey = getArguments().getString("key", "");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (TextUtils.isEmpty(singleKey)) {
            AppUtilities.showAlertDialog(mcontext, "No record found!", "Please go back and try another movie.");
            return;
        }

        //Initialization
        titleTV = (TextView) view.findViewById(R.id.titleTV);
        yearTV = (TextView) view.findViewById(R.id.yearTV);
        overTV = (TextView) view.findViewById(R.id.overTV);




    }
}
