package com.felix.zhiban.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.felix.zhiban.R;
import com.felix.zhiban.ZhiBanApp;
import com.felix.zhiban.bean.filmusbox.Subject;
import com.felix.zhiban.bean.top250.Root;
import com.felix.zhiban.bean.top250.Subjects;
import com.felix.zhiban.tool.ImageUtils.ImageLoaderFactory;
import com.felix.zhiban.tool.Utils;
import com.felix.zhiban.viewimpl.film.FilmDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaGF on 2017/4/30.
 */

public class FilmTop250Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Root root;
    private int status = 1;
    public static final int LOAD_MORE = 0;
    public static final int LOAD_PULL_TO = 1;
    public static final int LOAD_NONE = 2;
    public static final int LOAD_END = 3;
    private static final int TYPE_TOP = -1;
    private static final int TYPE_FOOTER = -2;
    private List<Subjects>subjectsList;

    public FilmTop250Adapter(Context context){
        this.context=context;
        subjectsList=new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_FOOTER){
            View view=View.inflate(parent.getContext(), R.layout.activity_view_footer,null);
            return new FilmTop250Adapter.FooterViewHolder(view);
        }else{
            Log.v("xgffilm","电影0");
            View rootView=View.inflate(parent.getContext(),R.layout.item_film_top,null);
            return new FilmTop250Adapter.FilmTopViewHolder(rootView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FilmTop250Adapter.FooterViewHolder){
            FilmTop250Adapter.FooterViewHolder footerViewHolder=(FilmTop250Adapter.FooterViewHolder)holder;
            footerViewHolder.bindItem();
        }else if(holder instanceof FilmTop250Adapter.FilmTopViewHolder){
            Log.v("xgffilm","电影");
            FilmTop250Adapter.FilmTopViewHolder filmTopViewHolder=(FilmTop250Adapter.FilmTopViewHolder)holder;
            filmTopViewHolder.bindItem(subjectsList.get(position),position);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return position;
        }
    }

    @Override
    public int getItemCount() {
        return subjectsList.size()+1;
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
                case LOAD_PULL_TO:
                    progress.setVisibility(View.GONE);//这里改为使用默认动画,故将progress隐藏起来
                    tv_load_prompt.setText("上拉加载更多");
                    itemView.setVisibility(View.VISIBLE);
                    break;
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

    public void updateLoadStatus(int status){
        this.status=status;
        notifyDataSetChanged();
    }

    class FilmTopViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout llFilmTop;

        private ImageView ivFilm;

        private TextView tvFilm;

        private TextView tvFilmEnglish;

        private TextView tvFilmGrade;

        private TextView tvRank;

        public FilmTopViewHolder(View itemView) {
            super(itemView);
            llFilmTop=(LinearLayout)itemView.findViewById(R.id.ll_item_view);
            ivFilm=(ImageView)itemView.findViewById(R.id.iv_film);
            tvFilm=(TextView)itemView.findViewById(R.id.tv_film);
            tvFilmEnglish=(TextView)itemView.findViewById(R.id.tv_film_english);
            tvFilmGrade=(TextView)itemView.findViewById(R.id.tv_film_grade);
            tvRank=(TextView)itemView.findViewById(R.id.tv_rank);
        }

        private void bindItem(final Subjects subjects,int position){
            if(!TextUtils.isEmpty(subjects.getImages().getLarge())){
                ImageLoaderFactory.getImageLoader().displayForImageView(context, subjects.getImages().getLarge(),ivFilm);
            }
            if(!TextUtils.isEmpty(subjects.getTitle())){
                tvFilm.setText(subjects.getTitle());
            }
            if(!TextUtils.isEmpty(subjects.getOriginal_title())){
                tvFilmEnglish.setText(subjects.getOriginal_title());
            }
            if(!TextUtils.isEmpty(""+subjects.getRating().getAverage())){
                tvFilmGrade.setText("评分:"+ String.valueOf(subjects.getRating().getAverage()));
            }
            if(position<9){
                tvRank.setText("0"+(position+1));
            }else{
                tvRank.setText(""+(position+1));
            }

            llFilmTop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, FilmDetailActivity.class);
                    intent.putExtra("id",subjects.getId());
//                    ActivityOptionsCompat options =
//                            ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,
//                                    ivFilm, ZhiBanApp.mContext.getResources().getString(R.string.transition_film_img));//与xml文件对应
//                    ActivityCompat.startActivity(context, intent, options.toBundle());
                    context.startActivity(intent);
                }
            });
        }

    }

    public List<Subjects> getList(){
        return subjectsList;
    }

    public void setList(List<Subjects> list){
        this.subjectsList=list;

    }
}
