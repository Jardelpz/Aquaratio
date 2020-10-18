package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class ConfiguracaoConta extends AppCompatActivity {

    String HOST = "http://tccaquaratio.tecnologia.ws";
    EditText edConfiNome, edConfiNickname, edConfiEmail, edConfiMoradores;
    Button btConfiAlterarSenha, btConfiExcluirConta, btAlterarDados;
    BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao_conta);

        edConfiNome = (EditText) findViewById(R.id.edConfiNome);
        edConfiNickname = (EditText) findViewById(R.id.edConfiNickname);
        edConfiEmail = (EditText) findViewById(R.id.edConfiEmail);
        edConfiMoradores = (EditText) findViewById(R.id.edConfiMoradores);

        btConfiAlterarSenha = (Button) findViewById(R.id.btConfiAlterarSenha);
        btConfiExcluirConta = (Button) findViewById(R.id.btConfiExcluirConta);
        btAlterarDados = (Button) findViewById(R.id.btAlterarDados);

        BoomMenuButton bmb;
        bmb = (BoomMenuButton) findViewById(R.id.bmbConta);

        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_4);
        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);

        int[] vetorImg = {
                R.drawable.ajuda,
                R.drawable.dicas,
                R.drawable.configuracoes,
                R.drawable.grafico,
                R.drawable.sobreapp,
                R.drawable.home
        };

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder();

            builder.normalImageRes(vetorImg[i]);
            builder.normalColor(Color.WHITE);
            bmb.addBuilder(builder);
            final int posicao = i;
            builder.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    if (posicao == 0) {
                        Intent boomAjuda = new Intent(ConfiguracaoConta.this, Ajuda.class);
                        finish();
                        startActivity(boomAjuda);
                    } else if (posicao == 1) {
                        Intent boomDicas = new Intent(ConfiguracaoConta.this, Dicas.class);
                        finish();
                        startActivity(boomDicas);
                    } else if (posicao == 2) {
                        Intent boomConf = new Intent(ConfiguracaoConta.this, Configuracao.class);
                        finish();
                        startActivity(boomConf);
                    } else if (posicao == 3) {
                        Intent boomGrafico = new Intent(ConfiguracaoConta.this, Grafico.class);
                        finish();
                        startActivity(boomGrafico);
                    } else if (posicao == 4) {
                        Intent boomSobre = new Intent(ConfiguracaoConta.this, Sobre.class);
                        finish();
                        startActivity(boomSobre);
                    } else if (posicao == 5) {
                        Intent boomConsumoDiario = new Intent(ConfiguracaoConta.this, Consumo_Diario.class);
                        finish();
                        startActivity(boomConsumoDiario);
                    }
                }
            });
        }

        btAlterarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = edConfiNome.getText().toString();
                String nickname = edConfiNickname.getText().toString();
                String email = edConfiEmail.getText().toString();
                String integrantes = edConfiMoradores.getText().toString();

                String URL = HOST + "/alterardadosconf.php";

                if (nome.isEmpty() || nickname.isEmpty() || email.isEmpty() || integrantes.isEmpty()) {
                    Toast.makeText(ConfiguracaoConta.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                } else {
                    Ion.with(ConfiguracaoConta.this)
                            .load(URL)
                            .setBodyParameter("nome_app", nome)
                            .setBodyParameter("nickname_app", nickname)
                            .setBodyParameter("email_app", email)
                            .setBodyParameter("integrantes_app", integrantes)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    try {
                                        String RETORNO = result.get("ALTERAR_DADOS").getAsString();
                                        if (RETORNO.equals("SUCESSO")) {
                                            edConfiNome.setText("");
                                            edConfiNickname.setText("");
                                            edConfiEmail.setText("");
                                            edConfiMoradores.setText("");
                                            Toast.makeText(ConfiguracaoConta.this, "Dados alterados com sucesso!", Toast.LENGTH_LONG).show();
                                        } else if (RETORNO.equals("ERRO")) {
                                            Toast.makeText(ConfiguracaoConta.this, "Erro!", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ConfiguracaoConta.this, "Ocorreu um erro!", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (Exception erro) {
                                        Toast.makeText(ConfiguracaoConta.this, "Erro!" + erro, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }

            }
        });


        btConfiExcluirConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirExcluirConta = new Intent(ConfiguracaoConta.this, ExcluirConta.class);
                startActivity(abrirExcluirConta);
            }
        });

        btConfiAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirAlterarSenha = new Intent(ConfiguracaoConta.this, AlterarSenha.class);
                startActivity(abrirAlterarSenha);
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
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ConfiguracaoConta.this, Configuracao.class);
        startActivity(intent);
        finish();
    }

}
