package com.gitlist.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.gitlist.App;
import com.gitlist.R;
import com.gitlist.model.testmodel.Results;
import com.squareup.picasso.Picasso;

public class AdapterMainActivity extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Results[] repos;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new BaseItemViewHolder(inflater.inflate(R.layout.row_main_adapter_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            Results res = repos[position];
            BaseItemViewHolder holder = (BaseItemViewHolder) viewHolder;
            holder.repoTitle.setText(res.getTitle());
            holder.repoDescription.setText(res.getId());
            holder.repoUrl.setText(res.getPopularity());

        Picasso.with(App.getApp().getApplicationContext())
                .load("https://image.tmdb.org/" + "t/p/w500" + res.getPoster_path())
                .resize(40, 40)
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return repos == null ? 0 : repos.length;
    }


    public void updateList(Results[] repos) {
        this.repos = repos;
        notifyDataSetChanged();
    }



    public class BaseItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView repoTitle;
        TextView repoDescription;
        TextView repoUrl;

        public BaseItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            repoTitle = itemView.findViewById(R.id.tv_repo_title);
            repoDescription = itemView.findViewById(R.id.tv_repo_description);
            repoUrl = itemView.findViewById(R.id.tv_repo_link);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
