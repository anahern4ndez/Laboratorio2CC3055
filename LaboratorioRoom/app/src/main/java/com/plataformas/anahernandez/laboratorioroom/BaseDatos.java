package com.plataformas.anahernandez.laboratorioroom;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;

/**
 * Author Esteban Cabrera 17781
 */

@Database(entities = {Clima.class}, version = 16)

public abstract class BaseDatos extends RoomDatabase {
    private static BaseDatos instance;
    public abstract ClimaDAO dao();


    public static BaseDatos getDatabase(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context, BaseDatos.class,"weather").allowMainThreadQueries().build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}