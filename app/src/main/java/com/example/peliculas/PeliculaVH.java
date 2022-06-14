package com.example.peliculas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;

public class PeliculaVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvTitle;
    TextView tvType;
    TextView tvYear;
    ImageView ivPoster;
    View item;


    public PeliculaVH(@NonNull View itemView) {
        super(itemView);
        this.tvTitle = itemView.findViewById(R.id.tvTitle);
        this.tvType = itemView.findViewById(R.id.tvType);
        this.tvYear = itemView.findViewById(R.id.tvYear);
        this.ivPoster = itemView.findViewById(R.id.ivPoster);
        this.item = itemView.findViewById(R.id.pelicula);
    }

    @Override
    public void onClick(View view) {
        Context context = item.getContext();
        Intent i = new Intent(context,DetailsActivity.class);

        BitmapDrawable drawable = (BitmapDrawable) ivPoster.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        i.putExtra("title",tvTitle.getText().toString());
        i.putExtra("type",tvType.getText().toString());
        i.putExtra("year",tvYear.getText().toString());
        i.putExtra("poster", bitmap);

        Activity activity = (Activity) context;
        activity.startActivity(i);
    }
}
