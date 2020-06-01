package com.example.iprodottidellamiaterra;

import java.util.Date;

public class Prodotto {
    String descr;
    String qnt;
    String info;
    int tempo;

    public Prodotto(String descr, String qnt, String info, int tempo) {
        this.descr = descr;
        this.qnt = qnt;
        this.info = info;
        this.tempo = tempo;
    }

    public Prodotto(String descr, String qnt) {
        this.descr = descr;
        this.qnt = qnt;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getQnt() {
        return qnt;
    }

    public void setQnt(String qnt) {
        this.qnt = qnt;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }


}
