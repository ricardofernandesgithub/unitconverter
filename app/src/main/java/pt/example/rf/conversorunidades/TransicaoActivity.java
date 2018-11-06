package pt.example.rf.conversorunidades;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class TransicaoActivity extends AppCompatActivity {

    ListView listView;
    PersistenciaOpenDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transicao);
        setTitle(getString(R.string.titulo_actividade_transicao));

        db = new PersistenciaOpenDbHelper(this);
        List<ConversaoFavorita> conversoesFavoritas = db.lerListaConversoesFavoritas();

        //Ler lista de conversões favoritas da base de dados
        int i = 0;
        String[] s = new String[conversoesFavoritas.size()];
        for (ConversaoFavorita cf : conversoesFavoritas) {
            s[i] = cf.getNomeConversao();
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s);

        //Ver lista de conversoes favoritas
        listView = (ListView) findViewById(R.id.listViewConversoesFavoritas);
        listView.setAdapter(adapter);

        //Click listener para iniciar actividade de conversão favorita
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AppData.nomeConversaoSeleccionada = (String) (listView.getItemAtPosition(position));
                ConversaoFavorita conversaoFavorita = db.getConversaoFavorita(AppData.nomeConversaoSeleccionada);
                AppData.codigoConversaoSeleccionada = conversaoFavorita.getCodigoConversao();

                Intent intent = new Intent(TransicaoActivity.this, ConversoesFavoritasActivity.class);
                startActivity(intent);
            }
        });

        //Voltar à actividade main
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonVoltar3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
