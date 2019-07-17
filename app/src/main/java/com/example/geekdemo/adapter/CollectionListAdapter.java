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
import com.example.geekdemo.bean.CollectionDbBean;

import java.util.ArrayList;
import java.util.List;

public class CollectionListAdapter extends RecyclerView.Adapter <CollectionListAdapter.ViewHolder> {

    List <CollectionDbBean> collectionDbBeanList = new ArrayList <>();
    Context context;
    ItemClickListener itemClickListener;

    public CollectionListAdapter(Context context) {

        this.context = context;
    }

    public void initData(List <CollectionDbBean> collectionDbBeanList) {

        this.collectionDbBeanList.addAll(collectionDbBeanList);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CollectionListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_collection, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final CollectionDbBean dbBean = collectionDbBeanList.get(i);

        Glide.with(context).load(dbBean.getImgUrl()).into(viewHolder.imageView);
        viewHolder.from.setText(getTypeName(dbBean.getFromType()));

        viewHolder.title.setText(dbBean.getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (itemClickListener != null) {

                    itemClickListener.onItemClick(dbBean.getFromType(), dbBean.getId());
                }
            }
        });
    }

    public String getTypeName(int type) {

        switch (type) {
            case 1:
                return "来自知乎";

            case 2:
                return "来自微信";

        }
        return "";
    }

    @Override
    public int getItemCount() {
        return collectionDbBeanList.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int type, String id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title, from;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_title);
            from = itemView.findViewById(R.id.item_from);
        }
    }
}
