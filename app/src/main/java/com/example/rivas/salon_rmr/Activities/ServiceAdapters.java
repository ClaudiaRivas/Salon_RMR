package com.example.rivas.salon_rmr.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rivas.salon_rmr.Model.Servicio;
import com.example.rivas.salon_rmr.R;

import java.util.List;

public class ServiceAdapters extends RecyclerView.Adapter<ServiceAdapters.ViewHolder>{
        private List<Servicio> servicios;
        Integer serviceImages [] = {R.drawable.service1, R.drawable.service2, R.drawable.service3, R.drawable.service4};
        private final Context ctx;

        public ServiceAdapters(List<Servicio> servicios, Context context){
            this.servicios = servicios;
            this.ctx = context;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service, parent , false);
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            final Servicio servicio = this.servicios.get(position);
            holder.txtNombre.setText(servicio.nombre);
            holder.txtPrecio.setText(servicio.precio.toString());
            holder.txtDescripcion.setText(servicio.descripcion);
            holder.serviceImage.setImageResource(serviceImages[position]);
            holder.rowServicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ctx, ReservationActivity.class);
                    intent.putExtra("nombre", servicio.nombre);
                    intent.putExtra("precio", servicio.precio.toString());
                    intent.putExtra("descripcion", servicio.descripcion);
                    intent.putExtra("imagen", serviceImages[position]);
                    ctx.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return this.servicios.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView txtNombre;
            private TextView txtPrecio;
            private TextView txtDescripcion;
            private ImageView serviceImage;
            private RelativeLayout rowServicio;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtNombre = itemView.findViewById(R.id.txtNombre);
                txtPrecio = itemView.findViewById(R.id.txtPrecio);
                txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
                serviceImage = itemView.findViewById(R.id.serviceImage);
                rowServicio = itemView.findViewById(R.id.rowServicio);
            }
        }
}
