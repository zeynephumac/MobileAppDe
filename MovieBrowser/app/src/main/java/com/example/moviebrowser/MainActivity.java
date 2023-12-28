package com.example.moviebrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements MovieFragment.OnMovieSelected {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void movieSelected(Movie movie) {
        int displayMode = getResources().getConfiguration().orientation;
        if(displayMode == Configuration.ORIENTATION_PORTRAIT){
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("movie", (Serializable) movie);
            startActivity(intent);
        }
        else{
            DetailsFragment df = (DetailsFragment) getSupportFragmentManager().findFragmentByTag("details");
            if(df == null){
                FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
                df = DetailsFragment.newInstance(movie);
                fts.add(R.id.container, df, "details");
                fts.commit();
            }
            else{
                df.setMovie(movie, findViewById(R.id.container));
            }
        }


    }
}