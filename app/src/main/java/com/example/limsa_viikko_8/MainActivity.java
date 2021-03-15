package com.example.limsa_viikko_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView console;
    TextView monies;
    Editable ph_text = null;
    SeekBar raha_liukuri;
    Spinner list_bottles;
    Context context = null;

    BottleDispenser jakari = BottleDispenser.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        console = (TextView) findViewById(R.id.textView);
        monies = (TextView) findViewById(R.id.textView2);
        raha_liukuri = (SeekBar) findViewById(R.id.seekBar);
        list_bottles = (Spinner) findViewById(R.id.spinner);
        context = MainActivity.this;

        addToBottleList();

        raha_liukuri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                monies.setText(String.valueOf((float)progress*5/100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void addToBottleList(){
        List<String> pulloList = new ArrayList<String>();
        pulloList.add("Pepsi Max, 0.5l, 1.8€");
        pulloList.add("Pepsi Max, 1.5l 2.2€");
        pulloList.add("Coca-Cola Zero, 0.5l, 2.0€");
        pulloList.add("Coca-Cola Zero, 1.5l, 2.5€");
        pulloList.add("Fanta Zero, 0.5l, 1.95€");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, pulloList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list_bottles.setAdapter(dataAdapter);
    }

        public void textHandler() {
            console.setText("");
        }

    public void moneyIN(View v){
            console.setText("");
            int cash_money = raha_liukuri.getProgress();
            raha_liukuri.setProgress(0);
            jakari.addMoney(console, cash_money);
        }

        public void buyBottle(View v){
            console.setText("");
            jakari.buyBottle(list_bottles.getSelectedItemId(), console);
        }

        public void moneyOUT(View v){
            console.setText("");
            jakari.returnMoney(console);
        }

        public void showMenu(View v){
            console.setText("");
            jakari.showTiedot(console, context);
        }
    }