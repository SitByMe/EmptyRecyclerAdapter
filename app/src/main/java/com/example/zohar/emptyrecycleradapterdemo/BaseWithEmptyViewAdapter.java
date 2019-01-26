package com.example.zohar.emptyrecycleradapterdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseWithEmptyViewAdapter<B> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<B> mDataList;
    private LayoutInflater mInflater;

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐 1：显示1个布局；0：不显示任何布局
     */
    private int mEmptyType = 0;

    public BaseWithEmptyViewAdapter(Context context) {
        this.mDataList = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (EMPTY_VIEW != viewType) {
            //返回空布局的viewHolder
            return onCreateItemHolder(mInflater, parent, viewType);
        }
        return onCreateEmptyHolder(mInflater, parent, viewType);
    }

    public abstract RecyclerView.ViewHolder onCreateItemHolder(LayoutInflater mInflater, ViewGroup parent, int viewType);

    public abstract RecyclerView.ViewHolder onCreateEmptyHolder(LayoutInflater mInflater, ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (EMPTY_VIEW != itemViewType) {
            onBindItemHolder(mDataList, holder, position);
        } else {
            onBindEmptyHolder(holder);
        }
    }

    public abstract void onBindItemHolder(List<B> mDataList, RecyclerView.ViewHolder holder, int position);

    public abstract void onBindEmptyHolder(RecyclerView.ViewHolder holder);

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() + mEmptyType : mEmptyType;
    }

    @Override
    public int getItemViewType(int position) {
        if (mEmptyType == EMPTY_VIEW) {
            //空布局的类型
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    public B getItem(int position) {
        return mDataList.get(position);
    }

    /**
     * 显示添加的数据
     * 如果数据为空，则显示空布局
     */
    public void addData(List<B> data) {
        if (data != null && !data.isEmpty()) {
            if (mEmptyType == 1) {
                mEmptyType = 0;
                notifyItemRemoved(0);
            }
            mDataList.addAll(data);
            notifyDataSetChanged();
        } else if (mDataList.size() == 0) {
            if (mEmptyType != 1) {
                mEmptyType = 1;
                notifyItemInserted(0);
            }
        }
    }

    /**
     * 显示空布局
     */
    public void showEmptyView() {
        if (!mDataList.isEmpty()) {
            mDataList.clear();
            notifyDataSetChanged();
        }
        if (mEmptyType != EMPTY_VIEW) {
            //当前布局不是空布局，则刷新显示空布局
            mEmptyType = EMPTY_VIEW;
            notifyItemInserted(0);
        }
    }
}
