package com.acenkzproject.myfuitlist;

import android.os.Parcel;
import android.os.Parcelable;

public class Fruit implements Parcelable {
    private String nama;
    private String deskripsi;
    private Integer gambar;

    protected Fruit(Parcel in) {
        nama = in.readString();
        deskripsi = in.readString();
        if (in.readByte() == 0) {
            gambar = null;
        } else {
            gambar = in.readInt();
        }
    }

    public static final Creator<Fruit> CREATOR = new Creator<Fruit>() {
        @Override
        public Fruit createFromParcel(Parcel in) {
            return new Fruit(in);
        }

        @Override
        public Fruit[] newArray(int size) {
            return new Fruit[size];
        }
    };

    public Fruit() {

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getGambar() {
        return gambar;
    }

    public void setGambar(Integer gambar) {
        this.gambar = gambar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(deskripsi);
        if (gambar == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(gambar);
        }
    }
}
