package com.plataformas.anahernandez.laboratorioroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String urlWeather = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
    JSONObject resultWeather;
    List<Clima> listaweather = new ArrayList<>(); //lista que contiene todos los elementos del url
    final List<Clima> display = new ArrayList<>(); //lista que contiene los elementos que se mostraran en el listview
    ListView listView;
    BaseDatos climaDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue  = Volley.newRequestQueue(this);
        climaDatabase = BaseDatos.getDatabase(this);

        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, urlWeather, null, new Response.Listener<JSONObject>()
        {
                    @Override
                    public void onResponse(JSONObject response) {
                        listaweather = fillInfo(response);
                        for (Clima el : listaweather)
                        {
                            if (el.getNombre().contains("main"))
                            {
                                display.add(el);
                                loadListView();
                            }
                        }
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                climaDatabase.dao().nukeTable(); //esto para que no se agregue la misma informacion al DB cada vez que corra el programa
                                climaDatabase.dao().insertAll(listaweather);
                            }
                        }).start();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                });
        queue.add(objRequest);

    }

    public List<Clima> fillInfo(JSONObject response)
    {

        List<Clima> toAdd = new ArrayList<>();

        try {
            ArrayList<String> elementos = new ArrayList<>();

            resultWeather = (JSONObject) response.getJSONArray("weather").get(0);
            elementos.add("id: " + resultWeather.getString("id"));
            elementos.add("main: " + resultWeather.getString("main"));
            elementos.add("icon: " + resultWeather.getString("icon"));
            elementos.add("description: " + resultWeather.getString("description"));
            Clima dato = new Clima(elementos, "weather");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add(response.getString("base"));
            dato = new Clima(elementos, "base");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add("temp: " + response.getJSONObject("main").getString("temp"));
            elementos.add("pressure: " + response.getJSONObject("main").getString("pressure"));
            elementos.add("humidity: " + response.getJSONObject("main").getString("humidity"));
            elementos.add("temp_min: " + response.getJSONObject("main").getString("temp_min"));
            elementos.add("temp_max: " + response.getJSONObject("main").getString("temp_max"));
            dato = new Clima(elementos, "main");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add(response.getString("visibility"));
            dato = new Clima(elementos, "visibility");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add("speed: " + response.getJSONObject("wind").getString("speed"));
            elementos.add("deg: " + response.getJSONObject("wind").getString("deg"));
            dato = new Clima(elementos, "wind");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add("all: " + response.getJSONObject("clouds").getString("all"));
            dato = new Clima(elementos, "clouds");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add(response.getString("dt"));
            dato = new Clima(elementos, "dt");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add("type: " + response.getJSONObject("sys").getString("type"));
            elementos.add("id: " + response.getJSONObject("sys").getString("id"));
            elementos.add("message: " + response.getJSONObject("sys").getString("message"));
            elementos.add("country: " + response.getJSONObject("sys").getString("country"));
            elementos.add("sunrise: " + response.getJSONObject("sys").getString("sunrise"));
            elementos.add("sunset: " + response.getJSONObject("sys").getString("sunset"));
            dato = new Clima(elementos, "sys");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add(response.getString("id"));
            dato = new Clima(elementos, "id");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add(response.getString("name"));
            dato = new Clima(elementos, "name");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add(response.getString("cod"));
            dato = new Clima(elementos, "cod");
            toAdd.add(dato);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return toAdd;
    }
    public void loadListView()
    {
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<Clima> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,display);
        listView.setAdapter(adapter);
        System.out.println(display);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //Abre la activity 2, mandando como objeto el hospital
                        Intent test = new Intent(view.getContext(), ActivityB.class);
                        startActivity(test);

                    }
                }
        );
    }
}
