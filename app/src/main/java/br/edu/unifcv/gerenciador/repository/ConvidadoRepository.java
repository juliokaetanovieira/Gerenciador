package br.edu.unifcv.gerenciador.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.edu.unifcv.gerenciador.constants.DataBaseConstants;
import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.repository.Base.DataBaseHelper;

public class ConvidadoRepository {

    private static ConvidadoRepository INSTANCE;

    private DataBaseHelper dataBaseHelper;

    private ConvidadoRepository(Context context){
        this.dataBaseHelper = new DataBaseHelper(context);

    }

    //VERIFICA SE JÁ EXISTE UMA INSTANCIA CRIADA, E VOLTARA A MESMA INSTANCIA
    public static synchronized ConvidadoRepository getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = new ConvidadoRepository(context);
        }
        return INSTANCE;

    }

    public boolean save(Convidado convidado) {
        try{

            SQLiteDatabase db = this.dataBaseHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(DataBaseConstants.CONVIDADO.COLUMNS.NOME, convidado.getNome());
            values.put(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA, convidado.getPresenca());

            db.insert(DataBaseConstants.CONVIDADO.TABLE_NAME, null, values);

        }catch (Exception e){
            return false;
        }
        return true;
    }

}
