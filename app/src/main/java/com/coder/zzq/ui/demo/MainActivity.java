package com.coder.zzq.ui.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.coder.zzq.ui.recyclerview.EasyRefreshRecyclerView;
import com.coder.zzq.ui.viewpager.EasyViewPager;

public class MainActivity extends AppCompatActivity implements OnRefreshListener, OnLoadMoreListener {
    private EasyViewPager mViewPager;
    private EasyRefreshRecyclerView mRefreshRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRefreshRecyclerView = findViewById(R.id.refresh_view);
        mRefreshRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
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
        mRefreshRecyclerView.getRecyclerView().setAdapter(adapter);
        mRefreshRecyclerView.getRefresher().setOnRefreshListener(this);
        mRefreshRecyclerView.getRecyclerView()
                .addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                        }
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });
        mRefreshRecyclerView.getRefresher().setOnLoadMoreListener(this);
        mRefreshRecyclerView.getRefresher().setRefreshing(true);

    }

    @Override
    public void onRefresh() {
        mRefreshRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshRecyclerView.getRefresher().setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mRefreshRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshRecyclerView.getRefresher().setLoadingMore(false);
            }
        }, 2000);
    }
}
