package com.example.peliculas;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;

class HiloConexion extends Thread {
    Handler colaMensajes;
    String url;
    boolean texto;
    int position;

    public HiloConexion(Handler colaMensajes, String url, boolean texto, int position){
        this.colaMensajes = colaMensajes;
        this.url = url;
        this.texto = texto;
        this.position = position;
    }

    public HiloConexion(Handler colaMensajes, String url, boolean texto){
        this.colaMensajes = colaMensajes;
        this.url = url;
        this.texto = texto;
    }

    public void run() {
        ConexionHttp con = new ConexionHttp();
        if(this.texto) {
            byte[] respueta = con.obtenerInformacion(this.url);
            String respuetaS = new String(respueta);

            Message message = new Message();
            message.arg1 = MainActivity.MENSAJE_STRING;
            message.obj = respuetaS;
            this.colaMensajes.sendMessage(message);

        }else{
            byte[] respueta = con.obtenerInformacion(url);

            Message message = new Message();
            message.arg1 = 3;
            message.arg2 = this.position ;
            message.obj = respueta;
            this.colaMensajes.sendMessage(message);
        }
    }
}