package com.example.amst4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class Menu extends AppCompatActivity {

    String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
    }


    public void salir(View v){//Comportamiento extraño, no siempre redirige al main
        this.finish();
        System.exit(0);
    }

    public void toView_Main(View v){
        Intent main = new Intent(getBaseContext(),MainActivity.class);
        startActivity(main);
    }

    public void toView_RedSensores(View v){
        Intent red_sensores = new Intent(getBaseContext(),RedSensores.class);
        red_sensores.putExtra("token", this.token);
        startActivity(red_sensores);
    }

}