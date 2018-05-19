package com.gitlist.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gitlist.R;
import com.gitlist.model.RepoItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterMainActivity extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_BASE = 101;
    private static final int ITEM_PROGRESS = 102;

    // TODO remove from prod
    Map<String, Integer> map = new HashMap<>();

    private List<RepoItem> repos;
    private boolean isLoadingAdded;

    // TODO remove method from prod
    public void showDuplicates(){
        map.clear();
        Log.e("myLogs", "____________DUPLICATES_______________" );
        for (RepoItem repoItem: repos) {
            if (map.get(repoItem.getFull_name()) == null) {
                map.put(repoItem.getFull_name(), 1);
            } else {
                Log.e("myLogs", "title " + repoItem.getFull_name());
                map.put(repoItem.getFull_name(), map.get(repoItem.getFull_name()) + 1);
            }
        }
        Log.e("myLogs", "_____________________________________" );

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM_PROGRESS:
                return new ProgressViewHolder(inflater.inflate(R.layout.row_main_adapter_progress, parent, false));
            default:
                return new BaseItemViewHolder(inflater.inflate(R.layout.row_main_adapter_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == ITEM_BASE) {
            RepoItem repoItem = repos.get(position);

//            if (map.get(repoItem.getFull_name()) == null) {
//                map.put(repoItem.getFull_name(), 1);
//            } else {
//                Log.e("myLogs", "title " + repoItem.getFull_name());
//                map.put(repoItem.getFull_name(), map.get(repoItem.getFull_name()) + 1);
//            }

            BaseItemViewHolder holder = (BaseItemViewHolder) viewHolder;
            holder.repoTitle.setText(repoItem.getFull_name());
            holder.repoDescription.setText(repoItem.getDescription());
            holder.repoUrl.setText(repoItem.getHtml_url());
        }
    }

    @Override
    public int getItemCount() {
        return repos == null ? 0 : repos.size();
    }

    @Override
    public int getItemViewType(int position) {
        //return (position == repos.size()) ? ITEM_PROGRESS : ITEM_BASE;

        return (position == repos.size() - 1 && isLoadingAdded) ? ITEM_PROGRESS : ITEM_BASE;
    }

    public void updateList(List<RepoItem> repos) {
        Log.e("myLogs", "updateList " + repos.size());
        this.repos = repos;
        notifyDataSetChanged();
        showDuplicates();
    }


    public void add(RepoItem repo) {
        repos.add(repo);
        notifyItemInserted(repos.size() - 1);
    }

    public void addToList(List<RepoItem> repos) {
        for (RepoItem repo : repos) {
            add(repo);
        }
    }

    public void addProgressItem() {
        //removeProgressItem();
        if(!isLoadingAdded){
            isLoadingAdded = true;
            add(new RepoItem());
        }
    }

    public void removeProgressItem() {
        isLoadingAdded = false;

        int position = repos.size() - 1;
        RepoItem item = repos.get(position);

        if (item != null) {
            repos.remove(position);
            notifyItemRemoved(position);
        }
    }

    public class BaseItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView repoTitle;
        TextView repoDescription;
        TextView repoUrl;

        public BaseItemViewHolder(View itemView) {
            super(itemView);
            repoTitle = itemView.findViewById(R.id.tv_repo_title);
            repoDescription = itemView.findViewById(R.id.tv_repo_description);
            repoUrl = itemView.findViewById(R.id.tv_repo_link);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class ProgressViewHolder extends RecyclerView.ViewHolder {

        public ProgressViewHolder(View itemView) {
            super(itemView);
        }

    }

}
