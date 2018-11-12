package com.example.rivas.salon_rmr.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rivas.salon_rmr.Activities.ServiceAdapters;
import com.example.rivas.salon_rmr.Model.Promociones;
import com.example.rivas.salon_rmr.Model.Servicio;
import com.example.rivas.salon_rmr.R;
import com.example.rivas.salon_rmr.Apputilities.AppConstants;
import com.example.rivas.salon_rmr.retrofitinterfaces.ServicesInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ServiceFragment extends Fragment {

    private ServiceAdapters adapter;
    public RecyclerView recyclerView;
    private ProgressDialog progress;
    List<Servicio> tmpServicios;
    public ServiceFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.servicesRecyclerView);
        getServices();
        return view;
    }
    private void getServices(){
        progress = ProgressDialog.show(getContext(), "Cargando...", "Espere por favor");
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppConstants.SERVICES_JSON).
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        ServicesInterface servicesInterface = retrofit.create(ServicesInterface.class);
        Call<List<Servicio>> call = servicesInterface.getServices();
        call.enqueue(new Callback<List<Servicio>>(){
            @Override
            public void onResponse(Call<List<Servicio>> call, Response<List<Servicio>> response) {
                Log.d("response", response.body().toString()); //response.body() Obtiene la respuesta del JSON en linea
                tmpServicios = response.body();
                updateAdapter(tmpServicios);
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<List<Servicio>> call, Throwable t) {
                Log.d("error", t.getMessage());
                progress.dismiss();
            }
        });
    }

    private void updateAdapter(List<Servicio> servicios){
        adapter = new ServiceAdapters(servicios, getActivity());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }
}
