package com.example.anew.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anew.R;
import com.example.anew.fragments.PopularMoviesFragment;
import com.example.anew.model.DataList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder> {

    private Context context;
    private PopularMoviesFragment mPopularMoviesFragment;
    private ArrayList<DataList> list;
    private int lastPosition=-1;

    public PopularMoviesAdapter(Context context, PopularMoviesFragment mPopularMoviesFragment, ArrayList<DataList> list) {
        this.context = context;
        this.mPopularMoviesFragment = mPopularMoviesFragment;
        this.list = list;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_popular_movies_adapter, parent, false);
        return new ViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        if (position > lastPosition) {
            lastPosition = position;
        }
        ViewHolder viewHolder = holder;

        holder.movieTitle.setText(list.get(position).getTitle());

        String commonPosterURL = "https://image.tmdb.org/t/p/w500";
        String imageURL = (commonPosterURL + list.get(position).getPoster_path());
        Glide.with(holder.itemView.getContext())
                .load(imageURL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.moviePoster);

        holder.rootMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sID =list.get(position).getId();
                mPopularMoviesFragment.getMovieByID(sID);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movieTitle)
        TextView movieTitle;
        @BindView(R.id.moviePoster)
        ImageView moviePoster;
        @BindView(R.id.rootMovies)
        LinearLayout rootMovies;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}