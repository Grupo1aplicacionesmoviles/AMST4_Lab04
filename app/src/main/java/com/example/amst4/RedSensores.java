package com.example.amst4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RedSensores extends AppCompatActivity {

    private RequestQueue mQueue;
    private String token = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_sensores);
        mQueue = Volley.newRequestQueue(this);

        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
        revisarSensores();

    }

    public void presentarTemperatura(){
        final TextView tempValue = (TextView) findViewById(R.id.textView_val_temp);
        String url_temp = "https://amst-labx.herokuapp.com/api/sensores/1";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url_temp, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            tempValue.setText(response.getString("temperatura") + " °C");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "JWT " + token);
                System.out.println(token);
                return params;
            }
        };
        this.mQueue.add(request);

    }

    public void presentarHumedad(){
        final TextView humedadValue = (TextView)findViewById(R.id.textView_val_humedad);
        String url_humedad ="https://amst-labx.herokuapp.com/api/sensores/2";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url_humedad, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            humedadValue.setText(response.getString("humedad")+ " %");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "JWT " + token);
                System.out.println(token);
                return params;
            }
        };
        this.mQueue.add(request);

    }

    public void presentarPeso(){
        final TextView pesoValue = (TextView) findViewById(R.id.textView_val_peso);
        String url_peso = "https://amst-labx.herokuapp.com/api/sensores/3";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url_peso, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            pesoValue.setText(response.getString("peso")+ " g");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "JWT " + token);
                System.out.println(token);
                return params;
            }
        };
        this.mQueue.add(request);

    }

    private void revisarSensores() {
        presentarTemperatura();
        presentarHumedad();
        presentarPeso();
    }

    public void toView_MenuPrincipal(View v){
        Intent menuPrincipal = new Intent(getBaseContext(), Menu.class);
        menuPrincipal.putExtra("token", this.token);//SI NO MANDO EL TOKEN SE CRASHEA
        startActivity(menuPrincipal);
    }

}