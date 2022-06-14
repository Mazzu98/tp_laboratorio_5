package com.example.peliculas;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsView {
    Activity a;
    Pelicula p;
    DetailsController c;

    TextView tvTitle;
    TextView tvYear;
    TextView tvType;
    ImageView ivPoster;

    DetailsView(Activity a, Pelicula p, DetailsController c){
        this.a = a;
        this.p = p;
        this.c = c;
        if(this.tvTitle == null){
            this.tvTitle = this.a.findViewById(R.id.tvTitle);
            this.tvYear = this.a.findViewById(R.id.tvYear);
            this.tvType = this.a.findViewById(R.id.tvType);
            this.ivPoster = this.a.findViewById(R.id.ivPoster);
        }
    }

    public void mostrarModelo() {
        this.tvTitle.setText(this.p.title);
        this.tvYear.setText(this.p.year);
        this.tvType.setText(this.p.type);
        this.ivPoster.setImageBitmap(BitmapFactory.decodeByteArray(this.p.poster,0,this.p.poster.length));
    }
}
