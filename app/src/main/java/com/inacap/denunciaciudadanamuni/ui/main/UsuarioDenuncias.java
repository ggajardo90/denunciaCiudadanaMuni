package com.inacap.denunciaciudadanamuni.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


import static android.content.ContentValues.TAG;


public class UsuarioDenuncias extends Fragment {
RecyclerView denuncia_rc;
List<Denuncia> lista;
FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_usuario_denuncias, container, false);

        denuncia_rc = view.findViewById(R.id.denuncias_rc);
        auth = FirebaseAuth.getInstance();
        final String uid = auth.getCurrentUser().getUid();

        lista = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Denuncias").child(uid);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               if (dataSnapshot.exists()){
                   lista.clear();
//                   txt.setText("");

                   for (DataSnapshot ds : dataSnapshot.getChildren()) {

                       Denuncia denuncia = ds.getValue(Denuncia.class);
                       denuncia.setId(ds.getKey());
                       lista.add(denuncia);
                   }



                   DenunciaAdapter adapter = new DenunciaAdapter(lista,getActivity(),R.layout.lista_denunciasuser);

                   LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                   layoutManager.setOrientation(RecyclerView.VERTICAL);

                   denuncia_rc.setLayoutManager(layoutManager);
                   denuncia_rc.setAdapter(adapter);
//
//
//                   }
               }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




        return  view;

    }
}