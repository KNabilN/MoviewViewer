package com.fitness.android.myapplication.UI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fitness.android.myapplication.R;
import com.fitness.android.myapplication.pojo.Results;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView moviesRecycler;
    private RecyclerView.LayoutManager manager;
    private MoviesAdapter adapter;

    private TextView error;
    private ProgressBar pg;

    private MoviesViewModel moviesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaration
        moviesRecycler = findViewById(R.id.rv_movies);
        pg = findViewById(R.id.pg);
        error = findViewById(R.id.error_txt);


        manager = new GridLayoutManager(this, 2);
//        //preparing

        moviesRecycler.setLayoutManager(manager);
//
//        view model
        moviesViewModel = ViewModelProviders.of(this)
                .get(MoviesViewModel.class);
        moviesViewModel.getMoves(null);

        // fetching data
        moviesViewModel.moviesMutable.observe(this, new Observer<Results>() {

            @Override
            public void onChanged(Results results) {
                adapter = new MoviesAdapter(results.getResults());
                moviesRecycler.setAdapter(adapter);
                showData();

            }

        });

        moviesViewModel.error.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                showError();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.pop_action:
                pg.setVisibility(View.VISIBLE);
                moviesViewModel.getMoves("popularity.desc");
                return true;
            case R.id.revenue_action:
                pg.setVisibility(View.VISIBLE);
                moviesViewModel.getMoves("revenue.desc");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void showError()
    {
        pg.setVisibility(View.INVISIBLE);
        moviesRecycler.setVisibility(View.INVISIBLE);
        error.setVisibility(View.VISIBLE);
    }

    public void showData()
    {
        pg.setVisibility(View.INVISIBLE);
        moviesRecycler.setVisibility(View.VISIBLE);
        error.setVisibility(View.INVISIBLE);
    }

}
