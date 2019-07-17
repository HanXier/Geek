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
import com.example.geekdemo.bean.DailyBeforeBean;
import com.example.geekdemo.bean.DailyListBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class DailyAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {


    private static final int BANNER = 0;
    private static final int DATE = 1;
    private static final int ITEM = 2;
    private final LayoutInflater inflater;
    List <DailyListBean.TopStoriesBean> topStoriesBeanList = new ArrayList <>();
    List <DailyListBean.StoriesBean> storiesBeanList = new ArrayList <>();
    List <DailyBeforeBean.StoriesBean> beforeStories = new ArrayList <>();
    boolean isBefore;
    String title = "今日新闻";
    Context context;
    private OnItemClick onItemClick;

    public DailyAdapter(Context context) {

        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    /**
     * 设置是否过往标识
     *
     * @param isBefore
     */
    public void setIsBefore(boolean isBefore, String title) {
        this.isBefore = isBefore;
        this.title = title;
    }

    public void initBanner(List <DailyListBean.TopStoriesBean> topStoriesBeanList) {
        if (topStoriesBeanList != null) {
            this.topStoriesBeanList.addAll(topStoriesBeanList);

        }
    }

    public void initDailyList(List <DailyListBean.StoriesBean> storiesBeanList) {
        if (storiesBeanList != null) {
            this.storiesBeanList.addAll(storiesBeanList);
            notifyDataSetChanged();
        }
    }

    public void refreshDate(String title) {

        if (title != null) {
            this.title = title;

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        switch (type) {
            case BANNER:
                return new BannerViewHolder(inflater.inflate(R.layout.item_top, viewGroup, false));
            case DATE:
                return new DateViewHolder(inflater.inflate(R.layout.item_date, viewGroup, false));
            case ITEM:
                return new ItemViewHolder(inflater.inflate(R.layout.item_daily, viewGroup, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int postion) {

        if (!isBefore) {
            if (viewHolder instanceof BannerViewHolder) {

                ((BannerViewHolder) viewHolder).banner.setImages(topStoriesBeanList);
                ((BannerViewHolder) viewHolder).banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {

                        DailyListBean.TopStoriesBean topStoriesBean = (DailyListBean.TopStoriesBean) path;
                        Glide.with(context).load(topStoriesBean.getImage()).into(imageView);
                    }
                }).start();

            } else if (viewHolder instanceof DateViewHolder) {

                ((DateViewHolder) viewHolder).date.setText(title);
            } else {
//            viewHolder.img
                if (title != null) {
                    postion = postion - 1;
                }
                if (topStoriesBeanList.size() > 0) {
                    postion = postion - 1;
                }

                if (storiesBeanList.size() <= 0) {
                    return;
                }
                final DailyListBean.StoriesBean storiesBean = storiesBeanList.get(postion);
                if (storiesBean.getImages() != null && storiesBean.getImages().size() > 0) {

                    Glide.with(context).load(storiesBean.getImages().get(0)).into(((ItemViewHolder) viewHolder).img);
                }
                ((ItemViewHolder) viewHolder).title.setText(storiesBean.getTitle());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClick != null) {
                            onItemClick.itemClick(storiesBean.getId());
                        }
                    }
                });
            }
        } else {
            if (viewHolder instanceof DateViewHolder) {

                ((DateViewHolder) viewHolder).date.setText(title);
            } else {
//            viewHolder.img
                if (title != null) {
                    postion = postion - 1;
                }

                if (beforeStories.size() <= 0) {
                    return;
                }
                DailyBeforeBean.StoriesBean storiesBean = beforeStories.get(postion);
                if (storiesBean.getImages() != null && storiesBean.getImages().size() > 0) {

                    Glide.with(context).load(storiesBean.getImages().get(0)).into(((ItemViewHolder) viewHolder).img);
                }
                ((ItemViewHolder) viewHolder).title.setText(storiesBean.getTitle());

            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (!isBefore) {

            if (position == 0 && topStoriesBeanList.size() > 0) {
                return BANNER;
            } else if (position == 1 && title != null) {
                return DATE;
            } else {
                return ITEM;
            }
        } else {

            if (position == 0 && title != null) {
                return DATE;
            } else {
                return ITEM;
            }
        }
    }

    @Override
    public int getItemCount() {
        int size = 0;

        if (!isBefore) {

            if (storiesBeanList.size() > 0) {
                size = storiesBeanList.size();
            }
            if (topStoriesBeanList.size() > 0) {
                size += 1;
            }
            if (title != null) {
                size += 1;
            }
        } else {
            if (beforeStories.size() > 0) {
                size = beforeStories.size();
            }
            if (title != null) {
                size += 1;
            }
        }
        return size;
    }

    public void initBeforeData(List <DailyBeforeBean.StoriesBean> stories) {
        this.beforeStories.clear();
        this.beforeStories.addAll(stories);

        notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void itemClick(int newsId);
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {


        Banner banner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);

            banner = itemView.findViewById(R.id.item_top_banner);

        }
    }

    class DateViewHolder extends RecyclerView.ViewHolder {


        TextView date;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tv_daily_date);
//            ButterKnife.bind(this, itemView);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        //        @BindView(R.id.iv_daily_item_image)
        ImageView img;

        //        @BindView(R.id.tv_daily_item_title)
        TextView title;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_daily_item_image);
            title = itemView.findViewById(R.id.tv_daily_item_title);
        }
    }
}
