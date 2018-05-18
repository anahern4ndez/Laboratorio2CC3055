package com.plataformas.anahernandez.laboratorioroom;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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


/** Ana Lucia Hernandez 17138
 * Esteban Cabrera 17781
 * Fernando Hengstenberg 17699
 * Raul Monzon 17014
 *Kevin Macario 17369
 * 08/05/2017
 * Laboratorio 2: Plataformas Moviles y Juegos
 *
 */


public class MainActivity extends AppCompatActivity {
    String urlWeather = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
    JSONObject resultWeather;
    List<Clima> listaweather = new ArrayList<>(); //lista que contiene todos los elementos del url
    List<String> display = new ArrayList<>(); //lista que contiene los elementos que se mostraran en el listview
    ListView listView;
    BaseDatos climaDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        climaDatabase = BaseDatos.getDatabase(this);

        final RequestQueue queue  = Volley.newRequestQueue(this);
        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, urlWeather, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response) {
                listaweather = fillInfo(response);
                //para que haga el display en el listview de unicamente la informacion de main
                for (Clima el : listaweather)
                {
                    if (el.getNombre().equals("main"))
                    {
                        for (String anidado : el.getAnidados()) {
                            display.add(anidado);
                        }
                    }
                }
                loadListView();
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
    /**
     * Metodo para cargar la listview con la informacion previamente obtenida de la pagina web
     */
    public void loadListView()
    {
        TextView tv = findViewById(R.id.tituloLista);
        tv.setText("Información de \"main\": ");
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,display);
        listView.setAdapter(adapter);
        System.out.println(display);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //Abre la activity 2
                        Intent test = new Intent(view.getContext(), ActivityB.class);
                        startActivity(test);

                    }
                }
        );
    }
    /**
     *  Metodo para jalar toda la informacion de la pagina de internet y guardarla en una lista
     * @param response: conexion a la informacion de internet
     * @return lista que contiene todos los elementos obtenidos de la pagina web
     */
    public List<Clima> fillInfo(JSONObject response)
    {

        List<Clima> toAdd = new ArrayList<>();

        try {
            ArrayList<String> elementos = new ArrayList<>();

            elementos = new ArrayList<>();
            elementos.add("lon: " + response.getJSONObject("coord").getString("lon"));
            elementos.add("lat: " + response.getJSONObject("coord").getString("lat"));
            Clima dato = new Clima();
            dato.setAnidados(elementos);
            dato.setNombre("coord");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            resultWeather = (JSONObject) response.getJSONArray("weather").get(0);
            elementos.add("id: " + resultWeather.getString("id"));
            elementos.add("main: " + resultWeather.getString("main"));
            elementos.add("icon: " + resultWeather.getString("icon"));
            elementos.add("description: " + resultWeather.getString("description"));
            dato = new Clima();
            dato.setAnidados(elementos);
            dato.setNombre("weather");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add(response.getString("base"));
            dato = new Clima();
            dato.setAnidados(elementos);
            dato.setNombre("base");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add("temp: " + response.getJSONObject("main").getString("temp"));
            elementos.add("pressure: " + response.getJSONObject("main").getString("pressure"));
            elementos.add("humidity: " + response.getJSONObject("main").getString("humidity"));
            elementos.add("temp_min: " + response.getJSONObject("main").getString("temp_min"));
            elementos.add("temp_max: " + response.getJSONObject("main").getString("temp_max"));
            dato = new Clima();
            dato.setAnidados(elementos);
            dato.setNombre("main");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add(response.getString("visibility"));
            dato = new Clima();
            dato.setAnidados(elementos);
            dato.setNombre("visibility");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add("speed: " + response.getJSONObject("wind").getString("speed"));
            elementos.add("deg: " + response.getJSONObject("wind").getString("deg"));
            dato = new Clima();
            dato.setAnidados(elementos);
            dato.setNombre("wind");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add("all: " + response.getJSONObject("clouds").getString("all"));
            dato = new Clima();
            dato.setAnidados(elementos);
            dato.setNombre("clouds");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add(response.getString("dt"));
            dato = new Clima();
            dato.setAnidados(elementos);
            dato.setNombre("dt");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add("type: " + response.getJSONObject("sys").getString("type"));
            elementos.add("id: " + response.getJSONObject("sys").getString("id"));
            elementos.add("message: " + response.getJSONObject("sys").getString("message"));
            elementos.add("country: " + response.getJSONObject("sys").getString("country"));
            elementos.add("sunrise: " + response.getJSONObject("sys").getString("sunrise"));
            elementos.add("sunset: " + response.getJSONObject("sys").getString("sunset"));
            dato = new Clima();
            dato.setAnidados(elementos);
            dato.setNombre("sys");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add(response.getString("id"));
            dato = new Clima();
            dato.setAnidados(elementos);
            dato.setNombre("id");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add(response.getString("name"));
            dato = new Clima();
            dato.setAnidados(elementos);
            dato.setNombre("name");
            toAdd.add(dato);
            elementos = new ArrayList<>();
            elementos.add(response.getString("cod"));
            dato = new Clima();
            dato.setAnidados(elementos);
            dato.setNombre("cod");
            toAdd.add(dato);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return toAdd;
    }
}
