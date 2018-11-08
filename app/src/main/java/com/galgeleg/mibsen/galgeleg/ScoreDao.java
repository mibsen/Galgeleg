package com.galgeleg.mibsen.galgeleg;

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

    @Insert
    void insertAll(Score... scores);

}
