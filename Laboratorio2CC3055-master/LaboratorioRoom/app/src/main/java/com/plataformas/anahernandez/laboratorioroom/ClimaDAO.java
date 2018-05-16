package com.plataformas.anahernandez.laboratorioroom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Author Raúl Monzon 17014
 * Esteban Cabrera 17781
 * Kevin Macario 17369
 * Raúl Monzon 17014
 * Reference: http://www.vogella.com/tutorials/AndroidSQLite/article.html
 */
@Dao
public interface ClimaDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertWeather(Clima descripcion);

    @Query("SELECT * FROM weather WHERE id=:id")
        List<Clima> getDescriptionById(int id);

}