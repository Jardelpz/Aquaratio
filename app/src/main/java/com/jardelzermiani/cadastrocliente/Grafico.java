package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;

public class Grafico extends AppCompatActivity {

    private static final String TAG = "Gafico";

    private LineChart Grafico;
    private BoomMenuButton bmb;
    //private int edMax1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Cotacao_Configuracao cc=new Cotacao_Configuracao();

        //edMax1=Integer.parseInt(cc.getEdFaixaConsumoMax1().getText().toString());



        bmb = (BoomMenuButton) findViewById(R.id.bmbGrafico);

        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_4);
        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);


        Grafico = (LineChart) findViewById(R.id.idGrafico);

        Grafico.setDragEnabled(true);
        Grafico.setScaleEnabled(false);

        YAxis leftYAxis = Grafico.getAxisLeft();

        leftYAxis.setAxisMaximum(15000f);
        leftYAxis.setAxisMinimum(1000f);
        leftYAxis.enableGridDashedLine(10f, 0, 10f);
        leftYAxis.setDrawLimitLinesBehindData(true);
        Grafico.getAxisRight().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, 645f));
        yValues.add(new Entry(1, 1268f));
        yValues.add(new Entry(2, 5560f));
        yValues.add(new Entry(3, 6232f));
        yValues.add(new Entry(4, 5530f));
        yValues.add(new Entry(5, 8041f));
        yValues.add(new Entry(6, 4632f));
        yValues.add(new Entry(7, 12338f));
        yValues.add(new Entry(8, 5054f));
        yValues.add(new Entry(9, 6235f));
        yValues.add(new Entry(10, 5320f));
        yValues.add(new Entry(11, 3023f));

        LineDataSet set1 = new LineDataSet(yValues, "Consumo Mensal em L");

        set1.setCircleColorHole(Color.RED);
        set1.setCircleRadius(5);
        set1.setFormSize(10);
        set1.setHighlightLineWidth(3);
        set1.setHighLightColor(Color.rgb(18,108,132));
        set1.setColor(Color.BLACK);
        set1.setLineWidth(3f);
        set1.setValueTextSize(12f);
        set1.setValueTextColor(Color.BLUE);
        set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        Grafico.setData(data);

        String[] values = new String[]{
                "Jan",
                "Feb",
                "Mar",
                "Abr",
                "Mai",
                "Jun",
                "Jul",
                "Ago",
                "Set",
                "Out",
                "Nov",
                "Dez"
        };

        XAxis xAxis = Grafico.getXAxis();
        xAxis.setValueFormatter((new MyAxisValuesFormatter(values)));
        xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(12f);
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
                        Intent boomAjuda = new Intent(Grafico.this, Ajuda.class);
                        finish();
                        startActivity(boomAjuda);
                    } else if (posicao == 1) {
                        Intent boomDicas = new Intent(Grafico.this, Dicas.class);
                        finish();
                        startActivity(boomDicas);
                    } else if (posicao == 2) {
                        Intent boomConf = new Intent(Grafico.this, Configuracao.class);
                        finish();
                        startActivity(boomConf);
                    } else if (posicao == 3) {
                        Intent boomGrafico = new Intent(Grafico.this, com.jardelzermiani.cadastrocliente.Grafico.class);
                        finish();
                        startActivity(boomGrafico);
                    } else if (posicao == 4) {
                        Intent boomSobre = new Intent(Grafico.this, Sobre.class);
                        finish();
                        startActivity(boomSobre);
                    } else if (posicao == 5) {
                        Intent boomConsumoDiario = new Intent(Grafico.this, Consumo_Diario.class);
                        finish();
                        startActivity(boomConsumoDiario);
                    }
                }
            });
        }
    }

    public class MyAxisValuesFormatter implements IAxisValueFormatter {
        private String[] mValues;

        public MyAxisValuesFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value];
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
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
        Intent intent = new Intent(com.jardelzermiani.cadastrocliente.Grafico.this, Consumo_Diario.class);
        startActivity(intent);
        finish();
    }

}