package com.example.gym_polyakov.DataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Users {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String username;

    public String password;

    public String email;

    public double weight;

    public double height;

    public boolean gender;
}
