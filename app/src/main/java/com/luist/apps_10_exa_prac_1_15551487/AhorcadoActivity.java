package com.luist.apps_10_exa_prac_1_15551487;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AhorcadoActivity extends AppCompatActivity implements View.OnClickListener {
    EditText eTxtPalabra, eTxtLetra;
    TextView txtVwLetras, txtVwIntentos;
    RadioButton rBtnDificil, rBtnMedio, rBtnFacil;
    Button btnOK, btnReintentar;
    String[] arrayPalabras = {"instituto", "chihuahua", "sistemas", "bisonte", "mensajeria", "dispositivo", "moviles", "reprobados", "cuestionario", "computadora"};
    String sPalabra;
    int iIntentos = 2;
    String sGuiones = "";
    CheckBox checkXTream;
    boolean bXTream = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorcado);

        eTxtPalabra = findViewById(R.id.eTxtPalabra);
        eTxtLetra = findViewById(R.id.eTxtLetra);
        txtVwLetras = findViewById(R.id.txtVwLetras);
        txtVwIntentos = findViewById(R.id.txtVwIntentos);
        btnOK = findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);
        btnReintentar = findViewById(R.id.btnReintentar);
        btnReintentar.setOnClickListener(this);
        rBtnDificil = findViewById(R.id.rBtnDificil);
        rBtnMedio = findViewById(R.id.rBtnMedio);
        rBtnFacil = findViewById(R.id.rBtnFacil);
        checkXTream = findViewById(R.id.checkBoxXTream);
        nuevoJuego();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOK:
                //Comparamos si el usuario ingreso una palabra completa con la variable
                //de la palabra seleccionada, si son iguales mostramos Dialog de ganador
                //de lo contrario el de perdedor.
                if (eTxtLetra.getText().toString().equals(sPalabra)) {
                    eTxtPalabra.setText("");
                    eTxtPalabra.setText(sPalabra);
                    showDialogResultado("HAS GANADO!!", R.drawable.ganador);
                    return;
                } else if (eTxtLetra.getText().toString().length() > 1) {
                    eTxtPalabra.setText("");
                    eTxtPalabra.setText(sPalabra);
                    showDialogResultado("HAS PERDIDO!!", R.drawable.perdedor);
                    return;
                }

                //Verificamos que la palabra seleccionada contenga la letra ingresada
                if (sPalabra.contains(eTxtLetra.getText().toString())) {
                    Log.d("vocales", isVocal(eTxtLetra.getText().toString().charAt(0)) + "");
                    //Si esta seleccionada el Check de XTream y la letra es vocal descontamos un intento
                    if (bXTream && isVocal(eTxtLetra.getText().toString().charAt(0))) {
                        iIntentos--;
                        txtVwIntentos.setText(iIntentos + "");
                        if (iIntentos == 0) {
                            showDialogResultado("HAS PERDIDO!!", R.drawable.perdedor);
                        }
                    }

                    //Cambiamos los guiones por la letra ingresada en las posiciones
                    //que nos regresa el metodo posicionesLetra()
                    char[] cGuiones = sGuiones.toCharArray();
                    for (int i : posicionesLetra(eTxtLetra.getText().toString().charAt(0), sPalabra)) {
                        cGuiones[i] = eTxtLetra.getText().toString().charAt(0);
                    }
                    sGuiones = String.valueOf(cGuiones);
                    eTxtPalabra.setText(sGuiones);
                    eTxtLetra.setText("");
                    //Comparamos la palabra seleccionada con el avance
                    if (sPalabra.equals(sGuiones)) {
                        showDialogResultado("HAS GANADO!!", R.drawable.ganador);
                    }
                } else {
                    iIntentos--;
                    txtVwIntentos.setText(iIntentos + "");
                    eTxtLetra.setText("");
                    if (iIntentos == 0) {
                        showDialogResultado("HAS PERDIDO!!", R.drawable.perdedor);
                        eTxtPalabra.setText("");
                        eTxtPalabra.setText(sPalabra);
                    }
                }
                break;
            case R.id.btnReintentar:
                Toast.makeText(this, "Nueva partida creada", Toast.LENGTH_LONG).show();
                nuevoJuego();
                break;
        }
    }

    //Metodo que limpia los EditText, reinicia la variable de intentos
    //y selecciona una nueva palabra
    private void nuevoJuego() {
        //Limpiamos todos los campos y variables usadas
        eTxtPalabra.setText("");
        eTxtLetra.setText("");
        iIntentos = 0;
        sPalabra = "";
        sGuiones = "";
        bXTream = checkXTream.isChecked();

        //Generamos un numero aleatorio del 0 al 9 para seleccionar una palabra del arreglo
        int max = 9;
        int min = 0;
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        sPalabra = arrayPalabras[rand];
        Log.d("PALABRA", sPalabra);

        //Generamos el string de guiones con la longitud de la palabra
        for (int x = 0; x < sPalabra.length(); x++) {
            sGuiones = sGuiones.concat("_");
        }
        //Revisamos el nivel de juego y lo asignamos a la variable correspondiente
        if (rBtnDificil.isChecked()) {
            iIntentos = 2;
        } else if (rBtnMedio.isChecked()) {
            iIntentos = 4;
        } else {
            iIntentos = 6;
        }

        eTxtPalabra.setText(sGuiones);
        txtVwLetras.setText(sPalabra.length() + "");
        txtVwIntentos.setText(iIntentos + "");
        Toast.makeText(this, sPalabra, Toast.LENGTH_LONG).show();
    }

    //Metodo que regresa las posiciones de una letra en una palabra
    public List<Integer> posicionesLetra(char LetraABuscar, String palabra) {
        List<Integer> posiciones = new ArrayList<>();
        for (int i = 0; i < palabra.length(); i++) {
            if ((int) LetraABuscar == (int) palabra.charAt(i))
                posiciones.add(i);
        }
        return posiciones;
    }

    void showDialogResultado(String sResultado, int img) {
        final Dialog dlgMiDialogo;
        dlgMiDialogo = new Dialog(this);
        dlgMiDialogo.setContentView(R.layout.dialogo_layout);
        ImageView imgVwResultado;
        TextView txtVwAhorcadoRes;
        Button btnCerrar;
        txtVwAhorcadoRes = dlgMiDialogo.findViewById(R.id.txtVwAhorcadoRes);
        imgVwResultado = dlgMiDialogo.findViewById(R.id.imgVwResultado);
        btnCerrar = dlgMiDialogo.findViewById(R.id.btnCerrar);
        imgVwResultado.setImageResource(img);
        txtVwAhorcadoRes.setText(sResultado);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlgMiDialogo.cancel();
            }
        });
        dlgMiDialogo.show();
    }

    //Metodo que detecta si una letra es vocal
    //Si no es vocal .indexOf regresa -1
    public static boolean isVocal(char c) {
        return "aeiou".indexOf(c) != -1;
    }
}
