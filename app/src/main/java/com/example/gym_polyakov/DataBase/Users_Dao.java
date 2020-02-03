package com.example.gym_polyakov.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Users_Dao {
    @Query("SELECT * FROM Users WHERE username = :username AND password = :password")
    Users getUser(String username, String password);

    @Query("SELECT * FROM Users")
    List<Users> getAll();

    @Insert
    void insert(Users users);

    @Update
    void update(Users users);

    @Delete
    void delete(Users users);
}
