package com.example.rivas.salon_rmr.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rivas.salon_rmr.Apputilities.AdaptadorProductos;
import com.example.rivas.salon_rmr.Apputilities.AdaptadorServicios;
import com.example.rivas.salon_rmr.Apputilities.FragmentConsultaFirebase;
import com.example.rivas.salon_rmr.Apputilities.ItemClickSupport;
import com.example.rivas.salon_rmr.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


public class ServiceFragment extends FragmentConsultaFirebase{

    //firebase
    CollectionReference dbServicio;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);


        //instancia al listview
        RecyclerView list = (RecyclerView) view.findViewById(R.id.listaServicios);
        list.setHasFixedSize(true);

        adaptadorItems = new AdaptadorServicios(listaItems,getContext());

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(mLayoutManager);

        //hacer instancia a la bd en firebase
        dbServicio = FirebaseFirestore.getInstance().collection("servicios");

        //establecer el adaptador a la listview
        list.setAdapter(adaptadorItems);
        /**
         * Registra cambios en la bd mediante el evento Document Changed
         */
        dbServicio.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                actualizarDatos(queryDocumentSnapshots.getDocumentChanges());
            }
        });

        ItemClickSupport.addTo(list).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                cargarFragmento(position);
            }
        });
        return view;
    }

    private void cargarFragmento(int i){
        if (mFragmentNavigation != null) {
            DetailsFragment mFragment = new DetailsFragment();
            mFragment.setItem( listaItems.get(i) );
            mFragmentNavigation.pushFragment(mFragment);
        }
    }

}
