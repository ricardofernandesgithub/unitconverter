package pt.example.rf.conversorunidades;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DefinicoesActivity extends AppCompatActivity {

    PersistenciaOpenDbHelper db = new PersistenciaOpenDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definicoes);
        setTitle(R.string.titulo_actividade_definicoes);

        definicoesPrecisao();

        //Apagar todas as conversões favoritas
        final AlertDialog.Builder dialogBuilder = apagarTodasConversoesFavoritas();

        //Definição "Sobre a aplicação"
        Button buttonSobre = (Button) findViewById(R.id.buttonSobre);
        buttonSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostraDialog(dialogBuilder);
            }
        });

    }

    private void definicoesPrecisao() {
        RadioButton radioButton2cd = (RadioButton) findViewById(R.id.radioButton2cd);
        RadioButton radioButton3cd = (RadioButton) findViewById(R.id.radioButton3cd);
        //Ler qual a precisão guardada
        boolean duasCasasDecimais = db.lerPrecisao();
        if (duasCasasDecimais) {
            radioButton2cd.setChecked(true);
        } else {
            radioButton3cd.setChecked(true);
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton2cd) {
                    AppData.precisao = "2";
                    db.actualizarPrecisao(AppData.precisao);
                } else {
                    AppData.precisao = "3";
                    db.actualizarPrecisao(AppData.precisao);
                }
            }
        });
    }

    @NonNull
    private AlertDialog.Builder apagarTodasConversoesFavoritas() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        Button buttonApagarTodosFavoritos = (Button) findViewById(R.id.buttonApgarTodosFavoritos);
        buttonApagarTodosFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogBuilder.setMessage(R.string.apagar_tudo_msg);
                dialogBuilder.setPositiveButton(R.string.sim,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int foiApagado = db.apagarTodasConversoesFavoritas();
                                if (foiApagado > 0) {
                                    Toast.makeText(DefinicoesActivity.this, String.valueOf(foiApagado) + getString(R.string.msg_nr_favoritos_apagados), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(DefinicoesActivity.this, R.string.msg_remover_falhou, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                );
                dialogBuilder.setNegativeButton(R.string.nao,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //finish();
                            }
                        }
                );
                dialogBuilder.show();
            }
        });
        return dialogBuilder;
    }

    private void mostraDialog(AlertDialog.Builder dialogBuilder) {
        dialogBuilder.setMessage(R.string.sobre_mensagem);
        dialogBuilder.setNegativeButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //
                    }
                }
        );
        dialogBuilder.show();
    }

}
