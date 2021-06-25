package com.example.amst4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class EnvioTemperatura extends AppCompatActivity {

    private RequestQueue mQueue;
    private String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio_temperatura);
        mQueue = Volley.newRequestQueue(this);

        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
    }

    public void enviarTemperatura(View v){
        final EditText txt_temperatura = (EditText) findViewById(R.id.tx_input_temperatura);
        final String new_temp = txt_temperatura.getText().toString();

        Map<String,String> map_token = new HashMap<>();
        map_token.put("temp",new_temp);
        map_token.put("token",token);

        JSONObject tokenJSON = new JSONObject(map_token);

        String url_temp = "https://amst-labx.herokuapp.com/api/sensores/1";

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, url_temp,tokenJSON,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {

                            Context context = getApplicationContext();
                            CharSequence text = "Temperatura " + new_temp + " °C enviada a la base de datos!";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            //tempValue.setText(response.getString("temperatura") + " °C");
                        } catch (Exception e) {
                            Context context = getApplicationContext();
                            CharSequence text = "Error al enviar temperatura a la base de datos!";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                toast.show();

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

    public void salir(View v){
        this.finish();
        System.exit(0);
    }
}