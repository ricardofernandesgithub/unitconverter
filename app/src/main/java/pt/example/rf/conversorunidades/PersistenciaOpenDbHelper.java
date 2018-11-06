package pt.example.rf.conversorunidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricardo fernandes on 13/07/2017.
 */

public class PersistenciaOpenDbHelper extends SQLiteOpenHelper {

    public static final int VERSAO = 1;
    public static final String NOME_DB = "persistencia.db";

    public static final String ID = "ID";

    public static final String TABELA_CONVERSOES = "conversoes_favoritas";
    public static final String CODIGO_CONVERSAO = "CODIGO_CONVERSAO";
    public static final String NOME_CONVERSAO = "NOME_CONVERSAO";

    public static final String TABELA_DEFINICOES_1 = "precisao";
    public static final String CASAS_DECIMAIS = "CASAS_DECIMAIS";
    public static final String PRECISAO_SELECCIONADA = "PRECISAO_SELECCIONADA";

    public PersistenciaOpenDbHelper(Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    //Criar tabela de conversões favoritas, e tabelas para definições
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABELA_CONVERSOES + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CODIGO_CONVERSAO + " TEXT," + NOME_CONVERSAO + " TEXT)");
        db.execSQL("CREATE TABLE precisao ('ID' INTEGER PRIMARY KEY AUTOINCREMENT, 'CASAS_DECIMAIS' TEXT, 'PRECISAO_SELECCIONADA' BOOLEAN)");

        //Inserir definições relacionadas com o número de casas decimais do arredondamento
        db.execSQL("INSERT INTO precisao ( CASAS_DECIMAIS,PRECISAO_SELECCIONADA ) VALUES ('2', 'true'), ('3', 'false')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CONVERSOES);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_DEFINICOES_1);
        onCreate(db);
    }

    long resultado;

    //Método para inserir conversões favoritas
    public long inserirFavorito(String codigoConversao, String nomeConversao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CODIGO_CONVERSAO, codigoConversao);
        contentValues.put(NOME_CONVERSAO, nomeConversao);
        resultado = db.insert(TABELA_CONVERSOES, null, contentValues); //Retorna -1 em caso de inserção falhar
        return resultado;
    }

    //Método para ler uma conversão favorita
    ConversaoFavorita getConversaoFavorita(String nomeConversao) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_CONVERSOES, new String[]{ID, CODIGO_CONVERSAO, NOME_CONVERSAO}, NOME_CONVERSAO + "=?", new String[]{nomeConversao}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        ConversaoFavorita conversaoFavorita = new ConversaoFavorita(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return conversaoFavorita;
    }

    //Lista de conversões favoritas
    public List<ConversaoFavorita> lerListaConversoesFavoritas() {
        List<ConversaoFavorita> listaConversoesFavoritas = new ArrayList<>();

        String lerQuery = "SELECT * FROM " + TABELA_CONVERSOES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(lerQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ConversaoFavorita conversaoFavorita = new ConversaoFavorita();
                conversaoFavorita.setId(Integer.parseInt(cursor.getString(0)));
                conversaoFavorita.setCodigoConversao(cursor.getString(1));
                conversaoFavorita.setNomeConversao(cursor.getString(2));
                listaConversoesFavoritas.add(conversaoFavorita);
            } while (cursor.moveToNext());
        }
        return listaConversoesFavoritas;
    }

    //Apagar conversão favorita
    public int apagarConversaoFavorita(String codigoConversao) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABELA_CONVERSOES, CODIGO_CONVERSAO + "=?", new String[]{codigoConversao});
    }

    //Actualizar precisão seleccionada
    public void actualizarPrecisao(String nrCasasDecimais) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (nrCasasDecimais.equals("2")) {
            db.execSQL("UPDATE precisao SET  PRECISAO_SELECCIONADA = 'true' WHERE CASAS_DECIMAIS = '2';");
            db.execSQL("UPDATE precisao SET  PRECISAO_SELECCIONADA = 'false' WHERE CASAS_DECIMAIS = '3';");
        } else if (nrCasasDecimais.equals("3")) {
            db.execSQL("UPDATE precisao SET  PRECISAO_SELECCIONADA = 'false' WHERE CASAS_DECIMAIS = '2';");
            db.execSQL("UPDATE precisao SET  PRECISAO_SELECCIONADA = 'true' WHERE CASAS_DECIMAIS = '3';");
        }
    }

    //Ler precisao
    public boolean lerPrecisao() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_DEFINICOES_1, new String[]{ID, CASAS_DECIMAIS, PRECISAO_SELECCIONADA}, CASAS_DECIMAIS + "=?", new String[]{"2"}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        boolean precisaoDuasCdVerdadeira = Boolean.parseBoolean(cursor.getString(2));
        return precisaoDuasCdVerdadeira;
    }

    //Apagar todas as conversões favoritas
    public int apagarTodasConversoesFavoritas() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABELA_CONVERSOES, null, null);
    }

}
