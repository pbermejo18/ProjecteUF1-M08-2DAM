package com.example.projecteuf1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class EquiposViewModel extends AndroidViewModel {

    EquiposRepositorio equiposRepositorio;

    MutableLiveData<List<Equipo>> listMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Equipo> equipoSelecionado = new MutableLiveData<>();

    public EquiposViewModel(@NonNull Application application) {
        super(application);

        equiposRepositorio = new EquiposRepositorio(application);

    }

    LiveData<List<Equipo>> obtener(){
        return equiposRepositorio.obtener();
    }

    void insertar(Equipo equipo){
        equiposRepositorio.insertar(equipo);
    }

    void eliminar(Equipo equipo){
        equiposRepositorio.eliminar(equipo);
    }
    void seleccionar(Equipo equipo){
        equipoSelecionado.setValue(equipo);
    }

    MutableLiveData<Equipo> seleccionado(){
        return equipoSelecionado;
    }
/*
    void actualizar(Elemento elemento, float valoracion){
        elementosRepositorio.actualizar(elemento, valoracion);
    }

 */
}
