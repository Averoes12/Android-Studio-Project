package com.averoes.daff.bacaberita.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.averoes.daff.bacaberita.R.id.*

/**
 * Created by daff on 05/02/19.
 */

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    val item_img = itemView.findViewById<ImageView>(item_image)
    val judul = itemView.findViewById<TextView>(item_judul)
    val penulis = itemView.findViewById<TextView>(item_penulis)
    val tanggal = itemView.findViewById<TextView>(item_tanggal)

}
