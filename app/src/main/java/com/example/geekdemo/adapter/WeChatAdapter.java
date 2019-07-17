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
import com.example.geekdemo.bean.Newslist;

import java.util.ArrayList;
import java.util.List;

public class WeChatAdapter extends RecyclerView.Adapter <WeChatAdapter.WeChatViewHolder> {

    List <Newslist> dataBeanList = new ArrayList <>();
    Context context;

    public WeChatAdapter(Context context) {
        this.context = context;
    }

    public void initData(List <Newslist> dataBeanList) {

        if (dataBeanList != null) {

            this.dataBeanList.addAll(dataBeanList);
            notifyDataSetChanged();
        }


    }

    @NonNull
    @Override
    public WeChatAdapter.WeChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View root = LayoutInflater.from(context).inflate(R.layout.item_wechat, viewGroup, false);
        return new WeChatViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull WeChatAdapter.WeChatViewHolder sectionViewHolder, int i) {


        Newslist dataBean = dataBeanList.get(i);
        Glide.with(context).load(dataBean.getPicUrl()).into(sectionViewHolder.img);
        sectionViewHolder.title.setText(dataBean.getTitle());
        sectionViewHolder.from.setText(dataBean.getDescription());
        sectionViewHolder.time.setText(dataBean.getCtime());

    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    public class WeChatViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView title, from, time;

        public WeChatViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_wechat_item_image);
            title = itemView.findViewById(R.id.tv_wechat_item_title);
            from = itemView.findViewById(R.id.tv_wechat_item_from);
            time = itemView.findViewById(R.id.tv_wechat_item_time);
        }
    }
}
