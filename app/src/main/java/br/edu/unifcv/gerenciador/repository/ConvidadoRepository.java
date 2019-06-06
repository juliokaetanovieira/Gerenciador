package br.edu.unifcv.gerenciador.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.unifcv.gerenciador.constants.ConvidadoConstants;
import br.edu.unifcv.gerenciador.constants.DataBaseConstants;
import br.edu.unifcv.gerenciador.model.Convidado;
import br.edu.unifcv.gerenciador.model.ConvidadosCount;
import br.edu.unifcv.gerenciador.repository.base.DataBaseHelper;

public class ConvidadoRepository {

    private static ConvidadoRepository INSTANCE;
    private DataBaseHelper dataBaseHelper;

    private ConvidadoRepository(Context context) {
        this.dataBaseHelper = new DataBaseHelper(context);
    }

    public static synchronized ConvidadoRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ConvidadoRepository(context);
        }
        return INSTANCE;
    }


    public boolean save(Convidado convidado) {
        try {
            SQLiteDatabase db = this.dataBaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DataBaseConstants.CONVIDADO.COLUMNS.NOME, convidado.getNome());
            values.put(DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA, convidado.getPresenca());
            db.insert(DataBaseConstants.CONVIDADO.TABLE_NAME, null, values);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public List<Convidado> getConvidadoByQuery(String sql) {
        List<Convidado> convidados = new ArrayList<>();
        try {
            SQLiteDatabase db = this.dataBaseHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Convidado convidado = new Convidado();
                    convidado.setId(cursor.getInt(cursor.getColumnIndexOrThrow(
                            DataBaseConstants.CONVIDADO.COLUMNS.ID)));
                    convidado.setNome(cursor.getString(cursor.getColumnIndexOrThrow(
                            DataBaseConstants.CONVIDADO.COLUMNS.NOME)));
                    convidado.setPresenca(cursor.getInt(cursor.getColumnIndexOrThrow(
                            DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA)));
                    convidados.add(convidado);
                }
                cursor.close();
            }

        } catch (Exception e) {
            return convidados;
        }

        return convidados;
    }

    public ConvidadosCount getCount() {
        ConvidadosCount convidadosCount = new ConvidadosCount(0, 0,0);
        Cursor cursor;
        try{
            SQLiteDatabase db = this.dataBaseHelper.getReadableDatabase();
            cursor = db.rawQuery("select count(*) from " +
                    DataBaseConstants.CONVIDADO.TABLE_NAME + " where " +
                    DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA + " = " +
                    ConvidadoConstants.CONFIRMACAO.PRESENTE, null);
            if(cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                convidadosCount.setPresente(cursor.getInt(0));
                cursor.close();
            }

            cursor = db.rawQuery("select count(*) from " +
                    DataBaseConstants.CONVIDADO.TABLE_NAME + " where " +
                    DataBaseConstants.CONVIDADO.COLUMNS.PRESENCA + " = " +
                    ConvidadoConstants.CONFIRMACAO.AUSENTE, null);
            if(cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                convidadosCount.setAusente(cursor.getInt(0));
                cursor.close();
            }

            cursor = db.rawQuery("select count(*) from " +
                    DataBaseConstants.CONVIDADO.TABLE_NAME, null);
            if(cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                convidadosCount.setAusente(cursor.getInt(0));
                cursor.close();
            }

            return convidadosCount;
        }catch (Exception e) {
            return convidadosCount;
        }
    }
}
