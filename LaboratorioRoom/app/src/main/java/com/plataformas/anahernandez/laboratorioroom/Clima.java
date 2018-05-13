package com.plataformas.anahernandez.laboratorioroom;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que describe los elementos que ira guardando Room
 * Created by anahernandez on 5/9/18.
 */

public class Clima implements Parcelable {

    private String containerName;
    private ArrayList<String> elementosAnidados;

    public Clima(ArrayList<String> elementosAnidados, String containerName) {
        this.containerName = containerName;
        this.elementosAnidados = elementosAnidados;
    }

    @Override
    public String toString() {
        String hilo = "Información de: \""+ containerName + "\"\n ";

        for (String el: elementosAnidados)
        {
            hilo += "\t" +el +", \n";
        }
        return hilo;
    }
    public ArrayList<String> getAnidados()
    {
        return elementosAnidados;
    }

    public String getNombre()
    {
        return containerName;
    }

    public boolean containsElemento(String texto)
    {
        boolean contiene = false;
        for (String el : elementosAnidados)
        {
            if (el.contains(texto))
            {
                contiene = true;
            }
        }
        return contiene;
    }

    protected Clima(Parcel in) {
        elementosAnidados = in.readArrayList(String.class.getClassLoader());
        containerName = in.readString();
    }

    public static final Creator<Clima> CREATOR = new Creator<Clima>() {
        @Override
        public Clima createFromParcel(Parcel in) {
            return new Clima(in);
        }

        @Override
        public Clima[] newArray(int size) {
            return new Clima[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(elementosAnidados);
        dest.writeString(containerName);
    }
}
