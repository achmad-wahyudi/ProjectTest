package com.example.projecttest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PostAdapter postAdapter;
    private RecyclerView rvListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListItem = findViewById(R.id.rv_list_item);

        postAdapter = new PostAdapter();

        showRecyclerList();
        loadDataApi();
    }

    private void showRecyclerList() {
        rvListItem.setLayoutManager(new LinearLayoutManager(this));
        rvListItem.setAdapter(postAdapter);
        rvListItem.setNestedScrollingEnabled(true);
        rvListItem.setHasFixedSize(true);
    }

    private void loadDataApi() {
        PostViewModel postViewModel = new ViewModelProvider(this).get("post", PostViewModel.class);
        postViewModel.getDataPosts().observe(this, getDataPosts);
        postViewModel.setDataPosts();
    }

    private final Observer<List<PostModel>> getDataPosts = daPostModelList -> {
        if (daPostModelList != null) {
            postAdapter.setData(daPostModelList, this);
        }
    };
}