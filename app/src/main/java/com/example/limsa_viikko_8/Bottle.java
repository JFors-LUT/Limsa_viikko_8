package com.example.limsa_viikko_8;

public class Bottle {
    private String name;
    //private String manufacturer;
    //private double total_energy = 0.3;
    private double tilavuus;
    private double hinta;
    private int ID;

    public Bottle(String nimi, String manuf, double koko, double arvo, int tunnus){
        name = nimi;
        //manufacturer = manuf;
        tilavuus = koko;
        hinta = arvo;
        ID = tunnus;
    }

    public String getName(){
        return name;
    }

    public double getHinta() {
        return hinta;
    }

    public double getTilavuus(){
        return tilavuus;
    }

    public int getID(){
        return ID;
    }

    public void setLastOstos(String lastNimi, double lastKoko, double lastArvo){
        name = lastNimi;
        tilavuus = lastKoko;
        hinta = lastArvo;
    }

    //public String getManufacturer(){}
    //public double getEnergy(){}
}