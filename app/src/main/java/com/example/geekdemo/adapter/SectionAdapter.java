package com.example.geekdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geekdemo.R;
import com.example.geekdemo.bean.SectionListBean;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter <SectionAdapter.SectionViewHolder> {

    List <SectionListBean.DataBean> dataBeanList = new ArrayList <>();
    Context context;

    public SectionAdapter(Context context) {
        this.context = context;
    }

    public void initData(List <SectionListBean.DataBean> dataBeanList) {

        if (dataBeanList != null) {

            this.dataBeanList.addAll(dataBeanList);
            notifyDataSetChanged();
        }


    }

    @NonNull
    @Override
    public SectionAdapter.SectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View root = LayoutInflater.from(context).inflate(R.layout.item_section, viewGroup, false);
        return new SectionViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionAdapter.SectionViewHolder sectionViewHolder, int i) {

//        sectionViewHolder.sectionBg
        SectionListBean.DataBean dataBean = dataBeanList.get(i);
        Glide.with(context).load(dataBean.getThumbnail()).into(sectionViewHolder.sectionBg);
        sectionViewHolder.sectionKind.setText(dataBean.getName());
        sectionViewHolder.sectionDes.setText(dataBean.getDescription());

    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {

        ImageView sectionBg;
        TextView sectionKind, sectionDes;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionBg = itemView.findViewById(R.id.section_bg);
            sectionKind = itemView.findViewById(R.id.section_kind);
            sectionDes = itemView.findViewById(R.id.section_des);
        }
    }
}
