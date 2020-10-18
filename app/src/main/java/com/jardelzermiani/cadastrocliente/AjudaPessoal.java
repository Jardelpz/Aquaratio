package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AjudaPessoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajudapessoal);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AjudaPessoal.this, Ajuda.class);
        startActivity(intent);
        finish();
    }
}
