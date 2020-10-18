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

public class Cadastro extends AppCompatActivity {

    private String HOST = "http://tccaquaratio.tecnologia.ws";

    private Button btCadastrar;
    private EditText edNome_cad, edNick_cad, edEmail_cad, edMorador_cad, edSenha_cad, edConfirmacaosenha_cad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        edNome_cad = (EditText) findViewById(R.id.edNome_cad);
        edNick_cad = (EditText) findViewById(R.id.edNick_cad);
        edEmail_cad = (EditText) findViewById(R.id.edEmail_cad);
        edMorador_cad = (EditText) findViewById(R.id.edMorador_cad);
        edSenha_cad = (EditText) findViewById(R.id.edSenha_cad);
        edConfirmacaosenha_cad = (EditText) findViewById(R.id.edConfirmacaosenha_cad);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edNome_cad.getText().toString();
                String email = edEmail_cad.getText().toString();
                String nickname = edNick_cad.getText().toString();
                String integrantes = edMorador_cad.getText().toString();
                String senha = edSenha_cad.getText().toString();
                String conf_senha = edConfirmacaosenha_cad.getText().toString();

                String URL = HOST + "/cadastro.php";

                if (conf_senha.equals(senha)) {
                    if (nome.isEmpty() || email.isEmpty() || nickname.isEmpty() || integrantes.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(Cadastro.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                    } else {
                        Ion.with(Cadastro.this)
                                .load(URL)
                                .setBodyParameter("nome_app", nome)
                                .setBodyParameter("email_app", email)
                                .setBodyParameter("nickname_app",nickname)
                                .setBodyParameter("senha_app", senha)
                                .setBodyParameter("integrantes_app",integrantes)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonObject result) {
                                        try {
                                            String RETORNO = result.get("CADASTRO").getAsString();
                                            if (RETORNO.equals("EMAIL_ERRO")) {
                                                Toast.makeText(Cadastro.this, "Esse Email já existe", Toast.LENGTH_LONG).show();
                                            } else if (RETORNO.equals("SUCESSO")) {
                                                Toast.makeText(Cadastro.this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                                                Intent abreCadastro = new Intent(Cadastro.this, Consumo_Diario.class);
                                                startActivity(abreCadastro);
                                                Toast.makeText(Cadastro.this, "Bem-Vindo", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(Cadastro.this, "Ocorreu um erro!", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (Exception erro) {
                                            Toast.makeText(Cadastro.this, "Erro!" + erro, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }

                } else {
                    Toast.makeText(Cadastro.this, "As senhas não coincidem ", Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(Cadastro.this, Login.class);
        startActivity(intent);
        finish();
    }
}
