package com.example.limsa_viikko_8;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class BottleDispenser {
    private int amount_bottles;
    public ArrayList<Bottle> bottles = new ArrayList<>();
    public Bottle last_ostos = new Bottle("","",0,0,0);
    private float money;

    private static BottleDispenser BD = new BottleDispenser();

    private BottleDispenser() {
        amount_bottles = 5;
        money = 0;

        bottles.add(new Bottle ("Pepsi Max", "Pepesi", 0.5, 1.8, 0));
        bottles.add(new Bottle("Pepsi Max", "Pepesi", 1.5, 2.2, 1));
        bottles.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.5, 2.0, 2));
        bottles.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 1.5, 2.5, 3));
        bottles.add(new Bottle("Fanta Zero", "Coca-Cola", 0.5, 1.95, 4));

    }

    public static BottleDispenser getInstance(){
        return BD;
    }

    public void showTiedot(TextView console, Context context) {
        String saveFile = "kuitti.txt";
        String s = ("Kuitti ostoksestasi:\n"+"Ostettu tuote: "+last_ostos.getName() +" \nPullon koko(l): "+ last_ostos.getTilavuus() +"\n"+ "Ostoksen hinta(€): "+last_ostos.getHinta());
        if (last_ostos.getHinta() != 0) {
            try {
                OutputStreamWriter ows = new OutputStreamWriter((context.openFileOutput(saveFile, Context.MODE_PRIVATE)));
                ows.write(s);
                ows.close();
            } catch (IOException e) {
            Log.e("IO","IOvirhe");
        } finally {
            System.out.println("Kirjoitusoperaatio käsitelty.");
            }
        }else{
            console.append("Tee ostos ennen kuitin tulostamista!");
        }
    }


    public void addMoney(TextView console, int cash_money) {
        money = money +  ( (float) cash_money*5/100); //progress to euro cents
        console.append("Klink! Added more money!");
    }

    public void buyBottle(long valinta, TextView console) {
        int pullo_vs = (int) valinta;
        int buy_succes = 0;

        for(int i = 0; i<amount_bottles; i++) {
            if (bottles.get(i).getID() == pullo_vs) {
                float hinta = (float) bottles.get(i).getHinta();
                if (money >= hinta) {
                    amount_bottles -= 1;
                    money -= hinta;
                    console.append("KACHUNK! " + (bottles.get(i).getName()) + " came out of the dispenser!");
                    buy_succes = 1;
                    last_ostos.setLastOstos(bottles.get(i).getName(), bottles.get(i).getTilavuus(), bottles.get(i).getHinta());
                    bottles.remove(i);
                    break;
                } else {
                    console.append("Add money first!");
                    buy_succes = 1;
                    break;
                }
        }
    }
        if(buy_succes == 0) {
            console.append("Sorry the specific soda is out of stock!");
        }
    }

    public void returnMoney(TextView console) {
        if(money != 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String moneyString = decimalFormat.format(money);
            console.append("Klink klink. Money came out! You got ");
            console.append(moneyString);
            console.append("€ back");
            money = 0;
        } else {
            console.append("No money in the machine!");
        }
    }
}