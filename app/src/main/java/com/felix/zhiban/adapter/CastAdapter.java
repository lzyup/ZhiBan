package com.felix.zhiban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.felix.zhiban.R;
import com.felix.zhiban.bean.filmdetail.FilmPeople;
import com.felix.zhiban.tool.ImageUtils.ImageLoaderFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaGF on 2017/5/2.
 */

public class CastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    private List<FilmPeople>filmPeoples=new ArrayList<>();

    private OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        mItemClickListener=listener;
    }

    public CastAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_detail_person,parent,false);
        return new CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CastViewHolder castViewHolder=(CastViewHolder)holder;
        if(mItemClickListener!=null){
            castViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.OnItemClick(view,position);
                }
            });
        }
        castViewHolder.bindItem(filmPeoples.get(position));

    }

    @Override
    public int getItemCount() {
        return filmPeoples.size();
    }

    class CastViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewAvatar;

        private TextView textViewPersonName;

        private TextView textViewPersonType;
        public CastViewHolder(View itemView) {
            super(itemView);
            imageViewAvatar=(ImageView)itemView.findViewById(R.id.img_avatar);
            textViewPersonName=(TextView)itemView.findViewById(R.id.txt_film_personname);
            textViewPersonType=(TextView)itemView.findViewById(R.id.txt_film_persontype);


        }

        private void bindItem(final FilmPeople filmPeople){
            ImageLoaderFactory.getImageLoader().displayForImageView(context,filmPeople.getAvatars().getLarge(),imageViewAvatar);
            textViewPersonName.setText(filmPeople.getName());
            if(filmPeople.getType()==1){
                textViewPersonType.setText("导演");
            }else{
                textViewPersonType.setText("演员");
            }
        }
    }

    public void setDatas(List<FilmPeople>filmPeoples){
        this.filmPeoples=filmPeoples;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        public void OnItemClick(View view,int position);
    }
}
