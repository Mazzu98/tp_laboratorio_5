package com.example.peliculas;

public class DetailsController {

    Pelicula p;
    DetailsView v;

    DetailsController(Pelicula p){this.p = p;}

    public void setView(DetailsView v) {this.v = v;}

    public void initData(){
        v.mostrarModelo();
    }
}
