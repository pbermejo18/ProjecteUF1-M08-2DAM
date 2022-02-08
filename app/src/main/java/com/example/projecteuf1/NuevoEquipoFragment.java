package com.example.projecteuf1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projecteuf1.databinding.FragmentNuevoEquipoBinding;


public class NuevoEquipoFragment extends Fragment {

    private FragmentNuevoEquipoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNuevoEquipoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EquiposViewModel equiposViewModel = new ViewModelProvider(requireActivity()).get(EquiposViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();
                String historia = binding.historia.getText().toString();

                if (nombre.equals("Barcelona") || nombre.equals("F.C Barcelona") || nombre.equals("F.C. Barcelona")) {
                    equiposViewModel.insertar(new Equipo(R.drawable.barcelona, nombre, historia));
                } else if (nombre.equals("Sevilla") || nombre.equals("Sevilla F.C") || nombre.equals("Sevilla F.C.")) {
                    equiposViewModel.insertar(new Equipo(R.drawable.sevilla, nombre, historia));
                } else if (nombre.equals("Real Sociedad")) {
                    equiposViewModel.insertar(new Equipo(R.drawable.realsociedad, nombre, historia));
                } else if (nombre.equals("Villarreal") || nombre.equals("Villarreal C.F") || nombre.equals("Villarreal C.F.")) {
                    equiposViewModel.insertar(new Equipo(R.drawable.villareal, nombre, historia));
                }

                navController.popBackStack();
            }
        });
    }
}