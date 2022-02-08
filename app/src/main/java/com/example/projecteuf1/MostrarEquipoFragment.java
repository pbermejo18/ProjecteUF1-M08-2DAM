package com.example.projecteuf1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projecteuf1.databinding.FragmentMostrarEquipoBinding;


public class MostrarEquipoFragment extends Fragment {

    private FragmentMostrarEquipoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarEquipoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EquiposViewModel equiposViewModel = new ViewModelProvider(requireActivity()).get(EquiposViewModel.class);

        equiposViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Equipo>() {
            @Override
            public void onChanged(Equipo equipo) {
                binding.escudo.setImageResource(equipo.image);
                binding.nombre.setText(equipo.nombre);
                binding.historia.setText(equipo.historia);
            }
        });

    }
}