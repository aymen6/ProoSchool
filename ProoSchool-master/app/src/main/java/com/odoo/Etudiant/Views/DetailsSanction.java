package com.odoo.Etudiant.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.odoo.R;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.orm.fields.OColumn;

public class DetailsSanction extends AppCompatActivity {
    private ImageView back;
    private TextView name_student,name_student1,dateSanction,Type,Titre,message;
    private ODataRow record = null;
    private Bundle extras;
    private ResPartner resPartner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_sanction);

        extras = getIntent().getExtras();
        String name= String.valueOf(extras.get("student_name"));
        String state= String.valueOf(extras.get("state"));
        String date= String.valueOf(extras.get("date"));
        String type= String.valueOf(extras.get("type"));
        String title= String.valueOf(extras.get("Title"));
        String Message= String.valueOf(extras.get("message"));



       name_student=(TextView)findViewById(R.id.student_name);
       dateSanction=(TextView)findViewById(R.id.Datesanction);
        Type=(TextView)findViewById(R.id.Type);
        Titre=(TextView)findViewById(R.id.Titre);
        message=(TextView)findViewById(R.id.message);
        back=(ImageView) findViewById(R.id.back);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        name_student1=(TextView)findViewById(R.id.student_name1);
        name_student1.setText(name);

        name_student.setText(name);
        dateSanction.setText(date);
        Type.setText(type);
        Titre.setText(title);
        message.setText(Message);
        message.setMovementMethod(new ScrollingMovementMethod());

    }
    private boolean hasRecordInExtra() {
        return extras != null && extras.containsKey(OColumn.ROW_ID);

    }



    public void BackTo(View view) {
         finish();
    }


}
