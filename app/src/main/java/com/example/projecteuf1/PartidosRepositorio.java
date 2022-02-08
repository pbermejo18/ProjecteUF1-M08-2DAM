package com.example.projecteuf1;

import java.util.ArrayList;
import java.util.List;

public class PartidosRepositorio {
    List<Partido> partidos = new ArrayList<>();

    PartidosRepositorio(){
        partidos.add(new Partido(R.drawable.barcelona,"16:00", R.drawable.sevilla));
        partidos.add(new Partido(R.drawable.realsociedad, "18:45", R.drawable.villareal));
        partidos.add(new Partido(R.drawable.sevilla,"21:00",R.drawable.realsociedad));
    }

    List<Partido> obtener() {
        return partidos;
    }
}
