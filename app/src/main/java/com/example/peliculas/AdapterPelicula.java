package com.example.peliculas;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterPelicula extends RecyclerView.Adapter<PeliculaVH> {
    public static List<Pelicula> peliculas;
    public Activity a;


    public AdapterPelicula(){}

    public AdapterPelicula(List<Pelicula> usuariuos, Activity a){
        AdapterPelicula.peliculas = usuariuos;
        this.a = a;
    }

    @NonNull
    @Override
    public PeliculaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelicula, parent, false);

        return new PeliculaVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaVH holder, int position) {
        holder.tvTitle.setText(AdapterPelicula.peliculas.get(position).getTitle());
        holder.tvType.setText(AdapterPelicula.peliculas.get(position).getType());
        holder.tvYear.setText(AdapterPelicula.peliculas.get(position).getYear());

        String url = AdapterPelicula.peliculas.get(position).getPosterUrl();
        if(
            AdapterPelicula.peliculas.get(position).getPoster().length == 0 &&
            !AdapterPelicula.peliculas.get(position).getLoadingPoster()
        ){
            Handler handler = new Handler((Handler.Callback) this.a);
            HiloConexion hilo = new HiloConexion(handler, url, false, position);
            hilo.start();
        }
        else{
            byte[] img = AdapterPelicula.peliculas.get(position).getPoster();
            holder.ivPoster.setImageBitmap(BitmapFactory.decodeByteArray(img,0,img.length));
        }
        holder.item.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return AdapterPelicula.peliculas.size();
    }

}
