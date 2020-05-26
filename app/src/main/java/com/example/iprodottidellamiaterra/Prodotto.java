package com.example.iprodottidellamiaterra;

public class Prodotto {
    String descr;
    String qnt;


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


}
