package com.galgeleg.mibsen.galgeleg.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.galgeleg.mibsen.galgeleg.GameState;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Database(entities = {Score.class, Word.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ScoreDao scoreDao();

    public abstract WordDao wordDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "db-galgeleg")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);

                                    System.out.println("Adding demo data to the database");
                                    new AsyncTask() {
                                        @Override
                                        protected Object doInBackground(Object[] objects) {



                                            ArrayList<Score> scores = new ArrayList<>();
                                            for (int i = 1; i < 4; i++) {
                                                Score s = new Score();
                                                s.level = ThreadLocalRandom.current().nextInt(1, 30 + 1);
                                                s.score = ThreadLocalRandom.current().nextInt(100, 300 + 1);
                                                s.username = "User_"+i;
                                                scores.add(s);
                                            }

                                            getAppDatabase(context).scoreDao().insertAll((Score[]) scores.toArray(new Score[0]));

                                            ArrayList<Word> words = new ArrayList<Word>();

                                            for (int i = 1; i < 4; i++) {
                                                for (int a = 1; a < 4; a++) {
                                                    Word w = new Word();
                                                    w.scoreId=i;
                                                    w.word = "Word_"+a;
                                                    w.score = ThreadLocalRandom.current().nextInt(1, 30 + 1);
                                                    words.add(w);
                                                }
                                            }

                                            getAppDatabase(context).wordDao().insertAll( words.toArray(new Word[0]));
                                            System.out.println("ADDED Demo data");
                                            return null;
                                        }
                                    }.execute();
                                }
                            })
                            .build();



            //Room.inMemoryDatabaseBuilder(context.getApplicationContext(),AppDatabase.class).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}