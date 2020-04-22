package com.fitness.android.myapplication.UI;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fitness.android.myapplication.R;
import com.fitness.android.myapplication.pojo.MoviesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {

    public static final String MOVIE_NAME="name";
    public static final String MOVIE_DATE="dare";
    public static final String MOVIE_PATH="path";
    public static final String MOVIE_POP="pop";
    public static final String MOVIE_OVER="over";

    public MoviesAdapter(List<MoviesModel> moviesData) {
        this.moviesData = moviesData;
        notifyDataSetChanged();
    }

    private List<MoviesModel> moviesData;
    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent
                ,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

        //here we have to get the link of the image from the data list
        holder.loadImage("https://image.tmdb.org/t/p/w500" + moviesData.get(position).getPoster_path());
        holder.movie_img.setTag(moviesData.get(position));

    }

    @Override
    public int getItemCount()
    {
        return moviesData.size();
    }


    public class MovieHolder extends RecyclerView.ViewHolder {

        ImageView movie_img;
        public MovieHolder(@NonNull final View itemView) {
            super(itemView);
            movie_img = itemView.findViewById(R.id.img_movie_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MoviesModel movie = (MoviesModel) movie_img.getTag();

                    Intent intent = new Intent(itemView.getContext(), DetailsActivity.class);

                    intent.putExtra(MOVIE_NAME, movie.getOriginal_title());
                    intent.putExtra(MOVIE_POP, movie.getPopularity());
                    intent.putExtra(MOVIE_PATH, "https://image.tmdb.org/t/p/w500"+movie.getPoster_path());
                    intent.putExtra(MOVIE_OVER, movie.getOverview());
                    intent.putExtra(MOVIE_DATE, movie.getRelease_date());

                    itemView.getContext().startActivity(intent);
                }
            });

        }

        public void loadImage(String url)
        {

            Picasso.get().
                load(url)
                    .into(movie_img);
        }
    }
}
