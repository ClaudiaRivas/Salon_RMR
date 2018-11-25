package com.example.rivas.salon_rmr.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rivas.salon_rmr.Apputilities.AdaptadorServicios;
import com.example.rivas.salon_rmr.Model.Servicio;
import com.example.rivas.salon_rmr.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ServiceFragment extends Fragment {


    //firebase
    CollectionReference dbServicio;
    //adaptador
    AdaptadorServicios adaptadorServicio;
    //lista
    private ArrayList<Servicio> listaServicio = new ArrayList<Servicio>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);

        //instancia al listview
        RecyclerView list = (RecyclerView) view.findViewById(R.id.listaServicios);
        list.setHasFixedSize(true);

        //creando el administrador del recyclerview

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(mLayoutManager);

        //hacer instancia a la bd en firebase
        dbServicio = FirebaseFirestore.getInstance().collection("servicios");
        //crear adaptador
        adaptadorServicio = new AdaptadorServicios(listaServicio,getContext());
        //establecer el adaptador a la listview
        list.setAdapter(adaptadorServicio);
        /**
         * Registra cambios en la bd mediante el evento Document Changed
         */
        dbServicio.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                actualizarDatos(queryDocumentSnapshots.getDocumentChanges());
            }
        });
        return view;
    }

    private void actualizarDatos(List<DocumentChange> cambios) {
        //para cada documento cambiado
        for (DocumentChange document_changed : cambios) {
            //obtengo el documento
            DocumentSnapshot document = document_changed.getDocument();
            //obtengo la posicion del producto basado en el Id
            int posicion = posicionServicio(document.getId());
            //si el documento fue eliminado
            if (document_changed.getType() == DocumentChange.Type.REMOVED) {
                //se elimina de la lista tambien
                listaServicio.remove(posicion);
            } else {
                //obtengo un objeto servicio del documento
                Servicio servicio = getServicio(document);
                //si la posicion es mayor a cero es por que existe en la lista y se actualiza
                if (posicion >= 0) {
                    listaServicio.set(posicion, servicio);
                } else {
                    //si no , es por que es un elemento nuevo
                    listaServicio.add(servicio);
                }
            }
            Log.d("LISTA FIREBASE SERVICIO", "Actualizada " + document.getId() + " " + document.getData().values());
        }
        //notifico al adaptador de los cambios
        adaptadorServicio.notifyDataSetChanged();
        Toast.makeText(getContext(), "Datos actualizados", Toast.LENGTH_SHORT).show();
    }

    private Servicio getServicio(DocumentSnapshot document) {
        Servicio servicio = new Servicio();
        //establecer parametros
        servicio.setId(document.getId());
        servicio.setNombre(document.getString("nombre"));
        servicio.setDescripcion(document.getString("descripcion"));
        servicio.setPrecio(document.getString("precio"));
        return servicio;
    }

    private int posicionServicio(String id) {
        for (Servicio servicio : listaServicio) {
            if (servicio.getId().equals(id)) {
                return listaServicio.indexOf(servicio);
            }
        }
        return -1;
    }


}
