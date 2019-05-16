package br.edu.unifcv.gerenciador.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATA_BASE_VERSION  = 1;
    private static final String DATA_BASE_NAME = "Convidados.db";

    private static final String SQL_CREATE_CONVIDADOS = "create table convidados(id int primary key autoincrement, nome varchar(255))";



    public DataBaseHelper(Context context) {
        super(context, this.DATA_BASE_NAME, null, this.DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.SQL_CREATE_CONVIDADOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
