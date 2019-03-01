package com.averoes.daff.listmahasiswa.model;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by daff on 21/02/19 at 18:16.
 */

public class MahasiswaModel implements Parcelable {

    private int id;
    private String name;
    private String nim;

    public MahasiswaModel() {

    }

    public MahasiswaModel(String name, String nim) {
        this.name = name;
        this.nim = nim;
    }

    protected MahasiswaModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        nim = in.readString();
    }

    public static final Creator<MahasiswaModel> CREATOR = new Creator<MahasiswaModel>() {
        @Override
        public MahasiswaModel createFromParcel(Parcel in) {
            return new MahasiswaModel(in);
        }

        @Override
        public MahasiswaModel[] newArray(int size) {
            return new MahasiswaModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.nim);
    }
}
