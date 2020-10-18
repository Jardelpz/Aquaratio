package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Cotacao_Configuracao extends AppCompatActivity {

    public static EditText edFaixaConsumoMin1, edFaixaConsumoMax1, edFaixapreco1, edFaixaConsumoMin2, edFaixaConsumoMax2, edFaixapreco2;
    public Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotacao_configuracao);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        edFaixaConsumoMin1 = (EditText) findViewById(R.id.edFaixaConsumoMin1);
        edFaixaConsumoMax1 = (EditText) findViewById(R.id.edFaixaConsumoMax1);
        edFaixapreco1 = (EditText) findViewById(R.id.edFaixapreco1);
        edFaixaConsumoMin2 = (EditText) findViewById(R.id.edFaixaConsumoMin2);
        edFaixaConsumoMax2 = (EditText) findViewById(R.id.edFaixaConsumoMax2);
        edFaixapreco2 = (EditText) findViewById(R.id.edFaixapreco2);


        btSalvar = (Button) findViewById(R.id.btsalvar);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirConfConta = new Intent(Cotacao_Configuracao.this, SettingsActivity.class);
                startActivity(abrirConfConta);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Cotacao_Configuracao.this, SettingsActivity.class);
        startActivity(intent);
        finish();
    }

    public Cotacao_Configuracao() {
    }

    public EditText getEdFaixaConsumoMin1() {
        return edFaixaConsumoMin1;
    }

    public void setEdFaixaConsumoMin1(EditText edFaixaConsumoMin1) {
        this.edFaixaConsumoMin1 = edFaixaConsumoMin1;
    }

    public EditText getEdFaixaConsumoMax1() {
        return edFaixaConsumoMax1;
    }

    public void setEdFaixaConsumoMax1(EditText edFaixaConsumoMax1) {
        this.edFaixaConsumoMax1 = edFaixaConsumoMax1;
    }

    public EditText getEdFaixapreco1() {
        return edFaixapreco1;
    }

    public void setEdFaixapreco1(EditText edFaixapreco1) {
        this.edFaixapreco1 = edFaixapreco1;
    }

    public EditText getEdFaixaConsumoMin2() {
        return edFaixaConsumoMin2;
    }

    public void setEdFaixaConsumoMin2(EditText edFaixaConsumoMin2) {
        this.edFaixaConsumoMin2 = edFaixaConsumoMin2;
    }

    public EditText getEdFaixaConsumoMax2() {
        return edFaixaConsumoMax2;
    }

    public void setEdFaixaConsumoMax2(EditText edFaixaConsumoMax2) {
        this.edFaixaConsumoMax2 = edFaixaConsumoMax2;
    }

    public EditText getEdFaixapreco2() {
        return edFaixapreco2;
    }

    public void setEdFaixapreco2(EditText edFaixapreco2) {
        this.edFaixapreco2 = edFaixapreco2;
    }
}
