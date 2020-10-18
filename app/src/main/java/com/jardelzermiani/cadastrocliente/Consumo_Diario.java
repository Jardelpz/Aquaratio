package com.jardelzermiani.cadastrocliente;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;


@SuppressLint("HandlerLeak")
public class Consumo_Diario extends AppCompatActivity {

    private ArrayList<Integer> listaImagem;

    private TextView txtPorcentagemDiaria, txtMetaDiaria, txtConsumidoDiaria;

    private CircleWaveDiario mWaterWaveView;
    private Message message;
    private int progress = 0;

    private BoomMenuButton bmb;

    private String HOST = "http://tccaquaratio.tecnologia.ws";

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int p = msg.what;
            mWaterWaveView.setmWaterLevel((float) p / 100);
        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumodiario);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtMetaDiaria = (TextView) findViewById(R.id.txtMetaDiaria);
        txtConsumidoDiaria = (TextView) findViewById(R.id.txtConsumidoDiaria);
        bmb = (BoomMenuButton) findViewById(R.id.bmbConsumoDiario);

        mWaterWaveView = (CircleWaveDiario) findViewById(R.id.wave_viewDiario);

        txtMetaDiaria.setText("Meta: " + "120" +" L");
        txtConsumidoDiaria.setText("Consumido: 120 L");

//        Calendar c = new GregorianCalendar();
//        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
//        Date data = c.getTime();
//        String dataFormatada = formatoData.format(data);
//
//        String diaAtual = dataFormatada.substring(0,2);
//        String mesAtual = dataFormatada.substring(3,5);
//        String anoAtual = dataFormatada.substring(6,10);
//
//        String nickUsu = SingletonLogin.getInstance().getNickname();
//
//        Ion.with(consumodiario.this)
//                .load(URL)
//                .setBodyParameter("nickUsu_app", nickUsu)
//                .setBodyParameter("diaAtual_app", diaAtual)
//                .setBodyParameter("mesAtual_app", mesAtual)
//                .setBodyParameter("anoAtual_app",anoAtual)
//                .asJsonObject()
//                .setCallback(new FutureCallback<JsonObject>(){
//                    @Override
//                    public void onCompleted(Exception e, JsonObject result){
//                        double consumoDiario = Double.parseDouble(result.get("RESULTADOS").getAsString());
//
//                        txtConsumidoDiaria.setText("%.1f %n L" + consumoDiario);
//                    }
//                });



        listaImagem = new ArrayList<>();
        metodoListaImg();

        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_4);
        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);

        mWaterWaveView.startWave();

        new Thread(runnable).start();
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder();
            builder.normalColor(Color.WHITE);
            builder.normalImageRes(listaImagem.get(i));

            final int posicao = i;
            builder.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    if (posicao == 0) {
                        Intent boomAjuda = new Intent(Consumo_Diario.this, Ajuda.class);
                        startActivity(boomAjuda);
                    } else if (posicao == 1) {
                        Intent boomDicas = new Intent(Consumo_Diario.this, Dicas.class);
                        startActivity(boomDicas);
                    } else if (posicao == 2) {
                        Intent boomConf = new Intent(Consumo_Diario.this, Configuracao.class);
                        startActivity(boomConf);
                    } else if (posicao == 3) {
                        Intent boomGrafico = new Intent(Consumo_Diario.this, Grafico.class);
                        startActivity(boomGrafico);
                    } else if (posicao == 4) {
                        Intent boomSobre = new Intent(Consumo_Diario.this, Sobre.class);
                        startActivity(boomSobre);
                    } else if (posicao == 5) {
                        Intent boomConsumoDiario = new Intent(Consumo_Diario.this, Consumo_Diario.class);
                        startActivity(boomConsumoDiario);
                    }

                }

            });
            bmb.addBuilder(builder);
        }
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
        // TODO Auto-generated method stub
        mWaterWaveView.stopWave();
        mWaterWaveView = null;
        super.onDestroy();
    }



    String URL = HOST + "/consumodiario.php";


    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            message = handler.obtainMessage();
            double progresso = 100;
            // TODO Auto-generated method stub
            try {
                for (int i = 1; i <= (int) progresso; i++) {
                    int x = progress++;
                    message.what = x;
                    handler.sendEmptyMessage(message.what);
                    mWaterWaveView.setWaveProgess(String.valueOf(progress) + "%");
                    if (i < 20) {
                        mWaterWaveView.setWaveUpdate("Excelente");
                    } else if (i == 50) {
                        mWaterWaveView.setWaveUpdate("Bom");
                    } else if(i == 75){
                        mWaterWaveView.setWaveUpdate("Cuidado");
                    }else if(i>=100){
                        progresso=progresso-100;
                        mWaterWaveView.setWaveUpdate("Ultrapassou");
                    }
                    Thread.sleep(70);
                }
                //mWaterWaveView.stopWave();

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    };

    private void metodoListaImg() {
        listaImagem.add(R.drawable.ajuda);
        listaImagem.add(R.drawable.dicas);
        listaImagem.add(R.drawable.configuracoes);
        listaImagem.add(R.drawable.grafico);
        listaImagem.add(R.drawable.sobreapp);
        listaImagem.add(R.drawable.home);
    }

    public boolean onTouchEvent(MotionEvent event) {

        int eventAction = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (eventAction) {
            case MotionEvent.ACTION_MOVE: {
                if (x < 350) {
                    Intent deslizardiario = new Intent(Consumo_Diario.this, Consumo_Semanal.class);
                    startActivity(deslizardiario);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                } else if (x > 350) {
                    Intent deslizardiario1 = new Intent(Consumo_Diario.this, Consumo_Mensal.class);
                    startActivity(deslizardiario1);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                break;
            }
        }
        return true;
    }


}