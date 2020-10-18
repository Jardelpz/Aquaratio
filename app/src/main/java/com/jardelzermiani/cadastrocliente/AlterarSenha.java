package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class AlterarSenha extends AppCompatActivity {

    private String HOST = "http://tccaquaratio.tecnologia.ws";
    private EditText edSenhaAtual, edSenhaNova, edConfSenhaNova;
    Button btAlterarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);


        edSenhaAtual = (EditText) findViewById(R.id.edSenhaAtual);
        edSenhaNova = (EditText) findViewById(R.id.edSenhaNova);
        edConfSenhaNova = (EditText) findViewById(R.id.edConfSenhaNova);

        btAlterarSenha = (Button) findViewById(R.id.btAlterarSenha);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        btAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String senha = edSenhaAtual.getText().toString();
                String novasenha = edSenhaNova.getText().toString();
                String confnovasenha = edConfSenhaNova.getText().toString();

                String URL = HOST + "/alterarsenhaconfiguracao.php";

                if (confnovasenha.equals(novasenha)) {
                    if (senha.isEmpty() || novasenha.isEmpty() || confnovasenha.isEmpty()) {
                        Toast.makeText(AlterarSenha.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                    } else {
                        Ion.with(AlterarSenha.this)
                                .load(URL)
                                .setBodyParameter("senha_app", senha)
                                .setBodyParameter("novasenha_app", novasenha)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonObject result) {
                                        try {
                                            String RETORNO = result.get("ALTERAR").getAsString();
                                            if (RETORNO.equals("SUCESSO")) {
                                                Toast.makeText(AlterarSenha.this, "Senha Alterada!", Toast.LENGTH_LONG).show();
                                                Intent abrirConfiguracaoConta = new Intent(AlterarSenha.this, ConfiguracaoConta.class);
                                                startActivity(abrirConfiguracaoConta);
                                            } else if (RETORNO.equals("ERRO")) {
                                                Toast.makeText(AlterarSenha.this, "Essa senha não existe!", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(AlterarSenha.this, "Ocorreu um erro!", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (Exception erro) {
                                            Toast.makeText(AlterarSenha.this, "Erro!" + erro, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                } else {
                    Toast.makeText(AlterarSenha.this, "As senhas não coincidem ", Toast.LENGTH_LONG).show();
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
    public void onBackPressed() {
        Intent intent = new Intent(AlterarSenha.this, ConfiguracaoConta.class);
        startActivity(intent);
        finish();
    }
}

