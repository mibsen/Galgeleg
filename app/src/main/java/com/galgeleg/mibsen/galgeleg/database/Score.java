package com.galgeleg.mibsen.galgeleg.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "scores")
public class Score {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "level")
    public int level;

    @ColumnInfo(name = "score")
    public long score;
}
