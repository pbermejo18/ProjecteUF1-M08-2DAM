package com.example.projecteuf1;

import android.app.Application;
import android.media.audiofx.DynamicsProcessing;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EquiposRepositorio {

    Executor executor = Executors.newSingleThreadExecutor();

    EquiposBaseDeDatos.EquiposDao equiposDao;

    EquiposRepositorio(Application application){
        equiposDao = EquiposBaseDeDatos.obtenerInstancia(application).obtenerEquiposDao();
    }

    LiveData<List<Equipo>> obtener(){
        return equiposDao.obtener();
    }

    void insertar(Equipo equipo){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                equiposDao.insertar(equipo);
            }
        });
    }

    void eliminar(Equipo equipo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                equiposDao.eliminar(equipo);
            }
        });
    }
/*
    public void actualizar(Equipo equipo, float valoracion) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                elemento.valoracion = valoracion;
                elementosDao.actualizar(elemento);
            }
        });
    }
 */
}
