package com.example.peliculas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;

import java.io.ByteArrayOutputStream;

public class DetailsActivity extends AppCompatActivity {

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle bundle = super.getIntent().getExtras();
        String title = bundle.getString("title", "Titulasooo");
        String year = bundle.getString("year", "2013");
        String type = bundle.getString("type", "movies");
        Bitmap poster = bundle.getParcelable("poster");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        poster.compress(Bitmap.CompressFormat.JPEG,80,stream);
        byte[] byteArrayPoster = stream.toByteArray();

        Pelicula pelicula = new Pelicula(title, year, type, byteArrayPoster);

        DetailsController controller = new DetailsController(pelicula);
        DetailsView view = new DetailsView(this, pelicula, controller);
        controller.setView(view);

        controller.initData();

        ActionBar acbar = super.getSupportActionBar();
        acbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}