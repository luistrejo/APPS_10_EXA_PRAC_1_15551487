package com.luist.apps_10_exa_prac_1_15551487;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class CalculaVolumenActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private Button btnCalc;
    private EditText etRadius;
    private TextView tvAngle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula_volumen);

        seekBar = findViewById(R.id.seekBar1);
        btnCalc = findViewById(R.id.btnCalcular);
        etRadius = findViewById(R.id.etRadius);
        tvAngle = findViewById(R.id.tvAngle);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etRadius.getText()) && !etRadius.getText().toString().equals("")) {
                    Double radius = Double.parseDouble(etRadius.getText().toString());
                    Double angle = Double.parseDouble(String.valueOf(seekBar.getProgress()));

                    Double resuParc1 = (angle * radius);
                    Double resuParc2 = resuParc1 * 3;
                    Double resuParc3 = resuParc2 * (2);
                    Double resu = resuParc3 / 3;

                    Toast.makeText(getApplicationContext(), "VOLUMEN: " + resu.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Debes introducir un radio", Toast.LENGTH_SHORT).show();
                }
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                tvAngle.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
