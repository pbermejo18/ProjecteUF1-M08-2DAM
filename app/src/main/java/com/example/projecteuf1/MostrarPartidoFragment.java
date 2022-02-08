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
import com.example.projecteuf1.databinding.FragmentMostrarPartidoBinding;
import com.example.projecteuf1.databinding.FragmentPrimerBinding;


public class MostrarPartidoFragment extends Fragment {

    private FragmentMostrarPartidoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarPartidoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PartidosViewModel partidosViewModel = new ViewModelProvider(requireActivity()).get(PartidosViewModel.class);

        partidosViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Partido>() {
            @Override
            public void onChanged(Partido partido) {
                binding.escudoLocal.setImageResource(partido.escudo_local);
                binding.hora.setText(partido.hora);
                binding.escudoVisitante.setImageResource(partido.escudo_visitante);
            }
        });

    }
}