package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class ExcluirConta extends AppCompatActivity {

    private String HOST = "http://tccaquaratio.tecnologia.ws";

    private EditText edEmailExcluir;
    private Button btExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluirconta);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btExcluir = (Button) findViewById(R.id.btExcluir);
        edEmailExcluir = (EditText) findViewById(R.id.edEmailExcluir);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();

        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmailExcluir.getText().toString();

                String URL = HOST + "/excluirconta.php";

                if (email.isEmpty()) {
                    Toast.makeText(ExcluirConta.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                } else {
                    Ion.with(ExcluirConta.this)
                            .load(URL)
                            .setBodyParameter("email_app", email)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    try {
                                        String RETORNO = result.get("EXCLUIR").getAsString();
                                        if (RETORNO.equals("SUCESSO")) {
                                            finishAffinity();
                                            Toast.makeText(ExcluirConta.this, "Conta Excluída", Toast.LENGTH_LONG).show();
                                        } else if (RETORNO.equals("ERRO")) {
                                            Toast.makeText(ExcluirConta.this, "Email incorreto!", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ExcluirConta.this, "Ocorreu um erro!", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (Exception erro) {
                                        Toast.makeText(ExcluirConta.this, "Erro!" + erro, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected  void onStop(){
        super.onStop();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ExcluirConta.this, ConfiguracaoConta.class);
        startActivity(intent);
        finish();
    }
}