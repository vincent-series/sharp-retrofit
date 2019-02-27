package com.coder.zzq.ui.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.coder.zzq.ui.recyclerview.BaseRecyclerViewAdapter;
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
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("闵傲")
                .create();

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                SmartToast.showInCenter("cancel");
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                SmartToast.showInCenter("dismiss");
            }
        });
        dialog.show();
        dialog.getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
//                dialog.dismiss();
            }
        }, 2000);
        adapter.setOnBodyItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<String>() {
            @Override
            public void onBodyItemClick(View itemView, int globalPos, int bodyPos, String s) {

            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
