package com.example.rivas.salon_rmr.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rivas.salon_rmr.Apputilities.FragmentConsultaFirebase;
import com.example.rivas.salon_rmr.Model.Item;
import com.example.rivas.salon_rmr.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends FragmentConsultaFirebase {

    View view;

    SliderLayout sliderLayout;

    private ImageSwitcher imageSwitcher;
    private int[] galeria = {R.drawable.p, R.drawable.p1, R.drawable.p2, R.drawable.p4};

    //firebase
    CollectionReference dbPromociones;
    //adaptador
    AdaptadorPromocion adaptadorPromocion;
    FragmentManager fragmentManager;

    public HomeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);


        //fragmentManager = getFragmentManager();
       //instancia al listview
        ListView list = (ListView) view.findViewById(R.id.listview);
        //hacer instancia a la bd en firebase
        dbPromociones = FirebaseFirestore.getInstance().collection("promociones");
        //crear adaptador
        adaptadorPromocion = new AdaptadorPromocion(getContext(),listaItems);
        //establecer el adaptador a la listview
        list.setAdapter(adaptadorPromocion);

        dbPromociones.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                actualizarDatos(queryDocumentSnapshots.getDocumentChanges());
            }
        });

        //para slider de imagenes
        sliderLayout =  view.findViewById(R.id.imageSwitcher);
        setSliderViews();

        return view;
    }

    @Override
    protected void actualizarDatos(List<DocumentChange> cambios) {
        super.actualizarDatos(cambios);
        adaptadorPromocion.notifyDataSetChanged();
    }

    private void setSliderViews() {

        for (int i = 0; i < galeria.length; i++) {
            //crear un elemento para el slider
            SliderView sliderView = new SliderView(getContext());
            //establecer la imagen
            sliderView.setImageDrawable(galeria[i]);
            //agregar el slider
            sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
            sliderLayout.addSliderView(sliderView);
        }
    }


    // Hacemos el metodo AdaptadorServicio
    private class AdaptadorPromocion extends ArrayAdapter<Item> {

        public AdaptadorPromocion(@NonNull Context context, @NonNull List<Item> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null)
                itemView = getLayoutInflater().inflate(R.layout.item_promociones, parent, false);


            //CurrentPromociones es la posicion en la que vamos a estar
            final Item item = listaItems.get(position);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.ImagenPromociones);
            imageView.setImageResource(R.drawable.service2);

            TextView TemaTxt = (TextView) itemView.findViewById(R.id.TxtTema);
            TemaTxt.setText(item.getNombre());

            TextView SubTemaTxt = (TextView) itemView.findViewById(R.id.TxtSubtema);
            SubTemaTxt.setText(item.getDescripcion());

            TextView PrecioTxt = (TextView) itemView.findViewById(R.id.TextPrecio);
            PrecioTxt.setText(item.getPrecio());

            RelativeLayout relativeLayout = itemView.findViewById(R.id.itemPromociones);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        //cargarFragmento(item);
                        return;

                }
            });
            return itemView;
        }



        private void cargarFragmento(Item item){

            if(fragmentManager==null) return;

            /*
            //Es el fragmento nuevo que quiero mostrar
            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setItem(item);
            //creo una transaccion de fragmentos
            FragmentManager transaction = fragmentManager;
            //iniciar la transaccion
            FragmentTransaction fragmentTransaction = transaction.beginTransaction();
            //reemplazar el fragmento actual con el nuevo
            fragmentTransaction.replace(R.id.fragmentHome, detailsFragment);
            //fragmentTransaction.addToBackStack(null);
            //guardar cambios
            fragmentTransaction.commit();
            */
        }


    }
}
