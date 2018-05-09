package com.plataformas.anahernandez.laboratorioroom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String urlWeather = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
    JSONObject resultWeather;
    JSONObject objRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue  = Volley.newRequestQueue(this);

        JsonObjectRequest objRequest = new JsonObjectRequest(Request.Method.GET, urlWeather, null, new Response.Listener<JSONObject>()
        {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            resultWeather = (JSONObject) response.getJSONArray("weather").get(0);
                            System.out.println(resultWeather.getString("description"));
                            System.out.println(response.getJSONObject("main").getString("temp"));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                });

        queue.add(objRequest);
    }
}
