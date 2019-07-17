package com.example.geekdemo.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geekdemo.R;
import com.example.geekdemo.bean.HotBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotAdapter extends RecyclerView.Adapter <HotAdapter.HotViewHolder> {

    private Context context;
    private ArrayList <HotBean.RecentBean> list;
    private OnItemClickListener mListener;

    public HotAdapter(Context context, ArrayList <HotBean.RecentBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HotAdapter.HotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_news, null);
        return new HotViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HotAdapter.HotViewHolder holder, int position) {

        final HotBean.RecentBean recentBean = list.get(position);
        holder.mTvTitle.setText(recentBean.getTitle());
        Glide.with(context).load(recentBean.getThumbnail()).into(holder.mIv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(view, recentBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, HotBean.RecentBean pos);
    }

    class HotViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.iv)
        ImageView mIv;

        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
