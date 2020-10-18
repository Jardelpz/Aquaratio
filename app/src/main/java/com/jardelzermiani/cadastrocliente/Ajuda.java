package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class Ajuda extends AppCompatActivity {

    private Button btAjudaMoeda, btAjudaGrafico, btAjudaConsumoM3, btAjudaGastoMonetario, btAjudaInfoPessoais, btAjudaAlterarSenha, btAjudaExcluirConta;
    private BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);



        btAjudaMoeda = (Button) findViewById(R.id.btAjudaMoeda);
        btAjudaGrafico = (Button) findViewById(R.id.btAjudaGrafico);
        btAjudaConsumoM3 = (Button) findViewById(R.id.btAjudaConsumoM3);
        btAjudaGastoMonetario = (Button) findViewById(R.id.btAjudaGastoMonetario);
        btAjudaInfoPessoais = (Button) findViewById(R.id.btAjudaInfoPessoais);
        btAjudaAlterarSenha = (Button) findViewById(R.id.btAjudaAlterarSenha);
        btAjudaExcluirConta = (Button) findViewById(R.id.btAjudaExcluirConta);


        bmb = (BoomMenuButton) findViewById(R.id.bmbAjuda);

        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_4);
        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);

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
                        Intent boomAjuda = new Intent(Ajuda.this, Ajuda.class);
                        finish();
                        startActivity(boomAjuda);
                    } else if (posicao == 1) {
                        Intent boomDicas = new Intent(Ajuda.this, Dicas.class);
                        finish();
                        startActivity(boomDicas);
                    } else if (posicao == 2) {
                        Intent boomConf = new Intent(Ajuda.this, Configuracao.class);
                        finish();
                        startActivity(boomConf);
                    } else if (posicao == 3) {
                        Intent boomGrafico = new Intent(Ajuda.this, Grafico.class);
                        finish();
                        startActivity(boomGrafico);
                    } else if (posicao == 4) {
                        Intent boomSobre = new Intent(Ajuda.this, Sobre.class);
                        finish();
                        startActivity(boomSobre);
                    } else if (posicao == 5) {
                        Intent boomConsumoDiario = new Intent(Ajuda.this, Consumo_Diario.class);
                        finish();
                        startActivity(boomConsumoDiario);
                    }
                }
            });
        }

        btAjudaMoeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirAjudaMoeda = new Intent(Ajuda.this, AjudaMoeda.class);
                startActivity(abrirAjudaMoeda);
            }
        });

        btAjudaGrafico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirAjudaGrafico = new Intent(Ajuda.this, AjudaGrafico.class);
                startActivity(abrirAjudaGrafico);
            }
        });

        btAjudaConsumoM3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirAjudaConsumo = new Intent(Ajuda.this, AjudaConsumo.class);
                startActivity(abrirAjudaConsumo);
            }
        });

        btAjudaGastoMonetario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirAjudaGastoMonetario = new Intent(Ajuda.this, AjudaGastoMonetario.class);
                startActivity(abrirAjudaGastoMonetario);
            }
        });

        btAjudaInfoPessoais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirAjudaInfo = new Intent(Ajuda.this, AjudaPessoal.class);
                startActivity(abrirAjudaInfo);
            }
        });

        btAjudaAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirAjudaSenha= new Intent(Ajuda.this, AjudaSenha.class);
                startActivity(abrirAjudaSenha);
            }
        });

        btAjudaExcluirConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirAjudaExcluir= new Intent(Ajuda.this, AjudaExcluir.class);
                startActivity(abrirAjudaExcluir);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Ajuda.this, Consumo_Diario.class);
        startActivity(intent);
        finish();
    }
}

