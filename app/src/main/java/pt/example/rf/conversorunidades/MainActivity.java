package pt.example.rf.conversorunidades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonDefinicoes = (Button) findViewById(R.id.buttonDefinicoes);
        buttonDefinicoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DefinicoesActivity.class);
                startActivity(intent);
            }
        });

        Button buttonFavoritos = (Button) findViewById(R.id.buttonFavoritos);
        buttonFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.grandezaSelecionada = 0;
                Intent intent = new Intent(MainActivity.this, TransicaoActivity.class);
                startActivity(intent);
            }
        });

        Button buttonArea = (Button) findViewById(R.id.buttonArea);
        buttonArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.grandezaSelecionada = 1;
                iniciaActividade();
            }
        });

        Button buttonBinario = (Button) findViewById(R.id.buttonBinario);
        buttonBinario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.grandezaSelecionada = 2;
                iniciaActividade();
            }
        });

        Button buttonComprimentoDistancia = (Button) findViewById(R.id.buttonComprimentoDistancia);
        buttonComprimentoDistancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.grandezaSelecionada = 3;
                iniciaActividade();
            }
        });

        Button buttonDivisas = (Button) findViewById(R.id.buttonDivisas);
        buttonDivisas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.grandezaSelecionada = 4;
                iniciaActividade();
            }
        });

        Button buttonEnergia = (Button) findViewById(R.id.buttonEnergia);
        buttonEnergia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.grandezaSelecionada = 5;
                iniciaActividade();
            }
        });

        Button buttonMassaPeso = (Button) findViewById(R.id.buttonMassaPeso);
        buttonMassaPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.grandezaSelecionada = 6;
                iniciaActividade();
            }
        });

        Button buttonPotencia = (Button) findViewById(R.id.buttonPotencia);
        buttonPotencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.grandezaSelecionada = 7;
                iniciaActividade();
            }
        });

        Button buttonPressao = (Button) findViewById(R.id.buttonPressao);
        buttonPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.grandezaSelecionada = 8;
                iniciaActividade();
            }
        });

        Button buttonTemperatura = (Button) findViewById(R.id.buttonTemperatura);
        buttonTemperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.grandezaSelecionada = 9;
                iniciaActividade();
            }
        });

        Button buttonVelocidade = (Button) findViewById(R.id.buttonVelocidade);
        buttonVelocidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.grandezaSelecionada = 10;
                iniciaActividade();
            }
        });

        Button buttonVolume = (Button) findViewById(R.id.buttonVolume);
        buttonVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.grandezaSelecionada = 11;
                iniciaActividade();
            }
        });
    }

    private void iniciaActividade() {
        Intent intent = new Intent(MainActivity.this, ConversoesActivity.class);
        startActivity(intent);
    }

}
