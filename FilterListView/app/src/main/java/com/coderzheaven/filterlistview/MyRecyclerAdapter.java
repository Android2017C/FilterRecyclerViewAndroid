package com.coderzheaven.filterlistview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyCustomViewHolder> {

    private List<ListItem> listItems, filterList;
    private Context mContext;

    public MyRecyclerAdapter(Context context, List<ListItem> listItems) {
        this.listItems = listItems;
        this.mContext = context;
        this.filterList = new ArrayList<ListItem>();
        // we copy the original list to the filter list and use it for setting row values
        this.filterList.addAll(this.listItems);
    }

    @Override
    public MyCustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, null);
        MyCustomViewHolder viewHolder = new MyCustomViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MyCustomViewHolder customViewHolder, int position) {

        ListItem listItem = filterList.get(position);
        customViewHolder.tvName.setText(listItem.name);
        customViewHolder.tvPlace.setText(listItem.place);
        customViewHolder.imgThumb.setBackgroundResource(listItem.logo);

    }

    @Override
    public int getItemCount() {
        return (null != filterList ? filterList.size() : 0);
    }

    public void filter(final String text) {

        // Searching could be complex..so we will dispatch it to a different thread...
        new Thread(new Runnable() {
            @Override
            public void run() {

                // Clear the filter list
                filterList.clear();

                // If there is no search value, then add all original list items to filter list
                if (TextUtils.isEmpty(text)) {

                    filterList.addAll(listItems);

                } else {
                    // Iterate in the original List and add it to filter list...
                    for (ListItem item : listItems) {
                        if (item.name.toLowerCase().contains(text.toLowerCase()) ||
                                item.place.toLowerCase().contains(text.toLowerCase())) {
                            // Adding Matched items
                            filterList.add(item);
                        }
                    }
                }

                // Set on UI Thread
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Notify the List that the DataSet has changed...
                        notifyDataSetChanged();
                    }
                });

            }
        }).start();

    }

}
