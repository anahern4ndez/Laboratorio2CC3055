package com.plataformas.anahernandez.laboratorioroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActivityB extends AppCompatActivity {

    BaseDatos climaDatabase;
    List<Clima> lweather = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        climaDatabase = BaseDatos.getDatabase(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                lweather = climaDatabase.dao().getAll();
                startView();
            }
        }).start();


    }
    public void startView()
    {
        TextView tv = findViewById(R.id.tituloDB);
        tv.setText("Información de la base de datos: ");
        listView = (ListView) findViewById(R.id.listView2);
        ArrayAdapter<Clima> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lweather);
        listView.setAdapter(adapter);
    }
}
