package com.inacap.denunciaciudadanamuni.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.inacap.denunciaciudadanamuni.R;
import com.inacap.denunciaciudadanamuni.model.Denuncia;

public class RegistroDenunciaFragment extends Fragment {

    EditText  txt_titulo,txt_direccion;
    Button bt_guardar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro_denuncia, container, false);

        txt_titulo = view.findViewById(R.id.txt_denuncia_titulo);
        txt_direccion = view.findViewById(R.id.txt_denuncia_direccion);

        bt_guardar = view.findViewById(R.id.btn_denuncia_guardar);

        bt_guardar.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {

                String titulo = txt_titulo.getText().toString();
                String direccion = txt_direccion.getText().toString();
                String estado = "En espera";

                if (titulo.isEmpty() || direccion.isEmpty()) {

                    Toast.makeText(getActivity(), "uno ambos campos entan vacios ,por favor ingrese la informacion solicitada", Toast.LENGTH_LONG).show();

                } else {
                    //Guardar informacion en fireBase
                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Denuncias");


                    FirebaseAuth auth;//declaramos un auth para traer el uid del user
                    auth = FirebaseAuth.getInstance();//inicializamos auth

                    Denuncia nuevaDenuncia = new Denuncia();
                    nuevaDenuncia.setTitulo(titulo);
                    nuevaDenuncia.setDireccion(direccion);
                    nuevaDenuncia.setEstado(estado);

                    String uid = auth.getCurrentUser().getUid();//traemos el uid del user desde firebase
                    myRef.child(uid).push().setValue(nuevaDenuncia);//no olvidaR EL PUSH SI NO QUEDA LA K .Tambien agramos ellc child el cual crea un hijo dentro del objeto tarea
                    txt_titulo.setText("");
                    txt_direccion.setText("");
                    Toast.makeText(getActivity(), "Su denuncia ya esta realizada ,gracias vecino", Toast.LENGTH_LONG).show();

                }
            }
        });

        return view;
    }
}
