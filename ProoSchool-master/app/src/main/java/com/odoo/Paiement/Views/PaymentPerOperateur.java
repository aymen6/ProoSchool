package com.odoo.Paiement.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.odoo.R;

public class PaymentPerOperateur extends AppCompatActivity {
    private ImageView back;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_per_operateur);
         back=(ImageView)findViewById(R.id.BackToMenu);
         back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();

             }
         });


     }

    public void back(View view) {
        finish();

    }

    public void next(View view) {
        Toast.makeText(PaymentPerOperateur.this, "traitement entrain de construction",
                Toast.LENGTH_LONG).show();
    }
}
