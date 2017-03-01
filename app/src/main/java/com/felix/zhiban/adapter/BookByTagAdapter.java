package com.felix.zhiban.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.felix.zhiban.R;
import com.felix.zhiban.ZhiBanApp;
import com.felix.zhiban.bean.book.Books;
import com.felix.zhiban.tool.ImageUtils.ImageLoaderFactory;
import com.felix.zhiban.tool.Utils;
import com.felix.zhiban.viewimpl.book.BookDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class BookByTagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    private int status=1;

    public static final int LOAD_MORE=0;

    public static final int LOAD_PULL_TO=1;

    public static final int LOAD_NONE=2;

    public static final int LOAD_END=3;

    private static final int TYPE_TOP=-1;

    private static final int TYPE_FOOTER=-2;

    private List<Books>list;

    public BookByTagAdapter(Context context){
        this.context=context;
        list=new ArrayList<>();
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_FOOTER){
            View view=View.inflate(parent.getContext(), R.layout.activity_view_footer,null);
            return new FooterViewHolder(view);
        }else{
            View rootView=View.inflate(parent.getContext(),R.layout.item_book_bytag,null);
            return new BookByTagViewHolder(rootView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FooterViewHolder){
            FooterViewHolder footerViewHolder=(FooterViewHolder)holder;
            footerViewHolder.bindItem();
        }else if(holder instanceof BookByTagViewHolder){
            BookByTagViewHolder bookByTagViewHolder=(BookByTagViewHolder)holder;
            bookByTagViewHolder.bindItem(list.get(position),position);
        }

    }

    @Override
    public int getItemCount() {
        return list.size()+1;
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
                    progress.setVisibility(View.GONE);
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

    class BookByTagViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llBook;

        private ImageView ivFilm;

        private TextView tvFilmName;

        private TextView tvFilmGrade;

        public BookByTagViewHolder(View itemView) {
            super(itemView);
            llBook=(LinearLayout)itemView.findViewById(R.id.ll_book);
            ivFilm=(ImageView)itemView.findViewById(R.id.iv_film);
            tvFilmName=(TextView)itemView.findViewById(R.id.tv_film_name);
            tvFilmGrade=(TextView)itemView.findViewById(R.id.tv_film_grade);
        }

        private void bindItem(final Books books, int position){
            ViewGroup.LayoutParams params=ivFilm.getLayoutParams();
            int width= Utils.getScreenWIdthDp(context);
            int ivwidth=(width-Utils.dp2px(context,80))/3;
            params.width=ivwidth;
            double height=(420.0/300.0)*ivwidth;
            params.height=(int)height;
            ivFilm.setLayoutParams(params);
            if(!TextUtils.isEmpty(books.getImages().getLarge())){
                ImageLoaderFactory.getImageLoader().displayForImageView(context, books.getImages().getLarge(),ivFilm);
            }
            if(!TextUtils.isEmpty(books.getRating().getAverage())){
                tvFilmGrade.setText("评分:"+ books.getRating().getAverage());
            }else{
                tvFilmGrade.setText("暂无评分");
            }
            if(!TextUtils.isEmpty(books.getTitle())){
                tvFilmName.setText(books.getTitle());
            }
            llBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, BookDetailActivity.class);
                    intent.putExtra("id",books.getId());
                    ActivityOptionsCompat options =
                            ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,
                                    ivFilm,ZhiBanApp.mContext.getResources().getString(R.string.transition_book_img));//与xml文件对应
                    ActivityCompat.startActivity(context, intent, options.toBundle());
                }
            });
        }
    }
    public List<Books> getList(){
        return list;
    }

    public void setList(List<Books>list){

       this.list=list;
    }

}
