package com.galgeleg.mibsen.galgeleg.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM words WHERE scoreID = :score ORDER BY id DESC")
    List<Word> getWords(int score);

    @Insert
    long insert(Word word);

    @Insert
    void insertAll(Word... words);

}
