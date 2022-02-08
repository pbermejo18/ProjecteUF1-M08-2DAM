package com.example.projecteuf1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Equipo {

    @PrimaryKey(autoGenerate = true)
    int id;

    int image;
    String nombre;
    String historia;

    public Equipo(int image, String nombre, String historia) {
        this.image = image;
        this.nombre = nombre;
        this.historia = historia;
    }
}
