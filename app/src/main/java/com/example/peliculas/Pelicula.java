package com.example.peliculas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Pelicula {
    public String title;
    public String type;
    public String year;
    public String posterUrl;
    public byte[] poster;
    public Boolean loadingPoster;

   public Pelicula(String title, String year, String type, String posterUrl) {
        this.title = title;
        this.year = year;
        this.type = type;
        this.posterUrl = posterUrl;
        this.poster = new byte[]{};
        this.loadingPoster = false;
    }

    public Pelicula(String title, String year, String type, byte[] poster) {
        this.title = title;
        this.year = year;
        this.type = type;
        this.poster = poster;
        this.loadingPoster = false;
    }

    public byte[] getPoster() {
        return poster;
    }

    public void setPoster(byte[] poster) {
        this.poster = poster;
    }

    public Boolean getLoadingPoster() {
        return loadingPoster;
    }

    public void setLoadingPoster(Boolean loadingPoster) {
        this.loadingPoster = loadingPoster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }



    public static List<Pelicula> jsonToArray(String object){
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(object);
            JSONArray pelis = obj.getJSONArray("Search");
            for(int i = 0; i < pelis.length(); i++){
                JSONObject element = pelis.getJSONObject(i);
                Pelicula pelicula = new Pelicula(element.getString("Title"),element.getString("Year"),element.getString("Type"),element.getString("Poster"));
                peliculas.add(pelicula);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return peliculas;
    }
}
