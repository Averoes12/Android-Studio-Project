package com.averoes.daff.bacaberita.model

/**
 * Created by daff on 05/02/19.
 */
import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
class BeritaItem : Parcelable {

    @SerializedName("penulis")
    var penulis: String? = null

    @SerializedName("foto")
    var foto: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("judul_berita")
    var judulBerita: String? = null

    @SerializedName("tanggal_posting")
    var tanggalPosting: String? = null

    @SerializedName("isi_berita")
    var isiBerita: String? = null

    override fun toString(): String {
        return "BeritaItem{" +
                "penulis = '" + penulis + '\''.toString() +
                ",foto = '" + foto + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",judul_berita = '" + judulBerita + '\''.toString() +
                ",tanggal_posting = '" + tanggalPosting + '\''.toString() +
                ",isi_berita = '" + isiBerita + '\''.toString() +
                "}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.isiBerita)
        dest.writeString(this.foto)
        dest.writeString(this.id)
        dest.writeString(this.judulBerita)
        dest.writeString(this.tanggalPosting)
        dest.writeString(this.penulis)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.isiBerita = `in`.readString()
        this.foto = `in`.readString()
        this.id = `in`.readString()
        this.judulBerita = `in`.readString()
        this.tanggalPosting = `in`.readString()
        this.penulis = `in`.readString()
    }

    companion object {


        object CREATOR : Parcelable.Creator<BeritaItem> {
            override fun createFromParcel(parcel: Parcel): BeritaItem {
                return BeritaItem(parcel)
            }

            override fun newArray(size: Int): Array<BeritaItem?> {
                return arrayOfNulls(size)
            }

        }
    }


}