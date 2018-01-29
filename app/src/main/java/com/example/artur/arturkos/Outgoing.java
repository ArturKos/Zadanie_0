package com.example.artur.arturkos;

/**
 * Created by Artur on 28.01.2018.
 */

public class Outgoing {

    public int          mId;
    public String       mData;
    public String       mTyp_id;
    public String       mNazwa;
    public double       mWartosc;

    public Outgoing(){
    }

    public Outgoing (String data, String typ_id, String nazwa, double wartosc) {
        this.mData = data;
        this.mTyp_id = typ_id;
        this.mNazwa = nazwa;
        this.mWartosc = wartosc;
    }

}
