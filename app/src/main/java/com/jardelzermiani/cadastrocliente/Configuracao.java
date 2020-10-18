package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jardelzermiani.cadastrocliente.helper.SessionManager;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class Configuracao extends AppCompatActivity {


    EditText edFaixaConsumoMin, edFaixaConsumoMax, edFaixapreco;
    Button btConfConta, btSair, btPreference;
    BoomMenuButton bmb;
    private Login login;
    private SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);


        btConfConta = (Button) findViewById(R.id.btConfConta);
        btSair = (Button) findViewById(R.id.btSair);
        btPreference=(Button)findViewById(R.id.btPreference);


        bmb = (BoomMenuButton) findViewById(R.id.bmbConfiguracao);

        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_4);
        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);

        session = new SessionManager(getApplicationContext());

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

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
                        Intent boomAjuda = new Intent(Configuracao.this, Ajuda.class);
                        finish();
                        startActivity(boomAjuda);
                    } else if (posicao == 1) {
                        Intent boomDicas = new Intent(Configuracao.this, Dicas.class);
                        finish();
                        startActivity(boomDicas);
                    } else if (posicao == 2) {
                        Intent boomConf = new Intent(Configuracao.this, Configuracao.class);
                        finish();
                        startActivity(boomConf);
                    } else if (posicao == 3) {
                        Intent boomGrafico = new Intent(Configuracao.this, Grafico.class);
                        finish();
                        startActivity(boomGrafico);
                    } else if (posicao == 4) {
                        Intent boomSobre = new Intent(Configuracao.this, Sobre.class);
                        finish();
                        startActivity(boomSobre);
                    } else if (posicao == 5) {
                        Intent boomConsumoDiario = new Intent(Configuracao.this, Consumo_Diario.class);
                        finish();
                        startActivity(boomConsumoDiario);
                    }
                }
            });
        }

        btPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirPreference = new Intent(Configuracao.this, SettingsActivity.class);
                startActivity(abrirPreference);
            }
        });

        btConfConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirConfConta = new Intent(Configuracao.this, ConfiguracaoConta.class);
                startActivity(abrirConfConta);
            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                finishAffinity();
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

        finish();
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
        Intent intent = new Intent(Configuracao.this, Consumo_Diario.class);
        startActivity(intent);
        finish();
    }
}
