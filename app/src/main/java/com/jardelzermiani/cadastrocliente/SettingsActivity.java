package com.jardelzermiani.cadastrocliente;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsActivity extends AppCompatPreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // load settings fragment
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MainPreferenceFragment()).commit();

    }


    public static class MainPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.activity_configuracao);

        }
    }

    @Override
    public void onBackPressed() {
        Intent intente= new Intent(SettingsActivity.this, Configuracao.class);
        startActivity(intente);
        finish();
    }

}



//
//        btSair.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finishAffinity();
//            }
//        });

