package pt.example.rf.conversorunidades;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ConversoesFavoritasActivity extends AppCompatActivity {

    public static final String VALOR_INTRODUZIDO = "valor_introduzido";
    public static final String VALOR_CONVERTIDO = "valor_convertido";

    double valorIntroduzido;
    double valorConvertido;

    final static DecimalFormat arredondar2 = new DecimalFormat("0.00");
    final static DecimalFormat arredondar3 = new DecimalFormat("0.000");
    static DecimalFormat arredondar = arredondar2;

    PersistenciaOpenDbHelper db = new PersistenciaOpenDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversoes_favoritas);
        setTitle(R.string.titulo_actividade_0);

        final TextView textViewUnidadeOrigemDestino = (TextView) findViewById(R.id.textViewUnidadeOrigemDestino);
        textViewUnidadeOrigemDestino.setText(AppData.nomeConversaoSeleccionada);
        final TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado2);

        converte(textViewResultado);

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        //Apagar conversão favorita
        apagaFavorito(dialogBuilder);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonVoltar2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Preserva o resultado quando o ecrã é rodado
        if (savedInstanceState != null) {
            valorIntroduzido = savedInstanceState.getDouble(VALOR_INTRODUZIDO);
            valorConvertido = savedInstanceState.getDouble(VALOR_CONVERTIDO);
            textViewResultado.setText(String.valueOf(arredondar.format(valorConvertido)));
        }

    }

    private void apagaFavorito(final AlertDialog.Builder dialogBuilder) {
        Button buttonApagarFavorito = (Button) findViewById(R.id.buttonApagarFavorito);
        buttonApagarFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogBuilder.setMessage(R.string.apagar_conversao);
                dialogBuilder.setPositiveButton(R.string.sim,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int foiApagado = db.apagarConversaoFavorita(AppData.codigoConversaoSeleccionada);
                                if (foiApagado > 0) {
                                    Toast.makeText(ConversoesFavoritasActivity.this, R.string.conversao_apagada, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ConversoesFavoritasActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(ConversoesFavoritasActivity.this, R.string.erro_apagar_favorito, Toast.LENGTH_SHORT).show();
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
    }

    private void converte(final TextView textViewResultado) {
        Button buttonConverter2 = (Button) findViewById(R.id.buttonConverter2);
        buttonConverter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean duasCasasDecimais = db.lerPrecisao();
                if (duasCasasDecimais) {
                    arredondar = arredondar2;
                } else {
                    arredondar = arredondar3;
                }

                FuncoesComuns.hideSoftKeyboard(ConversoesFavoritasActivity.this);

                EditText editTextValorIntroduzido = (EditText) findViewById(R.id.editTextIntroduzirValor2);
                String stringEditTextValorIntroduzido = editTextValorIntroduzido.getText().toString();
                try {
                    valorIntroduzido = Double.parseDouble(stringEditTextValorIntroduzido);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                //Validação do input
                validacoes(v, editTextValorIntroduzido, textViewResultado);
            }
        });
    }

    private void validacoes(View v, EditText editTextValorIntroduzido, TextView textViewResultado) {
        String str;
        if (AppData.codigoConversaoSeleccionada.equals("0921") && valorIntroduzido < -459.67) {
            str = getResources().getString(R.string.excepcao_f);
            textViewResultado.setText("");
            criarSnackbar(v, str);
        } else if (AppData.codigoConversaoSeleccionada.equals("0901") && valorIntroduzido < 0) {
            str = getResources().getString(R.string.excepcao_k);
            textViewResultado.setText("");
            criarSnackbar(v, str);
        } else if (AppData.codigoConversaoSeleccionada.equals("0912") && valorIntroduzido < -273.15) {
            str = getResources().getString(R.string.excepcao_c);
            textViewResultado.setText("");
            criarSnackbar(v, str);
        } else if (AppData.codigoConversaoSeleccionada.equals("0910") && valorIntroduzido < -273.15) {
            str = getResources().getString(R.string.excepcao_c);
            textViewResultado.setText("");
            criarSnackbar(v, str);
        } else if (AppData.codigoConversaoSeleccionada.equals("0902") && valorIntroduzido < 0) {
            str = getResources().getString(R.string.excepcao_k);
            textViewResultado.setText("");
            criarSnackbar(v, str);
        } else if (AppData.codigoConversaoSeleccionada.equals("0912") && valorIntroduzido < -459.67) {
            str = getResources().getString(R.string.excepcao_f);
            textViewResultado.setText("");
            criarSnackbar(v, str);
        } else if (editTextValorIntroduzido.getText().toString().trim().length() == 0) {
            str = getResources().getString(R.string.introduzir_valor2);
            textViewResultado.setText("");
            criarSnackbar(v, str);
        } else if (!AppData.codigoConversaoSeleccionada.substring(0, 2).equals("09") && valorIntroduzido <= 0) {
            str = getResources().getString(R.string.maior_que_0);
            textViewResultado.setText("");
            criarSnackbar(v, str);
        } else {
            valorConvertido = ConversoesClass.conversoes(AppData.codigoConversaoSeleccionada, valorIntroduzido);
            textViewResultado.setText(String.valueOf(arredondar.format(valorConvertido)));
        }
    }

    private void criarSnackbar(View v, String str) {
        Snackbar snackbar = Snackbar.make(v, str, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putDouble(VALOR_INTRODUZIDO, valorIntroduzido);
        outState.putDouble(VALOR_CONVERTIDO, valorConvertido);
        super.onSaveInstanceState(outState);
    }

}
