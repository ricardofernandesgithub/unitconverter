package pt.example.rf.conversorunidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Testes {
    // Context of the app under test.
    private Context appContext = InstrumentationRegistry.getTargetContext();

    @Before
    public void deleteDatabase() {
        appContext.deleteDatabase("persistencia.db");
    }

    @Test
    public void createDatabaseTest() throws Exception {
        final PersistenciaOpenDbHelper persistenciaOpenDbHelper = new PersistenciaOpenDbHelper(appContext);
        final SQLiteDatabase db = persistenciaOpenDbHelper.getReadableDatabase();

        assertTrue("Erro ao abrir base de dados!", db.isOpen());
        persistenciaOpenDbHelper.close();
    }

    @Test
    public void conversaoFavoritaTesteCrud() {
        final PersistenciaOpenDbHelper persistenciaOpenDbHelper = new PersistenciaOpenDbHelper(appContext);
        final SQLiteDatabase db = persistenciaOpenDbHelper.getReadableDatabase();

        //Inserir (create)
        ConversaoFavorita conversaoFavorita = new ConversaoFavorita();
        conversaoFavorita.setCodigoConversao("0921");
        conversaoFavorita.setNomeConversao("Fahrenheit > Celsius");

        long id = persistenciaOpenDbHelper.inserirFavorito(conversaoFavorita.getCodigoConversao(), conversaoFavorita.getNomeConversao());
        int idConversaoFavorita = (int) id;
        conversaoFavorita.setId(idConversaoFavorita);
        assertTrue(idConversaoFavorita > 0);

        //Ler (read)
        ConversaoFavorita cfParaVerificar;
        cfParaVerificar = persistenciaOpenDbHelper.getConversaoFavorita(conversaoFavorita.getNomeConversao());
        cfParaVerificar.getId();
        cfParaVerificar.getCodigoConversao();
        cfParaVerificar.getNomeConversao();
        assertEquals(cfParaVerificar.getId(), 1);
        assertEquals(cfParaVerificar.getCodigoConversao(), "0921");
        assertEquals(cfParaVerificar.getNomeConversao(), "Fahrenheit > Celsius");

        //Apagar (delete)
        int apagarConversao = persistenciaOpenDbHelper.apagarConversaoFavorita(cfParaVerificar.getCodigoConversao());
        assertEquals(1, apagarConversao);
    }

    @Test
    public void testConversoes() throws Exception {
        ConversoesClass conversao = new ConversoesClass();

        double temperatura = conversao.conversoes("0921", 234.5);
        assertEquals(temperatura, 112.50, 0.001); // (calculado, esperado, delta)

        double massa = conversao.conversoes("0610", 1.0);
        assertEquals(massa, 2.20, 0.005);
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("pt.example.rf.conversorunidades", appContext.getPackageName());
    }
}
