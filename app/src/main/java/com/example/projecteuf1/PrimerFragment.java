package com.example.projecteuf1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.projecteuf1.databinding.FragmentPrimerBinding;
import com.example.projecteuf1.databinding.FragmentSegundoBinding;
import com.example.projecteuf1.databinding.ViewholderElementoBinding;
import com.example.projecteuf1.databinding.ViewholderPartidoBinding;

import java.util.List;


public class PrimerFragment extends Fragment {

    private FragmentPrimerBinding binding;
    private PartidosViewModel partidosViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentPrimerBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        partidosViewModel = new ViewModelProvider(requireActivity()).get(PartidosViewModel.class);
        navController = Navigation.findNavController(view);

        binding.irANuevoElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navController.navigate(R.id.action_segundoFragment_to_nuevoEquipoFragment);
            }
        });

        PrimerFragment.PartidosAdapter elementosAdapter = new PrimerFragment.PartidosAdapter();
        binding.recyclerView.setAdapter(elementosAdapter);

        partidosViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Partido>>() {
            @Override
            public void onChanged(List<Partido> partidos) {
                elementosAdapter.establecerLista(partidos);
            }
        });

    }

    static class PartidoViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderPartidoBinding binding;

        public PartidoViewHolder(ViewholderPartidoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class PartidosAdapter extends RecyclerView.Adapter<PrimerFragment.PartidoViewHolder> {

        List<Partido> partidos;

        @NonNull
        @Override
        public PrimerFragment.PartidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PrimerFragment.PartidoViewHolder(ViewholderPartidoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PrimerFragment.PartidoViewHolder holder, int position) {

            Partido partido = partidos.get(position);

            holder.binding.nombre.setText(partido.hora);

            Glide.with(PrimerFragment.this)
                    .load(partido.escudo_local)
                    .into(holder.binding.escudoLocal);
            Glide.with(PrimerFragment.this)
                    .load(partido.escudo_visitante)
                    .into(holder.binding.escudoVisitante);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    partidosViewModel.seleccionar(partido);
                    navController.navigate(R.id.action_primerFragment_to_mostrarPartidoFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return partidos != null ? partidos.size() : 0;
        }

        public void establecerLista(List<Partido> partidos){
            this.partidos = partidos;
            notifyDataSetChanged();
        }
    }
}