package com.luist.apps_10_exa_prac_1_15551487;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class AcreditaActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    TextView txtVWPuntos, txtVwCalifa, txtVwResultado;
    SeekBar seekBarPuntos, seekBarCalifa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acredita);
        seekBarPuntos = findViewById(R.id.seekBarPuntos);
        seekBarPuntos.setOnSeekBarChangeListener(this);
        seekBarCalifa = findViewById(R.id.seekBarCalifa);
        seekBarCalifa.setOnSeekBarChangeListener(this);

        txtVWPuntos = findViewById(R.id.txtVwPuntos);
        txtVwCalifa = findViewById(R.id.txtVwCalifa);
        txtVwResultado = findViewById(R.id.txtVwResultado);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekBarPuntos:
                txtVWPuntos.setText(progress + "");
                evaluar();
                break;
            case R.id.seekBarCalifa:
                txtVwCalifa.setText(progress + "");
                evaluar();
                break;
        }
    }

    private void evaluar() {
        if (seekBarPuntos.getProgress() > seekBarCalifa.getProgress()) {
            txtVwResultado.setText("NO ACREDITADO");
        } else {
            txtVwResultado.setText("ACREDITADO");
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
