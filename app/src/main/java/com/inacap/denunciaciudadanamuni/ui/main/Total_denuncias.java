package com.inacap.denunciaciudadanamuni.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.inacap.denunciaciudadanamuni.R;
import com.inacap.denunciaciudadanamuni.adapter.DenunciaAdapter;
import com.inacap.denunciaciudadanamuni.model.Denuncia;

import java.util.ArrayList;
import java.util.List;


public class Total_denuncias extends Fragment {
    RecyclerView denuncias_total_rc;
    List<Denuncia> lista;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_total_denuncias, container, false);
        denuncias_total_rc = view.findViewById(R.id.total_denuncia_rc);
        auth = FirebaseAuth.getInstance();
         String uid = auth.getCurrentUser().getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Denuncias");

        lista = new ArrayList<>();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    lista.clear();
//                   txt.setText("");
                    for(DataSnapshot padre : dataSnapshot.getChildren()) {
                        for (DataSnapshot ds : padre.getChildren()) {
                            Denuncia denuncia = ds.getValue(Denuncia.class);
                            denuncia.setId(ds.getKey());
                            lista.add(denuncia);
                        }
                    }

                    DenunciaAdapter adapter = new DenunciaAdapter(lista,getActivity(),R.layout.lista_total_denuncias);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(RecyclerView.VERTICAL);
                    denuncias_total_rc.setLayoutManager(layoutManager);
                    denuncias_total_rc.setAdapter(adapter);
//
//
//                   }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(getTag(), "Failed to read value.", error.toException());
            }
        });


        return  view;
    }
}