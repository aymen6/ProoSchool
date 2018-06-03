package com.odoo.Paiement.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.odoo.R;

public class PayPerCard extends AppCompatActivity {
private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_per_card);

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
        Intent intent = new Intent(PayPerCard.this, IcPaiement.class);
        startActivity(intent);
    }
}
