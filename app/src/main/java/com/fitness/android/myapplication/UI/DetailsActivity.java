package com.fitness.android.myapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitness.android.myapplication.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    private TextView name, date, over, pop;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = findViewById(R.id.txtname);
        date = findViewById(R.id.txtdate);
        over = findViewById(R.id.txtoverview);
        pop = findViewById(R.id.txtpop);
        img = findViewById(R.id.img_profile);

        Intent intent = getIntent();
        if (intent != null)
        {
            name.setText(intent.getStringExtra(MoviesAdapter.MOVIE_NAME));
            date.setText(intent.getStringExtra(MoviesAdapter.MOVIE_DATE));
            over.setText(intent.getStringExtra(MoviesAdapter.MOVIE_OVER));
            pop.setText("Popularity: "+intent.getFloatExtra(MoviesAdapter.MOVIE_POP,0));

            Picasso.get().load(intent.getStringExtra(MoviesAdapter.MOVIE_PATH))
                    .into(img);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.detail, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.share_action:
                sendMessage();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void sendMessage() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,

                 "We recommend " +
                name.getText()+"\n\n"
                + pop.getText() + " love it"
                + "\n\n" + over.getText()+ "\n\n"
                +"From Karim's app"
        );
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");

        Intent share = Intent.createChooser(intent, null);
        if (share.resolveActivity(getPackageManager()) != null)
        {
            startActivity(share);
        }
    }
}
