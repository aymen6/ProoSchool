package com.odoo.HistoryPay.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.odoo.R;

public class HistoryPayActivity extends AppCompatActivity {
private ImageView backtomenu;
    private TextView student_namex,Parentx,classStudentx,montant2x,stateofx,montanttxtx,facturex;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_history_pay);
        backtomenu=(ImageView)findViewById(R.id.backtomenu);
        backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        extras = getIntent().getExtras();
        String student_name= String.valueOf(extras.get("student_name"));
        String parent_name= String.valueOf(extras.get("parent_id"));
        String ClassStudent= String.valueOf(extras.get("ClassStudent"));
        String state= String.valueOf(extras.get("state"));
        String titre_order= String.valueOf(extras.get("Title"));
        String montant_paye= String.valueOf(extras.get("montant"));

        student_namex=(TextView)findViewById(R.id.studentname);
        student_namex.setText(student_name);

      /*  Parentx=(TextView)findViewById(R.id.Parent);
        Parentx.setText(parent_name);
*/
      /*  classStudentx=(TextView)findViewById(R.id.classStudent);
        classStudentx.setText(ClassStudent);
*/
    /*  montant2x=(TextView)findViewById(R.id.montant2);
        montant2x.setText(montant_paye);
*/
   /*     stateofx=(TextView)findViewById(R.id.stateof);
        stateofx.setText(state);*/
/*
        montanttxtx=(TextView)findViewById(R.id.montanttxt);
        montanttxtx.setText(montant_paye);
*/
        facturex=(TextView)findViewById(R.id.facture);
        facturex.setText(titre_order);



    }
}
