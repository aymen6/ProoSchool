package com.odoo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.odoo.core.account.OdooLogin;
import com.odoo.core.support.OUser;

public class SplashScreen extends AppCompatActivity
{
        private static int SPLASH_TIME_OUT = 5000;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_splash_screen);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!checkLogin()) {
                        Intent intent = new Intent(SplashScreen.this, OdooLogin.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(SplashScreen.this, "access denied", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SplashScreen.this, "access to enter", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SplashScreen.this, OdooActivity.class);
                        startActivity(intent);
                        finish();
                        }

                    }

            },SPLASH_TIME_OUT);

        }

        private boolean checkLogin() {
            OUser user = OUser.current(this);







            if(user!=null){
                return true;
            }else{
                return false;
            }
        }
}
