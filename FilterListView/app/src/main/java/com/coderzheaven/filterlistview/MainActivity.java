package com.coderzheaven.filterlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = "MainActivity";
    private ArrayList<ListItem> allList;

    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchView = (SearchView) findViewById(R.id.search_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        setList();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyRecyclerAdapter(this, allList);
        mRecyclerView.setAdapter(adapter);

        setupSearchView();
    }

    public void setList() {

        allList = new ArrayList<ListItem>();

        ListItem item = new ListItem();
        item.setData("Google", "USA", R.drawable.google);
        allList.add(item);

        item = new ListItem();
        item.setData("Apple", "USA", R.drawable.apple);
        allList.add(item);

        item = new ListItem();
        item.setData("Samsung", "Korea", R.drawable.samsung);
        allList.add(item);

        item = new ListItem();
        item.setData("Sony", "Japan", R.drawable.sony);
        allList.add(item);

        item = new ListItem();
        item.setData("HTC", "Taiwan", R.drawable.htc);
        allList.add(item);

        for (int i = 0; i < 10000; i++) {
            item = new ListItem();
            item.setData("Google " + i, "USA " + i, R.drawable.google);
            allList.add(item);
        }

    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
    }

    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }

}
