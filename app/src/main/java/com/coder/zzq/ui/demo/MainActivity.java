package com.coder.zzq.ui.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.coder.zzq.ui.recyclerview.EasyRecyclerView;
import com.coder.zzq.ui.viewpager.EasyViewPager;

public class MainActivity extends AppCompatActivity {
    private EasyViewPager mViewPager;
    private EasyRecyclerView mEasyRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEasyRecyclerView = findViewById(R.id.easy_recycler_view);
        mEasyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PlainAdapter adapter = new PlainAdapter();
        adapter.setBodyData(new String[]{
                "123",
                "456",
                "789",
                "123",
                "456",
                "789",
                "123",
                "456",
                "789",
                "123",
                "456",
                "789",
                "123",
                "456",
                "789"
        });
        adapter.setHeaderData("header");
        adapter.setFooterData("footer");
        mEasyRecyclerView.setAdapter(adapter);
    }
}
