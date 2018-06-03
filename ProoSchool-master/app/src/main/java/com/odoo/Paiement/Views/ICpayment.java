package com.odoo.Paiement.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.odoo.R;

public class ICpayment extends AppCompatActivity {
    public TextView payment_method,payment_details;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icpayment);
        back=(ImageView)findViewById(R.id.BackToMenu);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }


    public void next(View view) {
        Intent intent = new Intent(ICpayment.this,Payment_method.class);
        startActivity(intent);
    }
}
