package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AjudaGastoMonetario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajudagastomonetario);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AjudaGastoMonetario.this, Ajuda.class);
        startActivity(intent);
        finish();
    }
}
