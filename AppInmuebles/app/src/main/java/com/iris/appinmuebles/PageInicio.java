package com.iris.appinmuebles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageInicio extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_inicio);
        loadComponents();
    }

    private void loadComponents() {
        Button login = findViewById(R.id.buttonLogin);
        Button register = findViewById(R.id.buttonRegister);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:{
                Intent activityLogin = new Intent(this, LoginActivity.class );
                activityLogin.putExtra("msn", "Bienvenido");
                startActivity(activityLogin);
                break;

            }
            case R.id.buttonRegister: {
                Intent activityRegister = new Intent(this, RegisterActivity.class );
                activityRegister.putExtra("msn", "Bienvenido");
                startActivity(activityRegister);
                break;
            }
        }
    }
}
