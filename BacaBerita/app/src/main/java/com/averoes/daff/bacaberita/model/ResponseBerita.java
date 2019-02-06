package com.averoes.daff.bacaberita.model;

/**
 * Created by daff on 05/02/19.
 */
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseBerita{

    @SerializedName("berita")
    private ArrayList<BeritaItem> berita;

    @SerializedName("status")
    private boolean status;

    public void setBerita(ArrayList<BeritaItem> berita){
        this.berita = berita;
    }

    public ArrayList<BeritaItem> getBerita(){
        return berita;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){
        return status;
    }

    @Override
    public String toString() {
        return
                "ResponseBerita{" +
                        "berita = '" + berita + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}

