package com.example.amst4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irMenuPrincipal(View v){
        final EditText usuario = (EditText) findViewById(R.id.tx_usuario);
        final EditText password = (EditText) findViewById(R.id.tx_pass);
        String str_usuario = usuario.getText().toString();
        String str_password = password.getText().toString();
        iniciarSesion(str_usuario, str_password);

    }

    public void toView_MenuPrincipal(String token){//Enviando el token a traves de las vistas Main->Menu
        Intent menuPrincipal = new Intent(getBaseContext(), Menu.class);
        menuPrincipal.putExtra("token", token);
        startActivity(menuPrincipal);
    }

    public void iniciarSesion(String usuario,String password){
        Map<String,String> param = new HashMap<>();
        param.put("username",usuario);
        param.put("password",password);
        JSONObject parametros = new JSONObject(param);

        String login_url = "https://amst-labx.herokuapp.com/db/nuevo-jwt";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, login_url, parametros,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            String token = response.getString("token");
                            toView_MenuPrincipal(token);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("Alerta!");
                        alertDialog.setMessage("Usuario o contraseña incorrecta");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int
                                            which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        }
                    });
                    mQueue.add(request);
        }
    }