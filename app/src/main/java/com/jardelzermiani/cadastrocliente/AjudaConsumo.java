package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AjudaConsumo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajudaconsumo);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AjudaConsumo.this, Ajuda.class);
        startActivity(intent);
        finish();
    }
}
