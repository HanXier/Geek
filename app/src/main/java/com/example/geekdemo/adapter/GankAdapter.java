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
import com.example.geekdemo.bean.GankBean;
import com.example.geekdemo.bean.GankBean.ResultsBean;

import java.util.ArrayList;
import java.util.List;

public class GankAdapter extends RecyclerView.Adapter <GankAdapter.GankViewHolder> {

    Context context;
    private List <GankBean.ResultsBean> dataBeanList = new ArrayList <>();

    public GankAdapter(Context context) {
        this.context = context;
    }

    public void initData(List <GankBean.ResultsBean> dataBeanList) {

        if (dataBeanList != null) {

            this.dataBeanList.addAll(dataBeanList);
            notifyDataSetChanged();
        }


    }

    @NonNull
    @Override
    public GankAdapter.GankViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View root = LayoutInflater.from(context).inflate(R.layout.item_tech, viewGroup, false);
        return new GankViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull GankAdapter.GankViewHolder sectionViewHolder, int i) {


        GankBean.ResultsBean dataBean = dataBeanList.get(i);
        if (dataBean.getImages() != null && dataBean.getImages().size() > 0) {
            Glide.with(context).load(dataBean.getImages().get(0)).into(sectionViewHolder.img);

        }
        sectionViewHolder.title.setText(dataBean.getDesc());
        sectionViewHolder.author.setText(dataBean.getWho());
        sectionViewHolder.time.setText(dataBean.getCreatedAt());

    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    public class GankViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView title, author, time;

        public GankViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_tech_icon);
            title = itemView.findViewById(R.id.tv_tech_title);
            author = itemView.findViewById(R.id.tv_tech_author);
            time = itemView.findViewById(R.id.tv_tech_time);
        }
    }
}
