package com.example.geekdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.example.geekdemo.R;
import com.example.geekdemo.utils.Constants;
import com.example.geekdemo.utils.GoldStatus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by codeest on 16/11/27.
 */

public class GoldManagerAdapter extends RecyclerView.Adapter <GoldManagerAdapter.ViewHolder> {


    private LayoutInflater inflater;

    public GoldManagerAdapter(Context mContext) {
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.gold_manager_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        GoldStatus goldStatus = Constants.goldStatuses.get(position);

        holder.tvType.setText(goldStatus.getTitle());
        holder.scSwitch.setChecked(goldStatus.isSelected());
        holder.scSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Constants.goldStatuses.get(holder.getAdapterPosition()).setSelected(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Constants.goldStatuses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvType;
        SwitchCompat scSwitch;

        public ViewHolder(View itemView) {
            super(itemView);

            tvType = itemView.findViewById(R.id.tv_gold_manager_type);
            scSwitch = itemView.findViewById(R.id.sc_gold_manager_switch);
        }
    }
}
