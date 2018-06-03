package com.odoo.MenuAppAfter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.odoo.R;

public class PageNotFound extends AppCompatActivity {
    private ImageView backtomenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_not_found);
        backtomenu=(ImageView)findViewById(R.id.backtomenu);
        backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
