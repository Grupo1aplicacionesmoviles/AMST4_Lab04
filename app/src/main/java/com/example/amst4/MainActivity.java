package com.example.amst4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irMenuPrincipal(View v){
        Intent menuPrincipal = new Intent(getBaseContext(), Menu.class);
        startActivity(menuPrincipal);
    }

}