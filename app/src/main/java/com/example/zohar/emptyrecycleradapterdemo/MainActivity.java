package com.example.zohar.emptyrecycleradapterdemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SmartRefreshLayout refreshLayout;
    private RecyclerView rvData;

    private SimpleAdapter emptyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = findViewById(R.id.refresh_layout);
        rvData = findViewById(R.id.rv_data);

        initView();
    }

    private void initView() {
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                rvData.postDelayed(() -> {
                    List<String> dataList = new ArrayList<>();
                    for (int i = 0; i < 20; i++) {
                        dataList.add("Item - " + i);
                    }
                    emptyAdapter.addData(dataList);
                    refreshLayout.finishRefresh();
                    refreshLayout.finishLoadMore();
                }, 1500);
            }

            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                rvData.postDelayed(() -> {
                    //清空数据
                    emptyAdapter.showEmptyView();
                    refreshLayout.finishRefresh();
                    refreshLayout.finishLoadMore();
                }, 1500);
            }
        });
        rvData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        emptyAdapter = new SimpleAdapter(this);
        rvData.setAdapter(emptyAdapter);
        emptyAdapter.showEmptyView();//初始化为显示emptyView
    }
}
