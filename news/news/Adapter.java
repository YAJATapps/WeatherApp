package com.groupk.weatherapp.ui.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.groupk.weatherapp.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Article> articles;
    private Context context;
    private OnItemClickListener OnItemClickListener;
     LayoutInflater layoutInflater;

    public Adapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
      //  layoutInflater = LayoutInflater.from(context);
    }

    public void addArticles(List<Article> articles){
        this.articles.addAll(articles);
        int count = getItemCount();
        notifyItemRangeInserted(count,count + articles.size());
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_title,parent,false);
        return new MyViewHolder(view, OnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        Article model = articles.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.centerCrop();
        Glide.with(context)
                .load(model.getUrlToTmage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);

                holder.title.setText(model.getTitle());
                holder.description.setText(model.getDescription());
                holder.source.setText(model.getSource().getName());
               // holder.time.setText("\u2022" + Utils.DateToTimeFormat(model.getPublishedAt()));
                holder.published_at.setText(Utils.DateFormat(model.getPublishedAt()));
                holder.author.setText(model.getAuthor());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.OnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, description, author, published_at, source, time;
        ImageView imageView;
        ProgressBar progressBar;
        OnItemClickListener onItemClickListener;
        public MyViewHolder(View itemView, OnItemClickListener onItemClickListener1){
          super(itemView);

          itemView.setOnClickListener(this);
          title = itemView.findViewById(R.id.title);
          description = itemView.findViewById(R.id.description_s);
          author = itemView.findViewById(R.id.news_author);
          published_at = itemView.findViewById(R.id.news_date);
          source = itemView.findViewById(R.id.source);
        //  time = itemView.findViewById(R.id.time);
          imageView = itemView.findViewById(R.id.news_img);
          progressBar = itemView.findViewById(R.id.loading_bar);
          this.onItemClickListener = onItemClickListener1;

        }
        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v,getAdapterPosition());
        }
    }
}
