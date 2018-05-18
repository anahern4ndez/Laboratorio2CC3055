package com.plataformas.anahernandez.laboratorioroom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


/** Ana Lucia Hernandez 17138
 * Esteban Cabrera 17781
 * Fernando Hengstenberg 17699
 * Raul Monzon 17014
 * Kevin Macario 17369
 * 08/05/2017
 * Laboratorio 2: Plataformas Moviles y Juegos
 * Clase que modela los objetos que guardara Room
 *
 */

@Entity (tableName = "clima")
public class Clima {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "container_name")
    @NonNull
    private String containerName;

    @Ignore
    private ArrayList<String> elementosAnidados = new ArrayList<>();

    @ColumnInfo(name = "context")
    private String context;

    public Clima()
    {
        elementosAnidados = new ArrayList<>();
    }

    public Clima(@NonNull ArrayList<String> elementosAnidados, @NonNull String containerName) {
        this.containerName = containerName;
        this.elementosAnidados = elementosAnidados;
    }

    @Override
    public String toString() {
        String hilo = "\t" + "\"" + containerName + "\":" + "\n" + context;
        return hilo;
    }
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getAnidados()
    {
        return elementosAnidados;
    }

    public void setAnidados(ArrayList<String> lista)
    {
        this.elementosAnidados = lista;
        this.context = "";
        for (String el: elementosAnidados)
        {
            context += el +", \n";
        }
    }

    public String getNombre()
    {
        return containerName;
    }
    public void setNombre(String nombre)
    {
        this.containerName = nombre;
    }

    public int getId() {
        return id;
    }

    public String getContainerName() {
        return containerName;
    }

    public ArrayList<String> getElementosAnidados() {
        return elementosAnidados;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public void setElementosAnidados(ArrayList<String> elementosAnidados) {
        this.elementosAnidados = elementosAnidados;
    }
}
