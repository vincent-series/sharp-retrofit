package com.coder.zzq.ui.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coder.zzq.ui.viewpager.EasyPageAdapter;
import com.coder.zzq.ui.viewpager.EasyViewPager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EasyViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setFixed(false);
        EasyPageAdapter pageAdapter = new EasyPageAdapter<String>() {
            @Override
            protected int providePageLayout() {
                return R.layout.text;
            }

            @Override
            protected void updateData(ViewGroup container, View pageView, int position, List<String> pageData) {
                TextView textView = (TextView) pageView;
                textView.setText(pageData.get(position));
            }
        };
        mViewPager.setAdapter(pageAdapter);
        pageAdapter.setData(new String[]{
                "123",
                "456",
                "789",
                "aaa",
                "bbb",
                "ccc",
                "123",
                "456",
                "789",
                "aaa",
                "bbb",
                "ccc"
        });
    }
}
