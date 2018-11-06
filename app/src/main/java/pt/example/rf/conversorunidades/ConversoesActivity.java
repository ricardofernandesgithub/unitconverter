package pt.example.rf.conversorunidades;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ConversoesActivity extends AppCompatActivity {

    public static final String VALOR_INTRODUZIDO = "valor_introduzido";
    public static final String VALOR_CONVERTIDO = "valor_convertido";

    PersistenciaOpenDbHelper db;

    //As duas variáveis seguintes servem para fazer o mapeamento da conversão pretendida, permitindo assim chamar o método correcto da classe ConversoesClass
    String codigoConversaoInicial = "";
    String codigoConversaoFinal = "";

    double valorIntroduzido;
    double valorConvertido;
    final static DecimalFormat arredondar2 = new DecimalFormat("0.00");
    final static DecimalFormat arredondar3 = new DecimalFormat("0.000");
    static DecimalFormat arredondar = arredondar2;

    int unidadeOrigemTrocar = 0;
    int unidadeDestinoTrocar = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversoes);

        final TextView textViewValorConvertido = (TextView) findViewById(R.id.textViewValorConvertido);

        int idArrayUnidades = setTituloConversao();

        final Spinner spinnerOrigem = (Spinner) findViewById(R.id.spinnerOrigem);
        final Spinner spinnerDestino = (Spinner) findViewById(R.id.spinnerDestino);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(idArrayUnidades));
        spinnerOrigem.setAdapter(adapter);
        spinnerDestino.setAdapter(adapter);

        //Troca unidades seleccionadas
        trocaUnidades(spinnerOrigem, spinnerDestino);

        //Fazer conversão
        fazerConversao(textViewValorConvertido, spinnerOrigem, spinnerDestino);

        //Voltar à actividade main
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonVoltar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Chamar construtor para criar tabela
        db = new PersistenciaOpenDbHelper(this);

        //Adicionar conversões favoritas
        adicionaConversaoFavorita(spinnerOrigem, spinnerDestino);

        //Preserva o resultado quando o ecrã é rodado
        if (savedInstanceState != null) {
            valorIntroduzido = savedInstanceState.getDouble(VALOR_INTRODUZIDO);
            valorConvertido = savedInstanceState.getDouble(VALOR_CONVERTIDO);
            textViewValorConvertido.setText(String.valueOf(arredondar.format(valorConvertido)));
        }

    }

    private void trocaUnidades(final Spinner spinnerOrigem, final Spinner spinnerDestino) {
        Button buttonTroca = (Button) findViewById(R.id.buttonTrocar);
        buttonTroca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unidadeOrigemTrocar = spinnerOrigem.getSelectedItemPosition();
                int troca = unidadeOrigemTrocar;
                unidadeDestinoTrocar = spinnerDestino.getSelectedItemPosition();
                spinnerOrigem.setSelection(unidadeDestinoTrocar);
                spinnerDestino.setSelection(troca);
            }
        });
    }

    private void fazerConversao(final TextView textViewValorConvertido, final Spinner spinnerOrigem, final Spinner spinnerDestino) {
        Button buttonConverter = (Button) findViewById(R.id.buttonConverter);
        buttonConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean duasCasasDecimais = db.lerPrecisao();
                if (duasCasasDecimais) {
                    arredondar = arredondar2;
                } else {
                    arredondar = arredondar3;
                }

                FuncoesComuns.hideSoftKeyboard(ConversoesActivity.this);

                EditText editTextValorIntroduzido = (EditText) findViewById(R.id.editTextValorIntroduzido);
                String stringEditTextValorIntroduzido = editTextValorIntroduzido.getText().toString();
                try {
                    valorIntroduzido = Double.parseDouble(stringEditTextValorIntroduzido);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                codigoConversaoFinal = codigoConversaoInicial;
                codigoConversaoFinal += String.valueOf(spinnerOrigem.getSelectedItemPosition());
                codigoConversaoFinal += String.valueOf(spinnerDestino.getSelectedItemPosition());
                String origem = String.valueOf(spinnerOrigem.getSelectedItemPosition());
                String destino = String.valueOf(spinnerDestino.getSelectedItemPosition());

                //Validação do input
                validacaoDeInput(v, editTextValorIntroduzido, origem, destino, textViewValorConvertido);
            }
        });
    }

    private void validacaoDeInput(View v, EditText editTextValorIntroduzido, String origem, String destino, TextView textViewValorConvertido) {
        String str;
        if (codigoConversaoFinal.equals("0921") && valorIntroduzido < -459.67) {
            str = getResources().getString(R.string.excepcao_f);
            textViewValorConvertido.setText("");
            criarSnackbar(v, str);
        } else if (codigoConversaoFinal.equals("0901") && valorIntroduzido < 0) {
            str = getResources().getString(R.string.excepcao_k);
            textViewValorConvertido.setText("");
            criarSnackbar(v, str);
        } else if (codigoConversaoFinal.equals("0912") && valorIntroduzido < -273.15) {
            str = getResources().getString(R.string.excepcao_c);
            textViewValorConvertido.setText("");
            criarSnackbar(v, str);
        } else if (codigoConversaoFinal.equals("0910") && valorIntroduzido < -273.15) {
            str = getResources().getString(R.string.excepcao_c);
            textViewValorConvertido.setText("");
            criarSnackbar(v, str);
        } else if (codigoConversaoFinal.equals("0902") && valorIntroduzido < 0) {
            str = getResources().getString(R.string.excepcao_k);
            textViewValorConvertido.setText("");
            criarSnackbar(v, str);
        } else if (codigoConversaoFinal.equals("0920") && valorIntroduzido < -459.67) {
            str = getResources().getString(R.string.excepcao_f);
            textViewValorConvertido.setText("");
            criarSnackbar(v, str);
        } else if (editTextValorIntroduzido.getText().toString().trim().length() == 0) {
            str = getResources().getString(R.string.introduzir_valor2);
            textViewValorConvertido.setText("");
            criarSnackbar(v, str);
        } else if (!codigoConversaoInicial.equals("09") && valorIntroduzido <= 0) {
            str = getResources().getString(R.string.maior_que_0);
            textViewValorConvertido.setText("");
            criarSnackbar(v, str);
        } else if (origem.equals(destino)) { // Unidade de origem e destino são iguais
            textViewValorConvertido.setText(String.valueOf(valorIntroduzido));
        } else {
            valorConvertido = ConversoesClass.conversoes(codigoConversaoFinal, valorIntroduzido);
            textViewValorConvertido.setText(String.valueOf(arredondar.format(valorConvertido)));
            codigoConversaoFinal = codigoConversaoInicial;
        }
    }

    private void adicionaConversaoFavorita(final Spinner spinnerOrigem, final Spinner spinnerDestino) {
        Button buttonAdicionarConversaoFavorita = (Button) findViewById(R.id.buttonAdicionarConversaoFavorita);
        buttonAdicionarConversaoFavorita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FuncoesComuns.hideSoftKeyboard(ConversoesActivity.this);

                codigoConversaoFinal = codigoConversaoInicial;
                codigoConversaoFinal += String.valueOf(spinnerOrigem.getSelectedItemPosition());
                codigoConversaoFinal += String.valueOf(spinnerDestino.getSelectedItemPosition());

                final String unidadeOrigem = spinnerOrigem.getSelectedItem().toString();
                final String unidadeDestino = spinnerDestino.getSelectedItem().toString();
                String origem = String.valueOf(spinnerOrigem.getSelectedItemPosition());
                String destino = String.valueOf(spinnerDestino.getSelectedItemPosition());

                if (!origem.equals(destino)) {
                    String nomeMetodo;
                    nomeMetodo = unidadeOrigem + " > " + unidadeDestino;
                    long foiInserido = db.inserirFavorito(codigoConversaoFinal, nomeMetodo);
                    if (foiInserido != -1) {
                        Toast.makeText(ConversoesActivity.this, R.string.conversao_adicionada, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ConversoesActivity.this, R.string.erro_adicionar_favorito, Toast.LENGTH_SHORT).show();
                    }
                } else { //Quando a unidade de origem e destino são iguais, não há utilizade em adicionar conversão favorita
                    Toast.makeText(ConversoesActivity.this, R.string.conversao_sem_utilidade, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int setTituloConversao() {
        int idArrayUnidades = 1;
        switch (AppData.grandezaSelecionada) {
            case 1:
                setTitle(R.string.titulo_actividade_1);
                idArrayUnidades = R.array.unidades_area;
                codigoConversaoInicial = "01";
                break;
            case 2:
                setTitle(R.string.titulo_actividade_2);
                idArrayUnidades = R.array.unidades_binario;
                codigoConversaoInicial = "02";
                break;
            case 3:
                setTitle(R.string.titulo_actividade_3);
                idArrayUnidades = R.array.unidades_comprimento_distancia;
                codigoConversaoInicial = "03";
                break;
            case 4:
                setTitle(R.string.titulo_actividade_4);
                idArrayUnidades = R.array.unidades_divisas;
                codigoConversaoInicial = "04";
                break;
            case 5:
                setTitle(R.string.titulo_actividade_5);
                idArrayUnidades = R.array.unidades_energia;
                codigoConversaoInicial = "05";
                break;
            case 6:
                setTitle(R.string.titulo_actividade_6);
                idArrayUnidades = R.array.unidades_massa_peso;
                codigoConversaoInicial = "06";
                break;
            case 7:
                setTitle(R.string.titulo_actividade_7);
                idArrayUnidades = R.array.unidades_potencia;
                codigoConversaoInicial = "07";
                break;
            case 8:
                setTitle(R.string.titulo_actividade_8);
                idArrayUnidades = R.array.unidades_pressao;
                codigoConversaoInicial = "08";
                break;
            case 9:
                setTitle(R.string.titulo_actividade_9);
                idArrayUnidades = R.array.unidades_temperatura;
                codigoConversaoInicial = "09";
                break;
            case 10:
                setTitle(R.string.titulo_actividade_10);
                idArrayUnidades = R.array.unidades_velocidade;
                codigoConversaoInicial = "10";
                break;
            case 11:
                setTitle(R.string.titulo_actividade_11);
                idArrayUnidades = R.array.unidades_volume;
                codigoConversaoInicial = "11";
                break;
        }
        return idArrayUnidades;
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
