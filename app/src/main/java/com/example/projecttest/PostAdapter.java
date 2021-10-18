package com.example.projecttest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ListViewHolder> {
    private final ArrayList<PostModel> postModels = new ArrayList<>();
    private Context mContext;

    @SuppressLint("NotifyDataSetChanged")
    void setData(List<PostModel> list, Context mContext) {
        this.mContext = mContext;
        postModels.clear();
        postModels.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup, false);
        return new PostAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostAdapter.ListViewHolder holder, final int position) {
        holder.bind(postModels.get(position), position);
    }

    @Override
    public int getItemCount() {
        return postModels.size();
    }

    @SuppressLint("NonConstantResourceId")
    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvTitle;

        ListViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        @SuppressLint("SetTextI18n")
        void bind(final PostModel postModel, final int position) {
            tvId.setText(postModel.getId());
            tvTitle.setText(postModel.getTitle());
        }
    }
}