package com.odoo.Document;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.odoo.R;

public class DemandeDoc extends AppCompatActivity {
Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_doc);

spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setPrompt("type de document choisi ");

spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = spinner.getSelectedItem().toString();
        Object item = parent.getItemAtPosition(position);
        Toast.makeText(DemandeDoc.this, "choix "+item.toString(), Toast.LENGTH_SHORT).show();

        //Toast.makeText()

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});


    }

    public void back(View view) {
    }
}
