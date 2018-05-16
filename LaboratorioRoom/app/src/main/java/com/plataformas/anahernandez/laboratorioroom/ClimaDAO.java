package com.plataformas.anahernandez.laboratorioroom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Author Raúl Monzon 17014
 * Reference: http://www.vogella.com/tutorials/AndroidSQLite/article.html
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