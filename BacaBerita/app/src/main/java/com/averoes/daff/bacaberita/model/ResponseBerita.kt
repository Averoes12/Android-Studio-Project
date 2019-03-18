package com.averoes.daff.bacaberita.model

/**
 * Created by daff on 05/02/19.
 */
import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class ResponseBerita {

    @SerializedName("berita")
    var berita: ArrayList<BeritaItem>? = null

    @SerializedName("status")
    var isStatus: Boolean = false

    override fun toString(): String {
        return "ResponseBerita{" +
                "berita = '" + berita + '\''.toString() +
                ",status = '" + isStatus + '\''.toString() +
                "}"
    }
}

