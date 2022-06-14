package com.example.peliculas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends AppCompatActivity implements Handler.Callback {

    List<Pelicula> peliculas;
    AdapterPelicula adapterPelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        this.peliculas = new ArrayList<>();
        this.adapterPelicula = new AdapterPelicula(peliculas, this);
        RecyclerView rv = super.findViewById(R.id.rvPeliculas);
        rv.setAdapter(this.adapterPelicula);
        rv.setLayoutManager(new LinearLayoutManager(this));


        Bundle bundle = super.getIntent().getExtras();
        String search = bundle.getString("search", "Harry Potter");
        String type = bundle.getString("type", "movies");
        String url = "http://www.omdbapi.com?i=tt3896198&apikey=99cddcac&s=" + search + "&type=" + type;

        Handler handler = new Handler(this);
        HiloConexion hilo = new HiloConexion(handler, url, true);
        hilo.start();

        ActionBar acbar = super.getSupportActionBar();
        acbar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if( message.arg1== 1 ){
            this.peliculas = Pelicula.jsonToArray(message.obj.toString());

            this.adapterPelicula = new AdapterPelicula(peliculas, this);
            RecyclerView rv = super.findViewById(R.id.rvPeliculas);
            rv.setAdapter(this.adapterPelicula);
            rv.setLayoutManager(new LinearLayoutManager(this));
        }
        else if(message.arg1 == 3){
            byte[] img = (byte[]) message.obj;
            if(img != null){
                this.peliculas.get(message.arg2).setPoster(img);
                this.peliculas.get(message.arg2).setLoadingPoster(true);
                this.adapterPelicula.notifyItemChanged(message.arg2);
            }
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}