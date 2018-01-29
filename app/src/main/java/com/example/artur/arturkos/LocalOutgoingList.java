package com.example.artur.arturkos;

import java.util.ArrayList;

/**
 * Created by Artur on 28.01.2018.
 */

public class LocalOutgoingList {

    private ArrayList<Outgoing> mOutgoingList;

    public LocalOutgoingList() {
        mOutgoingList = new ArrayList<>();
    }
    public void AddOutgoingToList(String data, String typ_id, String nazwa, double wartosc){
      mOutgoingList.add(new Outgoing(data, typ_id, nazwa, wartosc));
    }
    public  ArrayList<Outgoing> GetOutgoingList(){
        return mOutgoingList;
    }
}
