package com.plataformas.anahernandez.laboratorioroom;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;

/** Ana Lucia Hernandez 17138
 * Esteban Cabrera 17781
 * Fernando Hengstenberg 17699
 * Raul Monzon 17014
 *Kevin Macario 17369
 * 08/05/2017
 * Laboratorio 2: Plataformas Moviles y Juegos
 *
 */

@Database(entities = {Clima.class}, version = 16)

public abstract class BaseDatos extends RoomDatabase {
    private static BaseDatos instance;
    public abstract ClimaDAO dao();


    public static BaseDatos getDatabase(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context, BaseDatos.class,"clima")
                    // allow queries on the main thread.
                    .fallbackToDestructiveMigration()
                    //.allowMainThreadQueries()
                    .build();

        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}
