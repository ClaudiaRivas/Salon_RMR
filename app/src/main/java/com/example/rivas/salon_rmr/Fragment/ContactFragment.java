package com.example.rivas.salon_rmr.Fragment;



import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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
import android.widget.Toast;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ContactFragment extends Fragment {

    View view;

    DocumentReference mDocRef = FirebaseFirestore.getInstance().document("informacion/contacto");
    TextView txtHorarioLunes_Viernes,txtHorarioSabado,txtWhatsapp, txtinformacion,txtFacebook,txtInstagram, txtHorarioDomingo, txtIntegrante1, txtIntegrante2, txtIntegrante3,txtCorreoIntegrante,txtdireccion;
    CardView cardViewWhatsapp,cardViewFacebook,cardViewInstagram;

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

        cardViewWhatsapp = (CardView)view.findViewById(R.id.cardViewWhatsapp);
        cardViewFacebook = (CardView)view.findViewById(R.id.cardViewFacebook);
        cardViewInstagram = (CardView)view.findViewById(R.id.cardViewInstagram);


        cardViewWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO pendiente referencia
                Toast.makeText(getContext(), "Whatsapp", Toast.LENGTH_SHORT).show();
            }
        });

        cardViewFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ESO VA EN EL CARDVIEW DEL FACEBOOK
                //TODO pendiente referencia
                String facebookId = "fb://page/244840368866574";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId )));
            }
        });

        cardViewInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ESTO EN EL CARDVIEW DEL INSTAGRAM
                //TODO pendiente referencia
                //Toast.makeText(getContext(), "Instagram", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("http://instagram.com/milideescobar/?utm_source=ig_profile_share&igshid=1pngwgpz4nis8");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/milideescobar/?utm_source=ig_profile_share&igshid=1pngwgpz4nis8")));
                }
            }
        });


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
