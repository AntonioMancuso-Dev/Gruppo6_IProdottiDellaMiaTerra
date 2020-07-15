package com.example.iprodottidellamiaterra;

import java.util.Date;

public class Prodotto {
    String descr;
    String qnt;
    String info;
    Date startDate;
    int tempo;

    public Prodotto(String descr, String info, Date startDate) {
        this.descr = descr;
        this.info = info;
        this.startDate = startDate;
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
        return (int) ((new Date().getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }


}
