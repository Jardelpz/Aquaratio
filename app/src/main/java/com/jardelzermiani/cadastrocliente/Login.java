package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;
import com.jardelzermiani.cadastrocliente.helper.SessionManager;
import com.jardelzermiani.cadastrocliente.helper.SessionSQLite;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private String HOST = "http://tccaquaratio.tecnologia.ws";


    private Button btCadastrarLogin, btEntrar, btEsqueciSenha;
    private EditText edNick, edSenha;
    private SessionManager session;
    private SessionSQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btCadastrarLogin = (Button) findViewById(R.id.btCadastrarLogin);
        btEntrar = (Button) findViewById(R.id.btEntrar);
        btEsqueciSenha = (Button) findViewById(R.id.btEsqueciSenha);
        edNick = (EditText) findViewById(R.id.edNick);
        edSenha = (EditText) findViewById(R.id.edSenha);

        db = new SessionSQLite(getApplicationContext());

        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(Login.this, Consumo_Diario.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();


        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nickaname = edNick.getText().toString();
                String senha = edSenha.getText().toString();

                String URL = HOST + "/logar.php";

                if (nickaname.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(Login.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                } else {
                    Ion.with(Login.this)
                            .load(URL)
                            .setBodyParameter("nickname_app", nickaname)
                            .setBodyParameter("senha_app", senha)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    if(result == null) {
                                        try {
                                            String error = result.get("error").getAsString();
                                            JsonObject user = result.get("user").getAsJsonObject();
                                            String nome = user.get("nome").getAsString();
                                            String email = user.get("email").getAsString();
                                            String nickname = user.get("nickname").getAsString();
                                            String senha = user.get("senha").getAsString();
                                            String integrantes = user.get("integrantes").getAsString();

                                            db.addUser(nome, email, nickname, senha, integrantes);

                                            if (error == "FALSE") {
                                                session.setLogin(true);

                                                Intent abreCadastro = new Intent(Login.this, Consumo_Diario.class);
                                                startActivity(abreCadastro);
                                                Toast.makeText(Login.this, "Deslize para os lados", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(Login.this, "Ocorreu um erro!", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (Exception erro) {
                                            Toast.makeText(Login.this, "Erro!" + erro, Toast.LENGTH_LONG).show();
                                        }
                                    }else{
                                        Toast.makeText(Login.this, "" + result, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

        btCadastrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(Login.this, Cadastro.class);
                startActivity(abreCadastro);
            }
        });

        btEsqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent esqueciSenha = new Intent(Login.this, Recuperar_Senha.class);
                startActivity(esqueciSenha);
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
        super.onDestroy();
    }
}
