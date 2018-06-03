package com.odoo.Paiement.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.odoo.R;

public class Payment_method extends AppCompatActivity  implements View.OnClickListener{
    private LinearLayout americanExpress, visa, mastercard;
    public ImageView rightmark1,rightmark2,rightmark3,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        back=(ImageView)findViewById(R.id.BackToMenu);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        americanExpress = (LinearLayout) findViewById(R.id.americanExpress);
        visa = (LinearLayout) findViewById(R.id.visa);
        mastercard = (LinearLayout) findViewById(R.id.mastercard);
        rightmark1 = (ImageView) findViewById(R.id.rightmark1);
        rightmark2 = (ImageView) findViewById(R.id.rightmark2);
        rightmark3 = (ImageView) findViewById(R.id.rightmark3);

        americanExpress.setOnClickListener(this);
        visa.setOnClickListener(this);
        mastercard.setOnClickListener(this);


    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.americanExpress:

                Intent intent = new Intent(Payment_method.this,PaymentPerOperateur.class);
                startActivity(intent);
                rightmark1.setImageResource(R.drawable.ic_right);
                rightmark2.setImageResource(R.drawable.ic_round);
                rightmark3.setImageResource(R.drawable.ic_round);
                break;

            case R.id.visa:
                Intent intent1 = new Intent(Payment_method.this,PayPerCard.class);
                intent1.putExtra("card", '1');

                startActivity(intent1);
                rightmark1.setImageResource(R.drawable.ic_round);
                rightmark2.setImageResource(R.drawable.ic_right);
                rightmark3.setImageResource(R.drawable.ic_round);
                break;

            case R.id.mastercard:
                Intent intent2 = new Intent(Payment_method.this,PayPerCard.class);
                intent2.putExtra("card", "2");

                startActivity(intent2);

                rightmark1.setImageResource(R.drawable.ic_round);
                rightmark2.setImageResource(R.drawable.ic_round);
                rightmark3.setImageResource(R.drawable.ic_right);
                break;



        }
    }

    private void changeBackground(int i, LinearLayout americanExpress) {
    }

    public void back(View view) {
        finish();

    }
}
