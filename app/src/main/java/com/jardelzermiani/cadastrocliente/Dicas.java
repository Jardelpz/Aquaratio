package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class Dicas extends AppCompatActivity {


    private BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicas);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        bmb = (BoomMenuButton) findViewById(R.id.bmbDicas);

        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_4);
        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
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
                        Intent boomAjuda = new Intent(Dicas.this, Ajuda.class);
                        finish();
                        startActivity(boomAjuda);
                    } else if (posicao == 1) {
                        Intent boomDicas = new Intent(Dicas.this, Dicas.class);
                        finish();
                        startActivity(boomDicas);
                    } else if (posicao == 2) {
                        Intent boomConf = new Intent(Dicas.this, Configuracao.class);
                        finish();
                        startActivity(boomConf);
                    } else if (posicao == 3) {
                        Intent boomGrafico = new Intent(Dicas.this, Grafico.class);
                        finish();
                        startActivity(boomGrafico);
                    } else if (posicao == 4) {
                        Intent boomSobre = new Intent(Dicas.this, Sobre.class);
                        finish();
                        startActivity(boomSobre);
                    } else if (posicao == 5) {
                        Intent boomConsumoDiario = new Intent(Dicas.this, Consumo_Diario.class);
                        finish();
                        startActivity(boomConsumoDiario);
                    }
                }
            });
        }
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
        Intent intent = new Intent(Dicas.this, Consumo_Diario.class);
        startActivity(intent);
        finish();
    }

    public void ScrollView(){

    }
}
