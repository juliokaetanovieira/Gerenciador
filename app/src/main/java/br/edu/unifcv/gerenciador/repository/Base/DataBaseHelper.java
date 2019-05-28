package br.edu.unifcv.gerenciador.repository.Base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import br.edu.unifcv.gerenciador.constants.DataBaseConstants;

//Sempre ira passar informaçoes de criação do banco
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATA_BASE_VERSION  = 1;
    private static final String DATA_BASE_NAME = "Convidados.db";

    private static final String SQL_CREATE_TABLE_CONVIDADOS =
            "Create table " + DataBaseConstants.CONVIDADO.TABLE_NAME +
                    " ( " + DataBaseConstants.CONVIDADO.COLUMNS.ID +
                    " integer primary key autoincrement " +
                    DataBaseConstants.CONVIDADO.COLUMNS.NOME + "text, " +
                    DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA + " integer);";


    private static final String SQL_CREATE_CONVIDADOS = "create table convidados(id int primary key autoincrement, nome varchar(255))";

    private static final String SQL_DROP_TABLE_CONVIDADOS =
            "drop table " + DataBaseConstants.CONVIDADO.TABLE_NAME;

    public DataBaseHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(this.SQL_CREATE_TABLE_CONVIDADOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int newVersion) {
        //db.execSQL(SQL_DROP_TABLE_CONVIDADOS);

    }
}
