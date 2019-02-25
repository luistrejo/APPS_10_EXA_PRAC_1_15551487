package com.luist.apps_10_exa_prac_1_15551487;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAcredita = findViewById(R.id.btnAcredita);
        btnAcredita.setOnClickListener(this);
        Button btnAhorcado = findViewById(R.id.btnAhorcado);
        btnAhorcado.setOnClickListener(this);
        Button btnTicTacToe = findViewById(R.id.btnTicTacToe);
        btnTicTacToe.setOnClickListener(this);
        Button btnCalculaVolumen = findViewById(R.id.btnCalculaVolumen);
        btnCalculaVolumen.setOnClickListener(this);
        Button btnRotacion1 = findViewById(R.id.btnRotacion1);
        btnRotacion1.setOnClickListener(this);
        Button btnRotacion2 = findViewById(R.id.btnRotacion2);
        btnRotacion2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAcredita:
                iniciarActividad(AcreditaActivity.class);
                break;
            case R.id.btnAhorcado:
                iniciarActividad(AhorcadoActivity.class);
                break;
            case R.id.btnTicTacToe:
                iniciarActividad(TicTacToeActivity.class);
                break;
            case R.id.btnCalculaVolumen:
                iniciarActividad(CalculaVolumenActivity.class);
                break;
            case R.id.btnRotacion1:
                iniciarActividad(Rotacion1Activity.class);
                break;
            case R.id.btnRotacion2:
                iniciarActividad(Rotacion2Activity.class);
                break;
        }
    }

    private void iniciarActividad(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
