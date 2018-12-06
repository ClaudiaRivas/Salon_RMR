package com.example.rivas.salon_rmr.Apputilities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rivas.salon_rmr.R;

public class DialogoContacto {


    public void showDialog(final Activity activity, final String numero,final String msg){
        final Dialog dialog = new Dialog(activity);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setTitle("Selecciona");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_contact);

        LinearLayout whatssap = dialog.findViewById(R.id.layoutDialogWhatsapp);
        LinearLayout llamada  = dialog.findViewById(R.id.layoutDialogLlamada);
        LinearLayout mensaje  = dialog.findViewById(R.id.layoutDialogMensaje);
        Button btnCancelar    = dialog.findViewById(R.id.btnCancelarDialogo);


        whatssap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact.sendWhatsAppMessage(activity,numero,msg);
            }
        });
        llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", numero, null)));
            }
        });

        mensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", numero);
                smsIntent.putExtra("sms_body",msg);
                activity.startActivity(smsIntent);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void sendSMS(String phoneNo, String msg) {

    }
}
