package com.galgeleg.mibsen.galgeleg.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ScoreDao {

    @Query("SELECT * FROM scores ORDER BY score DESC")
    List<Score> getAllOrderedByScore();

    @Query("SELECT * FROM scores ORDER BY level DESC")
    List<Score> getAllOrderedByLevel();

    @Query("SELECT * FROM scores where id = :id")
    public Score get(int id);

    @Insert
    long insert(Score score);

    @Insert
    void insertAll(Score... scores);

}
