package com.luist.apps_10_exa_prac_1_15551487;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToeActivity extends AppCompatActivity implements View.OnClickListener {

    //CREAMOS EL ARREGLO BIDIMENSIONAL DE BOTONES
    private Button[][] buttonsArray = new Button[3][3];
    //BOOLEAN TURNO
    private boolean player1Turn = true;
    //CONTADOR
    private int roundCount;
    private TextView tvWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        tvWin = findViewById(R.id.tvWin);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttonsArray[i][j] = findViewById(resID);
                buttonsArray[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanReset();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("-")) {
            return;
        }
        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }
        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                tvWin.setText("GANADOR:X");
                blockButtons();
                showDialog("GANADOR: X!!!!!");
            } else {
                tvWin.setText("GANADOR:O");
                blockButtons();
                showDialog("GANADOR: O!!!!!");
            }
        } else if (roundCount == 9) {
            tvWin.setText("EMAPTE!!");
            blockButtons();
            showDialog("EMPATE :(!!!!!");
        } else {
            player1Turn = !player1Turn;
        }

    }


    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttonsArray[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("-")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("-")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("-")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("-")) {
            return true;
        }
        return false;
    }


    private void cleanReset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttonsArray[i][j].setText("-");
            }
        }
        roundCount = 0;
        player1Turn = true;
        tvWin.setText("GANADOR: ");
        enableButtons();
    }

    private void blockButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttonsArray[i][j].setEnabled(false);
            }
        }
    }

    private void enableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttonsArray[i][j].setEnabled(true);
            }
        }
    }

    private void showDialog(String textToShow) {
        new AlertDialog.Builder(this)
                .setTitle("Cuadro de dialogo")
                .setMessage(textToShow)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "FELICIDADES!!", Toast.LENGTH_SHORT).show();
                    }
                }).create().show();
    }
}
