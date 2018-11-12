package com.example.rivas.salon_rmr.retrofitinterfaces;



import com.example.rivas.salon_rmr.Model.Servicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicesInterface {
    @GET("bins/12wqn8")
    Call<List<Servicio>> getServices();

}
