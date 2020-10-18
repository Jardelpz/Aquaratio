package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Consumo_Mensal extends AppCompatActivity {

    private CircleWaveMensal mWaterWaveView;
    private Message message;
    private int progress = 0;

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
        setContentView(R.layout.activity_consumomensal);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView txtMoedaMensal, txtDinheiroMensal, txtPorcentagemMensal, txtMetaMensal, txtConsumidoMensal;

        txtMoedaMensal = (TextView) findViewById(R.id.txtMoedaMensal);
        txtDinheiroMensal = (TextView) findViewById(R.id.txtDinheiroMensal);
        txtMetaMensal = (TextView) findViewById(R.id.txtMetaMensal);
        txtConsumidoMensal = (TextView) findViewById(R.id.txtConsumidoMensal);


        //Consumo
        Calendar c = new GregorianCalendar();
        SimpleDateFormat formatoData = new SimpleDateFormat();
        Date data = c.getTime();
        String dataFormatada = formatoData.format(data);

        String mesAtual = "09";
        String anoAtual = "2018";

        String nickUsu = "3";

        String URL = "http:tccaquaratio.tecnologia.ws/consultaMensal.php";

        String consumoMensal = "";

        Ion.with(Consumo_Mensal.this)
                .load(URL)
                .setBodyParameter("nickUsu_app", nickUsu)
                .setBodyParameter("mesAtual_app", mesAtual)
                .setBodyParameter("anoAtual_app", anoAtual)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        try {
                            String consumoMensal = result.get("consumo").getAsString();

                        } catch (Exception erro) {
                            Toast.makeText(Consumo_Mensal.this, "Erro!" + erro, Toast.LENGTH_LONG).show();
                        }
                    }
                });
        //Consumo

        txtMetaMensal.setText("Meta: 3100" + " L");
        txtConsumidoMensal.setText("Consumido: " + " L");
        txtMoedaMensal.setText("600");
        txtDinheiroMensal.setText("R$ " + "64,90");

        mWaterWaveView = (CircleWaveMensal) findViewById(R.id.wave_viewMensal);
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

    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            message = handler.obtainMessage();
            double progresso = 80;
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
                    Thread.sleep(75);
                }
                //mWaterWaveView.stopWave();

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    };



    public boolean onTouchEvent(MotionEvent event) {

        int eventAction = event.getAction();
        int x = (int) event.getX();
        System.out.println(x);
        int y = (int) event.getY();

        switch (eventAction) {
            case MotionEvent.ACTION_MOVE: {
                if (x <= 350) {
                    Intent deslizarmensal = new Intent(Consumo_Mensal.this, Consumo_Diario.class);
                    startActivity(deslizarmensal);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                }
            }
        }
        return true;
    }


}