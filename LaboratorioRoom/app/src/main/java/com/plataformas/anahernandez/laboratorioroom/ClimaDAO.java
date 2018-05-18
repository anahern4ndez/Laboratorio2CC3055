package com.plataformas.anahernandez.laboratorioroom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/** Ana Lucia Hernandez 17138
 * Esteban Cabrera 17781
 * Fernando Hengstenberg 17699
 * Raul Monzon 17014
 *Kevin Macario 17369
 * 08/05/2017
 * Laboratorio 2: Plataformas Moviles y Juegos
 * Reference: http://www.vogella.com/tutorials/AndroidSQLite/article.html
 *
 */
@Dao
public interface ClimaDAO {
    @Query("SELECT * FROM clima")
    List<Clima> getAll();

    @Insert
    void insertAll(List<Clima> tabla);

    @Query("DELETE FROM clima")
    void nukeTable();

}