package com.example.projecteuf1;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import java.util.List;

@Database(entities = { Equipo.class }, version = 1, exportSchema = false)
public abstract class EquiposBaseDeDatos extends RoomDatabase {

    private static volatile EquiposBaseDeDatos INSTANCIA;

    static EquiposBaseDeDatos obtenerInstancia(final Context context) {
        if (INSTANCIA == null) {
            synchronized (EquiposBaseDeDatos.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context,
                            EquiposBaseDeDatos.class, "equipos.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCIA;
    }

    public abstract EquiposDao obtenerEquiposDao();

    @Dao
    interface EquiposDao {
        @Query("SELECT * FROM Equipo")
        LiveData<List<Equipo>> obtener();

        @Insert
        void insertar(Equipo equipo);

        @Update
        void actualizar(Equipo equipo);

        @Delete
        void eliminar(Equipo equipo);
    }
}