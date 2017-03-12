package com.felix.zhiban.adapter;


import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.felix.zhiban.R;
import com.felix.zhiban.bean.zhihunews.RootEntity;
import com.felix.zhiban.bean.zhihunews.StroriesEntity;
import com.felix.zhiban.tool.ImageUtils.ImageLoaderFactory;
import com.felix.zhiban.tool.Utils;

import java.util.ArrayList;
import java.util.List;

public class ZhihuNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private int status=1;

    public static final int LOAD_MORE=0;

    public static final int LOAD_PULL_TO=1;

    public static final int LOAD_NONE=2;

    public static final int LOAD_END=3;

    private static final int TYPE_TOP=-1;

    private static final int TYPE_FOOTER=-2;

    private List<StroriesEntity>stroriesEntityList;

    private RootEntity rootEntity;

    public ZhihuNewsAdapter(Context context) {
           this.context=context;
        stroriesEntityList=new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_FOOTER){
            View view=View.inflate(parent.getContext(), R.layout.activity_view_footer,null);
            return new FooterViewHolder(view);
        }else{
            View rootView=View.inflate(parent.getContext(),R.layout.item_news,null);
            return new NewsItemViewHolder(rootView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  FooterViewHolder){
            FooterViewHolder footerViewHolder=(FooterViewHolder)holder;
            footerViewHolder.bindItem();
        }else if(holder instanceof NewsItemViewHolder){
            NewsItemViewHolder newsItemViewHolder=(NewsItemViewHolder)holder;
            newsItemViewHolder.bindItem(stroriesEntityList.get(position),position);
        }

    }

    @Override
    public int getItemViewType(int position) {
       if(position+1==getItemCount()){
           return TYPE_FOOTER;
       }else{
           return position;
       }
    }

    @Override
    public int getItemCount() {
        return stroriesEntityList.size()+1;
    }

    public void updateLoadStatus(int status){  //动态更新状态
        this.status=status;
        notifyDataSetChanged();
    }

    class FooterViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_load_prompt;

        private ProgressBar progress;
        public FooterViewHolder(View itemView) {
            super(itemView);
            tv_load_prompt=(TextView)itemView.findViewById(R.id.tv_load_prompt);
            progress=(ProgressBar)itemView.findViewById(R.id.progress);
            LinearLayoutCompat.LayoutParams params=new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dp2px(context,40));
            itemView.setLayoutParams(params);
        }

        private void bindItem(){
            switch (status){
                case LOAD_MORE:
                    progress.setVisibility(View.VISIBLE);
                    tv_load_prompt.setText("正在加载...");
                    itemView.setVisibility(View.VISIBLE);
                    break;
//                case LOAD_PULL_TO:
//                    progress.setVisibility(View.GONE);
//                    tv_load_prompt.setText("上拉加载更多");
//                    itemView.setVisibility(View.VISIBLE);
//                    break;
                case LOAD_NONE:
                    progress.setVisibility(View.GONE);
                    tv_load_prompt.setText("已无更多加载");
                    break;
                case LOAD_END:
                    itemView.setVisibility(View.GONE);
                default:
                    break;
            }
        }

    }

    class NewsItemViewHolder extends RecyclerView.ViewHolder{

        private TextView newstextView;

        private ImageView newsimageView;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            newstextView=(TextView)itemView.findViewById(R.id.news_title_tv);
            newsimageView=(ImageView)itemView.findViewById(R.id.news_iv);
        }

        private void bindItem(final StroriesEntity stroriesEntity,int position){
            if(null!=stroriesEntity.getImages()){
                ImageLoaderFactory.getImageLoader().displayForImageView(context, stroriesEntity.getImages().get(0),newsimageView);
            }
            if(null!=stroriesEntity.getTitle()){
                newstextView.setText(stroriesEntity.getTitle());

            }
            newstextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }

    public List<StroriesEntity> getList(){
        return stroriesEntityList;
    }

    public void setList(List<StroriesEntity>list){

        this.stroriesEntityList=list;
    }

}
