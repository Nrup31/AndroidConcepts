package com.androidnewconcepts.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.androidnewconcepts.R;

/**
 * Created by indianic on 08/01/16.
 */
public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupToolbar();
    }

    private void setupToolbar() {

        toolbar = (Toolbar) findViewById(R.id.activity_menubar_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        mTitle = (TextView) toolbar.findViewById(R.id.actionbar_tvTitle);
        mTitle.setVisibility(View.VISIBLE);

    }
}
