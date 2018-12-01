package com.example.rivas.salon_rmr.Fragment;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rivas.salon_rmr.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ContactFragment extends Fragment {

    View view;

    DocumentReference mDocRef = FirebaseFirestore.getInstance().document("informacion/contacto");
    TextView txtHorarioLunes_Viernes,txtHorarioSabado,txtWhatsapp, txtinformacion,txtFacebook,txtInstagram, txtHorarioDomingo, txtIntegrante1, txtIntegrante2, txtIntegrante3,txtCorreoIntegrante,txtdireccion;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_contact, container, false);


        txtHorarioLunes_Viernes = (TextView) view.findViewById(R.id.txtHorarioLunes_Viernes);
        txtHorarioSabado = (TextView) view.findViewById(R.id.txtHorarioSabado);
        txtHorarioDomingo = (TextView) view.findViewById(R.id.txtHorarioDomingo);
        txtWhatsapp = (TextView) view.findViewById(R.id.txtWhatsapp);
        txtFacebook = (TextView) view.findViewById(R.id.txtFacebook);
        txtInstagram = (TextView) view.findViewById(R.id.txtInstagram);
        txtCorreoIntegrante = (TextView) view.findViewById(R.id.txtCorreoIntegrante);
        txtdireccion = (TextView) view.findViewById(R.id.txtdireccion);
        txtIntegrante1 = (TextView) view.findViewById(R.id.txtIntegrante1);
        txtIntegrante2 = (TextView) view.findViewById(R.id.txtIntegrante2);
        txtIntegrante3 = (TextView) view.findViewById(R.id.txtIntegrante3);
        txtinformacion = (TextView) view.findViewById(R.id.txtinformacion);


        mDocRef.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    actualizar(documentSnapshot);
                }
            }
        });
        return view;
    }

    private void actualizar(DocumentSnapshot doc){
        txtHorarioLunes_Viernes.setText( "Lunes a viernes : "+doc.getString("horarios_lunes_viernes")  );
        txtHorarioSabado.setText( "Sábado : "+ doc.getString("horarios_sabado")  );
        txtHorarioDomingo.setText( "Domingo : "+ doc.getString("horarios_domingo")  );
        txtWhatsapp.setText( doc.getString("whatsapp")  );
        txtFacebook.setText( doc.getString("facebook")  );
        txtInstagram.setText( doc.getString("instagram")  );
        txtIntegrante1.setText( doc.getString("integrante1")  );
        txtIntegrante2.setText( doc.getString("integrante2")  );
        txtIntegrante3.setText( doc.getString("integrante3")  );
        txtdireccion.setText( doc.getString("direccion")  );
        txtCorreoIntegrante.setText( doc.getString("correo_integrante")  );
        txtinformacion.setText( doc.getString("informacion_salon")  );
    }


}
