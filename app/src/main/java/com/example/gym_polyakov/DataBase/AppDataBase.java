package com.example.gym_polyakov.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract Users_Dao users_dao();
}
