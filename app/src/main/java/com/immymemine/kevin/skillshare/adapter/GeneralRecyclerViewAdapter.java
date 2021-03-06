package com.immymemine.kevin.skillshare.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.immymemine.kevin.skillshare.R;
import com.immymemine.kevin.skillshare.activity.ClassActivity;
import com.immymemine.kevin.skillshare.model.home.Class;
import com.immymemine.kevin.skillshare.utility.ConstantUtil;
import com.immymemine.kevin.skillshare.utility.TimeUtil;

import java.util.List;

/**
 *
 *  Created by quf93 on 2017-11-17.
 */

public class GeneralRecyclerViewAdapter extends RecyclerView.Adapter<GeneralRecyclerViewAdapter.GeneralViewHolder>{
    Context context;
    List<Class> classes;
    public GeneralRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public GeneralRecyclerViewAdapter(Context context, List<Class> classes) {
        this.context = context;
        this.classes = classes;
    }

    @Override
    public GeneralViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item_general, parent, false);
        return new GeneralViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GeneralViewHolder holder, int position) {
        if(classes != null) {
            Class mClass = classes.get(position);

            holder.id = mClass.get_id();
            holder.textViewTitle.setText(mClass.getTitle());
            holder.textViewTutor.setText(mClass.getTutorName());
            holder.textViewTime.setText(TimeUtil.calculateVideoTime(mClass.getDuration()));
            holder.url = mClass.getImageUrl();
            Glide.with(context).load(Uri.parse(holder.url))
                    .apply(RequestOptions.centerCropTransform())
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if(classes != null)
            return classes.size();
        return 5;
    }

    class GeneralViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTime, textViewTitle, textViewTutor;

        String id;
        String url;
        public GeneralViewHolder(View v) {
            super(v);

            imageView = v.findViewById(R.id.image_view_tutor);
            textViewTime = v.findViewById(R.id.text_view_time);
            textViewTitle = v.findViewById(R.id.text_view_title);
            textViewTutor = v.findViewById(R.id.text_view_profile);

            // item layout 자체에 onClick listener 를 달아준다. >>> item 어디를 클릭해도 이동
            v.setOnClickListener(view -> {
                Intent intent = new Intent(context, ClassActivity.class);
                intent.putExtra(ConstantUtil.ID_FLAG, id); // data for identification
                intent.putExtra(ConstantUtil.URL_FLAG, url);
                context.startActivity(intent);
            });
        }
    }
}

