package com.iris.appinmuebles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class RegisterActivity extends AppCompatActivity {
    ArrayList<String> tipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setSpinner();
        Button buttonRegister = findViewById(R.id.register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"registrando usuario", Toast.LENGTH_SHORT).show();
                sendData();
            }
        });
    }
    private void sendData(){
        EditText names = findViewById(R.id.name);
        EditText emails = findViewById(R.id.email);
        EditText passwords = findViewById(R.id.password);
        Spinner usertypes = findViewById(R.id.typeUser);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("name", names.getText().toString() );
        params.add("password", passwords.getText().toString() );
        params.add("email", emails.getText().toString() );
        params.add("rols", tipoUsuario.get(usertypes.getSelectedItemPosition()) );

        client.post(Utils.REGISTER_SERVICE, params, new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                //super.onFailure(statusCode, headers, throwable, errorResponse);
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                //super.onSuccess(statusCode, headers, response);
                if(response.has("rols")){
                    Toast.makeText(RegisterActivity.this, "Usuario registrado con exito", Toast.LENGTH_LONG).show();
                    Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(login);
                }
            }

           /* @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //super.onSuccess(statusCode, headers, response);
            }*/
        });
    }


    private void setSpinner() {
        tipoUsuario = new ArrayList<>();
        tipoUsuario.add("Administrador");
        tipoUsuario.add("Cliente");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tipoUsuario);
        Spinner spinner = findViewById(R.id.typeUser);
        spinner.setAdapter(adapter);
        //spinner.setOnFocusChangeListener();
        //spinner.getSelectedItemPosition();
    }
}
