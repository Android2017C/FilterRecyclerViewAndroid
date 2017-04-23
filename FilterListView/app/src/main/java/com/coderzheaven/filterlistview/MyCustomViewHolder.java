package com.coderzheaven.filterlistview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCustomViewHolder extends RecyclerView.ViewHolder {

    protected ImageView imageView;
    protected TextView tvName, tvPlace;
    protected ImageView imgThumb;

    public MyCustomViewHolder(View view) {
        super(view);
        this.tvName = (TextView) view.findViewById(R.id.tvName);
        this.tvPlace = (TextView) view.findViewById(R.id.tvPlace);
        this.imgThumb = (ImageView) view.findViewById(R.id.imgThumb);
    }

}