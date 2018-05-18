package com.gitlist.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gitlist.R;
import com.gitlist.model.RepoItem;

import java.util.List;

public class AdapterMainActivity extends RecyclerView.Adapter<AdapterMainActivity.ViewHolder> {

    private List<RepoItem> repos;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RepoItem repoItem = repos.get(position);
        if(repoItem != null){
            holder.repoTitle.setText(repoItem.getFull_name());
            holder.repoDescription.setText(repoItem.getDescription());
            holder.repoUrl.setText(repoItem.getHtml_url());
        }
    }

    @Override
    public int getItemCount() {
        return repos == null ? 0 : repos.size();
    }

    public void updateList(List<RepoItem> repos){
        this.repos = repos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView repoTitle;
        TextView repoDescription;
        TextView repoUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            repoTitle = itemView.findViewById(R.id.tv_repo_title);
            repoDescription = itemView.findViewById(R.id.tv_repo_description);
            repoUrl = itemView.findViewById(R.id.tv_repo_link);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
