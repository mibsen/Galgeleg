package com.galgeleg.mibsen.galgeleg.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "words", foreignKeys = @ForeignKey(entity = Score.class,
        parentColumns = "id",
        childColumns = "scoreId",
        onDelete = CASCADE))
public class Word {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int scoreId;

    @ColumnInfo(name = "word")
    public String word;

    @ColumnInfo(name = "score")
    public int score;

    @Override
    public String toString() {
        return word;
    }
}
