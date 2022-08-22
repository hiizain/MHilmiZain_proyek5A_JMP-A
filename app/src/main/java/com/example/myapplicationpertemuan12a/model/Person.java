package com.example.myapplicationpertemuan12a.model;

public class Person {
    private int id;
    private String nama, alamat;

    public Person(int id, String nama, String alamat){
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
    }

    public int getId(){
        return id;
    }

    public String getNama(){
        return nama;
    }

    public String getAlamat(){
        return alamat;
    }
}
