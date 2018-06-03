package com.odoo.Avis.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.odoo.R;

public class DetailsAvisAuParent extends AppCompatActivity {
    private TextView name_student,name_student1,date_Avis,class_student,Title,message;
    private Bundle extras;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_avis_au_parent);
        extras = getIntent().getExtras();
        String name= String.valueOf(extras.get("student_name"));
        String date= String.valueOf(extras.get("date"));
        String title= String.valueOf(extras.get("Title"));
        String Message= String.valueOf(extras.get("message"));
        String ClassStudent= String.valueOf(extras.get("ClassStudent"));



        back=(ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        name_student=(TextView)findViewById(R.id._nameStudent);
        name_student1=(TextView)findViewById(R.id._nameStudent1);

        date_Avis=(TextView)findViewById(R.id._dateAvis);
        Title=(TextView)findViewById(R.id._title);
        message=(TextView)findViewById(R.id._message);
        class_student=(TextView)findViewById(R.id._ClassStudent);


      name_student.setText(name);
        name_student1.setText(name);

        date_Avis.setText(date);
       class_student.setText(ClassStudent);
        Title.setText(title);
        message.setText(Message);



    }
}
