package com.example.zohar.emptyrecycleradapterdemo;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SimpleAdapter extends BaseWithEmptyViewAdapter<String> {

    public SimpleAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemHolder(LayoutInflater mInflater, ViewGroup parent, int viewType) {
        return new ItemViewHolder(mInflater.inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public RecyclerView.ViewHolder onCreateEmptyHolder(LayoutInflater mInflater, ViewGroup parent, int viewType) {
        return new EmptyViewHolder(mInflater.inflate(R.layout.layout_empty_list, parent, false));
    }

    @Override
    public void onBindItemHolder(List<String> mDataList, RecyclerView.ViewHolder holder, int position) {
        String s = mDataList.get(position);
        ((ItemViewHolder) holder).mTextView.setText(s);
    }

    @Override
    public void onBindEmptyHolder(RecyclerView.ViewHolder holder) {

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        ItemViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_view);
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView ivEmpty;
        private AppCompatTextView tvEmpty;

        EmptyViewHolder(View itemView) {
            super(itemView);
            ivEmpty = itemView.findViewById(R.id.iv_empty);
            tvEmpty = itemView.findViewById(R.id.tv_empty);
        }
    }
}
