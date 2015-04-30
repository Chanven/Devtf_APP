package com.devtf_l.app.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.devtf_l.app.R;

/**
 * Created by kyler on 10/25/14. 
 */
public class About extends Activity {

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_about_layout);
    }
}
