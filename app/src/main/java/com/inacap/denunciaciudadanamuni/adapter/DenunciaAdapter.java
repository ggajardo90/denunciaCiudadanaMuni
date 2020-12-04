package com.inacap.denunciaciudadanamuni.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inacap.denunciaciudadanamuni.R;
import com.inacap.denunciaciudadanamuni.model.Denuncia;

import java.util.List;

public class DenunciaAdapter extends RecyclerView.Adapter<DenunciaAdapter.DenunciaHolder> {
        public List<Denuncia> lista;
        public int layout;
        Activity activity;

    public DenunciaAdapter(List<Denuncia> lista, Activity activity, int layout) {
        this.lista = lista;
        this.activity = activity;
        this.layout = layout;
    }


    @NonNull
    @Override
    public DenunciaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);

        return new DenunciaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DenunciaHolder holder, int position) {
        Denuncia denuncia = lista.get(position);
        holder.id = denuncia.getId();
        holder.titulo_denuncia.setText(denuncia.getTitulo());
        holder.direccion_denuncia.setText(denuncia.getDireccion());
        holder.estado_denuncia.setText(denuncia.getEstado());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class DenunciaHolder extends RecyclerView.ViewHolder{
    String id;
    TextView titulo_denuncia,direccion_denuncia,estado_denuncia;

        public DenunciaHolder(@NonNull View itemView) {
            super(itemView);

            titulo_denuncia = itemView.findViewById(R.id.lista_titulo_denuncia);
            direccion_denuncia   = itemView.findViewById(R.id.lista_direccion_denuncia);
            estado_denuncia = itemView.findViewById(R.id.lista_estado_denuncia);
        }
    }


}
