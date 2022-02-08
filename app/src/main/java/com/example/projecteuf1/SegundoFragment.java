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
import com.example.projecteuf1.databinding.FragmentSegundoBinding;
import com.example.projecteuf1.databinding.ViewholderElementoBinding;

import java.util.List;

public class SegundoFragment extends Fragment {

    private FragmentSegundoBinding binding;
    private EquiposViewModel equiposViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentSegundoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        equiposViewModel = new ViewModelProvider(requireActivity()).get(EquiposViewModel.class);
        navController = Navigation.findNavController(view);

        binding.irANuevoElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_segundoFragment_to_nuevoEquipoFragment);
            }
        });

        ElementosAdapter elementosAdapter = new ElementosAdapter();
        binding.recyclerView.setAdapter(elementosAdapter);

//****************************************
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Equipo equipo = elementosAdapter.equipos.remove(posicion);
                equiposViewModel.eliminar(equipo);

            }
        }).attachToRecyclerView(binding.recyclerView);

//****************************



        equiposViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Equipo>>() {
            @Override
            public void onChanged(List<Equipo> equipos) {
                elementosAdapter.establecerLista(equipos);
            }
        });

    }

    static class ElementoViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderElementoBinding binding;

        public ElementoViewHolder(ViewholderElementoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class ElementosAdapter extends RecyclerView.Adapter<ElementoViewHolder> {

        List<Equipo> equipos;

        @NonNull
        @Override
        public ElementoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ElementoViewHolder(ViewholderElementoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ElementoViewHolder holder, int position) {

            Equipo equipo = equipos.get(position);

            holder.binding.nombre.setText(equipo.nombre);
            Glide.with(SegundoFragment.this)
                    .load(equipo.image)
                    .into(holder.binding.escudo);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    equiposViewModel.seleccionar(equipo);
                    navController.navigate(R.id.action_segundoFragment_to_mostrarEquipoFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return equipos != null ? equipos.size() : 0;
        }

        public void establecerLista(List<Equipo> equipos){
            this.equipos = equipos;
            notifyDataSetChanged();
        }
    }
}