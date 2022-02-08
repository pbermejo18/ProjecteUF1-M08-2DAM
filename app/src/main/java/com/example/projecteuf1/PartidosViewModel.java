package com.example.projecteuf1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PartidosViewModel extends AndroidViewModel {

    PartidosRepositorio partidosRepositorio;

    MutableLiveData<List<Partido>> listMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Partido> partidoselecionado = new MutableLiveData<>();

    public PartidosViewModel(@NonNull Application application) {
        super(application);

        partidosRepositorio = new PartidosRepositorio();

        listMutableLiveData.setValue(partidosRepositorio.obtener());
    }

    MutableLiveData<List<Partido>> obtener(){
        return listMutableLiveData;
    }

    void seleccionar(Partido partido){
        partidoselecionado.setValue(partido);
    }

    MutableLiveData<Partido> seleccionado(){
        return partidoselecionado;
    }
}
