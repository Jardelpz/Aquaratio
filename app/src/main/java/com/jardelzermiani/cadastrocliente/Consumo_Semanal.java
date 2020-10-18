package com.jardelzermiani.cadastrocliente;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class Consumo_Semanal extends AppCompatActivity {

    private CircleWaveSemanal mWaterWaveView;
    private Message message;
    private int progress = 0;
    private TextView txtPorcentagemSemanal, txtMetaSemanal, txtConsumidoSemanal;

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
        setContentView(R.layout.activity_consumosemanal);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        txtMetaSemanal = (TextView) findViewById(R.id.txtMetaSemanal);
        txtConsumidoSemanal = (TextView) findViewById(R.id.txtConsumidoSemanal);

        txtMetaSemanal.setText("Meta: 770"  + " L");
        txtConsumidoSemanal.setText("Consumido: 270" + " L");

        mWaterWaveView = (CircleWaveSemanal) findViewById(R.id.wave_viewSemanal);
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
            double progresso = 35;
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
                if (x >= 350) {
                    Intent deslizarsemanal = new Intent(Consumo_Semanal.this, Consumo_Diario.class);
                    startActivity(deslizarsemanal);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    break;
                }
            }
        }
        return true;
    }
}