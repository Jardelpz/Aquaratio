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

public class Recuperar_Senha extends AppCompatActivity {

    static private String HOST = "http://tccaquaratio.tecnologia.ws";

    private EditText edEmailRecuperar, edSenhaRecuperar;
    private Button btRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperarsenha);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        edEmailRecuperar = (EditText) findViewById(R.id.edEmailRecuperar);
        edSenhaRecuperar = (EditText) findViewById(R.id.edSenhaRecuperar);
        btRecuperar = (Button) findViewById(R.id.btRecuperar);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();

        btRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edEmailRecuperar.getText().toString();
                String senha = edSenhaRecuperar.getText().toString();

                String URL = HOST + "/editarsenha.php";

                if (email.isEmpty()||senha.isEmpty()) {
                    Toast.makeText(Recuperar_Senha.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                } else {
                    Ion.with(Recuperar_Senha.this)
                            .load(URL)
                            .setBodyParameter("email_app", email)
                            .setBodyParameter("senha_app",senha)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    try {
                                        String RETORNO = result.get("RECUPERAR").getAsString();
                                        if (RETORNO.equals("SUCESSO")) {
                                            Intent recuperarSenha = new Intent(Recuperar_Senha.this, Consumo_Diario.class);
                                            startActivity(recuperarSenha);//abriria outra activity p inserir a senha;
                                            Toast.makeText(Recuperar_Senha.this, "Senha Recuperada", Toast.LENGTH_LONG).show();
                                        } else if (RETORNO.equals("ERRO")) {
                                            Toast.makeText(Recuperar_Senha.this, "Email Incorreto", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(Recuperar_Senha.this, "Ocorreu um erro!", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (Exception erro) {
                                        Toast.makeText(Recuperar_Senha.this, "Erro!" + erro, Toast.LENGTH_LONG).show();
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
        finish();
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
        Intent intent = new Intent(Recuperar_Senha.this, Login.class);
        startActivity(intent);
        finish();
    }
}

